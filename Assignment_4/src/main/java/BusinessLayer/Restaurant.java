package BusinessLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;

import javax.swing.JOptionPane;

import DataLayer.FileWriter;

public class Restaurant extends Observable implements RestaurantProcessing, Serializable {

	public static ArrayList<MenuItem> restmenu = new ArrayList<MenuItem>();

	public Restaurant(ArrayList<MenuItem> menu) {
		
        restmenu.clear();
	    restmenu.addAll(menu);
		RestaurantProcessing.menu.clear();
		RestaurantProcessing.menu.addAll(menu);
	}

	public void createMenuItem(MenuItem mi) {
		assert mi != null;
		ArrayList<MenuItem> preList = menu;
		assert isWellFormed();

		int pre = menu.size();
		menu.add(mi);
		assert menu.size() == pre + 1;
		assert mi == menu.get(menu.size() - 1);
		for (int i = 0; i < menu.size() - 2; i++)
			assert preList.get(i) == menu.get(i);
		assert isWellFormed();
	}

	public void deleteMenuItem(MenuItem mi) {
		assert menu.size() > 0;
		ArrayList<MenuItem> preList = new ArrayList<MenuItem>();
		preList.addAll(menu);
		assert isWellFormed();

		menu.remove(mi);
		assert menu.size() == preList.size() - 1;
		assert isWellFormed();
	}

	public void editMenuItem(MenuItem mi) {
		assert menu.size() > 0;
		ArrayList<MenuItem> preList = new ArrayList<MenuItem>();
		preList.addAll(menu);
		assert isWellFormed();

		int index = menu.indexOf(mi);
		MenuItem toBeEdited = menu.get(index);
		String name = JOptionPane.showInputDialog(null, "Introduceti noul nume pentru produsul editat:");
		int price = Integer
				.parseInt(JOptionPane.showInputDialog(null, "Introduceti noul pret pentru produsul editat:"));
		toBeEdited.setName(name);
		if (toBeEdited instanceof BaseProduct)
			((BaseProduct) toBeEdited).setPrice(price);
		else if (toBeEdited instanceof CompositeProduct)
			((CompositeProduct) toBeEdited).setPrice(price);

		assert menu.size() == preList.size();
		assert isWellFormed();
	}

	public void createOrder(Order or, ArrayList<MenuItem> comandaActuala) {
		assert or != null && comandaActuala.size() > 0;
		Map<Order, ArrayList<MenuItem>> preMap = new HashMap<Order, ArrayList<MenuItem>>();
		preMap.putAll(comenzi);
		assert isWellFormedHash();

		comenzi.put(or, comandaActuala);
		assert comenzi.size() == preMap.size() + 1;
		assert comenzi.get(or) == comandaActuala;
		assert isWellFormedHash();
	}

	public int computePrice(Order or) {
		assert or != null && comenzi.get(or) != null;
		Map<Order, ArrayList<MenuItem>> preMap = new HashMap<Order, ArrayList<MenuItem>>();
		preMap.putAll(comenzi);
		assert isWellFormedHash();

		int totalPrice = 0;
		ArrayList<MenuItem> comandaActuala = comenzi.get(or);
		for (MenuItem mi : comandaActuala) {
			totalPrice += mi.computePrice();
		}

		assert preMap.equals(comenzi);
		assert isWellFormedHash();
		return totalPrice;

	}

	public void generateBill(Order or) {
		assert or != null && comenzi.get(or) != null;
		Map<Order, ArrayList<MenuItem>> preMap = new HashMap<Order, ArrayList<MenuItem>>();
		preMap.putAll(comenzi);
		assert isWellFormedHash();

		int totalPrice = computePrice(or);
		FileWriter.write(or, comenzi.get(or), totalPrice);

		assert preMap.equals(comenzi);
		assert isWellFormedHash();
	}

	public boolean isWellFormed() {
		Iterator<MenuItem> it = menu.iterator();
		int n = 0;
		while (it.hasNext()) {
			if (it.next() == menu.get(n)
					&& (menu.get(n) instanceof BaseProduct || menu.get(n) instanceof CompositeProduct))
				n++;

		}
		return n == menu.size();
	}

	public boolean isWellFormedHash() {
		Iterator it = comenzi.entrySet().iterator();
		int n = 0;
		while (it.hasNext()) {
			Map.Entry<Order, ArrayList<MenuItem>> pair = (Map.Entry<Order, ArrayList<MenuItem>>) it.next();
			if (pair.getValue() == comenzi.get(pair.getKey()))
				n++;
		}
		return n == comenzi.size();

	}

	public Map<Order, ArrayList<MenuItem>> getComenzi() {
		return comenzi;
	}

	public void setComenzi(Map<Order, ArrayList<MenuItem>> comenzi) {
		this.comenzi.putAll(comenzi);
	}

	public ArrayList<MenuItem> getMenu() {
		return menu;
	}

	public void setMenu(ArrayList<MenuItem> menu) {
		menu = menu;
	}

}
