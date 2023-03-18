package com.shop.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.service.ItemService;
import com.shop.vo.ItemVO;

public class ItemAddController implements Controller {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ItemService service = ItemService.getInstance();
		ArrayList<ItemVO> list = service.ItemList();

		request.setAttribute("list", list);
		HttpUtil.forward(request, response, "/result/itemlist.jsp");
	}
}
