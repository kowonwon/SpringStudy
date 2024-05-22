package com.springstudy.practice.service;

import com.springstudy.practice.domain.Member;

public interface MemberService {
	
	public boolean memberPassCheck(String id, String pass);
	
	public void updateMember(Member member);
	
	public boolean overlapIdCheck(String id);
	
	public void addMember(Member member);
	
	public int login(String id, String pass);
	
	public Member getMember(String id);
}
