package com.springstudy.ch01.factory;

import java.util.ArrayList;

import com.springstudy.ch01.domain.Product;
import com.springstudy.ch01.factory.ProductFactory;
import com.springstudy.ch01.interfaces.ProductDAO;
import com.springstudy.ch01.interfaces.ProductService;

public class ProductServiceImpl04 implements ProductService {
	
	public ArrayList<Product> getProductList() {
		
		ProductDAO dao = ProductFactory.createDAO();
		System.out.println("light-service : ProductServiceImpl04.getProductList()");
		return dao.getProductList();
	}
}
