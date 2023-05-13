<%@page import="com.shop.vo.ItemVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>쇼핑몰</title>

<script> 

function fnCalCount(type){
	const resultElement = document.getElementById('result');
	let number = resultElement.innerText;
	
	 if(type === 'plus') {
		    number = parseInt(number) + 1;
		  }else if(type === 'minus'&& number > 0)  {
		    number = parseInt(number) - 1;
		  }
		  
		  // 결과 출력
		  resultElement.innerText = number;
}

</script>
</head>
<body>


	<form action="order.do"  method="post"">
	  <input type="text" name="name" placeholder="이름">
    <input type="text" name="address" placeholder="주소">
    <input type="text" name="phone" placeholder="전화번호">
<% String orderId = (String) request.getAttribute("orderId"); %>

	<%  ArrayList<ItemVO> list = (ArrayList<ItemVO>) request.getAttribute("list"); 
    if(!list.isEmpty()) {  %>
	<table border="1">
		<tr>
			<th>옷</th>
			<th>가격</th>
			<th>사이즈</th>
			<th>수량</th>
		</tr>
	

		<%   for(int i=0; i<list.size(); i++){   
			       ItemVO item = list.get(i);   %>

		<tr>
			<td><%=item.getName() %></td>
			<td><%=item.getPrice() %>원</td>
			<td><%=item.getSize() %></td>
			
			<td>
				<input type = "number" name = "count_<%= item.getItem_id() %>" min = "0" max="10" step="0" value = "0">
				<input type="hidden" name="item_id_<%= item.getItem_id() %>" value="<%= item.getItem_id() %>">
			</td>
			
		</tr>
			
		<%   }
       }else{
    	   out.print("<h3>옷이 없습니다.</h3>");
       }
	%>
	</table>
	
		<input type="submit"  value="장바구니 담기" >
	</form>










</body>
</html>