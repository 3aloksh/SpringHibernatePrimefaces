package com.gadwala.model;

import java.io.Serializable;

public class Question implements Serializable{
	private static final long serialVersionUID = 1L;

	private int id;
	private int courseid;
	private String question;
	private String answers;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCourseid() {
		return courseid;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswers() {
		return answers;
	}
	public void setAnswers(String answers) {
		this.answers = answers;
	}
	public Question() {
		super();
	}
	public Question(int courseid, String question, String answers) {
		super();
		this.courseid = courseid;
		this.question = question;
		this.answers = answers;
	}
	
}
