<%@page import="com.gadwala.model.User"%>
<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	var showLevel = function () {
		var div = document.getElementById("level");
		var type = document.getElementById("type").value;
		console.log(type);
		if(type == "student") {			
			div.innerHTML = '<input type="number" name="level" min="1" max="4" placeholder="Level" required/>';
		} else {
			div.innerHTML = "";
		}
	}
</script>
<title>Edit user</title>

<link rel="stylesheet" href="../css/bootstrap.min.css"/>
<link rel="stylesheet" href="../css/admin_sign up.css"/>
</head>

<body>
<% User user = (User) session.getAttribute("user"); %>

<div class="layer"><div class="layer2"><h2 class="welc">Edit existing user</h2></div>
</div>
<form action="<%=request.getContextPath() + "/EditUser?id="+user.getId()%>" method="post">
<h2>Edit user</h2>
<a href="<%= request.getContextPath() + "/Users" %>" class="btn btn-danger btn-md">
   <span class="glyphicon glyphicon-chevron-left"></span> back
</a>
<span style="color: red; font-weight: red;"><% if(session.getAttribute("errorMessage") != null) { out.print(session.getAttribute("errorMessage")); session.invalidate(); } %></span>
 <input type="text" name="firstname" value="<%= user.getFirstname() %>" placeholder="First Name" required/>
 <input type="text" name="lastname" value="<%= user.getLastname() %>" placeholder="Last Name" required/>
 <input type="email" name="email" value="<%= user.getEmail() %>" placeholder="E-mail" required/>
 <input type="password" name="password" value="<%= user.getPassword() %>" placeholder="Password" required/>
 <input type="text" name="phone" value="<%= user.getPhone() %>" placeholder="Phone" required/><br/>
<label> Chooses user type:</label>
<select id="type"  class="slct" name="type" onchange="showLevel();">
<% 
	String type = null;
	type = user.getType();
	out.print(type);
%>
  <option value="admin" <%if(type.equals("admin")) out.print("selected=\"selected\"");  %> >admin</option>
  <option value="student" <%if(type.equals("student")) out.print("selected=\"selected\""); %>>trainee</option>
  <option value="manager" <%if(type.equals("manager")) out.print("selected=\"selected\""); %>>manager</option>
  <option value="instructor" <%if(type.equals("instructor")) out.print("selected=\"selected\""); %>>instructor</option>
</select>
<div id="level"></div>
<button type="submit">Edit User</button>
 </form>
 
 

<script src="../js/jquery-3.1.0.min.js"></script>
<script src="../js/bootstrap.min.js"></script>

</body>
</html>
		