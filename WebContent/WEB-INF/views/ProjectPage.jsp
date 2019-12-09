<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Project Information Page</title>
</head>
<body>
	<%@ include file="OrgNavigation.html" %>
	<%@ include file="VolunteerNavigation.html" %>
	<h1>${project.projectName} Information Page</h1>
	<table>
		<tr>
			<td>Project ID</td>
			<td>${project.projectID}</td>
		</tr>
		<tr>
			<td>Project Name</td>
			<td>${project.projectName}</td>
		</tr>
		<tr>
			<td>Project City</td>
			<td>${project.city}</td>
		</tr>
		<tr>
			<td>Project Country</td>
			<td>${project.country}</td>
		</tr>
		<tr>
			<td>Project Start</td>
			<td>${project.startDate}</td>
		</tr>
		<tr>
			<td>Project End</td>
			<td>${project.endDate}</td>
		</tr>
		<tr>
			<td>Project Organization ID</td>
			<td>${project.orgID}</td>
		</tr>
		<tr>
			<td>Project Key Skills</td>
			<td>${project.skills}</td>
		</tr>
	</table>
	
	
	<c:forEach items="${project.skills}" var="skill">
		<p>${skill}</p>
	</c:forEach>
	<a href="${pageContext.request.contextPath}/showProjectUpdate">Update Profile</a> 
</html>