package com.springstudy.bbs.dao;

import java.util.List;

import com.springstudy.bbs.domain.Board;

public interface BoardDao {
	
	public abstract List<Board> boardList();
	
	public abstract Board getBoard(int no);
	
	public String isPassCheck(int no, String pass);
	
	public abstract void updateBoard(Board board);
	
	public abstract void deleteBoard(int no);
}