package com.shop.service;

import java.util.ArrayList;

import com.shop.dao.MemberItemDAO;
import com.shop.vo.ItemVO;
import com.shop.vo.OrderVO;

public class OrderHistoryService {

	private static OrderHistoryService service = new OrderHistoryService();
	public MemberItemDAO dao = MemberItemDAO.getInstance();
	
	public static OrderHistoryService getInstance() {
		return service;
	}
	
}