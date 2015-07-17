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
				<li><a href="#">Clearing House</a></li>
				<li><a href="/referadr/chAdmin.html">Administrators</a></li>
				<li><a href="#">Practices</a></li>
				<li><a href="#">Master Data</a></li>
			</ul>
		</div>
		
<form:form method="POST" action="/referadr/saveCHPSmapping.html" commandName="cs">
<form:checkboxes path="sp" items="${specialities}" />
<input type="submit" value="Submit"/>

</form:form>
</body>
</html>