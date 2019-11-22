<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<!-- IMPORT CSS FOR PAGE AND NAVIGATION HERE -->

<meta charset="ISO-8859-1">
<title>Events Page</title>
</head>
<body>
	<h1>Events</h1>
	<%@ include file="Navigation.html" %>
	<form action="${pageContext.request.contextPath}/events/createEvent" method="post">
		<div>
			<label>Event Title: </label>
			<input name="title" />
		</div>
		<div>
			<label>Event Description: </label>
			<input name="description" />
		</div>
		<div>
			<label>Event Location: </label>
			<input name="location" />
		</div>
		<div>
			<label>Date &amp; Time: </label>
			<input type="datetime-local" name="dateTime" />
		</div>
		<div>
			<input type="submit" value="Create Event" />
		</div>
	</form>
	
	<table>
		<tr>
			<th>Event ID</th>
			<th>Title</th>
			<th>Description</th>
			<th>Location</th>
			<th>Date Time</th>
		</tr>
		
		<c:forEach items="${eventList}" var="event">
			<tr>
				<td>${event.title}</td>
				<td>${event.description}</td>
				<td>${event.location}</td>
				<td>${event.dateTime}</td>
				<td>
					<!-- Switch statement for table end, depends on user status-->
					<!-- Outer Condition check to see if logged in member created event -->
					<c:choose>
						<%-- Allow creators to delete event --%>
						<c:when test = "${currentMember.memberId == event.memberId}">
			            	<a href="${pageContext.request.contextPath}/events/deleteEvent">Delete</a>
			         	</c:when>
			         	<c:otherwise>
							<c:choose>
								<%-- Current attendees are given option to cancel, all others to sign up --%>
								<c:when test="${event.attendersContainsIdLoop(currentMember.memberId)}">
									<a href="${pageContext.request.contextPath}/events/cancelSignup?memberId=${currentMember.memberId}&eventId=${event.eventId}">Cancel</a>
								</c:when>
								<c:otherwise>
									<a href="${pageContext.request.contextPath}/events/signUpForEvent?memberId=${currentMember.memberId}&eventId=${event.eventId}">SignUp</a>
								</c:otherwise>	
							</c:choose>
						</c:otherwise>
		      		</c:choose>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>