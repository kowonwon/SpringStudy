$(function() {
	
	//댓글 삭제 버튼이 클릭되면
	$(document).on("click", ".deleteReply", function() {
			
		$("#replyForm").slideUp(300);
		$("#global-content > div").append($("#replyForm"));
		
		let no = $(this).attr("data-no");
		let writer = $(this).parent().prev().find("span").text();
		let bbsNo = $("#replyForm input[name=bbsNo]").val();
		let params = "no=" + no + "&bbsNo=" + bbsNo;
		console.log(params);
		let result = confirm(writer + "님이 작성한 " + no + "번 댓글을 삭제하시겠습니까?");
		
		if(result) {
			$.ajax({
				url: "replyDelete.ajax",
				type: "post",
				data: params,
				dataType: "json",
				success: function(resData) {
					console.log(resData);
					
				// 기존 댓글 삭제
					$("#replyList").empty();
					
					$.each(resData, function(i, v) {
						
						let date = new Date(v.regDate);
						// 2024-05-21 10:52:57
						let strDate = date.getFullYear() + "-"
						+ ((date.getMonth() + 1) < 10 ? "0" + (date.getMonth() + 1) : "0" + (date.getMonth() + 1)) + "-"
						+ (date.getDate() < 10 ? "0" + date.getDate() : date.getDate()) + " "
						+ (date.getHours() < 10 ? "0" + date.getHours() : date.getHours()) + ":"
						+ (date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes()) + ":"
						+ (date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds());
						
						let result =
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
						
					});
				},
				error: function() {
					console.log("error");
				}
			});
		}
		return false;
	});
	
	// 댓글 수정 폼이 서브밋 될 때
	//$("#replyUpdateForm").on("submit", function() {
	$(document).on("submit", "#replyUpdateForm", function() {
			// 댓글은 5자 이상
			if($("#replyContent").val().length < 5) {
				alert("댓글은 5자 이상이어야 합니다.");
				return false;
			}
			
			// bbsNo=10&replyWriter=admin&replyContent=1111111&no=2
			let params = $(this).serialize() + "&no=" + $(this).attr("data-no");
			console.log(params);
			$("#replyForm").slideUp(300);
			$("#global-content > div").append($("#replyForm"));
			
			$.ajax({
				url: "replyUpdate.ajax",
				type: "post",
				data: params,
				dataType: "json",
				success: function(resData) {
					console.log(resData);
					
				// 기존 댓글 삭제
					$("#replyList").empty();
					
					$.each(resData, function(i, v) {
						
						let date = new Date(v.regDate);
						// 2024-05-21 10:52:57
						let strDate = date.getFullYear() + "-"
						+ ((date.getMonth() + 1) < 10 ? "0" + (date.getMonth() + 1) : "0" + (date.getMonth() + 1)) + "-"
						+ (date.getDate() < 10 ? "0" + date.getDate() : date.getDate()) + " "
						+ (date.getHours() < 10 ? "0" + date.getHours() : date.getHours()) + ":"
						+ (date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes()) + ":"
						+ (date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds());
						
						let result =
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
						
					});
					
					$("#replyForm").slideUp(300).add("#replyContent").val("");
					
				},
				error: function() {
					console.log("error");
				}
			});
			
			return false;
	});
	
	// (임시)타이머 기능
	let timer;
	let clockStart = function() {
		timer = setInterval(function() {
			let date = new Date();
			$("#time").text("현재 시각 : " + date.toLocaleTimeString());
		}, 1000);
	}
	
	setTimeout(function() {
		clockStart();
	}, 1000);
	
	$("#stop").on("click", function() {
		clearInterval(timer);
	});
	
	$("#start").on("click", function() {
		clockStart();
	});
	
	// 댓글 쓰기 폼이 서브밋 될 때
	//$("#replyWriteForm").on("submit", function() {
	$(document).on("submit", "#replyWriteForm", function(e) {
		// 댓글은 5자 이상
		if($("#replyContent").val().length < 5) {
			alert("댓글은 5자 이상이어야 합니다.");
			return false;
		}
		
		let params = $(this).serialize();
		// let p = "replyWriter=" + $("replyWriter").val() + "&bbsNo=" + $("#bbsNo").val();
		console.log("params : " + params);
		
		$.ajax({
			url: "replyWrite.ajax",
			type: "post",
			data: params,
			dataType: "json",
			success: function(resData) {
				console.log(resData);
				
				// 기존 댓글 삭제
				$("#replyList").empty();
				
				$.each(resData, function(i, v) {
					
					let date = new Date(v.regDate);
					// 2024-05-21 10:52:57
					let strDate = date.getFullYear() + "-"
					+ ((date.getMonth() + 1) < 10 ? "0" + (date.getMonth() + 1) : "0" + (date.getMonth() + 1)) + "-"
					+ (date.getDate() < 10 ? "0" + date.getDate() : date.getDate()) + " "
					+ (date.getHours() < 10 ? "0" + date.getHours() : date.getHours()) + ":"
					+ (date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes()) + ":"
					+ (date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds());
					
					let result =
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
					
				});
				
				$("#replyForm").slideUp(300).add("#replyContent").val("");
			},
			error: function(xhr, status) {
				console.log("error : " + status);
			}
		});
		
		// ajax니까 폼 전송하면 안됨.
		return false;
	});
	
	// 댓글쓰기 버튼이 클릭되면
	$("#replyWrite").on("click", function() {
		
		// let dis = $("#replyForm").css("display");
		// console.log("dis : " + dis);
		
		if($("#replyForm").is(":visible")) { // 댓글쓰기 폼이 화면에 보일 때
			
			// 현재 댓글 쓰기 위치에 있는지
			// document.querySelector("#replyTitle").previousSibling();
			let $prev = $("#replyTitle").prev();
			if(!$prev.is("#replyForm")) {
				$("#replyForm").slideUp(300);
			}
			
			setTimeout(function() {
				$("#replyForm").insertBefore("#replyTitle").slideDown(300);
			}, 300);
			
		} else { // 화면에 보이지 않을 때
			$("#replyForm").removeClass("d-none").css("display", "none").insertBefore("#replyTitle").slideDown(300);
		}
		
		$("#replyForm").find("form").attr("id", "replyWriteForm").removeAttr("data-no");
		$("#replyWriteButton").val("댓글쓰기");
		$("#replyContent").val("");
		
	});
	
	// 수정 버튼이 클릭되면 - 이벤트 위임(Event Delegation)
	// document.qeurySelector(".modifyReply").addEventListener("click", function() {});
	// $(".modifyReply").on("click", function() {
	// document.qeurySelector(("#replyList").addEventListener("click", function(e)) {
	$(document).on("click", ".modifyReply", function(e) {
		console.log(e.target);
		let $replyRow = $(this).parents(".replyRow");
		
		if($("#replyForm").is(":visible")) { // 댓글 폼이 화면에 보이는 상태라면
			
			// 버튼이 클릭된 버튼이 속한 댓글을 수정하는 위치인지 판단
			let $next = $replyRow.next();
			if(! $next.is("#replyForm")) { // 현재 클릭된 버튼의 위치가 아니라면
				$("#replyForm").slideUp(300);
			}
			
			setTimeout(function() {
				$("#replyForm").insertAfter("#replyRow").slideDown(300);
			}, 300);
		} else { // 댓글 폼이 화면에 보이지 않는 상태라면
			$("#replyForm").removeClass("d-none").css("display", "none").insertAfter($replyRow).slideDown(300);
		}
		
		$("#replyForm").find("form").attr({"id": "replyUpdateForm", "data-no": $(this).attr("data-no")});
		$("#replyWriteButton").val("댓글수정");
		
		let reply = $(this).parent().parent().next().find("pre").text();
		$("#replyContent").val($.trim(reply));
		
	});
	
	// 추천/땡큐가 클릭되면
	$(".btnCommend").click(function() {
		let com = $(this).attr("id");
		console.log("com : " + com);
		
		$.ajax({
			url: "recommend.ajax",
			type: "post", // recommend=thank&no=10
			data: {recommend: com, no: $("#no").val()},
			dataType: "json",
			success: function(data) {
				console.log(data);
				$("#commend > .recommend").text("(" + data.recommend + ")");
				$("#thank > .recommend").text("(" + data.thank + ")");
				
				let msg = com == 'commend' ? '추천이' : '땡큐가';
				alert(msg + " 반영 되었습니다.");
			},
			error: function(xhr, status, error) {
				console.log("error : " + xhr.statusText + ", " + status + ", " + error);
			}
		});
	});
});