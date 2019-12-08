<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<!-- IMPORT CSS FOR PAGE AND NAVIGATION HERE -->
		
		<meta charset="ISO-8859-1">
		<title>Projects Page</title>
		<script src="${pageContext.request.contextPath}/script/sorttable.js"></script> 
	</head>
	<body>
		<h1>Projects for ${volunteer.userName}</h1>
		<p>Test ${volunteer.volunteerId}</p>
		
		<%@ include file="Navigation.html" %>
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
					<td>${project.projectName}</td>
					<td>${project.city}</td>
					<td>${project.country}</td>
					<td>${project.startDate}</td>
					<td>${project.endDate}</td>
					<td>${project.skills}</td>
					<td>${project.orgID}</td>
					<td>
	<!-- 					Switch statement for table end, depends on user status -->
	<!-- 					Outer Condition check to see if logged in member created event -->
<%-- 						<c:choose> --%>
<!-- 							Allow creators to delete event -->
<%-- 							<c:when test = "${project.orgID == orgUser.orgID}"> --%>
<%-- 				            	<a href="${pageContext.request.contextPath}/projects/deleteProject">Delete</a> --%>
<%-- 				         	</c:when> --%>
<%-- 				         	<c:otherwise> --%>
<%-- 								<c:choose> --%>
<!-- 									Current attendees are given option to cancel, all others to sign up -->
<%-- 									<c:when test="${event.attendersContainsIdLoop(currentMember.memberId)}"> --%>
<%-- 										<a href="${pageContext.request.contextPath}/events/cancelSignup?memberId=${currentMember.memberId}&eventId=${event.eventId}">Cancel</a> --%>
<%-- 									</c:when> --%>
<%-- 									<c:otherwise> --%>
<%-- 										<a href="${pageContext.request.contextPath}/events/signUpForEvent?memberId=${currentMember.memberId}&eventId=${event.eventId}">SignUp</a> --%>
<%-- 									</c:otherwise>	 --%>
<%-- 								</c:choose> --%>
<%-- 							</c:otherwise> --%>
<%-- 			      		</c:choose> --%>
			      		<c:choose>
							<c:when test="${project.volunteerIdSignLoop(volunteer.volunteerId)}">
								<a href="${pageContext.request.contextPath}/cancelSignup?volunteerId=${volunteer.volunteerId}&projectId=${project.projectID}">Cancel</a>
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