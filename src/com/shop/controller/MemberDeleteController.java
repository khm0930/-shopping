package com.shop.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.shop.service.MemberService;

public class MemberDeleteController implements Controller {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Parameter ����
		String id = request.getParameter("id");
		String nowpasswd = request.getParameter("nowpasswd");

		// Service ��ü�� �޼��� ȣ��
		MemberService service = MemberService.getInstance();
		service.memberDelete(id,nowpasswd);

		// Output View �������� �̵�
		HttpUtil.forward(request, response, "/result/memberDeleteOutput.jsp");

	}
}
