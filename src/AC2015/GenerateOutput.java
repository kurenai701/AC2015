package AC2015;

import java.io.PrintWriter;
import java.util.Scanner;

public class GenerateOutput {

	
	public static void main(String[] args) {

		GenerateOutput go = new GenerateOutput();
		MockTestGenerator mock = new MockTestGenerator();
		
		ReadInput ri = new ReadInput();
		
		Scanner scanInput = ri.ScannerInputFileForUnitTest();
		Problem pbMod = ri.ProcessReadInputToModel(scanInput);
		
		// Obtain output model.
	
		Solution outModTest = mock.getSolutionTest(pbMod);
		go.GenerateOutputFileFromOutputModel(outModTest, CommonStatic.OutputGeneratedPathUnitTest);
	}	

	public void GenerateOutputFileFromOutputModel(Solution sol, String filePath)
	{
		System.out.println("GenerateOutputFileFromOutputModel");
		if (sol == null)
		{
			System.out.println("outModel null");
		}
		else
		{
			PrintWriter writer = CommonStatic.FU.CreateWriterFile(filePath, CommonStatic.OutputEncoding);
			if (writer != null)
			{
				//  !!!!!!!!!!!!!!!!!!! //
				// TODO CODE HERE Output Generation Logic
				//  !!!!!!!!!!!!!!!!!!! //
		
				writer.println(sol.testSolInt);
				writer.println(sol.testSolString);
				
				writer.println("4242424242");
				writer.println("output generator");
				
				//  !!!!!!!!!!!!!!!!!!! //
				
				writer.close();
			}		
		}
	}
	

	
}
