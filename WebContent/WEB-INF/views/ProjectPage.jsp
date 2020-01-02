<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Project Information Page</title>
		<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js" integrity="sha384-6khuMg9gaYr5AxOqhkVIODVIvm9ynTT5J4V1cfthmT+emCG6yVmEZsRHdxlotUnm" crossorigin="anonymous"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
	</head>
	<body>
		<%@ include file="sessions.jsp" %>
		<c:choose>
			<c:when test="${sessionScope.user['class'].simpleName  eq 'Volunteer'}">
				IT WORKED
				<%@ include file="VolunteerNavigation.html" %>
	       	</c:when>
	       	<c:when test="${sessionScope.user['class'].simpleName  eq 'OrganizationUser'}">
	       		<%@ include file="OrgNavigation.html" %>
	       	</c:when>
		</c:choose>

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
				<td><fmt:formatDate value="${project.startDate}" pattern="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<td>Project End</td>
				<td><fmt:formatDate value="${project.endDate}" pattern="yyyy-MM-dd" /></td>
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
		
		<c:if test="${empty sessionScope.volunteer}">
			<c:if test="${orgUser.orgID == project.orgID}">
				<a href="${pageContext.request.contextPath}/showProjectUpdate">Update Project</a> 
			</c:if>
	    </c:if>
	</body>
</html>