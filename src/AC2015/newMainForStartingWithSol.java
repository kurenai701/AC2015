package AC2015;

public class newMainForStartingWithSol {


	

	public static void main(String[] args) {
		
		boolean ONLYBACKUP = false;
		
		// PARAMETRES !!
		int paramNbIterations = (50);
		int paramAcceptIterationNoImprove = 10;
		// PARAMETRES !!
		
		
		//tool
		SolutionImprover si = new SolutionImprover(); 
				
		Solution sol = si.DeserializeBestSol("BestSolutionInProcess.ser");
		
		
		
		// ****** Solution Improver ATTENTION AUX PARAMETRES; ATTENTION AUX PARAMETRES;
		if (!ONLYBACKUP)
		{
			sol = si.IterateImprover(sol, paramNbIterations, paramAcceptIterationNoImprove);
		}
		
		// ****** Generate output file
		FullProcess.GenerateOutputFileFromSolutionAndVerify(sol, Common.OutputGeneratedFullPath);		
		
		// Serialize Sol, and Verify Deserialisation possible
		FullProcess.VerifySerializeDeserializeSolution(sol); 
			
		// BACK-UP to a folder with score and time
		FullProcess.ProcessAllBackupOfSolutionToFolder(sol);
		
		// GET SCORE
		sol.PrintScore();			
	}



}
