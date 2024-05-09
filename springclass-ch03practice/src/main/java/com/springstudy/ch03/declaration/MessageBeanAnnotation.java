package com.springstudy.ch03.declaration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("messageAnnotation")
public class MessageBeanAnnotation {

	private String name;
	
	@Autowired
	public void setName(String name) {
		this.name = name;
	}
	public void messageDisplay() {
		System.out.println(
				"messageAnnotation의 messageDisplay() : 안녕하세요 " + name + "님!");
	}
	public void messageDisplay(String name) {
		System.out.println(
				"messageAnnotation의 messageDisplay(name) : 안녕하세요 " + name + "님!");
	}
	public void messageHello() {
		System.out.println(
				"messageAnnotation의 messageHello() : 안녕하세요 " + name + "님!");
	}
	public void messagePrint(String name) {
		System.out.println(
				"messageAnnotation의 messageHello(name) : 안녕하세요 " + name + "님!");
	}
}
