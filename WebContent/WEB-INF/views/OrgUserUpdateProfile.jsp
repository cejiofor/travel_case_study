<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Partner Profile</title>
</head>
<body>
	<%@ include file="OrgNavigation.html" %>
	<form:form action="${pageContext.request.contextPath}/updateOrgUser" method="post" modelAttribute="orgUser">
		<fieldset>
		    <legend>Update Partner Profile</legend>
		    <p style="color:red;">${errorMessage}</p>
			<table>
				<tr>
					<td><label for="orgUserId">OrgUserID</label></td>
					<td>
						<form:input path="orgUserId" />
						<p><form:errors path="orgUserId" class="error"/></p>
					</td>
				</tr>
				<tr>
					<td><label for="userId">UserID</label></td>
					<td>
						<form:input path="userId" />
						<p><form:errors path="userId" class="error"/></p>
					</td>
				</tr>
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
						<form:select path="skills">
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
		    <input type="submit" value="Update Profile">
		</fieldset>
	    </form:form>
</body>
</html>