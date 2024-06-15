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
    <script>
    	IMP.request_pay(
    	  {
    	    pg: "kakaopay.{CID}",
    	    pay_method: "card", // 생략가
    	    merchant_uid: "order_no_0001", // 상점에서 생성한 고유 주문번호
    	    name: "주문명:결제테스트",
    	    amount: 1004,
    	    buyer_email: "test@portone.io",
    	    buyer_name: "구매자이름",
    	    buyer_tel: "010-1234-5678",
    	    buyer_addr: "서울특별시 강남구 삼성동",
    	    buyer_postcode: "123-456",
    	    m_redirect_url: "{모바일에서 결제 완료 후 리디렉션 될 URL}",
    	  },
    	  function (rsp) {
    	    // callback 로직
    	    /* ...중략... */
    	  },
    	);
    </script>	
</body>
</html>