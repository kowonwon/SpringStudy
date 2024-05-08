<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<article>
	<h1>명준이의 당부</h1>
	<!--
		FirstMvcController 클래스 위에 @RequestMapping("/first")를 설정하고
		index() 메서드에 @RequestMapping({"/", "/intro", "/index"})으로 설정했다.			
		그리고 WEB-INF/spring/appServlet/servlet-context.xml에서 정적 리소스와
		관련된 url 맵핑을 아래와 같이 설정했기 때문에	
		<mvc:resources mapping="/resources/**" location="/resources/" />
		이미지의 위치를 "../resources/images/myungjun01.jpg"와 같이 지정해야 한다.		

		브라우저 주소 표시줄에 http://localhost:8080/springstudy-ch0401/first/,
		또는 http://localhost:8080/springstudy-ch0401/first/intro,
		또는 http://localhost:8080/springstudy-ch0401/first/index 등으로 표시되므로
		images 디렉터리는 ContextRoot/resources/images에 위치하기 때문에 현재 위치를
		기준으로 상대 참조 방식으로 "../resources/images/myungjun01.jpg"를 지정해야 한다.
	-->
	<div id="photo">
		<img src="../resources/images/myungjun01.jpg" id="img"/>
	</div>
	<div id="comment">
		<p>
			삼촌~ 이모들~ 스프링 공부 열심히 하삼~~<br/>
			<a href="detail?id=spring&no=20">디테일 당부보기</a>		 
		</p>
	</div>
</article>