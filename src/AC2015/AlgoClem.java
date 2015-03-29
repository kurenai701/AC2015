package AC2015;

import java.util.List;
import java.util.function.Predicate;


public class AlgoClem {

//
//	public static void main(String[] args) {
//		
//		
//		//// Initialization of our Tool Classes		
//		ProblemSimplifyer simplifyer = new ProblemSimplifyer();
//		AlgoInputToOutput algo = new AlgoInputToOutput();
//		SolutionImprover si = new SolutionImprover(); 
//		
//				
//		// ****** Get Problem Model from file
//		
//		Problem pbMod = FullProcess.FromInputFileToProblem(Common.InputFilePath);
//		
//		AlgoClem cl = new AlgoClem();
//		cl.ClemAlgo(pbMod);
//	}	
//	
//	
//	
//
//	
//	
//		
//	public Solution ClemAlgo(Problem pb)
//	{
//					
//				
//		
//		System.out.println("Starting Clem algo");		
//		////////////////
//	
//		
//		////////////////
//		
//		Solution sol = new Solution(pb);
//		Pos startPos = pb.AllPosMat[ pb.StartPos.x][pb.StartPos.y ][0];
//	
//		
//		
//		for(int ii=0;ii<pb.B;ii++)
//		{
//			sol.ballons[ii].posList.add( startPos		);
//		}
//		
//		//////////////////////////////////
//		// TODO Write THE ALGORITHM :-) //
//		//////////////////////////////////
//		// décollage différé
//		
//		
//	///	
//	//	List<Pos> l40 = LotsCoverMove0(25, pb);
//		//Sys.pln(l40.size());
//		
//		
//		
//		
//		
//		
//		for(int tt=0;tt<pb.T;tt++)
//		{
//			for(Ballon b : sol.ballons)
//			{
//				int mymove = 0;
//					
//				if(tt<3)
//				{
//					mymove = 1;
//     			}
//				b.addMove(mymove,pb);
//				
//			}
//		}
//		
//		
//		
//		
//		////
//		
//		
//		
//		
//		
//		
//		
//		
//
//		
//		////////////////
//				
//		
//		int score = sol.GetScore();
//		Sys.pln("Finished algo Clem:Score " + score);
//		return sol ;
//	}
//	
//	
//	
	
	
	
}
