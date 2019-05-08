package DataLayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import BusinessLayer.MenuItem;
import BusinessLayer.Restaurant;
import BusinessLayer.RestaurantProcessing;

public class RestaurantSerializator {
	
	public static void serialize(ArrayList<MenuItem> menu) {
		Restaurant rest=new Restaurant(menu);
		String filename="restaurantdata.txt";
		
		try {
			FileOutputStream file=new FileOutputStream(filename);
			ObjectOutputStream out=new ObjectOutputStream(file);
			
			out.writeObject(rest);
			
			out.close();
			file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void deserialize()
	{
		String filename="restaurantdata.txt";
		Restaurant rest=null;
		
		try {
			FileInputStream file=new FileInputStream(filename);
			ObjectInputStream in=new ObjectInputStream(file);
			
			rest=(Restaurant)in.readObject();
			System.out.println(rest.restmenu);
			RestaurantProcessing.menu.clear();
			RestaurantProcessing.menu.addAll(rest.restmenu);
			
			in.close();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
