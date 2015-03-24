package AC2015;

import java.util.Scanner;

public class ReadOutput {	

	
	
	public static void main(String[] args) {
		
		ReadOutput ro = new ReadOutput();
		Scanner scanOutputFile = ro.ScannerOutputFileForUnitTest();
		scanOutputFile.hashCode();
		
		Problem pbModVerif = ro.ProcessReadOutputToInputModel(scanOutputFile);
	
		System.out.println(pbModVerif.testString);
		System.out.println(pbModVerif.testint);
	
	}
	
	public Scanner ScannerOutputFile()
	{
		return CommonStatic.FU.ScannerFile(CommonStatic.OutputTestFilePath);
	}
	

	public Scanner ScannerOutputFileForUnitTest()
	{
		return CommonStatic.FU.ScannerFile(CommonStatic.OutputTestFilePathUnitTest);
	}
	
	
	
	public int EvaluateScoreFromOutput( Solution sol)
	{ 			
		ScoreInfo score =  sol.GetScoreModel();
		System.out.println("EvalScore : " +score.score);

		return score.score;
	}

	
	
	public Problem ProcessReadOutputToInputModel(Scanner scanOut)
	{
		System.out.println("VerifyOutputFileGeneratesInput");
		Problem pb = new Problem();
		
		if (scanOut != null)
		{				

			//  !!!!!!!!!!!!!!!!!!! //
			// TODO	CODE here parsing and processing of output file to obtain input model	
			//  !!!!!!!!!!!!!!!!!!! //
					
			pb.testint = scanOut.nextInt();
			pb.testString = scanOut.next();
			//  !!!!!!!!!!!!!!!!!!! //
		}			
		return pb;
	
	}
	

	
}
