package com.springstudy.ch03.declaration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopIndex {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"config/AopAnnotationContext.xml");
		
		MessageBeanAspectJAnnotation bean1 =
				ctx.getBean("messageBean", MessageBeanAspectJAnnotation.class);
		System.out.println("### messageBean ##");
		bean1.messageDisplay();
		bean1.messageDisplay("홍길동");
		bean1.messageHello();
		bean1.messagePrint("강감찬");
		bean1.messageDisplay("강감찬");
		
		MessageBeanAnnotation bean2 =
				ctx.getBean("messageAnnotation", MessageBeanAnnotation.class);
		System.out.println("### messageAnnotation ###");
		bean2.messageDisplay();
		bean2.messageDisplay("홍길동");
		bean2.messageHello();
		bean2.messagePrint("강감찬");
		bean2.messageDisplay("강감찬");
	}

}
