<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Connecting Doctors</title>

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


	<script type="text/javascript">
	   function removeNotes()
	   {
		   document.getElementById('PrvoRederalNotes').value="";
	   }
	
   /*     $(function() { $('#updateFiles').change(function (){
		  	$("#updateFileNames").empty();
    		var files = $('#updateFiles')[0].files;
    		for (var i = 0; i < files.length; i++) {
				var $p = $("<p></p>").text(files[i].name).appendTo("#updateFileNames");
    		}
		});
	   }); */
	   function updateValidation()
	   {
		  
		  var updateValue=document.getElementById('PrvoRederalNotes').value;
		 
		  if (updateValue==null || updateValue==""){
				$('#demo10').append('<div></div>').html("Please fill the required fields.");
      	var obj10=document.getElementById("warningmsg");
      	obj10.style.display="true"; 
			  return false;
		  }
		  else
			  return true;
	   }
	
	</script>
</head>
<body onload="removeNotes()">
              <div id="content-right-main" class="small-12 medium-12 large-10 columns">
                  <div id="main-content-full" class="small-12 medium-12 large-12 columns">
					<h1 class="red-heading">Update Records</h1>
				<div id="warningmsg" style="margin-left: 2%">
					 <p id="demo10" style="color: red"></p>
					</div>
                      <form:form class="refer-patient" id="formID" method="POST" action="updateAdditionalNotes.do" modelAttribute="Referral_Provider_Action" autocomplete="off" name="form" target="_top" enctype="multipart/form-data" >
                     
                          <div class="small-12 medium-12 large-Fefer-to-2">
                              
                                <form:hidden path="refProviderActionId" value="${providerActionId}"/>
								<form:hidden path="patientReferralInfo.refId" value="${patientReferralId}"/>
								<form:hidden path="providerInfo.providerId" value="${Referral_Provider_Action.patientReferralInfo.providerInfo.providerId}"/>
								<form:hidden path="patientReferralInfo.chInfo.id" value="${Referral_Provider_Action.patientReferralInfo.chInfo.id}"/>
                              <div id="cliniacal" class="small-12 medium-6 large-6 columns">
                                  <form:textarea path="providerNotes" placeholder="Additional Notes" id="PrvoRederalNotes" />
                              </div>

							  
                                <div id="upload-file" class="small-12 medium-6 large-6 columns">
                                  <div class="small-12 medium-12 large-12 columns no-padding">
									<div class="small-12 medium-12 large-12 columns no-padding">
                                  <input type="file" name="updatefiles" class="file" multiple>
									  <div class="input-group custom-upload-button">
										<span class="input-group-addon"><i class="glyphicon glyphicon-picture"></i></span>
										<input type="text" class="form-control" disabled placeholder="Upload File">
										<span class="input-group-btn">
										  <button class="browse btn btn-primary" type="button"><i class="fa fa-search"></i> Browse</button>
										</span>
									  </div>
                                  </div>

                                  </div>
                              </div><!-----uplload file------>
							  <div class="clearfix"></div>
                              <div class="clear-both"></div>
                                <div id="submit-button" class="small-12 medium-6 large-2 large-offset-10 columns">
                                  <div class="small-12 medium-12 large-12 columns no-padding">
                                    <input name="submit" type="submit" value="Submit" onclick="return updateValidation()" style="float: right;" />
                                  </div>
                              </div><!-----Submit Button------>
                          </div><!----Fefer-to-2---->
                          
                          
                      </form:form><!----refer-patient----->
                  </div><!-----#main-content-full------->
              </div><!-----#content right main----------->
</body>
</html>