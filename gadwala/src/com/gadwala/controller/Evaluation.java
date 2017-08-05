package com.gadwala.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gadwala.dao.CourseDao;
import com.gadwala.model.Course;
import com.gadwala.service.CourseService;

@WebServlet("/Evaluation")
public class Evaluation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		CourseDao dao = new CourseService();
		HttpSession session = request.getSession();
		try {
			Course course = dao.getCourseById(id);
			if(course != null) {
				session.setAttribute("course", course);
			} else {
				session.setAttribute("course", null);
			}
			response.sendRedirect(request.getContextPath() + "/instructor/eval-questions.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
