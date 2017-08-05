package com.gadwala.users.controller;

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
import com.gadwala.dao.UserDao;
import com.gadwala.model.Course;
import com.gadwala.model.User;
import com.gadwala.service.CourseService;
import com.gadwala.service.UserService;

/**
 * Servlet implementation class Users
 */
@WebServlet("/Users")
public class Users extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserDao dao = new UserService();
		try {
			ArrayList<User> users = dao.getAll();
			if(users != null) {
				session.setAttribute("users", users);				
				response.sendRedirect(request.getContextPath() + "/admin/users.jsp");
			} else {
				session.setAttribute("users", null);
				response.sendRedirect(request.getContextPath() + "/admin/users.jsp");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			response.sendRedirect(request.getContextPath() + "/admin/users.jsp");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("searchUser");
		HttpSession session = request.getSession();
		
		UserDao dao = new UserService();
		try {
			ArrayList<User> users = dao.getUserByName(search);
			if(users != null) {				
				session.setAttribute("users", users);
				response.sendRedirect(request.getContextPath() + "/admin/users.jsp");
			} else {
				session.setAttribute("users", null);
				response.sendRedirect(request.getContextPath() + "/admin/users.jsp");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			response.sendRedirect(request.getContextPath() + "/admin/users.jsp");
		}
	}

}
