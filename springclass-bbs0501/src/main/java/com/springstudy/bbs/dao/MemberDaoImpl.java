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

}
