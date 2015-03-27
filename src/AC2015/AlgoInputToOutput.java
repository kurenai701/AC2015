package AC2015;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class AlgoInputToOutput {

	
	

	public static void main(String[] args) {
		
		AlgoInputToOutput algo = new AlgoInputToOutput();
		MockTestGenerator mock = new MockTestGenerator();
		
		Problem pbModTest = mock.getProblemModTestAlgo();		
		algo.AlgoSimple(pbModTest);
	}	
		
	
	public Solution AlgoDandQ(Problem pb, int Rmin, int Rmax,int Cmin,int Cmax) //Cmax & Rmaax excluded
	{
		int Rsize = (Rmax-Rmin);
		int Csize = (Rmax-Rmin);
		
		int area = Rsize*Csize;
		if( area <=1)
		{
			return null;
		}
		
		if(  isValid(pb,Rmax,Rmin,Cmin,Cmax))
		{
			return new Solution( new Slice(Rmin, Rmax, Cmin-1, Cmax-1 , area)  );
		}
		
		int Ccentre = (int)((Rmin+Rmax)/2);
		int Rcentre = (int)((Cmin+Cmax)/2);
		Solution A;
		Solution B;
		
		if(Rsize>Csize)
		{	
			 A = AlgoDandQ(pb, Rmin, Rcentre ,Cmin,Cmax);
			 B = AlgoDandQ(pb, Rcentre+1,Rmax ,Cmin,Cmax);
			A.slices.addAll(B.slices);
			
		}else
		{
			 A = AlgoDandQ(pb, Rmin, Rmax ,Cmin,Ccentre);
			 B = AlgoDandQ(pb, Rmin,Rmax ,Ccentre,Cmax);
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
		
		return ( pb.sumAdd[Rmax-1][Cmax-1]+pb.sumAdd[Rmin-1][Cmin-1]-
				(pb.sumAdd[Rmax-1][Cmin-1] + pb.sumAdd[Rmin-1][Cmax-1])) > pb.H ;
		
		
	}
	
	
	public Solution AlgoSimple(Problem pb)
	{
		System.out.println("Starting simple algo");
				
		////////////////
		
		Solution sol = new Solution();
		
		//////////////////////////////////
		// TODO Write THE ALGORITHM :-) //
		//////////////////////////////////
				
	//	char[][] Pizza; 
	//	int[][] sumAdd;
		for(int rr = 0;rr<pb.R;rr++)
		{
			for(int cc = 0;cc<pb.R;cc++)
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
				if(cc>0&&rr>0)
				{
					sum -= pb.sumAdd[rr-1][cc-1];
				}
				
				pb.sumAdd[rr][cc] = sum;
			}
		}
		
		
		sol = AlgoDandQ(pb,0,pb.R,0,pb.C);
		
		
		
		
		
		
		
		
		
		
		
		
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
