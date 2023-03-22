
/**
 * 
 */
function fn_joinMember(){
	
	var joinForm = document.joinForm;
	var id = joinForm.id.value;
	
	if(joinForm.idDuplication.value!="idCheck"){
		alert("아이디 중복체크를 해주세요");
	}else{
		joinForm.method = "post";
		joinForm.action = "${contextPath}/member/memberInsert.do"
	}
	
}
function fn_dbCheckId() {
	var joinForm = document.joinForm;
	var id = joinForm.id.value;
	if(id.length=0 || id==""){
		alert("아이디를 입력해주세요.");
		joinForm.id.focus();
	}else{
		window.open("${contextPath}/member/dbCheckId.do?user_id="+id,"","width=500,height=300");
	}
}
