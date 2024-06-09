package com.springstudy.after.dao;

import java.util.List;

import com.springstudy.after.domain.Board;

public interface BoardDao {
	List<Board> boardList();
	Board getBoard(int no);
	void insertBoard(Board board);
	String isPassCheck(int no, String pass);
	void updateBoard(Board board);
	void deleteBoard(int no);
}