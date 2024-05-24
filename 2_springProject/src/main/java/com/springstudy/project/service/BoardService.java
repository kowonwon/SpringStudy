package com.springstudy.project.service;

import java.util.List;

import com.springstudy.project.domain.Budget;

public interface BoardService {
	List<Budget> boardList();
	
	Budget getBoard(int no);
	
	void insertBoard(Budget budget);
	
	void updateBoard(Budget budget);
	
	void deleteBoard(int no);
}
