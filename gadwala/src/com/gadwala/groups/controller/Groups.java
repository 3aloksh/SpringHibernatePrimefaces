package com.gadwala.groups.controller;

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
 * Servlet implementation class Groups
 */
@WebServlet("/Groups")
public class Groups extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int level = Integer.parseInt(request.getParameter("level"));
		
		CourseDao cdao = new CourseService();
		GroupDao dao = new GroupService();
		
		try {
			ArrayList<Group> ga = dao.getGroupsByNameAndLevel("GA", level);
			ArrayList<Group> gb = dao.getGroupsByNameAndLevel("GB", level);
			ArrayList<Group> gc = dao.getGroupsByNameAndLevel("GC", level);
			ArrayList<Group> gd = dao.getGroupsByNameAndLevel("GD", level);
			
			if(ga.size() == 0) {
				session.setAttribute("ga", null);
			} else {
				ArrayList<String> gaCourses = new ArrayList<>();
				for (Group group : ga) {
					Course course = cdao.getCourseById(group.getCourseid());
					gaCourses.add(course.getName());
				}
				session.setAttribute("ga", gaCourses);
			}
			if(gb.size() == 0) {
				session.setAttribute("gb", null);
			} else {
				ArrayList<String> gbCourses = new ArrayList<>();
				for (Group group : gb) {
					Course course = cdao.getCourseById(group.getCourseid());
					gbCourses.add(course.getName());
				}
				session.setAttribute("gb", gbCourses);
			}
			if(gc.size() == 0) {
				session.setAttribute("gc", null);
			} else {
				ArrayList<String> gcCourses = new ArrayList<>();
				for (Group group : gc) {
					Course course = cdao.getCourseById(group.getCourseid());
					gcCourses.add(course.getName());
				}
				session.setAttribute("gc", gcCourses);
			}
			if(gd.size() == 0) {
				session.setAttribute("gd", null);
			} else {
				ArrayList<String> gdCourses = new ArrayList<>();
				for (Group group : gd) {
					Course course = cdao.getCourseById(group.getCourseid());
					gdCourses.add(course.getName());
				}
				session.setAttribute("gd", gdCourses);
			}
			response.sendRedirect(request.getContextPath() + "/admin/groups.jsp?level=" + level);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
