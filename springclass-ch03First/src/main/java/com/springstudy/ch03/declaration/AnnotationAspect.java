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
public class AnnotationAspect {
	private static final Log logger =
			LogFactory.getLog(AnnotationAspect.class);
	
	// 포인트컷(Pointcut) - 포인트컷 지정자를 이용해 작성
	@Pointcut("execution(* com.springstudy.ch03.declaration..*Display(String)) && args(name)")
	public void argPointcut(String name) {}
	
	@Pointcut("bean(messageBean*)")
	public void beanScopePointcut() {}
	
	// 어드바이스(Before Advice, AfterReturning Advice, After Advice,
	//				AfterThrowing Advice, Around Advice)
	
	@Before("execution(* com.springstudy.ch03.declaration..*Display(String))")
	public void messageBeforeAdvice(JoinPoint joinPoint) throws Throwable{
		
		if(logger.isDebugEnabled()) {
			logger.debug("Before - default : "
					+ joinPoint.getSignature().getDeclaringTypeName()
					+ "." + joinPoint.getSignature().getName());
		}
	}
	
	@Before("argPointcut(name) && beanScopePointcut()")
	public void messageBeforeAdvice(JoinPoint joinPoint, String name) throws Throwable {
		if(name.equals("강감찬")) {
			if(logger.isDebugEnabled()) {
				logger.debug("Before - name : "
						+ joinPoint.getSignature().getDeclaringTypeName()
						+ "." + joinPoint.getSignature().getName() + "- arg : " + name);
			}
		}
	}
	
	@Around("execution(* message*(String)) && args(name) && beanScopePointcut()")
	public Object messageAroundAdvice(
			ProceedingJoinPoint joinPoint, String name) throws Throwable {
		
		Object obj = null;
		
		if(logger.isDebugEnabled()) {
			logger.debug("Around - Before : "
					+ joinPoint.getSignature().getDeclaringTypeName()
					+ "." + joinPoint.getSignature().getName());
		}
		
		obj = joinPoint.proceed();
		
		if(logger.isDebugEnabled()) {
			logger.debug("Around - After : "
					+ joinPoint.getSignature().getDeclaringTypeName()
					+ "." + joinPoint.getSignature().getName());
		}
		
		return obj;
	}
}