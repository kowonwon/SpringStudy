package com.springstudy.ch02.service;

import java.util.ArrayList;

import com.springstudy.ch02.dao.MemberDAO;
import com.springstudy.ch02.domain.Member;

public class MemberServiceImplProperty implements MemberService {

	// DB 작업을 누구한테 의존 - MemberDAO
	private MemberDAO memberDAO;
	
	// MemberDAO 타입을 생성자를 통해서 주입받음.
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	@Override
	public ArrayList<Member> getMemberList() {
		// 페이징, 검색관련 추가 처리 코드가 추가
		return memberDAO.getMemberList();
	}
}