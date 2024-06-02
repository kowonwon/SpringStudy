package com.springstudy.bbs.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springstudy.bbs.domain.Member;
import com.springstudy.bbs.service.MemberService;

@Controller
@SessionAttributes("member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/memberUpdateForm")
	public String memberUpdateForm() {
		
		return "member/memberUpdateForm";
	}
	
	// 회원 정보 수정
	@RequestMapping("/memberUpdateResult")
	public String memberUpdateInfo(Model model, Member member,
			String pass1, String emailId, String emailDomain,
			String mobile1, String mobile2, String mobile3,
			String phone1, String phone2, String phone3,
			@RequestParam(value="emailGet", defaultValue="false") boolean emailGet) {
		
		member.setPass(pass1);
		member.setEmail(emailId + "@" + emailDomain);
		member.setMobile(mobile1 + "-" + mobile2 + "-" + mobile3);
		if(phone2.equals("") || phone3.equals("")) {
			member.setPhone("");
		} else {
			member.setPhone(phone1 + "-" + phone2 + "-" + phone3);			
		}
		member.setEmailGet(emailGet);
		
		// MemberService를 통해서 회원 정보를 DB에서 수정
		memberService.updateMember(member);
		
		// 세션에 수정된 회원 정보를 갱신
		model.addAttribute("member", member);
		
		// 회원가입이 완료되면 로그인 폼으로 리다이렉트 시킨다.
		return "redirect:boardList";
	}	
	
	@RequestMapping("/joinResult")
	public String joinResult(Member member,
			String pass1, String emailId, String emailDomain,
			String mobile1, String mobile2, String mobile3,
			String phone1, String phone2, String phone3,
			@RequestParam(value="emailGet", defaultValue="false") boolean emailGet) {
		
		member.setPass(pass1);
		member.setEmail(emailId + "@" + emailDomain);
		member.setMobile(mobile1 + "-" + mobile2 + "-" + mobile3);
		if(phone2.equals("") || phone3.equals("")) {
			member.setPhone("");
		} else {
			member.setPhone(phone1 + "-" + phone2 + "-" + phone3);			
		}
		member.setEmailGet(emailGet);
		
		// MemberService를 통해서 회원 정보를 DB에 저장
		memberService.addMember(member);
		
		// 회원가입이 완료되면 로그인 폼으로 리다이렉트 시킨다.
		return "redirect:boardList";
	}
	
	// 회원 가입 폼에서 들어오는 중복 아이디 체크 요청을 처리하는 메서드
	@RequestMapping("/overlapIdCheck")
	public String overlapIdCheck(Model model, String id) {
		
		boolean overlap = memberService.overlapIdCheck(id);
		model.addAttribute("overlap", overlap);
		model.addAttribute("id", id);		
		
		// viewResolver 의 prefix + 반환하는 view의 이름 + suffix
		// /WEB-INF/index.jsp?body=views/ + member/overlapIdCheck + .jsp
		return "forward:WEB-INF/views/member/overlapIdCheck.jsp";
	}
	
	
	// login 요청을 처리하는 메서드
	@PostMapping("/login")
	public String login(Model model, 
			@RequestParam("userId") String id, 
			String pass, 
			HttpSession session, HttpServletResponse response) 
					throws IOException {
		
		int result = memberService.login(id, pass);
		if(result == -1) { // id가 존재하지 않는 경우
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('존재하지 않는 아이디 입니다.')");
			out.println("	history.back();");
			out.println("</script>");
			return null;
			
		} else if(result == 0) { // 비밀번호가 틀린 경우
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('비밀번호가 틀립니다.')");
			out.println("	history.back();");
			out.println("</script>");
			return null;			
		}
		
		Member member = memberService.getMember(id);
		session.setAttribute("isLogin", true);
		//session.setAttribute("member", member);
		model.addAttribute("member", member);
		
		return "redirect:boardList";
	}
	
	
	// logout 요청을 처리하는 메서드
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:boardList";
	}
	
}
