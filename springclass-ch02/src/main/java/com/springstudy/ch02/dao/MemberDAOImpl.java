package com.springstudy.ch02.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.springstudy.ch02.domain.Member;

public class MemberDAOImpl implements MemberDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	// 스프링이 제공하는 DriverManagerDataSource 객체 타입의 멤버 선언
	
	// 생성자 주입일 때
	// 스프링이 제공하는 DriverManagerDataSource 객체를 주입받는 생성자 필요
	
	
	// 셋터 주입일 때
	// 스프링이 제공하는 DriverManagerDataSource 객체를 주입받는 셋터 메서드 필요
	
	
	@Override
	public ArrayList<Member> getMemberList() {
		
		String selectAllMember = "SELECT * FROM member;";
		ArrayList<Member> memberList = null;
		
		try {
			
			pstmt = conn.prepareStatement(selectAllMember);
			rs = pstmt.executeQuery();
			
			memberList = new ArrayList<Member>();
			
			while(rs.next()) {
				
				Member member = new Member();
				member.setId(rs.getString("id"));
				member.setName(rs.getString("name"));
				member.setPass(rs.getString("pass"));
				member.setAge(rs.getInt("age"));
				member.setEmail(rs.getString("email"));
				member.setRegDate(rs.getTimestamp("reg_date"));
				
				memberList.add(member);
			}			
			
		} catch(SQLException e) {
			e.printStackTrace();
			
		} finally {			
			try {				
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();				
			} catch(SQLException e) { }
		}
		return memberList;
	}
}
