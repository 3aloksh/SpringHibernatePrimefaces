package com.gadwala.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.gadwala.model.Course;

public interface CourseDao {

	public Course getCourseById(int id) throws SQLException;
	public ArrayList<Course> getCoursesByLevel(int level) throws SQLException;
	public ArrayList<Course> getAllCourses() throws SQLException;
	public boolean create(Course course) throws SQLException;
	public ArrayList<Course> getCoursesByName(String search) throws SQLException;
	public boolean delete(int id) throws SQLException;
	public boolean update(Course course) throws SQLException;
	public ArrayList<Course> getCoursesByInstructor(int id);
}
