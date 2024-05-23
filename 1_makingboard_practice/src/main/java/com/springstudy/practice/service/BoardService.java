package com.springstudy.practice.service;

import java.util.List;
import java.util.Map;

import com.springstudy.practice.domain.Board;
import com.springstudy.practice.domain.Reply;

public interface BoardService {
	
	public Map<String, Integer> recommend(int no, String recommend);
	
	List<Reply> replyList(int no);
	
	public abstract Board getBoard(int no, boolean isCount);

	public abstract Map<String, Object> boardList(int pageNum, String type, String keyword);
	
	public abstract Board getBoard(int no);
	
	public void insertBoard(Board board);
	
	public boolean isPassCheck(int no, String pass);
	
	public abstract void updateBoard(Board board);
	
	public abstract void deleteBoard(int no);
}
