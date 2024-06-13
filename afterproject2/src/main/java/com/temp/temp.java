package com.temp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class temp {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/spring?useSSL=false&allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf8";
		String username = "root";
		String password = "12345678";
		
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			String query = "SELECT * FROM temp_memList where id = lemon";
			PreparedStatement ps = connection.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				System.out.println(rs.getInt("id") + ", " + rs.getString("name"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}