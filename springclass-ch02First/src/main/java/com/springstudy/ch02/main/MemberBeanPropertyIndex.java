package com.springstudy.ch02.main;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.springstudy.ch02.domain.Member;
import com.springstudy.ch02.service.MemberService;

public class MemberBeanPropertyIndex {

	public static void main(String[] args) {
		ApplicationContext ctx = new GenericXmlApplicationContext(
				"classpath:config/MemberBeanPropertyContext.xml");
		
		MemberService service = ctx.getBean(MemberService.class);
		List<Member> mList = service.getMemberList();
		for(Member m : mList) {
			System.out.println(m);
		}
	}

}
