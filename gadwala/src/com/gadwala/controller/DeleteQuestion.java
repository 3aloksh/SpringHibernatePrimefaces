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
import com.gadwala.service.QuestionService;


@WebServlet("/DeleteQuestion")
public class DeleteQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int qId = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		QuestionDao dao = new QuestionService();
		try {
			if(dao.deleteQuestion(qId)) {
				session.setAttribute("successMSG", "Question has been deleted successfully.");
				response.sendRedirect(request.getContextPath() + "/instructor/home.jsp");
			} else {
				session.setAttribute("errorMSG", "An error occurred.");
				response.sendRedirect(request.getContextPath() + "/instructor/home.jsp");
			}
		} catch (SQLException e) {
			session.setAttribute("MSG", "An error occurred.");
			response.sendRedirect(request.getContextPath() + "/instructor/home.jsp");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
