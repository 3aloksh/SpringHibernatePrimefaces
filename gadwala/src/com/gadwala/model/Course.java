package com.gadwala.model;

import java.io.Serializable;

public class Course implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private int capacity;
	private int fees;
	private int numberOfStudents;
	private int numberOfSessions;
	private int level;
	private String instractor;
	private String courseType;
	private String roomType;
	private String startDate;
	private String endDate;
	private int userid;
	
	public Course() { }
	
	public Course(String name, int capacity, int fees, int numberOfStudents,
			int numberOfSessions, int level, String instractor, String courseType,
			String roomType, String startDate, String endDate, int userid) {
		this.name = name;
		this.userid = userid;
		this.capacity = capacity;
		this.fees = fees;
		this.level = level;
		this.numberOfStudents = numberOfStudents;
		this.numberOfSessions = numberOfSessions;
		this.instractor = instractor;
		this.courseType = courseType;
		this.roomType = roomType;
		this.startDate = startDate;
		this.endDate = endDate;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getFees() {
		return fees;
	}
	public void setFees(int fees) {
		this.fees = fees;
	}
	public int getNumberOfStudents() {
		return numberOfStudents;
	}
	public void setNumberOfStudents(int numberOfStudents) {
		this.numberOfStudents = numberOfStudents;
	}
	public int getNumberOfSessions() {
		return numberOfSessions;
	}
	public void setNumberOfSessions(int numberOfSessions) {
		this.numberOfSessions = numberOfSessions;
	}
	public String getInstractor() {
		return instractor;
	}
	public void setInstractor(String instractor) {
		this.instractor = instractor;
	}
	public String getCourseType() {
		return courseType;
	}
	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "Course [name=" + name + ", capacity=" + capacity + ", fees="
				+ fees + ", numberOfStudents=" + numberOfStudents
				+ ", numberOfSessions=" + numberOfSessions + ", instractor="
				+ instractor + ", courseType=" + courseType + ", roomType="
				+ roomType + ", startDate=" + startDate + ", endDate="
				+ endDate + "]";
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
	
}
