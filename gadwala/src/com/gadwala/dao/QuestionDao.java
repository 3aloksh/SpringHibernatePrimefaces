package com.gadwala.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.gadwala.model.Question;

public interface QuestionDao {
	public ArrayList<Question> getAllByCourseID(int courseid);
	public boolean create(Question question) throws SQLException;
	public boolean update(Question question) throws SQLException;
	public Question getQuestion(int id) throws SQLException;
	public boolean deleteQuestion(int id) throws SQLException;
}
