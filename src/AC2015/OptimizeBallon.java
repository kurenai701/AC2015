package AC2015;

import java.sql.NClob;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class OptimizeBallon {
	int coveredT[][];
	int bestMoveT[][];// [tt][jj] : best move to reached at tt pos Index jj
	static final int SCORESHIFT = 100000000;// Score shift, used to differentiate reached positions 
	
	public int PARAMAVOID;// USed to modify Cible cost computation
	public int HEAT[];// used to modify score provided by cibles (close to TABU Search)
	
	
	
	
	
	// Optimized mapping
	
	
	//int   coordinateMap[];// [ii] 
	int ScorePosAtT[];
	
	// [ii] Score at time T+1 at pos index ii :  135k*4 = 540kB
	AtomicIntegerArray nextScoreAtT; 
	int   successor[][];// [ii][jj] pos that can be reached from ii with move jj-1
	Pos   mappedPos[];  // Pos associated to index ii	
	int mappedXY[];
	boolean isNotMinAltitude[];
	PosXY   posXY[];
	
	
	// score at time T for index ii : 135k * 4 = 540 kB
	AtomicIntegerArray curScoreAtT;
	Random rand;
	
	
	
	//=> 3240 kB , should fit in L3 cache of core i7
	
	
	public OptimizeBallon(Problem pb)
	{
		this.coveredT = new int[pb.L][pb.T+2];// init to 0
	
		HEAT = new int[pb.L];
		this.PARAMAVOID = 400;
		for(int Ncible = 0; Ncible < pb.L;Ncible++)
		{
			HEAT[Ncible] = 1;
		}
		this.rand = new Random(99);

		// The mapping to index has been optimized for performance (mostly processor cache L1 & L2)
		int Nindex = 0;
		
		
		for(int a = 0;a<=pb.A;a++)
		{
		for(int c = 0;c<pb.C;c++)
		{
		for(int r = 0;r<pb.R;r++)
		{
			
				
					if(pb.AllPosMat[r][c][a]!=null)
					{
						pb.AllPosMat[r][c][a].numOpt = Nindex; 
						
						Nindex++;
						
					}
				}
			}
		}
		
		
		successor = new int[3][Nindex];// Initialized to 0, index of POSINVALID
		isNotMinAltitude = new boolean[Nindex];
		mappedPos = new Pos[Nindex];  	
		mappedXY  = new int[Nindex];
		posXY = new PosXY[pb.R*pb.C];
		
		
// Only iterate to apply index, order irrelevant
		//int curIndex = 0;
		for(int r = 0;r<pb.R;r++)
		{
			for(int c = 0;c<pb.C;c++)
			{
				posXY[r+c*pb.R] = new PosXY();
				posXY[r+c*pb.R].coverList = pb.AllPosMat[r][c][2].coverList;
				
				for(int a = 0;a<=pb.A;a++)
				{
					
					Pos p = pb.AllPosMat[r][c][a];
					if(p!=null)
					{
						isNotMinAltitude[p.numOpt] = (a>0);
						
						mappedPos[p.numOpt] = pb.AllPosMat[r][c][a];
						mappedXY[p.numOpt] =r+c*pb.R;
						
						
						for(Move m : pb.AllPosMat[r][c][a].moves)
						{
							if(m.aChange<2)
							{
								successor[m.aChange+1][p.numOpt] = m.nextPos.numOpt;
							}
							
						}	
					}
				}
			}
		}		
		
	}

	
	public Ballon optimize(Problem pb,Ballon b)
	{
		// Remove b impact
		updateEffect(pb,b,-1);
		
		Ballon bOut = findBestPath(pb,pb.StartPos,b);
		
		//Add new b effect.
		updateEffect(pb,bOut,1);
		return bOut;
	}
	
	public List<Ballon> optimize(Problem pb,List<Ballon> bList)
	{
		// Remove b impact
		for(Ballon b : bList)
		{
			updateEffect(pb,b,-1);
		}
		List<Ballon> bOutList = new ArrayList<Ballon>();
		for(Ballon b : bList)
		{
			bOutList.add(findBestPath(pb,pb.StartPos,b));
		}
		//Add new b effect.
		for(Ballon b : bOutList)
		{
			updateEffect(pb,b,1);
		}
		return bOutList;
	}
	
	
	private int tt;
	
	// Find the best path for Ballon b, starting at Start
	public Ballon findBestPath(Problem pb, Pos Start, Ballon b)
	{
		long tstart = System.nanoTime();

		int fatherAtT[][] = new int[pb.T+2][mappedPos.length];
		
		// Dijkstra algorithm with all distances of 1. For all time, update all pos with score (ie onderated sum of customers served
			curScoreAtT = new AtomicIntegerArray(mappedPos.length);
	
			//Add start position
			curScoreAtT.set(pb.StartPos.numOpt, SCORESHIFT);
			ScorePosAtT = new int[pb.R*pb.C];
			
			for(this.tt=0;this.tt<pb.T+1;this.tt++)
			{
//				long endTime = System.nanoTime();
//				Sys.pln("Ballon #"+b.Num+" took up to start of precompute "+(endTime-tstart)/1e6+" ms");
				
				
				// Precompute score of position  ~5ms
				//for(int r = 0;r< pb.R;r++)
				//{
				
				IntStream.range(0, pb.R).parallel().forEach(
							r -> {
					      
				for(int c=0;c<pb.C;c++)
				{
					ScorePosAtT[r+pb.R*c] = scoreAtT(posXY[r+pb.R*c].coverList,this.tt+1);
				}
		//		Sys.pln("c: " +r);
				//return r;
							}
					);
							
					
				
				
				
//				endTime = System.nanoTime();
//				Sys.pln("Ballon #"+b.Num+" took up to end of init "+(endTime-tstart)/1e6+" ms");

				
				nextScoreAtT=  new AtomicIntegerArray(mappedPos.length);// May need faster setting with multithread
				if(tt%100==0)
				{
					Sys.pln("Start time : "+tt+" Npos accessed : ???" );
				}
				
				
				
//				for(int curIndex = 0;curIndex<mappedPos.length;curIndex++)
//				{
			IntStream.range(0, mappedPos.length).parallel().forEach(
							curIndex -> {
					
					
					
//					Sys.pln("index "+curIndex + " score : "+curScoreAtT[curIndex]);
					
					
					
					// The following for loop can be parallelized, but need to synchronize scurScoreAtT & fatherAtT
					// Or to divide the index in non conflicting sets
					for(int kk = 0;kk < 3;kk++)
					{
						int nextIndex = successor[kk][curIndex];
						int candidateScore = 	curScoreAtT.get(curIndex);
						if(isNotMinAltitude[nextIndex])
						{
							candidateScore+= ScorePosAtT[ mappedXY[nextIndex] ];
						}
						
						
						// Critical section
						while(true)
						{
							int prevVal = nextScoreAtT.get(nextIndex);
							boolean needUpdate = candidateScore > prevVal;
							
							if(needUpdate )
							{				
	
								if(nextScoreAtT.compareAndSet(nextIndex, prevVal, candidateScore))
								{
									fatherAtT[tt+1][nextIndex] = curIndex;
									break;
								}
							}else
							{
								break;
							}
						}
						
						
						
					}
			
				});
				curScoreAtT = nextScoreAtT;

				
				
			}
			
			long endTime = System.nanoTime();
			Sys.pln("Ballon #"+b.Num+" took up to end of path search "+(endTime-tstart)/1e6+" ms");
			
			// *********  Return best path to best final position
			float bestScore = 0;
			int bestIndexEnd=-1;
			float pBestKeep = (float)1;
			
			for(int curIndex =0;curIndex <mappedPos.length;curIndex++)
			{
				
				if( curScoreAtT.get(curIndex)  > bestScore && rand.nextFloat()<pBestKeep)
				{
					bestScore = curScoreAtT.get(curIndex);
					bestIndexEnd = curIndex;
				}
//				Sys.pln("index "+curIndex + " score : "+curScoreAtT[curIndex]);
				
			}

			Ballon result = new Ballon(b.Num,pb.StartPos);
			LinkedList<Integer> ResultPath= new LinkedList<Integer>();
			int curIndex = bestIndexEnd;
			int tt = pb.T+1;//To check
			Pos curPos = mappedPos[curIndex];
			while(tt>0)
			{
				int prevIndex  = fatherAtT[tt][curIndex];
				Pos prevPos = mappedPos[prevIndex];
				for(Move m : prevPos.moves)
				{
					if(m.nextPos == curPos)
					{
						ResultPath.addFirst(m.aChange);
					}
				}
				tt--;
				curPos = prevPos;
				curIndex = prevIndex;
			}
			
			for(int aChange : ResultPath)
			{
				result.addMove(aChange, pb);
			}
			
			// A last move was missing
			//result.addMove(0,pb);
			
			endTime = System.nanoTime();
			Sys.pln("Ballon #"+b.Num+" took "+(endTime-tstart)/1e6+" ms");


			return result;
		
	}
	
	

	
	public int 	scoreAtT(List<Integer> coverList,int tt)
	{
		if(coverList==null) //No score at altitude 0
			return -1000000;
		int score = 0;
		for(int Ncible : coverList)
		{
			// For all cible covered by pos
				score +=Integer.max(0, 1000 - PARAMAVOID*coveredT[Ncible][tt]);//NEw cible reached // Equation to tune
		}
		return score;
		
	}
	

	
	
	
	public void updateEffect(Problem pb, Ballon curB,int direction)//set direction to 1 to add, to -1 to remove
	{
	for(int tt=0; tt<=pb.T;tt++)
	{
		

			if( tt < curB.posList.size())
			{
				
				Pos curPos = curB.posList.get(tt);
				if(curPos.z>0)
				{
					
				
				for(int Ncible : curPos.coverList)
				{
					coveredT[Ncible][tt] = Integer.max(0,coveredT[Ncible][tt]+direction);
				}
				
				}
			}//if				

		}//For tt
	}
	
	
	
	public void partialUpdateEffect(Problem pb, PartialPath pp,int direction)//set direction to 1 to add, to -1 to remove
	{
for(int tt=pp.Tstart; tt<pp.Tstart+pp.posList.size();tt++)
{
	
		int curPosIndex = pp.posList.get(tt-pp.Tstart);
		Pos curPos = mappedPos[curPosIndex];
		if(curPos.z>0)
		{
			for(int Ncible : curPos.coverList)
			{
				coveredT[Ncible][tt] = Integer.max(0,coveredT[Ncible][tt]+direction);
			}
			
		}
}//For tt
	}
		
}
