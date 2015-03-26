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
		GenerateOutput genOut = new GenerateOutput();
		ReadOutput ro = new ReadOutput();
				
		// Get Problem Model
		Problem pbMod = FromInputFileToProblem(Common.InputFilePath);
		
		// TODO step for Simplification of Problem		
		Problem pbModSimplified = simplifyer.SimplifyProblem(pbMod); // TODO TODO TODO
		
		////**************** process Algorithm ***********************	
		
		Solution sol = algo.AlgoSimple(pbModSimplified);	
		
		////**********************************************************
				
		//// GenerateOutputFile
		genOut.GenerateOutputFileFromOutputModel(sol, Common.OutputGeneratedFullPath);
		
		//// To Verify Output correctly linked to Input		
		Scanner scanOutput = ro.ScannerOutputFile();			
		Problem pbModVerif = ro.ProcessReadOutputToProblemModel(scanOutput);
				
		ri.ProcessProblemModelToVerifFile(pbModVerif, Common.InputFileVerifPathFromOutputRead);	
		
		ro.EvaluateScoreFromOutput(sol);
	
		//// Serialize Solution class
		sol.SaveSolutionAsRaw(Common.SaveSerialFileName);
		
		//// Deserialize to verify there is no problem	
		Solution testdes = (Solution)(Common.FU.DeserializeFileToObject(Common.ACFileFolderPath+Common.SaveSerialFileName));
		System.out.println(testdes.testSolInt);		
			
		// BACK-UP to a folder with score and time
		ProcessAllBackupOfSolutionToFolder(sol);		
	}
		

	
	
	public static Problem FromInputFileToProblem(String fullFilePath)
	{
		ReadInput ri = new ReadInput();
		Scanner scanInput = ri.ScannerInputFile(fullFilePath);
		Problem pbMod = ri.ProcessReadInputToModel(scanInput);		
		
		// Generate again an InputFile from the ProblemModel for verification purpose
		ri.ProcessProblemModelToVerifFile(pbMod, Common.InputFileVerifPath);	
		
		return pbMod;
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
