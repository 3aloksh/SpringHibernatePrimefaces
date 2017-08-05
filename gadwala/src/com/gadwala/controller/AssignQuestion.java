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


@WebServlet("/AssignQuestion")
public class AssignQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		int id = Integer.parseInt(request.getParameter("id"));
		String question = request.getParameter("question");
		String answers = request.getParameter("answer");

		Question q = new Question(id, question, answers);
		
		QuestionDao dao = new QuestionService();
		try {
			if(dao.create(q)) {
				session.setAttribute("successMSG", "Question and Answers has been assigned successfully!");
			} else {
				session.setAttribute("errorMSG", "An Error Occured during adding Question!");
			}
			response.sendRedirect(request.getContextPath() + "/instructor/eval-questions.jsp");
		} catch (SQLException e) {
			session.setAttribute("errorMSG", "An Error Occured during adding Question!");
			response.sendRedirect(request.getContextPath() + "/instructor/eval-questions.jsp");
		}
	}

}
