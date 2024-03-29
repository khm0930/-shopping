package com.shop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shop.vo.ItemVO;
import com.shop.vo.MemberVO;
import com.shop.vo.OrderHistoryVO;
import com.shop.vo.OrderVO;

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


	public List<OrderHistoryVO> getOrderHistoryData() { //ORDER 마지막 페이지
		List<OrderHistoryVO> orderHistoryData = new ArrayList<OrderHistoryVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("select of.*, i.item_name,i.price FROM item i JOIN orders_form of ON i.item_id = of.item_id JOIN orders o ON of.order_id = o.order_id JOIN member m ON o.customer_id = m.customer_id ORDER BY o.delivery_date DESC");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				OrderHistoryVO orderHistory = new OrderHistoryVO();
				orderHistory.setdelivery_date(rs.getString("order_date"));
				orderHistory.setorder_id(rs.getString("orderer_id"));
				orderHistory.setitem_name(rs.getString("item_name")); //추가 해야함
				orderHistory.setcount(rs.getInt("count"));
				orderHistory.settotal_price(rs.getString("price"));
				orderHistoryData.add(orderHistory);
			}

		}catch (Exception ex) {
			System.out.println("insert error : " + ex);
		} finally {
			close(conn, pstmt);
		}
		return orderHistoryData;
	}
}