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
		<%@ include file="OrgNavigation.html" %>
		
		<h1>Organization Information Page</h1>
		<table>
			<tr style="display: none;">
				<td>Org ID</td>
				<td>${org.orgID}</td>
			</tr>
			<tr>
				<td>Org Name</td>
				<td>${org.orgName}</td>
			</tr>
			<tr>
				<td>Website</td>
				<td>${org.website}</td>
			</tr>
			<tr>
				<td>Mission</td>
				<td>${org.mission}</td>
			</tr>
			<tr>
				<td>Email</td>
				<td>${org.email}</td>
			</tr>
			<tr>
				<td>Address</td>
				<td>${org.address}</td>
			</tr>
			<tr>
				<td>Prime Contact ID</td>
				<td>${org.primeContactId}</td>
			</tr>
		</table>
		<a href="${pageContext.request.contextPath}/showOrgUpdate">Update Org Information</a> 
	</body>
</html>