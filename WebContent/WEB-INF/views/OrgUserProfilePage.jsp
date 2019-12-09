<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Partner Profile Page</title>
</head>
<body>
	<%@ include file="Navigation.html" %>
	<h1>Partner Profile Page</h1>
	
	<table>
		<tr style="display: none;">
			<td>User ID</td>
			<td>${orgUser.orgUserID}</td>
		</tr>
		<tr>
			<td>Username</td>
			<td>${orgUser.userName}</td>
		</tr>
		<tr>
			<td>First Name</td>
			<td>${orgUser.firstName}</td>
		</tr>
		<tr>
			<td>Last Name</td>
			<td>${orgUser.lastName}</td>
		</tr>
		<tr>
			<td>Address</td>
			<td>${orgUser.address}</td>
		</tr>
		<tr>
			<td>City</td>
			<td>${orgUser.city}</td>
		</tr>
		<tr>
			<td>State</td>
			<td>${orgUser.state}</td>
		</tr>
		<tr>
			<td>Country</td>
			<td>${orgUser.country}</td>
		</tr>
		<tr>
			<td>PrimeContact</td>
			<td>${orgUser.isPrimeContact}</td>
		</tr>
	</table>

	<a href="${pageContext.request.contextPath}/showOrgUserUpdate">Update Profile</a> 
</html>