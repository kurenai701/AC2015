package AC2015;

import java.util.ArrayList;

public class MockTestGenerator {

//	
//	public Problem getProblemModTestAlgo()
//	{
//		Problem pbtest = new Problem();
//			
//		pbtest.testint = 216;
//		pbtest.testString = "problemTestString";
//		
//		return pbtest;
//	}
//	
	
	
	
	
	public Problem getProblemModTestAlgo()
	{
		Problem pbtest = FullProcess.FromInputFileToProblem(Common.InputFilePath);;
		return pbtest;
	}
	
	
	
	// TO Initialize TEST
	public Solution getSolutionTest(Problem pb)
	{
		Solution sol = new Solution(pb);
		
		Slice sl1 = new Slice(0,2,0,1,42);
		Slice sl2 = new Slice(0,2,2,2,42);
		Slice sl3 = new Slice(0,2,3,4,42);
		
		
		sol.slices = new ArrayList<Slice>();
		sol.slices.add(sl1);
		sol.slices.add(sl2);
		sol.slices.add(sl3);
		
		return sol;
	}
	
		
}
