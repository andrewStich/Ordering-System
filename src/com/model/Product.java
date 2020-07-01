package com.model;

public class Product {
	private int ProductCode;
	private String ProductName;
	private double Price;
	private int Quantity;

	public Product(int productCode, String productName, double price, int quantity) {
		super();
		ProductCode = productCode;
		ProductName = productName;
		Price = price;
		Quantity = quantity;
	}

	public int getProductCode() {
		return ProductCode;
	}

	public void setProductCode(int productCode) {
		ProductCode = productCode;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	@Override
	public String toString() {
		return "Product Code: " + ProductCode + ", Product Name: " + ProductName + ", Price: " + Price + ", In Stock: "
				+ Quantity;
	}

}
