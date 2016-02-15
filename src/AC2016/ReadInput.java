package AC2016;

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
					
			pb = new Problem();
			pb.R = scIn.nextInt();
			pb.C = scIn.nextInt();
			pb.D = scIn.nextInt();
			pb.T = scIn.nextInt();
			pb.DL = scIn.nextInt();
			

			pb.WorldSim = new WorldSimulation(pb.R, pb.C, pb.D, pb.T, pb.DL);			
			
			
			// process products
			pb.P = scIn.nextInt();
			pb.WorldSim.buildProductTypeArray(pb.P);
		
			for (int i = 0; i < pb.P; i++)
			{	
				int weight = scIn.nextInt();				
				Product prod = new Product(i, weight);				
				pb.WorldSim.ProductTypeArray[i] = prod;	
			}
			
			// process warehouses
			pb.W = scIn.nextInt();
			pb.WorldSim.buildWarehouseArray(pb.W);
			
			for  (int j = 0; j < pb.W; j++)
			{
				// Warehouse Location
				int wX = scIn.nextInt();
				int wY = scIn.nextInt();				
				Pos whPos = new Pos(wX, wY, 'W');				
				Warehouse wh = new Warehouse(j, whPos, pb.P);
				
					for (int k = 0; k < pb.P; k++)
					{
						wh.prodStockArray[k] = scIn.nextInt();
					}				
				pb.WorldSim.WarehouseArray[j] = wh;
				pb.WorldSim.WorldPositions.add(whPos);
			}
			
			// process orders		
			pb.O = scIn.nextInt();
			for (int k = 1; k < pb.O; k++)
			{
				// CustomerLocation
				int cX = scIn.nextInt();
				int cY = scIn.nextInt();
								
				Pos custPos = new Pos(cX, cY, 'C');
				
				int nbItems = scIn.nextInt();
				Order ord = new Order(custPos, nbItems);
							
				for (int l = 0; l < ord.totalNumItems; l++)
				{
					ord.ProdListToDeliver.add(scIn.nextInt());
				}
			}				
		}		
		
		Sys.pln("pb with more info");
		
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
			
				writer.println(pb.testString);	
			
				
				//  !!!!!!!!!!!!!!!!!!! //
		    }				
				
			writer.close();
		}
	}
	

}
	
	