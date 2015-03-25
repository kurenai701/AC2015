package AC2015;

import java.io.File;
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
		 
		
		//// GenerateOutputFile **********************
		genOut.GenerateOutputFileFromOutputModel(sol, Common.OutputGeneratedFullPath);
		
		//// To Verify Output correctly linked to Input		
		Scanner scanOutput = ro.ScannerOutputFile();			
		Problem pbModVerif = ro.ProcessReadOutputToInputModel(scanOutput);
				
		ri.ProcessProblemModelToVerifFile(pbModVerif, Common.InputFileVerifPathFromOutputRead);	
		ro.EvaluateScoreFromOutput(sol);
	
		//// Serialize Solution class
		sol.SaveSolutionAsRaw(Common.SaveSerialFileName);
		
		//// Deserialize to verify there is no problem	
		Solution testdes = (Solution)(Common.FU.DeserializeFileToObject(Common.ACFileFolderPath+Common.SaveSerialFileName));
		System.out.println(testdes.testSolInt);		
			
		// back-up to folder
		ProcessAllBackupOfSolutionToFolder(sol, genOut);		
	}
		

	
	public static String getDirectoryNameWithScoreAndDate(int scorePrefix, String prefixDate)
	{			
		String resPath = String.format("%010d", scorePrefix)+ "_" + prefixDate;
		return resPath;
	}
	
	
	public static void ProcessAllBackupOfSolutionToFolder(Solution sol, GenerateOutput genOut)
	{
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
