package com.gadwala.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gadwala.dao.StudentGroupDao;
import com.gadwala.databaseUtil.DatabaseUtil;
import com.gadwala.model.StudentGroup;

public class StudentGroupService implements StudentGroupDao {
	private static final String CREATE = "INSERT INTO studentgroups(groupname, level, studentid) VALUES(?, ?, ?)";
	private static final String DELETE = "DELETE FROM studentgroups WHERE studentid = ?";
	private static final String GET_CAPACITY = "SELECT COUNT(*) AS total  FROM studentgroups WHERE level = ? AND groupname = ?";
	private static final String GET_BY_STUDENT = "SELECT * FROM studentgroups WHERE studentid = ?";
	
	@Override
	public int getGroupCapacity(int level, String group) {
		ResultSet rs = null;
		try (
				Connection conn = DatabaseUtil.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(GET_CAPACITY,  
							ResultSet.TYPE_SCROLL_INSENSITIVE, 
							ResultSet.CONCUR_READ_ONLY);
			) {
			stmt.setInt(1, level);
			stmt.setString(2, group);
			rs = stmt.executeQuery();
			rs.last();
			return rs.getInt("total");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		} finally {
			DatabaseUtil.getInstance().closeConnection();
		}
	}

	@Override
	public boolean create(StudentGroup studentGroup) {
		try (
				Connection conn = DatabaseUtil.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(CREATE);
				
			){
				stmt.setString(1, studentGroup.getGroup());
				stmt.setInt(2, studentGroup.getLevel());
				stmt.setInt(3, studentGroup.getStudentid());
				
				int affected = stmt.executeUpdate();
				System.out.println(affected);
				if(affected == 1) {
					return true;
				}
				return false;
		} catch (SQLException e) {
			DatabaseUtil.processException(e);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
		} finally {
			DatabaseUtil.getInstance().closeConnection();
		}
		return false;
	}

	@Override
	public boolean delete(int studentId) {
		try (
				Connection conn = DatabaseUtil.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(DELETE);
			) {
			stmt.setInt(1, studentId);
			if(stmt.executeUpdate() > 0)				
				return true;
			return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			DatabaseUtil.getInstance().closeConnection();
		}
	}

	@Override
	public StudentGroup getByStudentId(int studentId) {
		ResultSet rs = null;
		try (
				Connection conn = DatabaseUtil.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(GET_BY_STUDENT,  
							ResultSet.TYPE_SCROLL_INSENSITIVE, 
							ResultSet.CONCUR_READ_ONLY);
			) {
			stmt.setInt(1, studentId);
			rs = stmt.executeQuery();
			while(rs.next()) {
				return map(rs);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			DatabaseUtil.getInstance().closeConnection();
		}
		return null;
	}
	
	private StudentGroup map(ResultSet rs) throws SQLException {
		StudentGroup studentGroup = new StudentGroup();
		studentGroup.setId(rs.getInt("id"));
		studentGroup.setLevel(rs.getInt("level"));
		studentGroup.setStudentid(rs.getInt("studentid"));
		studentGroup.setGroup(rs.getString("groupname"));
		
		return studentGroup;
	}

}
