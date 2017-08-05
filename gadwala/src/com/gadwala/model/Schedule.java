package com.gadwala.model;

public class Schedule {
	private int id;
	private String groupname;
	private int level;
	private String coursename;
	private String location;
	private String instructor;
	private String day;
	private String slot;
	private int courseid;
	
	public Schedule() {
	}
	public Schedule(String groupname, int level, String coursename,
			String location, String instructor, String day, String slot) {
		super();
		this.groupname = groupname;
		this.level = level;
		this.coursename = coursename;
		this.location = location;
		this.instructor = instructor;
		this.day = day;
		this.slot = slot;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getSlot() {
		return slot;
	}
	public void setSlot(String slot) {
		this.slot = slot;
	}
	public int getCourseid() {
		return courseid;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	
	
	
}
