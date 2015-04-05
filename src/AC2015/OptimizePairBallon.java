package AC2015;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class OptimizePairBallon {
	OptimizeBallon optB;
	final static int DURATIONOPTMIDDLE = 20;// Number of cycles to optimize for in middle of path
	final static int DURATIONOPTEND = 10;		// Number of cycles to optimize for in end of path
	
	//optimize a pair of Ballon. The Ballons are updated directly
	void    optimizeBallonPair(
			Solution S,
			int indexBallonA, // index of first Ballon to optimize
			int indexBallonB, // index of second Ballon to optimize
			Problem pb)
			{
		int initialScore = S.GetScore();
		Ballon A = S.ballons[indexBallonA];
		Ballon B = S.ballons[indexBallonA];
		
				// Remove A & B actions
			optB.updateEffect(pb, A, -1);
			optB.updateEffect(pb, B, -1);
				//Optimize pair for all times
			for(int curT=0; curT<pb.T-DURATIONOPTMIDDLE;curT+=DURATIONOPTMIDDLE/2)
			{
				
				HashSet<PartialPath> AllPathForA = getAllPathFromTo(
						A.posList.get(curT).numOpt, 			//Index of start position
						A.posList.get(curT+DURATIONOPTMIDDLE).numOpt, 			//Index of end position
						DURATIONOPTMIDDLE, 		//Duration of path
						pb,
						curT);			//Start time of path

				HashSet<PartialPath> AllPathForB = getAllPathFromTo(
						B.posList.get(curT).numOpt, 			//Index of start position
						B.posList.get(curT+DURATIONOPTMIDDLE).numOpt, 			//Index of end position
						DURATIONOPTMIDDLE, 		//Duration of path
						pb,
						curT);			//Start time of path
				
				
				List<PartialPath> tempPaths =    getOptimalPathPair( 
						AllPathForA, // All possible paths for Ballon A
						AllPathForB, // All possible paths for Ballon B 
						curT,// Starting time of optimisation
						pb);
				
				// update Ballons with new paths
				PartialPath pA = tempPaths.get(0);
				PartialPath pB = tempPaths.get(1);
				
				A.updatePartialPath( curT,pA , pb ,optB);
				B.updatePartialPath( curT,pB , pb, optB);
			}//Optimize middle of paths
			
			
			// Optimize end of path
			{
				int curT = pb.T+1-DURATIONOPTEND;
				
				HashSet<PartialPath> AllPathForA = getAllPathFromTo(
						A.posList.get(curT).numOpt, 			//Index of start position
						-1, 			//Index of end position (-1 : no predefined position)
						DURATIONOPTEND, 		//Duration of path
						pb,
						curT);			//Start time of path

				HashSet<PartialPath> AllPathForB = getAllPathFromTo(
						B.posList.get(curT).numOpt, 			//Index of start position
						-1, 			//Index of end position (-1 : no predefined position)
						DURATIONOPTEND, 		//Duration of path
						pb,
						curT);			//Start time of path
				
				
				List<PartialPath> tempPaths =    getOptimalPathPair( 
						AllPathForA, // All possible paths for Ballon A
						AllPathForB, // All possible paths for Ballon B 
						curT,// Starting time of optimisation
						pb);
				
				// update Ballons with new paths
				PartialPath pA = tempPaths.get(0);
				PartialPath pB = tempPaths.get(1);
				
				A.updatePartialPath( curT,pA , pb ,optB);
				B.updatePartialPath( curT,pB , pb, optB);
			}//Optimize end of paths
			

	
			// Check final score. It has been updated since the Ballons A and B inside it have been updated. IT shall be at least as good as previous score
			int finalScore = S.GetScore();
			Sys.pln("Path optimizing index " + indexBallonA + " : " + indexBallonB + " initScore: "+ initialScore + " final :" + finalScore );
			
			if(finalScore< initialScore)
			{
				Sys.pln("ERROR!!! : Optimisation should always be improving");
			}
	
			}
	
	
	
	
	// List all the different paths from start to end position reachable in time T.
	// Two paths are different if they reach different unreached cibles at different times
	HashSet<PartialPath> getAllPathFromTo(
			int start, 			//Index of start position
			int end, 			//Index of end position
			int duration, 		//Duration of path
			Problem pb,
			int Tstart)			//Start time of path
	{
		HashSet<PartialPath> resp = new HashSet<PartialPath>();//A hashSet is used to avoid outputting 2 times paths with same cibles activated
		if(duration == 0 )
		{
			if(end == -1 || start==end)
			{
				resp.add(new PartialPath(start,optB,pb,Tstart));
				return resp;
			}
			else
			{
				return null;
			}
		}
		
		
		for(int ii=0;ii<3;ii++ )
		{
			int nextP = optB.successor[start][ii];
			
			HashSet<PartialPath> temp = getAllPathFromTo(nextP, end, duration-1,pb, Tstart+1);
			for(PartialPath endOfPath : temp)
			{
				endOfPath.posList.add(0, start);
				endOfPath.Tstart = Tstart;
				resp.add( endOfPath  );
			}
		}
		
		
		return resp;
		
	}
 
	
	
	
	
	
	// Answer the optimal pair of path for Ballon A and B (pair of path with best global score)
	List<PartialPath>    getOptimalPathPair( 
			Set<PartialPath> AllPathForA, // All possible paths for Ballon A
			Set<PartialPath> AllPathForB, // All possible paths for Ballon B 
			int Tstart,// Starting time of optimisation
			Problem pb)
			{
		
		TreeSet<PartialPath> AllPathForASorted = new TreeSet<PartialPath>();
		AllPathForASorted.addAll(AllPathForA); 
		TreeSet<PartialPath> AllPathForBSorted = new TreeSet<PartialPath>();
		AllPathForASorted.addAll(AllPathForB); 
		int bestScore = 0;//TODO : initialize with current solution score
		
		
		List<PartialPath> resp=null;
		for(PartialPath curPathA : AllPathForA)
		{
			int bestScoreA = curPathA.getScore();
			
			for(PartialPath curPathB : AllPathForB)
			{
				if(curPathB.getScore()+bestScoreA  < bestScore)
				{
					break;// Can not improve bestScore, do not look for worst path of B
				}
				
				int score = scoreCombined( curPathA, curPathB, pb);
				if(score>bestScore)
				{
					bestScore = score;
					resp = new ArrayList<PartialPath>();
					resp.add(curPathA);
					resp.add(curPathB);
				}
			}
			
			
			
		}
		
		
		return resp;
		
	}
	
	
	
	int scoreCombined( PartialPath curPathA,PartialPath curPathB, Problem pb)
	{
		int score = 0;
		score = curPathA.getScore();
		optB.partialUpdateEffect(pb, curPathA, 1);
		score += curPathB.getScore();
		optB.partialUpdateEffect(pb, curPathA, -1);
		return score;
		
		
	}
	
	
	

}
