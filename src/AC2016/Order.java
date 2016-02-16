package AC2016;

import java.util.*;

public class Order {

	
	public Pos CustomerPos = new Pos();
	public List<Integer> ProdListToDeliver = new ArrayList<Integer>();
	
	public int totalNumItems;
	public int orderScore = 0;
	public int T;
	
	public Warehouse closestWarehouse;
	
	
	public Order(){}
	
	public Order(Pos targetPos, int totalItems, int T)
	{
		CustomerPos = targetPos;
		totalNumItems = totalItems;
		this.T = T;
	}
	
	
	public void RemoveProdIdFromDeliveryList(int i, int currentTurn)
	{
		ProdListToDeliver.remove(i);
			
		if (ProdListToDeliver.size() == 0)
			orderScore = CalculateOrderScore(currentTurn);
	}
	
	
	public int CalculateOrderScore(int currentTurn)
	{
		return (int)Math.ceil(((double)(T - currentTurn) / (double)T) * 100);		
	}
	
}
