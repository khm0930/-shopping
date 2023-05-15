package com.shop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shop.vo.ItemVO;
import com.shop.vo.MemberVO;
import com.shop.vo.OrderDetailVO;
import com.shop.vo.OrderHistoryVO;
import com.shop.vo.OrderVO;

public class OrderHistoryDAO {

	private static OrderHistoryDAO dao = new OrderHistoryDAO();
	private OrderHistoryDAO(){}

	public static OrderHistoryDAO getInstance() {
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



	public ArrayList<OrderHistoryVO> Orders_form() {

		ArrayList<OrderHistoryVO> list = new ArrayList<OrderHistoryVO>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderHistoryVO item = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from item NATURAL JOIN Orders_form NATURAL JOIN Orders");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				item = new OrderHistoryVO();
				item.setitem_name(rs.getString(1)); //orderdetailvo에 아직 추가 안했음
				item.setitem_size(rs.getString(2));
				item.setcount(rs.getInt(3));
				list.add(item);

			} 

		} catch (Exception ex) {
			System.out.println("list error " + ex);
		} finally {
			close(conn, pstmt, rs);
		}

		return list;
	}

	public void insertordersform(OrderDetailVO member) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("insert into orders_form(order_id,item_id,count) values(?,?,?)");
			pstmt.setString(1, member.getorder_id());
			pstmt.setString(2, member.getitem_id());
			pstmt.setInt(3, member.getcount());
			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("insert error : " + ex);
		} finally {
			close(conn, pstmt);
		}
	}

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
	
	public ArrayList<OrderVO> OrderList() {

		ArrayList<OrderVO> list = new ArrayList<OrderVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderVO order = null;
		ItemVO item = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("select i.item_name,i.item_size,odf.count from orders_form odf NATURAL JOIN item i"); //아이템 id, 아이템 수량, 아이템 총 가격 필요하므로 orders_form 테이블, item 테이블 필요 
			rs = pstmt.executeQuery();
			while (rs.next()) {
				order = new OrderVO();
				order.setitemName(rs.getString(1));
				order.setitemSize(rs.getString(2));
				order.setCount(rs.getInt(3));
				//order.settotal_price(rs.getInt(4));
				list.add(order);
				
			} 

		} catch (Exception ex) {
			System.out.println("list error " + ex);
		} finally {
			close(conn, pstmt, rs);
		}

		return list;
	}

}