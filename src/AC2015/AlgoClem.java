package AC2015;

public class AlgoClem {


	public static void main(String[] args) {
		
		AlgoClem algo = new AlgoClem();
		MockTestGenerator mock = new MockTestGenerator();
		
		Problem pbModTest = mock.getProblemModTestAlgo();		
		algo.ClemAlgo(pbModTest);
	}	
	
	
	public Solution ClemAlgo(Problem pb)
	{
		
		
		System.out.println("Starting Clem algo");		
		////////////////
		
		Solution sol = new Solution();
		
		//////////////////////////////////
		// TODO Write THE ALGORITHM :-) //
		//////////////////////////////////
				
		sol.testSolInt = pb.testint;
		sol.testSolString = pb.testString;
		
		////////////////
				
		System.out.println("Finished Clem algo");
		return sol ;
	}
	
}
