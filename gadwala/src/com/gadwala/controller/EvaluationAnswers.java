package com.gadwala.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gadwala.dao.AnswerDao;
import com.gadwala.model.Answer;
import com.gadwala.model.User;
import com.gadwala.service.AnswerService;

@WebServlet("/EvaluationAnswers")
public class EvaluationAnswers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("user");
		Enumeration<String> answers = request.getParameterNames();
		AnswerDao dao = new AnswerService();
		Answer answer = new Answer();
		answer.setCourseid(Integer.parseInt(request.getParameter("courseId")));
		answer.setStudentid(loggedInUser.getId());

		ArrayList<String> list = new ArrayList<>();
		ArrayList<String> list1 = new ArrayList<>();
		
		while (answers.hasMoreElements()) {
			String element = answers.nextElement();
			if(element.startsWith("ans")) {
				list.add(element);
			}
			if(element.startsWith("quest")) {
				list1.add(element);
			}
			if(element.startsWith("note")) {
				answer.setAnswer(request.getParameter("notes"));
				dao.create(answer);
			}
		}
		for (String question : list1) {
			for (String ans : list) {
				answer.setQuestionid(Integer.parseInt(request.getParameter(question)));
				answer.setAnswer(request.getParameter(ans));
				dao.create(answer);
				list.remove(ans);
				break;
			}
		}
	
	}

}
