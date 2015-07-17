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
			
			 <c:if test="${sessionScope.clearingHouseId==null}">
	 <ul>
	<li><a href="PracticeInfo.do">PRACTICE</a></li>
    <li><a href="doctor.do">DOCTORS</a></li>
    <li><a href="practiceInsurance.do">INSURANCE</a></li>
    <li><a href="#">BILLING</a></li>
			</ul>
			</c:if>
			 <c:if test="${sessionScope.providePracticeId==null}">
			 <ul>
   <li><a href="ChHome.do">Clearing House</a></li>
    <li><a href="viewCHAdmins.do">Administrators</a></li>
   <li><a href="viewPractices.do">Practices</a></li>
   <li><a href="masterData.do">Master Data</a></li>
   </ul>
			</c:if>
		</div>

<div id="main-site-container" class="small-12 medium-12 large-12 columns">
	<div id="scroll" class="small-12 medium-12 large-8 columns">
	<div id="speciality-panel" class="panel">
		
 <form:form method="POST" action="updatePracticeInfo.do" modelAttribute="practiceInfo">

 <form:label path="practiceName">Name</form:label> 
 <form:input path="practiceName" value="${practiceInfo.practiceName}" readonly="true" />
 <form:hidden path="practiceId" value="${practiceInfo.practiceId}"/>
 <form:hidden path="practiceLocations.precticeLocationId" value="${practiceInfo.practiceLocations.precticeLocationId}"/>
 <form:hidden path="practiceLocations.radLocation.locId" value="${practiceInfo.practiceLocations.radLocation.locId}"/>
 
 <form:label path="practiceLocations.radLocation.locAddress1">Address1</form:label>
 <form:input path="practiceLocations.radLocation.locAddress1" value="${practiceInfo.practiceLocations.radLocation.locAddress1}"/>
 <form:label path="practiceLocations.radLocation.locAddress2">Address2</form:label> 
 <form:input path="practiceLocations.radLocation.locAddress2" value="${practiceInfo.practiceLocations.radLocation.locAddress2}"/>
 <div id="padding-right" class="small-12 medium-6 large-6 columns">
 <form:label path="practiceLocations.radLocation.locCity">City</form:label> 
 <form:input path="practiceLocations.radLocation.locCity" value="${practiceInfo.practiceLocations.radLocation.locCity}"/>
 </div>
 <div id="padding-0" class="small-12 medium-6 large-3 columns">
 <form:label path="practiceLocations.radLocation.locState">State</form:label>
 <form:select path="practiceLocations.radLocation.redState.stateId">
 <c:forEach items="${states}" var="state">
 <c:choose>
 <c:when test="${state.stateName == practiceInfo.practiceLocations.radLocation.locState}">
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
 <form:label path="practiceLocations.radLocation.locZip">Zip</form:label> 
 <form:input path="practiceLocations.radLocation.locZip" value="${practiceInfo.practiceLocations.radLocation.locZip}"/>
 </div>
 <div id="padding-right" class="small-12 medium-6 large-6 columns">
 <form:label path="practiceLocations.radLocation.locPhone">Phone</form:label>
 <form:input path="practiceLocations.radLocation.locPhone" value="${practiceInfo.practiceLocations.radLocation.locPhone}"/>
 </div>
 <div id="padding-left" class="small-12 medium-6 large-6 columns">
 <form:label path="practiceLocations.radLocation.locFax">Fax</form:label> 
 <form:input path="practiceLocations.radLocation.locFax" value="${practiceInfo.practiceLocations.radLocation.locFax}"/>
 </div>
 <div id="padding-0" class="small-12 medium-12 large-12 columns">
 <form:label path="practiceLocations.radLocation.locWebsite">URL</form:label> 
 <form:input path="practiceLocations.radLocation.locWebsite" value="${practiceInfo.practiceLocations.radLocation.locWebsite}"/>
 </div>
 <c:if test="${sessionScope.clearingHouseId==null}">
 <div id="padding-0" class="buttons-blue-container"><input type="submit" value="Submit"/></div>  
 	
</c:if>

</form:form>		
		
</body>
</html>