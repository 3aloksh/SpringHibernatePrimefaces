package com.gadwala.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gadwala.dao.CourseDao;
import com.gadwala.model.Course;
import com.gadwala.model.User;
import com.gadwala.service.CourseService;


@WebServlet("/InstructorCourses")
public class InstructorCourses extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CourseDao dao = new CourseService();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		ArrayList<Course> courses = dao.getCoursesByInstructor(user.getId());
		
		if(courses != null) {
			session.setAttribute("courses", courses);				
		} else {
			session.setAttribute("courses", null);
		}
		response.sendRedirect(request.getContextPath() + "/instructor/home.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
