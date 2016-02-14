package AC2016;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;


public class AlgoInputToOutput {

	
/*
	public static void main(String[] args) {
		
		AlgoInputToOutput algo = new AlgoInputToOutput();
		MockTestGenerator mock = new MockTestGenerator();
		
		Problem pbModTest = mock.getProblemModTestAlgo();		
		algo.AlgoSimple(pbModTest);
	}	
*/	
	
	public Solution AlgoSimple(Problem pb, Random rand)
	{
		Sys.pln("Starting simple algo");
				
		////////////////
		
		Solution sol = new Solution(pb);
		Pos startPos = pb.AllPosMat[ pb.StartPos.x][pb.StartPos.y ][0];

		//////////////////////////////////
		// TODO Write THE ALGORITHM :-) //
		//////////////////////////////////
		
		for(int tt=0;tt<pb.T;tt++)
		{
			List<Pos >Otherpos = new ArrayList<Pos>();
			for(Ballon b : sol.ballons)
			{
				
				// si tout seul, alors si on voit qq chose, on descend, sinon on monte 
				
				
				int mymove=0;
				boolean closeToOther = false;
				int RDIST = 7;
				
				
				
				Pos curPos = b.getLastPos();
				for(Pos p : Otherpos)
				{
					if( (curPos.x-p.x)*(curPos.x-p.x) +
						(curPos.y-p.y)*(curPos.y-p.y) <= RDIST*RDIST 	
							)
						{
							closeToOther=true;
						}
					
				}
				Otherpos.add(curPos);
				
				int altCible = 6;
				
				if(! closeToOther)
				{
					if(curPos.coverList.size()>10)// We are providing coverage
					{
						if( curPos.z>1)
						{
							mymove = -1;
						}
						
					}else
					{
					mymove = 1;
						if( curPos.z == altCible)
						{
							mymove = 0;
						}
					}
				}else
				{
					if(curPos.z>0  && curPos.z<pb.A )
					{
						mymove = rand.nextInt(3)-1;
					}
				}
				
				//saturate
				if(curPos.z==1)
				{
					mymove = Integer.max(0,mymove);
				}
				if(curPos.z==pb.A)
				{
					mymove = Integer.min(0,mymove);
				}
							
				b.addMove(mymove,pb);			
				
				
			}
		}
		
		////
//Last move	
		

		
		////////////////
				
		
		int score = sol.GetScore();
		Sys.pln("Finished algo simple:Score " + score);
		return sol ;
	}
	
	
	
	
	public Solution AlgoComplicatedFromProblem(Problem pb, Random rand)
	{
//		pb.T = 100;//TODO : REMOEV
//		Sys.pln("**********REMOVE PREVIOUS LINE*********");
//		
		
		System.out.println("Starting complicatedalgo");
		Solution sol = new Solution(pb);
		
		//////////////////////////////////
		// TODO Write THECOMPLICATED ALGORITHM :-) //
		//////////////////////////////////
					
		OptimizeBallon OptB = new OptimizeBallon(pb);

		for(int ii = 0;ii<pb.B;ii++)
		{
			sol.ballons[ii] = OptB.optimize(pb, sol.ballons[ii]);
		}
		
		
		
	
		////////////////
		int score = sol.GetScore();

		System.out.println("Finished complicatedalgo" + score);
		return sol ;
	}
	
	
	
	public List<Pos> LotsCoverMove0(int nbCov, Problem pb)
	{
		Predicate<Pos> PosLotsCover = p-> (p.coverList.size() > nbCov && p.moves.get(1).nextPos.coverList.size() > nbCov);
		// Predicate<Pos> PosLotsCover = p-> p.coverList.size() > 7 && p.moves.get(1).nextPos.coverList.size() > 7 ));
		List<Pos> listTest = Common.Where(pb.AllPos, PosLotsCover);
		Sys.pln(listTest.size());
		
		return listTest;	}
	
	
	
	
}
