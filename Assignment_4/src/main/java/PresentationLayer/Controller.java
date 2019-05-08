package PresentationLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import BusinessLayer.BaseProduct;
import BusinessLayer.CompositeProduct;
import BusinessLayer.MenuItem;
import BusinessLayer.Order;
import BusinessLayer.OrderDetails;
import BusinessLayer.RestaurantProcessing;
import DataLayer.RestaurantSerializator;

public class Controller {
     
	private static View myView;
	private static AdministratorGraphicalUserInterface adminView;
	private static WaiterGraphicalUserInterface waiterView;
	private static ChiefGraphicalUserInterface chiefView;
	private static OrderDetails orderView;
	private static Order newOrder;
	
	 public Controller(View myView)
	    {
	    	this.myView=myView;
	        addActionListeners();

	    }
	 
		private void addActionListeners() {
			
			
			myView.getWaiterButton().addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {
					Controller.waiterView=new WaiterGraphicalUserInterface();
					if(chiefView==null)
						chiefView=new ChiefGraphicalUserInterface();
					waiterView.addObserver(chiefView);
					addActionListeners1();
					
					
				}
				
			});
			myView.getAdminButton().addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					RestaurantSerializator.deserialize();
					Controller.adminView=new AdministratorGraphicalUserInterface();
					adminView.getSelectForOrder().setEnabled(false);
					addActionListeners2();
					
					
					
				}
				
			});
			
			
			myView.getChiefButton().addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {
					Controller.chiefView=new ChiefGraphicalUserInterface();
					addActionListeners3();
					
				
					
				}
				
			});
		}
		/*
		 * waiter
		 */
		private void addActionListeners1()
		{
			
			waiterView.getInsert().addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					RestaurantSerializator.deserialize();
					Controller.adminView=new AdministratorGraphicalUserInterface();
					adminView.getInsert().setEnabled(false);
					adminView.getEdit().setEnabled(false);
					adminView.getDelete().setEnabled(false);
					adminView.getSelect().setEnabled(false);
					addActionListeners2();
					
					int id=Integer.parseInt(waiterView.getId().getText());
					int table=Integer.parseInt(waiterView.getMasa().getText());
					newOrder=new Order(id,table);
					
					adminView.getSelectForOrder().addActionListener(new ActionListener() {
						
						public void actionPerformed(ActionEvent e) {
							int [] selected=adminView.getJt().getSelectedRows();
							ArrayList<MenuItem> orderItems=new ArrayList<MenuItem>();
							for(int i:selected)
							{
								
								MenuItem currentItem=RestaurantProcessing.menu.get(i);
								orderItems.add(currentItem);
							}
							
						
							
							
								waiterView.createOrder(newOrder, orderItems);
						}
						
					});
					
					
					
					
					
				}
				
			});
			
			waiterView.getCompute().addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					int row=waiterView.getJt().getSelectedRow();
					int columns=waiterView.getJt().getColumnCount();
					DefaultTableModel myModel=waiterView.getMyModel();
					ArrayList<Object> values=new ArrayList<Object>();
				    
					for(int i=0;i<columns;i++)
					{
						values.add(myModel.getValueAt(row, i));
					}
					String idS=values.get(0).toString();
					int id=Integer.parseInt(idS);
					String tableS=values.get(1).toString();
					int table=Integer.parseInt(tableS);
					String dateS=values.get(2).toString();
					
					Order or=new Order(id,table);
					or.setCurrentDate(dateS);
					
					waiterView.computePrice(or);
					
				}
				
			});
			
			waiterView.getBill().addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					int row=waiterView.getJt().getSelectedRow();
					int columns=waiterView.getJt().getColumnCount();
					DefaultTableModel myModel=waiterView.getMyModel();
					ArrayList<Object> values=new ArrayList<Object>();
				    
					for(int i=0;i<columns;i++)
					{
						values.add(myModel.getValueAt(row, i));
					}
					String idS=values.get(0).toString();
					int id=Integer.parseInt(idS);
					String tableS=values.get(1).toString();
					int table=Integer.parseInt(tableS);
					String dateS=values.get(2).toString();
					
					Order or=new Order(id,table);
					or.setCurrentDate(dateS);
					
					waiterView.generateBill(or);
					
				}
				
			});
			
		   waiterView.getBack().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				waiterView.dispose();
				
			}
			   
		   });	
		   
			
		   waiterView.getViewDetails().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				int row=waiterView.getJt().getSelectedRow();
				int columns=waiterView.getJt().getColumnCount();
				DefaultTableModel myModel=waiterView.getMyModel();
				ArrayList<Object> values=new ArrayList<Object>();
			    
				for(int i=0;i<columns;i++)
				{
					values.add(myModel.getValueAt(row, i));
				}
				String idS=values.get(0).toString();
				int id=Integer.parseInt(idS);
				String tableS=values.get(1).toString();
				int table=Integer.parseInt(tableS);
				String dateS=values.get(2).toString();
				
				Order or=new Order(id,table);
				or.setCurrentDate(dateS);
	
				ArrayList<MenuItem> items=RestaurantProcessing.comenzi.get(or);
				orderView=new OrderDetails(items);
				addActionListeners4();
				
			}
			   
		   });	
		}
		/*
		 * admin
		 */
		private void addActionListeners2()
		{
			adminView.getInsert().addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {
					String name=adminView.getNume().getText();
					String priceS=adminView.getPret().getText();
					int price=0;
					try{
						price=Integer.parseInt(priceS);
					}catch(NumberFormatException e)
					{
						JOptionPane.showMessageDialog(null,
							    "Pret invalid!",
							    "Update error",
							    JOptionPane.ERROR_MESSAGE);
						throw new NoSuchElementException("Pret incorect");
					}
					if(price <0)
					{
						JOptionPane.showMessageDialog(null,
							    "Pret < 0!",
							    "Update error",
							    JOptionPane.ERROR_MESSAGE);
						throw new NoSuchElementException("Pret incorect");
					}
					
					
					BaseProduct bp=new BaseProduct(name, price);
					adminView.createMenuItem(bp);
					
					
				}
				
			});
			
			adminView.getEdit().addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					int row=adminView.getJt().getSelectedRow();
					int columns=adminView.getJt().getColumnCount();
					/*DefaultTableModel myModel=adminView.getMyModel();
					ArrayList<Object> values=new ArrayList<Object>();
				    
					for(int i=0;i<columns;i++)
					{
						values.add(myModel.getValueAt(row, i));
					}
					String name=values.get(0).toString();
					String priceS=values.get(1).toString();
					int price=Integer.parseInt(priceS);*/
					
					MenuItem bp=RestaurantProcessing.menu.get(row);
					System.out.println(bp);
					adminView.editMenuItem(bp);
					
				}
				
			});
			adminView.getDelete().addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					int row=adminView.getJt().getSelectedRow();
					MenuItem mi=RestaurantProcessing.menu.get(row);
					adminView.deleteMenuItem(mi);
					
				}
				
			});
			
			adminView.getSelect().addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					String name=JOptionPane.showInputDialog(null, "Introduceti numele felului de mancare compus:");
					int [] selected=adminView.getJt().getSelectedRows();
					CompositeProduct newProduct=new CompositeProduct(name);
					for(int i:selected)
					{
						MenuItem currentItem=RestaurantProcessing.menu.get(i);
						System.out.println(currentItem);
						newProduct.add(currentItem);
					}
					adminView.createMenuItem(newProduct);
				}
				
			});
			adminView.getBack().addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					ArrayList<MenuItem> copy=RestaurantProcessing.menu;
			       RestaurantSerializator.serialize(copy);
			       
			       System.out.println(RestaurantProcessing.menu);
				   adminView.dispose();
					
				}
				
			});
		
		}
		/*
		 * chief
		 */
		private void addActionListeners3()
		{
			
		}
		/*
		 * order details
		 */
		private void addActionListeners4() {
			orderView.getBack().addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					orderView.dispose();
					
				}
				
			});
		}
}
