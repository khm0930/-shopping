package com.shop.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.MemberDAO;
import com.shop.service.MemberService;
import com.shop.vo.MemberVO;

public class LoginController implements Controller {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	String id = request.getParameter("id");
    	String passwd= request.getParameter("passwd");
    	String name = request.getParameter("name");
    	
		
    	
    	if (id.isEmpty() || passwd.isEmpty() ) {
			request.setAttribute("error", "아이디와 비밀번호를 입력해주세요."); //오류가 발생하여 입력 페이지로 이동했을때 보여 줄 오류 메시지를 request 에 저장
			HttpUtil.forward(request, response, "/memberlogin.jsp");
			return;
		}
    	
    	
		
		MemberService loginservice = MemberService.getInstance();
		MemberVO member = loginservice.memberLogin(id,passwd);
		MemberDAO memberDAO = MemberDAO.getInstance();
	
		
		//로그인이 성공 되면 member 객체가 넘어오고 실패하면 null이 넘어옴
	
		
		if (member == null) request.setAttribute("result", "아이디나 비밀번호를 다시 입력하세요.");
		request.setAttribute("member", member);
		//if (result==-2) request.setAttribute("result", "아이디나 비밀번호를 다시 입력하세요.");
		//request.setAttribute("member", member);
		
		
		//if (result == 0) request.setAttribute("result", "아이디나 비밀번호를 다시 입력하세요.");
		//request.setAttribute("member", member);
		
		
		
		
		
		request.setAttribute("name", name); 
		request.setAttribute("id", id); 		// id 값을 저장한후 페이지 이동
		HttpUtil.forward(request, response, "/result/memberLoginOutput.jsp");

		
	}
	
	
	/*public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Parameter ����
		String id = request.getParameter("id");
		
		// ��ȿ�� üũ
		if (id.isEmpty()) {
			request.setAttribute("error", "ID를 입력해주시기 바랍니다.");
			HttpUtil.forward(request, response,"/memberInsert.jsp" );
			return;
		}

		// Service ��ü�� �޼��� ȣ��
		MemberService service = MemberService.getInstance();
		MemberVO member = service.memberSearch(id);


		// Output View �������� �̵�
		if (member == null) request.setAttribute("result", "검색된 정보가 없습니다.");
		request.setAttribute("member", member);

		request.setAttribute("id", id); 		// id 값을 저장한후 페이지 이동
		HttpUtil.forward(request, response, "/result/memberInsertOutput.jsp");
	}*/


}