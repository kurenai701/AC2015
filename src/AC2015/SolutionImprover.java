package AC2015;

import java.util.Random;

public class SolutionImprover {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

	public SolutionImprover()
	{

	}

	public Solution TryImprove(Solution oldSol)
	{
		// Deep copy is necessary so as not to mess up "old sol"
		Solution newSol = Common.DeepCopy(oldSol);

		int scoreOld = oldSol.GetScore();
		//////////////////////////////////////
		// TODO Code logic to improve solution
		//////////////////////////////////////
		
		
		
		
		//////////////////////////////////////
		return newSol;


	}


	public Solution IterateImprover(Solution initSol, int nbIterations, int acceptIterationNoImprove)
	{
		return initSol;
//		Solution solCurrent = initSol;
//		Solution solTry;	
//
//		Solution bestSolution = Common.DeepCopy(initSol);
//		bestSolution.SaveSolutionAsRaw("BestSolutionInProcess.ser");
//		
//		int countIterNoImprove = 0;
//
//		// itérer
//		for (int nIter = 0; nIter <= nbIterations; nIter++)
//		{
//			solTry = TryImprove(solCurrent);
//
//			// Si on dépasse notre "meilleur score", procéder à sauvegarde de cette best sol.
//			if (solTry.GetScore() > bestSolution.GetScore())
//			{				
//				bestSolution = Common.DeepCopy(solTry);
//			
//				// Serialize best solution in path = Common.ACFileFolderPath+fileName
//				bestSolution.SaveSolutionAsRaw("BestSolutionInProcess.ser");
//			}
//			else
//			{
//				countIterNoImprove++; // incrémentation nb itération sans amélioration
//			}
//
//			// ALWAYS "shift".   TODO (add some random condition ?) TODO
//			solCurrent = solTry;	
//
//		
//			
//			
//			
//			// au bout d'un certain nombre d'itérations sans amélioration, repartir de la best solution
//			if (countIterNoImprove > acceptIterationNoImprove)
//			{
//				solCurrent = Common.DeepCopy(bestSolution);
//			}
//		}
//		
//		return bestSolution;
	}

	
	
	public Solution DeserializeBestSol(String filename)
	{
		Solution DesSol = (Solution)(Common.FU.DeserializeFileToObject(Common.ACFileFolderPath+filename));
		return DesSol;
	}
	

}
