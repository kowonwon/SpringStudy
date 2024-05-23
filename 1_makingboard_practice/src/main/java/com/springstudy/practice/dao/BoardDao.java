package com.springstudy.practice.dao;

import java.util.List;

import com.springstudy.practice.domain.Board;
import com.springstudy.practice.domain.Reply;

public interface BoardDao {
	
	public void updateReply(Reply reply);
	
	public void addReply(Reply reply);
	
	void updateRecommend(int no, String recommend);
	
	Board getRecommend(int no);
	
	List<Reply> replyList(int no);
	
	public abstract void incrementReadCount(int no);
	
	public abstract List<Board> boardList(int startRow, int num, String type, String keyword);
	
	public abstract int getBoardCount(String type, String keyword);
	
	public abstract Board getBoard(int no);
	
	void insertBoard(Board board);
	
	public String isPassCheck(int no, String pass);
	
	public abstract void updateBoard(Board board);
	
	public abstract void deleteBoard(int no);
}