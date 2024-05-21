package com.springstudy.practice.service;

import com.springstudy.practice.domain.Member;

public interface MemberService {
	public boolean overlapIdCheck(String id);
	public void addMember(Member member);
	public int login(String id, String pass);
	public Member getMember(String id);
}
