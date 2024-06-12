<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    
<!-- Page content-->
<div class="container mt-5 mb-5">
    <div class="row">
        <div class="col-lg-8 mx-auto">
            <!-- Post content-->
            <article>                
                <header class="mb-5">                    
                    <h1 class="fw-bolder mb-1">블로그 포스트 쓰기</h1>                    
                </header>
                <form action="postWriteProcess" method="post" id="postWriteForm" 
                	enctype="multipart/form-data">                	
					<div class="mb-3">
						<label for="title" class="form-label">포스트 제목 : </label>
					  	<input type="text" class="form-control" name="title" id="title" aria-describedby="titleHelp">
					  	<div id="titleHelp" class="form-text">블로그 포스트의 제목을 5자 이상 적어주세요</div>
					</div>
					<div class="mb-3">
						<!-- 현재는 FlowerPig로 하고 이후에 로그인한 사용자의 닉네임으로 적용 -->  
						<label for="writer" class="form-label">작성자 : </label>
					  	<input type="text" class="form-control text-secondary" 
					  		name="writer" id="writer" value="FlowerPig" readonly>
					</div>
					<div class="mb-3">
						<label for="title" class="form-label">카테고리 : </label>
						<select class="form-select" name="categoryNo" id="categoryNo">							
						<c:forEach var="c" items="${ categoryList }">
							<option value="${c.categoryNo}">${c.categoryName}</option>
						</c:forEach>
						</select>					  	
					</div>
					<div class="mb-3">
					  	<label for="content" class="form-label">내 용 : </label>
					  	<textarea class="form-control" name="content" id="content" rows="10"></textarea>
					</div>
					<div class="mb-3">
  						<label for="mainImg" class="form-label">메인 이미지 : </label>
  						<input class="form-control" type="file" name="addImg" id="addImg">
  					</div>
					<div class="mb-3">
  						<label for="addFile" class="form-label">첨부파일 : </label>
  						<input class="form-control" type="file" name="addFile" id="addFile">
  					</div>
  					<div class="mb-3 mt-5 d-md-flex justify-content-md-end">
						<button type="submit" class="btn btn-primary">Submit</button>
					</div>
				</form>
			</article>
		</div>
	</div>
</div>