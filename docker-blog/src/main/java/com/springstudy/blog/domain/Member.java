package com.springstudy.blog.domain;

import java.sql.Timestamp;

// 회원 정보를 저장하는 도메인 클래스
public class Member {
	
	private String memberId;
	private String name;
	private String nickname;
	private String pass;
	private String email;
	private String mobile;
	private String zipcode;
	private String address1;
	private String address2;
	private Timestamp regDate;
	
	public Member() {}
	public Member(String memberId, String name, String nickname,
					String pass, String email, String mobile, String zipcode,
					String address1, String address2, Timestamp regDate) {
		
		this.memberId = memberId;
		this.name = name;
		this.nickname = nickname;
		this.pass = pass;
		this.email = email;
		this.mobile = mobile;
		this.zipcode = zipcode;
		this.address1 = address1;
		this.address2 = address2;
		this.regDate = regDate;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
}
