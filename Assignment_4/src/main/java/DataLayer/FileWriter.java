package DataLayer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import BusinessLayer.MenuItem;
import BusinessLayer.Order;

public class FileWriter {
	
	public static void write(Order or,ArrayList<MenuItem> list,int totalPrice) {
		String filename=or.getOrderID()+".txt";
		PrintWriter writer=null;
		try {
			writer=new PrintWriter(filename,"UTF-8");
			
		
			writer.print(list.toString()+"\n Comanda din data de: "+or.getCurrentDate()+ "\n Masa:"+or.getTable()+"\n Total de plata:"+totalPrice+"" );
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}finally {
			writer.close();
		}
	}
}
