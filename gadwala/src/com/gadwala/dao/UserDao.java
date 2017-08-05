package com.gadwala.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.gadwala.model.User;


public interface UserDao {
	// get only one user by ID
	public User getUser(int id) throws SQLException;
	// get all users inside user table
	public ArrayList<User> getAll() throws SQLException, ClassNotFoundException;
	// create new user
	public boolean create(User user) throws SQLException;
	// update an existing user 
	public boolean edit(User user) throws SQLException;
	// delete an existing user by id
	public boolean delete(int id) throws SQLException;
	// get user by type
	public User getUser(String type) throws SQLException;
	// get instructors
	public ArrayList<User> getInstructors() throws SQLException;
	// get user by email
	public User getUserByEmail(String email) throws SQLException;
	// authenticate user
	public User authenticateUser(String email, String password) throws SQLException;
	// update password
	public boolean updatePassword(String password, int id) throws SQLException;
	public ArrayList<User> getUserByName(String search) throws SQLException;
}
