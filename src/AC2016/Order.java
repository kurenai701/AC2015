package AC2016;

import java.util.*;

public class Order {

	
	public Pos CustomerPos = new Pos();
	public List<Integer> ProdListToDeliver = new ArrayList<Integer>();
	
	public int totalNumItems;
	
	public Order(){}
	
	public Order(Pos targetPos, int totalItems)
	{
		CustomerPos = targetPos;
		totalNumItems = totalItems;
	}
	
	
}
