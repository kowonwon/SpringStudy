package com.springstudy.practice.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public void incrementReadCount(int no) {
		sqlSession.update(NAME_SPACE + ".incrementReadCount", no);
	}

	@Override
	public List<Board> boardList(int startRow, int num) {
		
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("startRow", startRow);
		params.put("num", num);
		
		return sqlSession.selectList(NAME_SPACE + ".boardList", params);
	}
	
	@Override
	public int getBoardCount() {
		return sqlSession.selectOne(NAME_SPACE + ".getBoardCount");
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
		sqlSession.update(NAME_SPACE + ".updateBoard", board);
	}

	@Override
	public void deleteBoard(int no) {
		sqlSession.delete(NAME_SPACE + ".deleteBoard", no);
	}
}