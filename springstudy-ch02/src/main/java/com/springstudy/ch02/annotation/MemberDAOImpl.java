package com.springstudy.ch02.annotation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import com.springstudy.ch02.dao.MemberDAO;
import com.springstudy.ch02.domain.Member;

// 이 클래스가 스프링 컴포넌트임을 선언하고 "memberDAOAnno"라는 빈 이름을 지정한다.
//@Component("memberDAOAnno")

/* @Component 대신에 이를 확장한 Annotation인 @Controller, @Service, 
 * @Repository 등을 사용해 클래스의 용도에 따라 스프링 빈 임을 정의할 수 있다.
 * 
 * 아래는 @Repository Annotation을 이용해 데이터 저장소 계층의 스프링
 * 빈임을 선언하고 있다. 빈의 이름은 별도로 부여하지 않았으므로 스프링이 알아서
 * 클래스 이름을 카멜 케이싱한 "memberDAOImpl"의 이름을 부여한다.
 **/
@Repository
public class MemberDAOImpl implements MemberDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DriverManagerDataSource dataSource;
	
	/* 스프링 DI 컨테이너로 부터 DriverManagerDataSource 객체를 주입받는 생성자
	 * 아래와 같이 @Autowired를 생성자에 붙이면 이 생성자를 통해 객체를 주입 받을 수 있다.
	 **/
	// setter 주입일 때는 아래의 애노테이션을 주석처리한다.
	// @Autowired	
	public MemberDAOImpl(DriverManagerDataSource dataSource) {
		this.dataSource = dataSource;
	}
		
	// 기본 생성자를 새로 추가한다.
	public MemberDAOImpl() {}
	
	/* 스프링 DI 컨테이너로 부터 DriverManagerDataSource 객체를 주입받는 setter 메서드
	 * DriverManagerDataSource 객체를 주입 받을 수 있는 setter 메서드를 새로 추가한다.
	 * 
	 * 이 클래스의 기본 생성자를 통해 객체를 생성하고 setter 메서드로 주입되므로
	 * 이 클래스에 반드시 기본 생성자가 존재해야 한다.
	 **/
	@Autowired
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
				
				Member m = new Member();
				m.setId(rs.getString("id"));
				m.setName(rs.getString("name"));
				m.setPass(rs.getString("pass"));
				m.setAge(rs.getInt("age"));
				m.setEmail(rs.getString("email"));
				m.setRegDate(rs.getTimestamp("reg_date"));
				
				memberList.add(m);				
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
