package com.springstudy.ch04.dao;

import org.springframework.stereotype.Repository;

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