package com.springstudy.ch04.dao;

import org.springframework.stereotype.Repository;

// 이 클래스가 데이터 액세스(데이터 저장소) 계층의 컴포넌트(Bean) 임을 선언한다.
@Repository
public class FirstMvcDaoImpl implements FirstMvcDao {

	@Override
	public String getMessage(int no, String id) {
		
		String msg = null;
		
		if(id.equals("spring")) {
			msg = "안녕 스프링 - " + no;
		} else {
			msg = "Hello Spring - " + no;
		}
		
		return msg;
	}
}
