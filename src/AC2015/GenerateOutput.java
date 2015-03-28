package AC2015;

import java.io.PrintWriter;
import java.io.Serializable;

public class GenerateOutput implements Serializable{

	
	public static void main(String[] args) {

		GenerateOutput go = new GenerateOutput();
		MockTestGenerator mock = new MockTestGenerator();
		
		// Obtain output model.
	
		Problem pbMod = new Problem();
		//Problem pbMod = mock.getProblemModTestAlgo();
		//Problem pbMod = mock.getProblemTestFromReadInput();
		
		Solution outModTest = mock.getSolutionTest(pbMod);
		
		
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
	
//	
//	public void WriteOutputFileGenerationLogic(PrintWriter writer, Solution sol)
//	{		
//		writer.println(sol.testSolInt);
//		writer.println(sol.testSolString);
//					
//		writer.println("4242424242");
//		writer.println("output generator");					
//	}
//	
	
	public void WriteOutputFileGenerationLogic(PrintWriter writer, Solution sol)
	{
		//  !!!!!!!!!!!!!!!!!!! //
		// TODO CODE HERE Output Generation Logic from Output Model
		//  !!!!!!!!!!!!!!!!!!! //
			
		writer.println(sol.testSolInt);
		writer.println(sol.testSolString);
					
		writer.println("4242424242");
		writer.println("output generator");
					
		//  !!!!!!!!!!!!!!!!!!! //					
		
	}


	
}
