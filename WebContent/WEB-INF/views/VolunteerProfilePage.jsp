<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Volunteer Profile Page</title>
	</head>
	<body>
		<%@ include file="Navigation.html" %>
		<h1>Volunteer Profile Page</h1>
		<!-- <c:forEach items="${volunteer.skills}" var="skill">
			<h3>${skill}</h3>
		</c:forEach> -->
		<table>
			<tr style="display: none;">
				<td>User ID</td>
				<td>${volunteer.userId}</td>
			</tr>
			<tr>
				<td>Username</td>
				<td>${volunteer.userName}</td>
			</tr>
			<tr>
				<td>First Name</td>
				<td>${volunteer.firstName}</td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td>${volunteer.lastName}</td>
			</tr>
			<tr>
				<td>Address</td>
				<td>${volunteer.address}</td>
			</tr>
			<tr>
				<td>City</td>
				<td>${volunteer.city}</td>
			</tr>
			<tr>
				<td>State</td>
				<td>${volunteer.state}</td>
			</tr>
			<tr>
				<td>Country</td>
				<td>${volunteer.country}</td>
			</tr>
			<tr>
				<td>Skills</td>
				<td>${volunteer.skills}</td>
			</tr>
		</table>

		<a href="${pageContext.request.contextPath}/showVolunteerUpdate">Update Volunteer Profile</a>
	</body> 
</html>