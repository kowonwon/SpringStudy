package com.springstudy.ch02.annotation;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springstudy.ch02.dao.MemberDAO;
import com.springstudy.ch02.domain.Member;
import com.springstudy.ch02.service.MemberService;

//@Component
@Service
public class MemberServiceImpl implements MemberService {

	// DB 작업을 누구한테 의존 - MemberDAO
	private MemberDAO memberDAO;
	
	// MemberDAO 타입을 생성자를 통해서 주입받음.
	@Autowired
	public MemberServiceImpl(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	@Override
	public ArrayList<Member> getMemberList() {
		System.out.println("생성자 주입");
		// 페이징, 검색관련 추가 처리 코드가 추가
		return memberDAO.getMemberList();
	}
}