package AC2015;

import java.util.Scanner;

public class ReadOutput {	

	
	
	public static void main(String[] args) {
		
		ReadOutput ro = new ReadOutput();
		Scanner scanOutputFile = ro.ScannerOutputFile(Common.OutputTestFileFullPathUnitTest);
		scanOutputFile.hashCode();
		
		Problem pbModVerif = ro.ProcessReadOutputToProblemModel(scanOutputFile);
	
		System.out.println(pbModVerif.testString);
		System.out.println(pbModVerif.testint);
	
	}
	
	public Scanner ScannerOutputFile(String OutputFilePath)
	{
		return Common.FU.ScannerFile(OutputFilePath);
	}
	
	
	
	///// SI CA S'Y PRETE POUR DES TESTS, DEPUIS L'OUTPUT, RECREER LE MODELE DU PROBLEME /////
	public Problem ProcessReadOutputToProblemModel(Scanner scOut)
	{
		System.out.println("ProcessReadOutputToProblemModel");
		Problem pb = new Problem();
		
		if (scOut != null)
		{				
			//  !!!!!!!!!!!!!!!!!!! //
			// TODO	CODE here parsing and processing of output file to obtain problem model	(si ça s'y prête)
			//  !!!!!!!!!!!!!!!!!!! //
					
			pb.testint = scOut.nextInt();
			pb.testString = scOut.next();
			//  !!!!!!!!!!!!!!!!!!! //
		}			
		return pb;
	
	}
	
	
	
///// SI CA S'Y PRETE POUR DES TESTS, DEPUIS L'OUTPUT, RECREER LA SOLUTION  (l'inverse de GenerateOutputFile) /////
	public Solution ProcessReadOutputBackToSolution(Scanner scOut)
	{
		System.out.println("ProcessReadOutputBackToSolutionModel");
		Solution sol = new Solution(new Problem());
		
		if (scOut != null)
		{
			//  !!!!!!!!!!!!!!!!!!! //
			// TODO	CODE here parsing and processing of output file to obtain problem model	(si ça s'y prête)
			//  !!!!!!!!!!!!!!!!!!! //
			
			//sol.testSolInt = scOut.nextInt();			
			
			//  !!!!!!!!!!!!!!!!!!! //			
		}
		
		return sol;
	}
	
	
}
