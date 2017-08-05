<%@page import="com.gadwala.model.User"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Add Course</title>
<link rel="stylesheet" href="../css/bootstrap.min.css"/>
<link rel="stylesheet" href="../css/addCourse.css"/>
</head>

<body>
<div class="layer"><div class="layer2"></div>
</div>
<form action="<%= request.getContextPath() + "/AddCourse" %>" method="post">
<h2>Add new Course</h2>
<a href="<%= request.getContextPath() + "/Courses" %>" class="btn btn-danger btn-md">
          <span class="glyphicon glyphicon-chevron-left"></span>back
</a>
<% 
	ArrayList<User> insts = (ArrayList<User>) session.getAttribute("insts"); 
	
	if(insts == null) {
		session.setAttribute("deleteMSG", "Please add Instructors First and then add courses");
		response.sendRedirect(request.getContextPath() + "/admin/courses.jsp");
	}
%>
<span style="color: red; font-weight: bold;"><% if(session.getAttribute("errorMessage") != null) { out.print(session.getAttribute("errorMessage")); session.invalidate(); } %></span>
 <input type="text" name="name" placeholder="Course name" required/>

 <input type="number" name="capacity" placeholder="Course capacity" required/>
 <span>Start date</span><input type="date" name="startdate" placeholder="Start date" required/>
 <span>End date</span><input type="date" name="enddate" placeholder="End date" required/>
 <input type="number" name="fees" placeholder="Course fees" required/>
<input type="number" name="mubmerofstudents" max="40" placeholder="Minimum Number of students" required/>
<input type="number" name="numberofsessions" placeholder="Number of sessions" required/>
<input type="number" min="1" max="4" name="level" placeholder="Course Level" required/><br/>
<label style="margin-left: 100px;">Instructor</label>
<select name="instractor" required style="color: black; margin-left: 100px;">
				<c:forEach items="${insts}" var="i">
					<option value='<c:out value="${i.getId()}"/>'><c:out value="${i.getFirstname()} "/><c:out value="${i.getLastname()}"/></option>
				</c:forEach>
			</select><br/>
<span>Course type</span>
 <p>
   <label>
     <input type="radio" name="ctype" value="Soft Skills" id="Coursetype_0">
     Soft Skils</label>
  
   <label>
     <input type="radio" name="ctype" value="Technical Skills" id="Coursetype_1">
     Technical Skills</label>
   
 </p>
 <span>Room type</span> 
 <p>
   <label>
     <input type="radio" name="rtype" value="lab" id="Roomtype_0">
     Lab</label>
  
   <label>
     <input type="radio" name="rtype" value="hall" id="Roomtype_1">
     Hall</label>
   
 </p> 
<button type="submit" class="add">Add course</button>
<button type="reset" class="cancle">Cancle</button>

 

 </form>

  

<script src="../js/jquery-3.1.0.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
</body>
</html>
