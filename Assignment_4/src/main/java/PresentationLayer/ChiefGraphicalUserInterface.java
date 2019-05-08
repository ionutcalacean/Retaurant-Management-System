package PresentationLayer;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import BusinessLayer.CompositeProduct;
import BusinessLayer.ItemsChiefs;
import BusinessLayer.MenuItem;
import PT2019.Assignment_4.ReflectionTable;

public class ChiefGraphicalUserInterface implements Observer {
 
	private JTable jt=new JTable();
	JScrollPane sp=new JScrollPane(jt);
	DefaultTableModel myModel;
	ArrayList<ItemsChiefs> produse=new ArrayList<ItemsChiefs>();
	private JFrame chiefFrame=new JFrame();
	
	
	public ChiefGraphicalUserInterface()
	{
		chiefFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		chiefFrame.setSize(400, 400);
		chiefFrame.setLocation(1500, 0);
		
		JPanel mainPanel=new JPanel();
        
		JPanel p_up=new JPanel();
		
		DefaultTableModel myModel=new DefaultTableModel();
		this.jt.setModel(myModel);
		jt.setPreferredScrollableViewportSize(new Dimension(300, 300));
		sp = new JScrollPane(jt);
		p_up.add(sp);
		
		mainPanel.add(p_up);
		chiefFrame.setContentPane(mainPanel);
	    chiefFrame.setTitle("CHIEF WINDOW");
	    chiefFrame.setVisible(true);
		
	}
	
	

	public JTable getJt() {
		return jt;
	}
   
	public void updateJt() {
		
		myModel.fireTableDataChanged();
		this.jt.setModel(myModel);

	}


	public void update(Observable arg0, Object arg1) {
		
		ArrayList<MenuItem> list=(ArrayList<MenuItem>)arg1;
		boolean containsComposed=false;
		for(MenuItem mi:list)
		{
			ItemsChiefs newItem=new ItemsChiefs(mi.getName());
			if(mi instanceof CompositeProduct)
				containsComposed=true;
			produse.add(newItem);
		}
		if(containsComposed) {
		   myModel = ReflectionTable.createTable(produse);
		   updateJt();
		}
		else
			produse.clear();
	}

	
}
