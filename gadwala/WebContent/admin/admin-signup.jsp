<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Administrator sign up</title>

<link rel="stylesheet" href="../css/bootstrap.min.css"/>
<link rel="stylesheet" href="../css/admin_sign up.css"/>
</head>

<body>

<div class="layer"><div class="layer2"><h2 class="welc"> Administrator Sign up</h2></div>
</div>
<form action="<%= request.getContextPath() + "/SignUp" %>" method="post">
<h2>Create new account</h2>
 <input type="text" name="firstname" placeholder="First Name" required/>
 <input type="text" name="lastname" placeholder="Last Name" required/>
 <input type="email" name="email" placeholder="E-mail" required/>
 <input type="password" name="password" placeholder="Password" required/>
 <input type="text" name="phone" placeholder="Phone" required/>
<button type="submit">Sign up</button>
<button type="reset" class="cancle">Cancel</button>

 </form>
 
 

<script src="../js/jquery-3.1.0.min.js"></script>
<script src="../js/bootstrap.min.js"></script>

</body>
</html>
