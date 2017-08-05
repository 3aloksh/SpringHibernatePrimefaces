<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="css/adminlogin.css" >
<title>Administrator Login Page</title>
</head>

<body>
	<div class="layer">
		<div class="layer2">
			<h2 class="welc">Get a new password</h2>
		</div>
	</div>
	<form action="<%=request.getContextPath() + "/ForgotPassword"%>" method="post">
		<h2>Get new Password</h2>
		<span style="color: red; font-weight: red;"><% if(session.getAttribute("errorMessage") != null) { out.print(session.getAttribute("errorMessage")); session.invalidate(); } %></span>
		<input type="email" name="email" placeholder="Write down your email..." required />
		<button type="submit">Submit</button><br/>
		<a href="/gadwala/index.html" class="back" style="margin-top: 50px; position: relative; width: 80px; ">Back</a>
	</form>
	<script src="../js/jquery-3.1.0.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
</body>
</html>