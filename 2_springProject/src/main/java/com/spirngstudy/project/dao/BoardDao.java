package com.spirngstudy.project.dao;

import java.util.List;

import com.springstudy.project.domain.Budget;

public interface BoardDao {
	List<Budget> boardList();
	
	Budget getBoard(int no);
	
	void insertBoard(Budget budget);
	
	String isPassCheck(int no, String pass);
	
	void updateBoard(Budget budget);
	
	void deleteBoard(int no);
}
