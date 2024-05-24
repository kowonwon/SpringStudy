<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>인덱스</title>
<link href="resources/bootstrap/bootstrap.min.css" rel="stylesheet">
<script src="resources/js/jquery-3.7.1.min.js"></script>
</head>
<body>
	<div class="row container">
		<div class="col">
			<%@ include file="template/side.jsp" %>
		</div>
		<div class="col">
			<jsp:include page="${param.body}" />
		</div>
		<%@ include file="template/footer.jsp" %>
	</div>
	<script src="resources/bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>