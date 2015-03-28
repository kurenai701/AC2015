package AC2015;

import java.io.Serializable;

public class Move implements Serializable{
	Pos nextPos;
	int cost;
	
	public Move(Pos nextPos, int cost) {
		super();
		this.nextPos = nextPos;
		this.cost = cost;
	}
	
	
	
}
