<%@page import="com.shop.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>수정</title>
</head>
<body>

<h3>수정 정보 검색 </h3>
${error}

<form action="memberSearch.do" method="post">
   ID : <input type="text"  name="id" /><br>
   PWD : <input type="password"  name="nowpasswd" ><br>
  <input type="hidden" name="job" value="update" />
  <input type="submit"  value="검색" />
</form>


<% session.invalidate(); %>
<% MemberVO member = (MemberVO)request.getAttribute("member");
   if(member != null) { %>
		<h3>회원 정보 수정 </h3>
		<form action="memberUpdate.do"  method="post">
			ID : <input type="text"  name="id" readonly  value="${member.id}"  > <br>
			현재 비밀번호 : <input type="password"  name="nowpasswd" ><br>
			변경 비밀번호 : <input type="password"  name="newpasswd" ><br>
			이름 : <input type="text" name="name" value="${member.name}"> <br>
			E-Mail : <input type="text" name="mail" value="${member.mail}"> <br>
			주소 : <input type="text" name="address" value="${member.address}"> <br>
			전화번호 : <input type="text" name="phone" value="${member.phone}"> <br>
			성별 : <input type="radio" name="gender" value="fmale">여성 <input type="radio" name="gender" value="male">남성 <br>
			<input type="submit"  value="수정" >
		</form>

<%}else{ %>
     ${result} <p>

<%} %>

</body>
</html>