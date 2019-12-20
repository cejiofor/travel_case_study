<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Organization Login Portal</title>
		<style>
			.error {
				color: red;
			}
		</style>
<!-- 		<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous"> -->
<!-- 		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script> -->
<!-- 		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js" integrity="sha384-6khuMg9gaYr5AxOqhkVIODVIvm9ynTT5J4V1cfthmT+emCG6yVmEZsRHdxlotUnm" crossorigin="anonymous"></script> -->
<!-- 		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script> -->
	</head>
	<body>
		<h1>Partner Selection</h1>
		<a href="${pageContext.request.contextPath}/">Cancel</a>
		
		<% if (request.getAttribute("errorMessage") != null) {%>
			<p style="color: red;"><%= request.getAttribute("errorMessage") %></p>
		<% } %>
		
		<c:forEach items="${sessionScope}" var="attr">
		    ${attr.key}=${attr.value}<br>
		</c:forEach>
		
		
		<p>Select Your Organization from the dropdown menu, or register a new organization</p>
		
		<form:form action="${pageContext.request.contextPath}/orgUserRegistration/${orgName}" method="get">
		<fieldset>
		    <legend>Select Partner Organization</legend>
		    <select name="orgName">
				<c:forEach items="${orgList}" var="org">
					<option>${org.orgName}</option>
				</c:forEach> 	
			</select>
		    <input type="submit" value="Select Partner">
		</fieldset>
		<fieldset>
			<legend>Register New Partner Organization</legend>
			<a href="${pageContext.request.contextPath}/orgRegistration">Register New Org Here</a>
		</fieldset>
	    </form:form>
	</body>
</html>