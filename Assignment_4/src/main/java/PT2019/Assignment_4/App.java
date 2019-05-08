package PT2019.Assignment_4;

import java.util.ArrayList;

import BusinessLayer.BaseProduct;
import BusinessLayer.CompositeProduct;
import BusinessLayer.MenuItem;
import BusinessLayer.Order;
import BusinessLayer.RestaurantProcessing;
import DataLayer.RestaurantSerializator;
import PresentationLayer.Controller;
import PresentationLayer.View;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
		MenuItem cartofi = new BaseProduct("cartofi", 6);
		MenuItem ceafa = new BaseProduct("ceafa", 12);
		CompositeProduct ceafaCuCartofi = new CompositeProduct("ceafa cu cartofi");
		MenuItem ceafaCartofi = ceafaCuCartofi;
		ceafaCuCartofi.add(ceafa);
		ceafaCuCartofi.add(cartofi);
		MenuItem ciorba = new BaseProduct("ciorba de vacuta", 10);
		MenuItem smantana = new BaseProduct("smantana", 3);
		MenuItem ardeu = new BaseProduct("ardei iute", 3);
		CompositeProduct ciorbaFull = new CompositeProduct("ciorba cu smantana si ardei");
		ciorbaFull.add(ciorba);
		ciorbaFull.add(smantana);
		ciorbaFull.add(ardeu);
		menu.add(ceafaCuCartofi);
		menu.add(ciorba);
		menu.add(smantana);
		menu.add(ardeu);
		menu.add(ciorbaFull);
		
		ArrayList<MenuItem> comanda = new ArrayList<MenuItem>();
		comanda.add(ceafaCuCartofi);
		comanda.add(ciorba);
		//Restaurant rest=new Restaurant(menu);
		Order or=new Order(1,1);
		//rest.createOrder(or, comanda);
		//rest.generateBill(or);
         RestaurantProcessing.comenzi.put(or, comanda);
    	RestaurantSerializator.serialize(menu);
    	
        RestaurantSerializator.deserialize();
        View myView=new View();
	    Controller myContr=new Controller(myView);
        
    }
}
