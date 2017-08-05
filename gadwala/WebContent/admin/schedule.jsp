<%@page import="com.gadwala.dao.CourseDao"%>
<%@page import="com.gadwala.service.ScheduleService"%>
<%@page import="com.gadwala.dao.ScheduleDao"%>
<%@page import="com.gadwala.model.Schedule"%>
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
	.table {
		font-weight: bold; 
		cursor: pointer;
		background-color: white;
		color: black;
	}
	td {
		width: 200px; height: 200px; 
		border-right: 2px solid black;
		border-bottom: 2px solid black;
	}
	tr > td:FIRST-CHILD {
		color: maroon; font-size: 1.8em;
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
<h2 style="position:relative; color: white;">Gadwala Schedules</h2>
<br/>
<div class="container-fluid" style="position: absolute;">
	<% if(session.getAttribute("errMSG") != null) {
        		out.println("<div class=\"alert alert-danger\"><strong style=\"color: red; text-align: center;\">" + session.getAttribute("errMSG") +"</strong></div>");
        		session.removeAttribute("errMSG");
        	}
       %>
     <%
     	ArrayList<Schedule> schdules = (ArrayList<Schedule>) session.getAttribute("schedules");
     %>  
	<table class="table" style="position: relative; text-align: center; font-weight: bold;" >
		<thead style="font-size: 1.4em;">
			<tr>
				<td align="center" style="height: 100px; border-top: 2px solid black; color: black; font-size: 1.4em;">Day</td>
				<td align="center" style="height: 100px; border-top: 2px solid black; color: maroon;">Slot 1</td>
				<td align="center" style="height: 100px; border-top: 2px solid black; color: maroon;">Slot 2</td>
				<td align="center" style="height: 100px; border-top: 2px solid black; color: maroon;">Slot 3</td>
				<td align="center" style="height: 100px; border-top: 2px solid black; color: maroon;">Slot 4</td>
				<td align="center" style="height: 100px; border-top: 2px solid black; color: maroon;">Slot 5</td>
			</tr>
		</thead>
		<tbody style="font-size: 1.0em;">
			<%
				ScheduleDao dao = new ScheduleService();
				ArrayList<Schedule> sat = (ArrayList<Schedule>) session.getAttribute("sat");  
			%>
			<tr>
				<td align="center" style="padding-top:100px;width: 200px; height: 200px; border-right: 2px solid black;">Saturday</td>
				<td width="200px; height: 200px;">
					<c:forEach items="${sat}" var="d">
						<c:if test="${d.slot == 'slot 1'}">
							<c:if test="${d.getLevel() eq '1'}"><p style="color: #FF4136"></c:if>
							<c:if test="${d.getLevel() eq '2'}"><p style="color: #001f3f"></c:if>
							<c:if test="${d.getLevel() eq '3'}"><p style="color: #B10DC9"></c:if>
							<c:if test="${d.getLevel() eq '4'}"><p style="color: #3D9970"></c:if>
							Level <c:out value="${d.getLevel()}"/>/<c:out value="${d.getGroupname()}"/> - <c:out value="${d.getCoursename()}"/> : <c:out value="${d.getLocation()}"/> : <c:out value="${d.getInstructor()}"/></p><br/>
										
						</c:if>
					</c:forEach>
				</td>
				<td width="200px; height: 200px;">
					<c:forEach items="${sat}" var="d">
						<c:if test="${d.slot == 'slot 2'}">
							<c:if test="${d.getLevel() eq '1'}"><p style="color: #FF4136"></c:if>
							<c:if test="${d.getLevel() eq '2'}"><p style="color: #001f3f"></c:if>
							<c:if test="${d.getLevel() eq '3'}"><p style="color: #B10DC9"></c:if>
							<c:if test="${d.getLevel() eq '4'}"><p style="color: #3D9970"></c:if>
							Level <c:out value="${d.getLevel()}"/>/<c:out value="${d.getGroupname()}"/> - <c:out value="${d.getCoursename()}"/> : <c:out value="${d.getLocation()}"/> : <c:out value="${d.getInstructor()}"/></p><br/>
						</c:if>
					</c:forEach>
				</td>
				<td width="200px; height: 200px;">
					<c:forEach items="${sat}" var="d">
						<c:if test="${d.slot == 'slot 3'}">
							<c:if test="${d.getLevel() eq '1'}"><p style="color: #FF4136"></c:if>
							<c:if test="${d.getLevel() eq '2'}"><p style="color: #001f3f"></c:if>
							<c:if test="${d.getLevel() eq '3'}"><p style="color: #B10DC9"></c:if>
							<c:if test="${d.getLevel() eq '4'}"><p style="color: #3D9970"></c:if>
							Level <c:out value="${d.getLevel()}"/>/<c:out value="${d.getGroupname()}"/> - <c:out value="${d.getCoursename()}"/> : <c:out value="${d.getLocation()}"/> : <c:out value="${d.getInstructor()}"/></p><br/>
						</c:if>
					</c:forEach>
				</td>
				<td width="200px; height: 200px;">
					<c:forEach items="${sat}" var="d">
						<c:if test="${d.slot == 'slot 4'}">
							<c:if test="${d.getLevel() eq '1'}"><p style="color: #FF4136"></c:if>
							<c:if test="${d.getLevel() eq '2'}"><p style="color: #001f3f"></c:if>
							<c:if test="${d.getLevel() eq '3'}"><p style="color: #B10DC9"></c:if>
							<c:if test="${d.getLevel() eq '4'}"><p style="color: #3D9970"></c:if>
							Level <c:out value="${d.getLevel()}"/>/<c:out value="${d.getGroupname()}"/> - <c:out value="${d.getCoursename()}"/> : <c:out value="${d.getLocation()}"/> : <c:out value="${d.getInstructor()}"/></p><br/>
						</c:if>
					</c:forEach>
				</td>
				<td width="200px; height: 200px;">
					<c:forEach items="${sat}" var="d">
						<c:if test="${d.slot == 'slot 5'}">
							<c:if test="${d.getLevel() eq '1'}"><p style="color: #FF4136"></c:if>
							<c:if test="${d.getLevel() eq '2'}"><p style="color: #001f3f"></c:if>
							<c:if test="${d.getLevel() eq '3'}"><p style="color: #B10DC9"></c:if>
							<c:if test="${d.getLevel() eq '4'}"><p style="color: #3D9970"></c:if>
							Level <c:out value="${d.getLevel()}"/>/<c:out value="${d.getGroupname()}"/> - <c:out value="${d.getCoursename()}"/> : <c:out value="${d.getLocation()}"/> : <c:out value="${d.getInstructor()}"/></p><br/>
						</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<%
					ArrayList<Schedule> sun = (ArrayList<Schedule>) session.getAttribute("sun"); 
				%>
				<td width="200px; height: 200px;" style="padding-top:80px;color: maroon; font-size: 1.8em;">Sunday</td>
				<td width="200px; height: 200px;">
					<c:forEach items="${sun}" var="d">
						<c:if test="${d.slot == 'slot 1'}">
							<c:if test="${d.getLevel() eq '1'}"><p style="color: #FF4136"></c:if>
							<c:if test="${d.getLevel() eq '2'}"><p style="color: #001f3f"></c:if>
							<c:if test="${d.getLevel() eq '3'}"><p style="color: #B10DC9"></c:if>
							<c:if test="${d.getLevel() eq '4'}"><p style="color: #3D9970"></c:if>
							Level <c:out value="${d.getLevel()}"/>/<c:out value="${d.getGroupname()}"/> - <c:out value="${d.getCoursename()}"/> : <c:out value="${d.getLocation()}"/> : <c:out value="${d.getInstructor()}"/></p><br/>
						</c:if>
					</c:forEach>
				</td>
				<td width="200px; height: 200px;">
					<c:forEach items="${sun}" var="d">
						<c:if test="${d.slot == 'slot 2'}">
							<c:if test="${d.getLevel() eq '1'}"><p style="color: #FF4136"></c:if>
							<c:if test="${d.getLevel() eq '2'}"><p style="color: #001f3f"></c:if>
							<c:if test="${d.getLevel() eq '3'}"><p style="color: #B10DC9"></c:if>
							<c:if test="${d.getLevel() eq '4'}"><p style="color: #3D9970"></c:if>
							Level <c:out value="${d.getLevel()}"/>/<c:out value="${d.getGroupname()}"/> - <c:out value="${d.getCoursename()}"/> : <c:out value="${d.getLocation()}"/> : <c:out value="${d.getInstructor()}"/></p><br/>
						</c:if>
					</c:forEach>
				</td>
				<td width="200px; height: 200px;">
					<c:forEach items="${sun}" var="d">
						<c:if test="${d.slot == 'slot 3'}">
							<c:if test="${d.getLevel() eq '1'}"><p style="color: #FF4136"></c:if>
							<c:if test="${d.getLevel() eq '2'}"><p style="color: #001f3f"></c:if>
							<c:if test="${d.getLevel() eq '3'}"><p style="color: #B10DC9"></c:if>
							<c:if test="${d.getLevel() eq '4'}"><p style="color: #3D9970"></c:if>
							Level <c:out value="${d.getLevel()}"/>/<c:out value="${d.getGroupname()}"/> - <c:out value="${d.getCoursename()}"/> : <c:out value="${d.getLocation()}"/> : <c:out value="${d.getInstructor()}"/></p><br/>
						</c:if>
					</c:forEach>
				</td>
				<td width="200px; height: 200px;">
					<c:forEach items="${sun}" var="d">
						<c:if test="${d.slot == 'slot 4'}">
							<c:if test="${d.getLevel() eq '1'}"><p style="color: #FF4136"></c:if>
							<c:if test="${d.getLevel() eq '2'}"><p style="color: #001f3f"></c:if>
							<c:if test="${d.getLevel() eq '3'}"><p style="color: #B10DC9"></c:if>
							<c:if test="${d.getLevel() eq '4'}"><p style="color: #3D9970"></c:if>
							Level <c:out value="${d.getLevel()}"/>/<c:out value="${d.getGroupname()}"/> - <c:out value="${d.getCoursename()}"/> : <c:out value="${d.getLocation()}"/> : <c:out value="${d.getInstructor()}"/></p><br/>
						</c:if>
					</c:forEach>
				</td>
				<td width="200px; height: 200px;">
					<c:forEach items="${sun}" var="d">
						<c:if test="${d.slot == 'slot 5'}">
							<c:if test="${d.getLevel() eq '1'}"><p style="color: #FF4136"></c:if>
							<c:if test="${d.getLevel() eq '2'}"><p style="color: #001f3f"></c:if>
							<c:if test="${d.getLevel() eq '3'}"><p style="color: #B10DC9"></c:if>
							<c:if test="${d.getLevel() eq '4'}"><p style="color: #3D9970"></c:if>
							Level <c:out value="${d.getLevel()}"/>/<c:out value="${d.getGroupname()}"/> - <c:out value="${d.getCoursename()}"/> : <c:out value="${d.getLocation()}"/> : <c:out value="${d.getInstructor()}"/></p><br/>
						</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<%
					ArrayList<Schedule> mon = (ArrayList<Schedule>) session.getAttribute("mon");   
				%>
				<td width="200px; height: 200px;" style="padding-top:80px;">Monday</td>
					<td width="200px; height: 200px;">
					<c:forEach items="${mon}" var="d">
						<c:if test="${d.slot == 'slot 1'}">
							<c:if test="${d.getLevel() eq '1'}"><p style="color: #FF4136"></c:if>
							<c:if test="${d.getLevel() eq '2'}"><p style="color: #001f3f"></c:if>
							<c:if test="${d.getLevel() eq '3'}"><p style="color: #B10DC9"></c:if>
							<c:if test="${d.getLevel() eq '4'}"><p style="color: #3D9970"></c:if>
							Level <c:out value="${d.getLevel()}"/>/<c:out value="${d.getGroupname()}"/> - <c:out value="${d.getCoursename()}"/> : <c:out value="${d.getLocation()}"/> : <c:out value="${d.getInstructor()}"/></p><br/>
						</c:if>
					</c:forEach>
				</td>
				<td width="200px; height: 200px;">
					<c:forEach items="${mon}" var="d">
						<c:if test="${d.slot == 'slot 2'}">
							<c:if test="${d.getLevel() eq '1'}"><p style="color: #FF4136"></c:if>
							<c:if test="${d.getLevel() eq '2'}"><p style="color: #001f3f"></c:if>
							<c:if test="${d.getLevel() eq '3'}"><p style="color: #B10DC9"></c:if>
							<c:if test="${d.getLevel() eq '4'}"><p style="color: #3D9970"></c:if>
							Level <c:out value="${d.getLevel()}"/>/<c:out value="${d.getGroupname()}"/> - <c:out value="${d.getCoursename()}"/> : <c:out value="${d.getLocation()}"/> : <c:out value="${d.getInstructor()}"/></p><br/>
						</c:if>
					</c:forEach>
				</td>
				<td width="200px; height: 200px;">
					<c:forEach items="${mon}" var="d">
						<c:if test="${d.slot == 'slot 3'}">
							<c:if test="${d.getLevel() eq '1'}"><p style="color: #FF4136"></c:if>
							<c:if test="${d.getLevel() eq '2'}"><p style="color: #001f3f"></c:if>
							<c:if test="${d.getLevel() eq '3'}"><p style="color: #B10DC9"></c:if>
							<c:if test="${d.getLevel() eq '4'}"><p style="color: #3D9970"></c:if>
							Level <c:out value="${d.getLevel()}"/>/<c:out value="${d.getGroupname()}"/> - <c:out value="${d.getCoursename()}"/> : <c:out value="${d.getLocation()}"/> : <c:out value="${d.getInstructor()}"/></p><br/>
						</c:if>
					</c:forEach>
				</td>
				<td width="200px; height: 200px;">
					<c:forEach items="${mon}" var="d">
						<c:if test="${d.slot == 'slot 4'}">
							<c:if test="${d.getLevel() eq '1'}"><p style="color: #FF4136"></c:if>
							<c:if test="${d.getLevel() eq '2'}"><p style="color: #001f3f"></c:if>
							<c:if test="${d.getLevel() eq '3'}"><p style="color: #B10DC9"></c:if>
							<c:if test="${d.getLevel() eq '4'}"><p style="color: #3D9970"></c:if>
							Level <c:out value="${d.getLevel()}"/>/<c:out value="${d.getGroupname()}"/> - <c:out value="${d.getCoursename()}"/> : <c:out value="${d.getLocation()}"/> : <c:out value="${d.getInstructor()}"/></p><br/>
						</c:if>
					</c:forEach>
				</td>
				<td width="200px; height: 200px;">
					<c:forEach items="${mon}" var="d">
						<c:if test="${d.slot == 'slot 5'}">
							<c:if test="${d.getLevel() eq '1'}"><p style="color: #FF4136"></c:if>
							<c:if test="${d.getLevel() eq '2'}"><p style="color: #001f3f"></c:if>
							<c:if test="${d.getLevel() eq '3'}"><p style="color: #B10DC9"></c:if>
							<c:if test="${d.getLevel() eq '4'}"><p style="color: #3D9970"></c:if>
							Level <c:out value="${d.getLevel()}"/>/<c:out value="${d.getGroupname()}"/> - <c:out value="${d.getCoursename()}"/> : <c:out value="${d.getLocation()}"/> : <c:out value="${d.getInstructor()}"/></p><br/>
						</c:if>
					</c:forEach>
				</td>
				
			</tr>
			<tr>
				<%
					ArrayList<Schedule> tue = (ArrayList<Schedule>) session.getAttribute("tue");   
				%>
				<td width="200px; height: 200px;" style="padding-top:80px;">Tuesday</td>
					<td width="200px; height: 200px;">
					<c:forEach items="${tue}" var="d">
						<c:if test="${d.slot == 'slot 1'}">
							<c:if test="${d.getLevel() eq '1'}"><p style="color: #FF4136"></c:if>
							<c:if test="${d.getLevel() eq '2'}"><p style="color: #001f3f"></c:if>
							<c:if test="${d.getLevel() eq '3'}"><p style="color: #B10DC9"></c:if>
							<c:if test="${d.getLevel() eq '4'}"><p style="color: #3D9970"></c:if>
							Level <c:out value="${d.getLevel()}"/>/<c:out value="${d.getGroupname()}"/> - <c:out value="${d.getCoursename()}"/> : <c:out value="${d.getLocation()}"/> : <c:out value="${d.getInstructor()}"/></p><br/>
						</c:if>
					</c:forEach>
				</td>
				<td width="200px; height: 200px;">
					<c:forEach items="${tue}" var="d">
						<c:if test="${d.slot == 'slot 2'}">
							<c:if test="${d.getLevel() eq '1'}"><p style="color: #FF4136"></c:if>
							<c:if test="${d.getLevel() eq '2'}"><p style="color: #001f3f"></c:if>
							<c:if test="${d.getLevel() eq '3'}"><p style="color: #B10DC9"></c:if>
							<c:if test="${d.getLevel() eq '4'}"><p style="color: #3D9970"></c:if>
							Level <c:out value="${d.getLevel()}"/>/<c:out value="${d.getGroupname()}"/> - <c:out value="${d.getCoursename()}"/> : <c:out value="${d.getLocation()}"/> : <c:out value="${d.getInstructor()}"/></p><br/>
						</c:if>
					</c:forEach>
				</td>
				<td width="200px; height: 200px;">
					<c:forEach items="${tue}" var="d">
						<c:if test="${d.slot == 'slot 3'}">
							<c:if test="${d.getLevel() eq '1'}"><p style="color: #FF4136"></c:if>
							<c:if test="${d.getLevel() eq '2'}"><p style="color: #001f3f"></c:if>
							<c:if test="${d.getLevel() eq '3'}"><p style="color: #B10DC9"></c:if>
							<c:if test="${d.getLevel() eq '4'}"><p style="color: #3D9970"></c:if>
							Level <c:out value="${d.getLevel()}"/>/<c:out value="${d.getGroupname()}"/> - <c:out value="${d.getCoursename()}"/> : <c:out value="${d.getLocation()}"/> : <c:out value="${d.getInstructor()}"/></p><br/>
						</c:if>
					</c:forEach>
				</td>
				<td width="200px; height: 200px;">
					<c:forEach items="${tue}" var="d">
						<c:if test="${d.slot == 'slot 4'}">
							<c:if test="${d.getLevel() eq '1'}"><p style="color: #FF4136"></c:if>
							<c:if test="${d.getLevel() eq '2'}"><p style="color: #001f3f"></c:if>
							<c:if test="${d.getLevel() eq '3'}"><p style="color: #B10DC9"></c:if>
							<c:if test="${d.getLevel() eq '4'}"><p style="color: #3D9970"></c:if>
							Level <c:out value="${d.getLevel()}"/>/<c:out value="${d.getGroupname()}"/> - <c:out value="${d.getCoursename()}"/> : <c:out value="${d.getLocation()}"/> : <c:out value="${d.getInstructor()}"/></p><br/>
						</c:if>
					</c:forEach>
				</td>
				<td width="200px; height: 200px;">
					<c:forEach items="${tue}" var="d">
						<c:if test="${d.slot == 'slot 5'}">
							<c:if test="${d.getLevel() eq '1'}"><p style="color: #FF4136"></c:if>
							<c:if test="${d.getLevel() eq '2'}"><p style="color: #001f3f"></c:if>
							<c:if test="${d.getLevel() eq '3'}"><p style="color: #B10DC9"></c:if>
							<c:if test="${d.getLevel() eq '4'}"><p style="color: #3D9970"></c:if>
							Level <c:out value="${d.getLevel()}"/>/<c:out value="${d.getGroupname()}"/> - <c:out value="${d.getCoursename()}"/> : <c:out value="${d.getLocation()}"/> : <c:out value="${d.getInstructor()}"/></p><br/>
						</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<%
					ArrayList<Schedule> wed = (ArrayList<Schedule>) session.getAttribute("wed");   
				%>
				<td width="200px; height: 200px;" style="padding-top:80px;">Wednesday</td>
					<td width="200px; height: 200px;">
					<c:forEach items="${wed}" var="d">
						<c:if test="${d.slot == 'slot 1'}">
							<c:if test="${d.getLevel() eq '1'}"><p style="color: #FF4136"></c:if>
							<c:if test="${d.getLevel() eq '2'}"><p style="color: #001f3f"></c:if>
							<c:if test="${d.getLevel() eq '3'}"><p style="color: #B10DC9"></c:if>
							<c:if test="${d.getLevel() eq '4'}"><p style="color: #3D9970"></c:if>
							Level <c:out value="${d.getLevel()}"/>/<c:out value="${d.getGroupname()}"/> - <c:out value="${d.getCoursename()}"/> : <c:out value="${d.getLocation()}"/> : <c:out value="${d.getInstructor()}"/></p><br/>
						</c:if>
					</c:forEach>
				</td>
				<td width="200px; height: 200px;">
					<c:forEach items="${wed}" var="d">
						<c:if test="${d.slot == 'slot 2'}">
							<c:if test="${d.getLevel() eq '1'}"><p style="color: #FF4136"></c:if>
							<c:if test="${d.getLevel() eq '2'}"><p style="color: #001f3f"></c:if>
							<c:if test="${d.getLevel() eq '3'}"><p style="color: #B10DC9"></c:if>
							<c:if test="${d.getLevel() eq '4'}"><p style="color: #3D9970"></c:if>
							Level <c:out value="${d.getLevel()}"/>/<c:out value="${d.getGroupname()}"/> - <c:out value="${d.getCoursename()}"/> : <c:out value="${d.getLocation()}"/> : <c:out value="${d.getInstructor()}"/></p><br/>
						</c:if>
					</c:forEach>
				</td>
				<td width="200px; height: 200px;">
					<c:forEach items="${wed}" var="d">
						<c:if test="${d.slot == 'slot 3'}">
							<c:if test="${d.getLevel() eq '1'}"><p style="color: #FF4136"></c:if>
							<c:if test="${d.getLevel() eq '2'}"><p style="color: #001f3f"></c:if>
							<c:if test="${d.getLevel() eq '3'}"><p style="color: #B10DC9"></c:if>
							<c:if test="${d.getLevel() eq '4'}"><p style="color: #3D9970"></c:if>
							Level <c:out value="${d.getLevel()}"/>/<c:out value="${d.getGroupname()}"/> - <c:out value="${d.getCoursename()}"/> : <c:out value="${d.getLocation()}"/> : <c:out value="${d.getInstructor()}"/></p><br/>
						</c:if>
					</c:forEach>
				</td>
				<td width="200px; height: 200px;">
					<c:forEach items="${wed}" var="d">
						<c:if test="${d.slot == 'slot 4'}">
							<c:if test="${d.getLevel() eq '1'}"><p style="color: #FF4136"></c:if>
							<c:if test="${d.getLevel() eq '2'}"><p style="color: #001f3f"></c:if>
							<c:if test="${d.getLevel() eq '3'}"><p style="color: #B10DC9"></c:if>
							<c:if test="${d.getLevel() eq '4'}"><p style="color: #3D9970"></c:if>
							Level <c:out value="${d.getLevel()}"/>/<c:out value="${d.getGroupname()}"/> - <c:out value="${d.getCoursename()}"/> : <c:out value="${d.getLocation()}"/> : <c:out value="${d.getInstructor()}"/></p><br/>
						</c:if>
					</c:forEach>
				</td>
				<td width="200px; height: 200px;">
					<c:forEach items="${wed}" var="d">
						<c:if test="${d.slot == 'slot 5'}">
							<c:if test="${d.getLevel() eq '1'}"><p style="color: #FF4136"></c:if>
							<c:if test="${d.getLevel() eq '2'}"><p style="color: #001f3f"></c:if>
							<c:if test="${d.getLevel() eq '3'}"><p style="color: #B10DC9"></c:if>
							<c:if test="${d.getLevel() eq '4'}"><p style="color: #3D9970"></c:if>
							Level <c:out value="${d.getLevel()}"/>/<c:out value="${d.getGroupname()}"/> - <c:out value="${d.getCoursename()}"/> : <c:out value="${d.getLocation()}"/> : <c:out value="${d.getInstructor()}"/></p><br/>
						</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<%
					ArrayList<Schedule> thu = (ArrayList<Schedule>) session.getAttribute("thu");  
				%>
				<td width="200px; height: 200px;" style="padding-top:80px;">Thursday</td>
					<td width="200px; height: 200px;">
					<c:forEach items="${thu}" var="d">
						<c:if test="${d.slot == 'slot 1'}">
							<c:if test="${d.getLevel() eq '1'}"><p style="color: #FF4136"></c:if>
							<c:if test="${d.getLevel() eq '2'}"><p style="color: #001f3f"></c:if>
							<c:if test="${d.getLevel() eq '3'}"><p style="color: #B10DC9"></c:if>
							<c:if test="${d.getLevel() eq '4'}"><p style="color: #3D9970"></c:if>
							Level <c:out value="${d.getLevel()}"/>/<c:out value="${d.getGroupname()}"/> - <c:out value="${d.getCoursename()}"/> : <c:out value="${d.getLocation()}"/> : <c:out value="${d.getInstructor()}"/></p><br/>
						</c:if>
					</c:forEach>
				</td>
				<td width="200px; height: 200px;">
					<c:forEach items="${thu}" var="d">
						<c:if test="${d.slot == 'slot 2'}">
							<c:if test="${d.getLevel() eq '1'}"><p style="color: #FF4136"></c:if>
							<c:if test="${d.getLevel() eq '2'}"><p style="color: #001f3f"></c:if>
							<c:if test="${d.getLevel() eq '3'}"><p style="color: #B10DC9"></c:if>
							<c:if test="${d.getLevel() eq '4'}"><p style="color: #3D9970"></c:if>
							Level <c:out value="${d.getLevel()}"/>/<c:out value="${d.getGroupname()}"/> - <c:out value="${d.getCoursename()}"/> : <c:out value="${d.getLocation()}"/> : <c:out value="${d.getInstructor()}"/></p><br/>
						</c:if>
					</c:forEach>
				</td>
				<td width="200px; height: 200px;">
					<c:forEach items="${thu}" var="d">
						<c:if test="${d.slot == 'slot 3'}">
							<c:if test="${d.getLevel() eq '1'}"><p style="color: #FF4136"></c:if>
							<c:if test="${d.getLevel() eq '2'}"><p style="color: #001f3f"></c:if>
							<c:if test="${d.getLevel() eq '3'}"><p style="color: #B10DC9"></c:if>
							<c:if test="${d.getLevel() eq '4'}"><p style="color: #3D9970"></c:if>
							Level <c:out value="${d.getLevel()}"/>/<c:out value="${d.getGroupname()}"/> - <c:out value="${d.getCoursename()}"/> : <c:out value="${d.getLocation()}"/> : <c:out value="${d.getInstructor()}"/></p><br/>
						</c:if>
					</c:forEach>
				</td>
				<td width="200px; height: 200px;">
					<c:forEach items="${thu}" var="d">
						<c:if test="${d.slot == 'slot 4'}">
							<c:if test="${d.getLevel() eq '1'}"><p style="color: #FF4136"></c:if>
							<c:if test="${d.getLevel() eq '2'}"><p style="color: #001f3f"></c:if>
							<c:if test="${d.getLevel() eq '3'}"><p style="color: #B10DC9"></c:if>
							<c:if test="${d.getLevel() eq '4'}"><p style="color: #3D9970"></c:if>
							Level <c:out value="${d.getLevel()}"/>/<c:out value="${d.getGroupname()}"/> - <c:out value="${d.getCoursename()}"/> : <c:out value="${d.getLocation()}"/> : <c:out value="${d.getInstructor()}"/></p><br/>
						</c:if>
					</c:forEach>
				</td>
				<td width="200px; height: 200px;">
					<c:forEach items="${thu}" var="d">
						<c:if test="${d.slot == 'slot 5'}">
							<c:if test="${d.getLevel() eq '1'}"><p style="color: #FF4136"></c:if>
							<c:if test="${d.getLevel() eq '2'}"><p style="color: #001f3f"></c:if>
							<c:if test="${d.getLevel() eq '3'}"><p style="color: #B10DC9"></c:if>
							<c:if test="${d.getLevel() eq '4'}"><p style="color: #3D9970"></c:if>
							Level <c:out value="${d.getLevel()}"/>/<c:out value="${d.getGroupname()}"/> - <c:out value="${d.getCoursename()}"/> : <c:out value="${d.getLocation()}"/> : <c:out value="${d.getInstructor()}"/></p><br/>
						</c:if>
					</c:forEach>
				</td>
			</tr>
		</tbody>
	</table>
</div>
<script src="../js/jquery-3.1.0.min.js"></script>
<script src="../js/bootstrap.min.js"></script>

</body>
</html>
