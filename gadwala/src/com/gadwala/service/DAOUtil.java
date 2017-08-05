package com.gadwala.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public final class DAOUtil {
	
	private DAOUtil() {}
	
	public static PreparedStatement prepareStatement
		(Connection conn, String sql, boolean returnedGeneratedKeys, Object ... values) 
			throws SQLException{
		
		PreparedStatement statement = conn.prepareStatement(sql, 
			returnedGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
		setValues(statement, values);
		return statement;
	}
	
	public static void setValues(PreparedStatement stmt, Object ...  values) throws SQLException {
		for(int i = 0; i < values.length; i++) {
			stmt.setObject(i + 1, values[i]);
		}
	}
	
	public static Date toSQLDate(java.util.Date date) {
		return (date != null) ? new Date(date.getTime()) : null;
	}
}
