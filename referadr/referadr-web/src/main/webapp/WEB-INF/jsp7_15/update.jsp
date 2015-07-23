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
<title>Insert title here</title>
<script src="jQuery/js/languages/jquery.validationEngine-en.js"
	type="text/javascript" charset="utf-8"></script>
<script src="jQuery/js/jquery.validationEngine.js"
	type="text/javascript" charset="utf-8"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script src="js/vendor/jquery.ui.widget.js"></script>
<script src="js/jquery.1.9.1.min.js"></script>
<script type="text/javascript" src="js/jsp-js/referPatientMain.js"></script>
<link rel="stylesheet" href="css7_15/style.css" />



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

					
						<div id="content-inner-main-right" class="small-12 medium-9 large-9 columns">
							<div class="main-content-inside">
								<div id="breadcrumbs-container" class="small-12 medium-12 large-12 columns">
									<ul class="breadcrumbs">
									  <li><a href="#">Referrals</a></li>
									  <li class="breadcrum-icon"><i class="fa fa-play"></i></li>
									  <li><a href="inbound.do">Inbound</a></li>
                                      <li class="breadcrum-icon"><i class="fa fa-play"></i></li>
                                      <li><a href="updateRefralView.do">Update Records</a></li>
									</ul>
								</div><!--#breadcrumbs-container-->
								<div class="panel callout">
								
								<div id="warningmsg" style="margin-left: 2%">
					 <p id="demo10" style="color: red"></p>
					</div>
								
								<form:form  id="formID" method="POST" action="updateAdditionalNotes.do" modelAttribute="Referral_Provider_Action" autocomplete="off" name="form" target="_top" enctype="multipart/form-data" class="refer-page-form">
									
									            <form:hidden path="refProviderActionId" value="${providerActionId}"/>
								<form:hidden path="patientReferralInfo.refId" value="${patientReferralId}"/>
								<form:hidden path="providerInfo.providerId" value="${Referral_Provider_Action.patientReferralInfo.providerInfo.providerId}"/>
								<form:hidden path="patientReferralInfo.chInfo.id" value="${Referral_Provider_Action.patientReferralInfo.chInfo.id}"/>
									<div id="cliniacal"class="small-12 medium-6 large-6 columns">
										 <form:textarea path="providerNotes" placeholder="Additional Notes" id="PrvoRederalNotes" />
									</div>
									
									<div class="small-12 medium-6 large-6 columns">
								
										 <input type="file" name="updatefiles"  id="fileToUpload" class="file" multiple>
									</div>
									
									<div class="clearfix"></div>
									
									<div id="submit-button"  class="small-12 medium-12 large-12 columns">
										<input  name="submit" type="Submit" value="Submit"  onclick="return updateValidation()" class="button alignright">
										
									</div>
									</form:form>
								<!--.refer-a-patient-page-form-->
									
									
									
									
								</div><!--.panel callout-->
							</div><!--.main-content-inside-->
						</div><!--#content-inner-main-right-->

</body>
</html>