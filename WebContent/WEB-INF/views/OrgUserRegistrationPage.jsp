<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Registration Page</title>
		<style>
			.error {
				color: red;
			}
		</style>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
		<link href="https://netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet"/>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
	</head>
	<body>
		<%@ include file="sessions.jsp" %>
		<h1>Partner Registration</h1>
		<h2>Partner Org: ${org.orgName}</h2>
	
		<% if (request.getAttribute("errorMessage") != null) {%>
			<p style="color: red;"><%= request.getAttribute("errorMessage") %></p>
		<% } %>

		<form:form action="${pageContext.request.contextPath}/registerOrgUser" method="post" modelAttribute="user">
		<fieldset>
		    <legend>Register Partner</legend>
		    <table class="table" style="width:50%">
		    	<tr>
					<td><label for="userName">Username/Email</label></td>
			        <td>
			            <form:input path="userName" />
			            <p><form:errors path="userName" class="error"/></p>
					</td>
			    </tr>
		    	<tr>
					<td><label for="password">Password</label></td>
			        <td>
					    <form:input path="password" />
					    <p><form:errors path="password" class="error" /></p>
			        </td>
				</tr>
				<tr>
					<td><label for="firstName">First Name</label></td>
					<td>
						<form:input path="firstName" />
						<p><form:errors path="firstName" class="error" /></p>
					</td>
				</tr>
				<tr>
					<td><label for="lastName">Last Name</label></td>
					<td>
						<form:input path="lastName" />
						<p><form:errors path="lastName" class="error" /></p>
					</td>
				</tr>
				<tr>
					<td><label for="address">Address</label></td>
					<td>
						<form:input path="address" />
						<p><form:errors path="address" class="error" /></p>
					</td>
				</tr>
				<tr>
					<td><label for="city">City</label></td>
					<td>
						<form:input path="city" />
						<p><form:errors path="city" class="error" /></p>
					</td>
				</tr>
				<tr>
					<td><label for="state">State</label></td>
					<td>
						<form:input path="state" />
						<p><form:errors path="state" class="error" /></p>
					</td>
				</tr>
				<tr>
					<td><label for="country">Country</label></td>
					<td>
						<form:input path="country" />
						<p><form:errors path="country" class="error" /></p>
					</td>
				</tr>
				<tr style="display:none;">
					<td><label for="orgId">OrgID</label></td>
					<td>
						<form:input path="orgId" value="${org.orgID}" />
						<p><form:errors path="orgId" class="error" /></p>
					</td>
				</tr>
				
				<tr>
					<td><label for="isPrimeContact">Are you the primary contact? </label></td>
					<td>
						<form:select path="isPrimeContact">
						    <form:option value="True"/>
						    <form:option value="False"/>
						</form:select>
						<p><form:errors path="isPrimeContact" class="error" /></p>
					</td>
				</tr>
				<tr style="display:none;">
					<td><label for="isVolunteer">Volunteer Status</label></td>
					<td>
						<form:input path="isVolunteer" value="false"/>
						<p><form:errors path="isVolunteer" class="error" /></p>
					</td>
				</tr>
		    </table>
		    <input type="submit" value="Submit Registration">
		</fieldset>
	    </form:form>
   		<a href="${pageContext.request.contextPath}/">Cancel</a>
	</body>
</html>