package com.springstudy.after.service;

import java.util.List;

import com.springstudy.after.domain.Board;

public interface BoardService {
	List<Board> boardList();
	Board getBoard(int no);
	void insertBoard(Board board);
	boolean isPassCheck(int no, String pass);
	void updateBoard(Board board);
	void deleteBoard(int no);
}
