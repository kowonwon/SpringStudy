package com.springstudy.ch01.factory;

import com.springstudy.ch01.interfaces.ProductDAO;
import com.springstudy.ch01.interfaces.ProductService;

public class ProductFactory {	
	
	public static ProductDAO createDAO() {
		// MySQL DB용 DAO
		return new ProductDAOImpl02();
		
		// Oracle DB용 DAO
//		return new ProductDAOImpl03();
	}
	
	public static ProductService createService() {
		return new ProductServiceImpl04();
	}
}
