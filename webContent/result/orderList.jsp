<%@page import="com.shop.vo.OrderVO"%>
<%@page import="com.shop.vo.ItemVO"%>
<%@page import="com.shop.service.ItemService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
<%@ page import="java.util.HashSet" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
<title>쇼핑몰</title>
</head>
<body>


    <form action="order.do" method="post">
        <% String orderId = (String) request.getAttribute("orderId"); %>
        <% Set<Integer> printedItems = new HashSet<>();%>
        <% ArrayList<OrderVO> list = (ArrayList<OrderVO>) request.getAttribute("order"); 
           if (list != null && !list.isEmpty()) { %>
        <table border="1">
            <tr>
                <th>옷</th>
                <th>사이즈</th>
                <th>수량</th>
                <th>총가격</th>
            </tr>
            <% for (OrderVO item : list) {
                Map<Integer, Integer> itemQuantityMap = item.getItemQuantityMap();
                Map<Integer, Integer> itemTotalPriceMap = item.getItemTotalPriceMap();
                for (int itemId : itemQuantityMap.keySet()) {
                    int quantity = itemQuantityMap.get(itemId);
                    if (quantity > 0) { // 수량이 0보다 큰 경우에만 출력
                        ItemVO itemDetails = ItemService.getInstance().getItem(itemId);
                        if (itemDetails != null && !printedItems.contains(itemId)) { // 아이템 정보가 유효한 경우에만 출력
                        	printedItems.add(itemId); //중복되는것은 출력하지 않기
            %>
            <tr>
                <td><%= itemDetails.getName() %></td>
                <td><%= itemDetails.getSize() %></td>
                <td><%= quantity %></td>
                <td><%= itemTotalPriceMap.get(itemId) %>원</td>
            </tr>
            <% } } } } %>
        </table>
        <% } else { %>
        <p>주문 내역이 없습니다.</p>
        <% } %>
        <input type="submit" value="주문하기">
    </form>
</body>
</html>
