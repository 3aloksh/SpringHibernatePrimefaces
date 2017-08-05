package com.gadwala.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.gadwala.model.Schedule;

public interface ScheduleDao {
	public Schedule getScheduleByGroupNameAndLevel(int level, String name) throws SQLException;
	public Schedule getScheduleByGroupNameAndLevel(int level, String name, int courseid) throws SQLException;
	public boolean Create(Schedule schedule) throws SQLException;
	public ArrayList<Schedule> getAll() throws SQLException;
	public ArrayList<Schedule> getAllByDay(String day);
	public void updateSchedule(String coursename, int courseid);
	public void delete();
}
