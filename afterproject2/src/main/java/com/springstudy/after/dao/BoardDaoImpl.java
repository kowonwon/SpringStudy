package com.springstudy.after.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springstudy.after.domain.Board;
import com.springstudy.after.domain.Lecture;
import com.springstudy.after.domain.Payment;

@Repository
public class BoardDaoImpl implements BoardDao{

	private final String NAME_SPACE = "com.springstudy.after.mapper.BoardMapper";
	
	@Autowired
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

	@Override
	public void insertPayment(Payment payment) {
		sqlSession.insert(NAME_SPACE + ".insertPayment", payment);
	}

	@Override
	public Lecture getLecture(int lectureId) {
		return sqlSession.selectOne(NAME_SPACE + ".getLecture", lectureId);
	}
}