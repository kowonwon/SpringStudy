package com.springstudy.bbs.dao;

import java.util.List;

import com.springstudy.bbs.domain.Board;
import com.springstudy.bbs.domain.Reply;

public interface BoardDao {
	
	// DB에 댓글을 추가하는 메서드
	void addReply(Reply reply);
	
	// 게시 글 번호에 해당하는 추천/탱큐를 업데이트하는 메서드
	void updateRecommend(int no, String recommend);
	
	// 게시 글 번호에 해당하는 추천/탱큐 수를 읽어오는 메서드
	Board getRecommend(int no);
	
	// 게시 글 번호에 해당하는 댓글 리스트를 가져오는 메소드
	public abstract List<Reply> replyList(int no);
	
	// 게시글 읽은 횟수를 증가시키는 메서드
	public abstract void incrementReadCount(int no);

	// 전체 게시 글의 수를 읽어오는 메서드 - paging 처리에 필요
	public abstract int getBoardCount(String type, String keyword);
	
	// 게시글 리스트 - 테이블에 있는 게시 글 리스트를 읽어와 반환하는 메서드
	public abstract List<Board> boardList(
			int startRow, int num, String type, String keyword);
	
	// 게시 글 상세보기 - no 해당하는 게시글 하나를 읽어와 반환하는 메서드
	Board getBoard(int no);
	
	// 게시글 추가하는 메서드
	void insertBoard(Board board);
	
	// 게시글 비밀번호 체크 메서드
	String isPassCheck(int no, String pass);
	
	// 게시글 수정하는 메서드
	void updateBoard(Board board);
	
	// 게시글 삭제하는 메서드
	void deleteBoard(int no);
}
