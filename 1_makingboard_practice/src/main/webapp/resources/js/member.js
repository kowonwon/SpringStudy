$(function() {
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
	
	$("#id").on("keyup", function() {
		var regExp = /[^A-Za-z0-9]/gi;
		if(regExp.text($(this).val())) {
			alert("영문 대소문자, 숫자만 입력할 수 있습니다.");
			$(this).val($(this).val().replace(regExp, ""));
		}
	});
	
	$("#pass1").on("keyup", inputCharReplace);
	$("#pass2").on("keyup", inputCharReplace);
	$("#emailid").on("keyup", inputCharReplace);
	$("#emailDomain").on("keyup", inputEmailDomainReplace);
	
	$("#btnOverlapId").on("click", function() {
		var id = $("#id").val();
		url="overlapIdCheck?id=" + id;
		
		if(id.length == 0) {
			alert("아이디를 입력해주세요.");
			return false;
		}
		
		if(id.length < 5) {
			alert("아이디는 5자 이상 입력해주세요.");
			return false;
		}
		
		window.open(url, "idCheck", "toolbar=no, location=no, status=no, menubar=no, width=400, height=200");
	});
	
	$("#idCheckForm").on("submit", function() {
		var id = $("#checkId").val();
		if(id.length == 0) {
			alert("아이디를 입력해주세요.");
			return false;
		}
		
		if(id.length < 5) {
			alert("아이디는 5자 이상 입력해주세요.");
			return false;
		}
	})
	
	$("#btnIdCheckClose").on("click", function() {
		var id = $(this).attr("data-id-value");
		opener.document.joinForm.id.value = id;
		opener.document.joinForm.isIdCheck.value = true;
		window.close();
	})
});