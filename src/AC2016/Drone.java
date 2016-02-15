package AC2016;

import java.util.*;

public class Drone {

	
	public int Id;	
	public int MaxPayload;
	
	
	public Pos CurrentPos;
	public int ETATargetPos;
	public Pos TargetPos;
	
	public List<Product> Inventory;
	public int CurrentPayload;
	
	public char CurrentInstruction;
	
	public Drone(){}
	
	public Drone(int id, int maxPayload)
	{
		this.Id = id;
		this.MaxPayload = maxPayload; 
	}
	

	public int flyToPos(Pos targetP, int currentTurn)
	{
		TargetPos = targetP;
		int numTurnUntilPos = CurrentPos.distToPos(TargetPos);
		ETATargetPos = currentTurn + numTurnUntilPos;
		return ETATargetPos;			
	}
	
	public void addProductToDrone(Product p, int numitem)
	{
		for (int i = 1; i <= numitem; i++)
		{
			this.Inventory.add(p);
		}
	}
	
	public void removeProductFromDrone(Product p, int numitem)
	{		
		this.Inventory.remove(p);	
	}
	
	public boolean canLoad(Product prod, int qtyitem, Warehouse wh)
	{
		return verifyLoadDronePossible(prod, qtyitem) && wh.hasEnoughProductAvailable(prod, qtyitem);
	}
	
	public void load(Product prod, int qtyitem, Warehouse wh, int currentTurn)
	{	
		if (canLoad(prod, qtyitem, wh))
		{
			int a = flyToPos(wh.Position, currentTurn);			
			if (wh.hasEnoughProductAvailable(prod, qtyitem))
			{
				wh.removeProductFromStock(prod, qtyitem);
			}			
		}
	}

	
	public boolean verifyLoadDronePossible(Product p, int qtyitem)
	{
		int available = this.MaxPayload - this.CurrentPayload;
		return ((p.Weight * qtyitem) <= available);	
	}
	

	
	
}
