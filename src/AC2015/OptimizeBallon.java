package AC2015;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class OptimizeBallon {
	int coveredT[][];
	int bestMoveT[][];

	
	public OptimizeBallon(Problem pb)
	{
		this.coveredT = new int[pb.L][pb.T+1];// init to 0
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
		Pos fatherAtT[][][][] = new Pos[pb.T+1][pb.R][pb.C][pb.A+1];
		
		// Floyd MAsrshall algorithm. For all time, update all pos with score (ie onderated sum of customers served
			HashSet<Pos> posAtT = new HashSet<Pos>();
			double curbestPathScore[][][] = new double[pb.R][pb.C][pb.A+1];// Score to reach r,c,a
			
			posAtT.add(Start);
			
			for(int tt=0;tt<pb.T;tt++)
			{
				HashSet<Pos> nextPosAtT = new HashSet<Pos>();
				double nextBestPathScore[][][] = new double[pb.R][pb.C][pb.A+1];
				
				Sys.pln(" time : "+tt+" Npos accessed : " + posAtT.size());
				for(Pos curPos : posAtT)//TODO : replace by a matrix with all positions storing position Numbers+ a matrix of adjacency
				{
					for(int kk = 0;kk < curPos.moves.size();kk++)//(Move m : curPos.moves)
					{
						
						Move m = curPos.moves.get(kk);
						Pos pp = m.nextPos;
						double scoreMove = scoreAtT( pp,tt+1);
						double candidateScore = scoreMove+curbestPathScore[curPos.x][curPos.y][curPos.z];
						boolean update =false;
						
						if(pp != null)
						{
						
							if(nextPosAtT.contains(pp))
							{
								 if( nextBestPathScore[ pp.x][ pp.y][pp.z]<	candidateScore)
								 {
									 update = true;
								 }
								
							}else
							{
								nextPosAtT.add(pp);
								 update = true;
							}
							if(update)
							{
								nextBestPathScore[ pp.x][ pp.y][ pp.z]= candidateScore;
								fatherAtT[tt+1][pp.x][pp.y][pp.z] = curPos;
							}
						}
					
					}
				}
				
				
				//Swap cur & next
				posAtT 				= nextPosAtT;
				curbestPathScore    = nextBestPathScore;
				
			}
			
			
			
			// *********  Return best path to best final position
			double bestScore = 0;
			LinkedList<Integer> movListResult=null;
			Pos bestPosEnd=null;
			for(Pos curPos : posAtT)
			{
				if( curbestPathScore[curPos.x][curPos.y][curPos.z]  > bestScore)
				{
					bestScore = curbestPathScore[curPos.x][curPos.y][curPos.z];
					bestPosEnd = curPos;
				}
			}

			Ballon result = new Ballon(b.Num,pb.StartPos);
			LinkedList<Integer> ResultPath= new LinkedList<Integer>();
			Pos curPos = bestPosEnd;
			int tt = pb.T;//To check
			while(tt>0)
			{
				Pos prevPos  = fatherAtT[tt][curPos.x][curPos.y][curPos.z];
				
				for(Move m : prevPos.moves)
				{
					if(m.nextPos == curPos)
					{
						ResultPath.addFirst(m.aChange);
					}
				}
				tt--;
				curPos = prevPos;
			
			}
			
			for(int aChange : ResultPath)
			{
				result.addMove(aChange, pb);
			}
			
			// A last move was missing
			result.addMove(0,pb);
			
			
			return result;
		
	}
	
	
	public double 	scoreAtT(Pos p,int tt)
	{
		if(p==null)
			return -1;
		double score = 0;
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
