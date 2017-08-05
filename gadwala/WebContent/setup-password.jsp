<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="css/adminlogin.css" >
<title>Setup new password</title>
</head>

<body>
	<div class="layer">
		<div class="layer2">
			<h2 class="welc">Set up new password</h2>
		</div>
	</div>
	<form action="<%=request.getContextPath() + "/SetupPassword"%>" method="post">
		<h2>Setting up new password</h2>
		<span style="color: red; font-weight: red;"><% if(session.getAttribute("errorMessage") != null) { out.print(session.getAttribute("errorMessage")); session.invalidate(); } %></span>
		<input type="password" name="password" placeholder="Write down your new password" required />
		<input type="password" name="confpassword" placeholder="Confirm password" required /><br/>
		<input type="hidden" name="email" value="<%= request.getParameter("email") %>" />
		<button type="submit">Submit</button>
	</form>
	<script src="../js/jquery-3.1.0.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
</body>
</html>