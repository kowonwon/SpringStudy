package com.springstudy.ch02.annotation;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springstudy.ch02.dao.ProductDAO;
import com.springstudy.ch02.domain.Product;
import com.springstudy.ch02.service.ProductService;

@Service
public class ProductServiceImplConstructor implements ProductService{
	private ProductDAO productDAO;
	
	@Autowired
	public ProductServiceImplConstructor(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
	
	@Override
	public ArrayList<Product> getProductList() {
		return productDAO.getProductList();
	}
}