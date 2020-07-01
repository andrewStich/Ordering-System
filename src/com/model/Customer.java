package com.model;

public class Customer {
	private int CustomerNumber;
	private String CustomerName;
	private String Address;
	private String City;
	private String Country;

	public Customer(int customerNumber, String customerName, String address, String city, String country) {
		super();
		CustomerNumber = customerNumber;
		CustomerName = customerName;
		Address = address;
		City = city;
		Country = country;
	}

	public int getCustomerNumber() {
		return CustomerNumber;
	}

	public void setCustomerNumber(int customerNumber) {
		CustomerNumber = customerNumber;
	}

	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	@Override
	public String toString() {
		return "Customer Number: " + CustomerNumber + ", Customer Name: " + CustomerName + ", Address: " + Address + " "
				+ City + ", " + Country;
	}

}
