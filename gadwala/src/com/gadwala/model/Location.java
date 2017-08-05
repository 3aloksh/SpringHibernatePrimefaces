package com.gadwala.model;

public class Location {
	private int id;
	private String name;
	private String day;
	private int active;
	private String type;
	
	public Location() { }
	
	public Location(String name, String day, int active, String type) {
		this.day = day;
		this.name = name;
		this.active = active;
		this.type = type;
	}
	
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
