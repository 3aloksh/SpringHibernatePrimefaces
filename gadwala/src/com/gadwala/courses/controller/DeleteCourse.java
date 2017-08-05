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
import com.gadwala.service.CourseService;
import com.gadwala.service.GroupService;
import com.gadwala.service.LocationService;
import com.gadwala.service.ScheduleService;


@WebServlet("/DeleteCourse")
public class DeleteCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int id = Integer.parseInt(request.getParameter("id"));
		CourseDao courseService = new CourseService();
		ScheduleDao sDao = new ScheduleService();
		GroupDao gDao = new GroupService();
		LocationDao lDao = new LocationService();
		
		try {
			if(courseService.delete(id)) {
				sDao.delete();
				gDao.deleteByCourseID(id);
				lDao.updateAll();
				session.setAttribute("successMSG", "Course has been deleted successfully");
				response.sendRedirect(request.getContextPath() + "/Courses");
			} else {
				session.setAttribute("errorMSG", "Can not delete course");
				response.sendRedirect(request.getContextPath() + "/Courses");
			}
		} catch (SQLException e) {
			session.setAttribute("errorMSG", "Something wrong on the server happend: " + e.getMessage());
			System.out.println(e.getMessage());
			response.sendRedirect(request.getContextPath() + "/Courses");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
