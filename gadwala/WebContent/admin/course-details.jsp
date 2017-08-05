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
<style type="text/css">
	.row > div {
		text-align: left;
	}
	#panel {
		width: 500px;
		height: 80%;
		margin: 10px auto;
	}
	.panel-body {
		color: black;
		font-size: 1.3em;
	}
</style>
<title>Course details</title>
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
          <input type="text" name="searchCourse" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Search</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
       <li><a href="<%= request.getContextPath() + "/Courses" %>">Courses</a></li>
        <li><a href="<%= request.getContextPath() + "/Users" %>">Users</a></li>
        <li><a href="<%=request.getContextPath() + "/Logout"%>">Log out</a></li>
       
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<h2 style="position:relative; color: white;">Course Details</h2>
<div style="position: absolute;">
       <% Course course = (Course) session.getAttribute("course"); %>
</div>
<hr/>
<div style="position: relative; color: white;">
<div id="panel" class="panel panel-info">
  <div class="panel-heading">
    <h3 class="panel-title">Course: <%= course.getName() %></h3>
  </div>
  <div class="container-fluid panel-body">
  	<div class="row">
  		<div class="col-md-6" style="font-weight: bold;">Course name</div>
  		<div class="col-md-6"><%= course.getName() %></div>
  	</div>
  	<hr/>
  	<div class="row">
  		<div class="col-md-6" style="font-weight: bold;">Capacity</div>
  		<div class="col-md-6"><%= course.getCapacity() %></div>
  	</div>
  	<hr/>
  	<div class="row">
  		<div class="col-md-6" style="font-weight: bold;">Fees</div>
  		<div class="col-md-6"><%= course.getFees() %></div>
  	</div>
  	<hr/>
  	<div class="row">
  		<div class="col-md-6" style="font-weight: bold;">Number of Students</div>
  		<div class="col-md-6"><%= course.getNumberOfStudents() %></div>
  	</div>
  	<hr/>
  	<div class="row">
  		<div class="col-md-6" style="font-weight: bold;">Number of Sessions</div>
  		<div class="col-md-6"><%= course.getNumberOfSessions() %></div>
  	</div>
  	<hr/>
  	<div class="row">
  		<div class="col-md-6" style="font-weight: bold;">Course Type</div>
  		<div class="col-md-6"><%= course.getCourseType() %></div>
  	</div>
  	<hr/>
  	<div class="row">
  		<div class="col-md-6" style="font-weight: bold;">Room Type</div>
  		<div class="col-md-6"><%= course.getRoomType() %></div>
  	</div>
  	<hr/>
  	<div class="row">
  		<div class="col-md-6" style="font-weight: bold;">Instructor</div>
  		<div class="col-md-6"><%= course.getInstractor() %></div>
  	</div>
  	<hr/>
  	<div class="row">
  		<div class="col-md-6" style="font-weight: bold;">Start date</div>
  		<div class="col-md-6"><%= course.getStartDate() %></div>
  	</div>
  	<hr/>
  	<div class="row">
  		<div class="col-md-6" style="font-weight: bold;">End date</div>
  		<div class="col-md-6"><%= course.getEndDate() %></div>
  	</div><hr/>
  	<div class="row">
  		 <a href="/gadwala/admin/add-user.jsp" class="btn btn-success btn-sm" >
          <span class="glyphicon glyphicon-plus">
          </span> Assign students to this course
        </a>
  	</div>
  </div>
</div>
</div>
<div>
	
</div>
<script src="../js/jquery-3.1.0.min.js"></script>
<script src="../js/bootstrap.min.js"></script>

</body>
</html>
