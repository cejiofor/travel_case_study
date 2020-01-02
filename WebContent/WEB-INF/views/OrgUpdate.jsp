<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Update Partner Profile</title>
		<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js" integrity="sha384-6khuMg9gaYr5AxOqhkVIODVIvm9ynTT5J4V1cfthmT+emCG6yVmEZsRHdxlotUnm" crossorigin="anonymous"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
	</head>
	<body>
		<%@ include file="sessions.jsp" %>
		<%@ include file="OrgNavigation.html" %>
		
		<form:form action="${pageContext.request.contextPath}/updateOrg" method="post" modelAttribute="org">
			<fieldset>
				<legend>Update Org Profile</legend>
				<p style="color:red;">${errorMessage}</p>
				<table>
					<tr style="display: none;">
						<td><label for="orgID">OrgID</label></td>
						<td>
							<form:input path="orgID" />
							<p><form:errors path="orgID" class="error"/></p>
						</td>
					</tr>
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
						<td><label for="mission">Mission</label></td>
						<td>
							<form:input path="mission" />
							<p><form:errors path="mission" class="error" /></p>
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
					<tr style="display:none;">
						<td><label for="primeContactId">Prime Contact ID</label></td>
						<td>
							<form:input path="primeContactId" value="1" />
							<p><form:errors path="primeContactId" class="error" /></p>
						</td>
					</tr>
				</table>
				<input type="submit" value="Update Org Profile">
			</fieldset>
		</form:form>
	</body>
</html>