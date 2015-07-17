
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<link rel="stylesheet" type="text/css"
	href="css/jquery.datetimepicker.css" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js" lang="en">
<head>
<meta charset="utf-8" />
<title>Refer a Patient</title>
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
<script src="js/page-js/nextPrevious.js"></script>


<script src="jQuery/js/languages/jquery.validationEngine-en.js"
	type="text/javascript" charset="utf-8"></script>
<script src="jQuery/js/jquery.validationEngine.js"
	type="text/javascript" charset="utf-8"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	 var Speciality = document.getElementById("SpecialityDiv");
	 Speciality.style.display = "block";
	 var Lab = document.getElementById("LabDiv");
	 Lab.style.display = "none";
	 document.getElementById("labform").style.display="none"; 
	
    $('input[type="radio"]').click(function(){
        if($(this).attr("value")=="yellow"){
        	 
        	 var Speciality = document.getElementById("SpecialityDiv");
        	 Speciality.style.display = "block";
        	 var Lab = document.getElementById("LabDiv");
        	 Lab.style.display = "none";
        	 document.getElementById("labform").style.display="none"; 
        }
        if($(this).attr("value")=="green"){
         
          var Speciality = document.getElementById("SpecialityDiv");
         
          document.getElementById("labSubCat").value='0';
          document.getElementById("lab").value='0';
          
     	 Speciality.style.display = "none";
     	 var Lab = document.getElementById("LabDiv");
     	 Lab.style.display = "block";
  
//alert("hi");
$('#labCatagory').empty();
$("#labCatagory").append($("<option value='0'></option>").text("Select Catagory"))
$.ajax({
    url :  "getAllLabCatagory.do",
    type : "GET",
    contentType : "application/json; charset=utf-8",
    success : function(t) {
        var e = t.labCatagories;
        for (i = 0; i < e.length; i++){
        	var cat=e[i]
          $("#labCatagory").append($("<option value='"+ cat.lab_category_id+ "'></option>").text(cat.lab_category_desc))
        }
      },
    error : function() {
    }
  })
     	 
         }

         });
});
</script>

<script type="text/javascript">
function selectService(labService){
	
		if($("#labServiceId_"+labService).prop('checked'))
		{
			
			$('input:checkbox[id^="labSubServiceId_'+ labService +'_"]').each(function(){
		   		 $(this).prop('checked',true);
	   		 });
		}
		else
			{
			
			$('input:checkbox[id^="labSubServiceId_'+ labService +'_"]').each(function(){
			    $(this).prop('checked',false);
			    });
			}
}
</script>


<script type="text/javascript">
function selectServicebySubService(labService){
	
	var n =$('input:checkbox[id^="labSubServiceId_'+ labService +'_"]:checked').length;
	var m =$('input:checkbox[id^="labSubServiceId_'+ labService +'_"]').length;
	if(n==m){
		$("#labServiceId_"+labService).prop('checked',true);
		}else{
			$("#labServiceId_"+labService).prop('checked',false);
		}
}
</script>



<script type="text/javascript">
$(function() {
$("#labCatagory").change(function() {
//alert('hiffff');
var catagoryId=document.getElementById("labCatagory").value;
$('#labSubCat').empty();
$("#labSubCat").append($("<option value='0'></option>").text("Select Sub-Catagory"))
$.ajax({
    url :  "getLabSubCatByCatagory.do?catagoryId="+catagoryId,
    type : "GET",
    contentType : "application/json; charset=utf-8",
    success : function(t) {
        var e = t.labSubCatList;
        for (i = 0; i < e.length; i++){
        	var ser=e[i]
          $("#labSubCat").append($("<option value='"+ ser.lab_sub_category_id+ "'></option>").text(ser.lab_sub_category_desc))
        }
 	      },
    error : function() {
    }
  })
}),	

$("#labSubCat").change(function() {
	//alert('hiffff');
	var labSubCatId=document.getElementById("labSubCat").value;
	$('#lab').empty();
	$("#lab").append($("<option value='0'></option>").text("Select Lab"))
	$.ajax({
	    url :  "getLabInfoList.do?subCatId="+labSubCatId,
	    type : "GET",
	    contentType : "application/json; charset=utf-8",
	    success : function(t) {
	        var e = t.LabInfoList;
	        for (i = 0; i < e.length; i++){
	        	var lab=e[i]
	          $("#lab").append($("<option value='"+ lab.lab_id+ "'></option>").text(lab.lab_desc))
	        }
	 	      },
	    error : function() {
	    }
	  })
	})


$("#lab").change(function() {
	var labId=document.getElementById("lab").value;	
	if(labId>0){
		document.getElementById("labform").style.display="block";    	
	}else{
		document.getElementById("labform").style.display="none";
		}
})	
});
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
			document.getElementById("labform").style.display="none"; 
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
		
		var provider="${providerSize}";
		     if(provider==1){
		      document.getElementById('referring').value="${providerId}";
		     }else{
		      document.getElementById('referring').value=0;
		     }
	  var clearHouselistSize="${clearHouselistSize}";
		     if(clearHouselistSize==1){
		      document.getElementById('chearingHouseId').value="${chid}";
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
		            }

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
				            }  
		             
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
		                	   document.getElementById("callScheduleid").disabled = false;
					            document.getElementById('providerInfoId').value=providerInfoValue.providerId;
					            document.getElementById("scheduleDate").value='';
			    	        	document.getElementById("scheduleTime").value='';
			    	        	document.getElementById("scheduleNotes").value='';
			    	        	document.getElementById("datetimepicker").value='';
			    	        	document.getElementById("timeselect").value='';
			    	        	document.getElementById("sheduleNotes").value='';
			    	        	document.getElementById("scheduleFlag").value='false';
					            }  
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
	          },
	          error : function() {
	          }
	        })
	        }else{
	        	document.getElementById('phone').value="";
	        }
		}

		 <c:if test="${sessionScope.referralInfo!=null}"> 
document.getElementById('patientId').value='${sessionScope.referralInfo.patientInfo.patientId}';
document.getElementById('patientIdHidden').value='${sessionScope.referralInfo.patientInfo.patientId}';
document.getElementById('patientName').value='${sessionScope.referralInfo.patientInfo.patientFirstName}';
document.getElementById('patientLastName').value='${sessionScope.referralInfo.patientInfo.patientLastName}';
document.getElementById('emailId').value='${sessionScope.referralInfo.patientInfo.patientEmail}';
document.getElementById('gender').value='${sessionScope.referralInfo.patientInfo.patientGender}';
document.getElementById('dob').value='${sessionScope.referralInfo.patientInfo.patientDob}';
document.getElementById('patientPhone').value='${sessionScope.referralInfo.patientInfo.patientPhoneNo}';
document.getElementById('additionalNotes').value='${sessionScope.referralInfo.scheduleNotes}';
document.getElementById('referring').value='${sessionScope.referralInfo.proId}';
document.getElementById('practiceSpectialtyId').value='${sessionScope.referralInfo.practiceSpecialty.praticeSplID}';
document.getElementById('group').value='${sessionScope.referralInfo.patientInfo.patientInsuranceInfoList[0].patientInsuranceGroup}';
document.getElementById('insuranceId').value='${sessionScope.referralInfo.patientInfo.patientInsuranceInfoList[0].patientInsuranceId}';
document.getElementById('insuranceName').value='${sessionScope.referralInfo.patientInfo.patientInsuranceInfoList[0].patientInsuranceNotes}';
</c:if>
	});
</script>
<script type="text/javascript">
function defaultReferralReason(){
	var PrvoRederalReason = document.getElementById('PrvoRederalReason').value;
if(PrvoRederalReason==''){
	document.getElementById('PrvoRederalReason').value="Evaluate & Treat.";
}
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
			for (i = 0; i < showData.length; i++) {
				locId.value = showData[i].radLocation.locId;
				patientId.value = showData[i].patientId;
				patientIdHidden.value = showData[i].patientId;
				if (showData[i].patientId == data) {
					recordIndex=i;
					patitentName.value = showData[i].patientFirstName;
					patitentLastName.value = showData[i].patientLastName;
					gender.value = showData[i].patientGender;
					dob.value = showData[i].patientDob;
					phone.value = showData[i].radLocation.locPhone;
					emailId.value = showData[i].patientEmail;
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
			
			if (showData != null && showData != "") {
				for (i = 0; i < showData.length; i++) {
					if (showData[i].patientId == data) {
						var a = showData[i].patientInsuranceInfoList;
						insuranceId.value = a[0].patientInsuranceId;
						group.value = a[0].patientInsuranceGroup;
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

			 var uploadfile = document.getElementById("upload-file");
			 uploadfile.style.display = "block";
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
			 var uploadfile = document.getElementById("upload-file");
			 uploadfile.style.display = "none";
		  }
	
	  }
  </script>
</head>
<body>
	<div class="modal fade" id="insuranceVarification" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true"
		style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content large-12 columns">
				<div class="modal-header">
					<button onclick="closediv()" type="button" class="close"
						data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true"><img class="close"
							src="bootstrap/alert_boxes_close_ico.png"></span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">&nbsp</h4>
				</div>
				<div id="mailsent" class="modal-body">
					<form>
						<div class="form-group">
							<label id="insname"> </label>
						</div>
						<div class="modal-footer">

							<button type="button" class="btn btn-default"
								onclick="cleardiv()">OK</button>
						</div>
					</form>
					<p id="demo222" style="color: red"></p>
				</div>
			</div>
		</div>
	</div>
	<!-- /.modal -->
	<form:form id="formID" method="POST" action="saveAllDetails.do"
		modelAttribute="PatientReferralInfo" autocomplete="off"
		enctype="multipart/form-data"
		onKeyPress="return disableEnterKey(event)" onsubmit="fillValues()">
		<form:hidden path="refId" />
		<form:hidden path="patientInfo.patientId" id="patientId" />
		<form:hidden path="patientInfo.radLocation.locId" id="locId" />
		<form:hidden path="scheduleDate" id="scheduleDate" />
		<form:hidden path="scheduleTime" id="scheduleTime" />
		<form:hidden path="scheduleNotes" id="scheduleNotes" />
		<form:hidden path="schedulFlag" id="scheduleFlag" />
		<form:hidden
			path="patientInfo.patientInsuranceInfoList[0].insuranceInfo.insuranceId"
			id="insuranceIdHideen" />
		<form:hidden
			path="patientInfo.patientInsuranceInfoList[0].patientInfo.patientId"
			id="patientIdHidden" />
		<form:hidden
			path="patientInfo.patientInsuranceInfoList[0].patientInsuranceInfoId"
			id="patientInsuranceInfoIdHidden" />
		<div id="content-right-main"
			class="small-12 medium-12 large-10 columns">
			<div id="main-content-full"
				class="small-12 medium-12 large-12 columns">
				<h1 class="red-heading">REFER A PATIENT</h1>
				<div id="warningmsgreferpatient11">
					<p id="demo11" style="color: red"></p>
				</div>
				<jsp:useBean id="today" class="java.util.Date" />
				<fmt:formatDate var="todayString" value="${today}"
					pattern="MM/dd/yyyy" />
				<form class="refer-patient">
					<div class="refer-doctor">
						<h3 class="circle-span">
							<span>1</span>Referring Doctor
						</h3>
						<div class="clear-both"></div>
						<div id="drops" class="small-12 medium-6 large-6 columns">
							<!-- <select>
                                    <option value="husker">Select Referring Doctor</option>
                                    <option value="starbuck">Starbuck</option>
                                    <option value="hotdog">Hot Dog</option>
                                    <option value="apollo">Apollo</option>
                                  </select> -->
							<form:select path="proId" id="referring" readonly="true">
								<form:option value="0" label="Select Referring Doctor" />
								<c:forEach items="${ProviderInfo}" var="refProviderInfo">
									<form:option value="${refProviderInfo.providerId}"
										label="${refProviderInfo.providerFirstName} ${refProviderInfo.providerLastName}" />
								</c:forEach>
							</form:select>
						</div>
						<div id="drops" class="small-12 medium-6 large-6 columns">
							<!--     <select>
                                    <option value="husker">Select Location</option>
                                    <option value="starbuck">Starbuck</option>
                                    <option value="hotdog">Hot Dog</option>
                                    <option value="apollo">Apollo</option>
                                  </select> -->
							<form:select
								path="practiceInfo.practiceLocations.radLocation.locAddress1"
								id="address1">
								<form:option value="0" label="Select Location" />
								<c:forEach items="${address1List}" var="address1">
									<%-- <form:option value="${address1.locAddress1}" label="${address1.locAddress1}" /> --%>
									<form:option value="${address1.locId}"
										label="${address1.locAddress1}" />
								</c:forEach>
							</form:select>
						</div>
						<form:hidden
							path="practiceInfo.practiceLocations.radLocation.locId"
							id="addId" />
						<form:hidden
							path="practiceInfo.practiceLocations.radLocation.redState.stateId"
							id="statId" />
					</div>
					<!----refer-doctor------>
					<div class="large-12 columns">
						<hr>
					</div>
					<div class="patient-info">
						<h3 class="circle-span">
							<span>2</span>Patient Information
						</h3>
						<div class="clear-both"></div>
						<div id="input-1" class="small-12 medium-6 large-6 columns">
							<!--  <input type="text" placeholder="First Name">  -->
							<form:input path="patientInfo.patientFirstName" id="patientName"
								placeholder="First Name" required="true"
								class="validate[required,custom[onlyLetterNumber]] text-input"
								maxlength="160" />
						</div>
						<div id="input-1" class="small-12 medium-6 large-6 columns">
							<!--  <input type="text" placeholder="Last Name">  -->
							<form:input path="patientInfo.patientLastName"
								id="patientLastName" placeholder="last Name" required="true"
								class="validate[required,custom[onlyLetterNumber]] text-input"
								maxlength="160" />
						</div>
						<div id="input-1" class="small-12 medium-6 large-6 columns">
							<!--  <input type="text" placeholder="DOB"> -->
							<input type="text" id="dob" placeholder="DOB" maxlength="10"
								required="true"
								class="textfield validate[required,custom[date]]" />
							<form:hidden path="patientInfo.patientDob" id="pDate"
								placeholder="DOB" required="true"
								class="validate[required] text-input" />
						</div>
						<div id="input-1" class="small-12 medium-6 large-6 columns">
							<!--  <input type="text" placeholder="Phone"> -->
							<form:input path="patientInfo.radLocation.locPhone"
								id="patientPhone" placeholder="Phone"
								pattern="^\d{3}-\d{3}-\d{4}$" onkeyup="formatPhoneNumber()"
								class="validate[custom[phone]] text-input" required="true"
								maxlength="15" />
						</div>
						<div id="drops" class="small-12 medium-6 large-6 columns">
							<!--  <select>
                                    <option value="husker">Gender</option>
                                    <option value="starbuck">Male</option>
                                    <option value="hotdog">Female</option>
                                  </select> -->
							<form:select path="patientInfo.patientGender" id="gender">
								<form:option value="G" label="Gender" />
								<form:option value="M" label="Male" />
								<form:option value="F" label="Female" />
							</form:select>
							<form:hidden path="patientInfo.patientGender" id="PGender" />
						</div>
						<div id="input-1" class="small-12 medium-6 large-6 columns">
							<!-- <input type="text" placeholder="Email"> -->
							<form:input path="patientInfo.patientEmail" placeholder="Email"
								class="validate[custom[email]]" id="emailId" />
						</div>
						<div class="clearfix"></div>
						<!--DO NOT DELETE THIS DIV-->
						<div id="option-1" class="small-12 medium-6 large-6 columns">
							<label>Is Insurance Info Available:</label>
							<!-- <input type="radio" name="pokemon" value="Red" id="pokemonRed"> -->
							<form:radiobutton
								path="patientInfo.patientInsuranceInfoList[0].patientPre1AuthReq"
								value="Y" id="insuranceyes" checked="checked"
								onclick="insuranceyess()" />
							<label for="pokemonRed">Yes</label>
							<!--  <input type="radio" name="pokemon" value="Blue" id="pokemonBlue"> -->
							<form:radiobutton
								path="patientInfo.patientInsuranceInfoList[0].patientPre1AuthReq"
								value="N" id="insuranceno" onClick="insurancenoo()" />
							<label for="pokemonBlue">No</label>
						</div>
						<div id="input-1" class="small-12 medium-6 large-6 columns">
							<!--  <input type="text" placeholder="Insurance Name"> -->
							<form:input
								path="patientInfo.patientInsuranceInfoList[0].insuranceInfo.insuranceCompany"
								id="insuranceName" placeholder="Insurance Name" maxlength="160" />
						</div>
						<div id="input-1" class="small-12 medium-6 large-6 columns">
							<!-- <input type="text" placeholder="Insurance ID"> -->
							<form:input
								path="patientInfo.patientInsuranceInfoList[0].patientInsuranceId"
								id="insuranceId" placeholder="Insurance ID"
								class="validate[custom[onlyLetterNumber]] text-input"
								maxlength="50" />
						</div>
						<div id="input-1" class="small-12 medium-6 large-6 columns">
							<!-- <input type="text" placeholder="Group ID"> -->
							<form:input
								path="patientInfo.patientInsuranceInfoList[0].patientInsuranceGroup"
								placeholder="Group ID" id="group" maxlength="160" />
						</div>
						<div id="upload-file" class="small-12 medium-6 large-6 columns">
							<div class="small-12 medium-12 large-12 columns no-padding">
								<input type="file" id="insFiles" name="insfiles"
									accept="image/*, .pdf,.doc,.docx,.txt,.xls,.xlsx, .ppt"
									multiple class="file">
								<div class="input-group custom-upload-button">
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-picture"></i></span> <input type="text"
										class="form-control" disabled placeholder="Upload File" >
									<span class="input-group-btn">
										<button class="browse btn btn-primary" type="button">
											<i class="fa fa-search"></i> Browse
										</button>
									</span>
								</div>
							</div>
						</div>
						<!-----uplload file------>
					</div>
					<!-----patient-info------>
					<div class="large-12 columns">
						<hr>
					</div>
					<div class="Fefer-to-2">
						<h3 class="circle-span">
							<span>3</span>Refer To
						</h3>
						 <input type="radio"
							name="referToRadio" value="yellow" id="noCheck" checked>
							 <label
							for="noCheck">Specialist</label>
							
							
							 <input type="radio"
							name="referToRadio"   value="green" id="yesCheck">
							
							 <label for="yesCheck" >Lab</label>
						<div class="clear-both"></div>

						<dir id="LabDiv">

							<div id="drops" 
								class="small-12 medium-6 large-3 columns custom-sawateen">
								<select name="lab_category_id" id="labCatagory">
									<option value="-1">Select Category</option>
									<!-- <option value="starbuck">Toxicology</option>
									<option value="hotdog">Blood Lab</option>
									<option value="apollo">Mouth Swab</option>
									<option value="starbuck">Radiology</option>
									<option value="hotdog">Urine Lab</option> -->
								</select>
							</div>
							<div id="drops" 
								class="small-12 medium-6 large-3 columns custom-sawateen">
								<select name="lab_sub_category_id" id="labSubCat">
										<option value="0">Select Sub-Catagory</option>
									<!-- <option value="starbuck">Blood Work</option>
									<option value="hotdog">Ultrasound</option>
									<option value="apollo">MRI</option>
									<option value="starbuck">XRay</option>
									<option value="hotdog">Toxicology</option>
									<option value="apollo">DNA</option> -->
								</select>
							</div>
							<div id="drops" 
								class="small-12 medium-6 large-3 columns custom-sawateen">
								<select name="lab_id" id="lab">
									<option value="0">Select Lab</option>
									<!-- <option value="starbuck">Urine Lab</option>
									<option value="hotdog">Hot Dog</option>
									<option value="apollo">Apollo</option> -->
								</select>
							</div>

						</dir>
						<div id="labform">
							<!-- <div id="dropsLab"
								class="small-12 medium-12 large-12 columns">
 -->
							<div class="small-12 medium-12 large-6 columns">
								<div class="left-form-heading">
									<input id="checkbox187" type="checkbox"> <label
										for="checkbox187"><h3>Complete Wellness Baseline
											1L,3TT</h3>
										<p>(Perform All panels Listed Below)</p></label>

								</div>
								<!----left-form-heading--------->
							</div>




							<div class="small-12 medium-12 large-6 columns">
								<div class="right-form-heading">
									<input id="checkbox188" type="checkbox"> <label
										for="checkbox188"><h3>Complete Wellness Followup
											1L,3TT</h3>
										<p>(Perform All panels Listed Below)</p></label>

								</div>
								<!------right-form-heading------->
							</div>


							<div class="space-padding-margin"></div>
							<!------space-padding-margin--------->

							<div id="mycustompadding"
								class="small-12 medium-12 large-5 columns custom-1">
								<div id="hematology" class="small-12 medium-4 large-4 columns">
									<div class="hematology-check">
										<div class="bg-label">
											<input id="labServiceId_2" onclick="selectService(2)" name="lab_service_desc" value="2" type="checkbox"><label
												for="checkbox1">Hematology</label>
										</div>
										<!--------bg-label----->
										<div class="hema-check">
											<input id="labSubServiceId_2_22" onclick="selectServicebySubService(2)" name="lab_sub_service_desc" value="22" type="checkbox"><label
												for="checkbox2">CBC</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_2_23" onclick="selectServicebySubService(2)" name="lab_sub_service_desc" value="23" type="checkbox"><label
												for="checkbox3">Ferritin</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_2_24" onclick="selectServicebySubService(2)" name="lab_sub_service_desc" value="24" type="checkbox"><label
												for="checkbox4">Vitamin B12</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_2_25" onclick="selectServicebySubService(2)" name="lab_sub_service_desc" value="25" type="checkbox"><label
												for="checkbox5">Vitamin D</label>
											<div class="clear-both"></div>
											<label>1L, 1TT</label>
										</div>
										<!-----hema-check------->
									</div>
									<!---------hematology check------>
									<div class="inflammation">
										<div class="inlammation-heading">
											<input id="labServiceId_3" onclick="selectService(3)" name="lab_service_desc" value="3" type="checkbox"><label
												for="checkbox6">Inflammation</label>
										</div>
										<div class="left-inflam-check">
											<input id="labSubServiceId_3_26" onclick="selectServicebySubService(3)" name="lab_sub_service_desc" value="26" type="checkbox"><label
												for="checkbox7">CRP</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_3_27" onclick="selectServicebySubService(3)" name="lab_sub_service_desc" value="27" type="checkbox"><label
												for="checkbox8">Homosysteine</label>
										</div>
										<!--------left inflam check----->
										<div class="right-inflam-check">
											<label>1TT</label>
										</div>
										<!------right inflam check-------->
									</div>
									<!-----hema-check------->
								</div>
								<div id="metabolic" class="small-12 medium-8 large-8 columns">
									<div class="metabolic-check">
										<div class="bg-label-1">
											<input id="labServiceId_1" onclick="selectService(1)" name="lab_service_desc" value="1" type="checkbox"><label
												for="labServiceId_1">Metabolic</label>
										</div>
										<!--------bg-label----->
										<div id="meta-left" class="small-6 medium-6 large-6 columns">
											<input id="labSubServiceId_1_1" onclick="selectServicebySubService(1)" name="lab_sub_service_desc" value="1" type="checkbox"><label
												for="labSubServiceId_1_1">Renal Function</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_1_2" onclick="selectServicebySubService(1)" value="2" name="lab_sub_service_desc" type="checkbox"><label
												for="labSubServiceId_1_2">BUN</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_1_3" onclick="selectServicebySubService(1)" value="3" name="lab_sub_service_desc" type="checkbox"><label
												for="labSubServiceId_1_3">Creatinine</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_1_4" onclick="selectServicebySubService(1)" value="4"  name="lab_sub_service_desc" type="checkbox"><label
												for="labSubServiceId_1_4">eGFR</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_1_5" onclick="selectServicebySubService(1)" value="5" name="lab_sub_service_desc" type="checkbox"><label
												for="labSubServiceId_1_5">Sodium</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_1_6" onclick="selectServicebySubService(1)" value="6" name="lab_sub_service_desc" type="checkbox"><label
												for="labSubServiceId_1_6">Potassium</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_1_7" onclick="selectServicebySubService(1)" value="7" name="lab_sub_service_desc" type="checkbox"><label
												for="labSubServiceId_1_7">Anion Gap</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_1_8" onclick="selectServicebySubService(1)" value="8" name="lab_sub_service_desc" type="checkbox"><label
												for="labSubServiceId_1_8">Chloride</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_1_9" onclick="selectServicebySubService(1)" value="9" name="lab_sub_service_desc" type="checkbox"><label
												for="labSubServiceId_1_9">Bicarbonate (CO3)</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_1_10" onclick="selectServicebySubService(1)" onclick="selectServicebySubService(1)" value="10" name="lab_sub_service_desc" type="checkbox"><label
												for="labSubServiceId_1_10">Calcium</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_1_11" onclick="selectServicebySubService(1)" value="11" name="lab_sub_service_desc" type="checkbox"><label
												for="labSubServiceId_1_11">Phosphorus</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_1_12" onclick="selectServicebySubService(1)" value="12" name="lab_sub_service_desc" type="checkbox"><label
												for="labSubServiceId_1_12">Magnesium</label>
										</div>

										<div id="meta-right" class="small-6 medium-6 large-6 columns">
											<input id="labSubServiceId_1_13" onclick="selectServicebySubService(1)" value="13" name="lab_sub_service_desc" type="checkbox"><label
												for="labSubServiceId_1_13">Magnesium</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_1_14" onclick="selectServicebySubService(1)"  value="14" name="lab_sub_service_desc" type="checkbox"><label
												for="labSubServiceId_1_14">ALT</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_1_15" onclick="selectServicebySubService(1)" value="15"  name="lab_sub_service_desc" type="checkbox"><label
												for="labSubServiceId_1_15">AST</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_1_16" onclick="selectServicebySubService(1)" value="16" name="lab_sub_service_desc" type="checkbox"><label
												for="labSubServiceId_1_16">ALP</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_1_17" onclick="selectServicebySubService(1)" value="17" name="lab_sub_service_desc" type="checkbox"><label
												for="labSubServiceId_1_17">Albumin</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_1_18" onclick="selectServicebySubService(1)" value="18" name="lab_sub_service_desc" type="checkbox"><label
												for="labSubServiceId_1_18">Total bilirubin</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_1_19" onclick="selectServicebySubService(1)" value="19" name="lab_sub_service_desc" type="checkbox"><label
												for="labSubServiceId_1_19">Direct bilirubin</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_1_20" onclick="selectServicebySubService(1)" value="20" name="lab_sub_service_desc" type="checkbox"><label
												for="labSubServiceId_1_20">Total Protein</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_1_21" onclick="selectServicebySubService(1)" value="21" name="lab_sub_service_desc" type="checkbox"><label
												for="labSubServiceId_1_21">Amylase</label>
											<div class="clear-both"></div>
											<label>2 TT</label>

										</div>
									</div>
									<!-------metabolic-check----->
								</div>
								<!------#metabolic------>
							</div>

							<div id="mycustompadding"
								class="small-12 medium-12 large-4 columns custom-2">
								<div id="prevention" class="small-12 medium-6 large-6 columns">
									<div class="prevention-head">
										<input id="labServiceId_4" onclick="selectService(4)" name="lab_service_desc" value="4" type="checkbox"><label
											for="checkbox30">Diabetes Prevention</label>
									</div>
									<!-------prevention-head------>
									<div class="prevention-check">
										<div id="prevent-left"
											class="small-6 medium-6 large-6 columns">
											<input id="labSubServiceId_4_28" onclick="selectServicebySubService(4)" name="lab_sub_service_desc" value="28" type="checkbox"><label
												for="checkbox31">Glucose</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_4_29" onclick="selectServicebySubService(4)" name="lab_sub_service_desc" value="29" type="checkbox"><label
												for="checkbox32">HBA 1C</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_4_30" onclick="selectServicebySubService(4)" name="lab_sub_service_desc" value="30" type="checkbox"><label
												for="checkbox33">Avg Glucose</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_4_31" onclick="selectServicebySubService(4)" name="lab_sub_service_desc" value="31" type="checkbox"><label
												for="checkbox34">Insulin</label>
										</div>
										<!-------#prevent-left---------->
										<div id="prevent-right"
											class="small-6 medium-6 large-6 columns">
											<input id="labSubServiceId_4_32" onclick="selectServicebySubService(4)" name="lab_sub_service_desc" value="32" type="checkbox"><label
												for="checkbox35">Growth Harmone</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_4_33" onclick="selectServicebySubService(4)" name="lab_sub_service_desc" value="33" type="checkbox"><label
												for="checkbox36">Cortisol</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_4_34" onclick="selectServicebySubService(4)" name="lab_sub_service_desc" value="34" type="checkbox"><label
												for="checkbox37">C peptide</label>
											<div class="clear-both"></div>
											<label>1L, 1 TT</label>
										</div>
										<!-------#prevent-right---------->
									</div>
									<!--------prevention-check------>
								</div>
								<!----#prevention----->

								<div id="card" class="small-12 medium-6 large-6 columns">
									<div class="card-head">
										<input id="labServiceId_5" onclick="selectService(5)" name="lab_service_desc" value="5" type="checkbox"><label
											for="checkbox38">Cardiovascular</label>
									</div>
									<!-------card-head------>
									<div class="card-check">
										<div id="card-left" class="small-6 medium-6 large-6 columns">
											<input id="labSubServiceId_5_35" onclick="selectServicebySubService(5)" name="lab_sub_service_desc" value="35" type="checkbox"><label
												for="checkbox39">HDL</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_5_36" onclick="selectServicebySubService(5)" name="lab_sub_service_desc" value="36" type="checkbox"><label
												for="checkbox40">LDL</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_5_37" onclick="selectServicebySubService(5)" name="lab_sub_service_desc" value="37" type="checkbox"><label
												for="checkbox41">Cholesterol</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_5_38" onclick="selectServicebySubService(5)" name="lab_sub_service_desc" value="38" type="checkbox"><label
												for="checkbox42">Triglycerides</label>
										</div>
										<!-------#card-left---------->
										<div id="card-right" class="small-6 medium-6 large-6 columns">
											<input id="labSubServiceId_5_39" onclick="selectServicebySubService(5)" name="lab_sub_service_desc" value="39" type="checkbox"><label
												for="checkbox43">Lp(a)</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_5_40" onclick="selectServicebySubService(5)" name="lab_sub_service_desc" value="40" type="checkbox"><label
												for="checkbox44">Apo A</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_5_41" onclick="selectServicebySubService(5)" name="lab_sub_service_desc" value="41" type="checkbox"><label
												for="checkbox45">Apo B</label>
											<div class="clear-both"></div>
											<label>1 TT</label>
										</div>
										<!-------#card-right---------->
									</div>
									<!--------card-check------>
								</div>
								<!---------#card----->

								<div id="other" class="small-12 medium-12 large-12 columns">
									<div class="other-head">
										<input id="checkbox46" type="checkbox"><label
											for="checkbox46">Other</label>
									</div>
									<!-------other-head------>
									<div id="other-left" class="small-6 medium-6 large-6 columns">
										<input type="text"> <input type="text">
									</div>
									<!-----#other left------>

									<div id="other-right" class="small-6 medium-6 large-6 columns">
										<input type="text"> <input type="text">
									</div>
									<!------#other right------>
								</div>
								<!--------#other----->
							</div>

							<div id="mycustompadding"
								class="small-12 medium-12 large-3 columns custom-3">
								<div class="hormone">
									<div class="hormone-head">
										<input id="labServiceId_6" onclick="selectService(6)" name="lab_service_desc" value="6" type="checkbox"><label
											for="checkbox47">Hormone</label>
									</div>
									<!-------hormone-head------>
									<div id="hormo-left" class="small-6 medium-6 large-6 columns">
										<input id="labSubServiceId_6_42" onclick="selectServicebySubService(6)" name="lab_sub_service_desc" value="42" type="checkbox"><label
											for="checkbox48">Testosterone</label>
										<div class="clear-both"></div>
										<input id="labSubServiceId_6_43" onclick="selectServicebySubService(6)" name="lab_sub_service_desc" value="43" type="checkbox"><label
											for="checkbox49">Free testosterone(adult male)</label>
										<div class="clear-both"></div>
										<input id="labSubServiceId_6_44" onclick="selectServicebySubService(6)" name="lab_sub_service_desc" value="44" type="checkbox"><label
											for="checkbox50">DHEAs</label>
										<div class="clear-both"></div>
										<input id="labSubServiceId_6_45" onclick="selectServicebySubService(6)" name="lab_sub_service_desc" value="45" type="checkbox"><label
											for="checkbox51">SHBG</label>
										<div class="clear-both"></div>
										<input id="labSubServiceId_6_46" onclick="selectServicebySubService(6)" name="lab_sub_service_desc" value="46" type="checkbox"><label
											for="checkbox52">Cortisol</label>
										<div class="clear-both"></div>
										<input id="labSubServiceId_6_47" onclick="selectServicebySubService(6)" name="lab_sub_service_desc" value="47" type="checkbox"><label
											for="checkbox53">LH</label>
										<div class="clear-both"></div>
										<input id="labSubServiceId_6_48" onclick="selectServicebySubService(6)" name="lab_sub_service_desc" value="48" type="checkbox"><label
											for="checkbox54">FSH</label>
										<div class="clear-both"></div>
										<input id="labSubServiceId_6_49" onclick="selectServicebySubService(6)" name="lab_sub_service_desc" value="49"" type="checkbox"><label
											for="checkbox55">Growth hormone</label>
										<div class="clear-both"></div>
										<input id="labSubServiceId_6_50" onclick="selectServicebySubService(6)" name="lab_sub_service_desc" value="50" type="checkbox"><label
											for="checkbox56">Free T3</label>
									</div>
									<!-----#hormo left------->
									<div id="hormo-right" class="small-6 medium-6 large-6 columns">
										<input id="labSubServiceId_6_51" onclick="selectServicebySubService(6)" name="lab_sub_service_desc" value="51" type="checkbox"><label
											for="checkbox57">Prolactin</label>
										<div class="clear-both"></div>
										<input id="labSubServiceId_6_52" onclick="selectServicebySubService(6)" name="lab_sub_service_desc" value="52" type="checkbox"><label
											for="checkbox58">HCG Beta (adult female)</label>
										<div class="clear-both"></div>
										<input id="labSubServiceId_6_53" onclick="selectServicebySubService(6)" name="lab_sub_service_desc" value="53" type="checkbox"><label
											for="checkbox59">Estradiol</label>
										<div class="clear-both"></div>
										<input id="labSubServiceId_6_54" onclick="selectServicebySubService(6)" name="lab_sub_service_desc" value="54" type="checkbox"><label
											for="checkbox60">Progesterone</label>
										<div class="clear-both"></div>
										<input id="labSubServiceId_6_55" onclick="selectServicebySubService(6)" name="lab_sub_service_desc" value="55" type="checkbox"><label
											for="checkbox61">TSH</label>
										<div class="clear-both"></div>
										<input id="labSubServiceId_6_56" onclick="selectServicebySubService(6)" name="lab_sub_service_desc" value="56" type="checkbox"><label
											for="checkbox62">T3 Total</label>
										<div class="clear-both"></div>
										<input id="labSubServiceId_6_57" onclick="selectServicebySubService(6)" name="lab_sub_service_desc" value="57" type="checkbox"><label
											for="checkbox63">T4 Total</label>
										<div class="clear-both"></div>
										<input id="labSubServiceId_6_58" onclick="selectServicebySubService(6)" name="lab_sub_service_desc" value="58" type="checkbox"><label
											for="checkbox64">Osteocalcin</label>
										<div class="clear-both"></div>
										<input id="labSubServiceId_6_59" onclick="selectServicebySubService(6)" name="lab_sub_service_desc" value="59" type="checkbox"><label
											for="checkbox65">PSA (adult male)</label> <label>1 TT</label>
									</div>
									<!-----#hormo right------->
								</div>
								<!----hormone---->
							</div>

							<div class="second-form-row">
								<div id="mycustompadding"
									class="small-12 medium-6 large-3 columns">
									<div id="second-hema"
										class="small-12 medium-12 large-12 columns">
										<div class="second-hema-head">
											<label>Hematology</label>
										</div>
										<!------second hema head------->
										<div class="second-hema-check">
											<input id="checkbox66" type="checkbox"><label
												for="checkbox66">Abnormal blood chemistry 790.6</label>
											<div class="clear-both"></div>
											<input id="checkbox67" type="checkbox"><label
												for="checkbox67">Anemia, unspecified 285.9</label>
											<div class="clear-both"></div>
											<input id="checkbox68" type="checkbox"><label
												for="checkbox68">Vitamin B deficiencies 266.2</label>
											<div class="clear-both"></div>
											<input id="checkbox69" type="checkbox"><label
												for="checkbox69">Vitamin D deficiency 268.9</label>
											<div class="clear-both"></div>
											<input id="checkbox70" type="checkbox"><label
												for="checkbox70">Iron deficiency 280.9</label>
											<div class="clear-both"></div>
											<input id="checkbox71" type="checkbox"><label
												for="checkbox71">Folate-deficiency 281.2</label>
											<div class="clear-both"></div>
											<input id="checkbox72" type="checkbox"><label
												for="checkbox72">Other abnormal blood tests 790.99</label>
											<div class="clear-both"></div>
											<input id="checkbox73" type="checkbox"><label
												for="checkbox73">Routine annual health check up
												V70.0</label>
										</div>
										<!------second-hema-check------>
									</div>
									<!------#second-hema------->

									<div id="second-meta"
										class="small-12 medium-12 large-12 columns">
										<div class="second-meta-head">
											<label>Metabolic</label>
										</div>
										<!--------second-meta-head------->
										<div class="second-meta-check">
											<p>Metabolic</p>
											<input id="checkbox74" type="checkbox"><label
												for="checkbox74">Metabolic Syndrome 277.7</label>
											<div class="clear-both"></div>
											<input id="checkbox75" type="checkbox"><label
												for="checkbox75">Metabolism endocrine, NOS 259.9</label>
											<div class="clear-both"></div>
											<input id="checkbox76" type="checkbox"><label
												for="checkbox76">Obesity, unspecifird 278.00</label>
											<div class="clear-both"></div>
											<input id="checkbox77" type="checkbox"><label
												for="checkbox77">Overweight 278.02</label>
											<div class="clear-both"></div>
											<input id="checkbox78" type="checkbox"><label
												for="checkbox78">Abnormal weight gain 783.1</label>
											<div class="clear-both"></div>
											<input id="checkbox79" type="checkbox"><label
												for="checkbox79">Nutritional deficiency V12.1</label>
											<div class="clear-both"></div>
											<input id="checkbox80" type="checkbox"><label
												for="checkbox80">Malnutrition, moderate 263.0</label>
											<div class="clear-both"></div>
											<input id="checkbox81" type="checkbox"><label
												for="checkbox81">Cushings Syndrome 255.0</label>
											<div class="clear-both"></div>
											<input id="checkbox82" type="checkbox"><label
												for="checkbox82">Glucocorticoid deficiency 255.41</label>
											<div class="clear-both"></div>
											<input id="checkbox83" type="checkbox"><label
												for="checkbox83">Kids BMI> 85% V58.53</label>
											<div class="clear-both"></div>
											<input id="checkbox84" type="checkbox"><label
												for="checkbox84">Bariatric Surgery status V45.86</label>
											<div class="clear-both"></div>
											<p>Liver/Reanal</p>
											<input id="checkbox85" type="checkbox"><label
												for="checkbox85">Chronic nonalcoholic liver disease
												NOS 571.8</label>
											<div class="clear-both"></div>
											<input id="checkbox86" type="checkbox"><label
												for="checkbox86">Recurrent Cystitis 595.9</label>
											<div class="clear-both"></div>
											<input id="checkbox87" type="checkbox"><label
												for="checkbox87">Impaired renal function 589.9</label>
											<div class="clear-both"></div>
											<input id="checkbox88" type="checkbox"><label
												for="checkbox88">Chronic kidney disease, NOS 585.9</label>
											<div class="clear-both"></div>
											<input id="checkbox89" type="checkbox"><label
												for="checkbox89">Kidney stone 274.11</label>
											<div class="clear-both"></div>
											<input id="checkbox90" type="checkbox"><label
												for="checkbox90">Urinary frequency 788.41</label>
											<div class="clear-both"></div>
											<input id="checkbox91" type="checkbox"><label
												for="checkbox91">Abnormal kidney function, NOS 794.4</label>
											<div class="clear-both"></div>
										</div>
										<!------second-meta-check----->
									</div>
									<!-----#second-meta-------->
								</div>

								<div id="mycustompadding"
									class="small-12 medium-6 large-3 columns">
									<div id="second-meta-2"
										class="small-12 medium-12 large-12 columns">
										<div class="second-meta-head-2"></div>
										<!--------second-meta-head-2------>
										<div class="second-meta-check-2">
											<p>Digestive Health</p>
											<input id="checkbox92" type="checkbox"><label
												for="checkbox92">Gerd/ Reflux 530.81</label>
											<div class="clear-both"></div>
											<input id="checkbox93" type="checkbox"><label
												for="checkbox93">Gastritis 535.50</label>
											<div class="clear-both"></div>
											<input id="checkbox94" type="checkbox"><label
												for="checkbox94">Dyspepsia 536.8</label>
											<div class="clear-both"></div>
											<input id="checkbox95" type="checkbox"><label
												for="checkbox95">Biliary Colic 574.20</label>
											<div class="clear-both"></div>
											<input id="checkbox96" type="checkbox"><label
												for="checkbox96">Diarrhea 787.91</label>
											<div class="clear-both"></div>
											<input id="checkbox97" type="checkbox"><label
												for="checkbox97">Abdominal tenderness 789.69</label>
											<div class="clear-both"></div>
											<input id="checkbox98" type="checkbox"><label
												for="checkbox98">Abdominal Pain, NOS 789.00</label>
											<div class="clear-both"></div>
											<input id="checkbox99" type="checkbox"><label
												for="checkbox99">Nausea with Vomiting 787.01</label>
											<div class="clear-both"></div>
											<input id="checkbox100" type="checkbox"><label
												for="checkbox100">Persistent vomiting 536.2</label>
											<div class="clear-both"></div>
											<p>Elderly</p>
											<input id="checkbox101" type="checkbox"><label
												for="checkbox101">Osteoporosis, NOS 733.00</label>
											<div class="clear-both"></div>
											<input id="checkbox102" type="checkbox"><label
												for="checkbox102">Alzheimer's 331.0</label>
											<div class="clear-both"></div>
											<input id="checkbox103" type="checkbox"><label
												for="checkbox103">Mild Cognitive Impairment 331.83</label>
											<div class="clear-both"></div>
											<input id="checkbox104" type="checkbox"><label
												for="checkbox104">Memory Loss 780.93</label>
											<div class="clear-both"></div>
											<input id="checkbox105" type="checkbox"><label
												for="checkbox105">Abdormal Gait 781.2</label>
											<div class="clear-both"></div>
											<input id="checkbox106" type="checkbox"><label
												for="checkbox106">Lack of coordination 781.3</label>
											<div class="clear-both"></div>
											<input id="checkbox107" type="checkbox"><label
												for="checkbox107">Altered mental status 780.97</label>
											<div class="clear-both"></div>
											<input id="checkbox108" type="checkbox"><label
												for="checkbox108">Dementia 290.8</label>
											<div class="clear-both"></div>
											<p>Dermatology</p>
											<input id="checkbox109" type="checkbox"><label
												for="checkbox109">Hirsutism 704.1</label>
											<div class="clear-both"></div>
											<input id="checkbox110" type="checkbox"><label
												for="checkbox110">Pruritus, NOS 698.9</label>
											<div class="clear-both"></div>
											<input id="checkbox111" type="checkbox"><label
												for="checkbox111">Alopecia 704.00</label>
											<div class="clear-both"></div>
											<input id="checkbox112" type="checkbox"><label
												for="checkbox112">Other psoriasis 696.1</label>
											<div class="clear-both"></div>
											<p>Gynecology</p>
											<input id="checkbox113" type="checkbox"><label
												for="checkbox113">Vaginal candidiasis 112.1</label>
											<div class="clear-both"></div>
											<input id="checkbox114" type="checkbox"><label
												for="checkbox114">Amenorrhea 626.0</label>
											<div class="clear-both"></div>
											<input id="checkbox115" type="checkbox"><label
												for="checkbox115">Vulvovaginitis 616.10</label>
											<div class="clear-both"></div>
											<input id="checkbox116" type="checkbox"><label
												for="checkbox116">Irregular menses 626.4</label>
											<div class="clear-both"></div>
											<input id="checkbox117" type="checkbox"><label
												for="checkbox117">Infertility Female 628.9</label>
											<div class="clear-both"></div>
											<input id="checkbox118" type="checkbox"><label
												for="checkbox118">Dysmenorrhea 625.3</label>
											<div class="clear-both"></div>
											<input id="checkbox119" type="checkbox"><label
												for="checkbox119">Vaginal bleeding 623.8</label>
											<div class="clear-both"></div>
											<input id="checkbox120" type="checkbox"><label
												for="checkbox120">Pregnancy V22.0</label>
											<div class="clear-both"></div>
											<p>Respiratory</p>
											<input id="checkbox121" type="checkbox"><label
												for="checkbox121">Shortness of Breath 786.05</label>
											<div class="clear-both"></div>
											<input id="checkbox122" type="checkbox"><label
												for="checkbox122">Dyspnea 786.09</label>
											<div class="clear-both"></div>
											<input id="checkbox123" type="checkbox"><label
												for="checkbox123">Cough 786.2</label>
											<div class="clear-both"></div>
											<input id="checkbox124" type="checkbox"><label
												for="checkbox124">Lung disease, NOS 518.89</label>
											<div class="clear-both"></div>
											<input id="checkbox125" type="checkbox"><label
												for="checkbox125">Chronic Bronchitis 491.0</label>
											<div class="clear-both"></div>
											<input id="checkbox126" type="checkbox"><label
												for="checkbox126">Abnormal chest x-ray 793.19</label>
											<div class="clear-both"></div>
											<input id="checkbox127" type="checkbox"><label
												for="checkbox127">Sleep apnea 327.29</label>
											<div class="clear-both"></div>
										</div>
										<!------second-meta-check-2---->
									</div>
									<!-----#second-meta-2------->
								</div>

								<div id="mycustompadding"
									class="small-12 medium-6 large-3 columns">
									<div id="second-inflam"
										class="small-12 medium-12 large-12 columns">
										<div class="second-inflam-head">
											<label>Inflammation</label>
										</div>
										<!--------second-inflam-head------->
										<div class="second-inflam-check">
											<input id="checkbox128" type="checkbox"><label
												for="checkbox128">Neuropatty, unspecified 356.9</label>
											<div class="clear-both"></div>
											<input id="checkbox129" type="checkbox"><label
												for="checkbox129">VLong-term Use of Medication
												V58.69</label>
											<div class="clear-both"></div>
											<input id="checkbox130" type="checkbox"><label
												for="checkbox130">Myalgia 729.1</label>
											<div class="clear-both"></div>
											<input id="checkbox131" type="checkbox"><label
												for="checkbox131">Fatigue, malaise, weakness 780.79</label>
											<div class="clear-both"></div>
											<input id="checkbox132" type="checkbox"><label
												for="checkbox132">Acute Gout 274.01</label>
											<div class="clear-both"></div>
											<input id="checkbox133" type="checkbox"><label
												for="checkbox133">Gout Unspecified 274.9</label>
											<div class="clear-both"></div>
											<input id="checkbox134" type="checkbox"><label
												for="checkbox134">Alcoholism 303.90</label>
											<div class="clear-both"></div>
											<input id="checkbox135" type="checkbox"><label
												for="checkbox135">Tobacco Use 305.1</label>
											<div class="clear-both"></div>
											<input id="checkbox136" type="checkbox"><label
												for="checkbox136">Personal Hx of Tobacco use V15.82</label>
											<div class="clear-both"></div>
											<input id="checkbox137" type="checkbox"><label
												for="checkbox137">Fever 780.60</label>
											<div class="clear-both"></div>
											<input id="checkbox138" type="checkbox"><label
												for="checkbox138">Chills 780.64</label>
											<div class="clear-both"></div>
											<input id="checkbox139" type="checkbox"><label
												for="checkbox139">Chronic fatigue syndrome 780.71</label>
											<div class="clear-both"></div>
											<input id="checkbox140" type="checkbox"><label
												for="checkbox140">Insomnia 780.50</label>
											<div class="clear-both"></div>
										</div>
										<!------second-inflam-check----->
									</div>
									<!-----#second-inflam-------->

									<div id="second-cardio"
										class="small-12 medium-12 large-12 columns">
										<div class="second-Cardio-head">
											<label>Cardiovascular</label>
										</div>
										<!--------second-cardio-head------->
										<div class="second-cardio-check">
											<input id="checkbox141" type="checkbox"><label
												for="checkbox141">Dizziness 780.4</label>
											<div class="clear-both"></div>
											<input id="checkbox142" type="checkbox"><label
												for="checkbox142">Chest Pain, NOS 786.50</label>
											<div class="clear-both"></div>
											<input id="checkbox143" type="checkbox"><label
												for="checkbox143">Hypertension, NOS 401.9</label>
											<div class="clear-both"></div>
											<input id="checkbox144" type="checkbox"><label
												for="checkbox144">Angina 413.9</label>
											<div class="clear-both"></div>
											<input id="checkbox145" type="checkbox"><label
												for="checkbox145">Cardiovascular disease 429.2</label>
											<div class="clear-both"></div>
											<input id="checkbox146" type="checkbox"><label
												for="checkbox146">Congestive Heart Failure 428.0</label>
											<div class="clear-both"></div>
											<input id="checkbox147" type="checkbox"><label
												for="checkbox147">Family History CAD V17.3</label>
											<div class="clear-both"></div>
											<input id="checkbox148" type="checkbox"><label
												for="checkbox148">Atrial Fibrillation 427.31</label>
											<div class="clear-both"></div>
											<input id="checkbox149" type="checkbox"><label
												for="checkbox149">Vascular disease 440.8</label>
											<div class="clear-both"></div>
											<input id="checkbox150" type="checkbox"><label
												for="checkbox150">Postural Hypotension 458.0</label>
											<div class="clear-both"></div>
											<input id="checkbox151" type="checkbox"><label
												for="checkbox151">Hypercholesterolemia 272.0</label>
											<div class="clear-both"></div>
											<input id="checkbox152" type="checkbox"><label
												for="checkbox152">Hypertrigylceridemia 272.1</label>
											<div class="clear-both"></div>
											<input id="checkbox153" type="checkbox"><label
												for="checkbox153">Hyperlipidemia NOS 272.4</label>
											<div class="clear-both"></div>
											<input id="checkbox154" type="checkbox"><label
												for="checkbox154">Screening for lipid disorders
												V77.91</label>
											<div class="clear-both"></div>
											<input id="checkbox155" type="checkbox"><label
												for="checkbox155">Palpitations 785.1</label>
											<div class="clear-both"></div>
											<input id="checkbox156" type="checkbox"><label
												for="checkbox156">Syncope 780.2</label>
											<div class="clear-both"></div>
										</div>
										<!------second-cardio-check----->
									</div>
									<!-----#second-cardio-------->
								</div>

								<div id="mycustompadding"
									class="small-12 medium-6 large-3 columns">
									<div id="second-diab"
										class="small-12 medium-12 large-12 columns">
										<div class="second-diab-head">
											<label>Diabetes Prevention</label>
										</div>
										<!--------second-diab-head------->
										<div class="second-diab-check">
											<input id="checkbox157" type="checkbox"><label
												for="checkbox157">Insulin Resistance 277.7</label>
											<div class="clear-both"></div>
											<input id="checkbox158" type="checkbox"><label
												for="checkbox158">Diabetes II, controlled 250.00</label>
											<div class="clear-both"></div>
											<input id="checkbox159" type="checkbox"><label
												for="checkbox159">Diabetes II, uncontrolled 250.02</label>
											<div class="clear-both"></div>
											<input id="checkbox160" type="checkbox"><label
												for="checkbox160">Screening for Diabetes Mellittus
												V77.1</label>
											<div class="clear-both"></div>
											<input id="checkbox161" type="checkbox"><label
												for="checkbox161">Prediabetes, abnormal glucose
												790.29</label>
											<div class="clear-both"></div>
											<input id="checkbox162" type="checkbox"><label
												for="checkbox162">Impaired Fasting Glucose 790.21</label>
											<div class="clear-both"></div>
										</div>
										<!------second-diab-check----->
									</div>
									<!-----#second-diab-------->

									<div id="second-hormone"
										class="small-12 medium-12 large-12 columns">
										<div class="second-hormone-head">
											<label>Hormone</label>
										</div>
										<!--------second-hormone-head------->
										<div class="second-hormone-check">
											<input id="checkbox163" type="checkbox"><label
												for="checkbox163">Depression, unspecified 311.0</label>
											<div class="clear-both"></div>
											<input id="checkbox164" type="checkbox"><label
												for="checkbox164">Major depressive disorder,
												recurrent 296.30</label>
											<div class="clear-both"></div>
											<input id="checkbox165" type="checkbox"><label
												for="checkbox165">Unspecified Psychosis 298.9</label>
											<div class="clear-both"></div>
											<input id="checkbox166" type="checkbox"><label
												for="checkbox166">Non psychotic disorder, NOS 300.9</label>
											<div class="clear-both"></div>
											<input id="checkbox167" type="checkbox"><label
												for="checkbox167">Psychosexual Dysfunction 302.70</label>
											<div class="clear-both"></div>
											<input id="checkbox168" type="checkbox"><label
												for="checkbox168">Low Libido 302.72</label>
											<div class="clear-both"></div>
											<input id="checkbox169" type="checkbox"><label
												for="checkbox169">Inhibited sexual desire 302.71</label>
											<div class="clear-both"></div>
											<input id="checkbox170" type="checkbox"><label
												for="checkbox170">Testicular dysfunction 257.2</label>
											<div class="clear-both"></div>
											<input id="checkbox171" type="checkbox"><label
												for="checkbox171">Impotence 607.84</label>
											<div class="clear-both"></div>
											<input id="checkbox172" type="checkbox"><label
												for="checkbox172">Andropause 253.4</label>
											<div class="clear-both"></div>
											<input id="checkbox173" type="checkbox"><label
												for="checkbox173">Menopause 627.2</label>
											<div class="clear-both"></div>
											<input id="checkbox174" type="checkbox"><label
												for="checkbox174">Post Menopausal, NOS 627.9</label>
											<div class="clear-both"></div>
											<input id="checkbox175" type="checkbox"><label
												for="checkbox175">Hormone disorder, unspecified
												259.9</label>
											<div class="clear-both"></div>
											<input id="checkbox176" type="checkbox"><label
												for="checkbox176">Disorders of Thyroid 246.0</label>
											<div class="clear-both"></div>
											<input id="checkbox177" type="checkbox"><label
												for="checkbox177">Hypothyroidism, unspecified 244.9</label>
											<div class="clear-both"></div>
											<input id="checkbox178" type="checkbox"><label
												for="checkbox178">Hyperparathyroidism 252.00</label>
											<div class="clear-both"></div>
											<input id="checkbox179" type="checkbox"><label
												for="checkbox179">Hypoparathyroidism 252.1</label>
											<div class="clear-both"></div>
											<input id="checkbox180" type="checkbox"><label
												for="checkbox180">Screening for Prostrate Cancer
												V76.44</label>
											<div class="clear-both"></div>
											<input id="checkbox181" type="checkbox"><label
												for="checkbox181">Elevated PSA 790.93</label>
											<div class="clear-both"></div>
											<input id="checkbox182" type="checkbox"><label
												for="checkbox182">Prostrate Cancer 185.0</label>
											<div class="clear-both"></div>
											<input id="checkbox183" type="checkbox"><label
												for="checkbox183">BPH 600.0</label>
											<div class="clear-both"></div>
											<input id="checkbox184" type="checkbox"><label
												for="checkbox184">Alopecia 704.00</label>
											<div class="clear-both"></div>
											<input id="checkbox185" type="checkbox"><label
												for="checkbox185">Androgen insensitivity, NOS 259.50</label>
											<div class="clear-both"></div>
											<input id="checkbox186" type="checkbox"><label
												for="checkbox186">Thyroid disorder, NOS 246.8</label>
											<div class="clear-both"></div>
										</div>
										<!------second-hormone-check----->
									</div>
									<!-----#second-hormone-------->
								</div>
							</div>
							<!------second-form-row------->

							<div id="text-area-6" class="small-12 medium-12 large-6 columns">
								<textarea name="labICDNote" cols="" rows=""
									placeholder="Additional ICD 9 Notes"></textarea>
							</div>
							<!---#text-area-6---->

							<div id="text-area-6" class="small-12 medium-12 large-6 columns">
								<textarea name="labNotes" cols="" rows="" placeholder="Notes"></textarea>
							</div>
							<!---#text-area-6---->

							<div id="input-4" class="small-12 medium-12 large-4 columns">
								<input name="labRefPhy" type="text" placeholder="Physician">
							</div>
							<!---#input-4---->

							<div id="input-4" class="small-12 medium-12 large-4 columns">
								<input name="Patient Sig" type="text" placeholder="Patient Sig">
							</div>
							<!---#input-4---->

							<div id="input-4" class="small-12 medium-12 large-4 columns">
								<input name="Date " type="text" value="" placeholder="Date">
							</div>
							<!---#input-4---->
						<!-- 	<div class="small-12 medium-12 large-2 offset-large-10 columns">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Cancel</button>
							</div> -->
				</form>
				<!------checkbox-req----->

			</div>

			<div id="SpecialityDiv">
				<div class="small-12 medium-6 large-6 columns"
					style="display: none;">
					<form:select path="chInfo.id" id="chearingHouseId">
						<form:option value="0" label="Select" />
						<c:forEach items="${chlearingHouseList}" var="refCh">
							<form:option value="${refCh.id}" label="${refCh.name}" />
						</c:forEach>
					</form:select>
				</div>
				<div id="drops"
					class="small-12 medium-6 large-3 columns custom-sawateen">
					<!--   <select>
                                    <option value="husker">Select Speciality</option>
                                    <option value="starbuck">Starbuck</option>
                                    <option value="hotdog">Hot Dog</option>
                                    <option value="apollo">Apollo</option>
                                </select> -->
					<form:select path="practiceSpecialty.praticeSplID"
						id="practiceSpectialtyId">
						<form:option value="0" label="Select Specialty" />
					</form:select>
				</div>
				<div id="drops"
					class="small-12 medium-6 large-3 columns custom-sawateen">
					<!--   <select>
                                    <option value="husker">Select Practice</option>
                                    <option value="starbuck">Starbuck</option>
                                    <option value="hotdog">Hot Dog</option>
                                    <option value="apollo">Apollo</option>
                                </select> -->
					<form:select path="practiceInfo.practiceId" id="practiceInfoId">
						<form:option value="0" label="Select Practice" />
					</form:select>
				</div>
				<div id="drops"
					class="small-12 medium-6 large-3 columns custom-sawateen">
					<!--    <select>
                                    <option value="husker">Select Provider</option>
                                    <option value="starbuck">Starbuck</option>
                                    <option value="hotdog">Hot Dog</option>
                                    <option value="apollo">Apollo</option>
                                </select> -->
					<form:select path="providerInfo.providerId" id="providerInfoId">
						<form:option value="0" label="Select Provider" />
					</form:select>
				</div>
				<div id="input-1"
					class="small-12 medium-6 large-3 columns baqi-width">
					<!--  <input type="text" placeholder="Schedule"> -->
					<input type="button" onclick="callSchedule()" id="callScheduleid"
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
				<!-- /.modal -->
				<!-- 	<h1>schedule</h1> -->
				<div class="large-12 columns text-center">
					<h3 class="circle-span">
						Clinical Notes:
						</h5>
				</div>
				<div id="cliniacal" class="small-12 medium-4 large-4 columns">
					<!--  <textarea placeholder="Refrral Reason:Evaluate & Treat"></textarea> -->
					<form:textarea
						path="referral_Provider_ActionList[0].providerRefReasons"
						placeholder="Referal Reason:Evaluate & Treat:"
						id="PrvoRederalReason" onfocus="return defaultReferralReason()"
						class="validate[required] text-input" maxlength="250" />

				</div>
				<div id="cliniacal" class="small-12 medium-4 large-4 columns">
					<!--  <textarea placeholder="Diagnosis Code"></textarea> -->
					<form:textarea
						path="referral_Provider_ActionList[0].providerDiagCode"
						placeholder="Diagnosis Code" id="PrvoDiagosis" maxlength="50" />
				</div>

				<div id="cliniacal" class="small-12 medium-4 large-4 columns">
					<!--  <textarea placeholder="Additional Notes"></textarea> -->
					<form:textarea path="referral_Provider_ActionList[0].providerNotes"
						placeholder="Additional Notes" id="additionalNotes"
						maxlength="250" />
				</div>
				<div class="clear-both"></div>
				<div id="upload-file"
					class="small-12 medium-6 large-6 large-offset-6 columns">
					<div class="small-12 medium-12 large-12 columns no-padding">
						<div class="small-12 medium-12 large-12 columns no-padding">
							<input id="referFiles" type="file" name="referfiles"
								accept="image/*, .pdf,.doc,.docx,.txt,.xls,.xlsx, .ppt" multiple
								class="file">
							<div class="input-group custom-upload-button">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-picture"></i></span> <input type="text"
									class="form-control" disabled placeholder="Upload File">
								<span class="input-group-btn">
									<button class="browse btn btn-primary" type="button">
										<i class="fa fa-search"></i> Browse
									</button>
								</span>
							</div>
						</div>

					</div>
				</div>
				<!-----uplload file------>
			
			</div>
				<div class="clearfix"></div>
				<div class="clear-both"></div>
				<div id="submit-button"
					class="small-12 medium-6 large-2 large-offset-10 columns">
					<div class="small-12 medium-12 large-12 columns no-padding">
						<!--  <input name="submit" type="button" value="Submit" /> -->
						<input type="submit" name="Submit" value="Submit"
							class="radius button" style="float: right;"> <!-- onclick="return Refralreasonvalidation1()" -->
					</div>
				</div>
				<!-----Submit Button------>
		</div>
		<!----Fefer-to-2---->
		</form>
		<!----refer-patient----->
		</div>
		<!-----#main-content-full------->
		</div>
		<!-----#content right main----------->
	</form:form>
</body>
<script src="js/page-js/jquery.datetimepicker.js"></script>
<script>
 jQuery('#dob').datetimepicker({
	 datepicker:true,
	 timepicker:false,
	 format:'m/d/Y'
	});
</script>


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
<script type="text/javascript">
jQuery('#datetimepicker2').datetimepicker({
	  datepicker:false,
	  format:'H:i',
	  step:30 
	});
</script>
</html>