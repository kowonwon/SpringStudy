package com.springstudy.ch02.main;

import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.springstudy.ch02.domain.Product;
import com.springstudy.ch02.service.ProductService;

public class ProductAnnotationConstructorIndex {

	public static void main(String[] args) {
		ApplicationContext ctx = new GenericXmlApplicationContext(
				"classpath:config/annotation/AnnotationConstructorContext.xml");
		
		ProductService service = ctx.getBean(
				"productServiceImplConstructor", ProductService.class);
		
		ArrayList<Product> productList = service.getProductList();
		System.out.println("## 회원 리스트 - Annotation 생성자 주입 ##");
		for(Product p : productList) {
			System.out.println(p);
		}
	}
}