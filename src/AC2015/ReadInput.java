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
			

				rs = scIn.nextInt();
				cs = scIn.nextInt();

				pb.StartPos = new Pos(rs, cs, 0);
			

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
			for (int Acounter = 1; Acounter <= A; Acounter++)
			{				
				// R lignes
				for (int Rcounter = 0; Rcounter < R; Rcounter++)
				{				
					for (int Ccounter = 0; Ccounter < C; Ccounter++)
					{
						// C paires d'entier
						drc = scIn.nextInt();
						crc = scIn.nextInt();
						
						// TODO
						pb.WorldWindMvt[Rcounter][Ccounter][Acounter] = new Mvt(drc, crc);
					}
				}
			}
			
			
			// Améliorer le problème model, parcourir le monde et voir les next positions
			
			// A Sections 
			for (int Acounter = 1; Acounter <= pb.A; Acounter++)
			{				
				// R lignes
				for (int Rcounter = 0; Rcounter < pb.R; Rcounter++)
				{				
					// C paires d'entier
					
					for (int Ccounter = 0; Ccounter < pb.C; Ccounter++)
					{					
						Pos p = new Pos(Rcounter, Ccounter, Acounter);
						
						for (int i = -1; i <= +1; i++)
						{
							int aa = Acounter+i;						
							
							if (aa >= 1 && aa <= pb.A)
							{
								Mvt mv = pb.WorldWindMvt[Rcounter][Ccounter][aa];						
								
								int newR = (Rcounter+mv.drc);
								int newC = (Ccounter+mv.crc)%pb.C;
								
								Pos nextp = new Pos(newR, newC, aa);
								
								// si sors des "rows" => move invalid
								if (newR < 0 || newR >= pb.R)
								{
									nextp = Pos.OUTOFMAP;
						//			Sys.pln("out of map from " + Rcounter + ","+ Ccounter + "," + aa + " to : " + newR + ","+ newC + "," + aa);	
									p.moves.add(Move.INVALID);
								}
								else // normal
								{
									Move move = new Move(nextp, 0,i);
									p.moves.add(move);
								}
							}
						}
																
						pb.AllPos.add(p);
						
						
					}
				}
			} 
		
		
		
			
			
		}
		
		setCoveredList(pb);
		
		Sys.pln("pb with more info");
		
		return pb;
	}
	
	
	public void setCoveredList(Problem pb)
	{
		//create allPosMat
		pb.AllPosMat = new Pos[pb.R][pb.C][pb.A+1];
		
		for(Pos p : pb.AllPos)
		{
			pb.AllPosMat[p.x][p.y][p.z]=p;
		}
		pb.AllPosMat[ pb.StartPos.x][pb.StartPos.y ][0] =  pb.StartPos;
	
		
		
		
		for(int Ncible = 0;Ncible < pb.L;Ncible++)
		{
			Pos curCible = pb.TargetPos[Ncible];
			for(int rDiff = -pb.V ; rDiff<=pb.V;rDiff++)
			{
				for(int cDiff = -pb.V ; cDiff<=pb.V;cDiff++)
				{
					if(cDiff*cDiff + rDiff*rDiff <= pb.V*pb.V)
					{
						// Covered cell
						int cellR = curCible.x+rDiff;
						int cellC = ((curCible.y+cDiff+2*pb.C)%pb.C);// modulo C
						if(cellR>=0 && cellR< pb.R)
						{
							for(int a=1;a<=pb.A;a++)
							{
									pb.AllPosMat[cellR][cellC][a].coverList.add(Ncible);
							}
						}
					}
					
				}
				
			}
		}
//Add start move		
		Pos temp = pb.AllPosMat[pb.StartPos.x  ][pb.StartPos.y][1];
		
		Pos nPosStart = temp.moves.get(0).nextPos;//ugly hack
		pb.StartPos.moves.add(new Move(  nPosStart  , 1, 1));

		pb.StartPos.moves.add(new Move(  pb.StartPos  , 1,0));
		
		
		Sys.pln("Fin");
		
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
	
	