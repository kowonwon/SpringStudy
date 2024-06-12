<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<title>Spring MVC Blog</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="resources/favicon/favicon.ico" />
<link rel="stylesheet" type="text/css" href="resources/startbootstrap/bootstrap-styles.css" />
<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/js/formcheck.js"></script>
</head>
<body>
	<%@ include file="template/header.jsp" %>
	<jsp:include page="${ param.body }" />
	<%@ include file="template/footer.jsp" %>

<!-- Bootstrap core JS-->	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>	
</body>
</html>
