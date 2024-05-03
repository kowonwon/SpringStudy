package com.springstudy.ch02.service;

import java.util.ArrayList;

import com.springstudy.ch02.dao.MemberDAO;
import com.springstudy.ch02.domain.Member;

public class MemberServiceImplConstructor implements MemberService {

	private MemberDAO memberDAO;
	
	public MemberServiceImplConstructor(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public ArrayList<Member> getMemberList() {
		return memberDAO.getMemberList();
	}
	
}