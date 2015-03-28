package AC2015;

import java.io.PrintWriter;
import java.io.Serializable;

public class GenerateOutput implements Serializable{

	
	public static void main(String[] args) {

		GenerateOutput go = new GenerateOutput();
		MockTestGenerator mock = new MockTestGenerator();
		AlgoInputToOutput al = new AlgoInputToOutput();
		
		// Obtain output model.
	
		// Problem pbMod = new Problem();
		//Problem pbMod = mock.getProblemModTestAlgo();
		Problem pbMod = mock.getProblemTestFromReadInput();
		
		
		// Solution outModTest = mock.getSolutionTest(pbMod);
		Solution outModTest = al.AlgoSimple(pbMod);
		
		
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


		String buildLine = "";
		// T Tours de simu
		for (int i = 0; i < sol.pb.T; i++)
		{
			
			for(final Ballon ball: sol.ballons) 
			{
				buildLine = buildLine + " " + (ball.aChanges.get(i).toString());
			}				
		
			buildLine = buildLine.trim();
			Sys.pln(buildLine);
			writer.println(buildLine);
		}
		
//		//  !!!!!!!!!!!!!!!!!!! //					
		
	}


	
}
