package AC2016;

import java.util.*;
import java.util.function.*;

public class WorldSimulation {

	
	
	public int MaxTurn;	
	public int R;
	public int C;
	
	public int CurrentTurn;
	
	public Product[] ProductTypeArray;
		
	public List<Pos> WorldPositions = new ArrayList<Pos>(); // On ne va stocker que les positions intéressantes (?)
	public List<Order> OrdersList = new ArrayList<Order>();
	
	public Drone[] DroneArray; 
	
	public List<Warehouse> WarehouseList = new  ArrayList<Warehouse>();
	
	
	public WorldSimulation(){}
		
	public WorldSimulation(int R, int C, int D, int T, int DL)
	{
		this.R = R;
		this.C = C;
		this.MaxTurn = T;		
		this.DroneArray = new Drone[D];	
		
		for (int i = 0; i < D; i++)
		{
			DroneArray[i] = new Drone(i, DL);
		}
	
	}
	
	
	public void buildProductTypeArray(int P)
	{
		this.ProductTypeArray = new Product[P];
	}
	
	
	public List<Warehouse> listWarehousesWithProductType(int prodtypeid)
	{
		Predicate<Warehouse> predWarehouseHasProdType = wh -> wh.isProductAvailable(prodtypeid);
		return Common.Where(WarehouseList, predWarehouseHasProdType);
	}
	
	
	
	
	public void findAndSetClosestWarehouseForOrder(Order ord)
	{
		int currentMinDist = 999999999;
		
		for (Warehouse wh : WarehouseList)
		{
			int whdist = ord.CustomerPos.distToPos(wh.Position);
			if (whdist < currentMinDist)
			{
				ord.closestWarehouse = wh;
			}			
		}	
	}
	
	public void findAndSetClosestWarehouseForAllOrders()
	{
		for (Order ordr : this.OrdersList)
		{
			findAndSetClosestWarehouseForOrder(ordr);
		}	
	}
	
	

	public void ProcessTurn()
	{
		CurrentTurn++;
		
		// process all flights instruction for Current turn;
		for (int i = 0; i < DroneArray.length ; i++)
		{
			DroneArray[i].processTurnArrivalToPosition(CurrentTurn);;
		}
		
		// process all unload instructions for Current turn;
		for (int i = 0; i < DroneArray.length ; i++)
		{
			DroneArray[i].processTurnActualUnload(CurrentTurn);
			DroneArray[i].processTurnActualDeliver(CurrentTurn);
		}
		
		// process all load instructions for Current turn;
		for (int i = 0; i < DroneArray.length ; i++)
		{
			DroneArray[i].processTurnActualLoad(CurrentTurn);
		}
		
	}
	

	
	
	
	
}
