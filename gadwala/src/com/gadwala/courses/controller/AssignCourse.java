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
import com.gadwala.dao.GroupDao;
import com.gadwala.model.Course;
import com.gadwala.model.Group;
import com.gadwala.service.CourseService;
import com.gadwala.service.GroupService;

/**
 * Servlet implementation class AssignCourse
 */
@WebServlet("/AssignCourse")
public class AssignCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int level = Integer.parseInt(request.getParameter("level"));
		String name = request.getParameter("name");
		HttpSession session = request.getSession();
		
		CourseDao dao = new CourseService();
		try {
			ArrayList<Course> courses = dao.getCoursesByLevel(level);
			if(courses.size() == 0) {
				session.setAttribute("courses", null);
				session.setAttribute("errMSG", "No Courses Found For this Level Add Courses First!.");
				response.sendRedirect(request.getContextPath() + "/admin/assign-courses.jsp?level="+ level + "&name=" + name);
			} else {
				session.setAttribute("courses", courses);
				session.setAttribute("errMSG", null);
				response.sendRedirect(request.getContextPath() + "/admin/assign-courses.jsp?level="+ level + "&name=" + name);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int courseid = Integer.parseInt(request.getParameter("course"));
		String gname = request.getParameter("name");
		int level = Integer.parseInt(request.getParameter("level"));
		
		HttpSession session = request.getSession();
		GroupDao dao = new GroupService();
		Group group = new Group(gname, courseid, level);
		
		try {
			ArrayList<Group> groups = dao.getGroupsByNameAndLevel(gname, level);
			for (Group group2 : groups) {
				if(group2.getCourseid() == courseid) {
					session.setAttribute("errMSG", "Level " + level + " Group " + gname + " has been already assigned for this course.");
					response.sendRedirect(request.getContextPath() + "/Groups?level=" + level);
					return;
				}
			}
			if(groups.size() == 2) {
				session.setAttribute("errMSG", "Level " + level + " Group " + gname + " has been assigned MAX required courses for this semester!");
				response.sendRedirect(request.getContextPath() + "/Groups?level=" + level);
			} else {
				dao.create(group);
				session.setAttribute("errMSG", "Level " + level + " Group " + gname + " has assgined successfully.");
				response.sendRedirect(request.getContextPath() + "/Groups?level=" + level);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		
	}

}
