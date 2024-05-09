package com.springstudy.bbs.dao;

import java.util.List;

import com.springstudy.bbs.domain.Board;

public interface BoardDao {
	
	/* 한 페이지에 보여 질 게시 글 리스트 요청 시 호출되는 메소드
	 * 현재 페이지에 해당하는 게시 글 리스트를 DB에서 읽어와 반환 하는 메소드
	 **/
	public abstract List<Board> boardList();
	
	/* 게시 글 상세보기 요청 시 호출되는 메서드
	 * no에 해당하는 게시 글 을 DB에서 읽어와 Board 객체로 반환 하는 메서드 
	 **/
	public abstract Board getBoard(int no);
	
	/* 게시 글쓰기 요청 시 호출되는 메서드
	 * 게시 글쓰기 요청 시 게시 글 내용을 Board 객체로 받아 DB에 추가하는 메서드 
	 **/
	public abstract void insertBoard(Board board);
	
	/* 게시 글 수정과 삭제 할 때 비밀번호 체크에서 호출되는 메서드 
	 * 게시 글 수정, 삭제 시 no에 해당하는 비밀번호를 DB에서 읽어와 반환하는 메서드
	 **/
	public String isPassCheck(int no, String pass);
	
	/* 게시 글 수정 요청 시 호출되는 메서드
	 * 게시 글 수정 요청 시 수정된 내용을 Board 객체로 받아 DB에 수정하는 메서드 
	 **/
	public abstract void updateBoard(Board board);
	
	/* 게시 글 삭제 요청 시 호출되는 메서드 
	 * no에 해당 하는 게시 글을 DB에서 삭제하는 메서드 
	 **/
	public abstract void deleteBoard(int no);
}
