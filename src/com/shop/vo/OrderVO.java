package com.shop.vo;

import java.util.HashMap;
import java.util.Map;
import java.sql.Date;
import java.time.LocalDate;

// 회원에 대한 정보들을 저장할 목적으로 만든 객체
public class OrderVO {    

	private int order_id;
	private String customers_id;
	private int total_price;
	private LocalDate delivery_date;
	private LocalDate order_date;
	private Map<Integer, Integer> itemMap;
	private String name;
	private String address;
	private String phone;
	private int count;
	private String itemname;
	private String itemsize;
	private int itemprice;
	private Map<Integer, Integer> itemQuantityMap;
	private Map<Integer, Integer> itemTotalPriceMap;
	private int itemId;


	public int getorder_id() {
		return order_id;
	}

	public void setorder_id(int order_id) {
		this.order_id = order_id;
	}

	public String getcustomers_id() {
		return customers_id;
	}

	public void setcustomers_id(String customers_id) {
		this.customers_id = customers_id;
	}

	public int getTotalPrice() {
		return total_price;
	}

	public void setTotalPrcie(int total_price) {
		this.total_price = total_price;
	}
	public LocalDate getDeliveryDate() {
		return delivery_date;
	}

	public void setDeliveryDate(LocalDate delivery_date) {
		this.delivery_date = delivery_date;
	}
	public LocalDate getOrderDate() {
		return order_date;
	}

	public void setOrderDate(LocalDate date) {
		this.order_date = date;
	}

	public Map<Integer, Integer> getItemMap() {
		if(itemMap ==null) {
			itemMap= new HashMap<>();
		}    
		return itemMap;
	}
	public Map<Integer, Integer> getItemQuantityMap() {
		if(itemQuantityMap ==null) {
			itemQuantityMap= new HashMap<>();
		}    
		return itemQuantityMap;
	}
	public void setItemQuantityMap(Map<Integer, Integer> itemQuantityMap) {
		this.itemQuantityMap = itemQuantityMap;
	}

	public Map<Integer, Integer> getItemTotalPriceMap() {
		if(itemTotalPriceMap ==null) {
			itemTotalPriceMap= new HashMap<>();
		}    
		return itemTotalPriceMap;
	}
	public void setItemTotalPriceMap(Map<Integer, Integer> itemTotalPriceMap) {
		this.itemTotalPriceMap = itemTotalPriceMap;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		// TODO Auto-generated method stub
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		// TODO Auto-generated method stub
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getitemName() {
		// TODO Auto-generated method stub
		return itemname;
	}
	public void setitemName(String itemname) {
		this.itemname = itemname;
	}
	public String getitemSize() {
		// TODO Auto-generated method stub
		return itemsize;
	}
	public void setitemSize(String itemsize) {
		this.itemsize = itemsize;
	}
	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getitemPrice() {
		return itemprice;
	}

	public void setitemPrice(int itemprice) {
		this.itemprice = itemprice;
	}
}
