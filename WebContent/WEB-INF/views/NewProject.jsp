<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create New Project</title>
</head>
<body>
	<form:form action="${pageContext.request.contextPath}/createProject" method="post" modelAttribute="project">
		<fieldset>
		    <legend>Create New Project</legend>
		    <p style="color:red;">${errorMessage}</p>
		    <table>
		    	<tr style="diplay:none;">
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
						<form:input path="startDate" />
						<p><form:errors path="startDate" class="error" /></p>
					</td>
				</tr>
				<tr>
					<td><label for="endDate">End Date</label></td>
					<td>
						<form:input path="endDate" />
						<p><form:errors path="endDate" class="error" /></p>
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