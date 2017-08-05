package com.gadwala.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.gadwala.dao.LocationDao;
import com.gadwala.databaseUtil.DatabaseUtil;
import com.gadwala.model.Location;

public class LocationService  implements LocationDao {
	
	private final static String UPDATE_ALL = "UPDATE location SET active = 0";
	private final static String GET_BY_TYPE = "SELECT * FROM location WHERE name LIKE ?";
	private final static String GET_ALL = "SELECT * FROM location WHERE active = 0";
	private final static String UPDATE = "UPDATE location SET active = ? WHERE id = ?";
	
	@Override
	public ArrayList<Location> getAllLocationsByType(String type)
			throws SQLException {
		ResultSet rs = null;
		ArrayList<Location> locations = new ArrayList<>();
		try (
				Connection conn = DatabaseUtil.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(GET_BY_TYPE);
			) {
			stmt.setString(1, "%" + type + "%");
			rs = stmt.executeQuery();
			while(rs.next()) {
				locations.add(map(rs));
			}
			return locations;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			DatabaseUtil.getInstance().closeConnection();
		}
	}

	@Override
	public ArrayList<Location> getAllLocations() throws SQLException {
		ResultSet rs = null;
		ArrayList<Location> locations = new ArrayList<>();
		try (
				Connection conn = DatabaseUtil.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(GET_ALL);
			) {
			rs = stmt.executeQuery();
			while(rs.next()) {
				locations.add(map(rs));
			}
			return locations;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			DatabaseUtil.getInstance().closeConnection();
		}
	}

	@Override
	public boolean update(int id) throws SQLException {
		try (
				Connection conn = DatabaseUtil.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(UPDATE);
				
			){
			stmt.setInt(1, 1);
			stmt.setInt(2, id);
			int affected = stmt.executeUpdate();
			if(affected == 1) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			DatabaseUtil.processException(e);
			return false;
		} catch (ClassNotFoundException e1) {
			System.out.println(e1.getMessage());
			return false;
		} finally {
			DatabaseUtil.getInstance().closeConnection();
		}
	}

	private Location map(ResultSet rs) throws SQLException {
		Location location = new Location();
		location.setName(rs.getString("name"));
		location.setId(rs.getInt("id"));
		location.setDay(rs.getString("day"));
		location.setActive(rs.getInt("active"));
		location.setType(rs.getString("type"));
		return location;
	}

	@Override
	public void updateAll() {
		try (
				Connection conn = DatabaseUtil.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(UPDATE_ALL);
				
			){
			
		stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			DatabaseUtil.processException(e);
		} catch (ClassNotFoundException e1) {
			System.out.println(e1.getMessage());
		} finally {
			DatabaseUtil.getInstance().closeConnection();
		}
		
	}
}
