package com.springstudy.ch01.interfaces;

import java.util.ArrayList;

import com.springstudy.ch01.domain.Product;

public interface ProductDAO {
	public ArrayList<Product> getProductList();
}
