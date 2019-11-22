<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile Update Page</title>
</head>
<body>
	<h1>Profile Update Page</h1>
	<%@ include file="Navigation.html" %>
	
	<% if (request.getAttribute("errorMessage") != null) {%>
	    <p style="color: red;"><%= request.getAttribute("errorMessage") %></p>
	<% } %>
	
	<form action="${pageContext.request.contextPath}/updateMember" method="post">
		<div>
			<input name="memberId" type="hidden" value="${currentMember.memberId}"/>
		</div>
		<div>
			<label>Member Name: </label>
			<input name="name" value="${currentMember.name}"/>
		</div>
		<div>
			<label>Member Email: </label>
			<input name="email" value="${currentMember.email}"/>
		</div>
		<div>
			<label>Member Password: </label>
			<input name="password" type="password" value="${currentMember.password}" />
		</div>
		<div>
			<label>Favorite Language: </label>
			<input name="favoriteLanguage" value="${currentMember.favoriteLanguage}"/>
		</div>
		<div>
			<input type="submit" value="Update Profile" />
		</div>
	</form>
	<a href="${pageContext.request.contextPath}/showProfile">Back to Member Profile</a>
	
</body>
</html>