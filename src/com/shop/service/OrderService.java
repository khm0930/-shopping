package com.shop.service;

import java.util.ArrayList;

import com.shop.dao.MemberItemDAO;
import com.shop.vo.ItemVO;
import com.shop.vo.OrderVO;

public class OrderService {

	private static OrderService service = new OrderService();
	public MemberItemDAO dao = MemberItemDAO.getInstance();
	
	public static OrderService getInstance() {
		return service;
	}
	
}