package com.gadwala.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gadwala.dao.QuestionDao;
import com.gadwala.model.Question;
import com.gadwala.service.QuestionService;

@WebServlet("/ShowQuestion")
public class ShowQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int courseId = Integer.parseInt(request.getParameter("courseId"));
		HttpSession session = request.getSession();
		QuestionDao dao = new QuestionService();
		ArrayList<Question> questions = dao.getAllByCourseID(courseId);
		if(questions.size() > 0) {
			session.setAttribute("questions", questions);
			response.sendRedirect(request.getContextPath() + "/student/evaluate.jsp");
		} else {
			session.setAttribute("questions", null);
			response.sendRedirect(request.getContextPath() + "/student/evaluate.jsp");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
