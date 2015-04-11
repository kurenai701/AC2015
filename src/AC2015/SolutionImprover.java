package AC2015;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

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
		Random rand = new Random(42*13  );
		double PRESTORE = 0.1;
		int  PARAMAVOIDCOEFF=9999;//-1;
		float  PARAMHEAT =  (float)0;
		double PSTOPBACKTRACKING = 0.03;
		boolean improved = false;
		int 	countIterNoImproveGlobal = 0;
		int NNOIMPROVE = 50;
		int countIterNoImprove = NNOIMPROVE/2;
		for (int nIter = 0; nIter <= nbIterations; nIter++)
		{
			if(countIterNoImprove%NNOIMPROVE==(NNOIMPROVE-1))
			{
				//Restart optimisation from another start point
				PARAMAVOIDCOEFF=6000;//-1;
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
				if(PARAMAVOIDCOEFF==6000)
				{
					bestScore = 0;
					PARAMAVOIDCOEFF=8000 ;
				}else
				{
					PARAMAVOIDCOEFF=9999 ;//-1;
				}
			}	
			  PRESTORE = 0.0;
			  
			solCurrent.pb = pb;
			
			// Low probability to move back to best solution
			if( rand.nextDouble() < PRESTORE || countIterNoImproveGlobal == 3)
			{
				Sys.pln("Restoring");
				solCurrent = Common.DeepCopy(bestSolution);
				OptB = new OptimizeBallon(pb);
				for(Ballon b : initSol.ballons)
				{
					OptB.updateEffect(pb, b, 1);
				}
				solCurrent.pb = pb;
				PSTOPBACKTRACKING = 0.3;
				countIterNoImproveGlobal = 0;
				countIterNoImprove = 0;
			
			}
			// Change PARAMAVOID & HEAT to explore further solutions
			OptB.PARAMAVOID= PARAMAVOIDCOEFF;
		

			//TODO : Cette fonction n'ameliore pas toujours, a creuser TODO
  			solTry = TryImprove(solCurrent, new Random(nIter),pb, OptB);
			solTry.pb = pb;
			bestSolution.pb = pb;
			
		//**** Try another Local Optimization
			OptimizePairBallon oPPB = new OptimizePairBallon(OptB);
			if(countIterNoImprove > NNOIMPROVE-15)// Only used as final optimization
			{
				for(int kk =0;kk<30;kk++)
				{
					int indexBallonA = rand.nextInt(pb.B);
					int indexBallonB = rand.nextInt(pb.B);
					oPPB.optimizePair(solTry, indexBallonA, indexBallonB, pb);
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
