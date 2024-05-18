package com.springstudy.practice.service;

import com.springstudy.practice.domain.Member;

public interface MemberService {
	public int login(String id, String pass);
	public Member getMember(String id);
}
