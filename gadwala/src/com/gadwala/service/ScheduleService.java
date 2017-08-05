package com.gadwala.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.gadwala.dao.ScheduleDao;
import com.gadwala.databaseUtil.DatabaseUtil;
import com.gadwala.model.Schedule;

public class ScheduleService implements ScheduleDao {
	
	private final static String GET_ALL = "SELECT * FROM schedule";
	private final static String DELETE = "DELETE FROM schedule";
	private final static String UPDATE = "UPDATE schedule set coursename = ? WHERE courseid = ?";
	private final static String GET_ALL_BY_DAY = "SELECT * FROM schedule WHERE day = ?";
	private final static String GET_BY_NAMELEVEL = "SELECT * FROM schedule WHERE groupname = ? AND level = ?";
	private final static String GET_BY_NAMELEVELCOURSE = "SELECT * FROM schedule WHERE groupname = ? AND level = ? AND courseid = ?";
	private final static String CREATE = "INSERT INTO schedule (groupname, level, coursename, location, day, slot, instructor, courseid)"
			+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	@Override
	public Schedule getScheduleByGroupNameAndLevel(int level, String name)
			throws SQLException {
		ResultSet rs = null;
		try (
				Connection conn = DatabaseUtil.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(GET_BY_NAMELEVEL);
			) {
			stmt.setString(1, name);
			stmt.setInt(2, level);
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
	public boolean Create(Schedule schedule) throws SQLException {
		ResultSet keys = null;
		Object[] values = {schedule.getGroupname(), schedule.getLevel(),
				schedule.getCoursename(), schedule.getLocation(),
				schedule.getDay(), schedule.getSlot(), schedule.getInstructor(), schedule.getCourseid()};
		try (
				Connection conn = DatabaseUtil.getInstance().getConnection();
				PreparedStatement stmt = DAOUtil.prepareStatement(conn, 
						 CREATE, true, values);
				
			){
			int affected = stmt.executeUpdate();
			if(affected == 1) {
				keys = stmt.getGeneratedKeys();
				keys.next();
				schedule.setId(keys.getInt(1));
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
	
	private Schedule map(ResultSet rs) throws SQLException {
		Schedule schedule = new Schedule();
		schedule.setId(rs.getInt("id"));
		schedule.setGroupname(rs.getString("groupname"));
		schedule.setLevel(rs.getInt("level"));
		schedule.setCourseid(rs.getInt("courseid"));
		schedule.setCoursename(rs.getString("coursename"));
		schedule.setLocation(rs.getString("location"));
		schedule.setInstructor(rs.getString("instructor"));
		schedule.setDay(rs.getString("day"));
		schedule.setSlot(rs.getString("slot"));
		
		return schedule;
	}

	@Override
	public Schedule getScheduleByGroupNameAndLevel(int level, String name,
			int courseid) throws SQLException {
		ResultSet rs = null;
		try (
				Connection conn = DatabaseUtil.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(GET_BY_NAMELEVELCOURSE);
			) {
			
			stmt.setString(1, name);
			stmt.setInt(2, level);
			stmt.setInt(3, courseid);
			
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
	public ArrayList<Schedule> getAll() throws SQLException {
		ResultSet rs = null;
		ArrayList<Schedule> schedules = new ArrayList<>();
		try (
				Connection conn = DatabaseUtil.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(GET_ALL);
			) {
			rs = stmt.executeQuery();
			while(rs.next()) {
				schedules.add(map(rs));
			}
			return schedules;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			DatabaseUtil.getInstance().closeConnection();
		}
	}

	@Override
	public ArrayList<Schedule> getAllByDay(String day) {
		ResultSet rs = null;
		try (
				Connection conn = DatabaseUtil.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(GET_ALL_BY_DAY);
			) {
			stmt.setString(1, day);
			rs = stmt.executeQuery();
			ArrayList<Schedule> schedules = new ArrayList<>();
			while(rs.next()) {
				schedules.add(map(rs));
			}
			return schedules;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			DatabaseUtil.getInstance().closeConnection();
		}
	}

	@Override
	public void updateSchedule(String coursename, int courseid) {
		try (
				Connection conn = DatabaseUtil.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(UPDATE);
				
			){
			stmt.setString(1, coursename);
			stmt.setInt(2, courseid);
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

	@Override
	public void delete() {
		try (
				Connection conn = DatabaseUtil.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(DELETE);
			) {
			stmt.executeUpdate();				
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DatabaseUtil.getInstance().closeConnection();
		}
		
	}
}
