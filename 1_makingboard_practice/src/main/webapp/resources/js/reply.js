$(function() {
	
	$(document).on("click", "modifyReply", function() {
		console.log($(this).parents(".replyRow"));
		var $replyRow = $(this).parents(".replyRow");
		
		if($("#replyForm").is(":visible")) {
			var $next = $replyRow.next();
			if(! $next.is("#replyForm")) {
				$("#replyForm").slideUp(300);
			}
			setTimeout(function() {
				$("#replyForm").insertAfter($replyRow).slideDown(300);
			}, 300);
		} else {
			$("#replyForm").removeClass("d-none").css("display", "none").insertAfter($replyRow).slideDown(300);
		}
		
		// id 변경, data-no 속성 읽어오기
		$("#replyForm").find("form").attr({"id": "replyUpdateForm", "data-no": $(this).attr("data-no")});
		$("#replyWirteButton").val("댓글수정");
		
		// 클릭된 수정 버튼이 있는 댓글을 읽어와 수정 폼의 입력란에 출력
		var reply = $(this).parent().parent().next().find("pre").text();
		$("#replyContent").val($.trim(reply));
	})
	
	$(document).on("submit", "#replyUpdateForm", function() {
		if($("#replyContent").val().length <= 5) {
			alert("댓글은 5자 이상");
			return false;
		}
		
		$("#global-content > div").append($("#replyForm").slideUp(300));
		
		var params = $(this).serialize() + "&no=" + $(this).attr("data-no");
		console.log(params);
		
		$.ajax({
			url: "replyUpdate.ajax",
			data: params,
			type: "post",
			dataType: "json",
			success: function(resData, status, xhr) {
				console.log(resData);
				
				$("#replyList").empty();
				$.each(resData, function(i, v) {
					var date = new Date(v.regDate);
					var strDate = date.getFullYear() + "-" + ((date.getMonth() + 1 < 10) ? "0" + (date.getMonth() + 1) : (date.getMonth() + 1)) + "-"
							+ (date.getDate() < 10 ? "0" + date.getDate() : date.getDate()) + " "
							+ (date.getHours() < 10 ? "0" + date.getHours() : date.getHours()) + ":"
							+ (date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes()) + ":"
							+ (date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds());
					var result =
							'<div class="row replyRow border border-top-0">'
							+ '<div class="col">'
							+ '	<!-- 댓글 작성자		수정 삭제 신고 버튼 영역 -->'
							+ '	<div class="row bg-light p-2">'
							+ '		<div class="col-4">'
							+	'			<span>' + v.replyWriter + '</span>'
							+	'		</div>'
							+	'  	<div class="col-8 text-end">'
							+	'			<span class="me-3">' + strDate + '</span>'
							+	'			<button class="modifyReply btn btn-outline-success btn-sm" data-no="' + v.no + '"><i class="bi bi-journal-text"> 수정</i></button>'
							+	'			<button class="deleteReply btn btn-outline-warning btn-sm" data-no="' + v.no + '"><i class="bi bi-trash3"> 삭제</i></button>'
							+	'			<button class="reportReply btn btn-outline-danger btn-sm" data-no="' + v.no + '"><i class="bi bi-megaphone"> 신고</i></button>'
							+	'		</div>'
							+	'	</div>'
							+	'	<!-- 댓글 내용 영역 -->'
							+	'	<div class="row">'
							+	'		<div class="col p-3">'
							+	'			<pre>' + v.replyContent + '</pre>'
							+	'		</div>'
							+	'	</div>'
							+	'</div>'
						+	'</div>';
					
					$("#replyList").append(result);
				})
				
				$("#replyContent").val("");
				
			},
			"error": function(xhr, status) {
				console.log("ajax 실패 : " + status + " " + xhr.status);
			}
		})
		return false;
	})
	
	$("#replyWrite").on("click", function() {
		
		if($("#replyForm").is(":visible")) {
			var $prev = $("#replyTitle").prev();
			if(! $prev.is("#replyForm")) {
				$("#replyForm").slideUp(300);
			}
			setTimeout(function() {
				$("#replyForm").insertBefore("#replyTitle").slideDown(300);
			}, 300);
		} else {
			$("#replyForm").removeClass("d-none").css("display", "none").insertBefore("#replyTitle").slideDown(300);
		}
		
		$("#replyForm").find("form").attr("id", "replyWriteForm").removeAttr("data-no");
		$("#replyContent").val("");
		$("#replyWriteButton").val("댓글쓰기");
	})
	
	$(document).on("submit", "#replyWriteForm", function(e) {
		if($("#replyContent").val().length < 5) {
			alert("댓글은 5자 이상 입력해야 합니다.");
			return false;
		}
		
		var params = $(this).serialize();
		console.log(params);
		
		$.ajax({
			url: "replyWrite.ajax",
			data: params,
			type: "post",
			dataType: "json",
			success: function(resData) {
				console.log(resData);
				
				$("#replyList").empty();
				$.each(resData, function(i, v) {
					var date = new Date(v.regDate);
					var strDate = date.getFullYear() + "-" + ((date.getMonth() + 1 < 10) ? "0" + (date.getMonth() + 1) : (date.getMonth() + 1)) + "-"
							+ (date.getDate() < 10 ? "0" + date.getDate() : date.getDate()) + " "
							+ (date.getHours() < 10 ? "0" + date.getHours() : date.getHours()) + ":"
							+ (date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes()) + ":"
							+ (date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds());
					var result =
							'<div class="row replyRow border border-top-0">'
							+ '<div class="col">'
							+ '	<!-- 댓글 작성자		수정 삭제 신고 버튼 영역 -->'
							+ '	<div class="row bg-light p-2">'
							+ '		<div class="col-4">'
							+	'			<span>' + v.replyWriter + '</span>'
							+	'		</div>'
							+	'  	<div class="col-8 text-end">'
							+	'			<span class="me-3">' + strDate + '</span>'
							+	'			<button class="modifyReply btn btn-outline-success btn-sm" data-no="' + v.no + '"><i class="bi bi-journal-text"> 수정</i></button>'
							+	'			<button class="deleteReply btn btn-outline-warning btn-sm" data-no="' + v.no + '"><i class="bi bi-trash3"> 삭제</i></button>'
							+	'			<button class="reportReply btn btn-outline-danger btn-sm" data-no="' + v.no + '"><i class="bi bi-megaphone"> 신고</i></button>'
							+	'		</div>'
							+	'	</div>'
							+	'	<!-- 댓글 내용 영역 -->'
							+	'	<div class="row">'
							+	'		<div class="col p-3">'
							+	'			<pre>' + v.replyContent + '</pre>'
							+	'		</div>'
							+	'	</div>'
							+	'</div>'
						+	'</div>';
					
					$("#replyList").append(result);
					$("#replyList").removeClass("text-center");
					$("#replyList").removeClass("p-5");
				})
				
				$("#replyForm").slideUp(300).add("#replyContent").val("");
				
			},
			"error": function(xhr, status) {
				console.log("error : " + status);
			}
		})
		return false;
	})
	
	$(".btnCommend").click(function() {
		var com = $(this).attr("id");
		console.log("com : " + com);
		
		$.ajax({
			url: "recommend.ajax",
			type: "post",
			data: {recommend: com, no: $("#no").val()},
			dataType: "json",
			success: function(data) {
				$("#commend > .recommend").text(" (" + data.recommend + ")");
				$("#thank > .recommend").text(" (" + data.thank + ")");
				
				var msg = com == 'commend' ? "추천이" : "땡큐가";
				alert(msg + " 반영되었습니다.");
			},
			error: function(xhr, status, error) {
				alert("error : " + xhr.statusText + ", " + status + ", " + error);
			}
		})
	})
})