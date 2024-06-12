package com.springstudy.blog.domain;

import java.sql.Timestamp;

// 포스트에 작성된 댓글을 저장하는 도메인 클래스
public class PostReply {
	
	private int replyNo;
	private String content;
	private String writer;
	private Timestamp regDate;
	private int postNo;

	public PostReply() {}
	public PostReply(int replyNo, String content, 
			String writer, Timestamp regDate, int postNo) {
		
		this.replyNo = replyNo;
		this.content = content;
		this.writer = writer;
		this.regDate = regDate;
		this.postNo = postNo;		
	}
	
	public int getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}	
}
