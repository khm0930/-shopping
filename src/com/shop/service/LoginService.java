package com.shop.service;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.shop.dao.LoginDAO;
import com.shop.dao.MemberDAO;
import com.shop.vo.MemberVO;

public class LoginService{
	
	private static LoginService service = new LoginService();
	public LoginDAO dao = LoginDAO.getInstance();
	public LoginService(){}
	
	public MemberVO getLoginMember(String id,String passwd){
		LoginDAO loginDAO =LoginDAO.getInstance();
		Connection con = getConnection();
		loginDAO.setConnection(con);
		MemberVO loginMember = loginDAO.selectLoginMember(id,passwd);
		close(con);
		return loginMember;
}

	public static Connection getConnection() {
		Connection con = null;
		try {
			InitialContext initCtx= new InitialContext();
			DataSource ds=(DataSource)initCtx.lookup("java:comp/env/jdbc/oracle");
			
			con=ds.getConnection();
			con.setAutoCommit(false);
			System.out.println("connect sucess");
		}catch(Exception e) {
			System.out.println("connection error");
			e.printStackTrace();
		}
		return con;
	}
	public static void close(Connection con) {
		try {
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}