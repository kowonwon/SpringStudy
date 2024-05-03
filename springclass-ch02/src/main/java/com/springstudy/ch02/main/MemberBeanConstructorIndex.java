package com.springstudy.ch02.main;

import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.springstudy.ch02.domain.Member;
import com.springstudy.ch02.service.MemberService;

public class MemberBeanConstructorIndex {
	public static void main(String[] args) {
		ApplicationContext ctx = new GenericXmlApplicationContext(
				"classpath:config/MemberBeanConstructorContext.xml");
		
		MemberService service = (MemberService) ctx.getBean("memberService");
		
		ArrayList<Member> memberList = service.getMemberList();
		System.out.println("## 회원 리스트 - 생성자 주입 ##");
		for(Member m : memberList) {
			System.out.println(m);
		}
	}
}