package com.springstudy.bbs.dao;

import com.springstudy.bbs.domain.Member;


public interface MemberDao {
	
	/**
	 * 한 명의 회원 정보를 반환하는 메서드
	 * @param id는 회원 아이디
	 * @return id에 해당하는 회원 정보를 DB에서 읽어와 Member 객체로 반환
	 **/
	public Member getMember(String id);
}
