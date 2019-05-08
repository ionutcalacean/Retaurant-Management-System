package PresentationLayer;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import BusinessLayer.MenuItem;
import BusinessLayer.Order;
import BusinessLayer.RestaurantProcessing;
import DataLayer.FileWriter;
import PT2019.Assignment_4.ReflectionTable;

public class WaiterGraphicalUserInterface extends Observable implements RestaurantProcessing{
	
	private JTable jt=new JTable();
	JScrollPane sp=new JScrollPane(jt);
	DefaultTableModel myModel;
	private JTextField id=new JTextField(10);
	private JTextField masa=new JTextField(10);
	private JButton insert = new JButton("CREATE");
	private JButton compute= new JButton("COMPUTE PRICE");
	private JButton bill=new JButton("GENERATE BILL");
	private JButton back = new JButton("BACK");
	private JButton viewDetails=new JButton("VIEW SELECTED ORDER DETAILS");
	private JFrame waiterFrame=new JFrame();
	
	public WaiterGraphicalUserInterface() {
		waiterFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		waiterFrame.setSize(800, 800);
		waiterFrame.setLocation(900, 200);
		
		JPanel mainPanel=new JPanel();
		JPanel p_up=new JPanel();
		
		ArrayList<Order> orders=new ArrayList<Order>();
		
		Iterator it=comenzi.entrySet().iterator();
    	while(it.hasNext())
    	{
    		Map.Entry<Order,ArrayList<MenuItem>> pair=(Map.Entry<Order,ArrayList<MenuItem>>)it.next();
    		orders.add(pair.getKey());
    		
    	}
    	
		myModel = ReflectionTable.createTable(orders);
		this.jt.setModel(myModel);
		jt.setPreferredScrollableViewportSize(new Dimension(700, 600));
		sp = new JScrollPane(jt);
		p_up.add(sp);
		
		JPanel p_down=new JPanel();
		
		JPanel p_down_1=new JPanel();
		JLabel idL=new JLabel("ID:");
		JLabel masaL=new JLabel("MASA:");
		p_down_1.add(idL);
		p_down_1.add(id);
		p_down_1.add(masaL);
		p_down_1.add(masa);
		
		JPanel p_down_2=new JPanel();
		p_down_2.add(insert);
		p_down_2.add(compute);
		p_down_2.add(bill);
		p_down_2.add(back);
		
		JPanel p_down_3=new JPanel();
		p_down_3.add(viewDetails);
		
		p_down.add(p_down_1);
		p_down.add(p_down_2);
		p_down.add(p_down_3);
		p_down.setLayout(new BoxLayout(p_down, BoxLayout.Y_AXIS));
		
		mainPanel.add(p_up);
		mainPanel.add(p_down);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	 	
		
		waiterFrame.setContentPane(mainPanel);
		waiterFrame.setTitle("WAITER WINDOW");
		waiterFrame.setVisible(true);
	}

	public void createMenuItem(MenuItem mi) {
		// TODO Auto-generated method stub
		
	}

	public void deleteMenuItem(MenuItem mi) {
		// TODO Auto-generated method stub
		
	}

	public void editMenuItem(MenuItem mi) {
		// TODO Auto-generated method stub
		
	}

	public void createOrder(Order or, ArrayList<MenuItem> comandaActuala) {
		
		comenzi.put(or, comandaActuala);
		updateJt();
		setChanged();
		notifyObservers(comandaActuala);
	}

	public int computePrice(Order or) {

		int totalPrice=0;
		ArrayList<MenuItem> comandaActuala=comenzi.get(or);
		for(MenuItem mi:comandaActuala)
		{
			totalPrice+=mi.computePrice();
		}
		JOptionPane.showMessageDialog(null, "Pret total comanda selectata:"+totalPrice+"", "Total price", JOptionPane.INFORMATION_MESSAGE);
		return totalPrice;
	}

	public void generateBill(Order or) {
		
		int totalPrice=computePrice(or);
		FileWriter.write(or, comenzi.get(or), totalPrice);
		
	}

	public JButton getInsert() {
		return insert;
	}

	public void setInsert(JButton insert) {
		this.insert = insert;
	}

	public JButton getCompute() {
		return compute;
	}

	public void setCompute(JButton compute) {
		this.compute = compute;
	}

	public JButton getBill() {
		return bill;
	}

	public void setBill(JButton bill) {
		this.bill = bill;
	}

	public JButton getBack() {
		return back;
	}

	public void setBack(JButton back) {
		this.back = back;
	}

	public JButton getViewDetails() {
		return viewDetails;
	}

	public void setViewDetails(JButton viewDetails) {
		this.viewDetails = viewDetails;
	}

	public JTable getJt() {
		return jt;
	}

	public void updateJt() {
        ArrayList<Order> orders=new ArrayList<Order>();
		
		Iterator it=comenzi.entrySet().iterator();
    	while(it.hasNext())
    	{
    		Map.Entry<Order,ArrayList<MenuItem>> pair=(Map.Entry<Order,ArrayList<MenuItem>>)it.next();
    		orders.add(pair.getKey());
    		
    	}
    	
		myModel = ReflectionTable.createTable(orders);
		myModel.fireTableDataChanged();
		this.jt.setModel(myModel);
	}

	public DefaultTableModel getMyModel() {
		return myModel;
	}

	public void setMyModel(DefaultTableModel myModel) {
		this.myModel = myModel;
	}
 
	public void dispose() {
		this.waiterFrame.dispose();
	}

	public JTextField getId() {
		return id;
	}

	public void setId(JTextField id) {
		this.id = id;
	}

	public JTextField getMasa() {
		return masa;
	}

	public void setMasa(JTextField masa) {
		this.masa = masa;
	}
	
	
}
