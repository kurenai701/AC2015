package AC2015;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class AlgoInputToOutput {

	
/*
	public static void main(String[] args) {
		
		AlgoInputToOutput algo = new AlgoInputToOutput();
		MockTestGenerator mock = new MockTestGenerator();
		
		Problem pbModTest = mock.getProblemModTestAlgo();		
		algo.AlgoSimple(pbModTest);
	}	
*/	
	
	public Solution AlgoSimple(Problem pb)
	{
		Sys.pln("Starting simple algo");
				
		////////////////
		
		Solution sol = new Solution(pb);
		Pos startPos = pb.AllPosMat[ pb.StartPos.x][pb.StartPos.y ][0];
		for(int ii=0;ii<pb.B;ii++)
		{
			sol.ballons[ii].posList.add( startPos		);
		}
		
		//////////////////////////////////
		// TODO Write THE ALGORITHM :-) //
		//////////////////////////////////
		Random rand = new Random(42);
		for(int tt=0;tt<pb.T;tt++)
		{
			List<Pos >Otherpos = new ArrayList<Pos>();
			for(Ballon b : sol.ballons)
			{
				
				// si tout seul, alors si on voit qq chose, on descend, sinon on monte 
				
				
				int mymove=0;
				boolean closeToOther = false;
				int RDIST = 8;
				
				
				
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
				
				int altCible = 100;
				
				if(! closeToOther)
				{
					if(curPos.coverList.size()>6)// We are providing coverage
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
		
		
		
		
		
		
		
		

		
		////////////////
				
		
		int score = sol.GetScore();
		Sys.pln("Finished algo simple:Score " + score);
		return sol ;
	}
	
	
	public Solution AlgoComplicatedFromProblem(Problem pb)
	{
		System.out.println("Starting complicatedalgo");
		Solution sol = new Solution(pb);
		
		//////////////////////////////////
		// TODO Write THECOMPLICATED ALGORITHM :-) //
		//////////////////////////////////
				
	
		////////////////
				
		System.out.println("Finished complicatedalgo");
		return sol ;
	}
	
	
	
}
