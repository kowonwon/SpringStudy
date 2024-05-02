package com.springstudy.ch01.strong;

import java.util.ArrayList;

import com.springstudy.ch01.domain.Product;

public class ProductService {
	
	public ArrayList<Product> getProductList() {
		
		ProductDAO dao = new ProductDAO();
		System.out.println("strong-service : ProductService.getProductList()");
		return dao.getProductList();
	}
}
