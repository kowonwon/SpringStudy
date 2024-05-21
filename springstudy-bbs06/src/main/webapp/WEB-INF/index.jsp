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
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
	<link rel="stylesheet" type="text/css" href="resources/css/member.css" />		
    <style>      	
    </style>
	<script src="resources/js/jquery-3.2.1.min.js"></script>
	<script src="resources/js/formcheck.js"></script>
	<script src="resources/js/member.js"></script>
</head>
<body>
	<div class="container">
		<%@ include file="template/header.jsp" %>
		<jsp:include page="${ param.body }" />
		<%@ include file="template/footer.jsp" %>
	</div>
    <script src="resources/bootstrap/bootstrap.bundle.min.js"></script>	

	<!-- 로그인 모달 -->
	<div class="modal fade" id="loginModal" tabindex="-1" aria-labelledby="loginModalLabel" aria-hidden="true"
		data-bs-backdrop="static" data-bs-keyboard="false">
		<div class="modal-dialog">
		    <div class="modal-content">
		      	<div class="modal-header bg-primary bg-gradient text-white">
			        <h1 class="modal-title fs-5 fw-bold" id="modalLabel">회원 로그인</h1>
			        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      	</div>
				<form action="login" method="post">
					<div class="modal-body">	        
						<div class="mb-3">
		            		<label for="userId" class="col-form-label fw-bold">아이디 : </label>
		            		<input type="text" class="form-control" id="userId" name="userId">
		          		</div>
			          	<div class="mb-3">
				            <label for="pass" class="col-form-label fw-bold">비밀번호 : </label>
			            	<input type="password" class="form-control" id="pass" name="pass">
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
	
</body>
</html>