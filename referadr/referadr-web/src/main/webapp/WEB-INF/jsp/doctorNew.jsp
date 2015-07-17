<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Doctor</title>

<link rel="stylesheet" href="cssNEW/foundation.css" />
<link rel="stylesheet" href="cssNEW/style.css" />

<!-- <script src="js/jqueryNew.js"></script> -->

<script src="js/vendor/jquery.ui.widget.js"></script>
<script src="js/jquery.1.9.1.min.js"></script>
<!-- <script src="js/page-js/jquery.fileupload.js"></script>
 <script src="js/page-js/myuploadfunction.js"></script>
<script src="js/page-js/refer_myuploadfunction.js"></script>  -->

<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="jQuery/css/validationEngine.jquery.css"
	type="text/css" />
<link rel="stylesheet" type="text/css"
	href="css/jquery.datetimepicker.css" />
<link rel="stylesheet" type="text/css" href="responsiveNEW/style.css">
<link rel="stylesheet" type="text/css"
	href="responsiveNEW/responsive.css">
<!-- <script src="js/vendor/modernizr.js"></script> -->
<script src="js/page-js/nextPrevious.js"></script>


<script src="jQuery/js/languages/jquery.validationEngine-en.js"
	type="text/javascript" charset="utf-8"></script>
<script src="jQuery/js/jquery.validationEngine.js"
	type="text/javascript" charset="utf-8"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script type="text/javascript" src="js/jsp-js/referPatient.js"></script>




<script>
	jQuery(document).ready(function() {
		$("#formId").validationEngine();
	});
</script>


</head>
<body>
	<div id="content-right-main"
		class="small-12 medium-12 large-10 columns">
		<div id="main-content-full"
			class="small-12 medium-12 large-12 columns">
			<h1 class="red-heading">Doctors</h1>
			<div class="large-12 columns">
				<a href="#">Add Staff</a>
			</div>
			
			 <form:form id="formId" method="POST" class="add-new-staff-field" action="saveDoctorInfo.do" modelAttribute="ProviderInfo" autocomplete="off"> 

				<div class="clearfix"></div>
				<div class="small-12 medium-6 large-6 columns">
					<form:input path="providerFirstName" placeholder="First Name" class="validate[required]"/>
				</div>
				<div class="small-12 medium-6 large-6 columns">
					<form:input path="providerLastName" placeholder="Last Name" class="validate[required]"/>
				</div>
				<div class="clearfix"></div>

				<div class="small-12 medium-4 large-5 columns">
					<form:input path="providerEmail" placeholder="Email Id" class="validate[required, custom[email]]"/>
						<form:hidden path="practiceInfo.practiceId" value="${providePracticeId}"/>
				</div>

				<div class="small-12 medium-4 large-5 columns">
					<form:select path="radCodes.codeId">
<c:forEach items="${RadCodes}" var="proRole">

<form:option value="${proRole.codeId}" label="${proRole.codeValue}"/>

</c:forEach>
</form:select>
				</div>

				<div class="small-12 medium-4 large-2 columns">
					<input name="submit" type="submit" disabled="disabled" value="Save"
						style="float: right;">
				</div>
			</form:form>
			<!--.add-new-staff-field-->

			<hr>

			<table border="1" cellpadding="0" cellspacing="0" width="100%">
				<tr class="first-row-of-table">
					<td>Name</td>
					<td>Email ID</td>
					<td>Role</td>
					<td>Edit</td>
				</tr>
				<c:forEach items="${ProviderInfoList}" var="provInfo">
					<tr>
						<td><div align="left">${provInfo.providerFirstName}</div></td>
						<td><div align="center">${provInfo.providerEmail}</div></td>
						<td><div align="center">${provInfo.radCodes.codeDesc}</div></td>
						<td><div align="center">
								 <a href="editDoctor.do?provId=${provInfo.providerId}">Edit</a>  
								<!-- <a href="#">Edit</a>  --><br>
								<a href="doctorTiming.do?provId=${provInfo.providerId}">Timing</a>
							</div></td>
					</tr>
				</c:forEach>
			</table>


		</div>
		<!-----#main-content-full------->
	</div>
	<!-----#content right main----------->
</body>
</html>