$(function() {

	$("#id").on("keyup", function() {
		
		let regExp = /[^A-Za-z0-9]/gi;
		if(regExp.test($(this).val())) { // 영문 대소문자, 숫자가 아니면
			alert("아이디는 영문 대소문자와 숫자만 가능합니다.");
			$(this).val($(this).val().replace(regExp, ""));
		}
	});
	



	// 모달 로그인 폼
	$("#modalLoginForm").on("submit", function() {
		
		let id = $("#userId").val();
		let pass = $("#pass").val();
		
		if(id.length <= 0) {
			alert("아이디를 입력해주세요");
			$("#userId").focus();
			return false;
		}
		if(pass.length <= 0) {
			alert("비밀번호를 입력해주세요");
			$("#userPass").focus();
			return false;
		}
	});
	
	// 로그인 폼 유효성 검사
	$("#loginForm").on("submit", function() {
		
		let id = $("#userId").val();
		let pass = $("#userPass").val();
		
		if(id.length <= 0) {
			alert("아이디를 입력해주세요");
			$("#userId").focus();
			return false;
		}
		if(pass.length <= 0) {
			alert("비밀번호를 입력해주세요");
			$("#userPass").focus();
			return false;
		}
	});

});