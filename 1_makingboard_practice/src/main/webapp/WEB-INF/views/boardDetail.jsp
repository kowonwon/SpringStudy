<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="resources/js/reply.js"></script>
<script src="resources/js/formcheck.js"></script>
	<div class="row my-5" id="global-content">
		<div class="col">
			<div class="row">
				<div class="col">
					<h2 class="fs-3 fw-bold text-center">게시 글 상세보기</h2>
				</div>
			</div>
			<form name="checkForm" id="checkForm">
				<input type="hidden" name="no" id="no" value="${board.no}" >
				<input type="hidden" name="pass" id="rPass">
				<input type="hidden" name="pageNum" value="${pageNum}">
				<c:if test="${searchOption}">
					<input type="hidden" name="type" value="${type}">
					<input type="hidden" name="keyword" value="${keyword}">
				</c:if>
			</form>
			<div class="row">
				<div class="col">
					<table class="table table-bordered">
						<tbody>
							<tr>
								<th>제 목</th>
								<td colspan="3">${board.title}</td>
							</tr>
							<tr>
								<th>작성자</th>
								<td>${board.writer}</td>
								<th>작성일</th>
								<td><fmt:formatDate value="${board.regDate}" 
									pattern="yyyy-MM-dd HH:mm:ss"/></td>
							</tr>
							<tr>
								<th>비밀번호</th>
								<td><input type="password" class="form-control" id="pass"></td>
								<th>조회수</th>
								<td>${board.readCount}</td>
							</tr>
							<tr>
								<th>파 일</th>
								<td colspan="3">
								<c:if test="${empty board.file1}">
									첨부파일 없음
								</c:if>
								<c:if test="${not empty board.file1}">
									<a href="fileDownload?fileName=${board.file1}">파일 다운로드</a>
								</c:if>	
								</td>
							</tr>
							<tr>								
								<td colspan="4" style="white-space: pre;">${board.content}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="row my-3">
				<div class="col text-center">
					<input type="button" class="btn btn-warning" id="detailUpdate" value="수정하기" >
					&nbsp;&nbsp;
					<input type="button" class="btn btn-danger" id="detailDelete" value="삭제하기" >
					<c:if test="${searchOption}">
						&nbsp;&nbsp;
						<input type="button" class="btn btn-primary" value="목록보기" 
							onclick="location.href='boardList?pageNum=${pageNum}&type=${type}&keyword=${keyword}'">
					</c:if>
					<c:if test="${not searchOption}">
						&nbsp;&nbsp;
						<input type="button" class="btn btn-primary" value="목록보기" 
							onclick="location.href='boardList?pageNum=${pageNum}'">
					</c:if>
				</div>
			</div>
			<!-- 추천/땡큐 영역 -->
			<div class="row my-5">
				<div class="col border p-3">
					<div id="recommend" class="text-end">
					 <span id="commend" class="btnCommend text-primary" style="cursor: pointer;">
					 	<img src="resources/images/recommend.png"/>추천
					 	<span class="recommend">(${board.recommend})</span>
					 </span>|
					 <span id="thank" class="btnCommend text-primary" style="cursor: pointer;">
					 	<img src="resources/images/smile.png"/>땡큐
					 	<span class="recommend">(${board.thank})</span>
					 </span>|
					 <span id="replyWrite" class="text-primary" style="cursor: pointer;">
					 	<i class="bi bi-file-earmark-text-fill" style="color: cornflowerblue;"></i>댓글쓰기
					 </span>
					</div>
				</div>
			</div>
			<!-- 댓글 헤더 영역 -->
			<div class="row" id="replyTitle">
				<div class="col p-2 text-center bg-dark text-white">
					<h3 class="fs-4">댓글</h3>
				</div>
			</div>
			<!-- 댓글 리스트 영역 -->
			<!-- 댓글이 존재하는 경우 -->
			<c:if test="${not empty replyList}">
				<div class="row mb-3">
					<div class="col" id="replyList">
						<c:forEach var="reply" items="${replyList}">
							<div class="replyRow row border border-top-0">
								<div class="col">
									<div class="row bg-light p-2">
										<div class="col-4">
											<span>${reply.replyWriter}</span>
										</div>
										<div class="col-8 text-end">
											<span class="me-3">
												<fmt:formatDate value="${reply.regDate}" pattern="yyyy-MM-dd HH:mm:ss" />
											</span>
											<button class="modifyReply btn btn-outline-success btn-sm" data-no="${reply.no}">
												<i class="bi bi-journal-text">수정</i>
											</button>
											<button class="deleteReply btn btn-outline-warning btn-sm" data-no="${reply.no}">
												<i class="bi bi-trash">삭제</i>
											</button>
											<button class="btn btn-outline-danger btn-sm" onclick="reportReply('${reply.no}')">
												<i class="bi bi-telephone-outbound">신고</i>
											</button>
										</div>
									</div>
									<div class="row">
										<div class="col p-3">
											<pre>${reply.replyContent}</pre>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</c:if>
			<!-- 댓글이 존재하지 않는 경우 -->
			<c:if test="${empty replyList}">
				<div class="row mb-5" id="replyList">
					<div class="col text-center border p-5">
						<div>댓글이 없습니다. sql data를 확인하십시오.</div>
					</div>
				</div>
			</c:if>
			<!-- 댓글 쓰기 폼 -->
			<div class="row my-3 d-none" id="replyForm">
				<div class="col">
					<form name="replyWriteForm" id="replyWriteForm">
						<input type="hidden" name="bbsNo" value="${board.no}"/>
						<input type="hidden" name="replyWriter" value="${sessionScope.member.id}"/>
						<div class="row bg-light my-3 p-3 border">
							<div class="col">
								<div class="row">
									<div class="col text=center">
										<span>악의적인 댓글은 알림 없이 삭제 처리됩니다.</span>
									</div>
								</div>
								<div class="row my-3">
									<div class="col-md-10">
										<textarea name="replyContent" id="replyContent" class="form-control" rows="4"></textarea>
									</div>
									<div class="col-md">
										<input type="submit" value="댓글쓰기" class="btn btn-primary h-100 w-100" id="replyWriteButton">
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>