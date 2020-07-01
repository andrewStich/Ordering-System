package com.daoInterfaces;

import java.util.List;

import com.model.Order;

public interface OrdersDao {
	
	public boolean addOrder(Order order);
	public Order getOrderById(int id);
	public List<Order> getAllOrders();
	public List<Order> getUserOrders(int id);
	public boolean deleteOrderById(int customerID, int productId);
	
}
