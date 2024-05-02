package com.springstudy.ch02.lifecycle;

import org.apache.log4j.Logger;
import org.springframework.context.support.GenericXmlApplicationContext;

// Bean 초기화 메서드와 소멸화 메서드(XML 설정)
public class BeanLifeCycle01 {
	
	/* 로깅 작업을 위해 log4j 라이브러리가 제공하는 Logger 클래스를
	 * 통해 Logger 객체를 얻는다. getLogger()의 인수로 로깅 작업을
	 * 할 클래스 타입을 지정하거나 클래스 명을 String으로 지정할 수 있다.
	 **/		
	protected static final Logger logger = 
			Logger.getLogger(BeanLifeCycle01.class);
	
	/* 아래와 같이 commons-logging 라이브러리를 log4j 설정에 맞게 로깅
	 * 처리를 할 수도 있다. org.apache.commons.logging.LogFactory 
	 * 클래스를 통해 Log 객체를 얻는다. getLog()의 인수로 로깅 작업을
	 * 할 클래스 타입을 지정하거나 클래스 명을 String으로 지정할 수 있다.
	 **/	
	/*
	protected static final Log logger = 
			LogFactory.getLog(BeanLifeCycle01.class);
	*/	
		
	private String name;
	private int age;
	
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	/* 초기화 콜백 메서드 - bean 설정에서 이 메서드를 초기화 콜백 메서드로 지정했다.
	 * 스프링이 이 클래스의 기본 생성자로 bean을 생성하고 프로퍼티에 의존성을
	 * 주입한 직후에 init-method에 지정된 이 메서드가 호출되는 순서로 진행된다.
	 **/	
	public void beanInit() {
		
		// 로그 레벨이 DEBUG 레벨 이상이면 로그를 출력한다.
		if(logger.isDebugEnabled()) {
			logger.debug("BeanLifeCycle01 - initMethod 시작");
			logger.debug("name : " + name + ", age : " + age);
		}
		
		System.out.println("애플리케이션 초기화에 필요한 작업을 수행");
				
		// 로그 레벨이 DEBUG 레벨 이상이면 로그를 출력한다.
		if(logger.isDebugEnabled()) {
			logger.debug("BeanLifeCycle01 - initMethod 종료");
		}
	}
	
	/* 소멸화 콜백 메서드 - bean 설정에서 이 메서드를 소멸화 콜백 메서드로 지정했다.
	 * 스프링이 이 클래스의 싱글턴 인스턴스를 소멸시키기 직전에 <bean> 태그에
	 * destroy-method 속성에 지정된 이 메서드를 호출해 준다.
	 * 소멸화 콜백 메서드의 이름이 정해져 있는 것이 아니라 소멸화 메서드로 사용할
	 * 메서드 이름을 <bean> 태그의 destroy-method에 지정해 주면 된다. 
	 * 소멸 메서드에는 주로 사용한 자원을 반납하거나 닫는 코드를 기술한다.
	 **/	
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
		
		// 로그 레벨이 DEBUG 레벨 이상이면 로그를 출력한다.
		if(logger.isDebugEnabled()) {
			logger.debug("main() 시작"); 
		}
		
		// GenericXmlApplicationContext는 ApplicationContext의 구현체 이다.
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(
				"classpath:config/lifecycle/BeanLifeCycle01.xml");
		
		BeanLifeCycle01 bean1 = (BeanLifeCycle01) ctx.getBean(
						"beanInit01", BeanLifeCycle01.class);
		
		/* close() 메서드는 현재 실행중인 DI 컨테이너를 종료하는 메서드로
		 * 스프링은 빈 컨테이너가 종료되기 직전에 모든 싱글턴 bean을 소멸 시킨다.
		 * 이 때 bean이 가지고 있는 소멸화 메서드를 호출한다. 
		 **/		
		ctx.close();
		
		// 로그 레벨이 DEBUG 레벨 이상이면 로그를 출력한다.
		if(logger.isDebugEnabled()) {
			logger.debug("main() 종료"); 
		}
	}
}
