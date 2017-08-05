package com.gadwala.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.gadwala.dao.QuestionDao;
import com.gadwala.databaseUtil.DatabaseUtil;
import com.gadwala.model.Question;

public class QuestionService implements QuestionDao {

	private final static String GET_ALL_BY_CID = "SELECT * FROM questions WHERE courseid = ?";
	private final static String CREATE = "INSERT INTO questions (question, answers, courseid) VALUES (?, ?, ?)";
	private final static String DELETE = "DELETE FROM questions WHERE id = ?";
	private final static String UPDATE = "UPDATE questions SET question = ?, answers = ?, courseid = ? WHERE id = ?";
	private final static String GET_BY_ID = "SELECT * FROM questions WHERE id = ?";
	
	@Override
	public ArrayList<Question> getAllByCourseID(int courseid) {
		ResultSet rs = null;
		try (
				Connection conn = DatabaseUtil.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(GET_ALL_BY_CID);
			) {
			stmt.setInt(1, courseid);
			rs = stmt.executeQuery();
			ArrayList<Question> questions = new ArrayList<>();
			while(rs.next()) {
				questions.add(map(rs));
			}
			return questions;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			DatabaseUtil.getInstance().closeConnection();
		}
	}

	@Override
	public boolean create(Question question) throws SQLException {
		try (
				Connection conn = DatabaseUtil.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(CREATE);
				
			){
				stmt.setString(1, question.getQuestion());
				stmt.setString(2, question.getAnswers());
				stmt.setInt(3, question.getCourseid());
				
				int affected = stmt.executeUpdate();
				System.out.println(affected);
				if(affected == 1) {
					return true;
				}
				return false;
		} catch (SQLException e) {
			DatabaseUtil.processException(e);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
		} finally {
			DatabaseUtil.getInstance().closeConnection();
		}
		return false;
	}

	private Question map(ResultSet rs) throws SQLException {
		Question question = new Question();
		question.setId(rs.getInt("id"));
		question.setAnswers(rs.getString("answers"));
		question.setQuestion(rs.getString("question"));
		question.setCourseid(rs.getInt("courseid"));
		
		return question;
	}

	@Override
	public boolean update(Question question) throws SQLException {
		try (
				Connection conn = DatabaseUtil.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(UPDATE);
			){
			stmt.setString(1, question.getQuestion());
			stmt.setString(2, question.getAnswers());
			stmt.setInt(3, question.getCourseid());
			stmt.setInt(4, question.getId());
			int affected = stmt.executeUpdate();
			if(affected == 1) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			DatabaseUtil.processException(e);
			return false;
		} catch (ClassNotFoundException e1) {
			System.out.println(e1.getMessage());
			return false;
		} finally {
			DatabaseUtil.getInstance().closeConnection();
		}
	}

	@Override
	public Question getQuestion(int id) throws SQLException {
		ResultSet rs = null;
		try (
				Connection conn = DatabaseUtil.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(GET_BY_ID);
			) {
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while(rs.next()) {
				return map(rs);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			DatabaseUtil.getInstance().closeConnection();
		}
		return null;
	}

	@Override
	public boolean deleteQuestion(int id) throws SQLException {
		try (
				Connection conn = DatabaseUtil.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(DELETE);
			) {
			stmt.setInt(1, id);
			if(stmt.executeUpdate() > 0)				
				return true;
			return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			DatabaseUtil.getInstance().closeConnection();
		}
	}

}
