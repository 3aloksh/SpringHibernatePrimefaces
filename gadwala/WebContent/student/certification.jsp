<%@page import="com.gadwala.service.AnswerService"%>
<%@page import="com.gadwala.dao.AnswerDao"%>
<%@page import="com.gadwala.model.Answer"%>
<%@page import="java.util.Scanner"%>
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
<title>Courses Certifications</title>
<style type="text/css">
.mydiv
{
	position:relative;
	top:0;
	left:0;
	font-size: 1.6em;
	color: black;
	
}
img
{
	width:60%;
	height:50%;
	text-align:center;
	margin:auto;
	text-align: center;    
    display: table-cell;
    vertical-align: middle; 
	
	
}
.p1
{
	position:absolute;
	top:35%;
	left:28%;
	
	
}
.p2
{
	position:absolute;
	top:51%;
	left:28%;
	
	
}
</style>
<script type="text/javascript">
	var showQuestions = function (name) {
		var div = document.getElementById("questions");
		var e = document.getElementById("course");
		var courseId = e.options[e.selectedIndex].value;
		if(courseId > 0) {
			window.location.href = 'http://localhost:8080/gadwala/ShowCertification?courseId=' + courseId;
		}
	}
</script>
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
      <a class="navbar-brand" href="#">Gadwala</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
      
        <li><a href="<%= request.getContextPath() + "/student/home.jsp" %>">Home</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
     
        <li><a href="<%= request.getContextPath() + "/Logout" %>">Log out</a></li>
       
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<% 
	ArrayList<Course> courses = (ArrayList<Course>) session.getAttribute("courses");
    ArrayList<Answer> answersForCourse = (ArrayList<Answer>) session.getAttribute("answersForCourse");
    AnswerDao dao = new AnswerService();
%>
<div class="container" style="position: relative; color: white">
<h3>Get Certification for Course : </h3>
	<label>Choose Course</label>
			<select name="course" id="course" required style="color: black;" onchange="showQuestions(this)">
				<option value="0">Select Course</option>
				<c:forEach items="${courses}" var="course">
					<option value='<c:out value="${course.getId()}"></c:out>'><c:out value="${course.getName()}"></c:out></option>
				</c:forEach>
			</select><br/>
</div>
<div class="container" style="font-weight: bold; position: relative; margin-bottom: 100px; margin-top: 50px;">
		
	<c:if test="${answersForCourse ne null}">
		<%
			CourseDao cDao = new CourseService();
			Course course = cDao.getCourseById(Integer.parseInt(request.getParameter("courseId")));
			//out.print(course.getName());
			User student = (User) session.getAttribute("user");
		%>
		<div class="mydiv">
		 <img src="../images/cert.png"/>
		 <p class="p1">Student : <%= student.getFirstname() + " " + student.getLastname() %></p>
		 <p class="p2">Course : <%= course.getName() %></p>
		
		</div>
	</c:if>
	
	<c:if test="${answersForCourse eq null}">
		<h2 class="alert alert-danger">Please Make sure you have Evaluated All course First to get certification<br/> <a href="<%= request.getContextPath() + "/StudentEvaluation" %>">Go Evaluation Page</a></h2>
		
</c:if>
</div>
<script src="../js/jquery-3.1.0.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
</body>
</html>
