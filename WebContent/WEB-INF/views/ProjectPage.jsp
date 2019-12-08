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
	<%@ include file="Navigation.html" %>
	<h1>${project.projectName} Information Page</h1>
	
	<h2>Email: ${currentMember.email}</h2>
	<h2>Favorite Language: ${currentMember.favoriteLanguage}</h2>
	
	
	<a href="${pageContext.request.contextPath}/showProjectUpdate">Update Profile</a> 
</html>