<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Page header with logo and tagline-->
<header class="py-5 bg-light border-bottom mb-4">
    <div class="container">
        <div class="text-center my-5">
            <h1>Welcome FlowerPig Blog</h1>
            <p class="lead mb-0">즐거운 인생~ 삶의 의미를 찾아가는 FlowerPig! 모두 모두 환영합니다. ^_^</p>
        </div>
    </div>
</header>
<!-- Page content-->
<div class="container">
    <div class="row">
        <!-- Blog entries-->
        <div class="col-lg-8">
            <!-- Featured blog post-->
            <c:if test="${ not empty postList }">
            <c:forEach var="post" items="${ postList }" varStatus="i">
            <div class="card mb-4">
                <a href="#!">
                	<!-- 메인 이미지가 있으면 그 이미지를 출력 -->
                	<c:if test="${ not empty post.mainImg }">
                		<img class="card-img-top" 
                			src="resources/postMainImg/${ post.mainImg }" alt="..." />
                	</c:if>
                	<!-- 메인 이미지가 없으면 랜덤 이미지 출력  -->
                	<c:if test="${ empty post.mainImg }">
                		<img class="card-img-top" 
                			src="https://picsum.photos/id/${i.count*5}/850/350" alt="..." />
                	</c:if>
                </a>                
                <div class="card-body">
                    <div class="small text-muted">                    	
                    	<fmt:formatDate value="${post.modDate}" 
                    		type="date" dateStyle="full" pattern="MMMM d, YYYY"/>
                    </div>
                    <h2 class="card-title">${post.title}</h2>
                    <p class="card-text">${post.content}</p>
                    <a class="btn btn-primary" href="postDetail?postNo=${post.postNo}">
                    	Read more →
                    </a>
                </div>
            </div>
            </c:forEach>    
            <!-- Pagination-->
            <nav aria-label="Pagination">
                <hr class="my-0" />
                <ul class="pagination justify-content-center my-4">
                    <li class="page-item disabled">
                    	<a class="page-link" href="#" tabindex="-1" aria-disabled="true">
                    		Newer</a>
                    </li>
                    <li class="page-item active" aria-current="page">
                    	<a class="page-link" href="#!">1</a>
                    </li>
                    <li class="page-item"><a class="page-link" href="#!">2</a></li>
                    <li class="page-item"><a class="page-link" href="#!">3</a></li>
                    <li class="page-item disabled">
                    	<a class="page-link" href="#!">...</a>
                    </li>
                    <li class="page-item"><a class="page-link" href="#!">15</a></li>
                    <li class="page-item"><a class="page-link" href="#!">Older</a></li>
                </ul>
            </nav>
            </c:if>
            <c:if test="${ empty postList }">
            <div class="card mb-4">
                <a href="#!">
                	<img class="card-img-top" 
                		src="https://dummyimage.com/850x350/dee2e6/6c757d.jpg" 
                		alt="..." />
                </a>
                <div class="card-body">
                    <div class="small text-muted">August 9, 2022</div>
                    <h2 class="card-title">작성된 포스트 없음</h2>
                    <p class="card-text">블로그 오픈 준비 중... 작성된 포스트가 없습니다.</p>
                    <a class="btn btn-primary" href="#">Read more →</a>
                </div>
            </div>
            </c:if>
        </div>
        <!-- Side widgets-->
        <div class="col-lg-4">
            <!-- Search widget-->
            <div class="card mb-4">
                <div class="card-header">Search</div>
                <div class="card-body">
                    <div class="input-group">
                        <input class="form-control" type="text" 
                        	placeholder="Enter search term..." 
                        	aria-label="Enter search term..." 
                        	aria-describedby="button-search" />
                        <button class="btn btn-primary" id="button-search" type="button">
                        	Go!
                        </button>
                    </div>
                </div>
            </div>            
            <!-- Side widget-->
            <div class="card mb-4">
                <div class="card-header">Side Widget</div>
                <div class="card-body">
	                <div class="row">
		        		<div class="d-grid gap-2 d-md-block">
							<a class="btn btn-primary" href="postWriteForm">
								포스트 쓰기</a>						  
						</div>
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
        </div>
    </div>
</div>

