package AC2015;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
			
		ri.ProcessProblemModelToVerifFile(pbMod, Common.InputFileVerifPath);
		
		
		//// process Algorithm ***********************
	
		Solution sol = algo.AlgoSimple(pbMod);
		 
		
		/////////// TODO FAIRE UN PROCESSING POUR QUE NOTRE OUTPUT RENDE LE SCORE ////////
		
		//// GenerateOutputFile **********************
		genOut.GenerateOutputFileFromOutputModel(sol, Common.OutputGeneratedFullPath);
							
		// To Verify Output correctly linked to Input		
		Scanner scanOutput = ro.ScannerOutputFile();	
		
		Problem pbModVerif = ro.ProcessReadOutputToInputModel(scanOutput);
				
		ri.ProcessProblemModelToVerifFile(pbModVerif, Common.InputFileVerifPathFromOutputRead);	
		ro.EvaluateScoreFromOutput(sol);
	
		// Serialize solution
		sol.SaveSolutionAsRaw(Common.SaveSerialFileName);
		
		// Deserialize		
		Solution testdes = (Solution)(Common.FU.DeserializeFileToObject(Common.ACFinalFilesFolderPath+Common.SaveSerialFileName));
		System.out.println(testdes.testSolInt);		
	
		
		ZipSourceOfFullSolution(sol.GetScore());
	}
	
	
	public static void ZipSourceOfFullSolution(int scorePrefix)
	{
		
		// Format Date For Prefix
		Date currentDate = new Date();		
		String prefixDate = Common.FilePrefixdateFormat.format(currentDate);
		
		
		String superPath = Common.ACFinalFilesFolderPath 
				+ scorePrefix
				+ "_"
				+ prefixDate 
				+ "-"
				+ Common.OUTPUT_ZIP_FULLPROC_FILE_NAME
				;
		
		// Zip Folder Sources 
				SrcZipUtil appZip = new SrcZipUtil();
				appZip.generateFileList(new File(Common.SOURCE_FOLDER));
				appZip.zipIt(superPath);			
	}
	
	
	
	
}
