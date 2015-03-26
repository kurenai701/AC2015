package AC2015;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
	public static final Locale ScannerLocale = Locale.US;

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



	public static <T> List<T> Where(List<T> list, Predicate<T> pred)
	{
		List<T> resultList = 
				list.stream()
				.filter(pred)
				.collect(Collectors.toList());

		return resultList;		
	}


	public static <T> T First(List<T> list, Predicate<T> pred)
	{		
		List<T> resultList = Where(list, pred);
		if (resultList != null && resultList.size() > 0) 
		{
			T firstRes = resultList.get(0);
			return firstRes;
		}
		else
			return null;

	}



	public static ByteArrayOutputStream SerializeObjectToStream(Object objToCopy)
	{		
		ObjectOutputStream oos = null;
		ByteArrayOutputStream bos = null;

		try
		{
			bos = new ByteArrayOutputStream(); 
			oos = new ObjectOutputStream(bos); 
			// serialize and pass the object
			try
			{
				oos.writeObject(objToCopy);   					
			}			
			catch (Exception e)
			{
				System.out.println(" encountered Exception on oos.writeObject");
				e.printStackTrace();
				return null;
			}
		}
		catch (Exception e)
		{
			System.out.println("encountered Exception in SerializeToStream");
			e.printStackTrace();
			return null;
		}		
		finally
		{
			if (oos != null)
			{
				try
				{
					oos.flush();
				} 
				catch (Exception e)
				{
					System.out.println("encountered Exception in oos.flush");
					e.printStackTrace();
				}    			
			}
		}				
		return bos;
	}
	
	
	
	public static Object DeserializeStreamToObject(ByteArrayOutputStream bos)
	{
		Object newObj = null;
		
		ObjectInputStream ois = null;		
		ByteArrayInputStream bin = null;
		
		try
		{
			bin = new ByteArrayInputStream(bos.toByteArray());
			ois = new ObjectInputStream(bin); 

			// deserialize by reading
			try
			{
				newObj = (ois.readObject()); // On va supposer qu'il n'y aura pas de problem de cast
			}
			catch(Exception e)
			{
				System.out.println("encountered Exception in DeserializeStreamToObject ois.readObject()");
				e.printStackTrace();
			}
			finally
			{		
				try
				{
					ois.close();
				}
				catch (Exception e)
				{
					System.out.println("encountered Exception in DeserializeStreamToObject ois.close()");
					e.printStackTrace();
				}   

			}
		}
		catch (Exception e)
		{
			System.out.println("encountered Exception in DeserializeStreamToObject");
			e.printStackTrace();
		}
		return newObj;
	}
	

	public static <T> T DeepCopy(T objToCopy)
	{

		T newObj = (T)DeserializeStreamToObject(SerializeObjectToStream(objToCopy)); // on va supposer que le cast fonctionne
		

		return newObj;

	}





}
