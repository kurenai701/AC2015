package AC2015;

import java.util.Scanner;

public class FullProcess {
	
	public static void main(String[] args) {
	
		//// Initialization of our Tool Classes;
		ReadInput ri = new ReadInput();
		AlgoInputToOutput algo = new AlgoInputToOutput();
		GenerateOutput genOut = new GenerateOutput();
		ReadOutput ro = new ReadOutput();
				
		//// process input**********************
		Scanner scanInput = ri.ScannerInputFile();
		//Scanner scanInput = ri.ScannerInputFileForUnitTest();
		//*********************************
		

		
		//// Get the ProblemModel from reading Input
		ProblemModel pbMod = ri.ProcessReadInputToModel(scanInput);
			
		ri.ProcessInputModelToVerifFile(pbMod, CommonStatic.InputFileVerifPath);
		
		
		//// process Algorithm ***********************
	
		OutputModel outMod = algo.AlgoSimple(pbMod);
		
		
		//// GenerateOutputFile **********************
		genOut.GenerateOutputFileFromOutputModel(outMod, CommonStatic.OutputGeneratedPath);
			
				
		// To Verify Output correctly linked to Input		
		Scanner scanOutput = ro.ScannerOutputFile();	
		
		
		ProblemModel pbModVerif = ro.ProcessReadOutputToInputModel(scanOutput);
				
		ri.ProcessInputModelToVerifFile(pbModVerif, CommonStatic.InputFileVerifPathFromOutputRead);	
		ro.EvaluateScoreFromOutput( outMod );
	}
	
}
