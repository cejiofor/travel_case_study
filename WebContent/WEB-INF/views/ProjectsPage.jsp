<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Projects Page</title>
<%-- 		<script src="${pageContext.request.contextPath}/script/sorttable.js"></script>  --%>
		<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js" integrity="sha384-6khuMg9gaYr5AxOqhkVIODVIvm9ynTT5J4V1cfthmT+emCG6yVmEZsRHdxlotUnm" crossorigin="anonymous"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
		
	</head>
	<body>
		<h1>All Projects</h1>
		
		
		
		<c:choose>
			<c:when test="${empty sessionScope.volunteer}">
				<%@ include file="OrgNavigation.html" %>
	       	</c:when>
	       	<c:when test="${empty sessionScope.orgUser}">
	       		<%@ include file="VolunteerNavigation.html" %>
	       	</c:when>
		</c:choose>
		
		<c:choose>
			<c:when test="${empty sessionScope.volunteer}">
				<%@ include file="OrgNavigation.html" %>
	       	</c:when>
	       	<c:when test="${empty sessionScope.orgUser}">
	       		<%@ include file="VolunteerNavigation.html" %>
	       	</c:when>
       	</c:choose>   
       	
       	<div class="input-group"> <span class="input-group-addon">Filter</span>
		    <input id="filter" type="text" class="form-control" placeholder="Type here...">
		</div>
		<table class="table" style="width:70%">
			<thead>
			<tr>
				<th>Project ID</th>
				<th>Project Title</th>
				<th>City</th>
				<th>Country</th>
				<th>Start Date</th>
				<th>End Date</th>
				<th>Key Skills</th>
				<th>Organization</th>
			</tr>
			</thead>
			<tbody class="searchable">
				<c:forEach items="${projectList}" var="project">
					<tr>
						<td>${project.projectID}</td>
						<td><a href="${pageContext.request.contextPath}/showProject/${project.projectID}">${project.projectName}</a></td>
						<td>${project.city}</td>
						<td>${project.country}</td>
						<td><fmt:formatDate value="${project.startDate}" pattern="yyyy-MM-dd" /></td>
						<td><fmt:formatDate value="${project.endDate}" pattern="yyyy-MM-dd" /></td>
						<td>${project.skills}</td>
						<td>${project.orgID}</td>
						<td>
				      		<c:choose>
								<c:when test="${volunteerMap.get(project.projectID).contains(volunteer.volunteerId)}">
									<a href="${pageContext.request.contextPath}/cancelSignUp?volunteerId=${volunteer.volunteerId}&projectId=${project.projectID}">Cancel</a>
								</c:when>
								<c:otherwise>
									<a href="${pageContext.request.contextPath}/projectSignUp?volunteerId=${volunteer.volunteerId}&projectId=${project.projectID}">SignUp</a>
								</c:otherwise>	
				      		</c:choose>
						</td>
					</tr>
				</c:forEach>
			</tbody>
			
		</table>
	</body>
	<script type="text/javascript">
		$(document).ready(function () {
	
		    (function ($) {
	
		        $('#filter').keyup(function () {
	
		            var rex = new RegExp($(this).val(), 'i');
		            $('.searchable tr').hide();
		            $('.searchable tr').filter(function () {
		                return rex.test($(this).text());
		            }).show();
	
		        })
	
		    }(jQuery));
	
		});
	</script>
</html>