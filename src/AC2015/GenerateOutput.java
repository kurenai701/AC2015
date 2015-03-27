package AC2015;

import java.io.PrintWriter;
import java.util.Scanner;

public class GenerateOutput {

	
	public static void main(String[] args) {

		GenerateOutput go = new GenerateOutput();
		MockTestGenerator mock = new MockTestGenerator();
		
		ReadInput ri = new ReadInput();
		
		Scanner scanInput = ri.ScannerInputFile(Common.InputFilePathUnitTest);
		Problem pbMod = ri.ProcessReadInputToModel(scanInput);
		
		// Obtain output model.
	
		
		Solution outModTest = mock.getSolutionTest(pbMod);
		System.out.println(outModTest.GetScore());
		go.GenerateOutputFileFromOutputModel(outModTest, Common.OutputTestFileFullPathUnitTest);
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
			PrintWriter writer = Common.FU.CreateWriterFile(filePath, Common.OutputEncoding);
			if (writer != null)
			{
				
				WriteOutputFileGenerationLogic(writer, sol);
				writer.close();
			}		
			
		}
	}
	
	
	public void WriteOutputFileGenerationLogic(PrintWriter writer, Solution sol)
	{
		//  !!!!!!!!!!!!!!!!!!! //
		// TODO CODE HERE Output Generation Logic from Output Model
		//  !!!!!!!!!!!!!!!!!!! //
		
		
		writer.println(sol.getU());
		
		for(final Slice sl: sol.slices) 
		{
			writer.println(sl.rowS + " " + sl.colS + " " + sl.rowE + " " + sl.colE);
		}				
					
		//  !!!!!!!!!!!!!!!!!!! //					
		
	}


	
}
