package com.springstudy.practice.service;

import java.util.List;
import java.util.Map;

import com.springstudy.practice.domain.Board;

public interface BoardService {
	
	public abstract Board getBoard(int no, boolean isCount);

	public abstract Map<String, Object> boardList(int pageNum, String type, String keyword);
	
	public abstract Board getBoard(int no);
	
	public void insertBoard(Board board);
	
	public boolean isPassCheck(int no, String pass);
	
	public abstract void updateBoard(Board board);
	
	public abstract void deleteBoard(int no);
}
