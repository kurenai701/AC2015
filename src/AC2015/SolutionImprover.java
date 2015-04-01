package AC2015;

import java.util.ArrayList;
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
//		
//		double PremoveCible = 0.00;
//		
//		for(int Ncible = 0; Ncible < pb.L;Ncible++)
//		{
//			if(rand.nextDouble()<PremoveCible)
//			{
//				optB.HEAT[Ncible] = (float)0;
//			}else{
//				optB.HEAT[Ncible] = (float)1;
//			}
//		}
			
		for(int Nb = 0;Nb<NBUPDATE;Nb++)
		{
			int ii = rand.nextInt(pb.B);
			ballonsRemoved.add(oldSol.ballons[ii]);
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
				
//				for(int Ncible = 0; Ncible < pb.L;Ncible++)
//				{
//						optB.HEAT[Ncible] = (float)1;
//				} 
//				 
//				for(int copt = 0;copt<Nopt;copt++)
//				{
//					for(int ii =0;ii<bOutList.size();ii++)
//					{
//						Ballon ans= optB.optimize(pb,bOutList.get(ii));
//						bOutList.remove(ii);
//						
//						
//						while(ans.posList.size()<pb.T+1)
//						{
//							ans.addMove(0, pb);
//						}
//						bOutList.add(ii, ans);
//						oldSol.ballons[ans.Num] = ans;
//							 
//					}
//					int curscore = oldSol.GetScore();
//					Sys.pln("CURS : " + curscore);
//					 if(curscore>bestscore)
//					 {
//						break;
//					 }
//					 //else
////					 {
////						break; 
////					 }
//				}
				
			
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
		// itérer
		Random rand = new Random(42);
		double PRESTORE = 0.1;
		float  PARAMAVOIDCOEFF=(float)1000;//-1;
		float  PARAMHEAT = (float)0;
		for (int nIter = 0; nIter <= nbIterations; nIter++)
		{
			if(countIterNoImprove%100==0)  
				PARAMAVOIDCOEFF=(float)2;//-1;
			if(rand.nextDouble()<0.2) 
				PARAMAVOIDCOEFF=(float)50000;//-1;
				
			  PRESTORE = 0.0;
			  
			solCurrent.pb = pb;
			
			// Low probability to move back to best solution
			if( rand.nextDouble() < PRESTORE )
			{
				Sys.pln("Restoring");
				solCurrent = Common.DeepCopy(bestSolution);
				solCurrent.pb = pb;
			
			}
			// Change PARAMAVOID & HEAT to explore further solutions
			OptB.PARAMAVOID= PARAMAVOIDCOEFF;
		

			
			solTry = TryImprove(solCurrent, new Random(nIter),pb, OptB);
			solTry.pb = pb;
			bestSolution.pb = pb;
			// Si on dépasse notre "meilleur score", procéder à sauvegarde de cette best sol.
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
