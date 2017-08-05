package com.gadwala.courses.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gadwala.dao.CourseDao;
import com.gadwala.model.Course;
import com.gadwala.service.CourseService;

@WebServlet("/Courses")
public class Courses extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		CourseDao courseService = new CourseService();
		try {
			ArrayList<Course> courses = courseService.getAllCourses();
			if(courses != null) {
				session.setAttribute("courses", courses);				
				response.sendRedirect(request.getContextPath() + "/admin/courses.jsp");
			} else {
				session.setAttribute("courses", null);
				response.sendRedirect(request.getContextPath() + "/admin/courses.jsp");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			response.sendRedirect(request.getContextPath() + "/admin/courses.jsp");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("searchCourse");
		HttpSession session = request.getSession();
		
		CourseDao courseService = new CourseService();
		try {
			ArrayList<Course> courses = courseService.getCoursesByName(search);
			if(courses != null) {				
				session.setAttribute("courses", courses);
				response.sendRedirect(request.getContextPath() + "/admin/courses.jsp");
			} else {
				session.setAttribute("courses", null);
				response.sendRedirect(request.getContextPath() + "/admin/courses.jsp");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			response.sendRedirect(request.getContextPath() + "/admin/courses.jsp");
		}
	}

}
