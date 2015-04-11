package AC2015;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;



/**
 * @author Alyx
 *
 */
public class OptimizePairBallon {
	OptimizeBallon optB;
	final static int DURATIONOPTMIDDLE = 14;//TODO// Number of cycles to optimize for in middle of path
	final static int DURATIONOPTEND = 8;		// Number of cycles to optimize for in end of path
	
	public OptimizePairBallon(OptimizeBallon optB)
	{
		this.optB = optB;
	}
	
	/**
	 * optimize a pair of Ballon. The Ballons are updated directly	
	 * @param S Solution to optimize
	 * @param indexBallonA Index of first Ballon to Optimize
	 * @param indexBallonB Index of second Ballon to optimize
	 * @param pb Problem
	 */
	public void optimizePair(
			Solution S,
			int indexBallonA, 
			int indexBallonB, 
			Problem pb)
			{
		if(indexBallonA == indexBallonB)
		{
			return;
		}
		
		
		
		
		int initialScore = S.GetScore();
		Ballon A = S.ballons[indexBallonA];
		Ballon B = S.ballons[indexBallonB];
		
				// Remove A & B actions
			optB.updateEffect(pb, A, -1);
			optB.updateEffect(pb, B, -1);
			
			
//**************  Optimize pair for all times, not working
			Ballon Acopy, Bcopy;
			if(DURATIONOPTMIDDLE/2>0)
			{
			for(int curT=0; curT<pb.T-DURATIONOPTMIDDLE;curT+=DURATIONOPTMIDDLE/2)
			{
				if(Common.DEBUG==2)
				{
					Sys.pln("Previous path for A:" + A.posList.subList(curT, curT+DURATIONOPTMIDDLE+1));
					Sys.pln("Previous path for B:" + B.posList.subList(curT, curT+DURATIONOPTMIDDLE+1));	
					//Acopy = Common.DeepCopy(A);
					//Bcopy = Common.DeepCopy(B);
				}
				
				boolean isColliding = false;
				for(int dec = 0;dec<=DURATIONOPTMIDDLE;dec++)
				{
					Pos posA = A.posList.get(curT+dec);
					Pos posB = B.posList.get(curT+dec);
					int dist = distXY(posA, posB, pb);
					if(dist<=2*pb.V)
						isColliding = true;
				}
				if(!isColliding)
					continue;
				
				
				
				
				
				PathStore=new HashMap<Long, HashSet<PartialPath>>();
				HashSet<PartialPath> AllPathForA = getAllPathFromTo(
						A.posList.get(curT).numOpt, 			//Index of start position
						A.posList.get(curT+DURATIONOPTMIDDLE).numOpt, 			//Index of end position
						DURATIONOPTMIDDLE, 		//Duration of path
						pb,
						curT);			//Start time of path
				if(AllPathForA.size()==0)
				{
					Sys.pln("Error, no path found for A!");
					throw(new RuntimeException());
				}
				PathStore=new HashMap<Long, HashSet<PartialPath>>();
				HashSet<PartialPath> AllPathForB = getAllPathFromTo(
						B.posList.get(curT).numOpt, 			//Index of start position
						B.posList.get(curT+DURATIONOPTMIDDLE).numOpt, 			//Index of end position
						DURATIONOPTMIDDLE, 		//Duration of path
						pb,
						curT);			//Start time of path
				if(AllPathForB.size()==0)
				{
					Sys.pln("Error, no path found for B!");
					throw(new RuntimeException());
				}
		
			
		
				
				
				List<PartialPath> tempPaths =    getOptimalPathPair( 
						AllPathForA, // All possible paths for Ballon A
						AllPathForB, // All possible paths for Ballon B 
						curT,// Starting time of optimisation
						pb);
				
				// update Ballons with new paths
				PartialPath pA = tempPaths.get(0);
				PartialPath pB = tempPaths.get(1);

				if(Common.DEBUG==1)
				{
					Sys.pln("Partialpath for A:   " + pA);
					Sys.pln("Partialpath for B:   " + pB);
				}
				
				A.updatePartialPath( curT,pA , pb ,optB);
				B.updatePartialPath( curT,pB , pb, optB);
				
				if(Common.DEBUG==2)
				{
					optB.updateEffect(pb, A, 1);
					optB.updateEffect(pb, B, 1);
					
					int middleScore = S.GetScore();
					Sys.pln("Path optimizing index " + indexBallonA + " startT "+ curT+": " + indexBallonB + " initScore: "+ initialScore + " middle :" + middleScore );
				
					if(middleScore< initialScore)
					{
						Sys.pln("pA : "+ pA);
						Sys.pln("pB : "+ pB);
						optB.updateEffect(pb, A, -1);
						optB.updateEffect(pb, B, -1);
						List<PartialPath> tempPaths3 =    getOptimalPathPair( 
								AllPathForA, // All possible paths for Ballon A
								AllPathForB, // All possible paths for Ballon B 
								curT,// Starting time of optimisation
								pb);
								
						
						Sys.pln("ERROR!!! : Optimisation shall always be improving");
						throw(new RuntimeException());
					}
					optB.updateEffect(pb, A, -1);
					optB.updateEffect(pb, B, -1);
				}
				
		
				
			}//Optimize middle of paths
			}
		
			
			
//***************   Optimize end of path, Not working , still reducing score.... TODO : reecrire sans cut&paste
			if(DURATIONOPTEND>0)
			{
				int curT = pb.T-DURATIONOPTEND+0;//TODO
				
				// Skip Ballon too far away
				boolean isColliding = false;
				for(int dec = 0;dec<=DURATIONOPTEND;dec++)
				{
					Pos posA = A.posList.get(curT+dec);
					Pos posB = B.posList.get(curT+dec);
					int dist = distXY(posA, posB, pb);
					if(dist<=2*pb.V)
						isColliding = true;
				}
				if(isColliding)
				{
				
					long timers = System.nanoTime();
					PathStore=new HashMap<Long, HashSet<PartialPath>>();
					HashSet<PartialPath> AllPathForA = getAllPathFromTo(
							A.posList.get(curT).numOpt, 			//Index of start position
							-1, 			//Index of end position (-1 : no predefined position)
							DURATIONOPTEND, 		//Duration of path
							pb,
							curT);			//Start time of path
					PathStore=new HashMap<Long, HashSet<PartialPath>>();
					HashSet<PartialPath> AllPathForB = getAllPathFromTo(
							B.posList.get(curT).numOpt, 			//Index of start position
							-1, 			//Index of end position (-1 : no predefined position)
							DURATIONOPTEND, 		//Duration of path
							pb,
							curT);			//Start time of path
					
				//	Sys.pln("Allpath took "+(System.nanoTime()-timers)/1e6+"ms"	);
					List<PartialPath> tempPaths =    getOptimalPathPair( 
							AllPathForA, // All possible paths for Ballon A
							AllPathForB, // All possible paths for Ballon B 
							curT,// Starting time of optimisation
							pb);
					Sys.pln("Allpath+getOptimal took "+(System.nanoTime()-timers)/1e6+"ms"	);
					// update Ballons with new paths
					PartialPath pA = tempPaths.get(0);
					PartialPath pB = tempPaths.get(1);
					
				//	pA.posList.add( optB.successor[0][pA.posList.get(pA.posList.size()-1)]     );
				//	pB.posList.add( optB.successor[0][pB.posList.get(pB.posList.size()-1)]     );
					
					A.updatePartialPath( curT,pA , pb ,optB);
					B.updatePartialPath( curT,pB , pb, optB);
				}//Optimize end of paths
			}
				
				//Put back effects
				optB.updateEffect(pb, A, 1);
				optB.updateEffect(pb, B, 1);
		
				// Check final score. It has been updated since the Ballons A and B inside it have been updated. IT shall be at least as good as previous score
			
				int finalScore = S.GetScore();
				Sys.pln("Path optimizing index " + indexBallonA + " : " + indexBallonB + " initScore: "+ initialScore + " final :" + finalScore );
				/*
				if(finalScore< initialScore)
				{
					Sys.pln("ERROR!!! : Optimisation should always be improving");
			//		throw(new RuntimeException());
				}
				*/
			}
	
	
	int distXY(Pos posA, Pos posB, Problem pb)
	{
		
		
		int x = Math.abs(posA.x-posB.x); 
		int y =	Math.min(  Math.abs(posA.y-posB.y) , pb.C-Math.abs(posA.y-posB.y));
		
		return (int)Math.ceil(Math.sqrt( (double)(x*x+y*y)));
		
	}
			
	
	
	HashMap<Long, HashSet<PartialPath>>  PathStore;
	
	/**
	 * List all the different paths from start to end position reachable in time T.
	 * Two paths are different if they reach different unreached cibles at different times
	 * @param start Index of start position
	 * @param end  Index of end position
	 * @param duration Duration of path
	 * @param pb Problem
	 * @param Tstart Start time of path
	 * @return All the paths from start to end
	 */
	HashSet<PartialPath> getAllPathFromTo(
			int start, 			
			int end, 			
			int duration, 		
			Problem pb,
			int Tstart)			
	{
		Long LookUpKey = (long)start + 1000000000*duration;  // unique for all cases
		if(PathStore.containsKey(LookUpKey))
		{
			return PathStore.get(LookUpKey);
		}
		
		
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
		
			
			
			if(end>-1)
			{
				Pos Pstart = optB.mappedPos[start];
				Pos Pend = optB.mappedPos[end];
				int dy = Math.min(  Math.abs(  Pend.y - Pstart.y   ) , pb.C- Math.abs( Pend.y - Pstart.y )  );
				if( 
			   ( !(Pend.z==0&&Pend.x==0)&
			(
				Math.abs(Pend.z - Pstart.z)>duration ||
				Math.abs(Pend.x - Pstart.x)   >duration*5 ||
				  dy>duration*8)))
				{
					PathStore.put(LookUpKey,null);
					return null;
				}
			}
		
		
		
		
		for(int ii=0;ii<3;ii++ )
		{
			int nextP = optB.successor[ii][start];
			
			HashSet<PartialPath> temp = getAllPathFromTo(nextP, end, duration-1,pb, Tstart+1);
			if(temp!=null&&temp.size()>0)
			{
				for(PartialPath endOfPath : temp)
				{
					PartialPath endOfPathClone = new PartialPath(endOfPath);
					endOfPathClone.posList.add(0, start);
					endOfPathClone.Tstart = Tstart;
//					Sys.pln("Adding path "+endOfPath+" Hashcode:"+endOfPath.hashCode());
					resp.add( endOfPathClone  );
				}
			}
		}
		
		PathStore.put(LookUpKey,resp);
		return resp;
		
	}
 
	
	
	
	
	
	
	/**
	 * // Answer the optimal pair of path for Ballon A and B (pair of path with best global score)
	 * @param AllPathForA All possible paths for Ballon A
	 * @param AllPathForB All possible paths for Ballon B 
	 * @param Tstart Starting time of optimisation
	 * @param pb Problem
	 * @return The optimal partialpath for Ballon A and Ballon B
	 */
	List<PartialPath>    getOptimalPathPair( 
			Set<PartialPath> AllPathForA, 
			Set<PartialPath> AllPathForB, 
			int Tstart,
			Problem pb)
			{
		
		TreeSet<PartialPath> AllPathForASorted = new TreeSet<PartialPath>();
		AllPathForASorted.addAll(AllPathForA); 
		TreeSet<PartialPath> AllPathForBSorted = new TreeSet<PartialPath>();
		AllPathForBSorted.addAll(AllPathForB); 
		int bestScore = Integer.MIN_VALUE;//TODO : initialize with current solution score
		
		
		List<PartialPath> resp=null;
		boolean earlybreak = false;
		for(PartialPath curPathA : AllPathForASorted)
		{
			int bestScoreA = curPathA.getScore();
			int ii = 0;
			for(PartialPath curPathB : AllPathForBSorted)
			{
				
				if(Common.DEBUG==1)
				{
					Sys.pln( "opt "+curPathA + "\n  B:"+curPathB+"\n ORIG ");
				}
				
				int bestScoreB =curPathB.getScore(); 
				if(bestScoreB+bestScoreA  <= bestScore)
				{
					if(Common.DEBUG==1)
					{
						Sys.pln("BRK") ;
					}
					if(ii==0)
						earlybreak=true;
					break;// Can not improve bestScore, do not look for worst path of B
				}
				ii++;
				
				int score = scoreCombined( curPathA, curPathB, pb);
				
				if(Common.DEBUG==1)
				{
					Sys.pln( "opt "+curPathA + "\n  B:"+curPathB+"\n score : "+score);
				}
				
				if(score>bestScore)
				{
					bestScore = score;
					resp = new ArrayList<PartialPath>();
					resp.add(curPathA);
					resp.add(curPathB);
				}
			}
			if(earlybreak)
				break;
			
			
			
		}
		
		
		return resp;
		
	}
	
	
	
	/**
	 * The score obtained with the two partial path applied
	 * @param curPathA PartialPath for Ballon A
	 * @param curPathB PArtialPath for Ballon B
	 * @param pb	Problem
	 * @return The resulting score
	 */
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
