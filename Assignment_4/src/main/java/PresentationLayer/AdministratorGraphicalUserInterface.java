package PresentationLayer;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import BusinessLayer.BaseProduct;
import BusinessLayer.CompositeProduct;
import BusinessLayer.MenuItem;
import BusinessLayer.Order;
import BusinessLayer.RestaurantProcessing;
import PT2019.Assignment_4.ReflectionTable;

public class AdministratorGraphicalUserInterface implements RestaurantProcessing{

	private JTable jt=new JTable();
	JScrollPane sp=new JScrollPane(jt);
	DefaultTableModel myModel;
	private JTextField nume=new JTextField(10);
	private JTextField pret=new JTextField(10);
	private JButton insert = new JButton("INSERT");
	private JButton edit = new JButton("EDIT");
	private JButton delete = new JButton("DELETE");
	private JButton back = new JButton("BACK");
	private JButton select=new JButton("CREATE COMPOSITE PRODUCT");
	private JButton selectForOrder=new JButton("SELECT PRODUCTS FOR ORDER");
	JFrame adminFrame=new JFrame();
	
	
	
	public AdministratorGraphicalUserInterface()
	{
		adminFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		adminFrame.setSize(800, 800);
		adminFrame.setLocation(100, 200);
		
		JPanel mainPanel=new JPanel();
		JPanel p_up=new JPanel();
		
		
		myModel = ReflectionTable.createTable(menu);
		System.out.println(menu);
		this.jt.setModel(myModel);
		jt.setPreferredScrollableViewportSize(new Dimension(700, 600));
		sp = new JScrollPane(jt);
		p_up.add(sp);
		
		JPanel p_down=new JPanel();
		
		JPanel p_down_1=new JPanel();
		JLabel numeL=new JLabel("NUME:");
		JLabel pretL=new JLabel("PRET:");
		p_down_1.add(numeL);
		p_down_1.add(nume);
		p_down_1.add(pretL);
		p_down_1.add(pret);
		
		JPanel p_down_2=new JPanel();
		p_down_2.add(insert);
		p_down_2.add(edit);
		p_down_2.add(delete);
		p_down_2.add(back);
		JPanel p_down_3=new JPanel();
		p_down_3.add(select);
		p_down_3.add(selectForOrder);
		JPanel p_down_4=new JPanel();
		JLabel selectL=new JLabel("Pentru a crea un fel compus selectati mai multe produse de mai sus");
		p_down_4.add(selectL);
		
		p_down.add(p_down_1);
		p_down.add(p_down_2);
		p_down.add(p_down_3);
		p_down.add(p_down_4);
		p_down.setLayout(new BoxLayout(p_down, BoxLayout.Y_AXIS));
 	
		mainPanel.add(p_up);
		mainPanel.add(p_down);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	
	    adminFrame.setContentPane(mainPanel);
	    adminFrame.setTitle("ADMIN WINDOW");
	    adminFrame.setVisible(true);
	}
	
	
	public void createMenuItem(MenuItem mi) {
		menu.add(mi);
		updateJt();
		
	}

	public void deleteMenuItem(MenuItem mi) {
		
		menu.remove(mi);
		updateJt();
	}

	public void editMenuItem(MenuItem mi) {
		int index=menu.indexOf(mi);
		
		System.out.println(index);
		MenuItem menuIt=menu.get(index);
		menuIt.setName(getNume().getText());
		String priceS=getPret().getText();
		int price=Integer.parseInt(priceS);
		if(menuIt instanceof CompositeProduct)
		{
			((CompositeProduct) menuIt).setPrice(price);
		}
		if(menuIt instanceof BaseProduct)
		{
			((BaseProduct) menuIt).setPrice(price);
		}
	
		updateJt();
		
	}

	public void createOrder(Order or, ArrayList<MenuItem> comandaActuala) {
		// TODO Auto-generated method stub
		
	}

	public int computePrice(Order or) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void generateBill(Order or) {
		// TODO Auto-generated method stub
		
	}


	public JButton getInsert() {
		return insert;
	}


	public void setInsert(JButton insert) {
		this.insert = insert;
	}


	public JButton getEdit() {
		return edit;
	}


	public void setEdit(JButton edit) {
		this.edit = edit;
	}


	public JButton getDelete() {
		return delete;
	}


	public void setDelete(JButton delete) {
		this.delete = delete;
	}


	public JButton getBack() {
		return back;
	}


	public void setBack(JButton back) {
		this.back = back;
	}


	public JTextField getNume() {
		return nume;
	}


	public void setNume(JTextField nume) {
		this.nume = nume;
	}


	public JTextField getPret() {
		return pret;
	}


	public void setPret(JTextField pret) {
		this.pret = pret;
	}
	
	
	public JButton getSelect() {
		return select;
	}


	public void setSelect(JButton select) {
		this.select = select;
	}


	public JTable getJt() {
		return jt;
	}
   
	public void updateJt() {
		myModel = ReflectionTable.createTable(menu);
		myModel.fireTableDataChanged();
		this.jt.setModel(myModel);

	}


	public DefaultTableModel getMyModel() {
		return myModel;
	}


	public void setMyModel(DefaultTableModel myModel) {
		this.myModel = myModel;
	}
	
	public void dispose()
	{
		adminFrame.dispose();
	}


	public JButton getSelectForOrder() {
		return selectForOrder;
	}


	public void setSelectForOrder(JButton selectForOrder) {
		this.selectForOrder = selectForOrder;
	}
	
	
}
