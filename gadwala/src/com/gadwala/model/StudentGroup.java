package com.gadwala.model;

public class StudentGroup {
	private int id;
	private String group;
	private int level;
	private int studentid;
	
	public StudentGroup() {}
	
	public StudentGroup(String group, int level, int studentid) {
		super();
		this.group = group;
		this.level = level;
		this.studentid = studentid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getStudentid() {
		return studentid;
	}
	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}
	
	
}
