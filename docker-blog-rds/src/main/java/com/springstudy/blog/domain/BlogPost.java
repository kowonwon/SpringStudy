package com.springstudy.blog.domain;

import java.sql.Timestamp;

// 블로그 포스트 정보를 저장하는 도메인 클래스
public class BlogPost {
	
	private int postNo;
	private String title;
	private String writer;
	private String content;
	private String mainImg;
	private String file1;
	private int readCount;
	private Timestamp regDate;
	private Timestamp modDate;
	private int categoryNo;
	
	public BlogPost() { }
	public BlogPost(int postNo, String title, String writer, 
			String content, String mainImg, String file1, int readCount,  
			Timestamp regDate, Timestamp modDate, int categoryNo) {
		
		this.postNo = postNo;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.mainImg = mainImg;
		this.file1 = file1;
		this.readCount = readCount;
		this.regDate = regDate;
		this.modDate = modDate;		
		this.categoryNo = categoryNo;
	}
	
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMainImg() {
		return mainImg;
	}
	public void setMainImg(String mainImg) {
		this.mainImg = mainImg;
	}
	public String getFile1() {
		return file1;
	}
	public void setFile1(String file1) {
		this.file1 = file1;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	public Timestamp getModDate() {
		return modDate;
	}
	public void setModDate(Timestamp modDate) {
		this.modDate = modDate;
	}
	public int getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}
}
