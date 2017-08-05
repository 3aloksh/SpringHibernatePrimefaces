package com.gadwala.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gadwala.dao.StudentDao;
import com.gadwala.model.User;
import com.gadwala.service.StudentService;

/**
 * Servlet implementation class Students
 */
@WebServlet("/Students")
public class Students extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		StudentDao dao = new StudentService();
		try {
			ArrayList<User> students = dao.getAll();
			if(students != null) {		
				session.setAttribute("students", students);
				response.sendRedirect(request.getContextPath() + "/admin/students.jsp");
			} else {
				session.setAttribute("students", null);
				response.sendRedirect(request.getContextPath() + "/admin/students.jsp");
			}
		} catch (SQLException e) {
			System.out.println("StudentServlet: " + e.getMessage());
			response.sendRedirect(request.getContextPath() + "/admin/students.jsp");
		} catch (ClassNotFoundException e) {
			System.out.println("StudentServlet: " + e.getMessage());
		}
	
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
