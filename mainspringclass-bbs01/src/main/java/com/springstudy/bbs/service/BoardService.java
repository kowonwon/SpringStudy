package com.springstudy.bbs.service;

import java.util.List;

import com.springstudy.bbs.domain.Board;

public interface BoardService {
	//게시글 리스트 - 테이블에 있는 게시 글 리스트를 읽어와 반환하는 메서드
	public abstract List<Board> boardList();
	
	// 게시글 상세보기 - no 해당하는 게시글 하나를 읽어와 반환하는 메서드
	Board getBoard(int no);
	
	// 게시글 추가 메서드
	void insertBoard(Board board);
	
	// 게시글 비밀번호 체크 메서드
	Boolean isPassCheck(int no, String pass);
	
	// 게시글 수정 메서드
	void updateBoard(Board board);
	
	// 게시글 삭제 메서드
	void deleteBoard(int no);
}