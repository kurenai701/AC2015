package AC2015;

import java.io.Serializable;
import java.util.ArrayList;

import javax.management.RuntimeErrorException;

public class Ballon implements Serializable {
	int Num;
	ArrayList<Integer> aChanges= new ArrayList<Integer>();// Contient -1, 0, ou 1 pour mouvements réels (-1,0,1);

	ArrayList<Pos> posList= new ArrayList<Pos>();;
	
	

	private static final long serialVersionUID = -4761854948924066721L;
	
	
	public Ballon(int num,Pos startPos) {
		super();
		Num = num;
		posList.add(startPos);

	}
	
	
	public Pos getLastPos()
	{
		return posList.get(posList.size()-1);
	}
	
	
	public void addMove(int mymove, Problem pb)
	{
		//aChanges.add(mymove);
		Pos curPos = posList.get(posList.size()-1);
		
		boolean valid = false;
		for(Move mov : curPos.moves)
		{
			if( mov.aChange == mymove)
			{
				posList.add( pb.AllPosMat[   mov.nextPos.x][   mov.nextPos.y][   mov.nextPos.z]   );
				aChanges.add( mymove);
				
				valid = true;
			}
		}
		if(!valid)
		{
			if(!(curPos.x == 0 && curPos.y==0))
			{
				Sys.pln("Not valid move, Killing baloon" + Num);
			}
			posList.add( Move.INVALID.nextPos   );
			aChanges.add( Move.INVALID.aChange);
		}
		//Sys.pln("baloon : " + Num + " moved from " +posList.get(posList.size()-2)+ "to " + posList.get(posList.size()-1));
			
		
	}
	

	public void addMultipleMove(int mymove, int my2ndmove,  int my3rdmove, int my4thmove, Problem pb)
	{
		if (mymove == 0 || mymove == -1 || mymove ==1) 
		addMove(mymove, pb);
		
		if (my2ndmove == 0 || my2ndmove == -1 || my2ndmove ==1) 
			addMove(my2ndmove, pb);
			
		if (my3rdmove == 0 || my3rdmove == -1 || my3rdmove ==1) 
			addMove(my3rdmove, pb);
			
		if (my4thmove == 0 || my4thmove == -1 || my4thmove ==1) 
			addMove(my4thmove, pb);
			
	}


	public void updatePartialPath(int startT, PartialPath pA, Problem pb, OptimizeBallon optB) {
		
		for(int ii = 0; ii< pA.posList.size()-1;ii++)
		{
			int curT = startT+ii;
			//*** curpos
			posList.remove(curT);
			Pos curPos = optB.mappedPos[pA.posList.get(ii)];
			posList.add(curT,curPos);
			
			//*** changes
			Pos nextPos = optB.mappedPos[pA.posList.get(ii+1)];
			if(curT<aChanges.size())
				aChanges.remove(curT);
			boolean updated = false;
			for(Move mov : curPos.moves)
			{
				if( mov.nextPos == nextPos)
				{
					aChanges.add( curT,mov.aChange);
					updated = true;
				}
			}
			if(!updated)
			{
			//	Sys.pln("ERROR, no path found");
				aChanges.add( curT,0);
				//throw(new RuntimeException());
			}
		}

	}
	
	@Override
	public String toString() {
		
		String resp = "Ballon #"+this.Num +" Pos :" ;
		for(Pos p :posList)
		{
			resp = resp+" "+p;
		}
		return resp;
	}
	
	
}
