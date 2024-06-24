package com.springstudy.after.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springstudy.after.dao.BoardDao;
import com.springstudy.after.domain.Board;
import com.springstudy.after.domain.Payment;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardDao boardDao;

	@Override
	public List<Board> boardList() {
		return boardDao.boardList();
	}

	@Override
	public Board getBoard(int no) {
		return null;
	}

	@Override
	public void insertBoard(Board board) {
	}

	@Override
	public boolean isPassCheck(int no, String pass) {
		return false;
	}

	@Override
	public void updateBoard(Board board) {
	}

	@Override
	public void deleteBoard(int no) {
	}

	// payment
	@Override
	public void insertPayment(Payment payment) {
		boardDao.insertPayment(payment);
	}
}