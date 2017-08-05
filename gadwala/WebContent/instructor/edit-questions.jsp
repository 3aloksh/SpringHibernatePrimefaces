<%@page import="com.gadwala.service.CourseService"%>
<%@page import="com.gadwala.dao.CourseDao"%>
<%@page import="com.gadwala.model.Question"%>
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
<title>Evaluation Questions</title>
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
<h2 style="position:relative; color: white;">Edit Question</h2>
<div style="position: absolute; color: white;">
	<% 
		Question question = (Question) session.getAttribute("question");
		CourseDao dao = new CourseService();
		Course course = dao.getCourseById(question.getCourseid());
		
	%>
</div>
<div>
</div><br/><br/>
<div style="position: relative; color: white;">
<div id="panel" class="panel panel-info">
  <div class="panel-heading">
    <h3 class="panel-title">Edit Course: <%= course.getName() %></h3>
  </div>
  <div class="container-fluid panel-body">
  	<form method="post" action="<%= request.getContextPath() + "/EditQuestion?id=" + question.getId() + "&courseId=" + question.getCourseid() %>">
  	<div class="row">
  		<% if(session.getAttribute("errorMSG") != null) {
        		out.println("<span class=\"alert alert-danger\"><strong style=\"color: red; text-align: center;\">" + session.getAttribute("errorMSG") +"</strong></span>");
        		session.removeAttribute("errorMSG");
        	}
        %>
        <% if(session.getAttribute("successMSG") != null) {
        		out.println("<span class=\"alert alert-success\"><strong style=\"color: green; text-align: center;\">" + session.getAttribute("successMSG") +"</strong></span>");
        		session.removeAttribute("successMSG");
        	}
        %>
  	</div><hr/>
  	<div class="row">
  		<div class="col-md-3" style="font-weight: bold;">Question</div>
  		<div class="col-md-9"><input style="width: 100%;" type="text" value="<%= question.getQuestion() %>" name="question" required placeholder="Write down your Question..." /> </div>
  	</div>
  	<hr/>
  	<div class="row">
  		<div class="col-md-3" style="font-weight: bold;">Answers</div>
  		<div class="col-md-9"><input style="width: 100%;" type="text" value="<%= question.getAnswers() %>" name="answer" required placeholder="answer1, answer2, answer3..." /> </div><br/><br/>
  		<span style="color: red; font-size: .7em; font-weight: bold;">* Note Write all Answers seperated by Comma ","</span>
  	</div>
  	<hr/>
  	<div class="row">
  		<input type="submit" class="btn btn-success btn-md" value="submit"/> 
  	</div>
  	</form>
  </div>
</div>
</div>
<script src="../js/jquery-3.1.0.min.js"></script>
<script src="../js/bootstrap.min.js"></script>

</body>
</html>
