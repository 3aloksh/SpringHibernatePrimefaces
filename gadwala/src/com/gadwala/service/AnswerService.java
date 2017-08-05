package com.gadwala.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.gadwala.dao.AnswerDao;
import com.gadwala.databaseUtil.DatabaseUtil;
import com.gadwala.model.Answer;

public class AnswerService implements AnswerDao {

	private final static String CREATE = "INSERT INTO answers(courseid, questionid, answer, studentid) VALUES(?, ?, ?, ?)";
	private final static String GET_ANS_FOR_STUDENT = "SELECT * FROM answers WHERE studentid = ?";
	private final static String GET_ANS_FOR_COURSE = "SELECT * FROM answers WHERE courseid = ?";
	
	@Override
	public boolean create(Answer answer) {
		try (
				Connection conn = DatabaseUtil.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(CREATE);
				
			){
				stmt.setInt(1, answer.getCourseid());
				stmt.setInt(2, answer.getQuestionid());
				stmt.setString(3, answer.getAnswer());
				stmt.setInt(4, answer.getStudentid());
				
				int affected = stmt.executeUpdate();
				if(affected == 1) {
					return true;
				}
				return false;
		} catch (SQLException e) {
			DatabaseUtil.processException(e);
		} catch (ClassNotFoundException e1) {
			System.out.println(e1.getMessage());
		} finally {
			DatabaseUtil.getInstance().closeConnection();
		}
		return false;
	}

	@Override
	public ArrayList<Answer> getAnswersForStudent(int studentId) {
		ResultSet rs = null;
		try (
				Connection conn = DatabaseUtil.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(GET_ANS_FOR_STUDENT);
			) {
			stmt.setInt(1, studentId);
			rs = stmt.executeQuery();
			ArrayList<Answer> answers = new ArrayList<>();
			while(rs.next()) {
				answers.add(map(rs));
			}
			return answers;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			DatabaseUtil.getInstance().closeConnection();
		}
	}
	
	private Answer map(ResultSet rs) throws SQLException {
		Answer answer = new Answer();
		answer.setAnswer(rs.getString("answer"));
		answer.setCourseid(rs.getInt("courseid"));
		answer.setQuestionid(rs.getInt("questionid"));
		answer.setId(rs.getInt("id"));
		answer.setStudentid(rs.getInt("studentid"));
		return answer;
		
	}

	@Override
	public ArrayList<Answer> getAnswersForCourses(int courseId) {
		ResultSet rs = null;
		try (
				Connection conn = DatabaseUtil.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(GET_ANS_FOR_COURSE);
			) {
			stmt.setInt(1, courseId);
			rs = stmt.executeQuery();
			ArrayList<Answer> answers = new ArrayList<>();
			while(rs.next()) {
				answers.add(map(rs));
			}
			return answers;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			DatabaseUtil.getInstance().closeConnection();
		}
	}

}
