package AC2015;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;





/**
 * @author Alyx
 * // Stores a partial path for a Ballon
 * Two Partial PAths are different only if they reach new cibles at different times.
 */
public class PartialPath implements Comparable<PartialPath>{
	public ArrayList<Integer> posList = new ArrayList<Integer>();
	public OptimizeBallon optB;
	public Problem pb;
	public int Tstart;

	static int tempcounter = 1;
	
	/**
	 * @param start Index of the start position
	 * @param optB  OptimizeBallon
	 * @param pb	Problem
	 * @param Tstart Time of optimisation start
	 */
	public PartialPath(int start, OptimizeBallon optB,Problem pb, int Tstart) {
		super();
		this.optB = optB;
		posList.add(start);
		this.pb=pb;
		this.Tstart = Tstart;
	}


	public PartialPath(PartialPath o) {
		posList.addAll(o.posList);
		optB	=	o.optB;
		pb		=	o.pb;
		Tstart	=	o.Tstart;
		
	}


	// Hash function to implements efficient HashSet
	@Override
	public int hashCode() {
		boolean USEPATHTRIMMING = true;
		if(USEPATHTRIMMING)
		{
			
		
		long hash = 10;
		int tt = Tstart;
		for(int posN :posList)
		{
			
			Pos p = optB.mappedPos[posN];
			List<Integer> coverList = optB.posXY[p.x+pb.R*p.y].coverList;
//			System.out.print("at Time "+tt+":");
				if(coverList==null) //No score at altitude 0
					continue;
				for(int Ncible : coverList)
				{
					// For all cible covered by pos
					if(optB.coveredT[Ncible][tt]==0 )
					{
						hash += (long)(Ncible+1)*(long)(Ncible+1)*(long)(Ncible+1)*(long)(Ncible+1);
					}
//					System.out.print(" "+Ncible);
				}
//				System.out.println(" seen for hash");
				
			hash = hash*(long)1000003+(long)47;
			tt++;
		}
		return (int)hash;
		
		
		}else
		{
		
		tempcounter++;
		return tempcounter;
		}
	}


	
	// HashCode is sufficiently discriminating for this problem. It is not a problem if we drop one partialpath from time to time
	// !!WARNINGN !! Not Generic, to adapt if code reused!!!
	public boolean equals(Object o)
	{
		PartialPath oo = (PartialPath)o;
		return (this.hashCode() == oo.hashCode());
			
	}


	// Greater if score is lower
	@Override
	public int compareTo(PartialPath o) {
		int myScore = this.getScore();
		int oScore  = o.getScore();
		if(oScore>myScore)
		{
			return 1;
		}
		if(oScore<myScore)
		{
			return -1;
		}
		
		
		return Integer.compare( this.hashCode(),o.hashCode());
	}


	int getScore() {
		int score = 0;
		
		for(int  ii = 0;ii<posList.size();ii++)
		{
			int tt = Tstart+ii;
			int posN = posList.get(ii);
			if(Common.DEBUG ==1)
			{
				System.out.print("at T:"+tt+"scored : ");
			}
			Pos p = optB.mappedPos[posN];
			List<Integer> coverList = optB.posXY[p.x+pb.R*p.y].coverList;
			
				if(coverList==null) //No score at altitude 0
					continue;
				if(p.z > 0)
				{
					for(int Ncible : coverList)
					{
						if(Common.DEBUG==1)
							System.out.print("c:"+Ncible+"|"+optB.coveredT[Ncible][tt]+" ");
						score += Integer.max(0, 10000 - 9999*optB.coveredT[Ncible][tt]);//New cible reached // Equation to tune
					}
				}
				if(Common.DEBUG==1)
					Sys.pln("");
				
		}
		return score;
		
		

	}
	
	@Override
	public String toString() {
		
		String resp = "Tstart "+this.Tstart +" Pos :" ;
		for(int pN :posList)
		{
			Pos p = optB.mappedPos[pN];
			resp = resp+" "+p;
		}
		return resp;
		
	}

}
