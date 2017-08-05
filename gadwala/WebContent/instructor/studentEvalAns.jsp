<%@page import="com.gadwala.service.UserService"%>
<%@page import="com.gadwala.dao.UserDao"%>
<%@page import="com.gadwala.model.Question"%>
<%@page import="com.gadwala.service.QuestionService"%>
<%@page import="com.gadwala.dao.QuestionDao"%>
<%@page import="com.gadwala.service.CourseService"%>
<%@page import="com.gadwala.dao.CourseDao"%>
<%@page import="com.gadwala.model.Answer"%>
<%@page import="com.gadwala.model.Course"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gadwala.model.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="../css/bootstrap.min.css"/>
<link rel="stylesheet" href="../css/adminpage.css"/>
<script src="../js/jquery-3.1.0.min.js"></script>
<title>Evaluation Answers</title>
<style type="text/css">
	.row > div {
		text-align: left;
	}
	#panel {
		width: 600px;
		height: 80%;
		margin: 10px auto;
	}
	.panel-body {
		color: black;
		font-size: 1.3em;
	}
</style>
</head>
<% 
	ArrayList<Answer> answers = (ArrayList<Answer>) session.getAttribute("ANSWERS");
	
%>
<body>
<div class="layer"><div class="layer2"></div></div>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="<%= request.getContextPath() + "/instructor/home.jsp"%>">Gadwala</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
      
       
      </ul>
       <form class="navbar-form navbar-left" method="post">
        <div class="form-group">
          <input type="text" style="width: 100%" name="searchCourse" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Search</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
       <li><a href="<%= request.getContextPath() + "/InstructorCourses" %>">My Courses</a></li>
        <li><a href="<%=request.getContextPath() + "/Logout"%>">Log out</a></li>  
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<div class="container-fluid" style="position: relative; color: white;"> 
	<h2>Students Evaluation For Questions on Course : <%= 
			request.getParameter("cName")
		%></h2>
		<% 
			if(answers != null) {
		%>
	<table class="table" style="position: relative; background-color: gray; color: maroon; font-weight: bold;">
    <thead>
      <tr style="font-size: 1.2em; font-weight: bold;">
        <th style="text-align: center; font-weight: bolder;">Course</th>
        <th style="text-align: center; font-weight: bolder;">Question</th>
        <th style="text-align: center; font-weight: bolder;">Answer</th>
        <th style="text-align: center; font-weight: bolder;">Student</th>
      </tr>
    </thead>
    <tbody>
     <% for(Answer a : answers) { %>
    	<tr>
    		<td><% CourseDao dao = new CourseService(); 
    			   Course course = dao.getCourseById(a.getCourseid());
    			   String courseName = course.getName();
    			%>
    			<%= courseName %></td>
    		<td>
    		<%
    			QuestionDao qDao = new QuestionService();
    			String question = "Notes";
    			if(a.getQuestionid() != 0) {    				
    				Question q = qDao.getQuestion(a.getQuestionid());
    				question = q.getQuestion();
    			}
    		%>
    		<%= question %></td>
    		<td><%= a.getAnswer() %></td>
    		<td>
    			<%
    				UserDao uDao = new UserService();
    				User user = uDao.getUser(a.getStudentid());
    				String username = user.getFirstname() + " " + user.getLastname();
    			%>
    			<%= username %></td>
    		
    	</tr>
    	<% } %>
    </tbody>
  </table>
		<% 		
			}else { %>
    		<h3 style="color: red;" class="alert alert-danger">No Evaluation Answers has been submitted yet by students for this course!</h3>
    	<% } %>
	
</div>
<script src="../js/jquery-3.1.0.min.js"></script>
<script src="../js/bootstrap.min.js"></script>

</body>
</html>
