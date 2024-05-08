package com.springstudy.ch03.declaration;

import org.springframework.stereotype.Component;

@Component("messageBean")
public class MessageBeanAspectJAnnotation {
		
	private String name;	
	
	public void setName(String name) {
		this.name = name;
	}
	public void messageDisplay() {
		System.out.println(
				"messageBean의 messageDisplay() : 안녕하세요 " + name + "님!");
	}
	public void messageDisplay(String name) {
		System.out.println(
				"messageBean의 messageDisplay(name) : 안녕하세요 " + name + "님!");
	}
	public void messageHello() {
		System.out.println(
				"messageBean의 messageHello() : 안녕하세요 " + name + "님!");
	}
	public void messagePrint(String name) {
		System.out.println(
				"messageBean의 messageHello(name) : 안녕하세요 " + name + "님!");
	}
}
