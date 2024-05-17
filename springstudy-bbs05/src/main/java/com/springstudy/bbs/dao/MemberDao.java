package com.springstudy.bbs.dao;

import com.springstudy.bbs.domain.Member;


public interface MemberDao {

	/**
	 * 한 명의 회원 정보를 반환하는 메서드
	 * @param id는 회원 아이디
	 * @return id에 해당하는 회원 정보를 DB에서 읽어와 Member 객체로 반환
	 **/
	public Member getMember(String id);

	// 회원 정보를 회원 테이블에 저장하는 메서드
	public void addMember(Member member);
	
	// 회원 정보 수정 시에 기존 비밀번호가 맞는지 체크하는 메서드
	public String memberPassCheck(String id);
	
	// 회원 정보를 회원 테이블에서 수정하는 메서드
	public void updateMember(Member member);
}
