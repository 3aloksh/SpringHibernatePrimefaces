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
import com.gadwala.dao.GroupDao;
import com.gadwala.dao.LocationDao;
import com.gadwala.dao.ScheduleDao;
import com.gadwala.dao.UserDao;
import com.gadwala.model.Course;
import com.gadwala.model.User;
import com.gadwala.service.CourseService;
import com.gadwala.service.GroupService;
import com.gadwala.service.LocationService;
import com.gadwala.service.ScheduleService;
import com.gadwala.service.UserService;


@WebServlet("/EditCourse")
public class EditCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		CourseDao dao = new CourseService();
		UserDao uDao = new UserService();
		HttpSession session = request.getSession();
		
		try {
			if(dao.getCourseById(id) != null) {
				session.setAttribute("insts", uDao.getInstructors());
				Course course = dao.getCourseById(id);
				session.setAttribute("course", course);
				response.sendRedirect(request.getContextPath() + "/admin/edit-course.jsp");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		int fees = Integer.parseInt(request.getParameter("fees"));
		int id = Integer.parseInt(request.getParameter("id"));
		int numberofstudents = Integer.parseInt(request.getParameter("mubmerofstudents"));
		int numberofsessions = Integer.parseInt(request.getParameter("numberofsessions"));
		int instractorid = Integer.parseInt(request.getParameter("instractor"));
		int level = Integer.parseInt(request.getParameter("level"));
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
		course.setId(id);
		CourseDao coursService = new CourseService();
		HttpSession session = request.getSession();
		
		try {
			if(coursService.update(course)) {
				ScheduleDao da = new ScheduleService();
				GroupDao gDao = new GroupService();
				LocationDao lDao = new LocationService();
				da.delete();
				gDao.deleteByCourseID(id);
				lDao.updateAll();
				//da.updateSchedule(name, id);
				
				session.setAttribute("successMSG", "Course: " + name + " has been updated successfully.");
				response.sendRedirect(request.getContextPath() + "/Courses");
			} else {
				session.setAttribute("errorMSG", "An error occurred try again.");
				response.sendRedirect(request.getContextPath() + "/Courses");
			}
		} catch (SQLException e) {
			session.setAttribute("errorMSG", "An error occurred try again.");
			System.out.println(e.getMessage());
			response.sendRedirect(request.getContextPath() + "/Courses");
		}
	}

}
