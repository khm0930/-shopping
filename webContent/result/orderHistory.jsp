<%@page import="com.shop.vo.OrderVO"%>
<%@page import="com.shop.vo.ItemVO"%>
<%@page import="com.shop.service.ItemService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
<%@ page import="java.util.HashSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<title>쇼핑몰</title>
</head>
<body>

	<h1>주문 완료</h1>
	<!-- 헤더 추가 -->
	<h2>주문 내역</h2>
	<!-- 헤더 추가 -->
	<%
	ArrayList<OrderVO> list = (ArrayList<OrderVO>) request.getAttribute("list");
	int totalPrice = 0; // 총 금액 변수 선언 및 초기화
	for (int i = 0; i < list.size(); i++) {
		OrderVO order = list.get(i);
		totalPrice = totalPrice+(order.getitemPrice()*order.getCount()); // 총 금액 누적 계산
	}

	if (!list.isEmpty()) {
	%>
	<table border="1">
		<tr>
			<th>주문 날짜</th>
			<th>주문자</th>
			<th>주소</th>
			<th>옷</th>
			<th>사이즈</th>
			<th>가격</th>
			<th>수량</th>
			<th>배송도착날짜</th>
		</tr>


		<%
		for (int i = 0; i < list.size(); i++) {
			OrderVO order = list.get(i);
		%>

		<tr>
			<td><%=order.getOrderDate()%></td>
			<td><%=order.getName()%></td>
			<td><%=order.getAddress()%></td>
			<td><%=order.getitemName()%></td>
			<td><%=order.getitemSize()%></td>
			<td><%=order.getitemPrice()%>원</td>
			<td><%=order.getCount()%></td>
			<td><%=order.getDeliveryDate()%></td>

		</tr>

		<%
		}

		} else {
		out.print("<h3>옷이 없습니다.</h3>");
		}
		%>
	</table>
	<p>
		총 금액: <%=totalPrice%>원
	</p>
	<a href="memberLogOut.jsp">로그아웃</a>
	<p>
	</form>










</body>
</html>