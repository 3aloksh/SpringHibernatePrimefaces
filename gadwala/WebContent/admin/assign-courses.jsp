<%@page import="com.gadwala.model.Group"%>
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
<title>Manage Groups</title>
<style type="text/css">
	body {
		color: white;
	}
	#groups {
		font-weight: bold; 
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
      <a class="navbar-brand" href="<%= request.getContextPath() + "/admin/home.jsp"%>">Gadwala</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
      
       
      </ul>
       <form class="navbar-form navbar-left" action="<%= request.getContextPath() + "/Users" %>" method="post">
        <div class="form-group">
          <input type="text" style="width: 100%;" name="searchUser" class="form-control" placeholder="Search">
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
<h2 style="position:relative; color: white;">Manage Gadwala Groups</h2>
<img alt="users" src="../images/users.png" style="position:relative;">

<hr/>
<div style="position: absolute; color: white;">
</div>
<div>
	<% 
		ArrayList<Course> courses = (ArrayList<Course>)session.getAttribute("courses");
	%>
	<div class="container" style="position: absolute; margin-left: 100px;">
 		
			<h2><span style="color: red; font-weight: bold;">
				<% if(session.getAttribute("errMSG") != null) { out.print(session.getAttribute("errMSG")); session.invalidate(); } %></span></h2>
				<br/>	 		
	<c:if test="${courses.size() gt 0}">
		<div class="row">
		<h2>Assign Courses to Level <%= request.getParameter("level") %> Group: <%= request.getParameter("name") %></h2>
		<form action="<%= request.getContextPath() + "/AssignCourse?level="+request.getParameter("level")+"&name="+request.getParameter("name")%>" method="post">
			<label>Choose Course</label>
			<select name="course" required style="color: black;">
				<c:forEach items="${courses}" var="course">
					<option value='<c:out value="${course.getId()}"></c:out>'><c:out value="${course.getName()}"></c:out></option>
				</c:forEach>
			</select><br/>
			<br/>
			<input class="btn btn-success" style="color: black;" type="submit" value="Assign Course"/>
		</form>
	</div>
	</c:if>
	
	</div>	
</div>
<script src="../js/jquery-3.1.0.min.js"></script>
<script src="../js/bootstrap.min.js"></script>

</body>
</html>
