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
import com.gadwala.service.Email;
import com.gadwala.service.UserService;

/**
 * Servlet implementation class ForgotPassword
 */
@WebServlet("/ForgotPassword")
public class ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String MSG = "http://localhost:8080/gadwala/setup-password.jsp?email=";
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		UserDao userService = new UserService();
		HttpSession session = request.getSession(true);
		
		try {
			User user = userService.getUserByEmail(email);
			if(user == null || user.getEmail().equals("")) {
				redirect(session, response, request);
			} else {
				if(Email.send(email, user.getFirstname(), ("Reterive your password " + MSG + email))) {
					response.sendRedirect(request.getContextPath() + "/check-mail-for-password.jsp");
				}
				
			}
		} catch (SQLException e) {
			redirect(session, response, request);
		}
		
	}
	private void redirect(HttpSession session, HttpServletResponse response, HttpServletRequest request) throws IOException {
		session.setAttribute("errorMessage", "Email does not exist on the database.");
		response.sendRedirect(request.getContextPath() + "/forgot-password.jsp");
		
	}

}
