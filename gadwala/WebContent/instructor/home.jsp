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
<title>Instructor Home Page</title>
<style type="text/css">
	tr:hover {
		font-weight: bold; 
		cursor: pointer;
		background-color: gray;
		color: black;	
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
<h2 style="position:relative; color: white;">Manage All Courses</h2>
<img alt="users" src="../images/courses.png" style="position:relative;">
<div style="position: absolute; color: white;">
	<% 
		ArrayList<Course> courses = (ArrayList<Course>) session.getAttribute("courses");
		
	%>
</div>
<br/>
<br/>
<div style="position:relative;">
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
	<br/>
	<br/>
	<c:choose>
		<c:when test="${courses ne null}">
			<table class="table" style="position: absolute; color: white;">
    <thead>
      <tr style="font-size: 1.2em; font-weight: bold;">
        <th>ID</th>
        <th>Course</th>
        <th>Instructor</th>
        <th>Level</th>
        <th>Start Date</th>
        <th>End Date</th>
        <th style="text-align: center;">Action</th>
      </tr>
    </thead>
    <tbody>
     <c:forEach items="${courses}" var="c">
    	<tr class='clickable-row'>
    		<td><c:out value="${c.getId()}"/></td>
    		<td><c:out value="${c.getName()}"/></td>
    		<td><c:out value="${c.getInstractor()}"/></td>
    		<td><c:out value="${c.getLevel()}"/></td>
    		<td><c:out value="${c.getStartDate()}"/></td>
    		<td><c:out value="${c.getEndDate()}"/></td>
    		<td>
    			<a style="font-weight: bold;" href="<c:out value="/gadwala/StudentEvalAns?id=${c.id}"/>"  class="btn btn-success btn-sm">
          			<span class="glyphicon glyphicon-arrow-left"></span> Students Evaluation </a>
    			<a href="<c:out value="/gadwala/Evaluation?id=${c.id}"/>"  class="btn btn-primary btn-sm">
          			<span class="glyphicon glyphicon-pencil"></span> Assign Evaluation Questions </a> 
          			<a href="<c:out value="/gadwala/Questions?id=${c.id}"/>"  class="btn btn-primary btn-sm">
          			 Evaluation Questions</a>
    	</tr>
     </c:forEach>
    </tbody>
  </table>
  	<!-- evaluation  -->
		</c:when>
		<c:otherwise>
			<h3 style="position: relative; text-align: center; color: white; color: red; margin-left: 150px;">No Courses Founded For you Right now.</h3>
		</c:otherwise>
	</c:choose>
</div>
<script src="../js/jquery-3.1.0.min.js"></script>
<script src="../js/bootstrap.min.js"></script>

</body>
</html>
