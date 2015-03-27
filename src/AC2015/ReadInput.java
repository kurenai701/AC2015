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
			
			int R;
			int C;
			int H;
			int S;
			
			R = scIn.nextInt();
			C = scIn.nextInt();
			H = scIn.nextInt();
			S = scIn.nextInt();
			
			
			pb = new Problem(R,C,H,S);
			pb.Pizza = new char[R][C];
						
			String line;
			for (int rcounter = 0; rcounter < R; rcounter++)
			{	
				line = scIn.next();
				
				for (int c=0; c<C; c++)
				{
					pb.Pizza[rcounter][c] = line.charAt(c);
				}
			}
			
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
//				
				
				writer.println(pb.R + " " +  pb.C + " " + pb.H + " " + pb.S);
//				writer.println(pb.testint);
//				writer.println(pb.testString);	
//			
				
				//  !!!!!!!!!!!!!!!!!!! //
		    }				
				
			writer.close();
		}
	}
	

}
	
	