<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<article>
	<!--
		컨트롤러에서 Model에 담은 데이터는 뷰 페이지에서 EL로 바로 접근이 가능하다.
		우리가 컨트롤러에서 Model에 담은 데이터는 스프링에 의해서 request 객체에
		저장되기 때문에 JSP에서와 같이 뷰 페이지에서 EL로 바로 접근이 가능하다. 
	-->
	<h1 id="bigH1">${title}</h1>	
	<div id="bigPhoto">
		<img src="../resources/images/myungjun01.jpg" id="bigImg"/>
	</div>
	<div id="bigComment">
		<p>${comment}</p><br/>	
		<h3>모델로 받은 - 메시지</h3>
		msg : ${ msg }	
	</div>	
</article>