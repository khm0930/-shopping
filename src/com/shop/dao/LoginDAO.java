package com.shop.dao;

import java.sql.*;

import com.shop.vo.MemberVO;

public class LoginDAO {
	
	private static LoginDAO loginDAO = new LoginDAO();
	Connection conn = null;
	private LoginDAO() {
	}
	
	public static LoginDAO getInstance() {
		if(loginDAO==null) {
			loginDAO = new LoginDAO();
		}
		return loginDAO;
	}
	
	public void setConnection(Connection con) {
		this.conn=con;
	}
	
	public Connection connect() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
		} catch (Exception ex) {
			System.out.println("오류 발생: " + ex);
			
		}
		return conn;
	}
	
	
	public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception ex) {
				System.out.println("오류발생 : " + ex);				
			}
		}
		close(conn, ps);
	} // close
	
	
	public void close(Connection conn, PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
			} catch (Exception ex) {
				System.out.println("오류발생 : " + ex);				
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (Exception ex) {
				System.out.println("오류발생 : " + ex);				
			}
		}
	} // close
	
	
	public MemberVO selectLoginMember(String id,String pwd) {
		MemberVO member = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		Connection conn = null;
		
		try {
			conn = connect();
			pstmt=conn.prepareStatement("SELECT * FROM member WHERE name_id=? AND password=?");
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				member = new MemberVO();
				member.setName(rs.getString(2));
				member.setId(rs.getString(3));
				member.setPasswd(rs.getString(4));
				member.setMail(rs.getString(5));
				member.setaddress(rs.getString(6));
				member.setphone(rs.getString(7));
				member.setgender(rs.getString(8));

		}
	}catch (Exception e) {
			e.printStackTrace();
		}
		
		return member;
		}
	}