package com.springstudy.ch03.declaration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MessageBeanAspectJAnnotationExample {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"config/MessageAspectJAnnotationContext.xml");
		
		MessageBeanAspectJAnnotation bean1 =
				ctx.getBean("messageBean", MessageBeanAspectJAnnotation.class);
		
		System.out.println("## messageBean ##");
		bean1.messageDisplay();
		System.out.println("");
		bean1.messageDisplay("홍길동");
		System.out.println("");
		bean1.messageHello();
		System.out.println("");
		bean1.messagePrint("강감찬");
		System.out.println();
		
		MessageBeanAnnotation bean2 =
				ctx.getBean("messageAnnotation", MessageBeanAnnotation.class);
		System.out.println("## messageAnnotation ##");
		bean2.messageDisplay();
		System.out.println("");
		bean2.messageDisplay("홍길동");
		System.out.println("");
		bean2.messageHello();
		System.out.println("");
		bean2.messagePrint("강감찬");
	}
}
