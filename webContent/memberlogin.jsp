<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
<title>생성</title>
</head>
<body>
<h3>민이 쇼핑몰 로그인</h3>

${error}																 <!--오류가 발생하여 현재 페이지로 다시 돌아올때 오류 메시지를 출력하는 코드  -->
<!--입력 뷰  -->
<form action="memberlogin.do"  method="post">							<!-- 폼에서 입력받은 값을 처리할 서버 프로그램으로 memberInsert.do를 지정 요청방식은 post  -->

	
	ID : <input type="text"  name="id" > <br>
	PWD : <input type="password"  name="passwd" ><br>
	<input type="submit"  value="로그인" >

</form>
</body>
</html>