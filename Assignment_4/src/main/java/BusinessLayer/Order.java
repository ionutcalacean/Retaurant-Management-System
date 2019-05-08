package BusinessLayer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {
	private int orderID;
	private int table;
	//private Date date;
	//private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private String currentDate;
	
	public Order(int orderID,int table)
	{
		this.orderID=orderID;
		this.table=table;
		Date date=new Date();
		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		currentDate=sdf.format(date);
	}
	
	@Override
	public int hashCode() {
		int hash=3;
		
		hash=29*hash+ orderID;
		hash=29*hash+ table;
		return hash;
		
	}


	@Override
	public boolean equals(Object obj) {
		if(this==obj) return true;
		if(obj==null) return false;
		if(this.getClass()!=obj.getClass())return false;
		Order order=(Order)obj;
		return orderID==order.orderID && table==order.table && currentDate.equals(order.currentDate);
		
	}
	

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getTable() {
		return table;
	}

	public void setTable(int table) {
		this.table = table;
	}


	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", table=" + table + ", date=" + currentDate + "]";
	}
	
	

}
