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
<title>Manage Courses</title>
<style type="text/css">
	tr:hover {
		font-weight: bold; 
		cursor: pointer;
		background-color: gray;
		color: black;	
	}
</style>
<script type="text/javascript">
$(document).ready(function($) {
	$('.clickable-row').click( function() {
    	window.location = $(this).data("href");
	});
});

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
      <a class="navbar-brand" href="<%= request.getContextPath() + "/admin/home.jsp"%>">Gadwala</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
      
       
      </ul>
       <form class="navbar-form navbar-left" action="<%= request.getContextPath() + "/Courses" %>" method="post">
        <div class="form-group">
          <input type="text" style="width: 100%" name="searchCourse" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Search</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
       <li><a href="<%= request.getContextPath() + "/Courses" %>">Courses</a></li>
       <li><a href="<%= request.getContextPath() + "/Students" %>">Students</a></li>
        <li><a href="<%= request.getContextPath() + "/Users" %>">Users</a></li>
        <li><a href="<%=request.getContextPath() + "/Logout"%>">Log out</a></li>
       
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<h2 style="position:relative; color: white;">Manage All Courses</h2>
<img alt="users" src="../images/courses.png" style="position:relative;">
<div style="position: absolute;">
        <a href="<%= request.getContextPath() + "/AddCourse" %>" class="btn btn-success btn-sm" >
          <span class="glyphicon glyphicon-plus">
          </span> Add new course
        </a>
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
</div>
<hr/>
<div style="position: absolute; color: white;">
	<% 
		ArrayList<Course> courses = (ArrayList<Course>)session.getAttribute("courses");
	%>
</div>
<div>
	<c:choose>
		<c:when test="${courses.size() > 0}">
				<table class="table" style="position: absolute; color: white;">
    <thead>
      <tr style="font-size: 1.2em; font-weight: bold;">
        <th>ID</th>
        <th>Course</th>
        <th>Capacity</th>
        <th>Fees</th>
        <th># students</th>
        <th># sessions</th>
        <th>Instructor</th>
        <th>Course Type</th>
        <th>Level</th>
        <th>Room Type</th>
        <th>Start Date</th>
        <th>End Date</th>
        <th style="text-align: center;">Action</th>
      </tr>
    </thead>
    <tbody>
     <c:forEach items="${courses}" var="c">
    	<tr class='clickable-row' data-href='<c:out value="/gadwala/CourseDetails?id=${c.id}"></c:out>'>
    		<td><c:out value="${c.id}"/></td>
    		<td><c:out value="${c.name}"/></td>
    		<td><c:out value="${c.capacity}"/></td>
    		<td><c:out value="${c.fees}"/></td>
    		<td><c:out value="${c.numberOfStudents}"/></td>
    		<td><c:out value="${c.numberOfSessions}"/></td>
    		<td><c:out value="${c.instractor}"/></td>
    		<td><c:out value="${c.courseType}"/></td>
    		<td><c:out value="${c.level}"/></td>
    		<td><c:out value="${c.roomType}"/></td>
    		<td><c:out value="${c.startDate}"/></td>
    		<td><c:out value="${c.endDate}"/></td>
    		<td><a href="<c:out value="/gadwala/EditCourse?id=${c.id}"/>"  class="btn btn-primary btn-sm">
          			<span class="glyphicon glyphicon-pencil"></span> Edit 
        		</a> <a href="<c:out value="/gadwala/DeleteCourse?id=${c.id}"/>"  class="btn btn-danger btn-sm">
          			<span class="glyphicon glyphicon-trash"></span> Delete 
        		</a>
    	</tr>
     </c:forEach>
    </tbody>
  </table>
		</c:when>
		<c:otherwise>
			<h3 style="position: relative; text-align: center; color: white; color: red; margin-left: 150px;">No Courses Founded.</h3>
		</c:otherwise>
	</c:choose>
</div>
<script src="../js/jquery-3.1.0.min.js"></script>
<script src="../js/bootstrap.min.js"></script>

</body>
</html>
