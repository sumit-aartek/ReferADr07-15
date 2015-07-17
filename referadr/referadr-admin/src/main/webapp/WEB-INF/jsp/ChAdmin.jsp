<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="secondary-menu" class="small-12 medium-12 large-12 columns">
			<ul>
				<li><a href="/referadr/ChHome.html">Clearing House</a></li>
				<li>Administrators</li>
				<li><a href="viewPractices.do">Practices</a></li>
				<li><a href="masterData.do">Master Data</a></li>
			</ul>
		</div>
		
<form:form method="POST" action="/updateCHAdmin.html" modelAttribute="CHAdminCommand">
<Center>
<table>

<tr>
 <td><form:label path="firstName">First Name</form:label></td> 
 <td><form:input path="firstName" value="${chAdmin.firstName}"/></td>
 </tr>
 <tr>
 <td><form:label path="lastName">Last Name</form:label></td> 
 <td><form:input path="lastName" value="${chAdmin.lastName}"/></td>
 </tr>
 <tr>
 <td><form:label path="emaiId">Email ID</form:label></td> 
 <td><form:input path="emaiId" value="${chAdmin.emaiId}"/></td>
 </tr>  
 
 <tr>
 <td><form:label path="contact">Contact</form:label></td> 
 <td><form:input path="contact" value="${chAdmin.contact}"/></td>
 </tr>
 <tr>
 <tr>
  <td colspan="2" align="center"><input type="submit" value="Submit"/></td>  
 </tr>
</table>
</Center>
</form:form>		
		
</body>
</html>