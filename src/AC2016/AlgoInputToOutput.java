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
	
		//////////////////////////////////
		// TODO Write THE ALGORITHM :-) //
		//////////////////////////////////
		
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
					
	
		////////////////
		int score = sol.GetScore();

		System.out.println("Finished complicatedalgo" + score);
		return sol ;
	}
	
		
	
}
