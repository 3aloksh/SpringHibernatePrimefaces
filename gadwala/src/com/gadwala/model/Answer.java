package com.gadwala.model;

public class Answer {
	private int id;
	private int courseid;
	private int questionid;
	private String answer;
	private int studentid;
	
	public Answer() {}
	
	public Answer(int courseid, int questionid) {
		super();
		this.courseid = courseid;
		this.questionid = questionid;
	}

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

	public int getQuestionid() {
		return questionid;
	}

	public void setQuestionid(int questionid) {
		this.questionid = questionid;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "Answer [courseid=" + courseid + ", questionid=" + questionid
				+ ", answer=" + answer + "]";
	}

	public int getStudentid() {
		return studentid;
	}

	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}
	
	
}
