package com.springstudy.ch02exam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.springstudy.ch02exam.domain.Product;

public class ProductDAOImpl implements ProductDAO{
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DriverManagerDataSource dataSource;
	
	public ProductDAOImpl() {}
	
	// 생성자 주입
	public ProductDAOImpl(DriverManagerDataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	// 셋터 주입
	public void setDataSource(DriverManagerDataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public ArrayList<Product> getProductList() {
		
		String selectAllMember = "SELECT * FROM product;";
		ArrayList<Product> productList = null;
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(selectAllMember);
			rs = pstmt.executeQuery();
			
			productList = new ArrayList<Product>();
			
			while(rs.next()) {
				
				Product product = new Product();
				product.setCode(rs.getString("code"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setManufacturer(rs.getString("manuFacturer"));
				product.setDescription(rs.getString("description"));
				
				productList.add(product);
			}			
			
		} catch(SQLException e) {
			e.printStackTrace();
			
		} finally {			
			try {				
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();				
			} catch(SQLException e) { }
		}
		return productList;
	}
}
