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
							<th>No</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
						<!-- 게시 글이 있는 경우 -->
						<c:if test="${not empty bList}">
							<c:forEach var="b" items="${bList}">
								<tr>
									<td>${b.no}</td>
									<td><a href="boardDetail?no=${b.no}" class="text-decoration-none link-dark">${b.title}</a></td>
									<td>${b.writer}</td>
									<td>${b.regDate}</td>
									<td>${b.readCount}</td>
								</tr>
							</c:forEach>
						</c:if>
						
						<!-- 게시 글이 없는 경우 -->
						<c:if test="${empty bList}">
							<tr>
								<td colspan="5">게시 글이 존재하지 않음.</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>