package com.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.dao.ConnectionFactory;
import com.dao.CustomerDaoImpl;
import com.dao.OrderDaoImpl;
import com.dao.ProductDaoImpl;
import com.model.Customer;
import com.model.Order;
import com.model.Product;

public class MainApp {

	static CustomerDaoImpl customerDao = new CustomerDaoImpl();
	static ProductDaoImpl productDao = new ProductDaoImpl();
	static OrderDaoImpl orderDao = new OrderDaoImpl();

	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		populateTables();

		System.out.println("\nWeclome to the Order App\n");

		while (true) {
			try {
				System.out.println("-------------------- Main Menu --------------------");
				System.out.println("1: Print all users and their orders");
				System.out.println("2: Update the quantity of a product");
				System.out.println("3: Delete a user's order of a specified product");
				System.out.println("4: Print data from all data");
				System.out.println("5: Print a user's most expensive order");
				System.out.println("0: Exit Program");
				System.out.print("Please select an option from above: ");

				int choice = Integer.parseInt(scan.nextLine());
				if(choice == 0) {
					break;
				}
				
				resolveChoice(choice);

				System.out.println("Press \"Enter\" to return to the menu...");
				scan.nextLine();
				
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		System.out.println("Exiting Program...");
		ConnectionFactory.closeConnection();
	}

	public static void resolveChoice(int choice) {
		switch (choice) {
		case (1):
			System.out.println("1: Print all users and their orders\n");
			printAllUsersAndProducts();
			break;
		case(2) :
			System.out.println("2: Update the quantity of a product\n");
			prepareProductUpdate();
			break;
		case(3): 
			System.out.println("3: Delete a user's order of a specified product\n");
			prepareDelete();
			break;
		case(4):
			System.out.println("4: Print data from all data\n");
			printAllData();
			break;
		case(5):
			System.out.println("5: Print a user's most expensive order\n");
			getMostExpensive();
			break;
		default:
			System.out.println("Please make a valid selection!");
		}
	}

	public static void printAllData() {
		List<Order> orderList = new ArrayList<Order>();
		List<Customer> customerList = new ArrayList<Customer>();
		List<Product> productList = new ArrayList<Product>();

		customerList = customerDao.getAllCustomers();
		orderList = orderDao.getAllOrders();
		productList = productDao.getAllProducts();

		for (Customer c : customerList) {
			System.out.println(c.toString());
		}
		System.out.println();

		for (Order o : orderList) {
			System.out.println(o.toString());
		}
		System.out.println();
		
		for (Product p : productList) {
			System.out.println(p.toString());
		}

	}

	public static void printAllUsersAndProducts() {

		List<Order> orderList = new ArrayList<Order>();
		List<Customer> customerList = new ArrayList<Customer>();

		customerList = customerDao.getAllCustomers();

		for (Customer c : customerList) {
			System.out.println(c.toString());
			orderList = orderDao.getUserOrders(c.getCustomerNumber());

			for (Order o : orderList) {
				System.out.println("\t" + productDao.getProductById(o.getProductCode()).toString());
			}
		}
		System.out.println();
	}
	
	public static void prepareProductUpdate() {
		int productNumber;
		int newQuantity;
		List<Product> productList = new ArrayList<Product>();
		
		productList = productDao.getAllProducts();
		for (Product p : productList) {
			System.out.println(p.toString());
		}
		System.out.println();
		
		
		while(true)
		try {
			System.out.print("Please enter the product Number of the product you would like to edit: ");
			productNumber = Integer.parseInt(scan.nextLine());
			break;
		} catch (Exception e) {
			System.out.println("Please enter a valid number");
			continue;
		}
		
		while(true)
			try {
				System.out.print("Please Enter the updated quantity: ");
				newQuantity = Integer.parseInt(scan.nextLine());
				break;
			} catch (Exception e) {
				System.out.println("Please enter a valid number");
				continue;
			}
		
		Product newProduct = productDao.getProductById(productNumber);
		newProduct.setQuantity(newQuantity);
		updateProduct(newProduct);
		
	}

	public static void updateProduct(Product product) {

		System.out.print("Old product data: ");
		System.out.println(productDao.getProductById(product.getProductCode()) + "\n");

		productDao.updateProduct(product);
		System.out.print("Updated product data: ");
		System.out.println(productDao.getProductById(product.getProductCode()) + "\n");
	}
	
	public static void prepareDelete() {
		int customerNumber;
		int productCode;
		printAllUsersAndProducts();
		
		while(true) {
			try {
				System.out.print("Enter the customer number: ");
				customerNumber = Integer.parseInt(scan.nextLine());
				break;
			} catch (Exception e) {
				System.out.println("Please enter a valid number");
				continue;
			}
		}
		
		while(true) {
			try {
				System.out.print("Enter the product number: ");
				productCode = Integer.parseInt(scan.nextLine());
				break;
			} catch (Exception e) {
				System.out.println("Please enter a valid number");
				continue;
			}
		}
		
		deleteOrder(customerNumber, productCode);
		
	}

	public static void deleteOrder(int customerNumber, int productCode) {
		List<Order> orderList = new ArrayList<Order>();
		orderList = orderDao.getAllOrders();

		orderDao.deleteOrderById(customerNumber, productCode);

		orderList = orderDao.getUserOrders(customerNumber);
		System.out.println("Updated list of customer #" + customerNumber + "'s orders");
		for (Order o : orderList) {
			System.out.println(o.toString());
		}
		System.out.println();
	}

	public static void populateTables() {
		List<Order> orderList = new ArrayList<Order>();
		List<Customer> customerList = new ArrayList<Customer>();
		List<Product> productList = new ArrayList<Product>();

		System.out.println("\nPopulating the database...");

		productList.add(new Product(123, "5 Dozen Eggs", 7.89, 34));
		productList.add(new Product(456, "Milk", 3.49, 26));
		productList.add(new Product(789, "Bread", 3.97, 40));
		productList.add(new Product(100, "Corvette", 60000, 1));

		for (Product p : productList) {
			productDao.addProduct(p);
		}

		customerList.add(new Customer(4, "Jian Yang", "123 incubator", "Silicon Valley", "USA"));
		customerList.add(new Customer(6, "Elrich Bachman", "???", "???", "Tibet"));

		for (Customer c : customerList) {
			customerDao.addCustomer(c);
		}

		orderList.add(new Order(1, 6, 123, 7.89, 1));
		orderList.add(new Order(2, 6, 456, 3.49, 2));
		orderList.add(new Order(3, 6, 789, 3.97, 2));
		orderList.add(new Order(4, 4, 456, 3.49, 1));
		orderList.add(new Order(5, 4, 789, 3.97, 1));
		orderList.add(new Order(6, 4, 100, 60000, 1));

		for (Order o : orderList) {
			orderDao.addOrder(o);
		}

		System.out.println("Data has been added");
	}
	
	public static void getMostExpensive() {
		int customerNumber;
		double max = Integer.MIN_VALUE;
		int maxOrderNumber = -1;
		List<Customer> customerList = new ArrayList<Customer>();
		List<Order> orderList = new ArrayList<Order>();
		
		customerList = customerDao.getAllCustomers();
		for (Customer c : customerList) {
			System.out.println(c.toString());
		}
		
		while(true) {
			try {
				System.out.print("\nEnter Customer Number to see their most expensive order: ");
				customerNumber = Integer.parseInt(scan.nextLine());
				break;
			} catch (Exception e) {
				System.out.println("Please enter a valid number");
				continue;
			}
		}
		
		orderList = orderDao.getUserOrders(customerNumber);
		
		for(Order o: orderList) {
			if(o.getPrice() * o.getQuantity() > max) {
				max = o.getPrice() * o.getQuantity();
				maxOrderNumber = o.getOrderNumber();
			}
		}
		
		Customer customer = customerDao.getCustomerById(customerNumber);
		
		System.out.println("\n" + customer.getCustomerName() + "'s most expensive order was order number " + maxOrderNumber + " where they paid $" + max);
		System.out.println(orderDao.getOrderById(maxOrderNumber)+ "\n");
	}
}
