package com.shop.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.shop.vo.ItemVO;
import com.shop.vo.MemberVO;

public class ItemDAO {
	private static ItemDAO dao = new ItemDAO();
	private ItemDAO(){}

	public static ItemDAO getInstance() {
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
	}
	public ArrayList<ItemVO> ItemList() {
		ArrayList<ItemVO> list = new ArrayList<ItemVO>();
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ItemVO item = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from item");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				item = new ItemVO();
				item.setItem_id(rs.getInt(1));
				item.setName(rs.getString(2));
				item.setPrice(rs.getInt(3));
				item.setSize(rs.getString(4));
				list.add(item);
				
			} 

		} catch (Exception ex) {
			System.out.println("list error " + ex);
		} finally {
			close(conn, pstmt, rs);
		}

		return list;
	}
	
	public ItemVO getItem(int itemId) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    ItemVO item = null;

	    try {
	        conn = connect(); // 데이터베이스 연결
	        String sql = "SELECT * FROM item WHERE item_id=?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, itemId);
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            item = new ItemVO();
	            item.setItem_id(rs.getInt("item_id"));
	            item.setName(rs.getString("item_name"));
	            item.setPrice(rs.getInt("price"));
	            item.setSize(rs.getString("item_size"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        close(conn, pstmt, rs); // 리소스 해제
	    }

	    return item;
	}
}
