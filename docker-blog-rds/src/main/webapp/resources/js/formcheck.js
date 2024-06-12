$(function() {

	// 포스트 쓰기 폼 유효성 검사
	$("#postWriteForm").on("submit", function() {
			
		if($("#title").val().length <= 0) {
			alert("제목이 입력되지 않았습니다.\n제목을 입력해주세요");
			$("#title").focus();
			return false;
		}
		if($("#writer").val().length <= 0) {
			alert("작성자가 입력되지 않았습니다.\n작성자를 입력해주세요");
			$("#writer").focus();
			return false;
		}
		if($("#content").val().length <= 0) {
			alert("내용이 입력되지 않았습니다.\n내용을 입력해주세요");
			$("#content").focus();
			return false;
		}
	});
	
	// 블로그 포스트 수정 폼 유효성 검사
	$("#postUpdateForm").on("submit", function() {
		
		if($("#title").val().length <= 0) {
			alert("제목이 입력되지 않았습니다.\n제목을 입력해주세요");
			$("#title").focus();
			return false;
		}
		if($("#writer").val().length <= 0) {
			alert("작성자가 입력되지 않았습니다.\n작성자를 입력해주세요");
			$("#writer").focus();
			return false;
		}
		if($("#content").val().length <= 0) {
			alert("내용이 입력되지 않았습니다.\n내용을 입력해주세요");
			$("#content").focus();
			return false;
		}
	});
	
	// 포스트 상세보기에서 포스트 삭제 요청 처리
	$("#detailDelete").on("click", function() {
	
		var result = confirm("현재 포스트를 삭제하시겠습니까?");
		console.log("deletePost : " + result);
		//return;
		if(result) {
			$("#checkForm").attr("action", "deletePost");
			$("#checkForm").attr("method", "post");
			$("#checkForm").submit();
		}
	});	
});