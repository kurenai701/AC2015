package AC2016;

import java.util.*;
import java.util.function.Predicate;

public class Drone {

	
	public int Id;	
	public int MaxPayload;
	
	public Pos CurrentPos;
		
	public int ETATargetPos;
	public Pos TargetPos;
	
	public List<Product> Inventory = new ArrayList<Product>();
	public int CurrentPayload;
	
	public String CurrentInstruction;
	
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
	
	public void addProductToDrone(Product p, int qtyitem)
	{
		for (int i = 1; i <= qtyitem; i++)
		{
			this.Inventory.add(p);
		}
	}
	
	public void removeProductFromDrone(Product p, int qtyitem)
	{		
		
		if (verifyDropPossible(p, qtyitem))
		{
			for (int i = 1; i <= qtyitem; i++)
			{		
				this.Inventory.remove(p);	
			}
		}
	
	}
	
	public boolean canLoad(Product prod, int qtyitem, Warehouse wh)
	{
		return verifyLoadDronePossible(prod, qtyitem) && wh.hasEnoughProductAvailable(prod, qtyitem);
	}
	
	public void loadInstruction(Product prod, int qtyitem, Warehouse wh, int currentTurn)
	{	
		int ETATurn = flyToPos(wh.Position, currentTurn);		
		// TODO quelque chose pour attendre puis Load.
	}

	
	public void actuaLoad(Product prod, int qtyitem, Warehouse wh)
	{
		if (wh.hasEnoughProductAvailable(prod, qtyitem))
		{
			wh.removeProductFromStock(prod, qtyitem);
			addProductToDrone(prod, qtyitem);
		}			
	}

	
	public boolean verifyLoadDronePossible(Product p, int qtyitem)
	{
		int available = this.MaxPayload - this.CurrentPayload;
		return ((p.Weight * qtyitem) <= available);	
	}
	
	public boolean verifyDropPossible(Product p, int qtyitem)
	{
		Predicate<Product> predicateCountType = (pr -> pr.TypeId == p.TypeId);
		return Common.Count(this.Inventory, predicateCountType) >= qtyitem;
	}
	

	
	
}
