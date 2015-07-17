<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>   
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
		
<form:form method="POST" action="saveCHPSmapping.do" commandName="cs">
<c:forEach items="${specialities}" var="speciality" varStatus="checkBox">
<c:choose>  
<c:when test="${checkBox.count%3 == 0}">
<div class="small-12 medium-4 large-4 columns">
<form:checkbox path="sp" value="${speciality}"/>${speciality}
<div class="clear-both"></div>
</div>
<br>
</c:when>
<c:otherwise>
<div class="small-12 medium-4 large-4 columns">
<form:checkbox path="sp" value="${speciality}"/>${speciality}
<div class="clear-both"></div>
</div>
</c:otherwise>  
</c:choose>
</c:forEach>

<div class="small-12 medium-4 large-4 columns">
</div>
<div class="small-12 medium-4 large-4 columns">
</div>
<br>
<div id="padding-right" class="small-12 medium-12 large-6 columns">
<form:input path="serviceName" value="${cs.serviceName}"/>
</div>
<div id="padding-left" class="small-12 medium-12 large-6 columns">
<form:select path="service">
<form:option value="Specialities" selected="true">Specialities</form:option>
<form:option value="Anciallary Services">Anciallary Services</form:option>
</form:select>
</div>
<!--  form:checkboxes path="sp" items="${specialities}" /-->
<input type="submit" value="Submit"/>

</form:form>
</body>
</html>