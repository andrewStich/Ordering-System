package com.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class ConnectionFactory {
	private static String config = "D:\\Projects\\Eclipse\\Order System 6-18\\Recources\\Config\\Dao.properties";
	private static Connection connection = null;
	
	private ConnectionFactory() {
		
	}
	
	public static Connection getConnection() {
		if(connection == null) {
			makeConnection();
		}
		return connection;
	}
	
	private static void makeConnection() {
		config = System.getProperty("Dao.properties", config);
		Properties props = new Properties();
		
		try {
			FileInputStream fis = new FileInputStream(config);
			props.load(fis);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			connection = DriverManager.getConnection(props.getProperty("jdbc.url"), props);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void closeConnection() {
		try {
			if(connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
