package com.springstudy.bbs.service;

import com.springstudy.bbs.domain.Member;

public interface MemberService {

	// 회원 정보 수정 시에 기존 비밀번호가 맞는지 체크하는 메서드
	public boolean memberPassCheck(String id, String pass);
	
	// 회원 정보를 member 테이블에서 수정하는 메서드
	public void updateMember(Member member);
	
	// 회원 가입시 아이디 중복을 체크하는 메서드
	public boolean overlapIdCheck(String id);
	
	// 회원 정보를 dao를 이용해서 DB 테이블에 추가하는 메서드
	public void addMember(Member mebmer);
	
	// 회원 로그인을 처리하는 메서드
	int login(String id, String pass);
	
	
	// id에 해당하는 회원 정보를 반환하는 메서드
	Member getMember(String id);
}
