package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.daoInterfaces.OrdersDao;
import com.model.Order;

public class OrderDaoImpl implements OrdersDao {
	
	private static Connection conn = null;

	@Override
	public boolean addOrder(Order order) {
		conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement("insert into Orders values(?, ?, ?, ?, ?)");
			stmt.setInt(1, order.getOrderNumber());
			stmt.setInt(2, order.getCustomerNumber());
			stmt.setInt(3, order.getProductCode());
			stmt.setDouble(4, order.getPrice());
			stmt.setInt(5, order.getQuantity());
			stmt.executeUpdate();
			
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public Order getOrderById(int id) {
		conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement("select * from Orders where OrderNumber = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				Order order = new Order (
					rs.getInt("OrderNumber"),
					rs.getInt("CustomerNumber"),
					rs.getInt("ProductCode"),
					rs.getDouble("Price"),
					rs.getInt("Quantity")
				);
				
				return order;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	@Override
	public List<Order> getAllOrders() {
		conn = ConnectionFactory.getConnection();
		List<Order> list = new ArrayList<Order>();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Orders");
			
			while(rs.next()) {
				Order order = new Order (
						rs.getInt("OrderNumber"),
						rs.getInt("CustomerNumber"),
						rs.getInt("ProductCode"),
						rs.getDouble("Price"),
						rs.getInt("Quantity")
					);
				list.add(order);
			}
			
			return list;
		} catch (Exception e) {
			// TODO: handle exception
		}
	
		return null;
	}

	@Override
	public boolean deleteOrderById(int customerId, int productId) {
		conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement("delete from Orders where CustomerNumber = ? and ProductCode = ?");
			stmt.setInt(1, customerId);
			stmt.setInt(2, productId);
			stmt.executeUpdate();
			
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		ConnectionFactory.closeConnection();
		return false;
	}

	@Override
	public List<Order> getUserOrders(int id) {
		conn = ConnectionFactory.getConnection();
		List<Order> list = new ArrayList<Order>();
		
		try {
			PreparedStatement stmt = conn.prepareStatement("select * from Orders where CustomerNumber = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Order order = new Order (
					rs.getInt("OrderNumber"),
					rs.getInt("CustomerNumber"),
					rs.getInt("ProductCode"),
					rs.getDouble("Price"),
					rs.getInt("Quantity")
				);
				
				list.add(order);
			}
			
			return list;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

}
