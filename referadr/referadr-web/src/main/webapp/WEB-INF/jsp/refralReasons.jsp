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
    <title>Connecting Doctors</title>
    
     <script src="js/jquery.1.9.1.min.js"></script>
	<script src="js/vendor/jquery.ui.widget.js"></script>

	<script src="js/page-js/jquery.fileupload.js"></script>
	<script src="js/page-js/patientName_uploadfunction.js"></script>
	
	
	
<script src="jQuery/js/languages/jquery.validationEngine-en.js" type="text/javascript" charset="utf-8"></script>
<script src="jQuery/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
	<link rel="stylesheet" href="jQuery/css/validationEngine.jquery.css"
	type="text/css" />
	
	
    <link rel="stylesheet" href="css/foundation.css" />
    <link rel="stylesheet" href="css/style.css" />
    <script src="js/vendor/modernizr.js"></script>
	<!-- <script src="js/jquery.1.9.1.min.js"></script> -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="responsive/style.css">
	<link rel="stylesheet" type="text/css" href="responsive/responsive.css">
	<script type="text/javascript" src="js/jsp-js/referPatient.js"></script>
	
	<style type="text/css">
		input[type="file"] {width: 36%; }
	</style>
	<script type="text/javascript">
	
	
	
	
	//////////////////
	
	$(function() {
		jQuery(document).ready(function() {
			$("#formID").validationEngine();
		});
	});
	
	
	$(document).ready(function() {
		
	    
	         
	         
	         
		var obj4 = document.getElementById("providerdropdowm");
		obj4.style.display = "none";
		/////////////////
		
		/* var provider="${providerSize}"; */
		  var clearHouselistSize="${clearHouselistSize}";
		  /*    if(provider==1){
		      document.getElementById('referring').value="${providerId}";
		     }else{
		      document.getElementById('referring').value=0;
		     } */
		     if(clearHouselistSize==1){
		      document.getElementById('chearingHouseId').value="${chid}";
		      
		    //  document.getElementById('practiceSpectialtyId').value="${practiceSpecialtyId}";
		      
		      var chearingHouseId = $("#chearingHouseId").val();
		        if(chearingHouseId!=0){
		        $("#practiceSpectialtyId").text(""), $("#practiceSpectialtyId").append($("<option value='0'></option>").text("Select"));
		        var chearingHouseId = $("#chearingHouseId").val();
		        $.ajax({
		          url : "practiceSpecialtyBychearingHouse.do?chearingHouseId=" + chearingHouseId,
		          type : "GET",
		          contentType : "application/json; charset=utf-8",
		          success : function(t) {
		            var e = t.practiceSpecialtyList;
		            var practiceSpecialityValue=t.practiceSpecialtyList[0];
		            for (i = 0; i < e.length; i++){
		            	var spl=e[i]
		                          
		              $("#practiceSpectialtyId").append($("<option value='"+ spl.praticeSplID+ "'></option>").text(spl.praticeSplDesc))
		            }
		           
		            if(e.length==1){
		           
		            document.getElementById('practiceSpectialtyId').value=practiceSpecialityValue.praticeSplID;
		            //here we are puting ajax code for practice dropdown
		            }
		            
		            
		            
		            
		            /////////////////////////3rd combo
		            var practiceSpectialtyId = $("#practiceSpectialtyId").val();
		            var chearingHouseId = $("#chearingHouseId").val();
		            if(practiceSpectialtyId!=0){
		            $("#practiceInfoId").text(""), $("#practiceInfoId").append($("<option value='0'></option>").text("Select"));
		            $.ajax({
		              url : "PracticeInfoBypracticeSpecialty.do?practiceSpectialtyId=" +practiceSpectialtyId+"&chearingHouseId="+chearingHouseId,
		              type : "GET",
		              contentType : "application/json; charset=utf-8",
		              success : function(t) {
		                var e = t.practiceInfoList;
		                var practiceInfoValue=t.practiceInfoList[0];
		                
		                for (i = 0; i < e.length; i++){
		                	var practiceInfo=e[i];
		                  $("#practiceInfoId").append($("<option value='"+ practiceInfo.practiceId+ "'></option>").text(practiceInfo.practiceName))
		                }
		               if(e.length==1){
		 		           
				            document.getElementById('practiceInfoId').value=practiceInfoValue.practiceId;
				            //here we are puting ajax code for practice dropdown
				            }  
		               ////////////////////////////end
		               
		               
		               
		               
		               
		               /////////////4th combo
		             
		          var practiceInfoId = $("#practiceInfoId").val();
		               if(practiceInfoId!=0){
		               $("#providerInfoId").text(""), $("#providerInfoId").append($("<option value='0'></option>").text("Select"));
		               $.ajax({
		                 url : "providerInfoByPracticeInfo.do?practiceInfoId=" +practiceInfoId,
		                 type : "GET",
		                 contentType : "application/json; charset=utf-8",
		                 success : function(t) {
		                   var e = t.practiceInfoList;
		                   var providerInfoValue=t.practiceInfoList[0];
		                   
		                   for (i = 0; i < e.length; i++){
		                   	var pracInfo=e[i]
		                     $("#providerInfoId").append($("<option value='"+ pracInfo.providerId+ "'></option>").text(pracInfo.providerFirstName))
		                   }
		                   if(e.length==1){
			 		           
					            document.getElementById('providerInfoId').value=providerInfoValue.providerId;
					            //here we are puting ajax code for practice dropdown
					            }  
		                   /////////////////4th end
		                   
		                   
		                   
		 
		                   },
		                 error : function() {
		                 }
		               })
		               }else{
		                 $("#providerInfoId").text(""), $("#providerInfoId").append($("<option value='0'></option>").text("Select"));
		               } 
		                },
		              error : function() {
		              }
		            })
		            }else{
		              $("#practiceInfoId").text(""), $("#practiceInfoId").append($("<option value='0'></option>").text("Select"));
		            }
		          },
		          error : function() {
		          }
		        })
		        }else{
		          $("#practiceSpectialtyId").text(""), $("#practiceSpectialtyId").append($("<option value='0'></option>").text("Select"));
		        }
		      
		     }
		     else{
		      document.getElementById('chearingHouseId').value=0;
		     }
		////////////////
	});
	//////////////////////
	
	
	
	
	/* function showProviderDropDown()
	{ 
		var providerID=document.getElementById("providerID").value;
		
		if(providerID==0){
		var obj4 = document.getElementById("providerdropdowm");
		obj4.style.display = "block";
		return false;	
		}else
			{
			return true;
			}
		
	} */
	function fillProviderId()
	{
		//var providerID="${providerId}";
		
		document.getElementById("providerID").value= document.getElementById("refproviderId").value;
		
	}
	
	
	     $(function() { $('#patientNameFiles').change(function (){
		  	$("#patientNameFileNames").empty();
    		var files = $('#patientNameFiles')[0].files;
    		for (var i = 0; i < files.length; i++) {
				var $p = $("<p></p>").text(files[i].name).appendTo("#patientNameFileNames");
    		}
		});
	   });
</script>

  </head>
  <body >
  
  <div class="row"> 
  <!--  <div id="secondary-menu" class="small-12 medium-12 large-12 columns">
   <ul>
    <li><a href="PracticeInfo.do">PRACTICE</a></li>
    <li><a href="doctor.do">DOCTORS</a></li>
    <li><a href="#">INSURANCE</a></li>
    <li><a href="#">BILLING</a></li>
   </ul>
  </div> -->
<div id="form-main-container-of-fancybox" style="margin-left:14%;" class="small-12 medium-12 large-8 columns">
<div id="float" class="panel">
<h4 class="text-align-center">Referral Form</h4>
<div id="warningmsg">

<p id="demo10" style="color: red"></p>
</div>
<form:form id="formID" method="POST" action="updateRefferal.do?fromProviderId=${fromProviderId}" modelAttribute="PatientReferralInfo" autocomplete="off" enctype="multipart/form-data" onsubmit="return showProviderDropDown()">
<form:hidden path="curStatusId" value="${referCurrentStatusId}" />
<form:hidden path="refId" value="${patientReferralId}" />
<input type="hidden" name='refprid' id='providerID' value='${providerId}' />
<div id="all">
     <div class="small-12 medium-12 large-12 columns" id='providerdropdowm'>
     	Please select referral provider<form:select path="proId" id="refproviderId" readonly="true" onchange="fillProviderId()">
								<form:option value="0" label="Select" />
								<c:forEach items="${ProviderInfo}" var="refProviderInfo">
									<form:option value="${refProviderInfo.providerId}"
										label="${refProviderInfo.providerFirstName}" />
								</c:forEach>
							</form:select>
						
						
						
						</div>
</div>

<div class="small-12 medium-12 large-12 columns">
   <form:textarea path="referral_Provider_ActionList[0].providerRefReasons" placeholder="Referal Reason" id="PrvoRederalReason" class="validate[required]" required="true" />
</div>
<div class="small-12 medium-12 large-12 columns">
 <form:textarea path="referral_Provider_ActionList[0].providerDiagCode" placeholder="Diagnosis Code" id="PrvoDiagosis" />
</div>
<div class="small-12 medium-12 large-12 columns">
 <form:textarea path="referral_Provider_ActionList[0].providerNotes" placeholder="Additional Notes" id="additionalNotes" />
</div>
<div class="buttons-blue-container">

</div>	
<div class="row"></div><!--DO NOT DELETE THIS ROW-->
<div class="small-12 medium-6 large-3 columns">
   <form:select path="chInfo.id" id="chearingHouseId">
         <form:option value="0" label="Select" />
         <c:forEach items="${chlearingHouseList}" var="refCh">
          <form:option value="${refCh.id}" label="${refCh.name}" />
         </c:forEach>
        </form:select>
</div><!---small-12 medium-6 large-3 columns---->
<div class="small-12 medium-6 large-3 columns">
  <form:select path="practiceSpecialty.praticeSplID" id="practiceSpectialtyId">
         <form:option value="0" label="Select" />
        </form:select>
</div><!---small-12 medium-6 large-3 columns---->
<div class="small-12 medium-6 large-3 columns">
<form:select path="practiceInfo.practiceId" id="practiceInfoId">
         <form:option value="0" label="Select" />
        </form:select>
</div><!---small-12 medium-6 large-3 columns---->
<div class="small-12 medium-6 large-3 columns">
 <form:select path="providerInfo.providerId" id="providerInfoId">
         <form:option value="0" label="Select" />
        </form:select>
</div><!---small-12 medium-6 large-3 columns---->

<input id="patientNameFiles" size="30" accept="image/*, .pdf,.doc,.docx,.txt,.xls,.xlsx, .ppt" type="file" name="files"  class="small radius button"
									multiple>
<span id="patientNameFileNames">No Files Selected</span>

<div class="buttons-blue-container">
<!-- <a href="#"><input type="button" name="Attach docs" value="Refer More" class="medium secondary button"></a> -->
</div>
<div class="row"></div><!--DO NOT DELETE THIS ROW-->
<hr>
<div class="buttons-blue-container">
<input type="submit" name="Submit" value="Submit" onclick="return Refralreasonvalidation()" class="small radius button" >
</div>
</form:form>

</div>
</div><!--#form-main-container-of-fancybox-->
   <!--  <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.2.min.js"></script> -->
	<script type="text/javascript" src="responsive/script.js"></script>
    <!-- <script src="js/vendor/jquery.js"></script> -->
    <script src="js/foundation.min.js"></script>
    <script>
      $(document).foundation();
    </script>
  </body>
</html>
