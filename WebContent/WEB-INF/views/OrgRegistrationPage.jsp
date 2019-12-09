<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Organization Registration Page</title>
		<style>
			.error {
				color: red;
			}
		</style>
	</head>
	<body>
		<h1>Organization Registration</h1>
		<%@ include file="Navigation.html" %>
		<a href="${pageContext.request.contextPath}/">Cancel</a>
		
		<% if (request.getAttribute("errorMessage") != null) {%>
			<p style="color: red;"><%= request.getAttribute("errorMessage") %></p>
		<% } %>

		<form:form action="${pageContext.request.contextPath}/registerOrganization" method="post" modelAttribute="organization">
		<fieldset>
		    <legend>Register Organization</legend>
		    <table>
		    	<tr>
					<td><label for="orgName">Organization Name</label></td>
			        <td>
			            <form:input path="orgName" />
			            <p><form:errors path="orgName" class="error"/></p>
					</td>
			    </tr>
		    	<tr>
					<td><label for="website">Website</label></td>
			        <td>
					    <form:input path="website" />
					    <p><form:errors path="website" class="error" /></p>
			        </td>
		    	</tr>
		    	<tr>
					<td><label for="email">Email</label></td>
			        <td>
					    <form:input path="email" />
					    <p><form:errors path="email" class="error" /></p>
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
					<td><label for="primeContactId">Prime Contact</label></td>
			        <td>
					    <form:select path="primeContactId">
			                <form:option value="true">Yes</form:option>
			                <form:option selected="selected" value="false">No</form:option>
			            </form:select>	
					    <p><form:errors path="primeContactId" class="error" /></p>
			        </td>
		    	</tr>
		    </table>
		    <input type="submit" value="Register Organization">
		</fieldset>
	    </form:form>
	</body>
</html>