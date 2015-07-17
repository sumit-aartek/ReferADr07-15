<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="js/jquery.1.9.1.min.js"></script>
<script src="js/vendor/jquery.ui.widget.js"></script>

<script src="js/page-js/jquery.fileupload.js"></script>
<script src="js/page-js/myuploadfunction.js"></script>
<script src="js/page-js/refer_myuploadfunction.js"></script>
<script type="text/javascript" src="js/jsp-js/referPatient.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="css/foundation.css" />
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="responsive/style.css">
<link rel="stylesheet" type="text/css" href="responsive/responsive.css">
<link rel="stylesheet" href="jQuery/css/validationEngine.jquery.css" type="text/css" />

<script src="js/vendor/modernizr.js"></script>
<script src="js/page-js/nextPrevious.js"></script>
<script src="js/page-js/validationForFisrtNext.js"></script>

<script src="jQuery/js/languages/jquery.validationEngine-en.js" type="text/javascript" charset="utf-8"></script>
<script src="jQuery/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
    jQuery(document).ready(function() {
    	 $("#formId").validationEngine();
    
    });

    	
 
</script>

</head>

	  	
		<div id="secondary-menu" class="small-12 medium-12 large-12 columns">
			<ul>
				<li><a href="PracticeInfo.do">Practice</a></li>
				<li><a href="doctor.do">Doctors</a></li>
				<li><a href="practiceInsurance.do">Insurance</a></li>
				<li><a href="#">Billing</a></li>
			</ul>
		</div>
<body>
 <form:form id="formId" method="POST" action="saveDoctorInfo.do" modelAttribute="ProviderInfo" autocomplete="off"> 
<!--  <div id="content-right-main" class="small-12 medium-12 large-9 columns"> -->
		
		<div id="main-site-container" class="small-12 medium-12 large-12 columns">
		<div id="speciality-panel" class="panel">
		<h1 class="doctor-staff">Doctors And Staff</h1>
		<div id="secondary-menu" class="small-12 medium-12 large-12 columns">
			<!-- <ul >
				<li><a href="#">PROVIDER NAME</a></li>
				<li><a href="#">EMAIL ID</a></li>
				<li><a href="#">STATUS</a></li>
				<li><a href="#">EDIT</a></li>
			</ul> -->
		
		
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr class="first-row-heading-table">
		<td>NAME</td>
					<td>EMAIL ID</td>
					<td>Role</td>
					<td>Edit</td>
		
		</tr>
 
 
  <c:forEach items="${ProviderInfoList}" var="provInfo">
  <tr>
    <td><div align="left">${provInfo.providerFirstName}</div></td>
    <td><div align="center">${provInfo.providerEmail}</div></td>
    <td><div align="center">${provInfo.radCodes.codeDesc}</div></td>
    <td><div align="center"><a href="editDoctor.do?provId=${provInfo.providerId}">Edit</a>
    						<br>
    						<a href="doctorTiming.do?provId=${provInfo.providerId}">Timing</a>
    	</div></td>
  </tr>
 </c:forEach>
</table></div>
<div>
<a href="#">Add Staff</a></div>
<div>
<div class="small-12 medium-12 large-2 columns">
<form:input path="providerFirstName" placeholder="First Name" class="validate[required]"/></div>
<div class="small-12 medium-12 large-2 columns">
<form:input path="providerLastName" placeholder="Last Name" class="validate[required]"/>
</div>

<div class="small-12 medium-12 large-3 columns">
<form:input path="providerEmail" placeholder="Email Id" class="validate[required, custom[email]]"/>
<form:hidden path="practiceInfo.practiceId" value="${providePracticeId}"/>

</div>
<div class="small-12 medium-12 large-3 columns">
<form:select path="radCodes.codeId">
<c:forEach items="${RadCodes}" var="proRole">

<form:option value="${proRole.codeId}" label="${proRole.codeValue}"/>

</c:forEach>
</form:select>

 </div><%-- <form:select path="proId" id="referring" readonly="true" >
								<form:option value="0" label="Select" />
								<c:forEach items="${ProviderInfo}" var="refProviderInfo">
									<form:option value="${refProviderInfo.providerId}" label="${refProviderInfo.providerFirstName}" />
								</c:forEach>
							</form:select> --%>

 <input type="submit" value="save" name="submit" class="small next"/>	
 </div>
	

</div>
</div>
			
			
			
		</div></form:form>
</body>
</html>