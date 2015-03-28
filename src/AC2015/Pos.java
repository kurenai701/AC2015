package AC2015;

import java.io.Serializable;
import java.util.ArrayList;

public class Pos implements Serializable{
	public static Pos OUTOFMAP = new Pos(0,0,0);
	
	public int x;
	public int y; 
	public int z;
	public int num;
	public int numInPosInterest;
	
	public ArrayList<Move> moves = new ArrayList<Move>();
	
	
	public ArrayList<Integer> coverList = new ArrayList<Integer>();//list of indices of the cible covered
	
	public int minDistance;// Used only for dijkstra
	public Pos previous;// Used only for dijkstra
	

	public Pos()
	{		
	}
		
	public Pos(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Num " + num + " R " + x +" C " + y+" A " + z;
	}
	
	
}
