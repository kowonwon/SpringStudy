package com.springstudy.ch02.lifecycle;

import org.apache.log4j.Logger;

public class BeanLifeCycle01 {
	protected static final Logger logger =
			Logger.getLogger(BeanLifeCycle01.class);
	
	private String name;
	private int age;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void beanInit() {
		if(logger.isDebugEnabled()) {
			logger.debug("BeanLifeCycle01 - initMethod 시작");
			logger.debug("name : " + name + ", age : " + age);
		}
		System.out.println("애플리케이션 초기화에 필요한 작업을 수행");
		if(logger.isDebugEnabled()) {
			logger.debug("BeanLifeCycle01 - initMethod 종료");
		}
	}
}