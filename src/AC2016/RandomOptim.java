package AC2016;

import java.util.ArrayList;
import java.util.List;

public class RandomOptim {
	
	/*
	 * 1 
	 * 2 Créer une liste d'instruction : Load A pos1 , dépose A à client Y  avec tous les couples possibles
	 * 3 Une liste est valide si : toujours un load de typ A avant livraison de type A  
	 * 4 Alloue une liste par drone
	 * 
	 * 
	 * Avant scoring : concaténation des load de même produit
	 * Score rapide : temps final
	 * Score exacte : simulation du système
	 * 
	 * 
	 * 
	 * 
	 * Recherche local : 
	 * a) Passe un produit d'un drone à un autre => passe le load et le unload.
	 * b) échange des opérations d'un drone.
	 * 
	 */
	pb = new Problem();
	
	List<Route> RouteList = new ArrayList<>();
	

	// Greedy Algorithms, allocate products to customers on a closest basis
	
	for(Order myorder:  pb.WorldSim.OrdersList) // TODO : trier par facilité
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
				RouteList.add( new Route( closestProd, Warehouse,myorder.customer   )  ); //Dans Route : closestProd.available = false;
		
		
		}
	}
	
	

	/* Optimize Routes
	 * 
	 *  Choose a Route, exchange the product in the Route with another product of same type, eventually from another Route
	 *  Score : Somme des distances des routes + Nombre d'entrepots utilisé pour chaque commande
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
	List<Drone > droneList = new ArrayList<>;
	
	for( Route r : RouteList )
	{
		droneList.get(curdrone).RouteList.add(r);
		
		curdrone = (curdrone+1)%pb.D;
		
	}
	
	
	
	
		

}
