package AC2016;

import java.lang.Math;
import java.util.*;

public class Pos {

	
	public int X;
	public int Y;	
	
	public char PosFilled; 

	public Warehouse WarehouseAtPos;	
	public List<Order> OrdersAtPos = new ArrayList<Order>();
	
	public Pos(){}
	
	public Pos(int x, int y, char posFilled)
	{
		this.X = x;
		this.Y = y;
		this.PosFilled = posFilled;
	}
	
	
	public int distToPos(Pos p2)
	{
		return (int)Math.ceil(  
				Math.sqrt(  Math.pow((p2.X - this.X),2.0) + Math.pow((p2.Y - this.Y),2.0))
				);		
	}
	
	public boolean isWarehouse()
	{
		return PosFilled == 'W';
	}
	
	public boolean isCustomer()
	{
		return PosFilled == 'C';
	}
	
	
}
