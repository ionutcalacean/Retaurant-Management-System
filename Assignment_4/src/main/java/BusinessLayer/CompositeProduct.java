package BusinessLayer;

import java.util.ArrayList;

public class CompositeProduct extends MenuItem {
	
	private String name;
	private int price;
	private ArrayList<MenuItem> dish;
	
    public CompositeProduct(String name)
    {
    	super(name);
    	this.name=name;
    	dish=new ArrayList<MenuItem>();
    	
    	
    }
    
    public int computePrice()
    {
    	int price=0;
    	for(MenuItem c:dish)
    	{
    		price+=c.computePrice();
    	}
    	return price;
    }
    
    public void add(MenuItem m)
    {
    	dish.add(m);
    	this.price=computePrice();
    }
    public void remove(MenuItem m)
    {
    	dish.remove(m);
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
		return "CompositeProduct [name=" + super.getName() + " price="+computePrice()+"]\n";
	}
    
    
}
