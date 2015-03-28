package AC2015;

import java.util.ArrayList;

public class Ballon {
	int Num;
	ArrayList<Integer> aChanges= new ArrayList<Integer>();// Contient -1, 0, ou 1 pour mouvements réels (-1,0,1);

	ArrayList<Pos> posList= new ArrayList<Pos>();;
	
	
	
	public Ballon(int num) {
		super();
		Num = num;

	}
	
	
	public void addMove(int mymove)
	{
		aChanges.add(mymove);
		Pos curPos = posList.get(posList.size()-1);
		
		boolean valid = false;
		for(Move mov : curPos.moves)
		{
			if( mov.aChange == mymove)
			{
				posList.add( mov.nextPos   );
				valid = true;
			}
		}
		if(!valid)
		{
			assert(false);
		}
		Sys.pln("baloon : " + Num + " moved to " + posList.get(posList.size()-1));
			
		
	}
	
	
	

}
