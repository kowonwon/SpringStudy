package com.springstudy.ch03.declaration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MessageBeanAspectJAnnotationAspect {
	private final static Log logger =
			LogFactory.getLog(MessageBeanAspectJAnnotationAspect.class);
	
	@Pointcut("execution("
			+ "* com.springstudy.ch03.declaration..*Display(String)) && args(name)")
	public void argPointcut(String name) {}
	
	@Pointcut("bean(messageBean*)")
	public void beanScopePointcut() {}
	
	@Before("execution(* com.springstudy.ch03.declaration..*Display(String))")
	public void messageBeforeAdvice(JoinPoint joinPoint) throws Throwable {
		if(logger.isDebugEnabled()) {
			logger.debug("before - default : "
					+ joinPoint.getSignature().getDeclaringTypeName()
					+ "." + joinPoint.getSignature().getName());
		}
	}
	
	@Before("argPointcut(name) && beanScopePointcut()")
	public void messageBeforeAdvice(
			JoinPoint joinPoint, String name) throws Throwable {
		if(name.equals("강감찬")) {
			if(logger.isDebugEnabled()) {
				logger.debug("Before - name : "
						+ joinPoint.getSignature().getDeclaringTypeName()
						+ "." + joinPoint.getSignature().getName() + " - arg : " + name);
			}
		}
		
	}
	
	@Around("execution(* message*(String)) "
			+ "&& args(name) && beanScopePointcut()")
	public Object messageAroundAdvice(
			ProceedingJoinPoint joinPoint, String name) throws Throwable {
		Object obj = null;
		if(logger.isDebugEnabled()) {
			logger.debug("Around - Before: "
					+ joinPoint.getSignature().getDeclaringTypeName()
					+ "." + joinPoint.getSignature().getName());
		}
		obj = joinPoint.proceed();
		if(logger.isDebugEnabled()) {
			logger.debug("Before - After : "
					+ joinPoint.getSignature().getDeclaringTypeName()
					+ "." + joinPoint.getSignature().getName() + " - arg : " + name);
		}
		return obj;
	}
}
