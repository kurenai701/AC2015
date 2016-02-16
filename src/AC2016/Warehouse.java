package AC2016;

public class Warehouse {

	
	public Pos Position;
	public int Id;	

	public int[] prodStockArray; // array pour les stocks des produits.
	
	
	public Warehouse(){}
	
	public Warehouse(int id, Pos pos, int numProdType)
	{
		this.Id = id;
		this.Position = pos;	
		Position.PosFilled = 'W';
		
		this.prodStockArray = new int[numProdType];
	}
	
	public boolean isProductAvailable(Product prod)
	{
		return hasEnoughProductAvailable(prod, 1);		
	}
	
	public boolean isProductAvailable(int prodtypeid)
	{
		return hasEnoughProductAvailable(prodtypeid, 1);		
	}
	
	public boolean hasEnoughProductAvailable(Product prod, int qty)
	{
		return countProductStock(prod) >= qty;		
	}
	

	public boolean hasEnoughProductAvailable(int prodtypeid, int qty)
	{
		return prodStockArray[prodtypeid] >= qty;			
	}
	
	public int countProductStock(Product prod)
	{
		return prodStockArray[prod.TypeId];
	}
	
	public void addProductToStock(Product prod, int qty)
	{	
		prodStockArray[prod.TypeId] += qty; 
	}
	
	public void removeProductFromStock(Product prod, int qty)
	{
		prodStockArray[prod.TypeId] -= qty;
	}
	
	
	
}
