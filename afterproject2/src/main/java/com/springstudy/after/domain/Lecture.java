package com.springstudy.after.domain;

public class Lecture {
	private int lectureId;
	private String lectureTitle;
	private String lectureDesc;
	private String instructorId;
	private int price;
	
	public Lecture() {}
	
	public Lecture(int lectureId, String lectureTitle, String lectureDesc, String instructorId, int price) {
		this.lectureId = lectureId;
		this.lectureTitle = lectureTitle;
		this.lectureDesc = lectureDesc;
		this.instructorId = instructorId;
		this.price = price;
	}

	public int getLectureId() {
		return lectureId;
	}

	public void setLectureId(int lectureId) {
		this.lectureId = lectureId;
	}

	public String getLectureTitle() {
		return lectureTitle;
	}

	public void setLectureTitle(String lectureTitle) {
		this.lectureTitle = lectureTitle;
	}

	public String getLectureDesc() {
		return lectureDesc;
	}

	public void setLectureDesc(String lectureDesc) {
		this.lectureDesc = lectureDesc;
	}

	public String getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(String instructorId) {
		this.instructorId = instructorId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
