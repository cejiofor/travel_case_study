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
		<h1>Registration</h1>
		<%@ include file="Navigation.html" %>
		<a href="${pageContext.request.contextPath}/">Cancel</a>
		
		<% if (request.getAttribute("errorMessage") != null) {%>
			<p style="color: red;"><%= request.getAttribute("errorMessage") %></p>
		<% } %>

		<form:form action="${pageContext.request.contextPath }/registerUser" method="post" modelAttribute="user">
		<fieldset>
		    <legend>Register User</legend>
		    <table>
		    	<tr>
					<td><label for="userName">Username</label></td>
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
<!-- 		    	<tr> -->
<!-- 					<td><label for="email">Email</label></td> -->
<!-- 			        <td> -->
<%-- 					    <form:input path="email" /> --%>
<%-- 					    <p><form:errors path="email" class="error" /></p> --%>
<!-- 			        </td> -->
<!-- 		    	</tr> -->
		    	<tr>
					<td><label for="primeContact">Prime Contact</label></td>
			        <td>
					    <form:select path="primeContact">
			                <form:option value="true">Yes</form:option>
			                <form:option selected="selected" value="false">No</form:option>
			            </form:select>	
					    <p><form:errors path="primeContact" class="error" /></p>
			        </td>
		    	</tr>
		    </table>
		    <input type="submit" value="Submit Registration">
		</fieldset>
	    </form:form>
	</body>
</html>