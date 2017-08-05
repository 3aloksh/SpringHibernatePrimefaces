package com.gadwala.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.gadwala.dao.GroupDao;
import com.gadwala.databaseUtil.DatabaseUtil;
import com.gadwala.model.Group;

public class GroupService implements GroupDao {
	private final String CREATE = "INSERT INTO groups (name, level, courseid) VALUES (?, ?, ?)";
	private final String GET_GROUPS_BY_LEVEL_NAME = "SELECT * FROM groups WHERE name = ? AND level = ?";
	private final String GET_ALL = "SELECT * FROM groups";
	private final String DELETE_ALL = "DELETE FROM groups WHERE courseid = ?";
	@Override
	public ArrayList<Group> getAll() throws SQLException {
		ResultSet rs = null;
		ArrayList<Group> groups = new ArrayList<>();
		try (
				Connection conn = DatabaseUtil.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(GET_ALL);
			) {
			rs = stmt.executeQuery();
			while(rs.next()) {
				groups.add(map(rs));
			}
			return groups;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			DatabaseUtil.getInstance().closeConnection();
		}
	}
	@Override
	public boolean update(Group group) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean create(Group group) throws SQLException {
		ResultSet keys = null;
		Object[] values = {group.getName(), group.getLevel(), group.getCourseid()};
		try (
				Connection conn = DatabaseUtil.getInstance().getConnection();
				PreparedStatement stmt = DAOUtil.prepareStatement(conn, 
						 CREATE, true, values);
				
			){
				int affected = stmt.executeUpdate();
				if(affected == 1) {
					keys = stmt.getGeneratedKeys();
					keys.next();
					group.setId(keys.getInt(1));
					return true;
				}
				return false;
		} catch (SQLException e) {
			DatabaseUtil.processException(e);
			return false;
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			return false;
		} finally {
			if(keys  != null) keys.close();
			DatabaseUtil.getInstance().closeConnection();
		}
	}
	
	private Group map(ResultSet rs) throws SQLException {
		Group group = new Group();
		group.setId(rs.getInt("id"));
		group.setCourseid(rs.getInt("courseid"));
		group.setLevel(rs.getInt("level"));
		group.setName(rs.getString("name"));
	
		return group;
	}
	@Override
	public ArrayList<Group> getGroupsByNameAndLevel(String name, int level)
			throws SQLException {
		ResultSet rs = null;
		ArrayList<Group> groups = new ArrayList<>();
		try (
				Connection conn = DatabaseUtil.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(GET_GROUPS_BY_LEVEL_NAME);
			) {
			
			stmt.setString(1, name);
			stmt.setInt(2, level);
			rs = stmt.executeQuery();
			while(rs.next()) {
				groups.add(map(rs));
			}
			return groups;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			DatabaseUtil.getInstance().closeConnection();
		}
	}
	@Override
	public void deleteByCourseID(int courseid) {
		try (
				Connection conn = DatabaseUtil.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(DELETE_ALL);
			) {
			stmt.setInt(1, courseid);
			stmt.executeUpdate();				
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DatabaseUtil.getInstance().closeConnection();
		}
		
	}
	

}
