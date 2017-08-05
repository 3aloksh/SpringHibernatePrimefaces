package com.gadwala.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.gadwala.model.Group;

public interface GroupDao {
	public ArrayList<Group> getAll() throws SQLException;
	public boolean update(Group group) throws SQLException;
	public boolean create(Group group) throws SQLException;
	public void deleteByCourseID(int courseid);
	public ArrayList<Group> getGroupsByNameAndLevel(String name, int level) throws SQLException;
}
