package com.springstudy.practice.dao;

import com.springstudy.practice.domain.Member;

public interface MemberDao {
	public String memberPassCheck(String id);
	
	public void updateMember(Member member);
	
	public void addMember(Member member);
	
	public Member getMember(String id);
}