package com.gadwala.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.gadwala.model.Location;

public interface LocationDao {
	public ArrayList<Location> getAllLocationsByType(String type) throws SQLException;
	public ArrayList<Location> getAllLocations() throws SQLException;
	public boolean update(int id) throws SQLException;
	public void updateAll();
	
}
