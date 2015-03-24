package AC2015;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class AlgoInputToOutput {

	
	

	public static void main(String[] args) {
		
		AlgoInputToOutput algo = new AlgoInputToOutput();
		MockTestGenerator mock = new MockTestGenerator();
		
		ProblemModel pbModTest = mock.getProblemModTestAlgo();		
		algo.AlgoSimple(pbModTest);

	}	
		
	
	public OutputModel AlgoSimple(ProblemModel pb)
	{
		System.out.println("Starting simple algo");
				
		////////////////
		
		OutputModel om = new OutputModel();
		
		
		
		////////////////
				
		return om ;
	}
	
	
	
	}
