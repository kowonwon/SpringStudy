package com.springstudy.ch02.domain;

import java.util.Calendar;

public class Product {
	private String code;
	private String name;
	private int price;
	private String manufacturer;
	private String description;
	
	public Product() {}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		
		return code + ", " + name + ", " + price + "원, " + " 제조사: " + manufacturer + " " + description; 
	}
}