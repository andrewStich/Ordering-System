package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.daoInterfaces.CustomerDao;
import com.model.Customer;

public class CustomerDaoImpl implements CustomerDao {
	
	private static Connection conn = null;

	@Override
	public boolean addCustomer(Customer customer) {
		conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement("insert into Customer values(?, ?, ?, ?, ?)");
			stmt.setInt(1,  customer.getCustomerNumber());
			stmt.setString(2, customer.getCustomerName());
			stmt.setString(3, customer.getAddress());
			stmt.setString(4, customer.getCity());
			stmt.setString(5, customer.getCountry());
			stmt.executeUpdate();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public Customer getCustomerById(int id) {
		conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement("select * from Customer where CustomerNumber = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				Customer customer = new Customer(
					rs.getInt("CustomerNumber"),
					rs.getString("CustomerName"),
					rs.getString("Address"),
					rs.getString("City"),
					rs.getString("Country")
				);
				
				return customer;	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Customer> getAllCustomers() {
		conn = ConnectionFactory.getConnection();
		List<Customer> customerList = new ArrayList<Customer>();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Customer");
			
			while(rs.next()) {
				Customer customer = new Customer(
					rs.getInt("CustomerNumber"),
					rs.getString("CustomerName"),
					rs.getString("Address"),
					rs.getString("City"),
					rs.getString("Country")
				);
				
				customerList.add(customer);
				
			}
			
			return customerList;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
}
