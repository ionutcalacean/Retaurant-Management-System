package PresentationLayer;


import java.awt.*;

import javax.swing.*;

public class View {
  
	private JButton waiterButton=new JButton("WAITER");
	private JButton adminButton=new JButton("ADMIN");
	private JButton chiefButton=new JButton("CHIEF");
	JFrame myFrame=new JFrame();
	
	public View(){
	
		myFrame.setSize(400, 200);
		myFrame.setLocation(700,500);
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel mainPanel=new JPanel();
		
		
		mainPanel.add(waiterButton);
		mainPanel.add(adminButton);
		mainPanel.add(chiefButton);
	
		
		
		myFrame.setTitle("Restaurant Main Menu");
        myFrame.setContentPane(mainPanel);		
		myFrame.setVisible(true);
	}


	public JButton getWaiterButton() {
		return waiterButton;
	}


	public void setWaiterButton(JButton clientsButton) {
		this.waiterButton = clientsButton;
	}


	public JButton getAdminButton() {
		return adminButton;
	}


	public void setAdminButton(JButton productsButton) {
		this.adminButton = productsButton;
	}


	public JButton getChiefButton() {
		return chiefButton;
	}


	public void setChiefsButton(JButton ordersButton) {
		this.chiefButton = ordersButton;
	}

	public void dispose()
	{
		myFrame.dispose();
	}
}
