package com.gadwala.users.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gadwala.dao.StudentGroupDao;
import com.gadwala.dao.UserDao;
import com.gadwala.model.StudentGroup;
import com.gadwala.model.User;
import com.gadwala.service.Email;
import com.gadwala.service.StudentGroupService;
import com.gadwala.service.UserService;

/**
 * Servlet implementation class AddUser
 */
@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/admin/add-user.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
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
				userService.create(regUser);
				// student needs to be assigned to group automatically
				if(type.equals("student")) {
					System.out.println("student");
					StudentGroupDao sGDao = new StudentGroupService();
					StudentGroup studentGroup = new StudentGroup();
					studentGroup.setStudentid(regUser.getId());
					studentGroup.setLevel(level);
					if(sGDao.getGroupCapacity(level, "GA") < 10) {
						studentGroup.setGroup("GA");
						sGDao.create(studentGroup);
					} else if(sGDao.getGroupCapacity(level, "GB") < 10) {
						studentGroup.setGroup("GB");
						sGDao.create(studentGroup);
					}else if(sGDao.getGroupCapacity(level, "GC") < 10) {
						studentGroup.setGroup("GC");
						sGDao.create(studentGroup);
					} else if(sGDao.getGroupCapacity(level, "GD") < 10) {
						studentGroup.setGroup("GD");
						sGDao.create(studentGroup);
					} else {
						System.out.println("Maximum Capcatiy for Groups Has been reached!");
					}
					
				}
				session.setAttribute("successMSG", "User created successfully.");
				// send an email to the registered user containing your username and password
				Email.send(email, firstname, "Welcome to Gadwala System your username: " +  email + " , and Password: " + password + " and URL: " + request.getContextPath());
				response.sendRedirect(request.getContextPath()+ "/Users");
			}
		} catch (SQLException e) {
			session.setAttribute("errorMSG", "An error occured.");
			response.sendRedirect(request.getContextPath()+ "/Users");
		}
	}

}
