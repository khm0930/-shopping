<%@page import="com.shop.vo.ItemVO"%>
<%@page import="com.shop.vo.MemberVO"%>
<%@page import="com.shop.vo.MemberItemVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>쇼핑몰</title>

<script> 


</script>
</head>
<body>





	<%  ArrayList<MemberItemVO> list = (ArrayList<MemberItemVO>) request.getAttribute("list"); 
    if(!list.isEmpty()) {  %>
	<table border="1">
		<tr>
			<th>아이디</th>
			<th>옷</th>
			<th>사이즈</th>
			<th>수량</th>
			<th>총금액</th>
		</tr>
	

		<%   for(int i=0; i<list.size(); i++){   
			       MemberItemVO memberitem = list.get(i);   %>

		<tr>
			<td><%=memberitem.getId() %></td>
			<td><%=memberitem.getitem_name() %>원</td>
			<td><%=memberitem.getitem_size() %></td>
			<td><%=memberitem.getcount() %></td>
			<td><%=memberitem.gettotal_price() %></td>		
			<td><%=memberitem.gettotal_price() %></td>	
		</tr>
			
		<%   }
       }else{
    	   out.print("<h3>주문목록이 없습니다.</h3>");
       }
	%>
	</table>
			<button type="button" onclick="fnCalCount('minus');" value='order'>주문하기</button>


</body>
</html>