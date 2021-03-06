<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Volunteer Profile Page</title>
		<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js" integrity="sha384-6khuMg9gaYr5AxOqhkVIODVIvm9ynTT5J4V1cfthmT+emCG6yVmEZsRHdxlotUnm" crossorigin="anonymous"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
	</head>
	<body>
		<%@ include file="sessions.jsp" %>
		<%@ include file="VolunteerNavigation.html" %>
		<h1>Volunteer Profile Page</h1>
		<!-- <c:forEach items="${volunteer.skills}" var="skill">
			<h3>${skill}</h3>
		</c:forEach> -->
		<table>
			<tr style="display: none;">
				<td>User ID</td>
				<td>${user.userId}</td>
			</tr>
			<tr>
				<td>Username</td>
				<td>${user.userName}</td>
			</tr>
			<tr>
				<td>First Name</td>
				<td>${user.firstName}</td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td>${user.lastName}</td>
			</tr>
			<tr>
				<td>Address</td>
				<td>${user.address}</td>
			</tr>
			<tr>
				<td>City</td>
				<td>${user.city}</td>
			</tr>
			<tr>
				<td>State</td>
				<td>${user.state}</td>
			</tr>
			<tr>
				<td>Country</td>
				<td>${user.country}</td>
			</tr>
			<tr>
				<td>Skills</td>
				<td>${user.skills}</td>
			</tr>
		</table>
		<a href="${pageContext.request.contextPath}/showVolunteerUpdate">Update Volunteer Profile</a>
	</body> 
</html>