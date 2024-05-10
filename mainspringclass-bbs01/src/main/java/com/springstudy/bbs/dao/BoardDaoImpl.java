package com.springstudy.bbs.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springstudy.bbs.domain.Board;

@Repository
public class BoardDaoImpl implements BoardDao {

	private final String NAME_SPACE = "com.springstudy.bbs.mapper.BoardMapper";
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<Board> boardList() {
		return sqlSession.selectList(NAME_SPACE + ".boardList");
	}

	@Override
	public Board getBoard(int no) {
		return sqlSession.selectOne(NAME_SPACE + ".getBoard", no);
	}

	@Override
	public void insertBoard(Board board) {
		sqlSession.insert(NAME_SPACE + ".insertBoard", board);
	}

	@Override
	public String isPassCheck(int no, String pass) {
		return sqlSession.selectOne(NAME_SPACE + ".isPassCheck", no);
	}

	@Override
	public void updateBoard(Board board) {

	}

	@Override
	public void deleteBoard(int no) {

	}

}