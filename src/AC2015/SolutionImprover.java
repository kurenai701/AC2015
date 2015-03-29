package AC2015;

import java.util.Random;

public class SolutionImprover {

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		
//	}

	public SolutionImprover()
	{

	}

	public Solution TryImprove(Solution oldSol, Random rand, Problem pb)
	{
		// Deep copy is necessary so as not to mess up "old sol"
		AlgoInputToOutput algo = new AlgoInputToOutput();
		
		Solution newSol  = algo.AlgoSimple(pb,rand);			
		//////////////////////////////////////
		// TODO Code logic to improve solution
		//////////////////////////////////////
		double pchange = 0.01;
		for(Ballon b : oldSol.ballons)
		{
		
			if(pchange > rand.nextDouble())
			{
				
				
			}
		}
		
		
		//////////////////////////////////////
		return newSol;


	}


	public Solution IterateImprover( Solution initSol, int nbIterations, int acceptIterationNoImprove,Problem pb)
	{
		
		Solution solCurrent = initSol;
		Solution solTry;	

		Solution bestSolution = Common.DeepCopy(initSol);
		bestSolution.SaveSolutionAsRaw("BestSolutionInProcess.ser");
		bestSolution.pb = pb;
		int countIterNoImprove = 0;

		// itérer
		for (int nIter = 0; nIter <= nbIterations; nIter++)
		{
			solCurrent.pb = pb;
			solTry = TryImprove(solCurrent, new Random(nIter),pb);
			solTry.pb = pb;
			bestSolution.pb = pb;
			// Si on dépasse notre "meilleur score", procéder à sauvegarde de cette best sol.
			if (solTry.GetScore() > bestSolution.GetScore())
			{				
				bestSolution = Common.DeepCopy(solTry);
				bestSolution.pb = pb;
				// Serialize best solution in path = Common.ACFileFolderPath+fileName
				bestSolution.SaveSolutionAsRaw("BestSolutionInProcess.ser");
			}
			else
			{
				countIterNoImprove++; // incrémentation nb itération sans amélioration
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
