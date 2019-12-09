<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Projects Page</title>
		<script src="${pageContext.request.contextPath}/script/sorttable.js"></script> 
	</head>
	<body>
		<h1>All Projects</h1>
		
<%-- 		<c:forEach items="${sessionScope}" var="attr"> --%>
<%-- 		    ${attr.key}=${attr.value}<br> --%>
<%-- 		</c:forEach> --%>
		
		<c:choose>
			<c:when test="${empty sessionScope.volunteer}">
				<%@ include file="OrgNavigation.html" %>
	       	</c:when>
	       	<c:when test="${empty sessionScope.orgUser}">
	       		<%@ include file="VolunteerNavigation.html" %>
	       	</c:when>
       	</c:choose>
		<table class="sorttable">
			<tr>
				<th>Project ID</th>
				<th>Project Title</th>
				<th>City</th>
				<th>Country</th>
				<th>Start Date</th>
				<th>End Date</th>
				<th>Key Skills</th>
				<th>Organization</th>
			</tr>
			
			<c:forEach items="${projectList}" var="project">
				<tr>
					<td>${project.projectID}</td>
					<td><a href="${pageContext.request.contextPath}/showProject/${project.projectID}">${project.projectName}</a></td>
					<td>${project.city}</td>
					<td>${project.country}</td>
					<td>${project.startDate}</td>
					<td>${project.endDate}</td>
					<td>${project.skills}</td>
					<td>${project.orgID}</td>
					<td>
			      		<c:choose>
							<c:when test="${volunteerMap.get(project.projectID).contains(volunteer.volunteerId)}">
								<a href="${pageContext.request.contextPath}/cancelSignUp?volunteerId=${volunteer.volunteerId}&projectId=${project.projectID}">Cancel</a>
							</c:when>
							<c:otherwise>
								<a href="${pageContext.request.contextPath}/projectSignUp?volunteerId=${volunteer.volunteerId}&projectId=${project.projectID}">SignUp</a>
							</c:otherwise>	
			      		</c:choose>
					</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>