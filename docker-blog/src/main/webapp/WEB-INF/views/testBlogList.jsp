<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
	<title>Main</title>
</head>
<body>
<a href="ckEditorWrite">CkEditor 글쓰기</a><br>
<a href="smartEditorWrite">SmartEditor 글쓰기</a><br>
<a href="summernoteWrite">Summernote 글쓰기</a><br>
<h1>
	게시 글 리스트  
</h1>
<c:if test="${ not empty bList }">
<c:forEach var="b" items="${ bList }">
	${ b.title }<br/>
</c:forEach>
</c:if>
</body>
</html>
