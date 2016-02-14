package AC2016;

import java.io.Serializable;
import java.util.Scanner;

public class MockTestGenerator implements Serializable {

	
	public Problem getProblemModTestAlgo()
	{
		Problem pbtest = new Problem();
			
		pbtest.testint = 216;
		pbtest.testString = "problemTestString";
		
		return pbtest;
	}
	
	
	public Problem getProblemTestFromReadInput()
	{

		ReadInput ri = new ReadInput();
		
		Scanner scanInput = ri.ScannerInputFile(Common.InputFilePath);
		Problem pbMod = ri.ProcessReadInputToModel(scanInput);
		
		return pbMod;
	}
	
	// TO Initialize TEST
	public Solution getSolutionTest(Problem pb)
	{
		Solution sol = new Solution(pb);
		
		//sol.testSolInt = 789;
		//sol.testSolString = "solutionTestString";
		return sol;	
		
	}
	
		
}
