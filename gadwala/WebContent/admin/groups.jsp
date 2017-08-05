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
		ArrayList<Group> groups = (ArrayList<Group>)session.getAttribute("groups");
 	%>
	<div class="container" style="position: absolute;">
 		<h2 style="color: green">Level <%= request.getParameter("level") %> Groups</h2><br/>
 		<% if(session.getAttribute("errMSG") != null) {
        		out.println("<div class=\"alert alert-danger\"><strong style=\"color: red; text-align: center;\">" + session.getAttribute("errMSG") +"</strong></div>");
        		session.removeAttribute("errMSG");
        	}
        %>
 		<table class="table" style="color: white; float: right;">
 			<thead>
 				<tr style="font-size: 1.2em; font-weight: bold; text-align: center;">
 					<td>Level</td>
 					<td>Group name</td>
 					<td>Courses Assigned</td>
 					<td>Students</td>
 					<td>Slot</td>
 					<td>Location</td>
 					<td>Assign Courses</td>
 				</tr>
 			</thead>
 			<tbody>
 				<tr>
 					<td><%= request.getParameter("level") %></td>
 					<td>GA</td>
 					<td>
 						<% 
 							ArrayList<String> ga = (ArrayList<String>) session.getAttribute("ga");
 							int gan = 0;
 							if(ga != null) {
 								gan = 1;
 							}
 							if(gan == 0) {
 								out.print("<p style=\"color: red; font-weight: bold;\">No Courses Assigned Yet.</p>");
 							} else {
 								for(String as : ga) {
 									out.print("<p>"+ as + "</p>");
 								}
 							}
 						%>
 					</td>
 					<td></td>
 					<td></td>
 					<td></td>
 					<td>
 						<a class="btn btn-success" href="<%= request.getContextPath() + "/AssignCourse?name=GA&level="+ request.getParameter("level")%>">
 							<span class="glyphicon glyphicon-plus"></span> Assign Courses
 						</a>
 					</td>
 				</tr>
 				<tr>
 					<td><%= request.getParameter("level") %></td>
 					<td>GB</td>
 					<td>
 						<% 
 							ArrayList<String> gb = (ArrayList<String>) session.getAttribute("gb");
 							int gbn = 0;
 							if(gb != null) {
 								gbn = 1;
 							}
 							if(gbn == 0) {
 								out.print("<p style=\"color: red; font-weight: bold;\">No Courses Assigned Yet.</p>");
 							} else {
 								for(String as : gb) {
 									out.print("<p>"+ as + "</p>");
 								}
 							}
 						%>
 					</td>
 					<td></td>
 					<td></td>
 					<td></td>
 					<td>
 						<a class="btn btn-success" href="<%= request.getContextPath() + "/AssignCourse?name=GB&level="+ request.getParameter("level")%>">
 							<span class="glyphicon glyphicon-plus"></span> Assign Courses
 						</a>
 					</td>
 				</tr>
 				<tr>
 					<td><%= request.getParameter("level") %></td>
 					<td>GC</td>
 					<td>
 						<% 
 							ArrayList<String> gc = (ArrayList<String>) session.getAttribute("gc");
 							int gcn = 0;
 							if(gc != null) {
 								gcn = 1;
 							}
 							if(gcn == 0) {
 								out.print("<p style=\"color: red; font-weight: bold;\">No Courses Assigned Yet.</p>");
 							} else {
 								for(String as : gc) {
 									out.print("<p>"+ as + "</p>");
 								}
 							}
 						%>
 					</td>
 					<td></td>
 					<td></td>
 					<td></td>
 					<td>
 						<a class="btn btn-success" href="<%= request.getContextPath() + "/AssignCourse?name=GC&level="+ request.getParameter("level")%>">
 							<span class="glyphicon glyphicon-plus"></span> Assign Courses
 						</a>
 					</td>
 				</tr>
 				<tr>
 					<td><%= request.getParameter("level") %></td>
 					<td>GD</td>
 					<td>
 						<% 
 							ArrayList<String> gd = (ArrayList<String>) session.getAttribute("gd");
 							int gdn = 0;
 							if(gd != null) {
 								gdn = 1;
 							}
 							if(gdn == 0) {
 								out.print("<p style=\"color: red; font-weight: bold;\">No Courses Assigned Yet.</p>");
 							} else {
 								for(String as : gd) {
 									out.print("<p>"+ as + "</p>");
 								}
 							}
 						%>
 					</td>
 					<td></td>
 					<td></td>
 					<td></td>
 					<td>
 						<a class="btn btn-success" href="<%= request.getContextPath() + "/AssignCourse?name=GD&level="+ request.getParameter("level")%>">
 							<span class="glyphicon glyphicon-plus"></span> Assign Courses
 						</a>
 					</td>
 				</tr>
 			</tbody>
 		</table><br/>
	</div>	
</div>
<script src="../js/jquery-3.1.0.min.js"></script>
<script src="../js/bootstrap.min.js"></script>

</body>
</html>
