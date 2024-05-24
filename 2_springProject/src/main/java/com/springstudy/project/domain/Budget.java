package com.springstudy.project.domain;

import java.sql.Timestamp;

public class Budget {
	private int no;
	private String writer;
	private String category;
	private String content;
	private int price;
	private Timestamp date;
	private String evaluation;
	private int good;
	private int bad;
	
	public Budget() {}
	public Budget(int no, String writer, String category, String content, int price, Timestamp date, String evaluation, int good,
			int bad) {
		this.no = no;
		this.writer = writer;
		this.category = category;
		this.content = content;
		this.price = price;
		this.date = date;
		this.evaluation = evaluation;
		this.good = good;
		this.bad = bad;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}
	public int getGood() {
		return good;
	}
	public void setGood(int good) {
		this.good = good;
	}
	public int getBad() {
		return bad;
	}
	public void setBad(int bad) {
		this.bad = bad;
	}
}