package com.daoInterfaces;

import java.util.List;

import com.model.Customer;

public interface CustomerDao {

	public boolean addCustomer(Customer customer);
	public Customer getCustomerById(int id);
	public List<Customer> getAllCustomers();
	
}
