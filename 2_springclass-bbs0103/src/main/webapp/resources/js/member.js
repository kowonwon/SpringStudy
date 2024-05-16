$(function() {
	
	// 모달 로그인 폼
$("#modalLoginForm").on("submit", function() {
		
		let id = $("#userId").val();
		let pass = $("#pass").val();
		
		if(id.length <= 0) {
			alert("아이디를 입력해주세요.");
			$("#userId").focus();
			return false;
		}
		if(pass.length <= 0) {
			alert("비밀번호를 입력해주세요.");
			$("#userPass").focus();
			return false;
		}
	});
	
	$("#loginForm").on("submit", function() {
		
		let id = $("#userId").val();
		let pass = $("#userPass").val();
		
		if(pass.length <= 0) {
			alert("비밀번호를 입력해주세요.");
			$("#userPass").focus();
			return false;
		}
		if(pass.length <= 0) {
			alert("비밀번호를 입력해주세요.");
			$("#userPass").focus();
			return false;
		}
	});
});