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
		<c:choose>
			<c:when test="${empty sessionScope.volunteer}">
				<%@ include file="OrgNavigation.html" %>
	       	</c:when>
	       	<c:when test="${empty sessionScope.orgUser}">
	       		<%@ include file="VolunteerNavigation.html" %>
	       	</c:when>
       	</c:choose>
       	
<%--     	<c:forEach items="${sessionScope}" var="attr"> --%>
<%-- 		    ${attr.key}=${attr.value}<br> --%>
<%-- 		</c:forEach> --%>

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
				<td>${project.startDate}</td>
			</tr>
			<tr>
				<td>Project End</td>
				<td>${project.endDate}</td>
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