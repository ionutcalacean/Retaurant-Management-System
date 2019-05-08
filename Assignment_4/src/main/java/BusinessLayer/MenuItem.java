package BusinessLayer;

import java.io.Serializable;

public class MenuItem implements Serializable{
	private String name;

	
	public MenuItem(String name)
	{
		this.name=name;
	
	}
	
	public int computePrice()
	{
		return 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "MenuItem [name=" + name + "]";
	}

   
     
	
}
