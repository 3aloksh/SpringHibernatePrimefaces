package com.gadwala.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.gadwala.dao.UserDao;
import com.gadwala.databaseUtil.DatabaseUtil;
import com.gadwala.model.User;

public class UserService implements UserDao {
	// all SQL operations statements
	private static final String SQL_GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
	private static final String SQL_GET_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
	private static final String SQL_GET_USER_BY_TYPE = "SELECT * FROM users WHERE type = ?";
	private static final String SQL_GET_ALL = "SELECT * FROM users";
	private static final String SQL_CREATE_USER = "INSERT INTO users(firstname, lastname, password, phone, type, email, level) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE_USER = "UPDATE users SET firstname = ?, lastname = ?, password = ?,"
			+ " phone = ?, type = ?, email = ?, level = ? WHERE id = ?";
	private static final String SQL_DELETE_USER = "DELETE FROM users WHERE id = ?";
	private static final String SQL_UPDATE_PASSWORD = "UPDATE users SET password = ? WHERE id = ?";
	private static final String SQL_AUTHENTICATE_USER = "SELECT * FROM users WHERE email = ? AND password = ?";
	private static final String SQL_GET_INSTRUCTOR = "SELECT * FROM users WHERE type = ?";
	private static final String SQL_GET_USER_BY_NAME = "SELECT * FROM users WHERE firstname LIKE ?";
	
	@Override
	public User getUser(int id) throws SQLException {
		ResultSet rs = null;
		try(
			Connection conn = DatabaseUtil.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement(SQL_GET_USER_BY_ID,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			) {
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if(rs.next()) {
				return map(rs);
			}
		} catch (SQLException e) {
			DatabaseUtil.processException(e);
			return null;
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		} finally {
			if (rs != null) rs.close();
			DatabaseUtil.getInstance().closeConnection();
		}
		return null;
	}

	@Override
	public ArrayList<User> getAll() throws SQLException {
		ResultSet rs = null;
		ArrayList<User> users = new ArrayList<>();
		try (
			Connection conn = DatabaseUtil.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement(SQL_GET_ALL,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			){
			rs = stmt.executeQuery();
			while(rs.next()) {
				users.add(map(rs));
			}
			return users;
		} catch (SQLException e) {
			DatabaseUtil.processException(e);
			return null;
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}finally {
			if (rs != null) rs.close();
			DatabaseUtil.getInstance().closeConnection();
		}
	}

	@Override
	public boolean create(User user) throws SQLException {
		ResultSet keys = null;
		Object[] values = {user.getFirstname(), user.getLastname(), user.getPassword(), 
				user.getPhone(), user.getType(), user.getEmail(), user.getLevel()};
		try (
				Connection conn = DatabaseUtil.getInstance().getConnection();
				 PreparedStatement stmt = DAOUtil.prepareStatement(conn, 
						 SQL_CREATE_USER, true, values);
		){
			int affected = stmt.executeUpdate();
			if(affected == 1) {
				keys = stmt.getGeneratedKeys();
				keys.next();
				user.setId(keys.getInt(1));
				return true;
			}
			return false;
		} catch (SQLException e) {
			DatabaseUtil.processException(e);
			return false;
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		} finally {
			if(keys  != null) keys.close();
			DatabaseUtil.getInstance().closeConnection();
		}
	}

	@Override
	public boolean edit(User user) throws SQLException {
		try(
				Connection conn = DatabaseUtil.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQL_UPDATE_USER,
						ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				) {
				stmt.setString(1, user.getFirstname());
				stmt.setString(2, user.getLastname());
				stmt.setString(3, user.getPassword());
				stmt.setString(4, user.getPhone());
				stmt.setString(5, user.getType());
				stmt.setString(6, user.getEmail());
				stmt.setInt(7, user.getLevel());
				stmt.setInt(8, user.getId());
				
				int affected = stmt.executeUpdate();
				if(affected > 0) {
					return true;
				}
			} catch (SQLException e) {
				DatabaseUtil.processException(e);
				return false;
			} catch (ClassNotFoundException e1) {
				return false;
			} finally {
				DatabaseUtil.getInstance().closeConnection();
			}
			return false;
	}

	@Override
	public boolean delete(int id) throws SQLException {
		try (
				Connection conn = DatabaseUtil.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQL_DELETE_USER);
		){
			stmt.setInt(1, id);
			int affected = stmt.executeUpdate();
			if(affected == 1) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			DatabaseUtil.processException(e);
			return false;
		} catch (ClassNotFoundException e1) {
			
			return false;
		} finally {
			DatabaseUtil.getInstance().closeConnection();
		}
	}
	
	public User map(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setLevel(rs.getInt("level"));
		user.setFirstname(rs.getString("firstname"));
		user.setLastname(rs.getString("lastname"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		user.setPhone(rs.getString("phone"));
		user.setType(rs.getString("type"));
		
		return user;
	}

	@Override
	public User getUser(String type) throws SQLException {
		ResultSet rs = null;
		try(
			Connection conn = DatabaseUtil.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement(SQL_GET_USER_BY_TYPE,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			) {
			stmt.setString(1, type);
			rs = stmt.executeQuery();
			if(rs.next()) {
				return map(rs);
			}
		} catch (SQLException e) {
			DatabaseUtil.processException(e);
			return null;
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		} finally {
			if (rs != null) rs.close();
			DatabaseUtil.getInstance().closeConnection();
		}
		return null;
	}

	@Override
	public User authenticateUser(String email, String password)
			throws SQLException {
		ResultSet rs = null;
		
		try (
			Connection conn = DatabaseUtil.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement(SQL_AUTHENTICATE_USER,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			){
			stmt.setString(1, email);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			if(rs.next()) {
				return map(rs);
			}
		} catch (SQLException e) {
			DatabaseUtil.processException(e);
			return null;
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		} finally {
			if (rs != null) rs.close();
			DatabaseUtil.getInstance().closeConnection();
		}
		return null;
	}

	@Override
	public User getUserByEmail(String email) throws SQLException {
		ResultSet rs = null;
		try(
			Connection conn = DatabaseUtil.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement(SQL_GET_USER_BY_EMAIL,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			) {
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			if(rs.next()) {
				return map(rs);
			}
		} catch (SQLException e) {
			DatabaseUtil.processException(e);
			return null;
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		} finally {
			if (rs != null) rs.close();
			DatabaseUtil.getInstance().closeConnection();
		}
		return null;
	}

	@Override
	public boolean updatePassword(String password, int id) throws SQLException {
		ResultSet rs = null;
		try(
			Connection conn = DatabaseUtil.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement(SQL_UPDATE_PASSWORD,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			) {
			stmt.setString(1, password);
			stmt.setInt(2, id);
			int affected = stmt.executeUpdate();
			if(affected > 0) {
				return true;
			}
		} catch (SQLException e) {
			DatabaseUtil.processException(e);
			return false;
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		} finally {
			if (rs != null) rs.close();
			DatabaseUtil.getInstance().closeConnection();
		}
		return false;
	}

	@Override
	public ArrayList<User> getInstructors() throws SQLException {
		ResultSet rs = null;
		ArrayList<User> users = new ArrayList<>();
		try (
			Connection conn = DatabaseUtil.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement(SQL_GET_INSTRUCTOR,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			){
			stmt.setString(1, "instructor");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				users.add(map(rs));
			}
			return users;
		} catch (SQLException e) {
			DatabaseUtil.processException(e);
			return null;
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}finally {
			if (rs != null) rs.close();
			DatabaseUtil.getInstance().closeConnection();
		}
	}

	@Override
	public ArrayList<User> getUserByName(String search) {
		ResultSet rs = null;
		ArrayList<User> users = new ArrayList<>();
		try (
				Connection conn = DatabaseUtil.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQL_GET_USER_BY_NAME);
			) {
			stmt.setString(1, "%" + search + "%");
			rs = stmt.executeQuery();
			while(rs.next()) {
				users.add(map(rs));
			}
			return users;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			DatabaseUtil.getInstance().closeConnection();
		}
	}
	
}
