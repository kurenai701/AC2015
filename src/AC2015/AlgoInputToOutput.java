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
		
		//////////////////////////////////
		// TODO Write THE ALGORITHM :-) //
		//////////////////////////////////
		
		for(int tt=0;tt<pb.T;tt++)
		{
			for(Ballon b : sol.ballons)
			{
				int mymove = 0;
				b.aChanges.add(mymove);
			}
		}

		
		////////////////
				
		Sys.pln("Finished algo simple");
		return sol ;
	}
	
	
	public Solution AlgoComplicatedFromProblem(Problem pb)
	{
		System.out.println("Starting complicatedalgo");
		Solution sol = new Solution();
		
		//////////////////////////////////
		// TODO Write THECOMPLICATED ALGORITHM :-) //
		//////////////////////////////////
				
	
		////////////////
				
		System.out.println("Finished complicatedalgo");
		return sol ;
	}
	
	
	
}
