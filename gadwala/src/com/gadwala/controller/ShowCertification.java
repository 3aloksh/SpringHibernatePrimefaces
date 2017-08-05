package com.gadwala.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gadwala.dao.AnswerDao;
import com.gadwala.model.Answer;
import com.gadwala.service.AnswerService;

@WebServlet("/ShowCertification")
public class ShowCertification extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int courseID = Integer.parseInt(request.getParameter("courseId"));
		AnswerDao dao = new AnswerService();
		ArrayList<Answer> answersForCourse = dao.getAnswersForCourses(courseID);
		HttpSession session = request.getSession();

		if(answersForCourse.size() > 0) {
			session.setAttribute("answersForCourse", answersForCourse);
			response.sendRedirect(request.getContextPath() + "/student/certification.jsp?courseId=" + courseID);
		} else {
			session.setAttribute("answersForCourse", null);
			response.sendRedirect(request.getContextPath() + "/student/certification.jsp?courseId=" + courseID);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
