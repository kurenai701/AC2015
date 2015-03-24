package AC2015;

public class CommonStatic {

	// FileUtil
	public static final FileUtil FU = new FileUtil();
	
	public static final String QualifFilesFolderPath = "C:\\ACQualifFile\\";
	
	public static final String ACFinalFilesFolderPath = "C:\\ACFile\\";
	
	
	
	// ZIP
	public static final String OUTPUT_ZIP_FILE = ACFinalFilesFolderPath+"FolderSourceMEGEClemAlex.zip"; 
	//  /!\ Folder ClemJava pour mes sources
	public static final String SOURCE_FOLDER = "C:\\ClemJava\\HC2015\\AC2015\\src";
	
	// INPUT
	public static final String InputFilePath = ACFinalFilesFolderPath+"dc.in";
	public static final String InputFilePathUnitTest =  ACFinalFilesFolderPath+"UNITTESTINPUT.txt";
	
	
	public static final String InputFileVerifPath = ACFinalFilesFolderPath+"verifInputFromInputModel.txt";
	public static final String InputEncoding = "UTF-8";
	
	//Input of Full processing GeneratedFromOutput
	public static final String InputFileVerifPathFromOutputRead = ACFinalFilesFolderPath+"verifInputFromOutput.txt";
	
	
	// OUTPUT	
	public static final String OutputGeneratedPathUnitTest = ACFinalFilesFolderPath+"UNITTESTOutputGenerated.txt";
	public static final String OutputGeneratedPath = ACFinalFilesFolderPath+"OutputGenerated.txt"; 	
	public static final String OutputEncoding = "UTF-8";
	
	
	// quand on aura généré le output generated, et qu'on veut le tester
//	public static final String OutputTestFilePath = ACFinalFilesFolderPath+"TESTEXEMPLEOUTPUT.txt";
	public static final String OutputTestFilePath = ACFinalFilesFolderPath+"OutputGenerated.txt";
	
	public static final String OutputTestFilePathUnitTest= ACFinalFilesFolderPath+"TESTEXEMPLEOUTPUT.txt";
	
	
	
	
}
