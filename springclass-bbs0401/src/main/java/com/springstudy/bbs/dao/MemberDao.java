package com.springstudy.bbs.dao;

import com.springstudy.bbs.domain.Member;

public interface MemberDao {
	
	// 회원 정보 수정 시에 기존 비밀번호가 맞는지 체크하는 메서드
	public String memberPassCheck(String id);
	
	// 회원 정보를 member 테이블에서 수정하는 메서드
	public void updateMember(Member member);
	
	// id에 해당하는 회원 정보를 DB 테이블에서 읽어와 반환하는 메서드
	public abstract Member getMember(String id);
	
	// 회원 가입 - 회원 정보를 DB 테이블에 저장하는 메서드
	public abstract void addMember(Member member);
}
