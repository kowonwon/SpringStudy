package com.springstudy.ch02exam.main;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.springstudy.ch02exam.domain.Product;
import com.springstudy.ch02exam.service.ProductService;

public class productBeanPropertyIndex {

	public static void main(String[] args) {
		ApplicationContext ctx = new GenericXmlApplicationContext(
				"classpath:config/ProductBeanPropertyContext.xml");
		
		ProductService service = ctx.getBean(ProductService.class);
		List<Product> pList = service.getProductList();
		for(Product p : pList) {
			System.out.println(p);
		}
	}
}