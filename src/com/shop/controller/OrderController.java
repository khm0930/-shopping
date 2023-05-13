package com.shop.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.service.ItemService;
import com.shop.service.OrderService;
import com.shop.vo.ItemVO;
import com.shop.vo.MemberItemVO;
import com.shop.vo.MemberVO;
import com.shop.vo.OrderVO;

public class OrderController implements Controller {
	//private OrderService orderService;
	int count;
	int itemId;
	int total=0;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Map<Integer, Integer> itemMap = new HashMap<>();
		Map<Integer, Integer> itemQuantityMap = new HashMap<>();
		Map<Integer, Integer> itemTotalPriceMap = new HashMap<>();
		
		
		OrderService service = OrderService.getInstance();
		ArrayList<OrderVO> orderlist = service.OrderList();
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		// 주문 정보 파라미터 받아오기
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		
		
		Enumeration<String> parameterNames = request.getParameterNames();
		
		while (parameterNames.hasMoreElements()) {
			String paramName = parameterNames.nextElement();
			if (paramName.startsWith("count_")) {
				itemId = Integer.parseInt(paramName.substring(6));
				count = Integer.parseInt(request.getParameter(paramName));
				int itemPrice = ItemService.getInstance().getPrice(itemId);
		        total = total+(count * itemPrice);
		        itemMap.put(itemId, count);
			}
		}
		int quantity=0;
		int itemPrice=0;
		int totalPrice=0;
		OrderVO orderItem = new OrderVO(); 	
		for (Map.Entry<Integer, Integer> entry : itemMap.entrySet()) {
			itemId = entry.getKey();
			quantity = entry.getValue();
			itemPrice = ItemService.getInstance().getPrice(itemId);
			totalPrice = quantity * itemPrice;

		    itemQuantityMap.put(itemId, quantity);
		    itemTotalPriceMap.put(itemId, totalPrice);
		    
		    orderItem.setItemQuantityMap(itemQuantityMap);
		    orderItem.setItemTotalPriceMap(itemTotalPriceMap);
		    orderItem.setItemId(itemId);
		    orderItem.settotal_price(total);
		    
		    ItemVO item = ItemService.getInstance().getItem(itemId);
		    orderItem.setitemName(item.getName());
	        orderItem.setitemSize(item.getSize());
		    System.out.println("Item ID: " + itemId + ", Quantity: " + quantity);
		    System.out.println("Item ID: " + itemId + ", Total Price: " + totalPrice);
		    
		    orderlist.add(orderItem);


		}
		orderItem.setName(name);
		orderItem.setAddress(address);
		orderItem.setPhone(phone);
		System.out.println("total:" + total);
		service.saveOrder(id,orderItem,itemMap);

		// 결제 페이지로 이동
		request.setAttribute("order", orderlist);
		HttpUtil.forward(request, response, "/result/orderList.jsp");
	}
}