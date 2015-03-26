package AC2015;

import java.io.File;
import java.util.Date;
import java.util.Scanner;

public class FullProcess {
	
	public static void main(String[] args) {
	
		//// Initialization of our Tool Classes;
		ReadInput ri = new ReadInput();
		ProblemSimplifyer simplifyer = new ProblemSimplifyer();
		AlgoInputToOutput algo = new AlgoInputToOutput();
		ReadOutput ro = new ReadOutput();
		
				
		// Get Problem Model from file
		Problem pbMod = FromInputFileToProblem(Common.InputFilePath);
		
		// Simplify Problem		
		Problem pbModSimplified = simplifyer.SimplifyProblem(pbMod);
		
		////**************** process Algorithm *******************////
		
		Solution sol = algo.AlgoSimple(pbModSimplified);	
		
		////******************************************************////
		
		GenerateOutputFileFromSolutionAndVerify(sol, Common.OutputGeneratedFullPath);		
		
		// GET SCORE
		sol.PrintScore();
	
		// Serialize Sol, and Verify Deserialisation possible
		VerifySerializeDeserializeSolution(sol);
			
		// BACK-UP to a folder with score and time
		ProcessAllBackupOfSolutionToFolder(sol);		
	}
		

	
	
	public static Problem FromInputFileToProblem(String fullFilePath)
	{
		ReadInput ri = new ReadInput();
		// Create the scanner for file
		Scanner scanInput = ri.ScannerInputFile(fullFilePath);
		// And build the problem model from parsing
		Problem pbMod = ri.ProcessReadInputToModel(scanInput);		
		
		// Generate again an InputFile from the ProblemModel for verification purpose
		ri.ProcessProblemModelToVerifFile(pbMod, Common.InputFileVerifPath);	
		
		return pbMod;
	}
	
	
	public static void GenerateOutputFileFromSolutionAndVerify(Solution sol, String fullGeneratedFilePath)
	{	
			GenerateOutput genOut = new GenerateOutput();
			genOut.GenerateOutputFileFromOutputModel(sol, fullGeneratedFilePath);
			
			ReadOutput ro = new ReadOutput();		
			
			// TODO VOIR SI CA S Y PRETE
			// To Verify Output correctly linked to Input	(si ça s'y prête)	
			
			Scanner scanOutput = ro.ScannerOutputFile(fullGeneratedFilePath);			
			Problem pbModVerif = ro.ProcessReadOutputToProblemModel(scanOutput);
					
			ReadInput ri = new ReadInput();
			ri.ProcessProblemModelToVerifFile(pbModVerif, Common.InputFileVerifPathFromOutputRead);	
	}
	
	public static void VerifySerializeDeserializeSolution(Solution sol)
	{
		//// Serialize Solution class
		sol.SaveSolutionAsRaw(Common.SaveSerialFileName);
		
		//// De-serialize to verify there is no problem	
		Solution testdes = (Solution)(Common.FU.DeserializeFileToObject(Common.ACFileFolderPath+Common.SaveSerialFileName));
		System.out.println(testdes.testSolInt);		
	}
	
	public static String getDirectoryNameWithScoreAndDate(int scorePrefix, String prefixDate)
	{			
		String resPath = String.format("%010d", scorePrefix)+ "_" + prefixDate;
		return resPath;
	}
	
	
	public static void ProcessAllBackupOfSolutionToFolder(Solution sol)
	{		
		// Initialize Tool
		GenerateOutput genOut = new GenerateOutput();
		
		////////// FOR BACK-UP ////////////
		String datePrefix = Common.getTimeAsPrefixDateFormatString();			
		String TargetBKPfolderName = getDirectoryNameWithScoreAndDate(sol.GetScore(),datePrefix);
	
		String targetBKPFolderPath = Common.ACFileFolderPath+TargetBKPfolderName;
		Common.FU.CreateDir(targetBKPFolderPath);
	
		// Backup serialized solution class to BKP folder
		sol.SaveSolutionAsRawToFullPath(targetBKPFolderPath+"\\"+ Common.SaveSerialFileName);
		// Backup generated output
		genOut.GenerateOutputFileFromOutputModel(sol, targetBKPFolderPath +"\\"+ Common.OutputGeneratedFileName);
	
		// Zip Sources for BKP	
		SrcZipUtil appZip = new SrcZipUtil();
		appZip.ZipSourceOfProject(targetBKPFolderPath+"\\"+Common.OUTPUT_ZIP_FULLPROC_FILE_NAME);	
	}

	
	
}
