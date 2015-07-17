<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
<Center><h2>Register</h2></Center>
<form:form method="POST" action="/referadr/saveCHAdmin.html" modelAttribute="command">
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
 <td><form:label path="password">Password</form:label></td> 
 <td><form:password path="password" value="${chAdmin.password}"/></td>
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