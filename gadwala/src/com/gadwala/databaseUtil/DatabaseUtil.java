package com.gadwala.databaseUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

public class DatabaseUtil {
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private static final String CONN_STRING = "jdbc:mysql://localhost/gadwala?autoReconnect=true";
	
	private static DatabaseUtil instance = null;
	private Connection conn = null;
	// private constructor so as no classes can instantiate this class.
	private DatabaseUtil() {
	}
	
	public static DatabaseUtil getInstance() {
		if(instance == null) {
			instance = new DatabaseUtil();
		}
		return instance;
	}
	
	public Connection getConnection() throws ClassNotFoundException {
		if(conn == null) {
			try {
//				System.out.println("Opening connection.....");
				//DriverManager.registerDriver(new Driver());
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				this.conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
//				System.out.println("Connected to the database successfully");
			} catch (SQLException e) {
				processException(e);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return conn;
	}
	
	public void closeConnection() {
//		System.out.println("Closing Connection.....");
		
		if(conn != null)
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				processException(e);
			}
	}
	
	public static void processException(SQLException e) {
		System.out.println("Error Message: " + e.getMessage());
		System.out.println("Error Code: " + e.getErrorCode());
		System.out.println("SQL state: " + e.getSQLState());
	}

}
