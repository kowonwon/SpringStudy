package com.springstudy.ch02.service;

import java.util.ArrayList;

import com.springstudy.ch02.dao.ProductDAO;
import com.springstudy.ch02.domain.Product;

public class ProductServiceImplProperty implements ProductService{
	private ProductDAO productDAO;
	
	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
	
	@Override
	public ArrayList<Product> getProductList() {
		return productDAO.getProductList();
	}
}
