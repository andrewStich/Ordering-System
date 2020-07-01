package com.daoInterfaces;

import java.util.List;

import com.model.Product;

public interface ProductDao {
	
	public boolean addProduct(Product product);
	public Product getProductById(int id);
	public List<Product> getAllProducts();
	public boolean updateProduct(Product newProduct);
	
}
