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
		<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js" integrity="sha384-6khuMg9gaYr5AxOqhkVIODVIvm9ynTT5J4V1cfthmT+emCG6yVmEZsRHdxlotUnm" crossorigin="anonymous"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
	</head>
	<body>
		<h1>Organization Registration</h1>
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