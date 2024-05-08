package com.springstudy.ch04.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springstudy.ch04.dao.FirstMvcDao;

//이 클래스가 서비스 계층(비즈니스 로직)의 컴포넌트(Bean) 임을 선언하고 있다.
@Service
public class FirstMvcServiceImpl implements FirstMvcService {

	/* 	인스턴스 필드에 @Autowired annotation을 사용하면 접근지정자가 
	 * private이고 setter 메서드가 없다 하더라도 문제없이 주입 된다.
	 * 하지만 우리는 항상 setter 메서드를 준비하는 습관을 들일 수 있도록 하자.
	 * 
	 * setter 주입 방식은 스프링이 기본 생성자를 통해 이 클래스의 인스턴스를
	 * 생성한 후 setter 주입 방식으로 BoardDao 타입의 객체를 주입하기 때문에  
	 * 기본 생성자가 존재해야 하지만 이 클래스에 다른 생성자가 존재하지 않으므로
	 * 컴파일러에 의해 기본 생성자가 만들어 진다.
	 **/
	@Autowired
	private FirstMvcDao firstMvcDao;	
	
	public void setFirstMvcDao(FirstMvcDao firstMvcDao) {
		this.firstMvcDao = firstMvcDao;
	}
	
	@Override
	public String getMessage(int no, String id) {		
		return firstMvcDao.getMessage(no, id);
	}
}
