package com.gadwala.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.gadwala.dao.StudentDao;
import com.gadwala.databaseUtil.DatabaseUtil;
import com.gadwala.model.User;

public class StudentService extends UserService implements StudentDao {
	private static final String SQL_GET_ALL = "SELECT * FROM users WHERE type = ?";
	
	public ArrayList<User> getAll() throws SQLException {
		ResultSet rs = null;
		ArrayList<User> users = new ArrayList<>();
		try (
			Connection conn = DatabaseUtil.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement(SQL_GET_ALL,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			){
			stmt.setString(1, "student");
			rs = stmt.executeQuery();
			while(rs.next()) {
				users.add( map(rs));
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
}
