package com.shop.service;

import java.util.ArrayList;

import com.shop.dao.MemberItemDAO;
import com.shop.vo.ItemVO;
import com.shop.vo.OrderDetailVO;

public class OrderDetailService {

	private static OrderDetailService service = new OrderDetailService();
	public MemberItemDAO dao = MemberItemDAO.getInstance();
	
	public static OrderDetailService getInstance() {
		return service;
	}

	public void memberInsert(OrderDetailVO member) {
		dao.insertordersform(member);
	}
	
	public ArrayList<OrderDetailVO> Orders_form() {
		ArrayList<OrderDetailVO> list = dao.Orders_form();
		return list;
	}
	
}