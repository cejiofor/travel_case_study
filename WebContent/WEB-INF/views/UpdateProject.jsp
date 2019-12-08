<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form action="${pageContext.request.contextPath}/updateVolunteer" method="post" modelAttribute="volunteer">
		<fieldset>
		    <legend>Register User</legend>
		    <p style="color:red;">${errorMessage}</p>
		    <table>
		    	<tr>
					<td><label for="userName">Username/Email</label></td>
			        <td>
			            <form:input path="userName" value="${volunteer.userName}"/>
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
					<td><label for="skills">Please Select Skills</label></td>
					<td>
						<form:select path="skills">
						    <form:option value="Advertising"/>
						    <form:option value="Coding"/>
						    <form:option value="Design"/>
						    <form:option value="Teaching"/>
						    <form:option value="Translation"/>
						</form:select>
						<p><form:errors path="skills" class="error" /></p>
					</td>
				</tr>
				<tr style="diplay:none;">
					<td><label for="isVolunteer">Volunteer Status</label></td>
					<td>
						<form:input path="isVolunteer" value="true"/>
						<p><form:errors path="isVolunteer" class="error" /></p>
					</td>
				</tr>
		    </table>
		    <input type="submit" value="Submit Registration">
		</fieldset>
	    </form:form>
</body>
</html>