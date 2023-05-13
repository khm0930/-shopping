package com.shop.vo;

public class ItemVO {    
	private int item_id;
	private String item_name;
	private int price;
	private String item_size;
	
	
	
	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public String getName() {
		return item_name;
	}

	public void setName(String name) {
		this.item_name = name;
	}
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	public String getSize() {
		return item_size;
	}

	public void setSize(String size) {
		this.item_size = size;
	}

	

}
