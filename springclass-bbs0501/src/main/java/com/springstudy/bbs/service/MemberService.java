package com.springstudy.bbs.service;

import com.springstudy.bbs.domain.Member;

public interface MemberService {
	
	// 회원 로그인을 처리하는 메서드
	int login(String id, String pass);
	
	
	// id에 해당하는 회원 정보를 반환하는 메서드
	Member getMember(String id);
}
