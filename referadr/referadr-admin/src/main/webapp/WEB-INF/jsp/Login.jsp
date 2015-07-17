<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
<br>
<Center><h2>Login Form</h2></Center>
<form:form method="POST" action="/referadr/validateCredentials.html" modelAttribute="loginCommand">
<Center>
<table>
<tr>
 <td><form:label path="userName">User Name</form:label></td> 
 <td><form:input path="userName" value="${login.userName}"/></td>
 </tr>
 <tr>
 <td><form:label path="password">Password</form:label></td> 
 <td><form:password path="password" value="${login.password}"/></td>
 </tr>
<tr>
  <td colspan="2" align="center"><input type="submit" value="Submit"/></td>  
 </tr>
 </table>
</Center> 
</form:form>
</body>
</html>