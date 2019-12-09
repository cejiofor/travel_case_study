<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
	</head>
	<body>
		<h1>Partner Registration</h1>
		<a href="${pageContext.request.contextPath}/">Cancel</a>
		
		<% if (request.getAttribute("errorMessage") != null) {%>
			<p style="color: red;"><%= request.getAttribute("errorMessage") %></p>
		<% } %>

		<form:form action="${pageContext.request.contextPath}/registerOrgUser" method="post" modelAttribute="orgUser">
		<fieldset>
		    <legend>Register Partner</legend>
		    <table>
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
	</body>
</html>