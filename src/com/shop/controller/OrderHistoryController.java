package com.shop.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.vo.ItemVO;
import com.shop.vo.OrderVO;
import com.shop.service.OrderHistoryService;


public class OrderHistoryController implements Controller {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrderHistoryService service = OrderHistoryService.getInstance();
		ArrayList<OrderVO> orderlist = service.OrderHistoryList();
		
		

		request.setAttribute("list", orderlist);
		HttpUtil.forward(request, response, "/result/orderHistory.jsp");
	}
}
