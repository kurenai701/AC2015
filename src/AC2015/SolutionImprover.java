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

	public Solution TryImprove(Solution oldSol, Random rand, Problem pb, OptimizeBallon optB)
	{
		// Deep copy is necessary so as not to mess up "old sol"
			//////////////////////////////////////
		// TODO Code logic to improve solution
		//////////////////////////////////////

		
		
		
//		double pchange = 0.02;
//		for(int ii = 0;ii< oldSol.ballons.length;ii++)
//		{
//		
	
//		if(pchange > rand.nextDouble())
		int ii = rand.nextInt(pb.B);
			{
				oldSol.ballons[ii] = optB.optimize(pb,oldSol.ballons[ii]);
			}
		
				while(oldSol.ballons[ii].posList.size()<pb.T+1)
				{
					oldSol.ballons[ii].addMove(0, pb);
				}
				
			
//		}
		
		
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
		int countIterNoImprove = 0;

		OptimizeBallon OptB = new OptimizeBallon(pb);
		for(Ballon b : initSol.ballons)
		{
			OptB.updateEffect(pb, b, 1);
		}
		

		bestSolution.pb = pb;
		int bestScore = bestSolution.GetScore();
		// it�rer
		Random rand = new Random(57);
		double PRESTORE = 0.2;
		float  PARAMAVOIDCOEFF=-100;
		float  PARAMHEAT = (float)0.0;
		for (int nIter = 0; nIter <= nbIterations; nIter++)
		{
			solCurrent.pb = pb;
			
			// Low probability to move back to best solution
			if( rand.nextDouble() < PRESTORE )
			{
				solCurrent = Common.DeepCopy(bestSolution);
				solCurrent.pb = pb;
			
			}
			// Change PARAMAVOID & HEAT to explore further solutions
			OptB.PARAMAVOID= rand.nextFloat()*(PARAMAVOIDCOEFF-0.1);
			for(int Ncible = 0; Ncible < pb.L;Ncible++)
			{
				OptB.HEAT[Ncible] = 1+rand.nextFloat()*PARAMHEAT;
			}

			
			solTry = TryImprove(solCurrent, new Random(nIter),pb, OptB);
			solTry.pb = pb;
			bestSolution.pb = pb;
			// Si on d�passe notre "meilleur score", proc�der � sauvegarde de cette best sol.
			int curScore = solTry.GetScore();
			Sys.pln("Score : " + curScore);
			if (curScore > bestScore)
			{				
				
				bestSolution = Common.DeepCopy(solTry);
				bestSolution.pb = pb;
				bestScore = bestSolution.GetScore();
				// Serialize best solution in path = Common.ACFileFolderPath+fileName
				bestSolution.SaveSolutionAsRaw("BestSolutionInProcess.ser");
				FullProcess.ProcessAllBackupOfSolutionToFolder(bestSolution);
			}
			else
			{
				countIterNoImprove++; // incr�mentation nb it�ration sans am�lioration
			}

			// ALWAYS "shift".   TODO (add some random condition ?) TODO
			solCurrent = solTry;	

		
			
			
			
			// au bout d'un certain nombre d'it�rations sans am�lioration, repartir de la best solution
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
