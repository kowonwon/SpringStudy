package com.springstudy.bbs.service;

import java.util.List;

import com.springstudy.bbs.domain.Board;

public interface BoardService {

	public abstract List<Board> boardList();

	public abstract Board getBoard(int no);

	public abstract void insertBoard(Board board);

	public String isPassCheck(int no, String pass);

	public abstract void updateBoard(Board board);
	
	public abstract void deleteBoard(int no);
}