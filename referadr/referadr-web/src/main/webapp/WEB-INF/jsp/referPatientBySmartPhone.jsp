<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html class="no-js" lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Refer Patient</title>
    
     <script src="js/jquery.1.9.1.min.js"></script>
	<script src="js/vendor/jquery.ui.widget.js"></script>

	<script src="js/page-js/jquery.fileupload.js"></script>
	<script src="js/page-js/update_uploadfunction.js"></script>
	
	
    <link rel="stylesheet" href="css/foundation.css" />
    <link rel="stylesheet" href="css/style.css" />
    <script src="js/vendor/modernizr.js"></script>
	
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="responsive/style.css">
	<link rel="stylesheet" type="text/css" href="responsive/responsive.css">
	
	<style type="text/css">
	input[type="file"] {
  	width: 46%; }
	</style>
	
 <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	
 
 $("#practiceSpectialtyId").autocomplete({
     source : function(request, response) {
      
       var searchName = $("#practiceSpectialtyId")[0];
       var searchValue = searchName.value;
       var chearingHouseId=document.getElementById('chearingHouseId').value
       $.ajax({
    	   url : "searchSplMap.do?searchValue=" + searchValue+"&chID=" + chearingHouseId,
         dataType : "json",
         data : {
           maxRows : 6,
           startsWith : request.term
         },
         success : function(data) {
           response(data);
         }
       });
     }
   });  
   });
</script>

<script type="text/javascript">

function checkPhoneAvaibility(){

   var patientPhone=document.getElementById("patientPhone").value;
	
	
	   $.ajax({
		   url : "checkPhoneAvaibility.do?searchValue=" + patientPhone,
	        type : "GET",
	        contentType : "application/json; charset=utf-8",
	        success : function(t) {
	        
	        	if(t.length==0){

	        		 $('#demo101').append('<div></div>').html(" Phone No. does not exist");
     	            var obj2=document.getElementById("div4");
     	           obj2.style.display="true";
	        	       document.getElementById("patientName").value='';	
	        	       document.getElementById("patientLastName").value='';	
	        	       document.getElementById("pDate").value='';	
	        	       document.getElementById("patientId").value='';
		        	}else{
		        		 var obj2=document.getElementById("div4");
		     	           obj2.style.display="none";
	       document.getElementById("patientName").value=t[0].pateintFirstName;	
	       document.getElementById("patientLastName").value=t[0].pateintLastName;	
	       document.getElementById("pDate").value=t[0].patientDob;	
	       document.getElementById("patientId").value=t[0].patientId;
		        	}
	          },
	        error : function() {
	        	//alert('error');
	        }
	      })
} 

</script>


<script type="text/javascript">
function formatPhoneNumber() {
	
	if(event.keyCode==8){
		return null;
	}
	
	var phoneNumber=document.getElementById("patientPhone").value;
    var rawPhoneNumber = phoneNumber.replace("(", "").replace(")", "").replace(/-/g, "").replace(/ /g, "");
    if (isNaN(rawPhoneNumber)) {
        return null;
    }
    if (rawPhoneNumber.length >3 && rawPhoneNumber.length<6) {
    	document.getElementById("patientPhone").value=rawPhoneNumber.substring(0, 3) + "-" + rawPhoneNumber.substring(3, 6) 
    	
    }
    if (rawPhoneNumber.length >=6) {
    	//alert("ghkasjhd="+ rawPhoneNumber.substring(0, 3) + "-" + rawPhoneNumber.substring(3, 6) + "-" + rawPhoneNumber.substring(6, 10));
    	document.getElementById("patientPhone").value=rawPhoneNumber.substring(0, 3) + "-" + rawPhoneNumber.substring(3, 6) + "-" + rawPhoneNumber.substring(6, 10);
    }
   
}
</script>
	

	
  </head>
  <body >
  <div class="row"> 
    <h1 align="center">Refer A Patient</h1>
 

<div id="form-main-container-of-fancybox" style="margin-left: 14%;" class="small-12 medium-12 large-8 columns">

<div id="float" class="panel">

<form:form id="formID" method="POST" action="saveReferAPatientDraft.do" modelAttribute="PatientReferralInfo" autocomplete="off" name="form"  target="_top"  >
<form:hidden path="practiceInfo.practiceId" id="practiceInfoId" value="${sessionScope.practiceId}"/>
<form:hidden path="providerInfo.providerId" id="providerInfoId" value="${sessionScope.providerId}"/>
<form:hidden path="chInfo.id" id="chearingHouseId" value="${sessionScope.chId}"/>
<form:hidden path="patientInfo.patientId" id="patientId" />

           <label>Patient Information:</label>
					<div id="div3" class="black_content"  style="border:1px solid black;height: 235px;padding: 0px 0px 0px 0px;" >
					
					<p id="demo102" style="color: red"></p>
						
						 <div class="small-12 medium-12 large-4 columns">
								<form:input path="patientInfo.radLocation.locPhone"
									id="patientPhone" placeholder="Phone" pattern="^\d{3}-\d{3}-\d{4}$"  onblur="checkPhoneAvaibility()" onkeyup="formatPhoneNumber()" class="validate[custom[phone]] text-input" required="true"
									 maxlength="15" />
							</div>
						
						<div class="small-12 medium-12 large-4 columns" id="a">
							<!-- onkeyup="stoppedTyping2()" put in all child div -->
							<form:input disabled="true" path="patientInfo.patientFirstName" id="patientName"
								placeholder="Patient First Name" required="true"
								class="validate[required,custom[onlyLetterNumber]] text-input"
								maxlength="160" />

						</div>
						<div class="small-12 medium-12 large-4 columns">
							<form:input disabled="true" path="patientInfo.patientLastName"
								id="patientLastName" placeholder="Patient last Name"
								required="true"
								class="validate[required,custom[onlyLetterNumber]] text-input"
								maxlength="160" />
						</div>

                           <div class="small-12 medium-12 large-4 columns">
							<%--<input type="text" id="dob" placeholder="DOB"
								required="true"
								class="textfield validate[required,custom[date]]" />
							<form:hidden path="patientInfo.patientDob" id="pDate"
								placeholder="DOB" required="true"
								class="validate[required] text-input" />
								 --%>
								<form:input path="patientInfo.patientDob" id="pDate" placeholder="DOB" disabled="true"  class="textfield validate[required,custom[date]]" required="true"/>
								
						</div>
						
						<%-- 	<div class="small-12 medium-6 large-3 columns">
								<form:select path="practiceSpecialty.praticeSplID"
									id="practiceSpectialtyId">
									<form:option value="0" label="Select Specialty" />
								</form:select>
							</div> --%>
						
						<div class="small-12 medium-12 large-4 columns">
								<form:input
									path="practiceSpecialty.praticeSplDesc"
									id="practiceSpectialtyId" placeholder="Spectialty Name"
									 maxlength="160" />
									<!--  class="validate[required] text-input" -->
							</div>
<div class="small-12 medium-12 large-12 columns">
                        <form:textarea
								path="referral_Provider_ActionList[0].providerNotes"
								placeholder="Additional Notes" id="additionalNotes" maxlength="250"/>
								</div>
								
							
							
								 
                      <%--   <div class="small-12 medium-12 large-4 columns">
							<form:select path="patientInfo.patientGender" id="gender"
								>
								<form:option value="G" label="Gender" />
								<form:option value="M" label="Male" />
								<form:option value="F" label="Female" />
							</form:select>
							<form:hidden path="patientInfo.patientGender" id="PGender" />
						</div> --%>
						
						<%--  <div class="small-12 medium-12 large-4 columns">
								<form:input path="patientInfo.patientEmail" placeholder="Email" class="validate[custom[email]]" id="emailId"/>
							</div> --%>
					<%-- 		<div id="padding-0" class="small-12 medium-12 large-12 columns">
							<div class="small-12 medium-12 large-6 columns">
									<label>Is Insurance info available:</label>
									<form:radiobutton
										path="patientInfo.patientInsuranceInfoList[0].patientPre1AuthReq"
										value="Y" id="insuranceyes" checked="checked" onclick="insuranceyess()" />
									<label for="insuranceyes">Yes</label>
									<!-- <input type="radio" name="patientInfo.patientInsuranceInfoList[0].patientPre1AuthReq" value="N" onClick="PreAuth()" checked="checked"/> -->
									<form:radiobutton
										path="patientInfo.patientInsuranceInfoList[0].patientPre1AuthReq"
										value="N" id="insuranceno" onClick="insurancenoo()"
										 />
									<label for="insuranceno">No</label>
								</div>
							</div>
							<div class="small-12 medium-12 large-4 columns">
								<form:input
									path="patientInfo.patientInsuranceInfoList[0].insuranceInfo.insuranceCompany"
									id="insuranceName" placeholder="Insurance Name"
									 maxlength="160" />
									<!--  class="validate[required] text-input" -->
							</div>

							<div class="small-12 medium-12 large-4 columns">
								<form:input
									path="patientInfo.patientInsuranceInfoList[0].patientInsuranceId"
									id="insuranceId" placeholder="Insurance ID"
									class="validate[custom[onlyLetterNumber]] text-input"
									maxlength="50" />
									<!-- class="validate[required,custom[onlyLetterNumber]] text-input" -->
							</div>

							<div class="small-12 medium-12 large-4 columns">
								<form:input
									path="patientInfo.patientInsuranceInfoList[0].patientInsuranceGroup"
									placeholder="GroupID" id="group"
									 maxlength="160" />
									<!-- class="validate[required] text-input" -->
							</div> --%>
							
</div>



<hr>
					<div id="div4">
					<p id="demo101" style="color: red"></p>
						</div>
<div class="buttons-blue-container">
<input type="submit" name="Submit" value="Submit" class="small radius button">
</div>
</form:form>
</div>
</div><!--#form-main-container-of-fancybox-->
    <!-- <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.2.min.js"></script> -->
	<script type="text/javascript" src="responsive/script.js"></script>
   <!--  <script src="js/vendor/jquery.js"></script> -->
    <script src="js/foundation.min.js"></script>
    <script>
      $(document).foundation();
    </script>
  </body>
</html>
