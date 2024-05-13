$(function() {

	// 삭제하기 버튼이 클릭되면
	$("#detailDelete").on("click", function() {
		
		let pass = $("#pass").val();
		if(pass.length <= 0) {
			alert("비밀번호를 입력해 주세요");
			return false;
		}
		
		$("#rPass").val(pass);
		$("#checkForm").attr("action", "delete");
		$("#checkForm").attr("method", "post");
		$("#checkForm").submit();
	});

	// 게시 글 쓰기 폼이 전송될 때 유효성 검사
	$("#updateForm").on("submit", function() {
		if($("#writer").val().length <= 0) {
			alert("작성자를 입력해 주세요");
			$("#writer").focus();
			return false;
		}
		if($("#title").val().length <= 0) {
			alert("제목 입력해 주세요");
			$("#title").focus();
			return false;
		}
		if($("#pass").val().length <= 0) {
			alert("비밀번호를 입력해 주세요");
			$("#pass").focus();
			return false;
		}
		if($("#content").val().length <= 0) {
			alert("게시 글 내용을 입력해 주세요");
			$("#content").focus();
			return false;
		}
	});


	// 수정하기 버튼이 클릭되면
	$("#detailUpdate").on("click", function() {
		
		let pass = $("#pass").val();
		if(pass.length <= 0) {
			alert("비밀번호를 입력해 주세요");
			return false;
		}
		
		$("#rPass").val(pass);
		$("#checkForm").attr("action", "update");
		$("#checkForm").attr("method", "post");
		$("#checkForm").submit();		
	});

	// 게시 글 쓰기 폼이 전송될 때 유효성 검사
	$("#writeForm").on("submit", function() {
		if($("#writer").val().length <= 0) {
			alert("작성자를 입력해 주세요");
			$("#writer").focus();
			return false;
		}
		if($("#title").val().length <= 0) {
			alert("제목 입력해 주세요");
			$("#title").focus();
			return false;
		}
		if($("#pass").val().length <= 0) {
			alert("비밀번호를 입력해 주세요");
			$("#pass").focus();
			return false;
		}
		if($("#content").val().length <= 0) {
			alert("게시 글 내용을 입력해 주세요");
			$("#content").focus();
			return false;
		}
	});
	
 
});