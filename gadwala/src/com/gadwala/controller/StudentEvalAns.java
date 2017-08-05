package com.gadwala.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gadwala.dao.AnswerDao;
import com.gadwala.dao.CourseDao;
import com.gadwala.model.Answer;
import com.gadwala.service.AnswerService;
import com.gadwala.service.CourseService;

@WebServlet("/StudentEvalAns")
public class StudentEvalAns extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AnswerDao dao = new AnswerService();
		CourseDao cDao = new CourseService();
		String courseName = "";
		try {
			courseName = cDao.getCourseById(Integer.parseInt(request.getParameter("id"))).getName();
		} catch (NumberFormatException | SQLException e) {
			System.out.println(e.getMessage());
		}
		int id = Integer.parseInt(request.getParameter("id"));
		ArrayList<Answer> answers = dao.getAnswersForCourses(id);
		HttpSession session = request.getSession();
		
		if(answers.size() > 0) {		
			session.setAttribute("ANSWERS", answers);
		} else {
			session.setAttribute("ANSWERS", null);
		}
		response.sendRedirect(request.getContextPath() + "/instructor/studentEvalAns.jsp?cName=" + courseName);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
