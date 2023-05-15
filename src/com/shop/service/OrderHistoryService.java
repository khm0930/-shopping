package com.shop.service;

import java.util.ArrayList;

import com.shop.dao.MemberItemDAO;
import com.shop.dao.OrderDAO;
import com.shop.dao.OrderHistoryDAO;
import com.shop.vo.OrderVO;

public class OrderHistoryService {

	private static OrderHistoryService service = new OrderHistoryService();
	public OrderDAO dao = OrderDAO.getInstance();
	
	public static OrderHistoryService getInstance() {
		return service;
	}
	public ArrayList<OrderVO> OrderHistoryList() {
		ArrayList<OrderVO> list = dao.OrderHistoryList();
		return list;
	}
}