package com.springstudy.practice.dao;

import com.springstudy.practice.domain.Member;

public interface MemberDao {
	public void addMember(Member member);
	
	public Member getMember(String id);
}