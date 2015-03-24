package AC2015;

import java.util.Scanner;

public class ReadOutput {	

	
	
	public static void main(String[] args) {
		
		ReadOutput ro = new ReadOutput();
		Scanner scanOutputFile = ro.ScannerOutputFileForUnitTest();
		scanOutputFile.hashCode();
		
		ProblemModel pbModVerif = ro.ProcessReadOutputToInputModel(scanOutputFile);
	
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
	

	
	
	// If there is a simple way to obtain a "score from the Generated outputFile"
	public int EvaluateScoreFromOutput( OutputModel oP)
	{ 	
		
		ScoreInfo score =  oP.GetScoreModel();
		System.out.println("EvalScore : " +score.score);

		return score.score;
	}

	
	
	public ProblemModel ProcessReadOutputToInputModel(Scanner scanOutput)
	{
		System.out.println("VerifyOutputFileGeneratesInput");
		ProblemModel pbModel = new ProblemModel();
		
		if (scanOutput != null)
		{				
			//  !!!!!!!!!!!!!!!!!!! //
			// TODO	CODE here parsing and processing of output file to obtain input model	
			//  !!!!!!!!!!!!!!!!!!! //
			
		//	pbModel.stringTest = scanOutput.next();
		//	pbModel.intTest1 = scanOutput.nextInt();
		//	pbModel.intTest1 = scanOutput.nextInt();
			//  !!!!!!!!!!!!!!!!!!! //
		}			
		return pbModel;
	
	}
	

	
}
