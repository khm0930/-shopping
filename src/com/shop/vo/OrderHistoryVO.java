package com.shop.vo;

public class OrderHistoryVO {    
	
	private String order_id;
	private String customers_id;
	private String total_price;
	private String delivery_date;
	private int count;
	private String item_name;
	private String price;
	private String item_size;
	
	
	public String getorder_id() {
		return order_id;
	}

	public void setorder_id(String order_id) {
		this.order_id = order_id;
	}
	
	public String getcustomers_id() {
		return customers_id;
	}

	public void setcustomers_id(String customers_id) {
		this.customers_id = customers_id;
	}
	
	public String gettotal_price() {
		return total_price;
	}
	
	public void settotal_price(String total_price) {
		this.total_price = total_price;
	}
	public String getdelivery_date() {
		return delivery_date;
	}

	public void setdelivery_date(String delivery_date) {
		this.delivery_date = delivery_date;
	}
	public int getcount() {
		return count;
	}

	public void setcount(int count) {
		this.count = count;
	}
	public String getitem_name() {
		return item_name;
	}

	public void setitem_name(String name) {
		this.item_name = name;
	}
	public String getitem_price() {
		return price;
	}

	public void setitem_price(String price) {
		this.price = price;
	}
	public String getitem_size() {
		return item_size;
	}

	public void setitem_size(String size) {
		this.item_size = size;
	}

}
