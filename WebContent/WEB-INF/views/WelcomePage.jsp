<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome Page</title>
</head>
<body>
	<h1>Welcome to Travel Corp!</h1>
	<h3>Travel with a cause, volunteer abroad!</h3>
	<a href="${pageContext.request.contextPath}/showVolunteerLogin" >Login/Sign Up as Volunteer</a>
	<a href="${pageContext.request.contextPath}/showOrgUserLogin" >Login/Sign Up as Organization Partner</a>
</body>
</html>