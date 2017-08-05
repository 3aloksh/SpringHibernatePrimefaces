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


@WebServlet("/Questions")
public class Questions extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		QuestionDao dao = new QuestionService();
		ArrayList<Question> questions = dao.getAllByCourseID(id);
		if(questions.size() > 0) 
			session.setAttribute("questions", questions);
		else
			session.setAttribute("questions", null);
		response.sendRedirect(request.getContextPath() + "/instructor/questions.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
