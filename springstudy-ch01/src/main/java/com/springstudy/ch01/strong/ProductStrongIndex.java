package com.springstudy.ch01.strong;

import java.util.ArrayList;

import com.springstudy.ch01.domain.Product;

public class ProductStrongIndex {
	
	public static void main(String[] args) {
		
		ProductService service = new ProductService();
		ArrayList<Product> pList = service.getProductList();
		
		for(Product p : pList) {
			System.out.println(p);
		}
	}
}
