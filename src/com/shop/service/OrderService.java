package com.shop.service;

import java.util.ArrayList;
import java.util.Map;

import com.shop.dao.OrderDAO;
import com.shop.vo.ItemVO;
import com.shop.vo.OrderVO;

public class OrderService {

	private static OrderService service = new OrderService();
	public OrderDAO dao = OrderDAO.getInstance();
	
	public static OrderService getInstance() {
		return service;
	}
	public void saveOrder(String id,OrderVO order, Map<Integer, Integer> itemMap) {
		dao.saveOrder(id,order,itemMap);
	}
	public ArrayList<OrderVO> OrderList() {
		ArrayList<OrderVO> list = dao.OrderList();
		return list;
	}
	
}