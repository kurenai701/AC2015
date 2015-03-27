package AC2015;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class AlgoInputToOutput {

	
	

	public static void main(String[] args) {
		
		AlgoInputToOutput algo = new AlgoInputToOutput();
		MockTestGenerator mock = new MockTestGenerator();
		
		Problem pbModTest = mock.getProblemModTestAlgo();		
		algo.AlgoSimple(pbModTest);
	}	
		
	
	public Solution AlgoSimple(Problem pb)
	{
		System.out.println("Starting simple algo");
				
		////////////////
		
		Solution sol = new Solution();
		
		//////////////////////////////////
		// TODO Write THE ALGORITHM :-) //
		//////////////////////////////////
				
		sol.testSolInt = pb.testint;
		sol.testSolString = pb.testString;
		
		////////////////
				
		System.out.println("Finished algo simple");
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
