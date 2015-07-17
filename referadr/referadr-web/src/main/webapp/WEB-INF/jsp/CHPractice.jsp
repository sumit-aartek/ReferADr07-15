<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
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
		
		<div id="add-a-practice" class="small-12 medium-12 large-12 columns">

			<c:if test="${!empty chPracticesInfo}">
			<table width="200" cellpadding="0" cellspacing="0">
			<tr class="first-row-heading-table">
				<td>Practice Name</td>
				<td>Category</td>
				<td>Speciality</td>
			  </tr> 
			<c:forEach items="${chPracticesInfo}" var="chPracticeInfo">
			  <tr>
				<td><a href="PracticeInfoFromCh.do?practiceId=${chPracticeInfo.practiceID}">${chPracticeInfo.name}</a></td>
				<td>${chPracticeInfo.category}</td>
				<td>
				<c:forEach items="${chPracticeInfo.sp}" var="sp">
					${sp} &nbsp; 
				</c:forEach>
				</td>
			  </tr>
			  
			 </c:forEach>
			 </table>
			</c:if>
			
		
		
		
 <form:form method="POST" action="addPractice.do" >
<h1 class="red-heading">Add A Practice</h1>

		<div id="padding-0" class="small-12 medium-12 large-6 columns">
			<div class="small-12 medium-12 large-12 columns">
			<div class="row">
				<div id="padding-0" class="small-12 medium-3 large-3 columns">
					<form:label path="name">Name</form:label>
				</div> 
				<div id="padding-0" class="small-12 medium-9 large-9 columns">
				<form:input path="name" value="${chpsp.name}"/>
				</div>
			</div>
			</div>
				
			<div class="small-12 medium-12 large-12 columns">
			<div class="row">
				<div id="padding-0" class="small-12 medium-3 large-3 columns">
					<form:label path="emailId">Email ID</form:label> 
				</div>
				<div id="padding-0" class="small-12 medium-9 large-9 columns">
					<form:input path="emailId" value="${chpsp.emailId}"/>
				</div>
			</div>
			</div>
			<div class="small-12 medium-12 large-12 columns">
			<div class="row">
				<div id="padding-0" class="small-12 medium-3 large-3 columns">
					<form:label path="emailId">Category</form:label>
				</div>
				<div id="padding-0" class="small-12 medium-9 large-9 columns">
					  <form:select path="categoryId">
						<c:forEach items="${categories}" var="category">
						 <form:option value="${category.codeId}">${category.codeDesc}</form:option>
						</c:forEach>
					  </form:select>					
				</div>
			</div>
			</div>		
	 	</div>
	 	<div class="small-12 medium-12 large-6 columns">
			<div class="row">
	 		  <p class="speciality">Press Shift Key to select more than one specialty</p>
	 		  <div class="small-12 medium-9 large-12 columns">	
 				<form:select path="sp" items="${practiceSpecialties}" />
 			  </div>
 			  
 			</div>
 		</div>	  	
 		<input type="submit" value="Add"/>
</form:form>		
</div>		

</body>
</html>