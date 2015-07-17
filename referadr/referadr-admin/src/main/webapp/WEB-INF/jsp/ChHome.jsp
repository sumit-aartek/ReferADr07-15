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
				<li>Clearing House</li>
				<li><a href="/referadr/chAdmin.html">Administrators</a></li>
				<li><a href="#">Practices</a></li>
				<li><a href="/referadr/masterData.html">Master Data</a></li>
			</ul>
		</div>
		
<%-- <form:form method="POST" action="/referadr/updateCHInfo.html" modelAttribute="CHInfocommand">
<Center>
<table>
<tr>
 <td><form:label path="name">Name</form:label></td> 
 <td><form:input path="name" value="Test CH" readonly="true" /></td>
 </tr>
 <tr>
 <td><form:label path="description">Description</form:label></td> 
 <td><form:input path="description" value="${chInfo.description}"/></td>
 </tr>
 <tr>
 <td><form:label path="address1">Address1</form:label></td> 
 <td><form:input path="address1" value="${chInfo.address1}"/></td>
 </tr>
 <tr>
 <td><form:label path="address2">Address2</form:label></td> 
 <td><form:input path="address2" value="${chInfo.address2}"/></td>
 </tr>  
 
 <tr>
 <td><form:label path="city">City</form:label></td> 
 <td><form:input path="city" value="${ch.city}"/></td>
 </tr>
 <tr>
 <td><form:label path="state">State</form:label></td> 
 <td><form:input path="state" value="${chInfo.state}"/></td>
 </tr>
 <tr>
 <td><form:label path="zip">Zip</form:label></td> 
 <td><form:input path="zip" value="${chInfo.zip}"/></td>
 </tr>
 <tr>
 <td><form:label path="phone">Phone</form:label></td> 
 <td><form:input path="phone" value="${chInfo.phone}"/></td>
 </tr>
 <tr>
 <td><form:label path="fax">Fax</form:label></td> 
 <td><form:input path="fax" value="${chInfo.fax}"/></td>
 </tr>
 
 <tr>
 <td><form:label path="url">URL</form:label></td> 
 <td><form:input path="url" value="${chInfo.url}"/></td>
 </tr>
 <tr>
 <tr>
  <td colspan="2" align="center"><input type="submit" value="Submit"/></td>  
 </tr>
</table>
</Center>
</form:form>		
		 --%>
</body>
</html>