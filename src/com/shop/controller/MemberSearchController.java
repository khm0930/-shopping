package com.shop.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.shop.service.MemberService;
import com.shop.vo.MemberVO;

public class MemberSearchController implements Controller {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Parameter ����
		String id = request.getParameter("id");
		String nowpasswd = request.getParameter("nowpasswd");
		String job = request.getParameter("job");

		String path = null;
		if (job.equals("search"))
			path = "/memberSearch.jsp";
		else if (job.equals("update"))
			path = "/memberUpdate.jsp";
		else if (job.equals("delete"))
			path = "/memberDelete.jsp";	
		
		// ��ȿ�� üũ
		if (id.isEmpty()||nowpasswd.isEmpty()) {
			request.setAttribute("error", "ID 와 password를 입력해주시기 바랍니다.");
			HttpUtil.forward(request, response, path);
			return;
		}
		

		// Service ��ü�� �޼��� ȣ��
		MemberService service = MemberService.getInstance();
		MemberVO member = service.memberSearch(id,nowpasswd);


		// Output View �������� �̵�
		if (member == null) request.setAttribute("result", "검색된 정보가 없습니다.");
		request.setAttribute("member", member);

		if(job.equals("search")) path="/result/memberSearchOutput.jsp";
		HttpUtil.forward(request, response, path);

	}
}

