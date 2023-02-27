package com.shop.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.shop.service.MemberService;
import com.shop.vo.MemberVO;

public class MemberUpdateController implements Controller {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Parameter ����
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		String name = request.getParameter("name");
		String mail = request.getParameter("mail");

		// ��ȿ�� üũ
		if (id.isEmpty() || passwd.isEmpty() || name.isEmpty() || mail.isEmpty()) {
			request.setAttribute("error", "모든 항목을 빠짐없이 입력해주시기 바랍니다.");
			HttpUtil.forward(request, response, "/memberUpdate.jsp");
			return;
		}

		// VO��ü�� ����Ÿ ���ε�
		MemberVO member = new MemberVO();
		member.setId(id);
		member.setPasswd(passwd);
		member.setName(name);
		member.setMail(mail);

		// Service ��ü�� �޼��� ȣ��
		MemberService service = MemberService.getInstance();
		service.memberUpdate(member);

		// Output View �������� �̵�
		request.setAttribute("id", id);
		HttpUtil.forward(request, response, "/result/memberUpdateOutput.jsp");
	}
}
