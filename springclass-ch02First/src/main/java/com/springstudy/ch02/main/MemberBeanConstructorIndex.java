package com.springstudy.ch02.main;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.springstudy.ch02.domain.Member;
import com.springstudy.ch02.service.MemberService;

public class MemberBeanConstructorIndex {

	public static void main(String[] args) {
		// applicationContext - DI Container
		ApplicationContext ac = new GenericXmlApplicationContext(
				"classpath:config/MemberBeanConstructorContext.xml");
		
		MemberService service = (MemberService)ac.getBean("memberService");
		List<Member> mList = service.getMemberList();
		System.out.println("### 회원 리스트 ###");
		for(Member m : mList) {
			System.out.println(m);
		}
	}
}