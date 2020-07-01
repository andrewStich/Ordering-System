package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.daoInterfaces.ProductDao;
import com.model.Product;

public class ProductDaoImpl implements ProductDao {
	
	private static Connection conn = null;

	@Override
	public boolean addProduct(Product product) {
		conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement("insert into Product values(?, ?, ?, ?)");
			stmt.setInt(1,  product.getProductCode());
			stmt.setString(2, product.getProductName());
			stmt.setDouble(3, product.getPrice());
			stmt.setInt(4, product.getQuantity());
			stmt.executeUpdate();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public Product getProductById(int id) {
		conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn .prepareStatement("select * from Product where ProductCode = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				Product product = new Product (
						rs.getInt("ProductCode"),
						rs.getString("ProductName"),
						rs.getDouble("Price"),
						rs.getInt("Quantity")
					);
				
				return product;
						
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	@Override
	public List<Product> getAllProducts() {
		conn = ConnectionFactory.getConnection();
		List<Product> list = new ArrayList<Product>();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Product");
			
			while(rs.next()) {
				Product product = new Product (
						rs.getInt("ProductCode"),
						rs.getString("ProductName"),
						rs.getDouble("Price"),
						rs.getInt("Quantity")
					);
				
				list.add(product);
			}
			
			return list;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	@Override
	public boolean updateProduct(Product newProduct) {
		conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement("update Product set ProductName = ?, Price = ?, Quantity = ? where ProductCode = ?");
			stmt.setString(1, newProduct.getProductName());
			stmt.setDouble(2, newProduct.getPrice());
			stmt.setInt(3, newProduct.getQuantity());
			stmt.setInt(4, newProduct.getProductCode());
			stmt.executeUpdate();
			return true;
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		return false;
	}

}
