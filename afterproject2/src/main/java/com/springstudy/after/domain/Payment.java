package com.springstudy.after.domain;

import java.sql.Timestamp;

public class Payment {
	private String payCode;
	private Timestamp payDate;
	private String payWay;
	private int price;
	private String studentId;
	private int lectureId;
	
	public Payment() {}
	
	public Payment(String payCode, Timestamp payDate, String payWay, int price, String studentId, int lectureId) {
		this.payCode = payCode;
		this.payDate = payDate;
		this.payWay = payWay;
		this.price = price;
		this.studentId = studentId;
		this.lectureId = lectureId;
	}
	
	public String getPayCode() {
		return payCode;
	}
	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}
	public Timestamp getPayDate() {
		return payDate;
	}
	public void setPayDate(Timestamp payDate) {
		this.payDate = payDate;
	}
	public String getPayWay() {
		return payWay;
	}
	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public int getLectureId() {
		return lectureId;
	}
	public void setLectureId(int lectureId) {
		this.lectureId = lectureId;
	}
}