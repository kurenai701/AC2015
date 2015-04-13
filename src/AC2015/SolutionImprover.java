package AC2015;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SolutionImprover {

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		
//	}

	public SolutionImprover()
	{

	}
	
	LinkedList<Integer> tabuList = new LinkedList<Integer>();
	int TABULENGTH = 50;
	
	public Solution TryImprove(Solution oldSol, Random rand, Problem pb, OptimizeBallon optB)
	{
		// Deep copy is necessary so as not to mess up "old sol"
			//////////////////////////////////////
		// TODO Code logic to improve solution
		//////////////////////////////////////

		List<Ballon> ballonsRemoved = new ArrayList<Ballon>();
    	 int bestscore = oldSol.GetScore();
		int Nopt = 0;
		int NBUPDATE = 1;
		int MAXTEST = 30;
		int cnt = MAXTEST;;
		for(int Nb = 0;Nb<NBUPDATE;Nb++)
		{
			while(cnt>0)
			{
				int ii = rand.nextInt(pb.B);
				if( !tabuList.contains(ii))
				{
					ballonsRemoved.add(oldSol.ballons[ii]);
					tabuList.add(ii);
					while(tabuList.size()>TABULENGTH)
						tabuList.pop();
					break;
				}
				cnt--;
			}
		}
			 List<Ballon> bOutList = optB.optimize(pb,ballonsRemoved);
			 
			 for(Ballon b : bOutList)
				{
				while(b.posList.size()<pb.T+1)
				{
					b.addMove(0, pb);
				}
				}
				
				for(Ballon b : bOutList)
				{
					oldSol.ballons[b.Num] = b;
				}		
				
				
		///////////////// Third optimization part ////////////////////
				/*
		long ticTime = System.nanoTime();
		boolean first = true;
		double PKEEP=0.05;
		
		if(		optB.lastBSet != null && 
				optB.curBSet  != null && 
				optB.lastBSet.size()>0 && 
				optB.curBSet.size()>0 )
		{
			Ballon bestB0 = oldSol.ballons[optB.lastBSet.first().Num];
			Ballon bestB1 = oldSol.ballons[optB.curBSet.first().Num];
			int bestScore = Integer.MIN_VALUE;
			//Removes Ballons from optB
			optB.updateEffect(pb,bestB0,-1);
			optB.updateEffect(pb,bestB1,-1);
			for(BallonIndex B0 : optB.lastBSet)
			{
				if(rand.nextDouble()>PKEEP && !first)
					continue;
				
				while(B0.posList.size()<pb.T+1)
				{
					B0.addMove(0, pb);
				}
				
				
				oldSol.ballons[ bestB0.Num ] = B0;
				for(BallonIndex B1 : optB.curBSet)
				{
					if(rand.nextDouble()>PKEEP && !first)
						continue;
					first = false;
					while(B1.posList.size()<pb.T+1)
					{
						B1.addMove(0, pb);
					}
					
					oldSol.ballons[ bestB1.Num ] = B1;
					int curScore = oldSol.GetScore();
					if(curScore>bestscore)
					{
						bestscore = curScore;
						bestB0 = B0;
						bestB1 = B1;
					}
				}
			}
			oldSol.ballons[ bestB0.Num ] = bestB0;
			oldSol.ballons[ bestB1.Num ] = bestB1;
			
			// Restore optB
			optB.updateEffect(pb,bestB0,1);
			optB.updateEffect(pb,bestB1,1);
		}
		Sys.pln("Third opt took "+ (System.nanoTime()-ticTime)/1e6 + " ms");
		*/
		//////////////////////////////////////
		return oldSol;


	}


	public Solution IterateImprover( Solution initSol, int nbIterations, int acceptIterationNoImprove,Problem pb)
	{
		
 		Solution solCurrent = initSol;
		Solution solTry;	

		Solution bestSolution = Common.DeepCopy(initSol);
		bestSolution.SaveSolutionAsRaw("BestSolutionInProcess.ser");
		bestSolution.pb = pb;
		

		OptimizeBallon OptB = new OptimizeBallon(pb);
		for(Ballon b : initSol.ballons)
		{
			OptB.updateEffect(pb, b, 1);
		}
		

		bestSolution.pb = pb;
		int bestScore = bestSolution.GetScore();
		int globalBestScore = bestScore;
		// itérer
		SecureRandom rand = new  SecureRandom();//much better random generator than java.util.Random. slower, but not releveant here.
		double PRESTORE = 0.1;
		int  PARAMAVOIDCOEFF=9999;//-1;
		float  PARAMHEAT =  0;
		double PSTOPBACKTRACKINGREF = 0.03;
		double PSTOPBACKTRACKING = PSTOPBACKTRACKINGREF;
		boolean improved = false;
		int 	countIterNoImproveGlobal = 0;
		int PARAMAVOIDREF = 6000;
		int NNOIMPROVE = 50;
		int countIterNoImprove = NNOIMPROVE/2;
		for (int nIter = 0; nIter <= nbIterations; nIter++)
		{
			if(countIterNoImprove%NNOIMPROVE==(NNOIMPROVE-1))
			{
				//Restart optimisation from another start point
				PARAMAVOIDCOEFF=PARAMAVOIDREF;//-1;
				countIterNoImprove = 0;
				bestScore = 0;
//				if(improved)
//				{
//					PSTOPBACKTRACKING = Double.min(0.3, PSTOPBACKTRACKING*2);
//				}else
//				{
//					PSTOPBACKTRACKING = Double.max(0.03   ,PSTOPBACKTRACKING/1.2);
//				}
//				improved = false;
				countIterNoImproveGlobal++;
				
			}
			
			if(rand.nextDouble()<PSTOPBACKTRACKING     ) 
			{
				if(PARAMAVOIDCOEFF==PARAMAVOIDREF)
				{
					bestScore = 0;
					PARAMAVOIDCOEFF=9999 ;
				}else
				{
					PARAMAVOIDCOEFF=9999 ;//-1;
				}
			}	
			  PRESTORE = 0.0;
			  
			solCurrent.pb = pb;
			
			// Low probability to move back to best solution
			if( rand.nextDouble() < PRESTORE || countIterNoImproveGlobal >= 1)
			{
				Sys.pln("Restoring");
				solCurrent = Common.DeepCopy(bestSolution);
				OptB = new OptimizeBallon(pb);
				for(Ballon b : initSol.ballons)
				{
					OptB.updateEffect(pb, b, 1);
				}
				solCurrent.pb = pb;
				PSTOPBACKTRACKING = PSTOPBACKTRACKINGREF;
				countIterNoImproveGlobal = 0;
				countIterNoImprove = 0;
				PARAMAVOIDCOEFF = 9999;
			
			}
			// Change PARAMAVOID & HEAT to explore further solutions
			OptB.PARAMAVOID= PARAMAVOIDCOEFF;
		

			//TODO : Cette fonction n'ameliore pas toujours, a creuser TODO
  			solTry = TryImprove(solCurrent, new Random(nIter),pb, OptB);
			solTry.pb = pb;
			bestSolution.pb = pb;
			
		//**** Try another Local Optimization
			OptimizePairBallon oPPB = new OptimizePairBallon(OptB);
			if(countIterNoImprove == NNOIMPROVE-2)// Only used as final optimization
			{
				for(int kk =0;kk<pb.B;kk++)
				{
					for(int ll =kk+1;ll<pb.B;ll++)
					{
						if(rand.nextDouble()<0.3)
						{
							int indexBallonA = kk;
							int indexBallonB = ll;
							oPPB.optimizePair(solTry, indexBallonA, indexBallonB, pb);
						}
					}
				}
				
				
				// Recover optB, it seems it was not correctly restored
				long trecs = System.nanoTime();
				OptB.coveredT = new int[pb.L][pb.T+3];// init to 0
				for(Ballon b : solTry.ballons)
				{
					OptB.updateEffect(pb, b, 1);
				}
				Sys.pln("recovery took :"+(System.nanoTime()-trecs)/1e6+"ms");
				
			}
			//****	
			
			
			// Si on dépasse notre "meilleur score", procéder à sauvegarde de cette best sol.
			int curScore = solTry.GetScore();
			Sys.pln("Score : " + curScore);
			//countIterNoImproveGlobal++;
			if (curScore > bestScore)
			{				
				bestSolution = Common.DeepCopy(solTry);
				bestSolution.pb = pb;
				bestScore = bestSolution.GetScore();
				// Serialize best solution in path = Common.ACFileFolderPath+fileName
				if(curScore > globalBestScore )
				{
					countIterNoImproveGlobal = 0;
					improved = true;
					globalBestScore = curScore;
					bestSolution.SaveSolutionAsRaw("BestSolutionInProcess.ser");
					FullProcess.ProcessAllBackupOfSolutionToFolder(bestSolution);
				}
				
				PARAMHEAT = Float.max( PARAMHEAT*(float)0.9,(float)0.1);
			//	PARAMAVOIDCOEFF = (float)1000;
				countIterNoImprove = 0;
				
			}
			else
			{
				countIterNoImprove++; // incrémentation nb itération sans amélioration
				PARAMHEAT = Float.min( PARAMHEAT*(float)1.1,(float)1.01);
			//	PARAMAVOIDCOEFF = PARAMAVOIDCOEFF*(float)1.04;
			}

			// ALWAYS "shift".   TODO (add some random condition ?) TODO
			solCurrent = solTry;	

		
			
			
			
			// au bout d'un certain nombre d'itérations sans amélioration, repartir de la best solution
			if (countIterNoImprove > acceptIterationNoImprove)
			{
				solCurrent = Common.DeepCopy(bestSolution);
			}
			solCurrent.pb = pb;
		}
		
		return bestSolution;
	}

	
	
	public Solution DeserializeBestSol(String filename)
	{
		Solution DesSol = (Solution)(Common.FU.DeserializeFileToObject(Common.ACFileFolderPath+filename));
		return DesSol;
	}
	

}
