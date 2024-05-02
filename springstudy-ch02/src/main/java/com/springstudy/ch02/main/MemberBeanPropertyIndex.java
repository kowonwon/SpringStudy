package com.springstudy.ch02.main;

import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.springstudy.ch02.domain.Member;
import com.springstudy.ch02.service.MemberService;

// 세터 주입(Setter Injection)
public class MemberBeanPropertyIndex {

	public static void main(String[] args) {
		
		// 스프링 설정 파일에 정의한 Bean을 생성해 빈 컨테이너에 담는다.
		ApplicationContext ctx = new GenericXmlApplicationContext(
				"classpath:config/MemberBeanPropertyContext.xml");
		
		// 빈 컨테이너에서 "memberService"란 id 또는 name을 가진 Bean 객체를 얻어온다.
		MemberService service = (MemberService) ctx.getBean("memberService");
		
		// MemberService 타입 객체를 통해 회원 리스트를 가져와 출력한다.
		ArrayList<Member> memberList = service.getMemberList();
		System.out.println("## 회원 리스트 - setter 주입 ##");		
		for(Member m : memberList) {
			System.out.println(m);
		}
	}
}
