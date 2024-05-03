package com.springstudy.ch02exam.service;

import java.util.ArrayList;

import com.springstudy.ch02exam.dao.ProductDAO;
import com.springstudy.ch02exam.domain.Product;

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
