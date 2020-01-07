<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Create New Project</title>
		<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js" integrity="sha384-6khuMg9gaYr5AxOqhkVIODVIvm9ynTT5J4V1cfthmT+emCG6yVmEZsRHdxlotUnm" crossorigin="anonymous"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
	</head>
	<body>
		<%@ include file="sessions.jsp" %>
		<%@ include file="OrgNavigation.html" %>

		<form:form action="${pageContext.request.contextPath}/createProject" method="post" modelAttribute="project">
			<fieldset>
				<legend>Create New Project</legend>
				<p style="color:red;">${errorMessage}</p>
				<table>
					<tr style="display:none;">
						<td><label for="projectID">Project ID</label></td>
						<td>
							<form:input path="projectID"/>
							<p><form:errors path="projectID" class="error"/></p>
						</td>
					</tr>
					<tr>
						<td><label for="projectName">Project Name</label></td>
						<td>
							<form:input path="projectName"/>
							<p><form:errors path="projectName" class="error"/></p>
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
						<td><label for="country">Country</label></td>
						<td>
							<form:input path="country" />
							<p><form:errors path="country" class="error" /></p>
						</td>
					</tr>
					<tr>
						<td><label for="startDate">Start Date</label></td>
						<td>
							<form:input path="startDate" type="date" />
							<p><form:errors path="startDate" class="error" /></p>
						</td>
					</tr>
					<tr>
						<td><label for="endDate">End Date</label></td>
						<td>
							<form:input path="endDate" type="date" />
							<p><form:errors path="endDate" class="error" /></p>
						</td>
					</tr>
					
					<tr style="diplay:none;">
						<td><label for="orgID">Org ID</label></td>
						<td>
							<form:input path="orgID" value="${user.orgId}"/>
							<p><form:errors path="orgID" class="error"/></p>
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
				</table>
				<input type="submit" value="Create Project">
			</fieldset>
		</form:form>
	</body>
</html>