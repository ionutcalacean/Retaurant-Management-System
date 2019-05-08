package BusinessLayer;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import PT2019.Assignment_4.ReflectionTable;

public class OrderDetails {

	private JTable jt=new JTable();
	JScrollPane sp=new JScrollPane(jt);
	DefaultTableModel myModel;
	private JButton back = new JButton("BACK");
	private JFrame orderFrame=new JFrame();
	
	public OrderDetails(ArrayList<MenuItem> items) {
		orderFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		orderFrame.setSize(600, 500);
		orderFrame.setLocation(1200, 100);
		
		JPanel mainPanel=new JPanel();
		JPanel p_up=new JPanel();
		
		
		myModel = ReflectionTable.createTable(items);
		this.jt.setModel(myModel);
		
		sp = new JScrollPane(jt);
		p_up.add(sp);
		
		JPanel p_down=new JPanel();
		
		p_down.add(back);
		
		mainPanel.add(p_up);
		mainPanel.add(p_down);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		orderFrame.setContentPane(mainPanel);
		orderFrame.setTitle("ORDER WINDOW");
		orderFrame.setVisible(true);
		
	}

	public JButton getBack() {
		return back;
	}

	public void setBack(JButton back) {
		this.back = back;
	}
	public void dispose()
	{
		orderFrame.dispose();
	}
	
}
