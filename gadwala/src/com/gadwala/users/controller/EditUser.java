package com.gadwala.users.controller;

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


@WebServlet("/EditUser")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		UserDao dao = new UserService();
		HttpSession session = request.getSession();
		try {
			if(dao.getUser(id) != null) {
				User user = dao.getUser(id);
				session.setAttribute("user", user);
				response.sendRedirect(request.getContextPath() + "/admin/edit-user.jsp");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
		int id = Integer.parseInt(request.getParameter("id"));
		
		int level = 0;
		if(request.getParameter("level") != null) {			
			level = Integer.parseInt(request.getParameter("level"));
			System.out.println(level);
		}
		
		UserDao userService = new UserService();
		HttpSession session = request.getSession();
		try {
			User user = userService.getUserByEmail(email);
			if(user != null) {
				session.setAttribute("deleteMSG", "User already exist Under " + user.getType()+ " Account.");
				response.sendRedirect(request.getContextPath()+ "/Users");
			} else {
				User regUser = new User(firstname, lastname, email, password, phone, type);
				regUser.setLevel(level);
				regUser.setId(id);
				if(userService.edit(regUser)) {					
					session.setAttribute("deleteMSG", "User has been updated successfully.");
				} else {
					session.setAttribute("deleteMSG", "Can not edit user.");
				}
				response.sendRedirect(request.getContextPath()+ "/Users");
			}
		} catch (SQLException e) {
			session.setAttribute("deleteMSG", "An error occured try again later.");
			response.sendRedirect(request.getContextPath()+ "/Users");
		}
	}

}
