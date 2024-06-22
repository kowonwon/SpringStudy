<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>스프링 게시판</title>
	<link href="resources/bootstrap/bootstrap.min.css" rel="stylesheet" >
  <style>      	
  </style>
	<!-- 포트원 결제 -->
  <script src="https://cdn.portone.io/v2/browser-sdk.js"></script>
</head>
<body>
	<div class="container">
		<%@ include file="template/header.jsp" %>
		<jsp:include page="${ param.body }" />
		<button onclick="requestPayment()">결제하기</button>
		<script type="text/javascript">
			const data = {
		    storeId: 'what?',
		    channelKey: 'what?',
		    orderName: '상품명',
		    totalAmount: 100,
		    currency: 'CURRENCY_KRW',
		    payMethod: 'CARD'
		  };

		  function requestPayment() {
		    const response = await PromiseRejectionEvent.requestPayment(data);
		    console.log(response);
		  }
		</script>
		<%@ include file="template/footer.jsp" %>
	</div>
	<script src="resources/bootstrap/bootstrap.bundle.min.js"></script>
	
	<!-- 포트원 결제 -->
	<script src="resources/js/payment.js"></script>	
</body>
</html>