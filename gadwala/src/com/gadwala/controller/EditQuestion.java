package com.gadwala.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gadwala.dao.QuestionDao;
import com.gadwala.model.Question;
import com.gadwala.service.QuestionService;

@WebServlet("/EditQuestion")
public class EditQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int qId = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		QuestionDao dao = new QuestionService();
		try {
			Question question = dao.getQuestion(qId);
			session.setAttribute("question", question);
			response.sendRedirect(request.getContextPath() + "/instructor/edit-questions.jsp");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		int id = Integer.parseInt(request.getParameter("id"));
		int courseId = Integer.parseInt(request.getParameter("courseId"));
		
		String question = request.getParameter("question");
		String answers = request.getParameter("answer");
		
		Question q = new Question(courseId, question, answers);
		q.setId(id);
		QuestionDao dao = new QuestionService();
		try {
			if(dao.update(q)) {
				session.setAttribute("successMSG", "Question and Answers has been updated successfully!");
			} else {
				session.setAttribute("errorMSG", "An Error Occured during adding Question!");
			}
			response.sendRedirect(request.getContextPath() + "/instructor/home.jsp");
		} catch (SQLException e) {
			session.setAttribute("errorMSG", "An Error Occured during adding Question!");
			response.sendRedirect(request.getContextPath() + "/instructor/home.jsp");
		}
	}

}
