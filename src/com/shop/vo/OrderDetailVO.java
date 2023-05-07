package com.shop.vo;
// 회원에 대한 정보들을 저장할 목적으로 만든 객체
public class OrderDetailVO {    
	
	
	private String order_id;
	private String item_id; 
	private int count;
	
	
	
	public String getorder_id() {
		return order_id;
	}

	public void setorder_id(String order_id) {
		this.order_id = order_id;
	}
	

	
	public String getitem_id() {
		return item_id;
	}

	public void setitem_id(String id) {
		this.item_id = id;
	}

	
	public int getcount() {
		return count;
	}

	public void setcount(int count) {
		this.count = count;
	}



}
