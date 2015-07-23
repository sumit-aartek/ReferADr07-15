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
<script type="text/javascript" src="js/jsp-js/referPatient.js"></script>
  
  <script type="text/javascript">
	$(function() {
		jQuery(document).ready(function() {
			$("#formID").validationEngine();
		});
	});
	
	$(document).ready(function() {
		var obj4 = document.getElementById("providerdropdowm");
		obj4.style.display = "none";
		  var clearHouselistSize="${clearHouselistSize}";
		     if(clearHouselistSize==1){
			    
		      document.getElementById('chearingHouseId').value="${chid}";
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
		            }
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
				            }  
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
		                	   document.getElementById("callScheduleid").disabled = false;
		                	   document.getElementById("scheduleDate").value='';
			    	        	document.getElementById("scheduleTime").value='';
			    	        	document.getElementById("scheduleNotes").value='';
			    	        	
			    	        	document.getElementById("datetimepicker").value='';
			    	        	document.getElementById("timeselect").value='';
			    	        	document.getElementById("sheduleNotes").value='';
			    	        	document.getElementById("scheduleFlag").value='false';
					            document.getElementById('providerInfoId').value=providerInfoValue.providerId;
					            }  
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
	});

	function fillProviderId()
	{
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

<script type="text/javascript">
function closeschedulediv(){
	var schedulefull = document.getElementById("shaduleModal");
	schedulefull.style.display = "none";
 }

function dateselected(){
	
	var Date=document.getElementById("datetimepicker").value;
	var provID=document.getElementById("providerInfoId").value;
	//alert('hi='+Date);
	   $.ajax({
	        url : "getProviderTimeSlots.do?practiceInfoId="+provID+"&scheduleDate="+Date,
	        type : "GET",
	        contentType : "application/json; charset=utf-8",
	        success : function(t) {
	      // alert('success');
	        	var timinglist=t.timings;
	        	//alert(timinglist);
	        	
	        	/*var select = document.getElementById("timeselect");
	        	var length = select.options.length;
	        	alert(length);
	        	for (i = 0; i < length; i++) {
	        	  select.options[i] = null;
	        	}*/
	        	
	        	$('#timeselect').empty();
	        	
	        	 for (var cur = 0; cur < timinglist.length; cur++){
	        	
	        	$("#timeselect").append($("<option value='"+timinglist[cur]+"'></option>").text(timinglist[cur]));
	        	
	        	 }
	        	 
	        	
	        	
	          },
	        error : function() {
	        	//alert('error');
	        }
	      })
	
}
function saveShedule(){
	//alert('hi');
	var Date=document.getElementById("datetimepicker").value;
	var Time=document.getElementById("timeselect").value;
	var notes=document.getElementById("sheduleNotes").value;
	//alert('hi'+Date+Time+notes);
	
	if(Date==''||Time==''||notes==''){
		//alert('hi false'+Date+Time+notes);
		return false;
	}
	
	document.getElementById("scheduleDate").value=Date;
	document.getElementById("scheduleTime").value=Time;
	document.getElementById("scheduleNotes").value=notes;
	document.getElementById("scheduleFlag").value='true';
	
	document.getElementById("datetimepicker").value='';
	document.getElementById("timeselect").value='';
	document.getElementById("sheduleNotes").value='';
	
	var schedulefull = document.getElementById("shaduleModal");
	schedulefull.style.display = "none";

	
	return true;
}

</script>



<script type="text/javascript">
function defaultReferralReason(){
	

	var PrvoRederalReason = document.getElementById('PrvoRederalReason').value;
if(PrvoRederalReason==''){
	document.getElementById('PrvoRederalReason').value="Evaluate & Treat.";
}
	////alert("hi"+PrvoRederalReason);
return true;
	}
</script>
<script type="text/javascript">

function callSchedule(){
	//alert('hi');
	var schedulefull = document.getElementById("shaduleModal");
	schedulefull.style.display = "block";
	var provID=document.getElementById("providerInfoId").value;
	//alert('hi=>'+providerInfoId);
    $.ajax({
        url : "getproviderDayOffDetails.do?practiceInfoId="+provID,
        type : "GET",
        contentType : "application/json; charset=utf-8",
        success : function(t) {
        var e=t.fullAddressByAddress1;
        for(var i=0;i<7;i++){
        // alert('success'+e[i].dayID);
        // alert('is off=>'+e[i].checkBox);
         if(e[i].checkBox){
         document.getElementById("day"+e[i].dayID).checked=false;
         }else{
        	 document.getElementById("day"+e[i].dayID).checked=true;
         }
         
        }
          },
        error : function() {
        }
      })
	
}
</script>


</head>
<body>
			<div id="content-inner-main-right" class="small-12 medium-9 large-9 columns">
							<div class="main-content-inside">
								<div id="breadcrumbs-container" class="small-12 medium-12 large-12 columns">
									<ul class="breadcrumbs">
									  <li><a href="#">Referrals</a></li>
									  <li class="breadcrum-icon"><i class="fa fa-play"></i></li>
									  <li><a href="inbound.do">Inbound</a></li>
                                      <li class="breadcrum-icon"><i class="fa fa-play"></i></li>
                                      <li><a href="refralReasonsView.do">Refer</a></li>
									</ul>
								</div><!--#breadcrumbs-container-->
								
								
								<div class="panel callout">
								
								
								<div id="warningmsg">

										<p id="demo10" style="color: red"></p>
										</div>
								
								
								<form:form id="formID" method="POST"  class="refer-page-form" action="updateRefferal.do?fromProviderId=${fromProviderId}" modelAttribute="PatientReferralInfo" autocomplete="off" enctype="multipart/form-data" onsubmit="return showProviderDropDown()">
                                         <form:hidden path="curStatusId" value="${referCurrentStatusId}" />
                                          <form:hidden path="refId" value="${patientReferralId}" />
                                          <input type="hidden" name='refprid' id='providerID' value='${providerId}' />
                                    <div id="all">
   											  <div class="small-12 medium-6 large-6 columns" id='providerdropdowm'>
 										Please select referral provider<form:select path="proId" id="refproviderId" readonly="true" onchange="fillProviderId()">
								<form:option value="0" label="Select" />
								<c:forEach items="${ProviderInfo}" var="refProviderInfo">
									<form:option value="${refProviderInfo.providerId}"
										label="${refProviderInfo.providerFirstName}" />
								</c:forEach>
							</form:select>
						
						
						
						</div>
</div>	
					                     <div id="drops" style="display: none;" class="small-12 medium-6 large-3 columns custom-sawateen">
                              <form:select path="chInfo.id" id="chearingHouseId">
         <form:option value="0" label="Select" />
         <c:forEach items="${chlearingHouseList}" var="refCh">
          <form:option value="${refCh.id}" label="${refCh.name}" />
         </c:forEach>
        </form:select>
                              </div>			
								
								
								
									<div class="small-12 medium-6 large-6 columns">
									<form:select path="practiceSpecialty.praticeSplID" id="practiceSpectialtyId">
    										  <form:option value="0" label="Select" />
        						 	</form:select>
									</div>
                                    <div class="small-12 medium-6 large-6 columns">
										<form:select path="practiceInfo.practiceId" id="practiceInfoId">
                                             <form:option value="0" label="Select" />
                                         </form:select>
									</div>
									   <div class="small-12 medium-6 large-6 columns">
	                        <form:select path="providerInfo.providerId" id="providerInfoId">
       							  <form:option value="0" label="Select" />
       						 </form:select>
									</div>
									
                                    <div id="input-1" class="small-12 medium-6 large-6 columns">
										<input  type="button" onclick="callSchedule()" id="callScheduleid" value="submit" class="button alignright" >
									</div>
									 
									 
									 
								<div class="modal fade" id="shaduleModal" tabindex="-1"
					role="dialog" aria-labelledby="exampleModalLabel"
					aria-hidden="true" style="display: none;">
					<div class="modal-dialog">
						<div class="modal-content large-12 columns">
							<div class="modal-header">
								<button onclick="closeschedulediv()" type="button" class="close"
									data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true"><img class="close"
										src="bootstrap/alert_boxes_close_ico.png"></span>
								</button>
								<h4 class="modal-title" id="exampleModalLabel">Schedule</h4>
							</div>
							<div id="mailsent" class="modal-body">
								<form>
									<div class="form-group">
										<input type="checkbox" disabled="true" id="day7" /> <label
											for="day7" class="control-label">Sun</label> <input
											type="checkbox" disabled="true" id="day1" /> <label
											for="day1" class="control-label">Mon</label> <input
											type="checkbox" disabled="true" id="day2" /> <label
											for="day2" class="control-label">Tue</label> <input
											type="checkbox" disabled="true" id="day3" /> <label
											for="day3" class="control-label">Wed</label> <input
											type="checkbox" disabled="true" id="day4" /> <label
											for="day4" class="control-label">Thu</label> <input
											type="checkbox" disabled="true" id="day5" /> <label
											for="day5" class="control-label">Fri</label> <input
											type="checkbox" disabled="true" id="day6" /> <label
											for="day6" class="control-label">Sat</label>
									</div>
									<div class="form-group">
										<label for="datetimepicker" class="control-label">Select
											Date</label> <input type="text" class="form-control"
											onchange="dateselected()" id="datetimepicker" /><br>
									</div>
									<div class="form-group">
										<label for="datetimepicker2" class="control-label">Select
											Time</label> <select class="form-control" id="timeselect">
											<option>__:__</option>
										</select>
										<!-- <input type="text" class="form-control" id="datetimepicker2"/><br> -->
									</div>
									<div class="form-group">
										<label for="message-text" class="control-label">Notes</label>
										<textarea class="form-control" id="sheduleNotes"></textarea>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-primary"
											onclick="return saveShedule()" id="sheduleButton">Continue</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
									
									
									
									
									
									<div class="small-12 medium-12 large-12 columns">
										<h3>Clinical Notes:</h3>
									</div>
									<div id="cliniacal"  class="small-12 medium-6 large-6 columns">
									<form:textarea path="referral_Provider_ActionList[0].providerRefReasons" placeholder="Referral Reason: Evaluate & Test" onclick="return defaultReferralReason()" id="PrvoRederalReason" class="validate[required]" required="true" />
									</div>
									<div class="small-12 medium-6 large-6 columns">
										 <form:textarea path="referral_Provider_ActionList[0].providerDiagCode" placeholder="Diagnosis Code" id="PrvoDiagosis" />
									</div>
									
									<div class="small-12 medium-6 large-6 columns">
										<form:textarea path="referral_Provider_ActionList[0].providerNotes" placeholder="Additional Notes" id="additionalNotes" />
									</div>
									
									<div class="small-12 medium-6 large-6 columns">
										
										 <input type="file" name="files" class="file" multiple>
										
									</div>  <!-- file uploads -->
									
									<div class="clearfix"></div>
									
									<div id="submit-button"  class="small-12 medium-12 large-12 columns">
										<input name="Submit" type="button" value="Submit" class="button alignright" onclick="return Refralreasonvalidation()">
										 
									</div>
									</form:form>
								<!--.refer-a-patient-page-form-->
									
									
									
									
								</div><!--.panel callout-->
							</div><!--.main-content-inside-->
						</div><!--#content-inner-main-right-->	

</body>
</html>