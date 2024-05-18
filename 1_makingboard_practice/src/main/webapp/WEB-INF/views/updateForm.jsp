<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="resources/js/formcheck.js"></script>
<!-- content -->
<div class="row my-5" id="global-content">
	<div class="col">
		<div class="row text-center">
			<div class="col">
				<h2 class="fs-3 fw-bold">게시 글 수정하기</h2>
			</div>
		</div>  
		<form name="updateForm" action="updateProcess" id="updateForm" 
			class="row g-3 border-primary" method="post">
			<input type="hidden" name="no" value="${board.no}">
			<input type="hidden" name="pageNum" value="${pageNum}" />
			<c:if test="${searchOption}">
				<input type="hidden" name="pageNum" value="${type}" />
				<input type="hidden" name="pageNum" value="${keyword}" />
			</c:if>
	  	<div class="col-4 offset-md-2">
		    <label for="writer" class="form-label">글쓴이</label>
		    <input type="text" class="form-control" name="writer"  id="writer" 
		    	readonly placeholder="작성자를 입력해 주세요" value="${board.writer}">
  		</div>
  		<div class="col-4 ">
		    <label for="pass" class="form-label">비밀번호</label>
		    <input type="password" class="form-control" name="pass"  id="pass" >
  		</div>
  		<div class="col-8 offset-md-2">
		    <label for="title" class="form-label">제 목</label>
		    <input type="text" class="form-control" name="title"  id="title" value="${board.title}">
  		</div>
			<div class="col-8 offset-md-2">
			    <label for="content" class="form-label">내 용</label>
			    <textarea class="form-control" name="content" id="content" rows="10">${board.content}</textarea>
	  		</div>	
	  		<div class="col-8 offset-md-2 text-center mt-5">
			   <input type="submit" value="수정하기" class="btn btn-primary"/>
			   <c:if test="${not searchOption}">
						&nbsp;&nbsp;<input type="button" value="목록보기" 
							onclick="location.href='boardList?pageNum=${pageNum}'" class="btn btn-primary"/>
			   </c:if>
			   <c:if test="${ searchOption}">
						&nbsp;&nbsp;<input type="button" value="목록보기" 
							onclick="location.href='boardList?pageNum=${pageNum}&type=${type}&keyword=${keyword}'" class="btn btn-primary"/>
			   </c:if>
	  		</div>	
		</form>
	</div>
</div>    
    
    