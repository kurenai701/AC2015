package AC2015;

import java.io.PrintWriter;
import java.util.Scanner;

public class GenerateOutput {

	
	public static void main(String[] args) {

		GenerateOutput go = new GenerateOutput();
		MockTestGenerator mock = new MockTestGenerator();
		
		ReadInput ri = new ReadInput();
		
		Scanner scanInput = ri.ScannerInputFileForUnitTest();
		ProblemModel pbMod = ri.ProcessReadInputToModel(scanInput);
		
		// Obtain output model.
	
		OutputModel outModTest = new OutputModel(pbMod);	
		go.GenerateOutputFileFromOutputModel(outModTest, CommonStatic.OutputGeneratedPathUnitTest);
	}	

	public void GenerateOutputFileFromOutputModel(OutputModel outM, String filePath)
	{
		System.out.println("GenerateOutputFileFromOutputModel");
		if (outM == null)
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
		
				writer.println("4242424242");
				writer.println("output generator");
				
				
				
				//  !!!!!!!!!!!!!!!!!!! //
				
				writer.close();
			}		
		}
	}
	

	
}
