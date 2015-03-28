package AC2015;

import java.io.Serializable;
import java.util.ArrayList;

public class Pos implements Serializable{

	
	int x;
	int y; 
	int z;
	ArrayList<Move> moves;
	
	int minDistance;// Used only for dijkstra
	
	

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
