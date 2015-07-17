<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Practice Information</title>


<link rel="stylesheet" href="cssNEW/foundation.css" />
<link rel="stylesheet" href="cssNEW/style.css" />

<!-- <script src="js/jqueryNew.js"></script> -->

<script src="js/vendor/jquery.ui.widget.js"></script>
<script src="js/jquery.1.9.1.min.js"></script>
<!-- <script src="js/page-js/jquery.fileupload.js"></script>
 <script src="js/page-js/myuploadfunction.js"></script>
<script src="js/page-js/refer_myuploadfunction.js"></script>  -->
<script type="text/javascript" src="js/jsp-js/referPatientMain.js"></script>
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



</head>
<body>
              <div id="content-right-main" class="small-12 medium-12 large-10 columns">
                  <div id="main-content-full" class="small-12 medium-12 large-12 columns">
						<div class="clearing-house-container">
					
							 <form:form method="POST" class="clearing-house-form" action="updatePracticeInfo.do" modelAttribute="practiceInfo">
							<div class="small-12 medium-12 large-6 columns no-padding">
								<div class="small-12 medium-12 large-12 columns">
									<label>Name</label>
									 <form:input path="practiceName" placeholder="Name" value="${practiceInfo.practiceName}" readonly="true" />
								</div>
								
								 <form:hidden path="practiceId" value="${practiceInfo.practiceId}"/>
 <form:hidden path="practiceLocations.precticeLocationId" value="${practiceInfo.practiceLocations.precticeLocationId}"/>
 <form:hidden path="practiceLocations.radLocation.locId" value="${practiceInfo.practiceLocations.radLocation.locId}"/>
								
								
								
								
								<div class="small-12 medium-12 large-12 columns">
									<label>Description</label>
									<input name="" type="text" value="" placeholder="Description"></input>
								</div>
								<div class="clearfix"></div>
								
								<div class="small-12 medium-12 large-12 columns">
									<label>Address 1</label>
									<form:input placeholder="Address 1" path="practiceLocations.radLocation.locAddress1" value="${practiceInfo.practiceLocations.radLocation.locAddress1}"/>
								</div>
								
								<div class="small-12 medium-12 large-12 columns">
									<label>Address 2</label>
									
								<form:input placeholder="Address 2" path="practiceLocations.radLocation.locAddress2" value="${practiceInfo.practiceLocations.radLocation.locAddress2}"/>
								</div>
								<div class="clearfix"></div>
							</div>
							<div class="small-12 medium-12 large-6 columns no-padding">
							
							<div class="small-12 medium-12 large-12 columns no-padding">
									  <input type="file" name="" class="file">
										  <div class="input-group custom-upload-button">
											<span class="input-group-addon"><i class="glyphicon glyphicon-picture"></i></span>
											<img src="images/gravatar.jpg" width="254">
											<input type="text" class="form-control" disabled="" placeholder="Upload File">
											<span class="input-group-btn">
											  <button class="browse btn btn-primary" type="button"><i class="fa fa-search"></i> Browse</button>
											</span>
										  </div>
									  </div>
							
							</div>
							<div class="clearfix"></div>
								<div class="small-12 medium-4 large-4 columns">
									<label>City</label>
									
									 <form:input  placeholder="City" path="practiceLocations.radLocation.locCity" value="${practiceInfo.practiceLocations.radLocation.locCity}"/>
								</div>
								
								<div class="small-12 medium-4 large-4 columns">
									<label>State</label>
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
								
								<div class="small-12 medium-4 large-4 columns">
									<label>Zip</label>
									 <form:input placeholder="Zip" path="practiceLocations.radLocation.locZip" value="${practiceInfo.practiceLocations.radLocation.locZip}"/>
								</div>
								<div class="clearfix"></div>
								
								<div class="small-12 medium-4 large-4 columns">
									<label>Phone</label>
									
							<form:input placeholder="Phone" path="practiceLocations.radLocation.locPhone" value="${practiceInfo.practiceLocations.radLocation.locPhone}"/>
								</div>
								
								<div class="small-12 medium-4 large-4 columns">
									<label>Fax</label>
									 <form:input placeholder="Fax" path="practiceLocations.radLocation.locFax" value="${practiceInfo.practiceLocations.radLocation.locFax}"/>
									
								</div>
								
								<div class="small-12 medium-4 large-4 columns">
									<label>URL</label>
									<form:input  placeholder="www.url.com" path="practiceLocations.radLocation.locWebsite" value="${practiceInfo.practiceLocations.radLocation.locWebsite}"/>
									
								</div>
								<div class="clearfix"></div>
								
								<div class="small-12 medium-12 large-2 large-offset-10 columns">
								 <c:if test="${sessionScope.clearingHouseId==null}">
									<button type="submit" class="btn btn-default alignright" data-dismiss="modal">Submit</button>
								</c:if>
								</div>
							</form:form><!--.clearing-house-form-->
						</div><!--.clearing-house-container-->
					                
                  </div><!-----#main-content-full------->
              </div><!-----#content right main----------->
</body>
</html>