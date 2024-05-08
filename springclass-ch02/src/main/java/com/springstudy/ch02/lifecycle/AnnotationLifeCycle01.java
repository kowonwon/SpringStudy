package com.springstudy.ch02.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AnnotationLifeCycle01 {
	
	protected static final Logger logger =
			Logger.getLogger(BeanLifeCycle01.class);
	
	private String name;
	private int age;
	
	@Autowired
	public void setName(String name) {
		this.name = name;
	}
	
	@Autowired
	public void setAge(int age) {
		this.age = age;
	}
	
	@PostConstruct
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
	
	@PreDestroy
	public void beanDestroy() {
		if(logger.isDebugEnabled()) {
			logger.debug("BeanLifeCycle01 - Destroy 시작");
		}
		System.out.println("애플리케이션 소멸화에 필요한 작업을 수행");
		
		if(logger.isDebugEnabled()) {
			logger.debug("BeanLifeCycle01 - Destroy 종료");
		}
	}
	
	public static void main(String[] args) {
		if(logger.isDebugEnabled()) {
			logger.debug("main() 시작");
		}
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(
				"classpath:config/lifecycle/AnnotationLifeCycle01.xml");
		AnnotationLifeCycle01 bean1 = ctx.getBean(AnnotationLifeCycle01.class);
		
		ctx.close();
		
		if(logger.isDebugEnabled()) {
			logger.debug("main() 종료");
		}
	}
}