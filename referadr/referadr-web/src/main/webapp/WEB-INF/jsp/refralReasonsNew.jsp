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
<title>Connecting Doctors</title>

<link rel="stylesheet" href="cssNEW/foundation.css" />
<link rel="stylesheet" href="cssNEW/style.css" />
<script src="js/vendor/jquery.ui.widget.js"></script>
<script src="js/jquery.1.9.1.min.js"></script>
<script type="text/javascript" src="js/jsp-js/referPatient.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="jQuery/css/validationEngine.jquery.css"
	type="text/css" />
<link rel="stylesheet" type="text/css"
	href="css/jquery.datetimepicker.css" />
<link rel="stylesheet" type="text/css" href="responsiveNEW/style.css">
<link rel="stylesheet" type="text/css"
	href="responsiveNEW/responsive.css">
<script src="js/page-js/nextPrevious.js"></script>
<script src="jQuery/js/languages/jquery.validationEngine-en.js"
	type="text/javascript" charset="utf-8"></script>
<script src="jQuery/js/jquery.validationEngine.js"
	type="text/javascript" charset="utf-8"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css"
	href="css/jquery.datetimepicker.css" />




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
             <div id="content-right-main" class="small-12 medium-12 large-10 columns">
             
             
             
                  <div id="main-content-full" class="small-12 medium-12 large-12 columns">

                      
                     <form:form id="formID" method="POST" action="updateRefferal.do?fromProviderId=${fromProviderId}" modelAttribute="PatientReferralInfo" autocomplete="off" enctype="multipart/form-data" onsubmit="return showProviderDropDown()">
                        <form:hidden path="curStatusId" value="${referCurrentStatusId}" />
						<form:hidden path="refId" value="${patientReferralId}" />
						<input type="hidden" name='refprid' id='providerID' value='${providerId}' />
						<form:hidden path="scheduleDate" id="scheduleDate" />
		<form:hidden path="scheduleTime" id="scheduleTime" />
		<form:hidden path="scheduleNotes" id="scheduleNotes" />
		<form:hidden path="schedulFlag" id="scheduleFlag" />
                          
                          
                          <div class="Fefer-to-2">
							<div class="small-12 medium-12 large-12 columns ">	
								<input type="radio" name="pokemon3" value="yellow" id="noCheck" checked>
								<label for="noCheck">Specialist</label>
								<input type="radio" name="pokemon3" value="green" id="yesCheck">
								<label for="yesCheck">Lab</label>
							</div>
                              <div class="clear-both"></div>
                              <div id="warningmsg">
									<p id="demo10" style="color: red"></p>
								</div>
                              
                            <div id="all">
     <div class="small-12 medium-12 large-12 columns" id='providerdropdowm'>
     	Please select referral provider
     	 <div id="drops"  class="small-12 medium-6 large-3 columns custom-sawateen">
     	<form:select path="proId" id="refproviderId" readonly="true" onchange="fillProviderId()">
								<form:option value="0" label="Select" />
								<c:forEach items="${ProviderInfo}" var="refProviderInfo">
									<form:option value="${refProviderInfo.providerId}"
										label="${refProviderInfo.providerFirstName}" />
								</c:forEach>
							</form:select>
						</div>	
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
                              
                              
                              
                              
                              <div id="drops" class="small-12 medium-6 large-3 columns custom-sawateen">
                               <!--   <select>
                                    <option value="husker">Select Speciality</option>
                                    <option value="starbuck">Starbuck</option>
                                    <option value="hotdog">Hot Dog</option>
                                    <option value="apollo">Apollo</option>
                                </select> -->
                                <form:select path="practiceSpecialty.praticeSplID" id="practiceSpectialtyId">
         <form:option value="0" label="Select" />
        </form:select>
                                
                              </div>
                              <div id="drops" class="small-12 medium-6 large-3 columns custom-sawateen">
                               <!--   <select>
                                    <option value="husker">Select Practice</option>
                                    <option value="starbuck">Starbuck</option>
                                    <option value="hotdog">Hot Dog</option>
                                    <option value="apollo">Apollo</option>
                                </select> -->
                                <form:select path="practiceInfo.practiceId" id="practiceInfoId">
         <form:option value="0" label="Select" />
        </form:select>
                              </div>
                              <div id="drops" class="small-12 medium-6 large-3 columns custom-sawateen">
                              <!--    <select>
                                    <option value="husker">Select Provider</option>
                                    <option value="starbuck">Starbuck</option>
                                    <option value="hotdog">Hot Dog</option>
                                    <option value="apollo">Apollo</option>
                                </select> -->
                                 <form:select path="providerInfo.providerId" id="providerInfoId">
         <form:option value="0" label="Select" />
        </form:select>
                              </div>
                         
                              
                              	<div id="input-1"
					class="small-12 medium-6 large-3 columns baqi-width">
					<input  type="button" onclick="callSchedule()" id="callScheduleid"
						value="Schedule" disabled="disabled">
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
                              
                              
                              
							  <div class="lab-form-container-radio">
							  
							  </div><!--.lab-form-container-radio-->
							  
							  <div class="large-12 columns text-center">
                              <h3 class="circle-span">Clinical Notes:</h5>
                              </div>
							  
							  
                              <div id="cliniacal" class="small-12 medium-4 large-4 columns">
                                   <form:textarea path="referral_Provider_ActionList[0].providerRefReasons" placeholder="Referral Reason: Evaluate & Test" onclick="return defaultReferralReason()" id="PrvoRederalReason" class="validate[required]" required="true" />
                              </div>
                              
                              <div id="cliniacal" class="small-12 medium-4 large-4 columns">
                                  <form:textarea path="referral_Provider_ActionList[0].providerDiagCode" placeholder="Diagnosis Code" id="PrvoDiagosis" />
                              </div>
                              
                              <div id="cliniacal" class="small-12 medium-4 large-4 columns">
                                  <form:textarea path="referral_Provider_ActionList[0].providerNotes" placeholder="Additional Notes" id="additionalNotes" />
                              </div>
                              <div class="clear-both"></div>

							  
                                <div id="upload-file" class="small-12 medium-6 large-6 large-offset-6 columns">
                                  <div class="small-12 medium-12 large-12 columns no-padding">
									<div class="small-12 medium-12 large-12 columns no-padding">
                                  <input type="file" name="files" class="file" multiple>
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
                                    <input name="submit" type="submit" value="Submit" style="float: right;" onclick="return Refralreasonvalidation()" />
                                  </div>
                              </div><!-----Submit Button------>
                          </div><!----Fefer-to-2---->
                          
                          
                      </form:form><!----refer-patient----->
                  </div><!-----#main-content-full------->
              </div><!-----#content right main----------->
</body>
<script src="js/page-js/jquery.datetimepicker.js"></script>
<script>

 function Disableday(date) {
	 
	  var day = date.getDay();
	 // If day == 1 then it is MOnday
	 if (day == 1) {
		var mon= document.getElementById('day1').checked;
		if(!mon){
	 return [false] ; 
		}	 
	 }
	 else if (day == 2) {
			var mon= document.getElementById('day2').checked;
			if(!mon){
		 return [false] ; 
			}	 
		 }
	 else if (day == 3) {
			var mon= document.getElementById('day3').checked;
			if(!mon){
		 return [false] ; 
			}	 
		 }
	 else if (day == 4) {
			var mon= document.getElementById('day4').checked;
			if(!mon){
		 return [false] ; 
			}	 
		 }
	 else if (day == 5) {
			var mon= document.getElementById('day5').checked;
			if(!mon){
		 return [false] ; 
			}	 
		 }
	 else if (day == 6) {
			var mon= document.getElementById('day6').checked;
			if(!mon){
		 return [false] ; 
			}	 
		 }
	 else if (day == 0) {
			var mon= document.getElementById('day7').checked;
			if(!mon){
		 return [false] ; 
			}	 
		 }
	  else { 
	 
	 return [true] ;
	 }
	}


	
 jQuery('#datetimepicker').datetimepicker({
	 timepicker:false,
	 format:'Y-m-d',
	beforeShowDay: Disableday
	
	});
</script>

</html>