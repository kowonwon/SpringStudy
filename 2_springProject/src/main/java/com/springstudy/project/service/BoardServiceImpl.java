package com.springstudy.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spirngstudy.project.dao.BoardDao;
import com.springstudy.project.domain.Budget;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	public void setBoardDao(BoardDao boardDao) {
		this.boardDao = boardDao;
	}

	@Override
	public List<Budget> boardList() {
		return boardDao.boardList();
	}

	@Override
	public Budget getBoard(int no) {
		return null;
	}

	@Override
	public void insertBoard(Budget budget) {
	}

	@Override
	public void updateBoard(Budget budget) {
	}

	@Override
	public void deleteBoard(int no) {
	}
}