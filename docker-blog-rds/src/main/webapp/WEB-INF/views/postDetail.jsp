<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
<!-- 미국식 날짜 표현을 위한 로케일 설정 -->
<fmt:setLocale value="en_US"/>

<form name="checkForm" id="checkForm">
	<input type="hidden" name="postNo" id="postNo" value="${ post.postNo }"/>	
</form>
    
<!-- Page content-->
<div class="container mt-5">
    <div class="row">
        <div class="col-lg-8">
            <!-- Post content-->
            <article>
                <!-- Post header-->
                <header class="mb-4">
                    <!-- Post title-->
                    <h1 class="fw-bolder mb-1">${post.title}</h1>
                    <!-- Post meta content-->                    
                    <fmt:formatDate value="${post.modDate}" pattern="MMMM d, yyyy"/>
                    <div class="text-muted fst-italic mb-2">
                    	Posted on January 1, 2022 by ${post.writer}</div>
                    <!-- Post categories-->
                    <a class="badge bg-secondary text-decoration-none link-light" href="#!">
                    	Web Design</a>
                    <a class="badge bg-secondary text-decoration-none link-light" href="#!">
                    Freebies</a>
                </header>
                <!-- Preview image figure-->
                <figure class="mb-4">
                	<!-- 메인 이미지가 있으면 그 이미지를 출력 -->
                	<c:if test="${ not empty post.mainImg }">
                		<img class="img-fluid rounded" 
                			src="resources/postMainImg/${ post.mainImg }" alt="..." />
                	</c:if>
                	<!-- 메인 이미지가 없으면 랜덤 이미지 출력  -->
                	<c:if test="${ empty post.mainImg }">
                		<img class="img-fluid rounded" 
                			src="https://picsum.photos/900/400" alt="..." />
                	</c:if>	
                </figure>
                <!-- Post content-->
                <section class="mb-5">
                    <p class="fs-5 mb-4"><pre>${post.content}</pre></p>                   
                    <h2 class="fw-bolder mb-4 mt-5">I have odd cosmic thoughts every day</h2>
                    <p class="fs-5 mb-4">For me, the most fascinating interface is Twitter</p>                    
                </section>
            </article>
            <!-- Comments section-->
            <section class="mb-5">
                <div class="card bg-light">
                    <div class="card-body">
                        <!-- Comment form-->
                        <form class="mb-4">
                        	<textarea class="form-control" rows="3" 
                        		placeholder="포스트를 읽고 궁금한 점은 댓글을 남겨 주세요"></textarea>                        	
                        </form>
                        <!-- 댓글이 있는 경우-->
                        <c:if test="${ not empty postReplyList }">
                        <c:forEach var="reply" items="${ postReplyList }">
                        <div class="d-flex" style="padding: 10px 3px;">
                            <div class="flex-shrink-0">
                            	<img class="rounded-circle" 
                            		src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="..." />
                            </div>
                            <div class="ms-3">
                                <div class="fw-bold">${ reply.writer }</div>
                                	${ reply.content }
                            </div>
                        </div>
                        </c:forEach>
                        </c:if>
                        <!-- 댓글이 없는 경우 -->                        
                        <c:if test="${ empty postReplyList }">
                        <div class="d-flex">
                            <div class="flex-shrink-0">
                            	<img class="rounded-circle" 
                            		src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="..." />
                            	</div>
                            <div class="ms-3">
                                <div class="fw-bold">댓글 없음</div>
                                댓글을 달아 주세요                                
                            </div>
                        </div>
                        </c:if>                        
                    </div>
                </div>
            </section>
            <div class="row m-5">
        		<div class="d-grid gap-3 d-md-flex justify-content-md-center">
				  <button class="btn btn-primary" type="button"
				  	onclick="location.href='postUpdateForm?postNo=${post.postNo}'">
				  	수정하기</button>
				  <button class="btn btn-primary" type="button" id="detailDelete">
				  삭제하기</button>
				  <button class="btn btn-primary" type="button"
				  	onclick="location.href='postList'">목록보기</button>
				</div>
        	</div>
        </div>
        <!-- Side widgets-->
        <div class="col-lg-4">
            <!-- Search widget-->
            <div class="card mb-4">
                <div class="card-header">Search</div>
                <div class="card-body">
                    <div class="input-group">
                        <input class="form-control" type="text" placeholder="Enter search term..." 
                        	aria-label="Enter search term..." aria-describedby="button-search" />
                        <button class="btn btn-primary" id="button-search" type="button">
                        	Go!
                        </button>
                    </div>
                </div>
            </div>
            <!-- Categories widget-->
            <div class="card mb-4">
                <div class="card-header">Categories</div>
                <div class="card-body">
                	<c:if test="${ not empty categoryList }">                	
                	<div class="row">
                        <div class="col-sm-6">
                            <ul class="list-unstyled mb-0">
                            <c:forEach var="c" items="${categoryList}" varStatus="i">                            	
                            	<c:if test="${ i.index < categoryList.size() / 2 }">
                                	<li>
                                		<a href="postListByCategory?categoryNo=${c.categoryNo}">
                                			${c.categoryName}</a>
                                	</li>
                                </c:if>                                
                            </c:forEach>
                            </ul>
                        </div>
                        <div class="col-sm-6">
                            <ul class="list-unstyled mb-0">
                            <c:forEach var="c" items="${categoryList}" varStatus="i">
                            	<c:if test="${ i.index >= categoryList.size() / 2 }">
                                	<li>
                                		<a 
                                			href="postListByCategory?categoryNo=${c.categoryNo}">
                                			${c.categoryName}
                                		</a>
                                	</li>
                                </c:if>                                
                            </c:forEach>
                            </ul>
                        </div>
                    </div>                 		                      
                    </c:if>
                    <c:if test="${ empty categoryList }">
                    <div class="row">
                        <div class="col-sm-6">
                            <ul class="list-unstyled mb-0">
                                <li><a href="#!">HTML5</a></li>
                                <li><a href="#!">Database</a></li>
                                <li><a href="#!">BigData</a></li>
                            </ul>
                        </div>
                        <div class="col-sm-6">
                            <ul class="list-unstyled mb-0">
	                        	<li><a href="#">Java</a></li>
	                            <li><a href="#">JSP</a></li>
	                            <li><a href="#">Spring</a></li>
                            </ul>
                        </div>
                    </div>
                     </c:if>
                </div>
            </div>
            <!-- Side widget-->
            <div class="card mb-4">
                <div class="card-header">Side Widget</div>
                <div class="card-body">준비중...</div>
            </div>
        </div>
    </div>
</div>
