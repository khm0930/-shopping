<%@page import="com.shop.vo.ItemVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
<title>쇼핑몰</title>
</head>
<body>

<%  ArrayList<ItemVO> list = (ArrayList<ItemVO>) request.getAttribute("list"); 
    if(!list.isEmpty()) {  %>
	   <table border="1">
	   		<tr><th>옷</th><th>가격</th><th>사이즈</th></tr>
			
			<%   for(int i=0; i<list.size(); i++){   
			       ItemVO item = list.get(i);   %>
			       
			        <tr>
			        	<td><%=item.getName() %></td>
			            <td><%=item.getPrice() %>원</td>
			            <td><%=item.getSize() %></td>
			            
			           
			       </tr>
			       
			<%   }
       }else{
    	   out.print("<h3>옷이 없습니다.</h3>");
       }
	%>
	  </table>


</body>
</html>