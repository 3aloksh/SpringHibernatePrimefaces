package com.gadwala.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gadwala.dao.UserDao;
import com.gadwala.model.User;
import com.gadwala.service.UserService;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/index.html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String url = request.getParameter("url");
		
		UserDao userService = new UserService();
		
		try {
			User user = userService.authenticateUser(email, password);
			
			if(user != null && user.getFirstname() != "") {
				// user authenticated successfully create the session and redirect to the home page
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				
				if(user.getType().equals("admin")) {
					// redirect to administrator home page.
					response.sendRedirect(request.getContextPath() + "/admin/home.jsp");
				} else if(user.getType().equals("student")) {
					// redirect to student page.
					response.sendRedirect(request.getContextPath() + "/student/home.jsp");
				} else if(user.getType().equals("instructor")) {
					// redirect to instructor home page.
					response.sendRedirect(request.getContextPath() + "/InstructorCourses");
				}else if(user.getType().equals("manager")) {
					// redirect to instructor home page.
					response.sendRedirect(request.getContextPath() + "/Manager");
				} else {
					
				}
				
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("errorMessage", "Email or password incorrect");
				if(url.equals("admin")) {
					response.sendRedirect("/gadwala/admin/login.jsp");
				} else if(url.equals("student")) {					
					response.sendRedirect("/gadwala/student/login.jsp");
				} else if(url.equals("instructor")){
					response.sendRedirect("/gadwala/instructor/login.jsp");
				} else {
					// redirect to Unauthorized page.
				}
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
