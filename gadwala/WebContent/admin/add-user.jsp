<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	var showLevel = function (name) {
		var div = document.getElementById("level");
		var type = document.getElementById("type").value;
		var addBtn = document.getElementById("add");
		addBtn.innerHTML = "Add " + type;
		if(type == "student") {			
			div.innerHTML = '<input type="number" name="level" min="1" max="4" placeholder="Level" required/>';
		} else {
			div.innerHTML = "";
		}
	}
</script>
<title>User creation</title>

<link rel="stylesheet" href="../css/bootstrap.min.css"/>
<link rel="stylesheet" href="../css/admin_sign up.css"/>
</head>

<body>

<div class="layer"><div class="layer2"><h2 class="welc">Create new user</h2></div>
</div>
<form action="<%=request.getContextPath() + "/AddUser"%>" method="post">
<h2>Create new account</h2>
<span style="color: red; font-weight: red;"><% if(session.getAttribute("errorMessage") != null) { out.print(session.getAttribute("errorMessage")); session.invalidate(); } %></span>
 <input type="text" name="firstname" placeholder="First Name" required/>
 <input type="text" name="lastname" placeholder="Last Name" required/>
 <input type="email" name="email" placeholder="E-mail" required/>
 <input type="password" name="password" placeholder="Password" required/>
 <input type="text" name="phone" placeholder="Phone" required/><br/>
<label> Chooses user type:</label>
<select id="type"  class="slct" name="type" onchange="showLevel(this);">
  <option value="admin">admin</option>
  <option value="student">trainee</option>
  <option value="manager">manager</option>
  <option value="instructor">instructor</option>
</select>
<div id="level"></div>
<button type="submit" id="add" >Add User</button>
 </form>
 
 

<script src="../js/jquery-3.1.0.min.js"></script>
<script src="../js/bootstrap.min.js"></script>

</body>
</html>
		