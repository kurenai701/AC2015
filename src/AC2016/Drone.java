package AC2016;

import java.util.*;
import java.util.function.Predicate;

import javax.activity.InvalidActivityException;

public class Drone {

	
	public int Id;	
	public int MaxPayload;
	
	public Pos CurrentPos;
		
	public int ETATarget;
	public Pos TargetPos;
	public boolean IsFlying;
	
	public List<Product> Inventory = new ArrayList<Product>();
	public int CurrentPayload;
	
	public String InstructionInProcess;
	public Product ProductInProcess;
	public int QtyInProcess;
	
	public Order OrderInProcess;
	
	public Drone(){}
	
	public Drone(int id, int maxPayload)
	{
		this.Id = id;
		this.MaxPayload = maxPayload; 
	}
	

	public boolean isAvailableForNewInstruction()
	{
			return (this.ETATarget == -1);
	}	

	public void loadInstruction(Product prod, int qtyitem, Warehouse wh, int currentTurn)
	{	
		InstructionInProcess = "L";
		ProductInProcess = prod;
		QtyInProcess = qtyitem;
		
		int ETATurn = flyToPos(wh.Position, currentTurn);			
	}
	
	public void unloadInstruction(Product prod, int qtyitem, Warehouse wh, int currentTurn)
	{	
		InstructionInProcess = "U";
		ProductInProcess = prod;
		QtyInProcess = qtyitem;
		
		int ETATurn = flyToPos(wh.Position, currentTurn);			
		// TODO, voir s'il y a autre chose à faire
	}
	
	
	public void deliverInstruction(Product prod, int qtyitem, Pos pos, int currentTurn)
	{	
		InstructionInProcess = "D";
		ProductInProcess = prod;
		QtyInProcess = qtyitem;
		
		int ETATurn = flyToPos(pos, currentTurn);			
		// TODO, voir s'il y a autre chose à faire
	}
	
	
	
	// helper function

	private int flyToPos(Pos targetP, int currentTurn)
	{
		TargetPos = targetP;
		int numTurnUntilPos = CurrentPos.distToPos(TargetPos);
		ETATarget = currentTurn + numTurnUntilPos;
		IsFlying = true;
		return ETATarget;			
	}
	
	
	private void addProductToDrone(Product p, int qtyitem)
	{
		for (int i = 1; i <= qtyitem; i++)
		{
			this.Inventory.add(p);
			this.CurrentPayload += p.Weight;
		}
	}
	
	private void removeProductFromDrone(Product p, int qtyitem)
	{		
		
		if (verifyDropPossible(p, qtyitem))
		{
			for (int i = 1; i <= qtyitem; i++)
			{		
				this.Inventory.remove(p);	
				this.CurrentPayload -= p.Weight;
			}
		}
	
	}
	
	public boolean canLoad(Product prod, int qtyitem, Warehouse wh)
	{
		return verifyLoadDronePossible(prod, qtyitem) && wh.hasEnoughProductAvailable(prod, qtyitem);
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
	
	// ACTUAL ACTIONS
	
	
	private void actualLoad(Product prod, int qtyitem, Warehouse wh)
	{
		if (canLoad(prod, qtyitem, wh))
		{
			wh.removeProductFromStock(prod, qtyitem);
			addProductToDrone(prod, qtyitem);
		}
	}
	
	private void actualUnload(Product p, int qtyitem, Warehouse wh)
	{
		if (verifyDropPossible(p, qtyitem))
		{
			removeProductFromDrone(p, qtyitem);
			wh.addProductToStock(p, qtyitem);
		}
		
	}
		
	private void actualDeliver(Product p, int qtyitem, Order ord, int currentTurn)
	{
		if (verifyDropPossible(p, qtyitem))
		{
			removeProductFromDrone(p, qtyitem);
			ord.RemoveProdIdFromDeliveryList(p.TypeId, currentTurn);
		}
	}
	
	
	
	/*Process qui composeront un "tour" pour le drone */
	
	public void processTurnArrivalToPosition(int currentTurn)
	{
		if (this.ETATarget == currentTurn)
		{
			this.CurrentPos = this.TargetPos;
			this.IsFlying = false;
			this.ETATarget++;		
		}
	}
	
	public void processTurnActualLoad(int currentTurn)
	{
		if (this.ETATarget == currentTurn && InstructionInProcess == "L");
		{
			if (this.CurrentPos.isWarehouse())
			{
				if(canLoad(ProductInProcess, QtyInProcess, this.CurrentPos.WarehouseAtPos))
				actualLoad(ProductInProcess, QtyInProcess, this.CurrentPos.WarehouseAtPos);
			}			
			this.ETATarget = -1;
		}
	}
	
	public void processTurnActualUnload(int currentTurn)
	{
		if (this.ETATarget == currentTurn && InstructionInProcess == "U");
		{
			if (this.CurrentPos.isWarehouse())
			{
				if(verifyDropPossible(ProductInProcess, QtyInProcess))
					actualUnload(ProductInProcess, QtyInProcess, this.CurrentPos.WarehouseAtPos);
			}			
			this.ETATarget = -1;
		}
	}
	

	public void processTurnActualDeliver(int currentTurn)
	{
		if (this.ETATarget == currentTurn && InstructionInProcess == "D");
		{
			if (this.CurrentPos.isWarehouse())
			{
				if(verifyDropPossible(ProductInProcess, QtyInProcess))
					actualDeliver(ProductInProcess, QtyInProcess, OrderInProcess,currentTurn);
			}			
			this.ETATarget = -1;
		}
	}
	
	

	
	
}
