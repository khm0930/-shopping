package com.shop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.shop.vo.MemberItemVO;

public class MemberItemDAO {

	private static MemberItemDAO dao = new MemberItemDAO();
	private MemberItemDAO(){}

	public static MemberItemDAO getInstance() {
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

	public void addmember(MemberItemVO member) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			
			
			conn = connect();
			pstmt = conn.prepareStatement("insert into orders values(auto.nextval,?,?,?)");
			pstmt.setInt(1, member.getorder_id());
			pstmt.setInt(2, member.gettotal_price());
			pstmt.setString(3, member.getdelivery_date());

			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("insert error : " + ex);
		} finally {
			close(conn, pstmt);
		}
	}
}