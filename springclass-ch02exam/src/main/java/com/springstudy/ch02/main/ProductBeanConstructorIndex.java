package com.springstudy.ch02.main;

import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.springstudy.ch02.domain.Product;
import com.springstudy.ch02.service.ProductService;

public class ProductBeanConstructorIndex {

	public static void main(String[] args) {
		ApplicationContext ctx = new GenericXmlApplicationContext(
				"classpath:config/ProductBeanConstructorContext.xml");
		ProductService service = (ProductService) ctx.getBean("productService");
		
		ArrayList<Product> productList = service.getProductList();
		System.out.println("## 상품 리스트 - 생성자 주입 ##");
		for(Product p : productList) {
			System.out.println(p);
		}
	}
}