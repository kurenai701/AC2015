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
					
			int R, C, A, L, V, B, T;
			
			R = scIn.nextInt();
			C = scIn.nextInt();
			A = scIn.nextInt();
			
			L = scIn.nextInt();
			V = scIn.nextInt();
			B = scIn.nextInt();
			T = scIn.nextInt();
			
			int rs;
			int cs;
			
			pb = new Problem(R, C, A, L, V, B, T);
			

			// cases départs des ballons
			for (int Bcounter = 0; Bcounter < B; Bcounter++)
			{				
				rs = scIn.nextInt();
				cs = scIn.nextInt();

				// TODO

				pb.StartPos[Bcounter] = new Pos(rs, cs, 0);
			}


			int ri;
			int ci;
			// cases cibles
			for (int Lcounter = 0; Lcounter < L; Lcounter++)
			{
				ri = scIn.nextInt();
				ci = scIn.nextInt();
				// TODO

				pb.TargetPos[Lcounter] = new Pos(ri, ci, 0);
			}
			
			
			// A Sections 
			
			int drc;
			int crc;
			for (int Acounter = 0; Acounter < A; Acounter++)
			{
				
				// R lignes
				for (int Rcounter = 0; Rcounter < R; Rcounter++)
				{				
					for (int Ccounter = 0; Ccounter < C; Ccounter++)
					{
						// C paires d'entier
						drc = scIn.nextInt();
						crc = scIn.nextInt();
					}
					
					// TODO
				}
				
				
				
				
				// TODO
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
				writer.println(pb.R + " " + pb.C + " " + pb.A);
				writer.println(pb.L + " " + pb.V + " " + pb.B + " " + pb.T);
				writer.println(pb.testString);	
			
				
				//  !!!!!!!!!!!!!!!!!!! //
		    }				
				
			writer.close();
		}
	}
	

}
	
	