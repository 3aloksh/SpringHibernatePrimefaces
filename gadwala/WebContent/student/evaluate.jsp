<%@page import="java.util.Scanner"%>
<%@page import="com.gadwala.service.CourseService"%>
<%@page import="com.gadwala.dao.CourseDao"%>
<%@page import="com.gadwala.model.Question"%>
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
<title>Evaluate Courses</title>

<script type="text/javascript">
	var showQuestions = function (name) {
		var div = document.getElementById("questions");
		var e = document.getElementById("course");
		var courseId = e.options[e.selectedIndex].value;
		if(courseId > 0) {
			window.location.href = 'http://localhost:8080/gadwala/ShowQuestion?courseId=' + courseId;
		}
	}
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
      <a class="navbar-brand" href="#">Gadwala</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
      
        <li><a href="<%= request.getContextPath() + "/student/home.jsp" %>">Home</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
     
        <li><a href="<%= request.getContextPath() + "/Logout" %>">Log out</a></li>
       
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<% 
	ArrayList<Course> courses = (ArrayList<Course>) session.getAttribute("courses");
    ArrayList<Question> questions = (ArrayList<Question>) session.getAttribute("questions");
    CourseDao dao = new CourseService();
%>
<div class="container" style="position: relative; color: white">
<h3>Available Courses to evaluate are: </h3>
	<label>Choose Course</label>
			<select name="course" id="course" required style="color: black;" onchange="showQuestions(this)">
				<option value="0">Select Course</option>
				<c:forEach items="${courses}" var="course">
					<option value='<c:out value="${course.getId()}"></c:out>'><c:out value="${course.getName()}"></c:out></option>
				</c:forEach>
			</select><br/>
</div>
<c:if test="${questions ne null}">
	<div style="position: relative; color: white; background-color: gray;" id="questions">
		<h2 style="color: maroon;">Course: <%= dao.getCourseById(questions.get(0).getCourseid()).getName() %></h2>
		<div class="row" style="margin: 5px; padding: 5px;">
			<form method="post" action="<%= request.getContextPath() + "/EvaluationAnswers?courseId=" + questions.get(0).getCourseid()%>">
			<%
				int counter = 0;
			%>
			<c:forEach items="${questions}" var="q">
				<input type="hidden" name='<c:out value="questionID${counter}"></c:out>' value='<c:out value="${q.getId()}"></c:out>'/>
				<p style="font-weight: 1.5em;"><c:out value="${q.getQuestion()}"></c:out></p>
				<c:set var="answers"  value="${q.getAnswers()}"/>
				<%
					String answers = (String) pageContext.getAttribute("answers");
					Scanner s = new Scanner(answers);
					s.useDelimiter(",");
					ArrayList<String> ans = new ArrayList<>();
					while(s.hasNext()) {
						ans.add(s.next().trim());
					}
					pageContext.setAttribute("ans", ans);
					counter ++;
					pageContext.setAttribute("counter", counter);
				
					s.close();
				%>
					<c:forEach items="${ans}" var="a">
						<input style="margin-left: 10px;" required type="radio" name='<c:out value="answers${counter}"></c:out>' value='<c:out value="${a}"></c:out>' /> <c:out value="${a}" /> 
					</c:forEach>
				<hr/>
			</c:forEach>
			<div class="form-group">
		  		<label for="comment">Notes:</label>
		  		<textarea required class="form-control" style="width: 50%; margin: 0 auto;"  name="notes" rows="5" id="notes"></textarea>
			</div>
			<input type="submit" value="Submit" class="btn btn-success btn-md" />
			</form>
		</div>
	</div>
</c:if>
<c:if test="${questions == null}">
	<h2 id="error" style="position: relative; color: red;">Questions for this course will be available at the end of this semester.</h2>
</c:if>

<script src="../js/jquery-3.1.0.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
</body>
</html>
