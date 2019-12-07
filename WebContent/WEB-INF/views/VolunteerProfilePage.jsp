<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Member Profile Page</title>
</head>
<body>
	<%@ include file="Navigation.html" %>
	<h1>Volunteer Profile Page</h1>
	
	<h2>Username: ${volunteer.username}</h2>
	<h2>First Name: ${volunteer.firstName}</h2>
	<h2>Last Name: ${volunteer.firstName}</h2>
	<h2>Address: ${volunteer.firstName}</h2>
	<h2>City: ${volunteer.firstName}</h2>
	<h2>State: ${volunteer.firstName}</h2>
	<h2>Country: ${volunteer.firstName}</h2>
	<h2>First Name: ${volunteer.firstName}</h2>
	<h2>Skills:</h2>
	<c:forEach items="${volunteer.skills}" var="skill">
		<h3>{skill}</h3>
	</c:forEach>
	<a href="${pageContext.request.contextPath}/showVolunteerUpdate">Update Volunteer Profile</a> 
</html>