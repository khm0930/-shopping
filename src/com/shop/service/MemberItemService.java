package com.shop.service;

import com.shop.dao.MemberItemDAO;
import com.shop.vo.MemberItemVO;

public class MemberItemService {

	private static MemberItemService service = new MemberItemService();
	public MemberItemDAO dao = MemberItemDAO.getInstance();
	
	public static MemberItemService getInstance() {
		return service;
	}

	public void memberInsert(MemberItemVO member) {
		dao.addmember(member);
	}
}