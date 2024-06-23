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
</head>
<body>
	<div class="container">
		<%@ include file="template/header.jsp" %>
		<jsp:include page="${ param.body }" />
		<button>결제하기</button>
		<%@ include file="template/footer.jsp" %>
	</div>
	<script src="resources/bootstrap/bootstrap.bundle.min.js"></script>
  <script src="https://cdn.iamport.kr/v1/iamport.js"></script>
	<script src="resources/js/payment2.js"></script>
</body>
</html>