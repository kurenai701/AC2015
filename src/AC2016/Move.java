package AC2016;

import java.io.Serializable;




public class Move implements Serializable{
	
	public static Move INVALID = new Move( Pos.OUTOFMAP, 1, 20  );

	
	public Pos nextPos;
	public int cost;
	public int aChange;
	
	public Move(Pos nextPos, int cost, int aChange) {
		super();
		this.nextPos = nextPos;
		this.cost = cost;
		this.aChange = aChange;
	}
	
	
	
}
