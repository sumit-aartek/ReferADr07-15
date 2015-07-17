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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	var showData;
	var showDocData;
</script>

<!-- <script src="js/vendor/jquery.js"></script> -->
<script src="js/jquery.1.9.1.min.js"></script>
<script src="js/vendor/jquery.ui.widget.js"></script>

<script src="js/page-js/jquery.fileupload.js"></script>
<script src="js/page-js/myuploadfunction.js"></script>
<script src="js/page-js/refer_myuploadfunction.js"></script>
<script type="text/javascript" src="js/jsp-js/referPatientMain.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="css/foundation.css" />
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="responsive/style.css">
<link rel="stylesheet" type="text/css" href="responsive/responsive.css">
<link rel="stylesheet" href="jQuery/css/validationEngine.jquery.css"
	type="text/css" />
	<link rel="stylesheet" type="text/css" href="css/jquery.datetimepicker.css"/>

<script src="js/vendor/modernizr.js"></script>
<script src="js/page-js/nextPrevious.js"></script>
<script src="js/page-js/validationForFisrtNext.js"></script>

<script src="jQuery/js/languages/jquery.validationEngine-en.js"
	type="text/javascript" charset="utf-8"></script>
<script src="jQuery/js/jquery.validationEngine.js"
	type="text/javascript" charset="utf-8"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<style type="text/css">
input[type="file"] {
	width: 86%;
}
</style>


<script src="js/foundation.min.js"></script>
<script>
	$(document).foundation();
</script>
<script type="text/javascript">
function closeschedulediv(){
	var schedulefull = document.getElementById("shaduleModal");
	schedulefull.style.display = "none";
 }
</script>
<script>
	$(function() {
		
		jQuery(document).ready(function() {
			$("#formID").validationEngine();
		});
	});
	
	$(function() { $('#insFiles').change(function (){
	  	$("#insFileNames").empty();
		var files = $('#insFiles')[0].files;
		for (var i = 0; i < files.length; i++) {
			var $p = $("<p></p>").text(files[i].name).appendTo("#insFileNames");
		}
	});
   });
	
	$(function() { $('#referFiles').change(function (){
	  	$("#referFileNames").empty();
		var files1 = $('#referFiles')[0].files;
		for (var i = 0; i < files1.length; i++) {
			var $p = $("<p></p>").text(files1[i].name).appendTo("#referFileNames");
		}
	});
   });
	/* $(document).ready(function() {
		$("#insuranceName").autocomplete({
		    source : function(request, response) {
		      var searchName = $("#insuranceName")[0];
		      var searchValue = searchName.value;
		      $.ajax({
		        url : "searchMap.do?searchValue=" + searchValue,
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
		  }); */
		  $(document).ready(function() {
				
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
		
		
		var hidePatientInfo= document.getElementById("allDetails");
		hidePatientInfo.style.display = "block";
		//============its for provider
		/* var provider="${providerSize}";
	
	    if(provider==1){
	    	document.getElementById('referring').value="${providerId}";
	    }else{
	    	document.getElementById('referring').value=0;
	    } */
		var provider="${providerSize}";
	
		     if(provider==1){
		      document.getElementById('referring').value="${providerId}";
		     }else{
		      document.getElementById('referring').value=0;
		     }
	  var clearHouselistSize="${clearHouselistSize}";
		     if(clearHouselistSize==1){
		      document.getElementById('chearingHouseId').value="${chid}";
		      
		    //  document.getElementById('practiceSpectialtyId').value="${practiceSpecialtyId}";
		      
		      var chearingHouseId = $("#chearingHouseId").val();
		        if(chearingHouseId!=0){
		        $("#practiceSpectialtyId").text(""), $("#practiceSpectialtyId").append($("<option value='0'></option>").text("Select Specialty"));
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
		            $("#practiceInfoId").text(""), $("#practiceInfoId").append($("<option value='0'></option>").text("Select Practice"));
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
		               $("#providerInfoId").text(""), $("#providerInfoId").append($("<option value='0'></option>").text("Select Provider"));
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
		                	 /*   var callScheduleid=document.getElementById("callScheduleid");
		                	   callScheduleid.style.display="true";  */
		                	   document.getElementById("callScheduleid").disabled = false;
		                	   
					            document.getElementById('providerInfoId').value=providerInfoValue.providerId;
					            document.getElementById("scheduleDate").value='';
			    	        	document.getElementById("scheduleTime").value='';
			    	        	document.getElementById("scheduleNotes").value='';
			    	        	
			    	        	document.getElementById("datetimepicker").value='';
			    	        	document.getElementById("timeselect").value='';
			    	        	document.getElementById("sheduleNotes").value='';
			    	        	document.getElementById("scheduleFlag").value='false';
					            //here we are puting ajax code for practice dropdown
					            }  
		                   /////////////////4th end
		                   
		                   
		                   
		 
		                   },
		                 error : function() {
		                 }
		               })
		               }else{
		                 $("#providerInfoId").text(""), $("#providerInfoId").append($("<option value='0'></option>").text("Select Provider"));
		               } 
		                },
		              error : function() {
		              }
		            })
		            }else{
		              $("#practiceInfoId").text(""), $("#practiceInfoId").append($("<option value='0'></option>").text("Select Practice"));
		            }
		          },
		          error : function() {
		          }
		        })
		        }else{
		          $("#practiceSpectialtyId").text(""), $("#practiceSpectialtyId").append($("<option value='0'></option>").text("Select Specialty"));
		        }
		      
		     }
		     else{
		      document.getElementById('chearingHouseId').value=0;
		     }
		    /*  setTimeout(function() {
		    	 var specialitySize="${practiceSpecialtySize}";
			     alert(specialitySize);
		  	 }, 500); */
		    
		
		var g="${size}";
		if(g==1){
		document.getElementById('address1').value="${locId}";
	     var address1Val = $("#address1").val();
	        if(address1Val!=0){
	        $.ajax({
	          url : "fullAddressByaddress1.do?address1=" + address1Val,
	          type : "GET",
	          contentType : "application/json; charset=utf-8",
	          success : function(t) {
	        	  var e = t.fullAddressByAddress1;
	        	  document.getElementById('phone').value=e[0].locPhone;
	        	  
	        	 /*  document.getElementById('address2').value=e[0].locAddress2;
	              document.getElementById('city').value=e[0].locCity;
	              document.getElementById('zip').value=e[0].locZip;
	             
	              document.getElementById('fax').value=e[0].locFax;
	              document.getElementById('state').value=e[0].redState.stateId;
	              document.getElementById('addId').value=e[0].locId; */
	          },
	          error : function() {
	          }
	        })
	        }else{
	        	document.getElementById('phone').value="";
	        	
	        	
	        /*   document.getElementById('address2').value="";
	          document.getElementById('city').value="";
	          document.getElementById('zip').value="";
	          
	          document.getElementById('fax').value="";
	          document.getElementById('state').value=""; */
	        }
		}

		 <c:if test="${sessionScope.referralInfo!=null}"> 
//alert('patient id ${sessionScope.referralInfo.patientInfo.patientId}');
document.getElementById('patientId').value='${sessionScope.referralInfo.patientInfo.patientId}';
document.getElementById('patientIdHidden').value='${sessionScope.referralInfo.patientInfo.patientId}';

//alert('patient FN ${sessionScope.referralInfo.patientInfo.patientFirstName}');
document.getElementById('patientName').value='${sessionScope.referralInfo.patientInfo.patientFirstName}';

//alert('patient LN ${sessionScope.referralInfo.patientInfo.patientLastName}');
document.getElementById('patientLastName').value='${sessionScope.referralInfo.patientInfo.patientLastName}';

//alert('patient Email ${sessionScope.referralInfo.patientInfo.patientEmail}');
document.getElementById('emailId').value='${sessionScope.referralInfo.patientInfo.patientEmail}';

//alert('patient Gen ${sessionScope.referralInfo.patientInfo.patientGender}');
document.getElementById('gender').value='${sessionScope.referralInfo.patientInfo.patientGender}';

//alert('patient Dob ${sessionScope.referralInfo.patientInfo.patientDob}');
document.getElementById('dob').value='${sessionScope.referralInfo.patientInfo.patientDob}';

//alert('patient phone ${sessionScope.referralInfo.patientInfo.patientPhoneNo}');
document.getElementById('patientPhone').value='${sessionScope.referralInfo.patientInfo.patientPhoneNo}';

//alert('patient notes ${sessionScope.referralInfo.scheduleNotes}');
document.getElementById('additionalNotes').value='${sessionScope.referralInfo.scheduleNotes}';

//alert('patient provID ${sessionScope.referralInfo.proId}');
document.getElementById('referring').value='${sessionScope.referralInfo.proId}';

//alert('patient spl ${sessionScope.referralInfo.practiceSpecialty.praticeSplID}');
document.getElementById('practiceSpectialtyId').value='${sessionScope.referralInfo.practiceSpecialty.praticeSplID}';

//alert('patient group ${sessionScope.referralInfo.patientInfo.patientInsuranceInfoList[0].patientInsuranceGroup}');
document.getElementById('group').value='${sessionScope.referralInfo.patientInfo.patientInsuranceInfoList[0].patientInsuranceGroup}';

//alert('patient pat ins id ${sessionScope.referralInfo.patientInfo.patientInsuranceInfoList[0].patientInsuranceId}');
document.getElementById('insuranceId').value='${sessionScope.referralInfo.patientInfo.patientInsuranceInfoList[0].patientInsuranceId}';

//alert('patient ins comp ${sessionScope.referralInfo.patientInfo.patientInsuranceInfoList[0].patientInsuranceNotes}');
document.getElementById('insuranceName').value='${sessionScope.referralInfo.patientInfo.patientInsuranceInfoList[0].patientInsuranceNotes}';
</c:if>
	});
</script> <script type="text/javascript">
function defaultReferralReason(){
	

	var PrvoRederalReason = document.getElementById('PrvoRederalReason').value;
if(PrvoRederalReason==''){
	document.getElementById('PrvoRederalReason').value="Evaluate & Treat.";
}
	//alert("hi"+PrvoRederalReason);
return true;
	}



</script>
<script type="text/javascript">
	function openSearchPage(data) {
		var recordIndex=-1;
		if (showData != null && showData != "") {
			
			var patitentName = document.getElementById('patientName');
			var patitentLastName = document.getElementById('patientLastName');
			var phone = document.getElementById('patientPhone');
			var gender = document.getElementById('gender');
			var dob = document.getElementById('dob');
			var emailId = document.getElementById('emailId');
			/* var address1 = document.getElementById('patientAddress1');
			var address2 = document.getElementById('patientAddress2');
			var city = document.getElementById('patientCity');
			var state = document.getElementById('PatientState');
			var zip = document.getElementById('patientZip');
			
			var fax = document.getElementById('patientFax');
			
			var ssn1 = document.getElementById('ssn1'); */
			
			/*  var ssn1=document.getElementById('ssn1').disabled = true;  */
			for (i = 0; i < showData.length; i++) {
				locId.value = showData[i].radLocation.locId;
				patientId.value = showData[i].patientId;
				//PatientState.value=showData[i].radLocation.redState.stateId;
				patientIdHidden.value = showData[i].patientId;
				if (showData[i].patientId == data) {
					recordIndex=i;
					
					patitentName.value = showData[i].patientFirstName;
					patitentLastName.value = showData[i].patientLastName;
					gender.value = showData[i].patientGender;
					dob.value = showData[i].patientDob;
					phone.value = showData[i].radLocation.locPhone;
					emailId.value = showData[i].patientEmail;
					
				//	address1.value = showData[i].radLocation.locAddress1;
				//	address2.value = showData[i].radLocation.locAddress2;
				//	city.value = showData[i].radLocation.locCity;
				//	zip.value = showData[i].radLocation.locZip;
					
				//	fax.value = showData[i].radLocation.locFax;
					
			//		ssn1.value = showData[i].patientSSN;
					
				//	state.value = showData[i].radLocation.redState.stateId;
				//	ssn1.disabled = true;
					gender.disabled = true;
					dob.disabled = true;
					patientName.disabled = true;
					patientLastName.disabled = true;
					
				}
			}
			var insuranceId = document.getElementById('insuranceId');
			var group = document.getElementById('group');
			var insurancePhone = document.getElementById('insurancePhone');
			var insuranceName = document.getElementById('insuranceName');
			
			/* var preAuth = document.getElementById('preAuth');
			var PreAuthDate = document.getElementById('PreAuthDate');
			var toDate = document.getElementById('toDate');
			var preAuthContachNo = document.getElementById('preAuthContachNo');
			var notes = document.getElementById('notes');
			var pokemonRed = document.getElementById('pokemonRed');
			var pokemonBlue = document.getElementById('pokemonBlue'); */
			
			
			//var confirmation = document.getElementById('confirmation');
			if (showData != null && showData != "") {
				for (i = 0; i < showData.length; i++) {
					if (showData[i].patientId == data) {
						var a = showData[i].patientInsuranceInfoList;
						insuranceId.value = a[0].patientInsuranceId;
						group.value = a[0].patientInsuranceGroup;
					//	insurancePhone.value = a[0].patientInsurancePhone;
					//	preAuth.value = a[0].patientPreAuthId;

						//TODO :- Create a separte function for get Date in MM/DD/YYYY format 
						var showStartDate = new Date(
								a[0].patientPreAuthStartDate);
						var newStartDate = showStartDate.getMonth()+1 + "/"
								+ showStartDate.getDate() + "/"
								+ showStartDate.getFullYear();

						var showEndDate = new Date(a[0].patientPreAuthEndDate);
						var newEndDate = showEndDate.getMonth()+1 + "/"
								+ showEndDate.getDate() + "/"
								+ showEndDate.getFullYear();

						/* PreAuthDate.value = newStartDate;
					toDate.value = newEndDate;

						preAuthContachNo.value = a[0].patientPreAuthContactName;
						notes.value = a[0].patientInsuranceNotes; */
						
						/* if (a[0].patientPre1AuthReq == 'Y') {
							
							$('#pokemonRed').attr('checked', true);
							document.getElementById('preAuth').disabled = false;
							document.getElementById('PreAuthDate').disabled = false;
							document.getElementById('toDate').disabled = false;
							document.getElementById('preAuthContachNo').disabled = false;
							  document.getElementById('confirmation').disabled = false;
							  var obj103=document.getElementById("preAuth");
								obj103.style.display="block"; 
								var obj104=document.getElementById("PreAuthDate");
								obj104.style.display="block"; 
								var obj105=document.getElementById("toDate");
								obj105.style.display="block"; 
								var obj106=document.getElementById("preAuthContachNo");
								obj106.style.display="block"; 
								var obj107=document.getElementById("confirmation");
					/* 			obj107.style.display="block";  */
						/* } else {
							$('#pokemonBlue').attr('checked', true);
							 var obj103=document.getElementById("preAuth");
								obj103.style.display="none"; 
								var obj104=document.getElementById("PreAuthDate");
								obj104.style.display="none"; 
								var obj105=document.getElementById("toDate");
								obj105.style.display="none"; 
								var obj106=document.getElementById("preAuthContachNo");
								obj106.style.display="none"; 
								var obj107=document.getElementById("confirmation");
								obj107.style.display="none"; 
						}  */
						insuranceName.value = a[0].insuranceInfo.insuranceCompany;
						
						insuranceIdHideen.value = a[0].insuranceInfo.insuranceId;
						patientInsuranceInfoIdHidden.value = a[0].patientInsuranceInfoId;
						insuranceName.disabled = true;
					}
				}
			}

			if(recordIndex!=-1){

				 $('#showDataTable').empty();
	            	if (showDocData != null && showDocData != "") {
	            		  $('#showDocTable').append('<table width="100%" border="0" cellpadding="0" cellspacing="0"></table>');
	                      var doctable = $('#showDocTable').children();
	                     // doctable.append("<tr class='first-row-heading-table'><td>Insurance Documents</td></tr>");
	            		
	            		
	                     	for(var j = 0, size1 = showDocData[recordIndex].length; j < size1 ; j++){
	                     	
	                   //  table.append($("<tr><td><a href='#' onclick='openSearchPage("+e[i].patientId+")'>"+e[i].patientId+"</a></td><td><a href='#' onclick='openSearchPage("+e[i].patientId+")'> "+e[i].patientFirstName +" "+e[i].patientLastName +"</a>"+"</td><td>"+e[i].patientDob+"</td></tr>"));
	                   //  $('#showInsDocs').html('<a href=download.do?filePath=' + docattacheList.docPath + '&fileName='+docattacheList.docName+'>Products</a>');
	                     doctable.append($('<tr><td><a href=download.do?filePath=' + showDocData[recordIndex][j].docPath + '&fileName='+showDocData[recordIndex][j].docName+'>'+showDocData[recordIndex][j].docName+'</a></td></tr>'))
	                     	}
	                     	

	            		
	            	}

				}
			

			var obj4 = document.getElementById("div2");
			obj4.style.display = "true";
			var obj5 = document.getElementById("div3");
			obj5.style.display = "block";
			/* var white_content=document.getElementById("white_content");
			white_content.style.display="block"; */
		}

	}
	function fillValues()
	{
		var pDate = document.getElementById('dob').value;
		document.getElementById('pDate').value = pDate;

	}
	function openBox4() {
		

	 	 var patientName=document.getElementById('patientName').value;
	   var patientLastName=document.getElementById('patientLastName').value;
	   var gender=document.getElementById('gender').value;
		var dob=document.getElementById('dob').value;
		var emailId=document.getElementById('emailId').value;
	//	var patientAddress1=document.getElementById('patientAddress1').value;
		//var patientCity=document.getElementById('patientCity').value;
		//var PatientState=document.getElementById('PatientState').value;
//		var patientZip=document.getElementById('patientZip').value;
		
if(patientName!="" && patientLastName=="" ||  gender=="G" || dob=="")
		{
	
	$('#demo101').append('<div></div>').html("Please fill the required fields.");
	var obj101=document.getElementById("div3");
	obj101.style.display="true"; 
	return false;
	}
	else{ 
	
		/* var preAuthDate=document.getElementById('PreAuthDate').value;
		 if(preAuthDate=='1/1/1970'){
		  document.getElementById('PreAuthDate').value = "";
		   document.getElementById('toDate').value = ""; */
		 }
		
		var pDate = document.getElementById('dob').value;
		document.getElementById('pDate').value = pDate;

		var pSsn1 = document.getElementById('ssn1').value;
		document.getElementById('pSsn1').value = pSsn1;

		var PGender = document.getElementById('gender').value;
		document.getElementById('PGender').value = PGender;

		var obj4 = document.getElementById("div3");
		obj4.style.display = "none";
		var obj5 = document.getElementById("div4");
		obj5.style.display = "block";
		/* var white_content=document.getElementById("white_content");
		white_content.style.display="block"; */
		
	return true;}
		/* 
		var preAuthDate=document.getElementById('PreAuthDate').value;
		 if(preAuthDate=='1/1/1970'){
		  document.getElementById('PreAuthDate').value = "";
		   document.getElementById('toDate').value = "";
		 }
		
		var pDate = document.getElementById('dob').value;
		document.getElementById('pDate').value = pDate;

		var pSsn1 = document.getElementById('ssn1').value;
		document.getElementById('pSsn1').value = pSsn1;

		var PGender = document.getElementById('gender').value;
		document.getElementById('PGender').value = PGender;

		var obj4 = document.getElementById("div3");
		obj4.style.display = "none";
		var obj5 = document.getElementById("div4");
		obj5.style.display = "block"; */
		/* var white_content=document.getElementById("white_content");
		white_content.style.display="block"; */

	//}
</script>
<script>
	var i = 1;
	function appendText() {

		var txt1 = '<div id="main-site-container" class="small-12 medium-12 large-12 columns"><div class="small-12 medium-12 large-12 columns"><input type="text" id=IN'+i+' placeholder="Insurance Name" ></div>'

		var txt2 = '<div class="small-12 medium-12 large-6 columns"><input type="text" id=II'+i+' placeholder="Insurance ID"></div>'

		var txt3 = '<div class="small-12 medium-12 large-6 columns"><input type="text" id=G'+i+' placeholder="Group"></div>'

		var txt4 = '<div class="small-12 medium-12 large-6 columns"><input type="text" id=IP'+i+' placeholder="Insurance Phone"></div>'

		var txt5 = '<div class="small-12 medium-12 large-6 columns"><input type="text" id=C'+i+' placeholder="Claim"></div>'

		//var txt6='<div id="padding-0" class="small-12 medium-12 large-12 columns"><div class="small-12 medium-12 large-6 columns"> <label>Pre AUTH</label><input type="radio" name="pokemon" value="Red" id=pokemonRed'+i+'><label for="pokemonRed">Yes</label><input type="radio" name="pokemon" value="Blue" id=pokemonBlue'+i+'><label for="pokemonBlue">No</label></div><div class="small-12 medium-12 large-6 columns"><input type="text" id=AU'+i+' placeholder="Pre AUTH"></div></div>'

		var txt7 = '<div id="padding-0" class="small-12 medium-12 large-12 columns"><div class="small-12 medium-12 large-6 columns"><input type="text" placeholder="Pre AUTH Date : From" id=AF'+i+'></div><div class="small-12 medium-12 large-6 columns"><input type="text" id=AT'+i+' placeholder="To"></div></div>'

		var txt8 = '<div id="padding-0" class="small-12 medium-12 large-12 columns"><div class="small-12 medium-12 large-6 columns"><input type="text" placeholder="Contact Name" id=CN'+i+'></div><div class="small-12 medium-12 large-6 columns"><input type="text" placeholder="Confirmation" id=CF'+i+'></div></div>'

		var txt9 = '<div id="padding-0" class="small-12 medium-12 large-12 columns"><div class="small-12 medium-12 large-6 columns"><textarea placeholder="Notes" id=N'+i+'></textarea></div><div class="small-12 medium-12 large-6 columns"><input type="file" name="Attach Docs" value="Attach Docs" id=fileToUpload'+i+' class="small radius button" multiple></div></div>'
		i++;
		$("b").before(txt1, txt2, txt3, txt4, txt5, txt7, txt8, txt9);

	}
	var i = 1;
	function referMore() {
		var txt1 = '<div id="main-site-container" class="small-12 medium-12 large-12 columns"><textarea id=IN'+i+' name="referral_Provider_ActionList[0].providerRefReasons" placeholder="Refral Reson"/>'

		var txt2 = '<div id="main-site-container" class="small-12 medium-12 large-12 columns"><textarea id=II'+i+' name="referral_Provider_ActionList[0].providerDiagCode" placeholder="Diagnosis code"/>'

		var txt3 = '<div id="main-site-container" class="small-12 medium-12 large-12 columns"><textarea id=G'+i+' name="referral_Provider_ActionList[0].providerNotes" placeholder="Additional code"/>'
		i++;
		$("p").before(txt1, txt2, txt3);

	}
</script>
<script type="text/javascript">
	/* function PreAuth() {

		
	//	var preAuthenticno = document.getElementById('pokemonBlueRadio').checked;
//alert("no selected="+preAuthenticno);
		if (preAuthenticno) {
		document.getElementById('preAuth').value = "";
		document.getElementById('PreAuthDate').value = "";
		document.getElementById('toDate').value = "";
		document.getElementById('preAuthContachNo').value = "";
		document.getElementById('confirmation').value = "";

		document.getElementById('preAuth').disabled = true;
		document.getElementById('PreAuthDate').disabled = true;
		document.getElementById('toDate').disabled = true;
		document.getElementById('preAuthContachNo').disabled = true;
		  document.getElementById('confirmation').disabled = true;

			var obj103=document.getElementById("preAuth");
			obj103.style.display="none"; 
			var obj104=document.getElementById("PreAuthDate");
			obj104.style.display="none"; 
			var obj105=document.getElementById("toDate");
			obj105.style.display="none"; 
			var obj106=document.getElementById("preAuthContachNo");
			obj106.style.display="none"; 
			var obj107=document.getElementById("confirmation");
			obj107.style.display="none"; 
		}else{
			document.getElementById('preAuth').disabled = false;
			document.getElementById('PreAuthDate').disabled = false;
			document.getElementById('toDate').disabled = false;
			document.getElementById('preAuthContachNo').disabled = false;
			document.getElementById('confirmation').disabled = false;
			
			
			var obj103=document.getElementById("preAuth");
			obj103.style.display="block"; 
			var obj104=document.getElementById("PreAuthDate");
			obj104.style.display="block"; 
			var obj105=document.getElementById("toDate");
			obj105.style.display="block"; 
			var obj106=document.getElementById("preAuthContachNo");
			obj106.style.display="block"; 
			var obj107=document.getElementById("confirmation");
			obj107.style.display="block"; 
		} 
	} */
</script>
<script type="text/javascript">
	/* function PreAuthYes() {
		var preAuthenticYes = document.getElementById('pokemonRed').checked;
		//alert("yes selected="+preAuthenticYes);
		if (preAuthenticYes) {
			document.getElementById('preAuth').disabled = false;
			document.getElementById('PreAuthDate').disabled = false;
			document.getElementById('toDate').disabled = false;
			document.getElementById('preAuthContachNo').disabled = false;
			document.getElementById('confirmation').disabled = false;
			
			
			var obj103=document.getElementById("preAuth");
			obj103.style.display="block"; 
			var obj104=document.getElementById("PreAuthDate");
			obj104.style.display="block"; 
			var obj105=document.getElementById("toDate");
			obj105.style.display="block"; 
			var obj106=document.getElementById("preAuthContachNo");
			obj106.style.display="block"; 
			var obj107=document.getElementById("confirmation");
			obj107.style.display="block"; 
		}
		else{

			document.getElementById('preAuth').value = "";
			document.getElementById('PreAuthDate').value = "";
			document.getElementById('toDate').value = "";
			document.getElementById('preAuthContachNo').value = "";
			document.getElementById('confirmation').value = "";

			document.getElementById('preAuth').disabled = true;
			document.getElementById('PreAuthDate').disabled = true;
			document.getElementById('toDate').disabled = true;
			document.getElementById('preAuthContachNo').disabled = true;
			  document.getElementById('confirmation').disabled = true;

				var obj103=document.getElementById("preAuth");
				obj103.style.display="none"; 
				var obj104=document.getElementById("PreAuthDate");
				obj104.style.display="none"; 
				var obj105=document.getElementById("toDate");
				obj105.style.display="none"; 
				var obj106=document.getElementById("preAuthContachNo");
				obj106.style.display="none"; 
				var obj107=document.getElementById("confirmation");
				obj107.style.display="none"; 

			
		}
	} */
</script>
<script type="text/javascript"> 
 
//create a function that accepts an input variable (which key was key pressed) 
function disableEnterKey(e){ 

//create a variable to hold the number of the key that was pressed      
var key; 
 
    //if the users browser is internet explorer 
    if(window.event){ 
      
    //store the key code (Key number) of the pressed key 
    key = window.event.keyCode; 
      
    //otherwise, it is firefox 
    } else { 
      
    //store the key code (Key number) of the pressed key 
    key = e.which;      
    } 
      
    //if key 13 is pressed (the enter key) 
    if(key == 13){ 
   
    //do nothing 
    return false; 
      
    //otherwise 
    } else { 
      
    //continue as normal (allow the key press for keys other than "enter") 
    return true; 
    } 
      
//and don't forget to close the function     
} 
</script> 
 
     <script type="text/javascript">

  function closediv(){
	 // document.getElementById("insuranceName").value='';
    		var insuranceVarification = document.getElementById("insuranceVarification");
    		insuranceVarification.style.display = "none";
         }
  </script>

    
       <script type="text/javascript">

  function cleardiv(){
	document.getElementById("insuranceName").value='';
    		var insuranceVarification = document.getElementById("insuranceVarification");
    		insuranceVarification.style.display = "none";
    	    var textbox = document.getElementById("insuranceName");
    	    textbox.focus().select();
    	    textbox.scrollIntoView();
         }
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
   


</head>
<body>
 <div class="modal fade" id="insuranceVarification" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" style="display: none;">
      <div class="modal-dialog">
        <div class="modal-content large-12 columns">
          <div class="modal-header">
            <button onclick="closediv()" type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><img class="close" src="bootstrap/alert_boxes_close_ico.png"></span></button>
            <h4 class="modal-title" id="exampleModalLabel">&nbsp </h4>
          </div>
          <div id="mailsent" class="modal-body">
            <form>
              <div class="form-group">
             <label id="insname"> </label>     
              </div>
			  <div class="modal-footer">
			 
              <button type="button" class="btn btn-default" onclick="cleardiv()" >OK</button> 
     
            
          </div>
            </form>
            <p id="demo222" style="color: red"></p>
          </div>
          
        </div>
      </div>
    </div><!-- /.modal -->





	<form:form id="formID" method="POST" action="saveAllDetails.do"
		modelAttribute="PatientReferralInfo" autocomplete="off" enctype="multipart/form-data" onKeyPress="return disableEnterKey(event)" onsubmit="fillValues()">
		<form:hidden path="refId" />
		<form:hidden path="patientInfo.patientId" id="patientId" />
		<form:hidden path="patientInfo.radLocation.locId" id="locId" />
		<form:hidden path="scheduleDate" id="scheduleDate" />
		<form:hidden path="scheduleTime" id="scheduleTime" />
		<form:hidden path="scheduleNotes" id="scheduleNotes" />
	<form:hidden path="schedulFlag" id="scheduleFlag"/>

		<form:hidden
			path="patientInfo.patientInsuranceInfoList[0].insuranceInfo.insuranceId"
			id="insuranceIdHideen" />
		<form:hidden
			path="patientInfo.patientInsuranceInfoList[0].patientInfo.patientId"
			id="patientIdHidden" />
		<form:hidden
			path="patientInfo.patientInsuranceInfoList[0].patientInsuranceInfoId"
			id="patientInsuranceInfoIdHidden" />
		<!-- <div id="secondary-menu" class="small-12 medium-12 large-12 columns">
			<ul>
				<li><a href="PracticeInfo.do">PRACTICE</a></li>
				<li><a href="doctor.do">DOCTORS</a></li>
				<li><a href="#">INSURANCE</a></li>
				<li><a href="#">BILLING</a></li>
			</ul>
		</div> -->
		<div id="main-site-container"
			class="small-12 medium-12 large-12 columns">

			<div id="scroll" class="small-12 medium-12 large-12 columns"
			>
				<div id="speciality-panel" class="panel">

				<div  class="black_content"> 
						<h1 align="center">Refer a Patient</h1>
						 </div> 
                             		 <div id="warningmsgreferpatient11">

                                <p id="demo11" style="color: red"></p>
                            </div>
					<jsp:useBean id="today" class="java.util.Date" />
						<fmt:formatDate var="todayString" value="${today}"
							pattern="MM/dd/yyyy" />
							
						
						<div style=" margin-left: 64%;"  class="small-12 medium-12 large-4 date">
						<form:input path="" value="${todayString}" disabled="true" />
						</div>
						<label>Referring Doctor:</label>
								<div style="border:1px solid black;height: 68px;padding: 1rem 0px 0px 0px;" >
						<div class="small-12 medium-12 large-6 columns">
						
							<form:select path="proId" id="referring" readonly="true">
								<form:option value="0" label="Select Referring Doc" />
								<c:forEach items="${ProviderInfo}" var="refProviderInfo">
									<form:option value="${refProviderInfo.providerId}"
										label="${refProviderInfo.providerFirstName} ${refProviderInfo.providerLastName}"/>
								</c:forEach>
							</form:select>
						</div>
						<div class="small-12 medium-12 large-6 columns">
							<form:select
								path="practiceInfo.practiceLocations.radLocation.locAddress1"
								id="address1">
								<form:option value="0" label="Select Location" />
								<c:forEach items="${address1List}" var="address1">
									<%-- <form:option value="${address1.locAddress1}" label="${address1.locAddress1}" /> --%>
									<form:option value="${address1.locId}" label="${address1.locAddress1}" />
								</c:forEach>
							</form:select>
						</div>
						<form:hidden
							path="practiceInfo.practiceLocations.radLocation.locId"
							id="addId" />
							
							</div>
							
						<%-- <div class="small-12 medium-12 large-6 columns">
							<form:input
								path="practiceInfo.practiceLocations.radLocation.locAddress2"
								placeholder="Address2" id="address2" readonly="true" />
						</div>
						<div class="small-12 medium-12 large-3 columns">
							<form:input
								path="practiceInfo.practiceLocations.radLocation.locCity"
								placeholder="City" id="city" readonly="true" />
						</div>
						<div class="small-12 medium-12 large-2 columns">
							<select id="state" onkeyup="stoppedTyping()" disabled>
								<option value="0">State</option>
								<c:forEach items="${stateList}" var="state">
									<option value="${state.stateId}">${state.stateName}</option>
								</c:forEach>
							</select> --%>
							
							<form:hidden
								path="practiceInfo.practiceLocations.radLocation.redState.stateId"
								id="statId" />
						<!-- </div> -->
						<%-- <div class="small-12 medium-12 large-2 columns">
							<form:input
								path="practiceInfo.practiceLocations.radLocation.locZip"
								placeholder="Zip" id="zip" readonly="true" />
						</div>
						<div class="small-12 medium-12 large-3 columns">
							<form:input
								path="practiceInfo.practiceLocations.radLocation.locPhone"
								placeholder="Phone" id="phone" readonly="true" />
						</div>
						<div class="small-12 medium-12 large-2 columns">
							<form:input
								path="practiceInfo.practiceLocations.radLocation.locFax"
								placeholder="Fax" id="fax" readonly="true" />
						</div> --%>
						<div class="small-12 medium-12 large-12 columns">
							<input type="button" value="next" class="small next"
								onclick="openBox2();" style="display: none"/>

						</div>
						<p id="demo" style="color: red"></p>
					


					<div id="div2" class="black_content">
						<p id="demo2" style="color: red"></p>

						<div class="small-12 medium-4 large-4 columns">
							<input type="text" placeholder="Patient Last Name"
								class="required specialChar" id="lastName" maxlength="140" style="display: none" />
						</div>
						<div class="small-12 medium-4 large-4 columns">
							<input type="date" placeholder="MM/DD/YYYY" id="patientDateOb" style="display: none"/>
						</div>
						<div class="small-12 medium-4 large-4 columns">
							<input type="text" placeholder="SSN" id="patientSsn"
								maxlength="9" style="display: none"/>
						</div>
						<div style="display: none" class="small-12 medium-12 large-4 columns">
						<input type="button" value="Search" id="search""/>
						 </div>
						<div class="row">
							
						</div><br>
						<div id="showDataTable"></div>


						<div class="small-12 medium-6 large-6 columns">
							<input type="button" value="Previous" class="small previous"
								onClick="PreviousopenBox1()" style="display: none"/>
						</div>
						<div class="small-12 medium-6 large-6 columns">
							<input type="button" value="next" class="small next"
								onclick="openBox3()" style="display: none"/>
						</div>
					</div>

           <div id="allDetails">
           <label>Patient Information:</label>
					<div id="div3" class="black_content"  style="border:1px solid black;height: 235px;padding: 0px 0px 0px 0px;" >
					<p id="demo101" style="color: red"></p>
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
							<input type="text" id="dob" onkeyup="formatDate()" placeholder="DOB" maxlength="10"
								required="true"
								class="textfield validate[required,custom[date]]" />
							<form:hidden path="patientInfo.patientDob" id="pDate"
								placeholder="DOB" required="true"
								class="validate[required] text-input" />
						</div>

                         <div class="small-12 medium-12 large-4 columns">
								<form:input path="patientInfo.radLocation.locPhone"
									id="patientPhone" placeholder="Phone" pattern="^\d{3}-\d{3}-\d{4}$" onkeyup="formatPhoneNumber()" class="validate[custom[phone]] text-input" required="true"
									 maxlength="15" />
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
						<%-- <div class="small-12 medium-12 large-6 columns">
							<form:input path="patientInfo.radLocation.locAddress1"
								id="patientAddress1" placeholder="Address1" required="true"
								class="validate[required] text-input" maxlength="160" />
						</div>

						<div class="small-12 medium-12 large-6 columns">
							<form:input path="patientInfo.radLocation.locAddress2"
								id="patientAddress2" placeholder="Address2" 
								 maxlength="160" />
						</div>

						<div id="padding-0" class="small-12 medium-12 large-8 columns">
							<div class="small-12 medium-12 large-4 columns">
								<form:input path="patientInfo.radLocation.locCity"
									id="patientCity" placeholder="City" required="true"
									class="validate[required] text-input" maxlength="160" />
							</div>

							<div class="small-12 medium-12 large-4 columns">
								<form:select path="patientInfo.radLocation.redState.stateId"
									id="PatientState" onkeyup="stoppedTyping()">
									<form:option value="0" label="Select" />
									<c:forEach items="${stateList}" var="state">
										<form:option value="${state.stateId}"
											label="${state.stateName}" />
									</c:forEach>
								</form:select>
							</div>

							<div class="small-12 medium-12 large-4 columns">
								<form:input path="patientInfo.radLocation.locZip"
									id="patientZip" placeholder="Zip" required="true"
									class="validate[required] text-input" maxlength="10" />
							</div>
						</div>

						<div id="padding-0" class="small-12 medium-12 large-4 columns">

							<div class="small-12 medium-12 large-6 columns">
								<form:input path="patientInfo.radLocation.locPhone"
									id="patientPhone" placeholder="Phone" 
									 maxlength="10" />
							</div>

							<div class="small-12 medium-12 large-6 columns">
								<form:input path="patientInfo.radLocation.locFax"
									id="patientFax" placeholder="Fax" maxlength="10" />
							</div>
						</div>

						<div class="small-12 medium-12 large-4 columns">
							<form:select path="patientInfo.patientGender" id="gender"
								required="true">
								<form:option value="G" label="Gender" />
								<form:option value="M" label="Male" />
								<form:option value="F" label="Female" />
							</form:select>
							<form:hidden path="patientInfo.patientGender" id="PGender" />
						</div>

						<div class="small-12 medium-12 large-4 columns">
							<input type="text" id="dob" placeholder="MM/dd/yyyy"
								required="true"
								class="textfield validate[required,custom[date]]" />
							<form:hidden path="patientInfo.patientDob" id="pDate"
								placeholder="DOB" required="true"
								class="validate[required] text-input" />
						</div>

						<div class="small-12 medium-12 large-4 columns">
							<input type="text" id="ssn1" placeholder="SSN"
								maxlength="9" />
							<form:hidden path="patientInfo.patientSSN" id="pSsn1"
								placeholder="SSN" maxlength="11" />
						</div> --%>
						
						
						
						
						<div>
							<input type="button" value="Previous" class="small previous"
								onClick="PreviousopenBox2()" style="display: none"/> <input type="button"
								value="next" class="small next" onClick="openBox4()" style="display: none"/>
							<!--verify2()  -->
						</div>
					



					<!-- Insurancw Div start -->
				 <div id="div4" class="black_content"  >
						<!--<p id="demo102" style="color: red"></p> -->
						<div id="main-site-container"
							class="small-12 medium-12 large-12 columns">
							<%-- <div class="small-12 medium-12 large-4 columns">
								<form:input
									path="patientInfo.patientInsuranceInfoList[0].insuranceInfo.insuranceCompany"
									id="insuranceName" placeholder="Insurance Name"
									class="validate[required] text-input" maxlength="160" />
							</div>

							<div class="small-12 medium-12 large-4 columns">
								<form:input
									path="patientInfo.patientInsuranceInfoList[0].patientInsuranceId"
									id="insuranceId" placeholder="Insurance ID"
									class="validate[required,custom[onlyLetterNumber]] text-input"
									maxlength="160" />
							</div>

							<div class="small-12 medium-12 large-4 columns">
								<form:input
									path="patientInfo.patientInsuranceInfoList[0].patientInsuranceGroup"
									placeholder="GroupID" id="group"
									class="validate[required] text-input" maxlength="160" />
							</div> --%>
</div>
                               <div class="buttons-blue-container">
									<input id="insFiles" type="file" name="insfiles" accept="image/*, .pdf,.doc,.docx,.txt,.xls,.xlsx, .ppt" multiple class="small radius button" >
									<!-- <span id="insFileNames">No Files Selected</span> -->
								</div>
								
							 <div id="showDocTable"></div>
						<%-- 	<div class="small-12 medium-12 large-6 columns">
								<form:input
									path="patientInfo.patientInsuranceInfoList[0].patientInsurancePhone"
									placeholder="Insurance Phone" id="insurancePhone"
									class="validate[custom[phone]] text-input" maxlength="15" />
							</div>

							<div class="small-12 medium-12 large-6 columns">
								<input type="text" placeholder="Claim Phone" max="160">
							</div>

							<div id="padding-0" class="small-12 medium-12 large-12 columns">
								<div class="small-12 medium-12 large-6 columns">
									<label>Pre AUTH</label>
									<form:radiobutton
										path="patientInfo.patientInsuranceInfoList[0].patientPre1AuthReq"
										value="Y" id="pokemonRed" onClick="PreAuthYes()" />
									<label for="pokemonRed">Yes</label>
									<!-- <input type="radio" name="patientInfo.patientInsuranceInfoList[0].patientPre1AuthReq" value="N" onClick="PreAuth()" checked="checked"/> -->
									<form:radiobutton
										path="patientInfo.patientInsuranceInfoList[0].patientPre1AuthReq"
										value="N" id="pokemonBlueRadio" onClick="PreAuth()"
										checked="checked" />
									<label for="pokemonBlue1">No</label>
								</div>

								<div class="small-12 medium-12 large-6 columns">
									<form:input
										path="patientInfo.patientInsuranceInfoList[0].patientPreAuthId"
										placeholder="Pre AUTH" id="preAuth"
										class="validate[required] text-input" maxlength="50" />
								</div>
							</div>

							<div id="padding-0" class="small-12 medium-12 large-12 columns">
								<div class="small-12 medium-12 large-6 columns">
									<form:input
										path="patientInfo.patientInsuranceInfoList[0].startDate"
										placeholder="Pre AUTH Date : From" id="PreAuthDate"
										class="textfield validate[required,custom[date]]" />
								</div>

								<div class="small-12 medium-12 large-6 columns">
									<form:input
										path="patientInfo.patientInsuranceInfoList[0].endDate"
										placeholder=" Pre AUTH Date : To" id="toDate"
										class="textfield validate[required,custom[date]]" />
								</div>
							</div>

							<div id="padding-0" class="small-12 medium-12 large-12 columns">
								<div class="small-12 medium-12 large-6 columns">
									<form:input
										path="patientInfo.patientInsuranceInfoList[0].patientPreAuthContactName"
										placeholder="Contact Name" id="preAuthContachNo"
										class="validate[required] text-input" maxlength="160" />
								</div>

								<div class="small-12 medium-12 large-6 columns">
									<form:input
										path="patientInfo.patientInsuranceInfoList[0].patientPreauthConfirmation"
										placeholder="Confirmation" id="confirmation"
										class="validate[required] text-input" maxlength="160" />
								</div>
							</div>

							<div id="padding-0" class="small-12 medium-12 large-12 columns">
								<div class="small-12 medium-12 large-6 columns">
									<form:textarea
										path="patientInfo.patientInsuranceInfoList[0].patientInsuranceNotes"
										placeholder="Notes" id="notes"
										 maxlength="160" />
								</div>
								<!-- $$$$$$$$$$$$$$$$$$$$$$$$$$$$ -->
								<!-- <div class="small-12 medium-12 large-6 columns">
									<input id="insFiles" type="file" name="insfiles" accept="image/*, .pdf,.doc,.docx,.txt,.xls,.xlsx, .ppt" multiple class="small radius button" >
									<span id="insFileNames">No Files Selected</span>
								</div> -->
							</div>
							<b></b> --%>
							<!-- 
							<div class="buttons-blue-container">
                    <a href="#"><input type="button" name="Attach docs" value="Add More Insurance" class="medium secondary button"></a>
                        </div>
                         -->
							<input type="button" value="Previous" class="small previous"
								onClick="PreviousopenBox3()" style="display: none"/> <input type="button"
								value="next" class="small next" onClick="return openBox5()" style="display: none"/>

						</div>
						
				<!-- 	 -->




					<div id="div5" class="black_content">
						<div class="small-12 medium-12 large-12 columns" style="padding: 0px 0px 0px 0px;">
						
							<!-- <b>Please select all four drop downs</b> -->
							
					<label>Refer To:</label>		
					<div style="border:1px solid black;height: 69px;padding: 1rem 0px 0px 0px;"> 
							
							
							<div class="small-12 medium-6 large-3 columns" style="display:none;">
								<form:select path="chInfo.id" id="chearingHouseId">
									<form:option value="0" label="Select" />
									<c:forEach items="${chlearingHouseList}" var="refCh">
										<form:option value="${refCh.id}"
											label="${refCh.name}" />
									</c:forEach>
								</form:select>
							</div>
							<div class="small-12 medium-6 large-3 columns">
								<form:select path="practiceSpecialty.praticeSplID"
									id="practiceSpectialtyId">
									<form:option value="0" label="Select Specialty" />
								</form:select>
							</div>
							<div class="small-12 medium-6 large-3 columns">
								<form:select path="practiceInfo.practiceId" id="practiceInfoId">
									<form:option value="0" label="Select Practice" />
								</form:select>
							</div>
							<div class="small-12 medium-6 large-3 columns">
								<form:select path="providerInfo.providerId" id="providerInfoId">
									<form:option value="0" label="Select Provider" />
								</form:select>
							</div>
							<div class="small-12 medium-6 large-3 columns">
								<input type="button" onclick="callSchedule()" id="callScheduleid" value="Schedule" style="background-color: #008CBA;height:37px; font-size: 0.8125rem;" disabled="disabled"   >
							</div>
							</div>
							<br>
							
							<div class="modal fade" id="shaduleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" style="display: none;">
      <div class="modal-dialog">
        <div class="modal-content large-12 columns">
          <div class="modal-header">
            <button onclick="closeschedulediv()" type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><img class="close" src="bootstrap/alert_boxes_close_ico.png"></span></button>
            <h4 class="modal-title" id="exampleModalLabel">Schedule</h4>
          </div>
          <div id="mailsent" class="modal-body">
            <form>
            <div class="form-group">
             <input type="checkbox" disabled="true" id="day7" /> <label for="day7" class="control-label" >Sun</label> 
            <input type="checkbox" disabled="true" id="day1" /> <label for="day1" class="control-label" >Mon</label> 
            <input type="checkbox" disabled="true" id="day2" /> <label for="day2" class="control-label" >Tue</label>
            <input type="checkbox" disabled="true" id="day3" /> <label for="day3" class="control-label" >Wed</label> 
            <input type="checkbox" disabled="true" id="day4" /> <label for="day4" class="control-label" >Thu</label> 
            <input type="checkbox" disabled="true" id="day5" /> <label for="day5" class="control-label" >Fri</label>
            <input type="checkbox" disabled="true" id="day6" /> <label for="day6" class="control-label" >Sat</label>   
           
               </div>  
              <div class="form-group">
                <label for="datetimepicker" class="control-label" >Select Date</label>        
				<input type="text" class="form-control" onchange="dateselected()"  id="datetimepicker"/><br>
              </div>
              <div class="form-group">
                <label for="datetimepicker2" class="control-label" >Select Time</label>  
                <select class="form-control"  id="timeselect" >
                <option>__:__</option>
                </select>      
				<!-- <input type="text" class="form-control" id="datetimepicker2"/><br> -->
              </div>
              <div class="form-group">
                <label for="message-text" class="control-label">Notes</label>
                <textarea class="form-control" id="sheduleNotes" ></textarea>
              </div>
              <div class="modal-footer">
           
            <button type="button" class="btn btn-primary" onclick="return saveShedule()" id="sheduleButton">Continue</button>
          </div>
            </form>
          </div>
          
        </div>
      </div>
    </div><!-- /.modal -->
  <!-- 	<h1>schedule</h1> -->
							
							
							
				<label>Clinical Notes:</label>		
					<div style="border:1px solid black;height: 215px;padding: 1rem 1rem 1rem 1rem;"> 
							<form:textarea
								path="referral_Provider_ActionList[0].providerRefReasons"
								placeholder="Referal Reason:Evaluate & Treat:" id="PrvoRederalReason" onfocus="return defaultReferralReason()"  class="validate[required] text-input" maxlength="250"/>
							<form:textarea
								path="referral_Provider_ActionList[0].providerDiagCode"
								placeholder="Diagnosis Code" id="PrvoDiagosis" maxlength="50"/>
							<form:textarea
								path="referral_Provider_ActionList[0].providerNotes"
								placeholder="Additional Notes" id="additionalNotes" maxlength="250"/>
						</div>	
							
							
							<%-- <div class="row"></div>
							<div class="small-12 medium-6 large-3 columns">
								<form:select path="chInfo.id" id="chearingHouseId">
									<form:option value="0" label="Select" />
									<c:forEach items="${chlearingHouseList}" var="refCh">
										<form:option value="${refCh.id}"
											label="${refCh.name}" />
									</c:forEach>
								</form:select>
							</div>
							<div class="small-12 medium-6 large-3 columns">
								<form:select path="practiceSpecialty.praticeSplID"
									id="practiceSpectialtyId">
									<form:option value="0" label="Select" />
								</form:select>
							</div>
							<div class="small-12 medium-6 large-3 columns">
								<form:select path="practiceInfo.practiceId" id="practiceInfoId">
									<form:option value="0" label="Select" />
								</form:select>
							</div>
							<div class="small-12 medium-6 large-3 columns">
								<form:select path="providerInfo.providerId" id="providerInfoId">
									<form:option value="0" label="Select" />
								</form:select>
							</div>
 --%>

							<div class="buttons-blue-container">
								<input id="referFiles"   type="file" name="referfiles" accept="image/*, .pdf,.doc,.docx,.txt,.xls,.xlsx, .ppt" class="small radius button" multiple style=" width:80%;" >
								<!-- <span id="referFileNames">No Files Selected</span> -->
							</div>
						
							<div class="row"></div>
							
							<!--DO NOT DELETE THIS ROW-->
							<div class="small-12 medium-12 large-12 columns">
								<input type="submit" name="Submit" value="Submit"
									class="small radius button"  onclick="return Refralreasonvalidation1()">
							</div>
							</div>
					  	    
						<input type="button" value="Previous" class="small previous"
							onClick="PreviousopenBox4()" style="display: none"/>
					</div>
				</div>
				</div></div>
			</div>
		</div>
		</div>
	</form:form>
</body>
<script src="js/page-js/jquery.datetimepicker.js"></script>
<script>
/* 
$('#datetimepicker').datetimepicker({
dayOfWeekStart : 1,
lang:'en',
disabledDates:['1986/01/08','1986/01/09','1986/01/10'],
startDate:	'2015/01/05'
});
$('#datetimepicker').datetimepicker({value:'2015/04/15 05:03',step:10});

$('.some_class').datetimepicker();
 */
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
<script type="text/javascript">
jQuery('#datetimepicker2').datetimepicker({
	  datepicker:false,
	  format:'H:i',
	  step:30 
	});
</script>
</html>
