package AC2016;

import java.io.PrintWriter;
import java.io.Serializable;

public class GenerateOutput implements Serializable{

	
	public static void main(String[] args) {

		GenerateOutput go = new GenerateOutput();
		MockTestGenerator mock = new MockTestGenerator();
		AlgoInputToOutput al = new AlgoInputToOutput();
		
//		AlgoClem algoCl = new AlgoClem();
		
		
		// Obtain output model.
	
		// Problem pbMod = new Problem();
		//Problem pbMod = mock.getProblemModTestAlgo();
		Problem pbMod = mock.getProblemTestFromReadInput();
		
		
		// Solution outModTest = mock.getSolutionTest(pbMod);
		//Solution outModTest = al.AlgoSimple(pbMod);
	//	Solution outModTest = algoCl.ClemAlgo(pbMod);
		
	//	go.GenerateOutputFileFromOutputModel(outModTest, Common.OutputTestFileFullPathUnitTest);
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
			buildLine = "";
			//for(final Ballon ball: sol.ballons) 
			{
				int instruction = 0;	
				buildLine = buildLine + " " + (instruction);
			}				
		
			buildLine = buildLine.trim();
		//	Sys.pln(buildLine);
			writer.println(buildLine);
		}
		
//		//  !!!!!!!!!!!!!!!!!!! //					
		
	}


	
}
