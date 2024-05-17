	package com.springstudy.bbs.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.springstudy.bbs.domain.Member;
import com.springstudy.bbs.service.MemberService;

// 스프링 MVC의 컨트롤러임을 선언하고 있다.
@Controller

/* 스프링은 데이터를 세션 영역에 저장할 수 있도록 @SessionAttributes("모델이름")
 * 애노테이션을 제공하고 있다. 클래스 레벨에 @SessionAttributes("모델이름")와
 * 같이 애노테이션과 모델 이름을 지정하고 아래 login() 메서드와 같이 그 컨트롤러 
 * 메서드에서 @SessionAttributes에 지정한 모델이름과 동일한 이름으로 모델에
 * 객체를 추가하면 이 객체를 세션 영역에 저장해 준다.
 **/
@SessionAttributes("member")
public class MemberController {
	
	private MemberService memberService;
	
	/* @Autowired annotation을 사용해 MemberService 구현체를 셋터 주입하고 있다. 
	 * 스프링이 기본 생성자를 통해 이 클래스의 인스턴스를 생성한 후 setter 주입
	 * 방식으로 MemberService 구현체를 주입해 준다. 셋터 주입은 반드시 기본 생성자가
	 * 존재해야 하지만 이 클래스에는 다른 생성자가 존재하지 않으므로 컴파일러에 의해
	 * 자동으로 기본 생성자가 만들어 진다.
	 **/
	@Autowired
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	/* @RequestMapping의 ()에 method=RequestMethod.Post를 지정해
	 * "/login"으로 들어오는 POST 방식의 요청을 처리할 수 있도록 설정하고 있다.
	 * 
	 * 요청을 처리한 결과를 뷰에 전달하기 위해 사용하는 것이 Model 객체이다.
	 * 컨트롤러는 요청을 처리한 결과 데이터를 모델에 담아 뷰로 전달하고 뷰는
	 * 모델로 부터 데이터를 읽어와 클라이언트로 보낼 결과 페이지를 만들게 된다.
	 *   
	 * 스프링은 컨트롤러에서 모델에 데이터를 담을 수 있는 다양한 방법을 제공하는데
	 * 아래와 같이 파라미터에 Model을 지정하는 방식이 많이 사용된다. 
	 * @RequestMapping 애노테이션이 적용된 메서드의 파라미터에 Model
	 * 을 지정하면 스프링이 이 메서드를 호출하면서 Model 타입의 객체를 넘겨준다.
	 * 우리는 Model을 받아 이 객체에 결과 데이터를 담기만 하면 뷰로 전달된다.
	 * 	
	 * @RequestMapping 애노테이션이 적용된 메서드의 파라미터에 
	 * @RequestParam 애노테이션에 파라미터 이름을 지정하면 
	 * 이 애노테이션이 앞에 붙은 매개변수에 파라미터 값을 바인딩 시켜준다.
	 * 
	 * @RequestParam 애노테이션에 사용할 수 있는 속성은 아래와 같다.
	 * value : HTTP 요청 파라미터의 이름을 지정한다.
	 * required : 요청 파라미터가 필수인지 설정하는 속성으로 기본값은 true 이다.
	 * 			이 값이 true인 상태에서 요청 파라미터의 값이 존재하지 않으면
	 * 			스프링은 Exception을 발생시킨다.
	 * defaultValue : 요청 파라미터가 없을 경우 사용할 기본 값을 문자열로 지정한다.
	 * 
	 * @RequestParam(value="id" required="false" defaultValue="")
	 * 
	 * @RequestParam 애노테이션은 요청 파라미터 값을 읽어와 메서드의 
	 * 파라미터 타입에 맞게 변환해 준다.
	 * 스프링은 요청 파라미터의 값으로 변환할 수 없는 경우 400 에러를 발생시킨다.
	 * 
	 * @RequestMapping 애노테이션이 적용된 메서드에 요청 파라미터
	 * 이름과 메서드의 파라미터 이름이 같은 경우 @RequestParam 애노테이션을
	 * 지정하지 않아도 스프링으로부터 요청 파라미터를 받을 수 있다. 
	 **/	 
	@RequestMapping(value="/login", method=RequestMethod.POST)	
	public String login(Model model, @RequestParam("userId") String id, 
			@RequestParam("pass") String pass, 
			HttpSession session, HttpServletResponse response) 
					throws ServletException, IOException {
		
		int result = memberService.login(id, pass);
		
		if(result == -1) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('존재하지 않는 아이디 입니다.');");
			out.println("	history.back();");
			out.println("</script>");
			
			/* 컨트롤러에서 null을 반환하거나 메서드의 반환 타입이 void일 경우
			 * Writer나 OutputStream을 이용해 응답 결과를 직접 작성할 수 있다.
			 * DispatcherServlet을 경유해 리소스 자원에 접근하는 경우에
			 * 자바스크립트의 history.back()은 약간의 문제를 일으킬 수 있다.
			 * history 객체를 이용하는 경우 서버로 요청을 보내는 것이 아니라
			 * 브라우저의 접속 이력에서 이전 페이지로 이동되기 때문에 발생한다. 
			 **/
			return null;
			
		} else if(result == 0) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('비밀번호가 다릅니다.');");
			out.println("	location.href='loginForm'");
			out.println("</script>");
			
			return null;
		}
		
		Member member = memberService.getMember(id);
		session.setAttribute("isLogin", true);	
		
		/* 클래스 레벨에 @SessionAttributes("member") 애노테이션을
		 * 지정하고 컨트롤러의 메서드에서 아래와 같이 동일한 이름으로 모델에
		 * 추가하면 스프링이 세션 영역에 데이터를 저장해 준다.
		 **/ 
		model.addAttribute("member", member);
		System.out.println("member.name : " + member.getName());

		/* 클라이언트 요청을 처리한 후 리다이렉트 해야할 경우 아래와 같이 redirect:
		 * 접두어를 붙여 뷰 이름을 반환하면 된다. 뷰 이름에 redirect 접두어가 붙으면
		 * HttpServletResponse를 사용해서 지정한 경로로 Redirect 된다. 
		 * redirect 접두어 뒤에 경로를 지정할 때 "/"로 시작하면 ContextRoot를
		 * 기준으로 절대 경로 방식으로 Redirect 된다. "/"로 시작하지 않으면 현재 
		 * 경로를 기준으로 상대 경로로 Redirect 된다. 또한 다른 사이트로 Redirect
		 * 되기를 원한다면 redirect:http://사이트 주소를 지정한다.
		 * 
		 * 로그인이 성공하면 게시 글 리스트로 리다이렉트 된다.
		 **/		
		return "redirect:/boardList";
	}
	
	/* @RequestMapping의 ()에 method 속성을 지정하지 않았으므로 
	 * "/logout" 으로 들어오는 GET과 POST 방식 요청 모두를 처리할 수 있다.
	 **/
	@RequestMapping("/logout")
	public String logout(SessionStatus sessionStatus, HttpSession session) {	
		
		/* 세션 영역에서 객체를 삭제
		 * 세션 영역에서만 삭제되고 모델에는 삭제되지 않는다.
		 * 세션을 다시 시작하지 않기 때문에 세션이 계속해서 유지된다.
		 **/
		//sessionStatus.setComplete();
		
		// 현재 세션을 종료하고 새로운 세션을 시작한다.
		session.invalidate();
		
		/* 클라이언트 요청을 처리한 후 리다이렉트 해야할 경우 아래와 같이 redirect:
		 * 접두어를 붙여 뷰 이름을 반환하면 된다. 뷰 이름에 redirect 접두어가 붙으면
		 * HttpServletResponse를  사용해서 지정한 경로로 Redirect 된다. 
		 * redirect 접두어 뒤에 경로를 지정할 때 "/"로 시작하면 ContextRoot를
		 * 기준으로 절대 경로 방식으로 Redirect 된다. "/"로 시작하지 않으면 현재 
		 * 경로를 기준으로 상대 경로로 Redirect 된다. 또한 다른 사이트로 Redirect
		 * 되기를 원한다면 redirect:http://사이트 주소를 지정한다.
		 * 
		 * 로그아웃 되면 게시 글 리스트로 리다이렉트 된다./
		 **/
		return "redirect:/boardList";
	}
	
	/* 회원가입 폼에서 들어오는 요청을 처리하는 메서드
	 * 아래는 "/joinResult"로 들어오는 GET 방식 요청을 처리하는 메서드를 지정한
	 * 것이다. method 속성을 생략하면 GET 방식과 POST 방식 모두를 처리할 수 있다.
	 * 
	 * 실제 memberJoinForm.jsp에서 폼을 전송할 때 POST 방식으로 폼을 전송
	 * 했지만 아래의 joinResult() 메서드에서 정상적으로 처리된다.
	 * method 속성이 생략되면 스프링은 GET 방식 요청을 먼저 적용해 처리 하지만
	 * POST 방식의 요청도 처리해 준다.
	 * 
	 * 만약 아래에서 method=RequestMethod.GET 를 명시적으로 지정했거나
	 * /joinInfo 요청을 처리하는 메서드가 존재하지 않는다면 아래와 같이 405
	 * 익셉션이 발생하게 된다.
	 * 
	 * HTTP Status 405 - Request method 'POST' not supported
	 * 
	 * 스프링이 자동으로 처리해 주긴 하지만 GET 방식과 POST 방식을 명확하게
	 * 처리하는 것이 바람직한 코딩 방법이다.
	 **/	
	@RequestMapping("/joinResult")
	public String joinResult(Model model, Member member,
			String pass1, String emailId, String emailDomain,
			String mobile1, String mobile2, String mobile3,
			String phone1, String phone2, String phone3,
			@RequestParam(value="emailGet", required=false, 
				defaultValue="false")boolean emailGet) {		
		
		member.setPass(pass1);
		member.setEmail(emailId + "@" + emailDomain);
		member.setMobile(mobile1 + "-" + mobile2 + "-" + mobile3);
				
		if(phone2.equals("") || phone3.equals("")) {			
			member.setPhone("");				
		} else {			
			member.setPhone(phone1 + "-" + phone2 + "-" + phone3);
		}				
		member.setEmailGet(Boolean.valueOf(emailGet));

		// MemberService를 통해서 회원 가입 폼에서 들어온 데이터를 DB에 저장한다.
		memberService.addMember(member);
		System.out.println("joinResult : " + member.getName());
		
		// 로그인 폼으로 리다이렉트 시킨다.
		return "redirect:loginForm";
	}
	
	// 회원가입 폼에서 들어오는 중복 아이디 체크 요청을 처리하는 메서드
	@RequestMapping("/overlapIdCheck")	
	public String overlapIdCheck(Model model, String id) {		
		
		// 회원 아이디 중복 여부를 받아온다.
		boolean overlap = memberService.overlapIdCheck(id);
		
		// model에 id와 회원 아이디 중복 여부를 저장 한다. 
		model.addAttribute("id", id);
		model.addAttribute("overlap", overlap);
		
		/* 회원 가입 폼에서 아이디 중복확인 버튼을 클릭하면 새창으로 뷰가 보이게
		 * 해야 하므로 뷰 이름을 반환 할 때 "forward:" 접두어를 사용했다. 
		 * "forwrad:" 접두어가 있으면 뷰 리졸버 설정에 지정한 prefix, suffix를
		 * 적용하지 않고 "forwrad:" 뒤에 붙인 뷰 페이지로 포워딩 된다. 
		 **/
		return "forward:WEB-INF/views/member/overlapIdCheck.jsp";
	}	

	// 회원 정보 수정 폼 요청을 처리하는 메서드
	@RequestMapping("/memberUpdateForm")
	public String updateForm(Model model, HttpSession session) {		
				
		/* 로그인 처리를 할 때 세션 영역에 회원 정보를 저장했기 때문에 뷰의 정보만 반환한다.
		 * 이렇게 별도의 처리가 필요 없을 경우 뷰 전용 컨트롤러를 사용하는 것도 좋다.
		 **/
		return "member/memberUpdateForm";
	}	
	
	// 회원 수정 폼에서 들어오는 요청을 처리하는 메서드
	@RequestMapping("/memberUpdateResult")
	public String memberUpdateInfo(Model model, Member member,
			String pass1, String emailId, String emailDomain,
			String mobile1, String mobile2, String mobile3,
			String phone1, String phone2, String phone3,
			@RequestParam(value="emailGet", required=false, 
				defaultValue="false")boolean emailGet) {
		
		member.setPass(pass1);
		member.setEmail(emailId + "@" + emailDomain);
		member.setMobile(mobile1 + "-" + mobile2 + "-" + mobile3);
				
		if(phone2.equals("") || phone3.equals("")) {			
			member.setPhone("");				
		} else {			
			member.setPhone(phone1 + "-" + phone2 + "-" + phone3);
		}				
		member.setEmailGet(Boolean.valueOf(emailGet));	
			
		// MemberService를 통해서 회원 수정 폼에서 들어온 데이터를 DB에서 수정한다.
		memberService.updateMember(member);		
		System.out.println("memberUpdateResult : " + member.getId());
	
		/* 클래스 레벨에 @SessionAttributes({"member"}) 
		 * 애노테이션을 지정하고 컨트롤러의 메서드에서 아래와 같이 동일한 
		 * 이름으로 모델에 추가하면 스프링이 세션 영역에 데이터를 저장해 준다.
		 **/ 
		model.addAttribute("member", member);
		
		// 게시 글 리스트로 리다이렉트 시킨다.
		return "redirect:boardList";
	}
}
