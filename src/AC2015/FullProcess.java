package AC2015;

import java.io.File;
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
		Problem pbMod = ri.ProcessReadInputToModel(scanInput);
			
		ri.ProcessProblemModelToVerifFile(pbMod, CommonStatic.InputFileVerifPath);
		
		
		//// process Algorithm ***********************
	
		Solution sol = algo.AlgoSimple(pbMod);
		
		
		//// GenerateOutputFile **********************
		genOut.GenerateOutputFileFromOutputModel(sol, CommonStatic.OutputGeneratedPath);
							
		// To Verify Output correctly linked to Input		
		Scanner scanOutput = ro.ScannerOutputFile();	
		
		Problem pbModVerif = ro.ProcessReadOutputToInputModel(scanOutput);
				
		ri.ProcessProblemModelToVerifFile(pbModVerif, CommonStatic.InputFileVerifPathFromOutputRead);	
		ro.EvaluateScoreFromOutput(sol);
	
		// CLEM TEST serialize deserialize
		sol.SaveSolutionAsRaw("serdestest.ser");
		Solution testdes = (Solution)(CommonStatic.FU.DeserializeFileToObject("C:\\ACFile\\serdestest.ser"));
		
		System.out.println(testdes.testSolInt);		
	
		
		// Zip Folder Sources 
		SrcZipUtil appZip = new SrcZipUtil();
		appZip.generateFileList(new File(CommonStatic.SOURCE_FOLDER));
		appZip.zipIt(CommonStatic.OUTPUT_ZIP_FILE_FULL_PROCESS);
		
		
	}
	
	
	
	
	
	
}
