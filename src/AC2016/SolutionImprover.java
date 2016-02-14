package AC2016;

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
	
	public Solution TryImprove(Solution oldSol, Random rand, Problem pb)
	{
		// Deep copy is necessary so as not to mess up "old sol"
			//////////////////////////////////////
		// TODO Code logic to improve solution
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
			
				solCurrent.pb = pb;
				PSTOPBACKTRACKING = PSTOPBACKTRACKINGREF;
				countIterNoImproveGlobal = 0;
				countIterNoImprove = 0;
				PARAMAVOIDCOEFF = 9999;
			
			}
	
		

			//TODO : Cette fonction n'ameliore pas toujours, a creuser TODO
  			solTry = TryImprove(solCurrent, new Random(nIter),pb);
			solTry.pb = pb;
			bestSolution.pb = pb;
			
		
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
