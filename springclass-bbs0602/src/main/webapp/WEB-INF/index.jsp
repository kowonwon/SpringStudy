<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, inittial-scale=1">
<title>Insert title here</title>
<link href="resources/bootstrap/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<link href="resources/css/member.css" rel="stylesheet">
<script src="resources/js/jquery-3.7.1.min.js"></script>
<script src="resources/js/member.js"></script>
</head>
<body>
	<div class="container">
		<%@ include file="template/header.jsp" %>
		<jsp:include page="${param.body}" />
		<%@ include file="template/footer.jsp" %>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="loginModal" 
		data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" 
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header bg-primary text-white">
	        <h1 class="modal-title fs-5" id="staticBackdropLabel">회원 로그인</h1>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <form action="login" method="post" id="modalLoginForm">
		      <div class="modal-body">
		      	<div class="mb-3">
		      		<label for="userId" class="form-label fw-bold">아이디 : </label>
		      		<input type="text" id="userId" name="userId" class="form-control" >
		      	</div>
		      	<div class="mb-3">
		      		<label for="pass" class="form-label fw-bold">비밀번호 : </label>
		      		<input type="text" id="pass" name="pass" class="form-control" >
		      	</div> 
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
		        <button type="submit" class="btn btn-primary">로그인</button>
		      </div>
	      </form>
	    </div>
	  </div>
	</div>
	
	<script src="resources/bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>