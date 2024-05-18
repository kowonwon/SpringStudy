<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>		
<!-- content -->
<div class="row my-5" id="global-content">
	<div class="col">
		<div class="row">
			<div class="col">
				<h2 class="fs-3 fw-bold text-center">게시 글 리스트</h2>
			</div>
		</div>
		<!-- 검색 폼 -->
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
				<input type="text" name="keyword" class="form-control" />
			</div>
			<div class="col-auto">
				<input type="submit" value="검색" class="btn btn-primary" />
			</div>
		</form>
		<c:if test="${searchOption}">
			<div class="row my-3">
				<div class="col text-center">
					"${keyword}" 검색 결과
				</div>
			</div>
			<div class="row my-3">
				<div class="col-6">
					<a href="boardList" class="btn btn-outline-success">리스트</a>
				</div>
				<div class="col-6 text-end">
					<a href="boardList" class="btn btn-outline-success">글쓰기</a>
				</div>
			</div>
		</c:if>
		<c:if test="${not searchOption}">
			<div class="row my-3">
				<div class="col text-end">
					<a href="writeForm" class="btn btn-outline-success">글쓰기</a>
				</div>
			</div>
		</c:if>
		<div class="row my-3">
			<div class="col">
				<table class="table table-hover">
					<thead>
						<tr class="table-dark">
							<th>No</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
						<!-- 검색 + 게시 글 있음 -->
						<c:if test="${searchOption and not empty bList}">
							<c:forEach var="b" items="${bList}" varStatus="status">
								<tr>
									<td>${b.no}</td>
									<td><a href="boardDetail?no=${b.no}&pageNum=${currentPage}&type=${type}&keyword=${keyword}"
									class="text-decoration-none link-dark">${b.title}</a></td>
									<td>${b.writer}</td>
									<td>${b.regDate}</td>
									<td>${b.readCount}</td>
								</tr>
							</c:forEach>
						</c:if>
					
						<!-- 검색 x + 게시 글이 있는 경우 -->
						<c:if test="${not searchOption and not empty bList}">
							<c:forEach var="b" items="${bList}">
								<tr>
									<td>${b.no}</td>
									<td><a href="boardDetail?no=${b.no}&pageNum=${currentPage}"
									class="text-decoration-none link-dark">${b.title}</a></td>
									<td>${b.writer}</td>
									<td>${b.regDate}</td>
									<td>${b.readCount}</td>
								</tr>
							</c:forEach>
						</c:if>
						
						<!-- 검색 o + 게시 글이 없는 경우 -->
						<c:if test="${searchOption and empty bList}">
							<tr>
								<td colspan="5">"${keyword}"가 포함된 게시 글이 존재하지 않음.</td>
							</tr>
						</c:if>
						
						<!-- 검색 x + 게시 글이 없는 경우 -->
						<c:if test="${not searchOption and empty bList}">
							<tr>
								<td colspan="5">게시 글이 존재하지 않음.</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
		
		<!-- 검색 o + 리스트 o 페이지네이션 -->
		<c:if test="${searchOption and not empty bList}">
			<div class="row">
				<div class="col">
					<nav>
					  <ul class="pagination justify-content-center">
						  <c:if test="${startPage > pageGroup}">
						    <li class="page-item">
						    	<a class="page-link" href="boardList?pageNum=${startPage - pageGroup}&type=${type}&keyword=${keyword}">Pre</a>
						    </li>
						  </c:if>
						  <c:forEach var="i" begin="${startPage}" end="${endPage}">
						  	<c:if test="${i == currentPage}">
						    	<li class="page-item active">
						    		<span class="page-link">${i}</span>
						    	</li>
						  	</c:if>
						  	<c:if test="${i != currentPage}">
							    <li class="page-item">
							    	<a class="page-link" href="boardList?pageNum=${i}&type=${type}&keyword=${keyword}">${i}</a>
							    </li>
						  	</c:if>
						  </c:forEach>
						  <c:if test="${endPage < pageCount}">
						  	<li class="page-item">
						  		<a class="page-link" href="boardList?pageNum=${startPage + pageGroup}&type=${type}&keyword=${keyword}">Next</a>
						  	</li>
						  </c:if>
					  </ul>
					</nav>
				</div>
			</div>
		</c:if>
		
		<!-- 검색x + 리스트o 페이지네이션 -->
		<c:if test="${not searchOption and not empty bList}">
			<div class="row">
				<div class="col">
					<nav>
					  <ul class="pagination justify-content-center">
						  <c:if test="${startPage > pageGroup}">
						    <li class="page-item">
						    	<a class="page-link" href="boardList?pageNum=${startPage - pageGroup}">Pre</a>
						    </li>
						  </c:if>
						  <c:forEach var="i" begin="${startPage}" end="${endPage}">
						  	<c:if test="${i == currentPage}">
						    	<li class="page-item active">
						    		<span class="page-link">${i}</span>
						    	</li>
						  	</c:if>
						  	<c:if test="${i != currentPage}">
							    <li class="page-item">
							    	<a class="page-link" href="boardList?pageNum=${i}">${i}</a>
							    </li>
						  	</c:if>
						  </c:forEach>
						  <c:if test="${endPage < pageCount}">
						  	<li class="page-item">
						  		<a class="page-link" href="boardList?pageNum=${startPage + pageGroup}">Next</a>
						  	</li>
						  </c:if>
					  </ul>
					</nav>
				</div>
			</div>
		</c:if>
	</div>
</div>