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
       <li><a href="<%= request.getContextPath() + "/manager/home.jsp" %>">Home</a></li>
       <li><a href="<%= request.getContextPath() + "/Logout" %>">Logout</a></li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<h2 style="position:relative; color: white; margin-left: -70px;">Reports</h2>
<img alt="users" src="../images/reports.png" style="position:relative;">
<div style="position: absolute;">
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
        <th>Total Money</th>
      </tr>
    </thead>
    <tbody>
     <c:forEach items="${courses}" var="c">
    	<tr class='clickable-row'>
    		<td><c:out value="${c.id}"/></td>
    		<td><c:out value="${c.name}"/></td>
    		<td><c:out value="${c.capacity}"/></td>
    		<td><c:out value="${c.fees}"/></td>
    		<td><c:out value="${c.numberOfStudents}"/></td>
    		<td><c:out value="${c.numberOfSessions}"/></td>
    		<td><c:out value="${c.numberOfStudents * c.fees} L.E"/></td>
    	</tr>
     </c:forEach>
    </tbody>
  </table>
		</c:when>
	</c:choose>
</div>
<script src="../js/jquery-3.1.0.min.js"></script>
<script src="../js/bootstrap.min.js"></script>

</body>
</html>
