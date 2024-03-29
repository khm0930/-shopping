package com.shop.service;

import java.util.ArrayList;

import com.shop.dao.MemberDAO;
import com.shop.vo.MemberVO;

public class MemberService {

	private static MemberService service = new MemberService();
	public MemberDAO dao = MemberDAO.getInstance();
	
	public static MemberService getInstance() {
		return service;
	}

	public void memberInsert(MemberVO member) {
		dao.memberInsert(member);
	}

	public MemberVO memberSearch(String id,String pwd) {
		MemberVO member = dao.memberSearch(id,pwd);
		return member;
	}

	public void memberUpdate(MemberVO member) {
		dao.memberUpdate(member);
	}

	public void memberDelete(String id,String pwd) {
		dao.memberDelete(id,pwd);
	}

	public ArrayList<MemberVO> memberList() {
		ArrayList<MemberVO> list = dao.memberList();
		return list;
	}
	
	public MemberVO memberLogin(String id, String pwd) {
		MemberVO member = dao.memberLogin(id,pwd);
		return member;
	}
	public int checkIdService(String id) {
		int member = dao.memberIdCheck(id);
		return member;
	}

}

