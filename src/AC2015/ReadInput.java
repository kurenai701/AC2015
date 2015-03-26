package AC2015;

import java.io.PrintWriter;
import java.util.Scanner;



public class ReadInput {
	

	public static void main(String[] args) {
		
		ReadInput ri = new ReadInput();
		Scanner scanInput = ri.ScannerInputFile(Common.InputFilePathUnitTest);
		Problem pbMod = ri.ProcessReadInputToModel(scanInput);
		ri.ProcessProblemModelToVerifFile(pbMod, Common.InputFileVerifPath);
	}
	
	public Scanner ScannerInputFile(String fileFullPath)
	{
		return Common.FU.ScannerFile(fileFullPath);
	}
	
			
	// From Input file to Input Model
	public Problem ProcessReadInputToModel(Scanner scIn)
	{	
		System.out.println("ProcessReadInputToModel");
		Problem pb = new Problem();
		
		if (scIn != null)
		{
			//  !!!!!!!!!!!!!!!!!!! //
			// TODO	CODE here parsing of file, and saving to model	
			//  !!!!!!!!!!!!!!!!!!! //
					
			int A = scIn.nextInt();
			
			String test = scIn.next();
			
			pb = new Problem(A, null, test);
			
			// SOME PIECES OF CODE FOR REFERENCE
//			
//			int tempInt;
//			String tempString;
//			double tempDouble;
//			String tempLine;
//			
//			for (int counter = 0; counter < 42/*TODO change*/; counter++)
//			{				
//				tempInt = scIn.nextInt();
//				tempString = scIn.next(); 
//				tempDouble = scIn.nextDouble();
//				tempLine = scIn.nextLine();
//				
//				// faire qqch 
//			}
//			
			//  !!!!!!!!!!!!!!!!!!! //
		}	
		
		return pb;
	}
	
		
	// If time, and if we want to verify that we have correct modeling
	// From Input Model to Something similar as Input Text File
	public void ProcessProblemModelToVerifFile(Problem pb, String FilePath)
	{
		System.out.println("ProcessProblemModelToVerifFile");
		
		// Writer
		PrintWriter writer = Common.FU.CreateWriterFile(FilePath, "UTF-8");
		
		if (writer != null)
		{
			if (pb != null)
			{	
			//  !!!!!!!!!!!!!!!!!!! //
				// TODO CODE here processing to read the model and write the file
				//  !!!!!!!!!!!!!!!!!!! //
				
				
				// write first line
				writer.println("print 1st line of ProcessProblemModelToVeriFile");
				writer.println(pb.testint);
				writer.println(pb.testString);	
			
				
				//  !!!!!!!!!!!!!!!!!!! //
		    }				
				
			writer.close();
		}
	}
	

}
	
	