package com.gadwala.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.gadwala.dao.CourseDao;
import com.gadwala.databaseUtil.DatabaseUtil;
import com.gadwala.model.Course;

public class CourseService implements CourseDao {

	private static final String SQL_CREATE_COURSE = "INSERT INTO courses(capacity, fees, enddate, "
			+ "startdate, name, numberofsessions, numberofstudents, instractor, coursetype, roomtype, level, userid) VALUES("
			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_GET_ALL = "SELECT * FROM courses ORDER BY level";
	private static final String GET_BY_INSTRUCTOR = "SELECT * FROM courses WHERE userid = ?";
	private static final String SQL_GET_COURSE = "SELECT * FROM courses WHERE name LIKE ?";
	private static final String SQL_DELETE = "DELETE FROM courses WHERE id = ?";
	private static final String SQL_GET_BY_ID = "SELECT * FROM courses WHERE id = ?";
	private static final String SQL_GET_BY_LEVEL = "SELECT * FROM courses WHERE level = ?";
	private static final String SQL_UPDATE = "UPDATE courses SET capacity = ?, fees = ?, enddate = ?, startdate = ?,"
			+ " name = ?, numberofsessions = ?, "
			+ "numberofstudents = ?, instractor = ?, coursetype = ?, roomtype = ?, level = ?, userid = ? WHERE id = ?";
	
	@Override
	public Course getCourseById(int id) throws SQLException {
		ResultSet rs = null;
		try (
				Connection conn = DatabaseUtil.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQL_GET_BY_ID);
			) {
			stmt.setInt(1, id);
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

	@Override
	public ArrayList<Course> getAllCourses() throws SQLException {
		ResultSet rs = null;
		ArrayList<Course> courses = new ArrayList<>();
		try (
				Connection conn = DatabaseUtil.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQL_GET_ALL);
			) {
			rs = stmt.executeQuery();
			while(rs.next()) {
				courses.add(map(rs));
			}
			return courses;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			DatabaseUtil.getInstance().closeConnection();
		}
	}

	@Override
	public boolean create(Course course) throws SQLException {
		ResultSet keys = null;
		Object[] values = {course.getCapacity(), course.getFees(), course.getEndDate(), 
				course.getStartDate(), course.getName(), course.getNumberOfSessions(), 
				course.getNumberOfStudents(), course.getInstractor(), course.getCourseType()
				, course.getRoomType(), course.getLevel(), course.getUserid()};
		try (
				Connection conn = DatabaseUtil.getInstance().getConnection();
				PreparedStatement stmt = DAOUtil.prepareStatement(conn, 
						 SQL_CREATE_COURSE, true, values);
				
			){int affected = stmt.executeUpdate();
			if(affected == 1) {
				keys = stmt.getGeneratedKeys();
				keys.next();
				course.setId(keys.getInt(1));
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
	
	@Override
	public boolean delete(int id) throws SQLException {
		try (
				Connection conn = DatabaseUtil.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQL_DELETE);
			) {
			stmt.setInt(1, id);
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
	public ArrayList<Course> getCoursesByName(String search) throws SQLException {
		ResultSet rs = null;
		ArrayList<Course> courses = new ArrayList<>();
		try (
				Connection conn = DatabaseUtil.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQL_GET_COURSE);
			) {
			stmt.setString(1, "%" + search + "%");
			rs = stmt.executeQuery();
			while(rs.next()) {
				courses.add(map(rs));
			}
			return courses;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			DatabaseUtil.getInstance().closeConnection();
		}
	}
	
	private Course map(ResultSet rs) throws SQLException {
		Course course = new Course();
		course.setId(rs.getInt("id"));
		course.setName(rs.getString("name"));
		course.setCapacity(rs.getInt("capacity"));
		course.setFees(rs.getInt("fees"));
		course.setLevel(rs.getInt("level"));
		course.setNumberOfStudents(rs.getInt("numberofstudents"));
		course.setNumberOfSessions(rs.getInt("numberofsessions"));
		course.setInstractor(rs.getString("instractor"));
		course.setCourseType(rs.getString("coursetype"));
		course.setRoomType(rs.getString("roomtype"));
		course.setStartDate(rs.getString("startdate"));
		course.setEndDate(rs.getString("enddate"));
		course.setUserid(rs.getInt("userid"));
		return course;
	}

	@Override
	public boolean update(Course course) throws SQLException {
		try (
				Connection conn = DatabaseUtil.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQL_UPDATE);
				
			){
			stmt.setInt(1, course.getCapacity());
			stmt.setInt(2, course.getFees());
			stmt.setString(3, course.getEndDate());
			stmt.setString(4, course.getStartDate());
			stmt.setString(5, course.getName());
			stmt.setInt(6, course.getNumberOfSessions());
			stmt.setInt(7, course.getNumberOfStudents());
			stmt.setString(8, course.getInstractor());
			stmt.setString(9, course.getCourseType());
			stmt.setString(10, course.getRoomType());
			stmt.setInt(11, course.getLevel());
			stmt.setInt(12, course.getUserid());
			stmt.setInt(13, course.getId());
			
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

	@Override
	public ArrayList<Course> getCoursesByLevel(int level) throws SQLException {
		ResultSet rs = null;
		ArrayList<Course> courses = new ArrayList<>();
		try (
				Connection conn = DatabaseUtil.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQL_GET_BY_LEVEL);
			) {
			stmt.setInt(1, level);
			rs = stmt.executeQuery();
			while(rs.next()) {
				courses.add(map(rs));
			}
			return courses;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			DatabaseUtil.getInstance().closeConnection();
		}
	}

	@Override
	public ArrayList<Course> getCoursesByInstructor(int id) {
		ResultSet rs = null;
		try (
				Connection conn = DatabaseUtil.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(GET_BY_INSTRUCTOR);
			) {
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			ArrayList<Course> courses = new ArrayList<>();
			while(rs.next()) {
				courses.add(map(rs));
			}
			return courses;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			DatabaseUtil.getInstance().closeConnection();
		}
	}

	

}
