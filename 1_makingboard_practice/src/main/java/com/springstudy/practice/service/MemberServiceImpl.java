package com.springstudy.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springstudy.practice.dao.MemberDao;
import com.springstudy.practice.domain.Member;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@Override
	public int login(String id, String pass) {
		Member m = memberDao.getMember(id);
		int result = 1;
		
		if(m == null) {
			return result;
		}
		if(passwordEncoder.matches(pass, m.getPass())) {
			result = 1;
		} else {
			result = 0;
		}
		return result;
	}

	@Override
	public Member getMember(String id) {
		return memberDao.getMember(id);
	}

	@Override
	public boolean overlapIdCheck(String id) {
		Member member = memberDao.getMember(id);
		System.out.println("overlapIdCheck - member : " + member);
		if(member == null) {
			return false;
		}
		return true;
	}

	@Override
	public void addMember(Member member) {
		member.setPass(passwordEncoder.encode(member.getPass()));
		System.out.println(member.getPass());
		memberDao.addMember(member);
	}

}
