package com.springstudy.ch02exam.dao;

import java.util.ArrayList;

import com.springstudy.ch02exam.domain.Product;

public interface ProductDAO {
	public ArrayList<Product> getProductList();
}