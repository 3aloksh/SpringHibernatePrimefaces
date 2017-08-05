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

/**
 * Servlet implementation class SetupPassword
 */
@WebServlet("/SetupPassword")
public class SetupPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetupPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");
		String confPassword = request.getParameter("confpassword");
		String email = request.getParameter("email");
		HttpSession session = request.getSession();
		UserDao userService = new UserService();
		
		if(!password.equals(confPassword)) {
			session.setAttribute("errorMessage", "Passwords do not match!");
			response.sendRedirect(request.getContextPath() + "/setup-password.jsp");
		} else {
			try {
				User user = userService.getUserByEmail(email);
				if(user == null || user.getEmail().equals("")) {
					session.setAttribute("errorMessage", "User not found please click the link in the mail again");
					response.sendRedirect(request.getContextPath() + "/setup-password.jsp");
				} else {
					if(userService.updatePassword(password, user.getId())) {
						response.sendRedirect(request.getContextPath() + "/index.html");
					}
				}
			} catch (SQLException e) {
				session.setAttribute("errorMessage", "An Error occurred");
				response.sendRedirect(request.getContextPath() + "/setup-password.jsp");
			}
		}
	}

}
