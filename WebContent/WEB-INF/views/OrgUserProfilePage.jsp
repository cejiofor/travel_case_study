<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Partner Profile Page</title>
		<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js" integrity="sha384-6khuMg9gaYr5AxOqhkVIODVIvm9ynTT5J4V1cfthmT+emCG6yVmEZsRHdxlotUnm" crossorigin="anonymous"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
	</head>
	<body>
		<%@ include file="sessions.jsp" %>
		<%@ include file="OrgNavigation.html" %>
		<h1>Partner Profile Page</h1>
		<table>
			<tr style="display: none;">
				<td>User ID</td>
				<td>${orgUser.orgUserId}</td>
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
	</body>
</html>