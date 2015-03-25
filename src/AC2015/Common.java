package AC2015;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {

	// FileUtil
	public static final FileUtil FU = new FileUtil();
	
	public static final String QualifFilesFolderPath = "C:\\ACQualifFile\\";
	
	public static final String ACFileFolderPath = "C:\\ACFile\\";
	
	public static final DateFormat FilePrefixdateFormat = new SimpleDateFormat("MMdd-HHmm");
	
	// ZIP
	public static final String OUTPUT_ZIP_FILE = ACFileFolderPath+"FolderSourceMEGEClemAlex.zip"; 
	public static final String OUTPUT_ZIP_FULLPROC_FILE_NAME = "FullSourceMEGEClemAlex.zip"; 
	//  /!\ Folder ClemJava pour mes sources
	public static final String SOURCE_FOLDER = "C:\\ClemJava\\HC\\AC2015\\src";
	// /!\ Folder Alex
	//	public static final String SOURCE_FOLDER = "C:\\AlexPath\\HashCode\\AC2015\\src";
		
	// INPUT
	public static final String InputFilePath = ACFileFolderPath+"dc.in";
	public static final String InputFilePathUnitTest =  ACFileFolderPath+"INPUT-UNITTEST.txt";
	
	
	public static final String InputFileVerifPath = ACFileFolderPath+"verifInputFromInputModel.txt";
	public static final String InputEncoding = "UTF-8";
	
	//Input of Full processing GeneratedFromOutput
	public static final String InputFileVerifPathFromOutputRead = ACFileFolderPath+"verifInputFromOutput.txt";
	
	
	// OUTPUT	
	public static final String OutputGeneratedFullPathUnitTest = ACFileFolderPath+"UNITTESTOutputGenerated.txt";
		
	public static final String OutputGeneratedFileName = "OutputGenerated.txt";
	public static final String OutputGeneratedFullPath = ACFileFolderPath+OutputGeneratedFileName; 	
		
	public static final String OutputEncoding = "UTF-8";
	
	
	// quand on aura généré le output generated, et qu'on veut le tester
		
	//	public static final String OutputTestFileName = "TESTEXEMPLEOUTPUT.txt";
	public static final String OutputTestFileName = "OutputGenerated.txt";
	public static final String OutputTestFileFullPath = ACFileFolderPath+OutputTestFileName;
	
	public static final String OutputTestFileNameUnitTest="TESTEXEMPLEOUTPUT.txt";
	public static final String OutputTestFileFullPathUnitTest= ACFileFolderPath+OutputTestFileNameUnitTest;
	
	
	// SAVE SERIALIZED
	public static final String SaveSerialFileName = "SolutionSerialized.ser";
	
	
	
	
	
	//MMdd-hhmm
	public static String getTimeAsPrefixDateFormatString()
	{
		// Format Date For Prefix
		Date currentDate = new Date();		
		String prefixDate = Common.FilePrefixdateFormat.format(currentDate);
		return prefixDate;	
	}
	
}
