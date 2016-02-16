package AC2016;

public class Product {

	public int TypeId;
	public int Weight;
	public int Id;
	public Warehouse warehouse;
	public boolean available;

	
	public Product(){}
	
	public Product(int typeId, int weight, int Id, Warehouse warehouse)
	{
		this.TypeId = typeId;
		this.Weight = weight;
		this.Id = Id;
		this.warehouse = warehouse;
		this.available = true;
	}
	


}




