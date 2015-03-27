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
		
		if(  isValid(Rmax,Rmin,Cmin,Cmax))
		{
			return new Solution( new Slice(Rmin, Rmax, Cmin-1, Cmax-1 , area)  )
		}
		
		int Ccentre = (int)((Rmin+Rmax)/2);
		int Rcentre = (int)((Cmin+Cmax)/2);
		
		if(Rsize>Csize)
		{	
			Solution A = AlgoDandQ(pb, Rmin, Rcentre ,Cmin,Cmax);
			Solution B = AlgoDandQ(pb, Rcentre+1,Rmax ,Cmin,Cmax);
			A.slices.addAll(B.slices);
			
		}else
		{
			Solution A = AlgoDandQ(pb, Rmin, Rcentre ,Cmin,Cmax);
			Solution B = AlgoDandQ(pb, Rcentre+1,Rmax ,Cmin,Cmax);
			A.slices.addAll(B.slices);
			
		}
			
		
		return A;
	
	}
	public Solution AlgoSimple(Problem pb)
	{
		System.out.println("Starting simple algo");
				
		////////////////
		
		Solution sol = new Solution();
		
		//////////////////////////////////
		// TODO Write THE ALGORITHM :-) //
		//////////////////////////////////
				
		sol = AlgoDandQ(pb,0,R,0,C);
		
		
		
		
		
		
		
		
		
		
		
		
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
