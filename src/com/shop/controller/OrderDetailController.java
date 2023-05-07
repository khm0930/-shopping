package com.shop.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.shop.service.OrderDetailService;
import com.shop.vo.OrderDetailVO;

public class OrderDetailController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		OrderDetailVO order = new OrderDetailVO();

		// Parameter 추출
		String order_id = request.getParameter("order_id");
		String item_id = request.getParameter("item_id");

		String countParam = request.getParameter("count_" + order.getitem_id());
		int count = Integer.parseInt(countParam);

		// String count = request.getParameter("count");

		// VO 객체에 데이터 바인딩(저장)

		order.setorder_id(order_id); // 클라이언트로부터 전달된 입력값들을 setter 메소드를 호출하여 MemberVO 객체의 멤버변수에 저장
		order.setitem_id(item_id);
		order.setcount(count);

		// Service 객체의 메소드 호출
		OrderDetailService service = OrderDetailService.getInstance(); // 회원 관리 서비스를 처리하는 모델인 MemberService 객체를 추출한후
																		// 회원정보 생성 서비스를 처리하는 memberInsert() 메소드를 호출
		service.memberInsert(order);

		// Output View 페이지로 이동
		// request.setAttribute("order_id", order_id); // id 값을 저장한후 페이지 이동
		// HttpUtil.forward(request, response, "/result/order_form.jsp");

		// MemberItemService service = MemberItemService.getInstance();
		ArrayList<OrderDetailVO> list = service.Orders_form();

		// String name = request.getParameter("name");
		// MemberItemVO item = new MemberItemVO();

		// item.setitem_name(name);

		request.setAttribute("list", list);
		HttpUtil.forward(request, response, "/result/orders_form.jsp");
	}

}
