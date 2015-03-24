package AC2015;

public class MockTestGenerator {

	
	
	
	public Problem getProblemModTestAlgo()
	{
		Problem pbtest = null;//new ProblemModel();
			
		pbtest.testint = 216;
		pbtest.testString = "problemTestString";
		
		return pbtest;
	}
	
	// TO Initialize TEST
	public Solution getSolutionTest(Problem pb)
	{
		Solution sol = new Solution(pb);
		sol.testSolInt = 789;
		sol.testSolString = "solutionTestString";
		return sol;	
		
	}
	
		
}
