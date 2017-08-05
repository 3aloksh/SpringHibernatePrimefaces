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

import com.gadwala.dao.CourseDao;
import com.gadwala.dao.GroupDao;
import com.gadwala.dao.StudentGroupDao;
import com.gadwala.model.Course;
import com.gadwala.model.Group;
import com.gadwala.model.StudentGroup;
import com.gadwala.model.User;
import com.gadwala.service.CourseService;
import com.gadwala.service.GroupService;
import com.gadwala.service.StudentGroupService;


@WebServlet("/StudentEvaluation")
public class StudentEvaluation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User student = (User) session.getAttribute("user");
		StudentGroupDao dao = new StudentGroupService();
		StudentGroup studentGroup = dao.getByStudentId(student.getId());
		GroupDao gDAO = new GroupService();
		
		try {
			ArrayList<Group> groups = gDAO.getGroupsByNameAndLevel(studentGroup.getGroup(), studentGroup.getLevel());
			// get courses for this student
			CourseDao cDAO = new CourseService();
			ArrayList<Course> courses = new ArrayList<>();
			for (Group group : groups) {
				courses.add(cDAO.getCourseById(group.getCourseid()));
			}
			session.setAttribute("courses", courses);
			response.sendRedirect(request.getContextPath() + "/student/evaluate.jsp");
		} catch (SQLException e) {
		
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
