<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
<div id="secondary-menu" class="small-12 medium-12 large-12 columns">
			<ul>
				<li><a href="ChHome.do">Clearing House</a></li>
				<li><a href="viewCHAdmins.do">Administrators</a></li>
				<li><a href="viewPractices.do">Practices</a></li>
				<li><a href="masterData.do">Master Data</a></li>
			</ul>
		</div>

<h1>Administrators</h1>
<div id="user-roles" class="small-12 medium-12 large-12 columns">
<table width="200" border="0">
<c:if test="${!empty chAdmins}"> 
<c:forEach items="${chAdmins}" var="chAdminInfo">
<tr>
<td class="role-name">
<c:out value="${chAdminInfo.firstName}"/> &nbsp;
<c:out value="${chAdminInfo.lastName}"/> 
</td>
<td>COMPLETE</td><td>Done</td>
<td>
<a href="editCHAdmin.do?chAdminId=${chAdminInfo.chAdminId}">Edit</a>
</td>
</tr>
</c:forEach>
</c:if>
</table>
</div>
<div id="add-administrator" class="small-12 medium-12 large-12 columns">
<h1>Add an Administrator</h1>
<form:form method="POST" action="addNewCHAdmin.do">
 <div class="small-12 medium-2 large-2 columns">
<form:label path="firstName">Name</form:label> 
</div>
 <div class="small-12 medium-3 large-4 columns">
<form:input path="firstName" value="${chAdminInfo.firstName}"/>
 </div>
<div class="small-12 medium-2 large-2 columns">
<form:label path="emaiId">Email ID</form:label>
</div> 
<div class="small-12 medium-3 large-4 columns">
<form:input path="emaiId" value="${chAdminInfo.emaiId}"/>
</div>
<div class="small-12 medium-12 large-12 columns">
<input type="submit" value="Submit"/>
</div>  
</form:form>	
</div>
</body>
</html>