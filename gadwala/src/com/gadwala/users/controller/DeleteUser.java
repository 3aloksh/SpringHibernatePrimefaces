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
import com.gadwala.service.StudentGroupService;
import com.gadwala.service.UserService;


@WebServlet("/DeleteUser")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int id = Integer.parseInt(request.getParameter("id"));
		UserDao dao = new UserService();
		try {
			if(dao.delete(id)) {
				StudentGroupDao sGDAO = new StudentGroupService();
				sGDAO.delete(id);
				session.setAttribute("successMSG", "User has been deleted successfully");
				response.sendRedirect(request.getContextPath() + "/Users");
			} else {
				session.setAttribute("errorMSG", "Can not delete course");
				response.sendRedirect(request.getContextPath() + "/Users");
			}
		} catch (SQLException e) {
			session.setAttribute("errorMSG", "Something wrong on the server happend: " + e.getMessage());
			System.out.println(e.getMessage());
			response.sendRedirect(request.getContextPath() + "/Users");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
