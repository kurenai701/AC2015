package AC2015;

import java.io.Serializable;

public class Move implements Serializable{
	public Pos nextPos;
	public int cost;
	
	public Move(Pos nextPos, int cost) {
		super();
		this.nextPos = nextPos;
		this.cost = cost;
	}
	
	
	
}
