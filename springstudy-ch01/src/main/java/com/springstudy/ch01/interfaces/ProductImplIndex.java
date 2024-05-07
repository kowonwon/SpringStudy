package com.springstudy.ch01.interfaces;

import java.util.ArrayList;

import com.springstudy.ch01.domain.Product;

public class ProductImplIndex {
	
	public static void main(String[] args) {
		
		ProductService service = new ProductServiceImpl01();
		ArrayList<Product> pList = service.getProductList();
		
		for(Product p : pList) {
			System.out.println(p.toString());
		}
	}	
}
