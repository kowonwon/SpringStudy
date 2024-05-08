package com.springstudy.ch02.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.springstudy.ch02.domain.Member;

public class MemberDAOImpl implements MemberDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DriverManagerDataSource dataSource;
		
	// 스프링 DI 컨테이너로 부터 DriverManagerDataSource 객체를 주입받는 생성자
	public MemberDAOImpl(DriverManagerDataSource dataSource) {
		this.dataSource = dataSource;
	}

	// 기본 생성자 - Property 주입할 때 필요함
	public MemberDAOImpl() {}
	
	// 스프링 DI 컨테이너로 부터 DriverManagerDataSource 객체를 주입받는 setter 메서드
	public void setDataSource(DriverManagerDataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public ArrayList<Member> getMemberList() {
		
		String selectAllMember = "SELECT * FROM member;";
		ArrayList<Member> memberList = null;
		
		try {
			conn = dataSource.getConnection();
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
