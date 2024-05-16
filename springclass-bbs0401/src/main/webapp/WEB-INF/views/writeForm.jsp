<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="resources/js/formcheck.js"></script>
<!-- content -->
<div class="row my-5" id="global-content">
	<div class="col">
		<div class="row text-center">
			<div class="col">
				<h2 class="fs-3 fw-bold">게시 글쓰기</h2>
			</div>
		</div>  
		<form name="writeForm" action="writeProcess" id="writeForm" 
			class="row g-3 border-primary" method="post" enctype="multipart/form-data">
		  	<div class="col-4 offset-md-2">
			    <label for="writer" class="form-label">글쓴이</label>
			    <input type="text" class="form-control" name="writer"  id="writer" 
			    	placeholder="작성자를 입력해 주세요">
	  		</div>
	  		<div class="col-4 ">
			    <label for="pass" class="form-label">비밀번호</label>
			    <input type="password" class="form-control" name="pass"  id="pass" >
	  		</div>
	  		<div class="col-8 offset-md-2">
			    <label for="title" class="form-label">제 목</label>
			    <input type="text" class="form-control" name="title"  id="title" >
	  		</div>
	  		<div class="col-8 offset-md-2">
			    <label for="file1" class="form-label">파일</label>
			    <input type="file" class="form-control" name="file1"  id="file1" >
	  		</div>
				<div class="col-8 offset-md-2">
			    <label for="content" class="form-label">내 용</label>
			    <textarea class="form-control" name="content" id="content" rows="10"></textarea>
	  		</div>	
	  		<div class="col-8 offset-md-2 text-center mt-5">
			   <input type="submit" value="등록하기" class="btn btn-primary"/>
					&nbsp;&nbsp;<input type="button" value="목록보기" 
						onclick="location.href='boardList'" class="btn btn-primary"/>
	  		</div>	
		</form>
	</div>
</div>    
    
    