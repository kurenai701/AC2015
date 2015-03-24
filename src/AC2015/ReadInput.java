package AC2015;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class ReadInput {

	
	

	public static void main(String[] args) {
		
		ReadInput ri = new ReadInput();
		
		Scanner scanInput = ri.ScannerInputFileForUnitTest();
		ProblemModel pbMod = ri.ProcessReadInputToModel(scanInput);
		ri.ProcessInputModelToVerifFile(pbMod, CommonStatic.InputFileVerifPath);

	}
	
	public Scanner ScannerInputFile()
	{
		return CommonStatic.FU.ScannerFile(CommonStatic.InputFilePath);
	}
	
	public Scanner ScannerInputFileForUnitTest()
	{
		return CommonStatic.FU.ScannerFile(CommonStatic.InputFilePathUnitTest);
	}
			
	// From Input file to Input Model
	public ProblemModel ProcessReadInputToModel(Scanner scIn)
	{	
		System.out.println("ProcessReadInputToModel");
		ProblemModel pbm = new ProblemModel();
		
		if (scIn != null)
		{
			//  !!!!!!!!!!!!!!!!!!! //
			// TODO	CODE here parsing of file, and saving to model	
			//  !!!!!!!!!!!!!!!!!!! //
					
			int A = scIn.nextInt();
			String test = scIn.next();
						
			pbm = new ProblemModel(A,null, test);
													
			//  !!!!!!!!!!!!!!!!!!! //
		}	
		
		return pbm;
	}
	
	
	
	// If time, and if we want to verify that we have correct modeling
	// From Input Model to Something same as Input Text File
	public void ProcessInputModelToVerifFile(ProblemModel pb, String FilePath)
	{
		System.out.println("ProcessModelToVerifFile");
		
		// Writer
		PrintWriter writer = CommonStatic.FU.CreateWriterFile(FilePath, "UTF-8");
		
		if (writer != null)
		{
			if (pb != null)
			{	
				//  !!!!!!!!!!!!!!!!!!! //
				// TODO CODE here processing to read the model and write the file
				//  !!!!!!!!!!!!!!!!!!! //
				
				// write first line
				writer.println("first line");
				writer.println(pb.testint);
				writer.println(pb.testString);
				
		    }				
				//  !!!!!!!!!!!!!!!!!!! //
			writer.close();
		}
	}
}
	
	