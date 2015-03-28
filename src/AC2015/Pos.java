package AC2015;

import java.io.Serializable;
import java.util.ArrayList;

public class Pos implements Serializable{

	
	int x;
	int y; 
	int z;
	ArrayList<Move> moves;
	ArrayList<Integer> coverList;//list of indices of the cible covered
	
	int minDistance;// Used only for dijkstra
	Pos previous;// Used only for dijkstra
	

	public Pos()
	{		
	}
		
	public Pos(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	
	
}
