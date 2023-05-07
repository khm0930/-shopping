<%@page import="com.shop.vo.ItemVO"%>
<%@page import="com.shop.vo.MemberVO"%>
<%@page import="com.shop.vo.OrderHistoryVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>주문목록</title>

<script> 


</script>
</head>
<body>





	<%  ArrayList<OrderHistoryVO> list = (ArrayList<OrderHistoryVO>) request.getAttribute("list"); 
    if(!list.isEmpty()) {  %>
	<table border="1">
		<tr>
			<th>옷</th>
			<th>가격</th>
			<th>사이즈</th>
			<th>수량</th>
		</tr>
	

		<%   for(int i=0; i<list.size(); i++){   
			       OrderHistoryVO memberitem = list.get(i);   %>

		<tr>
			<td><%=memberitem.getitem_name() %></td>
			<td><%=memberitem.getitem_price() %></td>
			<td><%=memberitem.getitem_size() %></td>
			<td><%=memberitem.getcount() %></td>	
		</tr>
			
		<%   }
       }else{
    	   out.print("<h3>주문목록이 없습니다.</h3>");
       }
	%>
	</table>
	 		<% 	OrderHistoryVO memberitem = list.get(3); %>
			총 금액 : <%=memberitem.gettotal_price() %> <br>
			<button type="button" onclick="fnCalCount('minus');" value='order'>주문하기</button>


</body>
</html>