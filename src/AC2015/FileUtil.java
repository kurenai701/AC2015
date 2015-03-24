package AC2015;


import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


public class FileUtil {

	
	public Scanner ScannerFile(String fileFullpath)
	{
		try
		{
			// Open Text File
			Scanner fileScan = new Scanner(new FileReader(fileFullpath));
			System.out.println("File Found : " + fileFullpath);
			return fileScan;
		}
		catch (FileNotFoundException fnfe)
		{
			System.out.println("File not found : " + fileFullpath);
			fnfe.printStackTrace();
			return null;
		}	
		catch (Exception e)
		{
			System.out.println("ScannerFile Exception ");
			e.printStackTrace();
			return null;
		}
	}
	
	
	public PrintWriter CreateWriterFile (String fileFullpath, String encoding) // default parameter like in C# ?
	{
		PrintWriter writer;
		try {
			
			String targetEncoding;
			// put default encoding if null			
			targetEncoding = ((encoding == null || encoding.isEmpty()) ? "UTF-8" : encoding);
			
			
			writer = new PrintWriter(fileFullpath, encoding);
			System.out.println("Writer Created " + fileFullpath + " With encoding " + targetEncoding);
			return writer;
		}
		catch(UnsupportedEncodingException uee)
		{
			System.out.println(" encountered unsupportedEncodingException");
			uee.printStackTrace();
			return null;
		}
		catch(FileNotFoundException fnfe)
		{
			System.out.println(" encountered FileNotFoundException");
			fnfe.printStackTrace();
			return null;
		}
		catch (Exception e)
		{
			System.out.println("WriterFile Exception " + e.toString());
			e.printStackTrace();
			return null;
		}
	}

}
