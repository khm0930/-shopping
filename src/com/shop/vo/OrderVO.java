package com.shop.vo;
// 회원에 대한 정보들을 저장할 목적으로 만든 객체
public class OrderVO {    
	
	private String order_id;
	private String customers_id;
	private String total_price;
	private String delivery_date;

	
	
	
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

}
