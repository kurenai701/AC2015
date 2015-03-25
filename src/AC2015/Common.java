package AC2015;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Common {

	// FileUtil
	public static final FileUtil FU = new FileUtil();
	
	public static final String QualifFilesFolderPath = "C:\\ACQualifFile\\";
	
	public static final String ACFinalFilesFolderPath = "C:\\ACFile\\";
	
	public static final DateFormat FilePrefixdateFormat = new SimpleDateFormat("MMdd-HHmm");
	
	// ZIP
	public static final String OUTPUT_ZIP_FILE = ACFinalFilesFolderPath+"FolderSourceMEGEClemAlex.zip"; 
	public static final String OUTPUT_ZIP_FULLPROC_FILE_NAME = "FullSourceMEGEClemAlex.zip"; 
	//  /!\ Folder ClemJava pour mes sources
	public static final String SOURCE_FOLDER = "C:\\ClemJava\\HC\\AC2015\\src";
	// /!\ Folder Alex
	//	public static final String SOURCE_FOLDER = "C:\\AlexPath\\HashCode\\AC2015\\src";
		
	// INPUT
	public static final String InputFilePath = ACFinalFilesFolderPath+"dc.in";
	public static final String InputFilePathUnitTest =  ACFinalFilesFolderPath+"UNITTESTINPUT.txt";
	
	
	public static final String InputFileVerifPath = ACFinalFilesFolderPath+"verifInputFromInputModel.txt";
	public static final String InputEncoding = "UTF-8";
	
	//Input of Full processing GeneratedFromOutput
	public static final String InputFileVerifPathFromOutputRead = ACFinalFilesFolderPath+"verifInputFromOutput.txt";
	
	
	// OUTPUT	
	public static final String OutputGeneratedFullPathUnitTest = ACFinalFilesFolderPath+"UNITTESTOutputGenerated.txt";
		
	public static final String OutputGeneratedFileName = "OutputGenerated.txt";
	public static final String OutputGeneratedFullPath = ACFinalFilesFolderPath+OutputGeneratedFileName; 	
		
	public static final String OutputEncoding = "UTF-8";
	
	
	// quand on aura généré le output generated, et qu'on veut le tester
		
	//	public static final String OutputTestFileName = "TESTEXEMPLEOUTPUT.txt";
	public static final String OutputTestFileName = "OutputGenerated.txt";
	public static final String OutputTestFileFullPath = ACFinalFilesFolderPath+OutputTestFileName;
	
	public static final String OutputTestFileNameUnitTest="TESTEXEMPLEOUTPUT.txt";
	public static final String OutputTestFileFullPathUnitTest= ACFinalFilesFolderPath+OutputTestFileNameUnitTest;
	
	
	// SAVE SERIALIZED
	public static final String SaveSerialFileName = "SolutionSerialized.ser";
	
}
