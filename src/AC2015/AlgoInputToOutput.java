package AC2015;

import java.util.ArrayList;
import java.util.Random;


public class AlgoInputToOutput {

	
	

//	public static void main(String[] args) {
//		
//		AlgoInputToOutput algo = new AlgoInputToOutput();
//		MockTestGenerator mock = new MockTestGenerator();
//		
//		Problem pbModTest = mock.getProblemModTestAlgo();		
//		algo.AlgoSimple(pbModTest);
//	}	
		
	
	public Solution AlgoDandQ(Problem pb, int Rmin, int Rmax,int Cmin,int Cmax) //Cmax & Rmaax excluded
	{
		int Rsize = (Rmax-Rmin);
		int Csize = (Cmax-Cmin);
		
		int area = Rsize*Csize;
		if( area <=1)
		{
			return null;
		}
		
		if(  isValid(pb,Rmax,Rmin,Cmin,Cmax))
		{
			return new Solution( new Slice(Rmin, Rmax-1, Cmin, Cmax-1 , area)  );
		}
		return null;
//		
//		int Rcentre = (int)((Rmin+Rmax)/2);
//		int Ccentre = (int)((Cmin+Cmax)/2);
//		Solution A;
//		Solution B;
//		
//		if(Rsize>Csize)
//		{	
//			 A = AlgoDandQ(pb, Rmin, Rcentre ,Cmin,Cmax);
//			 B = AlgoDandQ(pb, Rcentre,Rmax ,Cmin,Cmax);
//
//			
//		}else
//		{
//			 A = AlgoDandQ(pb, Rmin, Rmax ,Cmin,Ccentre);
//			 B = AlgoDandQ(pb, Rmin,Rmax ,Ccentre,Cmax);
//
//		}
//		 if(A==null){
//			 A=B;
//		 }else if(B!=null)
//		 {
//			 A.slices.addAll(B.slices);
//		 }
//			
//		
//		return A;
	
	}
	
	boolean  isValid(Problem pb, int Rmax,int Rmin,int Cmin,int Cmax)
	{
		int Rsize = (Rmax-Rmin)+1;
		int Csize = (Rmax-Rmin)+1; //non mais ! pourquoi ça fait un meilleur score avec Rmax - Rmin que si c'était Cmax - Cmin, avec une solution valide !
		
		// taille pizza trop grande (plus que S)
		if( Rsize*Csize > pb.S)
		{
			return false;
		}
		
		// nb jambon.
		int NHAM = getNbHamSlice(pb, Rmax,Rmin,Cmin,Cmax);
		
		if(NHAM >= pb.H)
		{
			boolean resp = true;
				
				// verifier qu'on a pas "déjà bloqué par une autre part"
				for(int ii = Rmin;ii<Rmax;ii++ )
				{
					for(int jj = Cmin;jj<Cmax;jj++ )
					{
						if(pb.BLOCKED[ii][jj])
						{
							resp = false;
						}
					}
				}
				return resp;
				
		}else
		{
			return false;
		}
	}
	
	
	public int getNbHamSlice(Problem pb, int Rmax,int Rmin,int Cmin,int Cmax)
	{
			int NHAM = 0;
		
		// nombre de jambons de la part en 4 accès.
		// Plus : en bas à droite
		// Moins : en haut à droite, et en bas à gauche
		// Plus : en haut à gauche (qui a été retiré 2 fois, donc faut le rajouter une fois)
		//		________ 
		//      |		|
		//		|  +  +	|
		//		|  +  +	|
		//		|_______|

				
		
		if(Rmax>0 && Cmax>0)
		{
			 NHAM += pb.sumAdd[Rmax-1][Cmax-1];
		}
		if(Rmin>0 && Cmin>0)
		{
			 NHAM += pb.sumAdd[Rmin-1][Cmin-1];
		}
		if(Rmax>0 && Cmin>0)
		{
			 NHAM -= pb.sumAdd[Rmax-1][Cmin-1];
		}
		if(Rmin>0 && Cmax>0)
		{
			 NHAM -= pb.sumAdd[Rmin-1][Cmax-1];
		}		
		
		return NHAM;
	}
	
	
	public void buildTableauSumAdd(Problem pb)
	{
		
		pb.sumAdd = new int[pb.R][pb.C];
		for(int rr = 0;rr<pb.R;rr++)
		{
			for(int cc = 0;cc<pb.C;cc++)
			{
				int sum=0 ;
				if(pb.Pizza[rr][cc]=='H')
				{
					sum++;
				}
				if(rr>0)
				{
					sum += pb.sumAdd[rr-1][cc];
				}
				if(cc>0)
				{
					sum += pb.sumAdd[rr][cc-1];
				}
				if( (cc>0) && (rr>0) )
				{
					sum -= pb.sumAdd[rr-1][cc-1];
				}
				
				pb.sumAdd[rr][cc] = sum;
			}
		}
			
			
	}
	
	
	public Solution AlgoSimple(Problem pb)
	{
		System.out.println("Starting simple algo");
				
		////////////////
		
		Solution sol = new Solution(pb);
		sol.slices = new ArrayList<Slice>(); 
	
		//////
		buildTableauSumAdd(pb);
		//////////////////////////////////
		// TODO Write THE ALGORITHM :-) //
		//////////////////////////////////

		
		pb.BLOCKED = new boolean[pb.R][pb.C];
		
		
		int CSIZE = 2;
		int RSIZE = 4;
		
//		Random rand = new Random(42);
//		CSIZE = rand.nextInt(13);
//		RSIZE = rand.nextInt(13/CSIZE);
//		
		
		
			for(CSIZE = 12;CSIZE>0;CSIZE--)
			{
				for(RSIZE = 12;RSIZE>0;RSIZE--)
				{	
					
					//// CORE
					while(true)
					{
						
		    	 Solution soladd= firstAdd( pb,  RSIZE,  CSIZE);
					if(soladd!=null)
					{
							int rowSt = soladd.slices.get(0).rowS;
							int rowEnd = soladd.slices.get(0).rowE;
							int colSt = soladd.slices.get(0).colS;
							int colEnd = soladd.slices.get(0).colE;
						
						
							for(int ii = rowSt;ii<=rowEnd;ii++ )
							{
								for(int jj = colSt ;jj<=colEnd;jj++ )
								{
									// bloquer les cases déjà dans une part
									pb.BLOCKED[ii][jj]=true;
								}
							}
							
							// rajouter à la solution courante
							sol.slices.addAll(soladd.slices);
							
					}else{
						break;
						}
					}
					
					
					
				}
			}
	
	
	
		
				
		////////////////
				
		System.out.println("Finished algo simple");
		return sol ;
	}
	
	
	

	public Solution firstAdd(Problem pb, int RSIZE, int CSIZE)
	{
		if(RSIZE*CSIZE<=pb.S)
		{
			for(int Rstart = 0;Rstart<pb.R-RSIZE+1;Rstart++ )
			{
				for(int Cstart = 0;Cstart<pb.C-CSIZE+1;Cstart++ )
				{
	
					
					Solution soladd = AlgoDandQ(pb,Rstart,Rstart+RSIZE,Cstart,Cstart+CSIZE);
					if(soladd!=null)
					{
						return soladd;
	
					}
					
					
				}
				
			}
		}
		return null;
		
	}
	
		
	
}
