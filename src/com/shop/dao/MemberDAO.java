package com.shop.dao;

import java.sql.*;
import java.util.ArrayList;

import com.shop.vo.MemberVO;

public class MemberDAO {

	private static MemberDAO dao = new MemberDAO();
	private MemberDAO(){}

	public static MemberDAO getInstance() {
		return dao;
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

	public void memberInsert(MemberVO member) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			
			
			conn = connect();
			pstmt = conn.prepareStatement("insert into member values(auto.nextval,?,?,?,?,?,?,?)");
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getId());
			pstmt.setString(3, member.getPasswd());
			pstmt.setString(4, member.getMail());
			pstmt.setString(5, member.getaddress());
			pstmt.setString(6, member.getphone());
			pstmt.setString(7, member.getgender());
			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("insert error : " + ex);
		} finally {
			close(conn, pstmt);
		}
	}

	public MemberVO memberSearch(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		MemberVO member = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from member where NAME_ID=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				member = new MemberVO();
				member.setName(rs.getString(2));
				member.setId(rs.getString(3));
				member.setPasswd(rs.getString(4));
				member.setMail(rs.getString(5));
				member.setaddress(rs.getString(6));
				member.setphone(rs.getString(7));
				member.setgender(rs.getString(8));
			}

		} catch (Exception ex) {
			System.out.println("search error : " + ex);
		} finally {
			close(conn, pstmt, rs);
		}

		return member;
	}

	public void memberUpdate(MemberVO member) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("update member set password=?,name=?,mail=?,address=?,phone=?,gender=? where name_id=?");
			pstmt.setString(1, member.getPasswd());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getMail());
			pstmt.setString(4, member.getaddress());
			pstmt.setString(5, member.getphone());
			pstmt.setString(6, member.getgender());
			pstmt.setString(7, member.getId());
			pstmt.executeUpdate();

		} catch (Exception ex) {
			System.out.println("update error : " + ex);
		} finally {
			close(conn, pstmt);
		}

	}

	public void memberDelete(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("delete from member where NAME_ID=?");
			pstmt.setString(1, id);
			pstmt.executeUpdate(); 

		} catch (Exception ex) {
			System.out.println("delete error : " + ex);
		} finally {
			close(conn, pstmt);
		}
	}

	public ArrayList<MemberVO> memberList() {

		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO member = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from member");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				member = new MemberVO();
				member.setName(rs.getString(2));
				member.setId(rs.getString(3));
				member.setPasswd(rs.getString(4));
				member.setMail(rs.getString(5));
				member.setaddress(rs.getString(6));
				member.setphone(rs.getString(7));
				member.setgender(rs.getString(8));
				list.add(member);
			}

		} catch (Exception ex) {
			System.out.println("list error " + ex);
		} finally {
			close(conn, pstmt, rs);
		}

		return list;
	}


	public MemberVO memberLogin(String id, String password) {
		
		String name = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO member = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from member where NAME_ID=? AND password = ?");
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				member = new MemberVO();
				member.setName(rs.getString(2));
				member.setId(rs.getString(3));
				member.setPasswd(rs.getString(4));
				member.setMail(rs.getString(5));
				member.setaddress(rs.getString(6));
				member.setphone(rs.getString(7));
				member.setgender(rs.getString(8));
			}

		} catch (Exception ex) {
			System.out.println("search error : " + ex);
		} finally {
			close(conn, pstmt, rs);
		}

		return member;
	}
	public int memberIdCheck(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO member = null;
		int idCheck = 1;
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from member where NAME_ID=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next() || id.equals("")) {
				idCheck = 0;
			}
			else {
				idCheck=1;
			}

		} catch (Exception ex) {
			System.out.println("idCheck error : " + ex);
		} finally {
			close(conn, pstmt, rs);
		}

		return idCheck;
	}

	
	
	
	
	
}
