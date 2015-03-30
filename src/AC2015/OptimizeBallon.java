package AC2015;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class OptimizeBallon {
	int coveredT[][];
	int bestMoveT[][];// [tt][jj] : best move to reached at tt pos Index jj
	static final float SCORESHIFT = 60000;// Score shift, used to differentiate reached positions 
	
	
	// Optimized mapping
	
	
	//int   coordinateMap[];// [ii] 
	float ScorePosAtT[][];
	
	// [ii] Score at time T+1 at pos index ii :  135k*4 = 540kB
	float nextScoreAtT[]; 
	int   successor[][];// [ii][jj] pos that can be reached from ii with move jj-1
	Pos   mappedPos[];  // Pos associated to index ii	
	// score at time T for index ii : 135k * 4 = 540 kB
	float curScoreAtT[];
	// [ii][jj]Score at time T+1 at pos index ii after move jj-1 : 3*135k*4 = 1620kB
	float nextScoreAtTDependingAchange[][];// indices are optimized for Multithreading
	
	
	
	//=> 3240 kB , should fit in L3 cache of core i7
	
	
	public OptimizeBallon(Problem pb)
	{
		this.coveredT = new int[pb.L][pb.T+1];// init to 0

		// The mapping to index has been optimized for performance (mostly processor cache L1 & L2)
		int Nindex = 0;
		
		
		for(int r = 0;r<pb.R;r++)
		{
			for(int c = 0;c<pb.C;c++)
			{
				for(int a = 0;a<=pb.A;a++)
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
		mappedPos = new Pos[Nindex];  	
// Only iterate to apply index, order irrelevant
		for(int r = 0;r<pb.R;r++)
		{
			for(int c = 0;c<pb.C;c++)
			{
				for(int a = 0;a<=pb.A;a++)
				{
					Pos p = pb.AllPosMat[r][c][a];
					if(p!=null)
					{
						mappedPos[p.numOpt] = pb.AllPosMat[r][c][a];
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

	
	// Find the best path for Ballon b, starting at Start
	public Ballon findBestPath(Problem pb, Pos Start, Ballon b)
	{
		long tstart = System.nanoTime();

		int fatherAtT[][] = new int[pb.T+1][mappedPos.length];
		
		// Dijkstra algorithm with all distances of 1. For all time, update all pos with score (ie onderated sum of customers served
			curScoreAtT = new float[mappedPos.length];
	
			//Add start position
			curScoreAtT[pb.StartPos.numOpt] = SCORESHIFT;
			ScorePosAtT = new float[pb.R][pb.C];
			
			
			for(int tt=0;tt<pb.T;tt++)
			{
//				long endTime = System.nanoTime();
//				Sys.pln("Ballon #"+b.Num+" took up to start of precompute "+(endTime-tstart)/1e6+" ms");
				
				
				// Precompute score of position  ~5ms
				for(int r = 0;r< pb.R;r++)
				{
					for(int c=0;c<pb.C;c++)
					{
						Pos p = pb.AllPosMat[r][c][2];
						ScorePosAtT[r][c] = scoreAtT(p,tt+1);
					}
				}
				
				
//				endTime = System.nanoTime();
//				Sys.pln("Ballon #"+b.Num+" took up to end of init "+(endTime-tstart)/1e6+" ms");

				
				nextScoreAtT= new float[mappedPos.length];// May need faster setting with multithread
				if(tt%10==0)
				{
					Sys.pln("Start time : "+tt+" Npos accessed : ???" );
				}
				for(int curIndex = 0;curIndex<mappedPos.length;curIndex++)
				{
//					Sys.pln("index "+curIndex + " score : "+curScoreAtT[curIndex]);
					
					// The following for loop can be parallelized, but need to synchronize scurScoreAtT & fatherAtT
					// Or to divide the index in non conflicting sets
					for(int kk = 0;kk < 3;kk++)
					{
						int nextIndex = successor[kk][curIndex];
						Pos p = mappedPos[nextIndex];
						float candidateScore = 	curScoreAtT[curIndex];
						if(p.z>0)
							candidateScore+= ScorePosAtT[ p.x  ][p.y];// Can be optimised scoreAtT(mappedPos[nextIndex],tt+1);
						
						if(candidateScore > nextScoreAtT[nextIndex])
						{					
							nextScoreAtT[nextIndex] = candidateScore;
							fatherAtT[tt+1][nextIndex] = curIndex;				
						}
						
					}
					
				}
				curScoreAtT = nextScoreAtT;

				
				
			}
			
			long endTime = System.nanoTime();
			Sys.pln("Ballon #"+b.Num+" took up to end of path search "+(endTime-tstart)/1e6+" ms");
			
			// *********  Return best path to best final position
			float bestScore = 0;
			int bestIndexEnd=-1;
			for(int curIndex =0;curIndex <mappedPos.length;curIndex++)
			{
				
				if( curScoreAtT[curIndex]  > bestScore)
				{
					bestScore = curScoreAtT[curIndex];
					bestIndexEnd = curIndex;
				}
//				Sys.pln("index "+curIndex + " score : "+curScoreAtT[curIndex]);
				
			}

			Ballon result = new Ballon(b.Num,pb.StartPos);
			LinkedList<Integer> ResultPath= new LinkedList<Integer>();
			int curIndex = bestIndexEnd;
			int tt = pb.T;//To check
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
			result.addMove(0,pb);
			
			endTime = System.nanoTime();
			Sys.pln("Ballon #"+b.Num+" took "+(endTime-tstart)/1e6+" ms");


			return result;
		
	}
	
	
	public float 	scoreAtT(Pos p,int tt)
	{
		if(p==null) //No score at altitude 0
			return -1000000;
		if(p.z==0)
			return 0;
		float score = 0;
		for(int Ncible : p.coverList)
		{
			// For all cible covered by pos
				score += 1/(1+10000*coveredT[Ncible][tt]);//NEw cible reached // Equation to tune
		}
		return score;
		
	}

	
	
	
	public void updateEffect(Problem pb, Ballon curB,int direction)//set direction to 1 to add, to -1 to remove
	{
	for(int tt=0; tt<=pb.T;tt++)
	{
		int covered[] = new int[pb.L];// init to 0
		boolean stillAlive = false;
		

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
				stillAlive=true;
			}//if				
			if(!stillAlive)
			{
			break;//Fast evaluate end
			}

		}//For tt
	}
	
	
	
}
