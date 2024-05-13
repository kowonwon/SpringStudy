package com.springstudy.practice.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springstudy.practice.domain.Board;

@Repository
public class BoardDaoImpl implements BoardDao{
	private final String NAME_SPACE = "com.springstudy.practice.mapper.BoardMapper";
	
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<Board> boardList() {
		return sqlSession.selectList(NAME_SPACE + ".boardList");
	}

	@Override
	public Board getBoard(int no) {
		return sqlSession.selectOne(NAME_SPACE + ".getBoard", no);
	}

	@Override
	public String isPassCheck(int no, String pass) {
		return null;
	}

	@Override
	public void updateBoard(Board board) {
	}

	@Override
	public void deleteBoard(int no) {
	}
}