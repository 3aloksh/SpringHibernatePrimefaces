package com.gadwala.dao;

import java.util.ArrayList;

import com.gadwala.model.Answer;

public interface AnswerDao {
	public boolean create(Answer answer);
	public ArrayList<Answer> getAnswersForStudent(int studentId);
	public ArrayList<Answer> getAnswersForCourses(int courseId);
}
