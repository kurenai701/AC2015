package AC2016;

import java.util.*;


public class RandomOptim {
	
	/*
	 * 1 
	 * 2 Cr�er une liste d'instruction : Load A pos1 , d�pose A � client Y  avec tous les couples possibles
	 * 3 Une liste est valide si : toujours un load de typ A avant livraison de type A  
	 * 4 Alloue une liste par drone
	 * 
	 * 
	 * Avant scoring : concat�nation des load de m�me produit
	 * Score rapide : temps final
	 * Score exacte : simulation du syst�me
	 * 
	 * 
	 * 
	 * 
	 * Recherche local : 
	 * a) Passe un produit d'un drone � un autre => passe le load et le unload.
	 * b) �change des op�rations d'un drone.
	 * 
	 */
	public void opti()
	{
	Problem pb = new Problem();
	
	List<Route> RouteList = new ArrayList<Route>();
	

	// Greedy Algorithms, allocate products to customers on a closest basis
	
	for(Order myorder:  pb.WorldSim.OrdersList) // TODO : trier par facilit�
	{
		for( int prod :myorder.ProdListToDeliver   )
		{
				// Find an available product // By default, use closest
			List<Product> candidateProductList = new ArrayList<>();
			Product closestProd = null;
			int minDist = Integer.MAX_VALUE;
				foreach(Product curProd : ProductOfType(prod)  ))
				{
					if(curProd.available)
					{
						candidateProductList.add(curProd);
						int curdist = curProd.warehouse.Position.distToPos(myorder.CustomerPos);
						if(  curdist < minDist  )
						{
							minDist = curdist;
							closestProd = curProd;
						}
						
					}
				}
				
				
				// Reserve product 

				// Create route
				RouteList.add( new Route( closestProd, myorder   )  ); //Dans Route : 
				closestProd.available = false;
				// Allocate specific order myorder.
		
		}
	}
	
	

	/* Optimize Routes
	 * 
	 *  Choose a Route, exchange the product in the Route with another product of same type, eventually from another Route
	 *  Score : Somme des distances des routes + Nombre d'entrepots utilis� pour chaque commande
	 *  
	 *  
	 *  
	 */
	
	/*
			int curscore = Integer.MAX_VALUE;
			int NOPTS = 30;
			
			
			
			for(int t = 0;t<NOPTS;t++)
			{
				Exchange(  )
				
				
			}
		*/	
	
	
	// Now, needs to allocate Routes to Drones, then optimize commands of drones
	
		int curDrone = 0;
		List<Drone > droneList = new ArrayList<>();
		
		for( Route r : RouteList )
		{
			// For the moment, allocate one by one
			droneList.get(curDrone).RouteList.add(r);
			curDrone = (curDrone+1)%pb.D;
			
		}
		
		
		
		
		
		for(int treslongtemps = 0; treslongtemps <1;)
		{
			 // Randomly Modify allocation of product to order
			
			
			// Randomly Modify allocation of Route to Drone
	
			
			
				// Optimize the flight route of drone
			foreach(Drone d :droneList)
			{
				OptimizeFlyOrder(d);
			}
		
		
		}
		
		
		
	
	
	
	}
	
	public void OptimizeFlyOrder(Drone drone)
	{
		// Parcourt les ordres, et regroupe les ordres � la suite pour le m^me produit� la m�me warehouse
		
		
		// Parcourt les ordres, et regroupe les ordres 
		
		
		
	}
		

}
