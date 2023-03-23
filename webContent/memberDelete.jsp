<%@page import="com.shop.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>삭제</title>
</head>
<body>
<h3>회원 탈퇴 확인 </h3>
${error}
<form action="memberSearch.do" method="post">
   ID : <input type="text"  name="id" /><br>
   PWD : <input type="password"  name="nowpasswd" ><br>
  <input type="submit"  value="확인" />
    <input type="hidden" name="job" value="delete" />
</form>

<% MemberVO member = (MemberVO)request.getAttribute("member");
   if(member != null) { %>
		<h3>검색 정보 결과</h3>
		${member.id } /  ${member.name } / ${member.mail } / ${member.address } / ${member.phone } / ${member.gender } <p>
	
	    <form action="memberDelete.do" method="post">
	       <input type="hidden" name="id"  value="${member.id}" />
	       <input type="submit"  value="탈퇴" />
	    </form>

<%}else{ %>
     ${result} <p>

<%} %>
</body>
</html>