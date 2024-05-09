<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- content -->
<div class="row my-5" id="global-content">
	<div class="col">
		<div class="row text-center">
			<div class="col">
				<h2 class="fs-3 fw-bold">게시 글 리스트</h2>
			</div>
		</div>  		
		<form name="searchForm" id="searchForm" action="#" 
			class="row justify-content-center my-3">
			<div class="col-auto">
				<select name="type" class="form-select">
					<option value="title">제목</option>
					<option value="writer">작성자</option>
					<option value="content">내용</option>
				</select>
			</div>
			<div class="col-4">
				<input type="text" name="keyword" class="form-control"/>
			</div>
			<div class="col-auto">
				<input type="submit" value="검 색" class="btn btn-primary"/>
			</div>
		</form>  		
		<div class="row">
			<div class="col text-end">
				<a href="writeForm" class="btn btn-outline-success">글쓰기</a>
			</div>
		</div>
		<div class="row my-3">
			<div class="col">
				<table class="table table-hover">
					<thead>
						<tr class="table-dark">
							<th>NO</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>조회수</th>
						</tr>		
					</thead>
					<tbody class="text-secondary">
						<!-- 게시 글이 있는 경우 -->
					<c:if test="${ not empty boardList }">
						<c:forEach var="b" items="${boardList}" varStatus="status">
						<tr>
							<td>${ b.no }</td>
							<td><a href="boardDetail?no=${b.no}&pageNum=${currentPage}" 
								class="text-decoration-none link-secondary">${ b.title }</a></td>
							<td>${ b.writer }</td>
							<td>${ b.regDate }</td>
							<td>${ b.readCount }</td>
						</tr>
						</c:forEach>
					</c:if>
					<!-- 게시 글이 없는 경우 -->
					<c:if test="${ empty boardList }">
						<tr>
							<td colspan="5" class="text-center">게시 글이 존재하지 않습니다.</td>
						</tr>
					</c:if>
					</tbody>					
				</table>
			</div>			
		</div>
		<div class="row">
			<div class="col">
				<nav aria-label="Page navigation">
				  <ul class="pagination justify-content-center">
				  	<%--
					/* 현재 페이지 그룹의 시작 페이지가 pageGroup보다 크다는 것은
					 * 이전 페이지 그룹이 존재한다는 것으로 현재 페이지 그룹의 시작 페이지에
					 * pageGroup을 마이너스 하여 링크를 설정하면 이전 페이지 그룹의
					 * startPage로 이동할 수 있다.
				 	 **/
				 	 --%>
				  	<c:if test="${ startPage > pageGroup }">
					    <li class="page-item">
					      <a class="page-link" href="boardList?pageNum=${ startPage - pageGroup }">Pre</a>
					    </li>
				    </c:if>
					<%--
					/* 현재 페이지 그룹의 startPage 부터 endPage 만큼 반복하면서
				 	 * 현재 페이지와 같은 그룹에 속한 페이지를 출력하고 링크를 설정한다.
				 	 * 현재 페이지는 링크를 설정하지 않는다.
				 	 **/
				 	--%>				    
				    <c:forEach var="i" begin="${startPage}" end="${endPage}">
				    	<c:if test="${i == currentPage }">
					    	<li class="page-item active" aria-current="page">
					    		<span class="page-link">${i}</span>
					    	</li>
				    	</c:if>
				    	<c:if test="${i != currentPage }">
					    	<li class="page-item"><a class="page-link" href="boardList?pageNum=${ i }">${i}</a></li>
					    </c:if>					    
				    </c:forEach>
				    
				    <%-- 
					/* 현재 페이지 그룹의 마지막 페이지가 전체 페이지 보다 작다는 것은
					 * 다음 페이지 그룹이 존재한다는 것으로 현재 페이지 그룹의 시작 페이지에
					 * pageGroup을 플러스 하여 링크를 설정하면 다음 페이지 그룹의
					 * startPage로 이동할 수 있다.
				 	 **/
				 	 --%>
					<c:if test="${ endPage < pageCount }">
					    <li class="page-item">
					      <a class="page-link" href="boardList?pageNum=${ startPage + pageGroup }">Next</a>
					    </li>
				  	</c:if>
				  </ul>
				</nav>
			</div>
		</div>
	</div>
</div>
