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

<div id="main-site-container" class="small-12 medium-12 large-12 columns">
	<div id="scroll" class="small-12 medium-12 large-8 columns">
	<div id="speciality-panel" class="panel">
		
 <form:form method="POST" action="updateCHInfo.do" modelAttribute="chInfo">
<form:hidden path="id" value="${chInfo.id}"/>
 <form:label path="name">Name</form:label> 
 <form:input path="name" value="${chInfo.name}" readonly="true" />
 <form:label path="description">Description</form:label> 
 <form:input path="description" value="${chInfo.description}"/>
 
 <form:hidden path="radLocation.locId" value="${chInfo.radLocation.locId}"/>
 
 <form:label path="radLocation.locAddress1">Address1</form:label>
 <form:input path="radLocation.locAddress1" value="${chInfo.radLocation.locAddress1}"/>
 <form:label path="radLocation.locAddress2">Address2</form:label> 
 <form:input path="radLocation.locAddress2" value="${chInfo.radLocation.locAddress2}"/>
 <div id="padding-right" class="small-12 medium-6 large-6 columns">
 <form:label path="radLocation.locCity">City</form:label> 
 <form:input path="radLocation.locCity" value="${chInfo.radLocation.locCity}"/>
 </div>
 <div id="padding-0" class="small-12 medium-6 large-3 columns">
 <form:label path="radLocation.locState">State</form:label>
 <form:select path="radLocation.redState.stateId">
 <c:forEach items="${states}" var="state">
 <c:choose>
 <c:when test="${state.stateId == chInfo.radLocation.redState.stateId}">
 <form:option value="${state.stateId}" selected="true">${state.stateName}</form:option>
 </c:when>
 <c:otherwise>
 <form:option value="${state.stateId}">${state.stateName}</form:option>
 </c:otherwise>
 </c:choose>
 </c:forEach>
 </form:select>
 </div>
 <div id="padding-left" class="small-12 medium-6 large-3 columns">
 <form:label path="radLocation.locZip">Zip</form:label> 
 <form:input path="radLocation.locZip" value="${chInfo.radLocation.locZip}"/>
 </div>
 <div id="padding-right" class="small-12 medium-6 large-6 columns">
 <form:label path="radLocation.locPhone">Phone</form:label>
 <form:input path="radLocation.locPhone" value="${chInfo.radLocation.locPhone}"/>
 </div>
 <div id="padding-left" class="small-12 medium-6 large-6 columns">
 <form:label path="radLocation.locFax">Fax</form:label> 
 <form:input path="radLocation.locFax" value="${chInfo.radLocation.locFax}"/>
 </div>
 <div id="padding-0" class="small-12 medium-12 large-12 columns">
 <form:label path="radLocation.locWebsite">URL</form:label> 
 <form:input path="radLocation.locWebsite" value="${chInfo.radLocation.locWebsite}"/>
 </div>
 <div id="padding-0" class="buttons-blue-container"><input type="submit" value="Submit"/></div>  

</form:form>		
		
</body>
</html>