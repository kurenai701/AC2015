package AC2015;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Common {

	// FileUtil
	public static final FileUtil FU = new FileUtil();

	//	public static final String QualifFilesFolderPath = "C:\\ACQualifFile\\";
	public static final String ACFileFolderPath = "C:\\ACFile\\";
	

	/////////////// ZIP
	public static final String OUTPUT_ZIP_FILE = ACFileFolderPath+"FolderSourceMEGEClemAlex.zip"; 
	public static final String OUTPUT_ZIP_FULLPROC_FILE_NAME = "FullSourceMEGEClemAlex.zip"; 
	
	//  /!\ Folder ClemJava pour mon code Source
	public static final String SOURCE_FOLDER = "C:\\ClemJava\\HC\\AC2015\\src";
	// /!\ Folder Alex
	//	public static final String SOURCE_FOLDER = "C:\\AlexPath\\HashCode\\AC2015\\src";
		
	/////////////////////////////////
	/////////////// INPUT ///////////
	/////////////////////////////////
	public static final String InputEncoding = "UTF-8";
	
	public static final String InputFileName = "INPUT-dc.in";
	public static final String InputFilePath = ACFileFolderPath+InputFileName;
	
	public static final String InputFileNameUnitTest = "INPUT-UNITTEST.txt";
	public static final String InputFilePathUnitTest =  ACFileFolderPath+InputFileNameUnitTest;
	
	// ForInputVerification
	public static final String InputFileVerifFileName = "verifInputFromInputModel.txt";
	public static final String InputFileVerifPath = ACFileFolderPath+InputFileVerifFileName;	
	
	//Input of Full processing GeneratedFromOutput
	public static final String VerifInputFromOutputReadFileName = "verifInputFromReadOutput.txt";
	public static final String InputFileVerifPathFromOutputRead = ACFileFolderPath+VerifInputFromOutputReadFileName;
	
	/////////////////////////////////
	////////////// OUTPUT	/////////
	/////////////////////////////////
	public static final String OutputEncoding = "UTF-8";
	
	public static final String OutputGeneratedFileNameUnitTest = "UNITTESTOutputGenerated.txt";
	public static final String OutputGeneratedFullPathUnitTest = ACFileFolderPath+OutputGeneratedFileNameUnitTest;
		
	public static final String OutputGeneratedFileName = "OutputGenerated.txt";
	public static final String OutputGeneratedFullPath = ACFileFolderPath+OutputGeneratedFileName; 	
		

	
	// quand on aura généré le output generated, et qu'on veut le tester pour vérifier que c'est plus ou moins conforme avec l'input

	//	public static final String OutputTestFileName = "TESTEXEMPLEOUTPUT.txt";
	public static final String OutputTestFileName = "OutputGenerated.txt";
	public static final String OutputTestFileFullPath = ACFileFolderPath+OutputTestFileName;
	
	public static final String OutputTestFileNameUnitTest="TESTEXEMPLEOUTPUT.txt";
	public static final String OutputTestFileFullPathUnitTest= ACFileFolderPath+OutputTestFileNameUnitTest;
	
	
	// SAVE SERIALIZED SOLUTION
	public static final String SaveSerialFileName = "SolutionSerialized.ser";	
	
	
	public static final DateFormat FilePrefixdateFormat = new SimpleDateFormat("MMdd-HHmm");	
	//MMdd-hhmm
	public static String getTimeAsPrefixDateFormatString()
	{
		// Format Date For Prefix
		Date currentDate = new Date();		
		String prefixDate = Common.FilePrefixdateFormat.format(currentDate);
		return prefixDate;	
	}
	
	

	public static <T> List<T> WherePredicate(List<T> list, Predicate<T> pred)
	{
		List<T> resultList = 
				list.stream()
				.filter(pred)
				.collect(Collectors.toList());
	
		return resultList;		
	}
	
	
}
