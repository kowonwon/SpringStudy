<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Spring MVC 게시판</title>
	<!--
		BoardController에서 클래스 레벨에 @RequestMapping 애노테이션을 사용해
		별도로 경로 매핑을 하지 않고 boardList(Model model) 메서드에만
		@RequestMapping({"/boardList", "/list"}) 애노테이션으로 요청 매핑을 설정했다.
		그리고 WEB-INF/spring/appServlet/servlet-context.xml에서 정적 리소스와
		관련된 url 맵핑을 아래와 같이 설정했기 때문에
		<mvc:resources mapping="/resources/**" location="/resources/" />		
		css의 위치를 "resources/css/index.css"와 같이 지정해야 한다.
	 
		브라우저 주소 표시줄에 http://localhost:8080/springstudy-bbs01/list
		또는 http://localhost:8080/springstudy-bbs01/boardList 등으로
		표시되므로 css 디렉터리는 ContextRoot/resources/css에 위치하기 때문에 현재
		위치를 기준으로 상대 참조 방식으로 "resources/css/index.css"를 지정해야 한다.
	-->	
	<title>스프링 게시판</title>
	<link href="resources/bootstrap/bootstrap.min.css" rel="stylesheet" >
    <style>      	
    </style>
	<script src="resources/js/jquery-3.2.1.min.js"></script>
	<!-- jQuery -->
	<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<!-- iamport.payment.js -->
	<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-{SDK-최신버전}.js"></script>
</head>
<body>
	<div class="container">
		<%@ include file="template/header.jsp" %>
		<jsp:include page="${ param.body }" />
		<button id="payButton">카카오페이 결제</button>
		<%@ include file="template/footer.jsp" %>
	</div>
    <script src="resources/bootstrap/bootstrap.bundle.min.js"></script>
    
    <!-- 결제 기능 -->
    <script>
    $("#check_module").click(function () {
  		var IMP = window.IMP; // 생략가능
  		IMP.init('가맹점식별코드'); 
  		// i'mport 관리자 페이지 -> 내정보 -> 가맹점식별코드
  		// ''안에 띄어쓰기 없이 가맹점 식별코드를 붙여넣어주세요. 안그러면 결제창이 안뜹니다.
  		IMP.request_pay({
  			pg: 'kakao',
  			pay_method: 'card',
  			merchant_uid: 'merchant_' + new Date().getTime(),
  			/* 
  			 *  merchant_uid에 경우 
  			 *  https://docs.iamport.kr/implementation/payment
  			 *  위에 url에 따라가시면 넣을 수 있는 방법이 있습니다.
  			 */
  			name: '주문명 : 아메리카노',
  			// 결제창에서 보여질 이름
  			// name: '주문명 : ${auction.a_title}',
  			// 위와같이 model에 담은 정보를 넣어 쓸수도 있습니다.
  			amount: 2000,
  			// amount: ${bid.b_bid},
  			// 가격 
  			buyer_name: '이름',
  			// 구매자 이름, 구매자 정보도 model값으로 바꿀 수 있습니다.
  			// 구매자 정보에 여러가지도 있으므로, 자세한 내용은 맨 위 링크를 참고해주세요.
  			buyer_postcode: '123-456',
  			}, function (rsp) {
  				console.log(rsp);
  			if (rsp.success) {
  				var msg = '결제가 완료되었습니다.';
  				msg += '결제 금액 : ' + rsp.paid_amount;
  				// success.submit();
  				// 결제 성공 시 정보를 넘겨줘야한다면 body에 form을 만든 뒤 위의 코드를 사용하는 방법이 있습니다.
  				// 자세한 설명은 구글링으로 보시는게 좋습니다.
  			} else {
  				var msg = '결제에 실패하였습니다.';
  				msg += '에러내용 : ' + rsp.error_msg;
  			}
  			alert(msg);
  		});
  	});
    </script>	
</body>
</html>