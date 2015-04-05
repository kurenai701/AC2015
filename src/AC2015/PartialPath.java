package AC2015;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;




// Stores a partial path. Returns the comparison of the score of each path
public class PartialPath implements Comparable<PartialPath>{
	public LinkedList<Integer> posList = new LinkedList<Integer>();
	public OptimizeBallon optB;
	public Problem pb;
	public int Tstart;
	
	
	public PartialPath(int start, OptimizeBallon optB,Problem pb, int Tstart) {
		super();
		this.optB = optB;
		posList.add(start);
		this.pb=pb;
		this.Tstart = Tstart;
	}


	// Hash function to implements efficient HashSet
	@Override
	public int hashCode() {
		long hash = 10;
		int tt = Tstart;
		for(int posN :posList)
		{
			
			Pos p = optB.mappedPos[posN];
			List<Integer> coverList = optB.posXY[p.x+pb.R*p.y].coverList;
			
				if(coverList==null) //No score at altitude 0
					continue;
				for(int Ncible : coverList)
				{
					// For all cible covered by pos
					if(optB.coveredT[Ncible][tt]==0 )
					{
						hash += (long)(Ncible)*(long)(Ncible)*(long)(Ncible)*(long)(Ncible);
					}
				}
			hash = hash*(long)1000003+(long)47;
			tt++;
		}
		return (int)hash;
		
	}


	
	// HashCode is sufficiently discriminating for this problem. It is not a problem if we drop one partialpath from time to time
	// !!WARNINGN !! Not Generic, to adapt if code reused!!!
	public boolean equals(Object o)
	{
		PartialPath oo = (PartialPath)o;
		return (this.hashCode() == oo.hashCode());
			
	}


	@Override
	public int compareTo(PartialPath o) {
		int myScore = this.getScore();
		int oScore  = o.getScore();
		return Integer.compare(myScore, oScore);
	}


	int getScore() {
		int score = 0;
		int tt = Tstart;
		for(int posN :posList)
		{
			
			Pos p = optB.mappedPos[posN];
			List<Integer> coverList = optB.posXY[p.x+pb.R*p.y].coverList;
			
				if(coverList==null) //No score at altitude 0
					continue;
				if(p.z > 0)
				{
					for(int Ncible : coverList)
					{
						score += Integer.max(0, 1000 - optB.PARAMAVOID*optB.coveredT[Ncible][tt]);//New cible reached // Equation to tune

					}
				}
				tt++;
		}
		return score;
		
		

	}
	
	

}
