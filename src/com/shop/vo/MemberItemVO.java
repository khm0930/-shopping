package com.shop.vo;
// 회원에 대한 정보들을 저장할 목적으로 만든 객체
public class MemberItemVO {    
	
	
	private int order_id;
	private String id; 
	private String item_name;
	private String item_size;
	private int count;
	private int total_price;
	private String delivery_date;

	
	
	
	public int getorder_id() {
		return order_id;
	}

	public void setorder_id(int order_id) {
		this.order_id = order_id;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getitem_name() {
		return item_name;
	}

	public void setitem_name(String item_name) {
		this.item_name = item_name;
	}
	
	public String getitem_size() {
		return item_size;
	}

	public void setitem_size(String item_size) {
		this.item_size = item_size;
	}

	public int getcount() {
		return count;
	}

	public void setcount(int count) {
		this.count = count;
	}

	public int gettotal_price() {
		return total_price;
	}
	
	public void settotal_price(int total_price) {
		this.total_price = total_price;
	}
	public String getdelivery_date() {
		return delivery_date;
	}

	public void setdelivery_date(String delivery_date) {
		this.delivery_date = delivery_date;
	}

}
