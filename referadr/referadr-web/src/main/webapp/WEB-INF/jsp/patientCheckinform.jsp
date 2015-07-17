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
    <title>Patient Checkin</title>
    
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
function hideshow(){
	var flag=document.getElementById("tandcFlag").value;
	if(flag=='hide'){
	var hidetc= document.getElementById("TandC");
	hidetc.style.display = "block";
	document.getElementById("tandcFlag").value='show';
	}
	if(flag=='show'){
		var hidetc= document.getElementById("TandC");
		hidetc.style.display = "none";
		document.getElementById("tandcFlag").value='hide';
		}
}

</script>	

     <script type="text/javascript">

$(document).ready(function() {
 
	var hidetandc= document.getElementById("TandC");
	hidetandc.style.display = "none";
	
 $("#insuranceName").autocomplete({
     source : function(request, response) {
      
       var searchName = $("#insuranceName")[0];
       var searchValue = searchName.value;
       var practiceID=document.getElementById('practiceInfoId').value
       $.ajax({
         url : "searchMap.do?searchValue=" + searchValue+"&practiceID="+practiceID,
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
  function insuranceyess(){
	 
	  var insuranceyes = document.getElementById("insuranceyes").checked;
	  if(insuranceyes){
			 var insuranceName = document.getElementById("insuranceName");
			 insuranceName.style.display = "block";

			 var group = document.getElementById("group");
			 group.style.display = "block";

			 var insuranceId = document.getElementById("insuranceId");
			 insuranceId.style.display = "block";
		  }
	  }
  function insurancenoo(){
	  var insuranceno = document.getElementById("insuranceno").checked;
	  if(insuranceno){
		  document.getElementById("insuranceName").value='';
			 var insuranceName = document.getElementById("insuranceName");
			 insuranceName.style.display = "none";
			 document.getElementById("group").value='';
			 var group = document.getElementById("group");
			 group.style.display = "none";
			 document.getElementById("insuranceId").value='';
			 var insuranceId = document.getElementById("insuranceId");
			 insuranceId.style.display = "none";
		  }
	
	  }

  </script>



<script type="text/javascript">

function checkPhoneAvaibility(){
 
   var patientPhone=document.getElementById("patientPhone").value;
	
	
	   $.ajax({
		   url : "checkPhoneAvaibility.do?searchValue=" + patientPhone,
	        type : "GET",
	        contentType : "application/json; charset=utf-8",
	        success : function(t) {
	        	
	       document.getElementById("patientName").value=t[0].pateintFirstName;	
	       document.getElementById("patientLastName").value=t[0].pateintLastName;	
	       document.getElementById("pDate").value=t[0].patientDob;	
	       document.getElementById("gender").value=t[0].patientGender;	
	       document.getElementById("emailId").value=t[0].pateintEmail;	
	       document.getElementById("insuranceName").value=t[0].insuranceCompany;	
	       document.getElementById("insuranceId").value=t[0].insuranceId;	
	       document.getElementById("group").value=t[0].patientInsuranceGroup;
	       document.getElementById("patientId").value=t[0].patientId;
	       	
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
	
	<script type="text/javascript">
function checkFields(){
	
if(document.getElementById("insuranceyes").checked){
	if(document.getElementById("insuranceName").value==''){
return false;
		}
	return true;
}
else{
	return true;
}



	
	//document.getElementById("insuranceyes").checked;
	// document.getElementById("insuranceno").checked;
	alert("hi");
	return false;
}
	</script>
	
	
	<script type="text/javascript">
	function formatDate() {
		
		if(event.keyCode==8){
			return null;
		}
		
		var dob=document.getElementById("dob").value;
	    var rawdob = dob.replace("(", "").replace(")", "").replace("/", "").replace(/ /g, "");
	    if (isNaN(rawdob)) {
	        return null;
	    }
	    if (rawdob.length >2 && rawdob.length<4) {
	    	document.getElementById("dob").value=rawdob.substring(0, 2) + "/" + rawdob.substring(2, 4) 
	    	
	    }
	    if (rawdob.length >=4) {
	    	//alert("ghkasjhd="+ rawPhoneNumber.substring(0, 3) + "-" + rawPhoneNumber.substring(3, 6) + "-" + rawPhoneNumber.substring(6, 10));
	    	document.getElementById("dob").value=rawdob.substring(0, 2) + "/" + rawdob.substring(2, 4) + "/" + rawdob.substring(4, 8);
	    }
	   
	}
	</script>
	
	
  </head>
  <body >
  <div class="row"> 
    <h1 align="center">Patient Check in</h1>
 

<div id="form-main-container-of-fancybox" style="margin-left: 14%;" class="small-12 medium-12 large-8 columns">

<div id="float" class="panel">

<form:form id="formID" method="POST" action="savePatientCheckin.do" modelAttribute="PatientReferralInfo" autocomplete="off" name="form"  target="_top"  >
<form:hidden path="practiceInfo.practiceId" id="practiceInfoId" value="${practiceId}"/>
<form:hidden path="patientInfo.patientId" id="patientId" />

           <label>Patient Information:</label>
					<div id="div3" class="black_content"  style="border:1px solid black;height: 235px;padding: 0px 0px 0px 0px;" >
					<p id="demo101" style="color: red"></p>
						
						 <div class="small-12 medium-12 large-4 columns">
								<form:input path="patientInfo.radLocation.locPhone"
									id="patientPhone" placeholder="Phone" pattern="^\d{3}-\d{3}-\d{4}$"  onblur="checkPhoneAvaibility()" onkeyup="formatPhoneNumber()" class="validate[custom[phone]] text-input" required="true"
									 maxlength="15" />
							</div>
						
						<div class="small-12 medium-12 large-4 columns" id="a">
							<!-- onkeyup="stoppedTyping2()" put in all child div -->
							<form:input path="patientInfo.patientFirstName" id="patientName"
								placeholder="Patient First Name" required="true"
								class="validate[required,custom[onlyLetterNumber]] text-input"
								maxlength="160" />

						</div>
						<div class="small-12 medium-12 large-4 columns">
							<form:input path="patientInfo.patientLastName"
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
								<form:input path="patientInfo.patientDob" id="pDate" placeholder="DOB"  onkeyup="formatDate()" maxlength="10" class="textfield validate[required,custom[date]]" required="true"/>
								
						</div>

                        
                        <div class="small-12 medium-12 large-4 columns">
							<form:select path="patientInfo.patientGender" id="gender"
								>
								<form:option value="G" label="Gender" />
								<form:option value="M" label="Male" />
								<form:option value="F" label="Female" />
							</form:select>
							<form:hidden path="patientInfo.patientGender" id="PGender" />
						</div>
						
						 <div class="small-12 medium-12 large-4 columns">
								<form:input path="patientInfo.patientEmail" placeholder="Email" class="validate[custom[email]]" id="emailId"/>
							</div>
							<div id="padding-0" class="small-12 medium-12 large-12 columns">
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
							</div>
							
</div>

	<div class="small-12 medium-12 large-6 columns">
								 <input type="checkbox" name="schedulFlag" onclick="" checked="checked" id="checkbox" /> I read and accept all
								 <a style="font-size: 13px" onclick="hideshow()" href="#">T&C</a>
								 </div>
								 
								 <div class="small-12 medium-12 large-12 columns" id="TandC">
								 <input type="hidden" id="tandcFlag" value="hide"/>
								<br>
								 dasdaddad fdfdf
								 sdaddf dfdff dfdfdfdf dfdfdffd vdfdfdf
								 assdas dfdfdfdfdf
								 dasdasdadad
								 asdddfdfdf dfdffd dfdfff
								 
								 
								 </div>

<hr>
<div class="buttons-blue-container">
<input type="submit" name="Submit" onclick="return checkFields()" value="Submit" class="small radius button">
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
