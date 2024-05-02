package com.springstudy.ch01.interfaces;

import java.util.ArrayList;

import com.springstudy.ch01.domain.Product;

public class ProductServiceImpl02 implements ProductService {
	
	public ArrayList<Product> getProductList() {
		
		ProductDAO dao = new ProductDAOImpl();
		System.out.println("impl-service : ProductServiceImpl02.getProductList()");
		return dao.getProductList();
	}
}
