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
				<li><a href="ChHome.do">Clearing House</a></li>
				<li><a href="viewCHAdmins.do">Administrators</a></li>
				<li><a href="viewPractices.do">Practices</a></li>
				<li><a href="masterData.do">Master Data</a></li>
			</ul>
		</div>
		
<div id="administrators-form" class="small-12 medium-12 large-12 columns">
<form:form method="POST" action="updateCHAdmin.do" modelAttribute="chAdmin">
<form:hidden path="chAdminId" value="${chAdmin.chAdminId}"/>
<div class="row">
<div class="small-12 medium-3 large-3 columns">
 <span><form:label path="firstName">First Name</form:label></span>
 </div>
 <div class="small-12 medium-9 large-9 columns">
 <form:input path="firstName" value="${chAdmin.firstName}"/>
</div>
</div>
 <div class="row">
 <div class="small-12 medium-3 large-3 columns">
 <span><form:label path="lastName">Last Name</form:label></span>
 </div> 
 <div class="small-12 medium-9 large-9 columns">
 <form:input path="lastName" value="${chAdmin.lastName}"/>
 </div>
 </div>
 <div class="row">
 <div class="small-12 medium-3 large-3 columns">
 <span><form:label path="emaiId">Email ID</form:label></span>
 </div> 
 <div class="small-12 medium-9 large-9 columns">
<form:input path="emaiId" value="${chAdmin.emaiId}" readonly="true"/>
</div>
</div>
 <div class="row">
 <div class="small-12 medium-3 large-3 columns">
<span><form:label path="contact">Phone</form:label></span>
</div>
<div class="small-12 medium-9 large-9 columns">
<form:input path="contact" value="${chAdmin.contact}"/></div>
 </div>
  <div class="buttons-blue-container">
  <input type="submit" value="Submit"/>  
 </div>
</form:form>		
</div>		
</body>
</html>