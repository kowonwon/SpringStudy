package com.springstudy.bbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springstudy.bbs.dao.MemberDao;
import com.springstudy.bbs.domain.Member;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public int login(String id, String pass) {
		
		Member m = memberDao.getMember(id);
		int result = -1;
		if(m == null) { // id에 해당하는 회원이 없음
			return result;
		}
		
		// 회원이 입력한 비밀번호와 db에 저장된 암호화된 비밀번호를 비교
		if(passwordEncoder.matches(pass, m.getPass())) { // 로그인 성공
			result = 1;
		} else { // 비밀번호가 틀림
			result = 0;
		}
		
		return result;
	}

	@Override
	public Member getMember(String id) {		
		return memberDao.getMember(id);
	}

	@Override
	public boolean overlapidCheck(String id) {
		Member member = memberDao.getMember(id);
		if(member == null) {
			return false;
		}
		return true;
	}

	@Override
	public void addMember(Member member) {
		// 회원이 입력한 정보에서 중요정보는 비식별화 - 비밀번호를 암호화
		member.setPass(passwordEncoder.encode(member.getPass()));
		memberDao.addMember(member);
	}

	@Override
	public boolean memberPassCheck(String id, String pass) {
		String dbPass = memberDao.memberPassCheck(id);
		boolean result = false;
		
		if(passwordEncoder.matches(pass, dbPass)) {
			result = true;
		}
		return result;
	}

	@Override
	public void updateMember(Member member) {
		// 회원이 입력한 정보에서 중요정보는 비식별화 - 비밀번호를 암호화
		member.setPass(passwordEncoder.encode(member.getPass()));
		memberDao.updateMember(member);
	}

}