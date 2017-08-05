package com.gadwala.courses.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gadwala.dao.CourseDao;
import com.gadwala.dao.UserDao;
import com.gadwala.model.Course;
import com.gadwala.model.User;
import com.gadwala.service.CourseService;
import com.gadwala.service.UserService;

/**
 * Servlet implementation class AddCourse
 */
@WebServlet("/AddCourse")
public class AddCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao dao = new UserService();
		HttpSession session = request.getSession();
		try {
			session.setAttribute("insts", dao.getInstructors());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		response.sendRedirect(request.getContextPath() + "/admin/add-course.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		int fees = Integer.parseInt(request.getParameter("fees"));
		int numberofstudents = Integer.parseInt(request.getParameter("mubmerofstudents"));
		int numberofsessions = Integer.parseInt(request.getParameter("numberofsessions"));
		int level = Integer.parseInt(request.getParameter("level"));
		int instractorid = Integer.parseInt(request.getParameter("instractor"));
		String courseType = request.getParameter("ctype");
		String roomType = request.getParameter("rtype");
		String endDate = request.getParameter("enddate");
		int capacity = Integer.parseInt(request.getParameter("capacity"));
		String startDate = request.getParameter("startdate");
		
		UserDao dao = new UserService();
		String instractor = "";
		int userid = 0;
		try {
			User inst = dao.getUser(instractorid);
			instractor = (inst.getFirstname() + " " + inst.getLastname());
			userid = inst.getId();
		} catch (SQLException e) {
		}
		Course course = new Course(name, capacity, fees, numberofstudents, numberofsessions, level, instractor, courseType, roomType, startDate, endDate, userid);
		CourseDao coursService = new CourseService();
		HttpSession session = request.getSession();
		try {
			if(coursService.create(course)) {
				session.setAttribute("successMSG", "Course: " + name + " has been added successfully.");
				response.sendRedirect(request.getContextPath() + "/Courses");
			} else {
				session.setAttribute("errorMSG", "An error occurred try again.");
				response.sendRedirect(request.getContextPath() + "/Courses");
			}
		} catch (SQLException e) {
			session.setAttribute("errorMSG", "An error occurred try again.");
			response.sendRedirect(request.getContextPath() + "/Courses");
		}
	}

}
