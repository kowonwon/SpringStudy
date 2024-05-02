package com.springstudy.ch02.annotation;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springstudy.ch02.dao.MemberDAO;
import com.springstudy.ch02.domain.Member;
import com.springstudy.ch02.service.MemberService;

// 이 클래스가 스프링 컴포넌트임을 선언하고 "memberService"라는 빈 이름을 지정한다.
// @Component("memberService")

/* @Component 대신에 이를 확장한 Annotation인 @Controller, @Service,
 * @Repository 등을 사용해 스프링 빈 임을 정의할 수 있다.
 * 
 * 아래는 @Service Annotation을 이용해 이 클래스가 서비스 계층의 스프링 빈임을
 * 선언하고 "memberService"라는 빈의 이름을 지정하고 있다.
 **/
@Service("memberService")
public class MemberServiceImplProperty implements MemberService {
	
	private MemberDAO memberDAO;
	
	/* 스프링 설정 파일에 <context:component-scan>의 base-package에
	 * 지정한 패키지를 기준으로 스캔하여 @Component, @Controller, @Service,
	 * @Repository 애노테이션이 붙은 클래스의 객체를 생성하고 MemberDAO 타입의 
	 * 객체를 아래와 같이 @Autowired 애노테이션이 붙은 setter 메서드에 주입해 준다.
	 * 
	 * 이 동작은 클래스의 기본 생성자를 통해 객체를 생성하고 setter 메서드로 주입한다.
	 * 그러므로 이 클래스에 기본 생성자가 반드시 존재해야 하지만 다른 생성자가
	 * 정의되어 있지 않으므로 컴파일러에 의해 기본 생성자가 자동으로 만들어 진다.  
	 **/
	@Autowired
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	@Override
	public ArrayList<Member> getMemberList() {		
		return memberDAO.getMemberList();
	}
}
