package com.springstudy.bbs.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springstudy.bbs.domain.Member;

@Repository
public class MemberDaoImpl implements MemberDao {
	
	private static final String NAME_SPACE = "com.springstudy.bbs.mapper.MemberMapper";
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public Member getMember(String id) {		
		return sqlSession.selectOne(NAME_SPACE + ".getMember", id);
	}

	@Override
	public void addMember(Member member) {
		sqlSession.insert(NAME_SPACE + ".addMember", member);
	}

	@Override
	public String memberPassCheck(String id) {
		return sqlSession.selectOne(NAME_SPACE + ".memberPassCheck", id);
	}

	@Override
	public void updateMember(Member member) {
		sqlSession.update(NAME_SPACE + "updateMember", member);
	}
}