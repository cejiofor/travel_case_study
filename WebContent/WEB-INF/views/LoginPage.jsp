<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Login Page</title>
		<style>
		    .error {
				color: red;
		    }
		</style>
	</head>
	<body>
		<h1>Welcome to Travel Corps</h1>
		<form:form action="${pageContext.request.contextPath}/loginUser" method="post" modelAttribute="user">
		<fieldset>
		    <legend>Login Here</legend>
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
				    <form:input path="password" />
				    <p><form:errors path="password" class="error" /></p>
				</div>
		    </div>
		    <input type="submit" value="Login">
		</fieldset>
		</form:form>
		<a href="${pageContext.request.contextPath}/showRegistration">Register Here</a>
	</body>
</html>