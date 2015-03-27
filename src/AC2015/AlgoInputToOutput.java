package AC2015;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
		
		int Rcentre = (int)((Rmin+Rmax)/2);
		int Ccentre = (int)((Cmin+Cmax)/2);
		Solution A;
		Solution B;
		
		if(Rsize>Csize)
		{	
			 A = AlgoDandQ(pb, Rmin, Rcentre ,Cmin,Cmax);
			 B = AlgoDandQ(pb, Rcentre,Rmax ,Cmin,Cmax);

			
		}else
		{
			 A = AlgoDandQ(pb, Rmin, Rmax ,Cmin,Ccentre);
			 B = AlgoDandQ(pb, Rmin,Rmax ,Ccentre,Cmax);

		}
		 if(A==null){
			 A=B;
		 }else if(B!=null)
		 {
			 A.slices.addAll(B.slices);
		 }
			
		
		return A;
	
	}
	
	boolean  isValid(Problem pb, int Rmax,int Rmin,int Cmin,int Cmax)
	{
		int Rsize = (Rmax-Rmin);
		int Csize = (Rmax-Rmin);
		
		if( Rsize*Csize > pb.S)
		{
			return false;
		}
		int NHAM = 0;
		
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
		
	
		
		if(NHAM > pb.H)
		{
			boolean resp = true;
				
				for(int ii = Rmin;ii<Rmax;ii++ )
				{
					for(int jj = Rmin;jj<Cmax;jj++ )
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
	
	
	public Solution AlgoSimple(Problem pb)
	{
		System.out.println("Starting simple algo");
				
		////////////////
		
		Solution sol = new Solution(pb);
		sol.slices = new ArrayList<Slice>(); 
	
		//////////////////////////////////
		// TODO Write THE ALGORITHM :-) //
		//////////////////////////////////
				
	//	char[][] Pizza; 
	//	int[][] sumAdd;
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
		pb.BLOCKED = new boolean[pb.R][pb.C];
		
		
		int CSIZE = 2;
		int RSIZE = 4;
		for(RSIZE = 12;RSIZE>0;RSIZE--)
		{
			for(CSIZE = 12;CSIZE>0;CSIZE--)
			{
			
							if(RSIZE*CSIZE<=pb.S)
							{
								
							
							
						
							for(int Rstart = 0;Rstart<pb.R-RSIZE;Rstart++ )
							{
								for(int Cstart = 0;Rstart<pb.R-CSIZE;Rstart++ )
								{
					
									Solution soladd = AlgoDandQ(pb,Rstart,Rstart+CSIZE,Cstart,Cstart+RSIZE);
									if(soladd!=null)
									{
										for(int ii = 0;ii<RSIZE;ii++ )
										{
											for(int jj = 0;jj<CSIZE;jj++ )
											{
												pb.BLOCKED[ii][jj]=true;
					
											}
										}
										sol.slices.addAll(soladd.slices);
									}
									
									
								}
								
							}
							}
			}//CSIZE
		}//RSIZE
		
		
		
		
		
		
		
		
		
		
		
		////////////////
				
		System.out.println("Finished algo simple");
		return sol ;
	}
	
	
	
	
	
	
	public Solution AlgoComplicatedFromProblem(Problem pb)
	{
		System.out.println("Starting complicatedalgo");
		Solution sol = new Solution();
		
		//////////////////////////////////
		// TODO Write THECOMPLICATED ALGORITHM :-) //
		//////////////////////////////////
				
	
		////////////////
				
		System.out.println("Finished complicatedalgo");
		return sol ;
	}
	
	
	
}
