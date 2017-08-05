package com.gadwala.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gadwala.dao.UserDao;
import com.gadwala.model.User;
import com.gadwala.service.UserService;


@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	private void redirect(String type, String adminPath, String studentPath, HttpServletRequest request, RequestDispatcher dispatcher) {
		if(type.equals("admin")) {					
			dispatcher = request.getRequestDispatcher(adminPath);
		} else {
			dispatcher = request.getRequestDispatcher(studentPath);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
		// get context path
		String path = request.getContextPath();
		String adminPath = path + "/admin/signup.jsp";
		String studentPath = path + "/student/signup.jsp";
		
		UserDao userService = new UserService();
		RequestDispatcher dispatcher = null;
		try {
			User user = userService.getUserByEmail(email);
			if(user == null || user.getFirstname().equals("")) {
				request.setAttribute("errorMSG", "Email Already Exist!");
				
				redirect(type, adminPath, studentPath, request, dispatcher);
			} else {
				User regUser = new User(firstname, lastname, email, password, phone, type);
				if(userService.create(regUser)) {
					request.setAttribute("successMSG", "Congrats! User has been registered successfully, Please Check your email to confirm your Account");
					redirect(type, adminPath, studentPath, request, dispatcher);
				} else {
					request.setAttribute("errorMSG", "There is a server error right now try again");
					
					redirect(type, adminPath, studentPath, request, dispatcher);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
