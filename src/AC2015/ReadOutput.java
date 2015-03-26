package AC2015;

import java.util.Scanner;

public class ReadOutput {	

	
	
	public static void main(String[] args) {
		
		ReadOutput ro = new ReadOutput();
		Scanner scanOutputFile = ro.ScannerOutputFileForUnitTest();
		scanOutputFile.hashCode();
		
		Problem pbModVerif = ro.ProcessReadOutputToProblemModel(scanOutputFile);
	
		System.out.println(pbModVerif.testString);
		System.out.println(pbModVerif.testint);
	
	}
	
	public Scanner ScannerOutputFile()
	{
		return Common.FU.ScannerFile(Common.OutputTestFileFullPath);
	}
	

	public Scanner ScannerOutputFileForUnitTest()
	{
		return Common.FU.ScannerFile(Common.OutputTestFileFullPathUnitTest);
	}
	
	
	
	public int EvaluateScoreFromOutput( Solution sol)
	{ 			
		ScoreInfo score =  sol.GetScoreModel();
		System.out.println("EvalScore : " +score.score);

		return score.score;
	}

	
	
	public Problem ProcessReadOutputToProblemModel(Scanner scanOut)
	{
		System.out.println("ProcessReadOutputToProblemModel");
		Problem pb = new Problem();
		
		if (scanOut != null)
		{				
			//  !!!!!!!!!!!!!!!!!!! //
			// TODO	CODE here parsing and processing of output file to obtain problem model	
			//  !!!!!!!!!!!!!!!!!!! //
					
			pb.testint = scanOut.nextInt();
			pb.testString = scanOut.next();
			//  !!!!!!!!!!!!!!!!!!! //
		}			
		return pb;
	
	}
	
	// CLEM : Faire un ProcessReadOutputToSol ?
	
	
}
