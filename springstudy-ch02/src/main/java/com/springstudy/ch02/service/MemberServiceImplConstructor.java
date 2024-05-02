package com.springstudy.ch02.service;

import java.util.ArrayList;

import com.springstudy.ch02.dao.MemberDAO;
import com.springstudy.ch02.domain.Member;

public class MemberServiceImplConstructor implements MemberService {
	
	private MemberDAO memberDAO;
	
	// 스프링 DI 컨테이너로 부터 MemberDAO 객체를 주입받는 생성자
	public MemberServiceImplConstructor(MemberDAO memberDAO) {		
		this.memberDAO = memberDAO;
	}
	
	@Override
	public ArrayList<Member> getMemberList() {		
		return memberDAO.getMemberList();		
	}
}
