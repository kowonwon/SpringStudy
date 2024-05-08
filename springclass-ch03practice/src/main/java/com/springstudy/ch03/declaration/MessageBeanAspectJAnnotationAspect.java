package com.springstudy.ch03.declaration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
public class MessageBeanAspectJAnnotationAspect {
	private final static Log logger =
			LogFactory.getLog(MessageBeanAspectJAnnotationAspect.class);
	
	@Pointcut("execution("
			+ "* com.springstudy.ch03.declaration..*Display(String)) && args(name)")
	public void argPointcut(String name) {}
	
	@Pointcut("bean(messageBean*)")
	public void beanScopePointcut() {}
	
}