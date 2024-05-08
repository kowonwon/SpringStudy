package com.springstudy.ch04.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springstudy.ch04.service.FirstMvcService;

//스프링 MVC의 컨트롤러임을 선언하고 있다.
@Controller

/* @RequestMapping을 이용해 처리할 요청 경로를 지정한다.
 * @RequestMapping은 클래스 레벨과 메서드 레벨에 지정할 수 있다.
 * 아래는 "ContextRoot/first"로 들어오는 요청을 이 컨트롤러가 처리할 수 있도록
 * 설정한 것이다. 이 컨트롤러의 모든 메서드는 "ContextRoot/first/*"로
 * 들어오는 요청만을 처리할 수 있다.
 **/
@RequestMapping("/first")
public class FirstMvcController {
	
	/* 	인스턴스 필드에 @Autowired annotation을 사용하면 접근지정자가 
	 * private이고 setter 메서드가 없다 하더라도 문제없이 주입 된다.
	 * 하지만 우리는 항상 setter 메서드를 준비하는 습관을 들일 수 있도록 하자.
	 * 
	 * setter 주입 방식은 스프링이 기본 생성자를 통해 이 클래스의 인스턴스를
	 * 생성한 후 setter 주입 방식으로 BoardService 타입의 객체를 주입하기 때문에  
	 * 기본 생성자가 존재해야 하지만 이 클래스에 다른 생성자가 존재하지 않으므로
	 * 컴파일러에 의해 기본 생성자가 만들어 진다.
	 **/
	@Autowired
	private FirstMvcService service;
	
	public void setFirstMvcService(FirstMvcService service) {
		this.service = service;
	}
	
	/* @RequestMapping 애노테이션이 적용된 메서드의 파라미터와 반환 타입
	 * 
	 * 1. 메서드의 파라미터 타입으로 지정할 수 있는 객체와 애노테이션 
	 * @RequestMapping 애노테이션이 적용된 컨트롤러 메서드의 파라미터에
	 * 아래와 같은 객체와 애노테이션을 사용할 수 있도록 지원하고 있다.
	 * 
	 * - HttpServletRequest, HttpServletResponse
	 *   요청/응답을 처리하기 위한 서블릿 API
	 *   
	 * - HttpSession 
	 *   HTTP 세션을 위한 서블릿 API  
	 * 
	 * - org.springframework.ui.Model, ModelMap, java.util.Map
	 *   뷰에 모델 데이터를 전달하기 위한 모델 객체
	 *   
	 * - 커맨드 객체(VO, DTO)
	 *   요청 데이터를 저장할 객체
	 * 
	 * - Errors, BindingResult    
	 *   검증 결과를 저장할 객체로 커맨드 객체 바로 뒤에 위치 시켜야 한다.
	 *   
	 * - @RequestParam
	 *   HTTP 요청 파라미터의 값을 메서드의 파라미터로 매핑하기 위한 애노테이션  
	 * 
	 * - @RequestHeader
	 *   HTTP 요청 헤더의 값을 파라미터로 받기 위한 애노테이션
	 *   
	 * - @RequestCookie
	 *   Cookie 데이터를 파라미터로 받기 위한 애노테이션
	 *   
	 * - @RequestVariable
	 *   RESTful API 방식의 파라미터를 받기 위한 경로 변수 설정 애노테이션
	 *   
	 * - @RequestBody
	 *   요청 몸체의 데이터를 자바 객체로 변환하기 위한 애노테이션
	 *   String이나 JSON으로 넘어오는 요청 몸체의 데이터를 자바 객체로
	 *   변환하기 위한 사용하는 애노테이션 이다.
	 *   
	 * - Writer, OutputStream
	 *   응답 데이터를 직접 작성할 때 메서드의 파라미터로 지정해 사용한다.
	 *   
	 * 2. 메서드의 반환 타입으로 지정할 수 있는 객체와 애노테이션
	 * - String
	 *   뷰 이름을 반환할 때 메서드의 반환 타입으로 지정
	 * 
	 * - void
	 *   컨트롤러의 메서드에서 직접 응답 데이터를 작성할 경우 지정
	 * 
	 * - ModelAndView
	 *   모델과 뷰 정보를 함께 반환해야 할 경우 지정
	 *   이전의 컨트롤는 스프링이 지원한는 Controller 인터페이스를
	 *   구현해야 했는데 이때 많이 사용하던 반환 타입이다.
	 * 
	 * - 자바 객체 
	 *   메서드에 @ResponseBody가 적용된 경우나 메서드에서 반환되는
	 *   객체를 JSON 또는 XML과 같은 양식으로 응답을 변환 할 경우에 사용한다. 
	 **/
	
	/* @RequestMapping은 클래스 레벨과 메서드 레벨에 지정할 수 있다.
	 * @RequestMapping의 ()에 처리할 요청 URI만 지정할 때는 value 속성을
	 * 생략하고 처리할 요청 URI를 String 또는 String 배열을 지정할 수 있지만
	 * 다른 속성을 같이 지정할 경우 value 속성에 처리할 요청 URI를 지정해야 한다.
	 * 또한 method 속성을 지정해 컨트롤러가 처리할 HTTP 요청 방식을 지정할 수
	 * 있으며 method 속성을 생략하면 기본 값은 RequestMethod.GET 이다.
	 *    
	 * 아래는 "ContextRoot/first/", "ContextRoot/first/intro", 
	 * "ContextRoot/first/index"로 들어오는 GET 방식 요청을 이 메서드가 처리할 수
	 * 있도록 설정한 것이다. 아래와 같이 비즈니스 로직 처리 없이 단순히 뷰만
	 * 보여줘야 할 경우 Spring Web MVC 설정에서<view-controller /> 태그를
	 * 사용해 뷰 페이지를 지정하면 편리하게 뷰 전용 컨트롤러를 설정할 수 있다.	  
	 **/
	@RequestMapping(value={"/", "/intro", "/index"}, method=RequestMethod.GET)
	public String index() {	
		
		/* servlet-context.xml에 설정한 ViewResolver에서 prefix와 suffix에
		 * 지정한 정보를 제외한 뷰 이름을 문자열로 반환하면 된다.
		 * 
		 * 아래와 같이 뷰 이름을 반환하면 포워드 되어 제어가 뷰 페이지로 이동한다.
		 **/
		return "/main";
	}
	
	/* 아래는 "/detail"로 들어오는 GET 방식 요청을 처리하는 메서드를 지정한 것이다.
	 * method 속성을 생략했기 때문에 RequestMethod.GET이 적용된다.
	 *  
	 * 스프링은 클라이언트로 부터 넘어 오는 요청 파라미터를 받을 수 있는 여러 가지
	 * 방법을 제공하고 있다. 아래와 같이 Controller 메서드에 요청 파라미터 이름과
	 * 동일한 이름의 메서드 파라미터를 지정하면 스프링으로부터 요청 파라미터를 넘겨
	 * 받을 수 있다. 만약 요청 파라미터와 메서드의 파라미터 이름이 다른 경우에는
	 * 메서드의 파라미터 앞에 @RequestParam("요청 파라미터 이름")을 사용해 
	 * 요청 파라미터의 이름을 지정하면 된다.
	 * 
	 * @RequestMapping 애노테이션이 적용된 Controller 메서드의 파라미터에 
	 * @RequestParam 애노테이션을 사용해 요청 파라미터 이름을 지정하면 
	 * 이 애노테이션이 앞에 붙은 매개변수에 요청 파라미터 값을 바인딩 시켜준다.
	 * 
	 * @RequestParam 애노테이션에 사용할 수 있는 속성은 아래와 같다.
	 * value : HTTP 요청 파라미터의 이름을 지정한다.
	 * required : 요청 파라미터가 필수인지 설정하는 속성으로 기본 값은 true 이다.
	 * 			이 값이 true인 상태에서 요청 파라미터의 값이 존재하지 않으면
	 * 			스프링은 Exception을 발생시킨다.
	 * defaultValue : 요청 파라미터가 없을 경우 사용할 기본 값을 문자열로 지정한다.
	 * 
	 * @RequestParam(value="no" required=false defaultValue="1")
	 * 
	 * @RequestParam 애노테이션은 요청 파라미터 값을 읽어와 Controller 메서드의
	 * 파라미터 타입에 맞게 변환해 준다. 만약 요청 파라미터를 Controller 메서드의 
	 * 파라미터 타입으로 변환할 수 없는 경우 스프링은 400 에러를 발생시킨다.
	 * 
	 * @RequestMapping 애노테이션이 적용된 Controller 메서드의 파라미터
	 * 이름과 요청 파라미터의 이름이 같은 경우 @RequestParam 애노테이션을
	 * 지정하지 않아도 스프링으로부터 요청 파라미터를 받을 수 있다.
	 *
	 * 요청을 처리한 결과를 뷰에 전달하기 위해 사용하는 것이 모델이다.
	 * 컨트롤러는 요청을 처리한 결과 데이터를 모델에 담아 뷰로 전달하고 뷰는
	 * 모델로 부터 데이터를 읽어와 클라이언트로 보낼 결과 페이지를 만들게 된다.
	 *   
	 * 스프링은 컨트롤러에서 모델에 데이터를 담을 수 있는 다양한 방법을 제공하는데
	 * 아래와 같이 파라미터에 Model을 지정하는 방식이 많이 사용된다. 
	 * @RequestMapping 애노테이션이 적용된 메서드의 파라미터에 Model
	 * 을 지정하면 스프링이 이 메서드를 호출하면서 Model 타입의 객체를 넘겨준다.
	 * 우리는 Model을 받아 이 객체에 결과 데이터를 담기만 하면 뷰로 전달된다.
	 **/	
	@RequestMapping("/detail")
	public String detail(Model model, HttpServletRequest request, 
			@RequestParam(value="num", defaultValue="1") int no, String id) {
		
		// HttpServletRequest 객체로 이용해 요청 파라미터를 직접 받을 수도 있다.
		// String id = request.getParameter("id");
		
		// FirstMvcService 객체를 이용해 메시지를 가져와 Model에 담는다.
		String msg = service.getMessage(no, id);
		model.addAttribute("msg", msg);
		
		// 뷰로 보낼 필요한 데이터가 있으면 추가로 Model에 담는다.
		model.addAttribute("title", "명준이의 디테일 당부");
		model.addAttribute("comment", "정말 루~ 스프링 공부 열심히 해야 해여~ %_%");
		return "detail";
	}
}
