package com.springstudy.ch01.factory;

import java.util.ArrayList;

import com.springstudy.ch01.domain.Product;
import com.springstudy.ch01.interfaces.ProductService;

public class ProductLooseIndex {
	
	public static void main(String[] args) {
		
		ProductService service = ProductFactory.createService();
		ArrayList<Product> pList = service.getProductList();
		
		for(Product p : pList) {
			System.out.println(p.toString());
		}
	}	
}
