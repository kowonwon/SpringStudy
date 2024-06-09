package com.springstudy.after.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.springstudy.after.domain.Board;

@Repository
public class BoardDaoImpl implements BoardDao{

	private final String NAME_SPACE = "com.springstudy.after.mapper.BoardMapper";
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<Board> boardList() {
		return sqlSession.selectList(NAME_SPACE + ".boardList");
	}

	@Override
	public Board getBoard(int no) {
		return null;
	}

	@Override
	public void insertBoard(Board board) {
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