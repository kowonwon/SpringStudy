package com.springstudy.bbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springstudy.bbs.dao.BoardDao;
import com.springstudy.bbs.domain.Board;

@Service
public class BoardServiceImpl implements BoardService{

	// BoardDao 주입
	@Autowired
	private BoardDao boardDao;
	
	@Override
	public List<Board> boardList() {
		return boardDao.boardList();
	}

	@Override
	public Board getBoard(int no) {
		return boardDao.getBoard(no);
	}

	@Override
	public void insertBoard(Board board) {
		boardDao.insertBoard(board);
	}

	@Override
	public Boolean isPassCheck(int no, String pass) {
		boolean result = false;
		String dbPass = boardDao.isPassCheck(no, pass);
		if(dbPass.equals(pass)) {
			result = true;
		}
		return result;
	}

	@Override
	public void updateBoard(Board board) {
	}

	@Override
	public void deleteBoard(int no) {
	}
	
}