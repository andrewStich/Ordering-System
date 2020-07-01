package com.model;

public class Order {
	private int OrderNumber;
	private int CustomerNumber;
	private int ProductCode;
	private double price;
	private int quantity;

	public Order(int orderNumber, int customerNumber, int productCode, double price, int quantity) {
		super();
		OrderNumber = orderNumber;
		CustomerNumber = customerNumber;
		ProductCode = productCode;
		this.price = price;
		this.quantity = quantity;
	}

	public int getOrderNumber() {
		return OrderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		OrderNumber = orderNumber;
	}

	public int getCustomerNumber() {
		return CustomerNumber;
	}

	public void setCustomerNumber(int customerNumber) {
		CustomerNumber = customerNumber;
	}

	public int getProductCode() {
		return ProductCode;
	}

	public void setProductCode(int productCode) {
		ProductCode = productCode;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Order Number: " + OrderNumber + ", Customer: " + CustomerNumber + ", Product: " + ProductCode
				+ ", Price per item: " + price + ", Quantity Ordered: " + quantity;
	}

}
