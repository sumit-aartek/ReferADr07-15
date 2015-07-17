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
<form:form method="POST" action="saveProvider.do" modelAttribute="provider">
<Center>
<table>
<form:hidden path="practiceInfo.practiceId" value="${provider.practiceInfo.practiceId}"/>
<form:hidden path="radCodes.codeId" value="${provider.radCodes.codeId}"/>
<tr>
 <td><form:label path="providerFirstName">First Name</form:label></td> 
 <td><form:input path="providerFirstName" value="${provider.providerFirstName}"/></td>
 </tr>
 <tr>
 <td><form:label path="providerLastName">Last Name</form:label></td> 
 <td><form:input path="providerLastName" value="${provider.providerLastName}"/></td>
 </tr>
 <tr>
 <td><form:label path="providerEmail">Email ID</form:label></td> 
 <td><form:input path="providerEmail" value="${provider.providerEmail}"/></td>
 </tr>
 <tr>
 <td><form:label path="providerPwd">Password</form:label></td> 
 <td><form:password path="providerPwd" value="${provider.providerPwd}"/></td>
 </tr>  
 <tr>
 <td><form:label path="providerPhone">Contact</form:label></td> 
 <td><form:input path="providerPhone" value="${provider.providerPhone}"/></td>
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