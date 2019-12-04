<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Member Profile Page</title>
</head>
<body>
	<%@ include file="Navigation.html" %>
	<h1>Member Profile Page</h1>
	
	<h2>Email: ${currentMember.email}</h2>
	<h2>Favorite Language: ${currentMember.favoriteLanguage}</h2>
	<a href="${pageContext.request.contextPath}/showUpdatePage">Update Profile</a> 
</html>