package AC2016;

import java.util.*;

public class Order {

	
	public Pos CustomerPos = new Pos();
	public List<Integer> ProdListToDeliver = new ArrayList<Integer>();
	
	public int OrderId;
	
	public int TotalNumItems;
	public int OrderScore = 0;
	public int T;
	
	public Warehouse closestWarehouse;
		
	public Order(){}
	
	public Order(int id, Pos targetPos, int totalItems, int T)
	{
		this.OrderId = id;
		CustomerPos = targetPos;
		TotalNumItems = totalItems;
		this.T = T;
	}
	
	
	public void RemoveProdIdFromDeliveryList(int i, int currentTurn)
	{
		ProdListToDeliver.remove(i);
			
		if (ProdListToDeliver.size() == 0)
			OrderScore = CalculateOrderScore(currentTurn);
	}
	
	
	public int CalculateOrderScore(int currentTurn)
	{
		return (int)Math.ceil(((double)(T - currentTurn) / (double)T) * 100);		
	}
	
}
