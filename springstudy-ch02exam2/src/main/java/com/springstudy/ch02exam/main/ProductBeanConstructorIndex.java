package com.springstudy.ch02exam.main;

import java.util.ArrayList;

import org.springframework.context.ApplicationContext;

import com.springstudy.ch02exam.domain.Product;
import com.springstudy.ch02exam.service.ProductService;

public class ProductBeanConstructorIndex {
	public static void main(String[] args) {
		ApplicationContext ctx = new GernericXmlApplicationContext(
				"classpath:config/ProductBeanConstructorContext.xml");
		
		ProductService service = (ProductService) ctx.getBean("memberService");
		
		ArrayList<Product> productList = service.getProductList();
		System.out.println("## 회원 리스트 - 생성자 주입 ##");
		for(Product p : productList) {
			System.out.println(p);
		}
	}
}