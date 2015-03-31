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

		
		
		
//		double pchange = 0.02;
//		for(int ii = 0;ii< oldSol.ballons.length;ii++)
//		{
//		
	
//		
		List<Ballon> ballonsRemoved = new ArrayList<Ballon>();
		double pchange = 0.04;
		
//		ballonsRemoved.add(oldSol.ballons[ii]);
//		for(int ii=0;ii<oldSol.ballons.length;ii++)
//		{
//			if(pchange > rand.nextDouble())
//			{
		 int bestscore = oldSol.GetScore();
			
		int ii = rand.nextInt(pb.B);
		int jj = rand.nextInt(pb.B);
				ballonsRemoved.add(oldSol.ballons[ii]);
				ballonsRemoved.add(oldSol.ballons[jj]);
				
			//	ballonsRemoved.add(oldSol.ballons[(ii+1)%pb.B]);// TODO : remove worst Ballon score
//			}
//		}
		
				int Nopt = rand.nextInt(7);
				
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
				 
				 
				for(int copt = 0;copt<Nopt;copt++)
				{
					for( ii =0;ii<bOutList.size();ii++)
					{
						Ballon ans= optB.optimize(pb,bOutList.get(ii));
						bOutList.remove(ii);
						
						
						while(ans.posList.size()<pb.T+1)
						{
							ans.addMove(0, pb);
						}
						bOutList.add(ii, ans);
						oldSol.ballons[ans.Num] = ans;
							 
					}
					int curscore = oldSol.GetScore();
					Sys.pln("CURS : " + curscore);
					 if(curscore>bestscore)
					 {
						break;
					 }
					 //else
//					 {
//						break; 
//					 }
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
		// itérer
		Random rand = new Random(42);
		double PRESTORE = 0.2;
		float  PARAMAVOIDCOEFF=0;//-1;
		float  PARAMHEAT = (float)0;
		for (int nIter = 0; nIter <= nbIterations; nIter++)
		{
			solCurrent.pb = pb;
			
			// Low probability to move back to best solution
			if( rand.nextDouble() < PRESTORE )
			{
				Sys.pln("Restoring");
				solCurrent = Common.DeepCopy(bestSolution);
				solCurrent.pb = pb;
			
			}
			// Change PARAMAVOID & HEAT to explore further solutions
			OptB.PARAMAVOID= (rand.nextFloat()-(float)(0.4))*PARAMAVOIDCOEFF+1000;
			for(int Ncible = 0; Ncible < pb.L;Ncible++)
			{
				OptB.HEAT[Ncible] = 1+(rand.nextFloat()-(float)0.5)*PARAMHEAT;
			}

			
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
				
			}
			else
			{
				countIterNoImprove++; // incrémentation nb itération sans amélioration
				PARAMHEAT = Float.min( PARAMHEAT*(float)1.1,(float)1.01);
				
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
