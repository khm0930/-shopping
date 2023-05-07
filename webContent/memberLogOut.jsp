<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그아웃</title>
</head>
<body>
	<% session.invalidate(); %>
	<a href="memberlogin.jsp">로그인 하기</a> <p><br>
	<a href="memberInsert.jsp">회원 가입</a> <p>
</body>
</html>