<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
<title>생성</title>
</head>
<body>
<h3>회원 가입</h3>

${error}																 <!--오류가 발생하여 현재 페이지로 다시 돌아올때 오류 메시지를 출력하는 코드  -->
<!--입력 뷰  -->
<form action="memberInsert.do"  method="post">							<!-- 폼에서 입력받은 값을 처리할 서버 프로그램으로 memberInsert.do를 지정 요청방식은 post  -->

	ID : <input type="text"  name="id" > <br>
	비밀번호 : <input type="password"  name="passwd" ><br>
	이름 : <input type="text" name="name"> <br>
	E-Mail : <input type="text" name="mail" > <br>
	주소 : <input type="text" name="address" > <br>
	전화번호 : <input type="text" name="phone" > <br>
	성별 : <input type="radio" name="gender" value=female>여성 <input type="radio" name="gender" value=male>남성 <br>
	<input type="submit"  value="가입" >

</form>
</body>
</html>