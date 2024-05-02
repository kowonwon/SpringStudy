package com.springstudy.ch02.annotation;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springstudy.ch02.dao.MemberDAO;
import com.springstudy.ch02.domain.Member;
import com.springstudy.ch02.service.MemberService;

//@Component Annotation을 이용해 이 클래스가 스프링 컴포넌트임을 선언한다.
//@Component("memberServiceImpl")

/* @Component 대신에 이를 확장한 Annotation인 @Controller, @Service,
 * @Repository 등을 사용해 스프링 빈 임을 정의할 수 있다.
 * 
 * 아래는 @Service Annotation을 이용해 이 클래스가 서비스 계층의 스프링 빈임을
 * 선언하고 있다. 빈의 이름은 별도로 부여하지 않았으므로 스프링이 알아서 클래스 이름을
 * 카멜 케이싱해 "memberServiceImplConstructor"이라는 빈 이름을 부여한다.
 **/
@Service
public class MemberServiceImplConstructor implements MemberService {	
	
	MemberDAO memberDAO;
	
	/* 스프링 설정 파일에 <context:component-scan>의 base-package에
	 * 지정한 패키지를 기준으로 스캔하여 @Component, @Controller, @Service,
	 * @Repository 애노테이션이 붙은 클래스의 객체를 생성하고 MemberDAO 타입의 
	 * 객체를 아래와 같이 @Autowired 애노테이션이 붙은 생성자에 주입해 준다.
	 **/
	@Autowired	
	public MemberServiceImplConstructor(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	@Override
	public ArrayList<Member> getMemberList() {		
		return memberDAO.getMemberList();
	}
}
