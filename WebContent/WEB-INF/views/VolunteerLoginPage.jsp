<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Volunteer Login Page</title>
		<style>
		    .error {
				color: red;
		    }
		</style>
		<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js" integrity="sha384-6khuMg9gaYr5AxOqhkVIODVIvm9ynTT5J4V1cfthmT+emCG6yVmEZsRHdxlotUnm" crossorigin="anonymous"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
	</head>
	<body>
		<h1>Welcome to Travel Corps</h1>
		<form:form action="${pageContext.request.contextPath}/loginVolunteer" method="post" modelAttribute="user">
		<fieldset>
		    <legend>Volunteer Login Here</legend>
		    <p style="color:red;">${errorMessage}</p>
		    <div>
				<label for="userName">Username: </label>
				<div>
				    <form:input path="userName" />
					<p><form:errors path="userName" class="error" /></p>
				</div>
		    </div>
		    <div>
				<label for="password">Password</label>
				<div>
				    <form:input path="password" type="password" />
				    <p><form:errors path="password" class="error" /></p>
				</div>
		    </div>
		    <input type="submit" value="Login">
		</fieldset>
		</form:form>
		<a href="${pageContext.request.contextPath}/volunteerRegistration">Volunteer Registration</a>
	</body>
</html>