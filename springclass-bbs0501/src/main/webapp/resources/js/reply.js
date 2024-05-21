$(function() {
	
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