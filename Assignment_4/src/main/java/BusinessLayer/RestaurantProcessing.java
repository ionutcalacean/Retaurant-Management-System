package BusinessLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface RestaurantProcessing {
	public static Map<Order, ArrayList<MenuItem>> comenzi=new HashMap<Order,ArrayList<MenuItem>>();
	public static ArrayList<MenuItem> menu=new ArrayList<MenuItem>();
	
	/**
	 * Adds a new elemen at the end of the list
	 * @pre el != null
	 * @post getSize() == getSize()@pre + 1
	 * @post el@pre == getElement(getSize()-1)
	 * @post @forall k:[0...getSize()-2] @
	 *    (getElement(k)@pre == getElement(k))
	 *    @invariant isWellFormed()
	 */
	public void createMenuItem(MenuItem mi);
	/**
	 * Remove the specified element
	 * 
	 * @pre getSize()>0
	 * @post getSize()==getSize()@pre-1
	 * @invariant isWellFormed()
	 */
	public void deleteMenuItem(MenuItem mi);
	/**
	 * Edit the specified element
	 * @pre getSize()>0
	 * @post getSize()==getSize()@pre
	 * @invariant isWellFormed()
	 * 
	 */
	public void editMenuItem(MenuItem mi);
	/**
	 * Create an order with all menuItems in the list
	 * @pre or!= null
	 * @pre comandaActuala.getSize()>0
	 * @post getSize() == getSize()@pre + 1
	 * @post list.get(or)=comandaActuala
	 * @invariant isWellFormedHash()
	 */
	public void createOrder(Order or,ArrayList<MenuItem> comandaActuala);
	
	/**
	 * Compute price for all ordered products for a client
	 * @pre or!=null
	 * @pre list.get(or)!=null
	 * @post @result == sum == @forall k:0..list.get(or).size()
     *   (sum+=list.get(or).getElement(k)) 
	 * @post @nochange
	 * @invariant isWellFormedHash()
	 */
	public int computePrice(Order or);
	/**
	 * Generate a bill for client
	 * @pre or!=null
	 * @pre list.get(or)!=null
	 * @post @nochange
	 * @invariant isWellFormedHash()
	 */
	public void generateBill(Order or);
	
	

}
