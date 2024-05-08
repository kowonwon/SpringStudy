package com.springstudy.ch02.main;

import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.springstudy.ch02.domain.Member;
import com.springstudy.ch02.service.MemberService;

// Annotation 세터 주입 main
public class MemberAnnotationPropertyIndex {

	public static void main(String[] args) {
		
		/* 지금은 자동으로 DI 컨테이너가 생성되지 않기 때문에 스프링이 제공하는
		 * DI 컨테이너 역할을 하는 클래스의 인스턴스를 우리가 수동으로 생성하고
		 * 빈 설정 파일을 인수로 지정해야 한다. 그래야 스프링 DI 컨테이너가 생성되면서
		 * 빈 설정 파일을 읽어 그 설정 파일에 빈으로 정의된 클래스를 객체로 생성한다.
		 * 
		 * Bean 정의 파일에 component-scan의 base-package에 지정한 패키지를
		 * 기준으로 스캐닝 하여 @Component, @Controller, @Service, @Repository
		 * Annotation이 붙은 클래스의 객체를 생성하고 @Autowired, @Resource 등의
		 * Annotation이 붙은 생성자나 세터 메서드를 이용해 필요한 객체를 주입하게 된다.
		 **/
		ApplicationContext ctx = new GenericXmlApplicationContext(
				"classpath:config/annotation/AnnotationPropertyContext.xml");
	
		/* 빈 컨테이너에서 MemberService 타입(MemberService를 구현한 구현체)의
		 * "memberService"라는 Bean의 이름을 가진 객체를 구한다.
		 **/
		MemberService service = ctx.getBean("memberService", MemberService.class);
		
		// MemberService 타입의 객체를 통해 회원 리스트를 가져와 출력한다.
		ArrayList<Member> memberList = service.getMemberList();
		System.out.println("## 회원 리스트 - Annotation setter 주입 ##");
		for(Member m : memberList) {
			System.out.println(m);
		}
	}
}
