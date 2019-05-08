package BusinessLayer;

public class BaseProduct extends MenuItem{
    private String name;
	private int price;
	public BaseProduct(String name,int price)
	{
		super(name);
		this.name=name;
	  this.price=price;
	}
	
	public int computePrice()
	{
		return price;
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "BaseProduct [name="+super.getName()+" price=" + price + "]\n";
	}
	

}
