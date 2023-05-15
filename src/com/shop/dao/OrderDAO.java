package com.shop.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.time.LocalDate;

import com.shop.vo.ItemVO;
import com.shop.vo.OrderDetailVO;
import com.shop.vo.OrderHistoryVO;
import com.shop.vo.OrderVO;

public class OrderDAO {

	private static OrderDAO dao = new OrderDAO();
	private OrderDAO(){}


	public static OrderDAO getInstance() {
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

	/*public void insertorders(OrderVO member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList<OrderVO> order = new ArrayList<OrderVO>();
		try {


			conn = connect();
			pstmt = conn.prepareStatement("insert into orders values(auto.nextval,?,?,?)");
			pstmt.setString(1, member.getcustomers_id());
			pstmt.setInt(2, member.getTotalPrice());
			pstmt.setLocalDate(3, member.getDeliveryDate());

			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("insert error : " + ex);
		} finally {
			close(conn, pstmt);
		}
	}*/




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
	
	public void registerOutParameter(int parameterIndex, int sqlType) throws SQLException {
		PreparedStatement pstmt = null;
	    ((CallableStatement) pstmt).registerOutParameter(parameterIndex, sqlType);
	}

	
	OrderVO order2 = new OrderVO();
	public void saveOrder(String id, OrderVO order,Map<Integer, Integer> itemMap) {
		// 데이터베이스 연결
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt0 = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		int customerId = -1;

		try {
			conn = connect();
			String sql = "SELECT customers_id FROM member WHERE name_id = ?";
			pstmt0 = conn.prepareStatement(sql);
			System.out.println("id: " + id);
			pstmt0.setString(1, id);
			rs = pstmt0.executeQuery();
			if (rs.next()) {				
				customerId = rs.getInt("customers_id");
				System.out.println("CUSTOMERID: " + customerId);
			}
			// 주문 내역 저장 쿼리문 작성
			// orders 테이블에 주문 정보 저장
			
			/*String sql1 = "INSERT INTO orders (order_id,customers_id,ordername, orderaddress, orderphone) VALUES (auto.nextval, ?, ?, ?,?)";
			pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setInt(1, customerId);
			pstmt1.setString(2, order.getName());
			pstmt1.setString(3, order.getAddress());
			pstmt1.setString(4, order.getPhone());
			pstmt1.executeUpdate();
			*/
			String sql5 = "{call INSERT_ORDER (?, ?, ?, ?, ?, ?, ?, ?)}";
			CallableStatement cstmt1 = conn.prepareCall(sql5);
			cstmt1.setInt(1, customerId);
			cstmt1.setInt(2, order.getTotalPrice());
			cstmt1.setDate(3, java.sql.Date.valueOf(order.getDeliveryDate()));
			cstmt1.setString(4, order.getName());
			cstmt1.setString(5, order.getAddress());
			cstmt1.setString(6, order.getPhone());
			cstmt1.setDate(7, java.sql.Date.valueOf(order.getOrderDate()));
			cstmt1.registerOutParameter(8, java.sql.Types.NUMERIC); //출력 매개변수를 사용 주문번호 id를 갖고오기 위해
			cstmt1.executeUpdate();

			order2.setorder_id(cstmt1.getInt(8));
			System.out.println("order_id: " + order2.getorder_id());
			int itemId;
			int count;
			// orders_form 테이블에 주문 항목 및 수량 저장
			String sql2 = "INSERT INTO orders_form (order_id, item_id, count) VALUES (?, ?, ?)";
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, order2.getorder_id());
			for (Map.Entry<Integer, Integer> entry : itemMap.entrySet()) {
				pstmt2.setInt(2, entry.getKey());
				pstmt2.setInt(3, entry.getValue());
				if(entry.getValue()>0)
					pstmt2.executeUpdate();
			}
			System.out.println("sql succes");


		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("saveOrder waring");
		} finally {
			// 리소스 해제
			try {
				if (pstmt1 != null) pstmt1.close();
				if (pstmt2 != null) pstmt2.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public int getCustomerId(String id) {
		OrderDAO orderDao = OrderDAO.getInstance();
		int customerId = orderDao.getCustomerIdById(id);
		return customerId;
	}

	private int getCustomerIdById(String id) {	
		OrderDAO orderDAO = OrderDAO.getInstance();
		return orderDAO.getCustomerId(id);
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
	
	public ArrayList<OrderVO> OrderHistoryList() {

		ArrayList<OrderVO> list = new ArrayList<OrderVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderVO order = null;
		ItemVO item = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("SELECT o.ordername, o.orderaddress, o.order_date, o.delivery_date, o.total_price, i.item_name,i.item_size,i.price, orf.count FROM orders o JOIN orders_form orf ON o.order_id = orf.order_id JOIN item i ON orf.item_id = i.item_id WHERE o.order_id = ?"); //아이템 id, 아이템 수량, 아이템 총 가격 필요하므로 orders_form 테이블, item 테이블 필요 
			System.out.println("order_id: " + order2.getorder_id());
			pstmt.setInt(1,order2.getorder_id());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				order = new OrderVO();
				order.setName(rs.getString(1));
				order.setAddress(rs.getString(2));
				order.setOrderDate(rs.getDate(3).toLocalDate());
				order.setDeliveryDate(rs.getDate(4).toLocalDate());
				order.setTotalPrcie(rs.getInt(5));
				order.setitemName(rs.getString(6));
				order.setitemSize(rs.getString(7));
				order.setitemPrice(rs.getInt(8));
				order.setCount(rs.getInt(9));
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