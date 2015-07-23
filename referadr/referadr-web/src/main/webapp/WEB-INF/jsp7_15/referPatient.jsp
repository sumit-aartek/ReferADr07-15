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
<link rel="stylesheet" type="text/css" href="css/jquery.datetimepicker.css" />
<script type="text/javascript" src="js7_15/jsp-js/referPatientMain.js"></script>

<link rel="stylesheet" href="jQuery/css/validationEngine.jquery.css" type="text/css" />
<script src="jQuery/js/languages/jquery.validationEngine-en.js" type="text/javascript" charset="utf-8"></script>
<script src="jQuery/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>






<!--date picker starts here-->
		<link rel="stylesheet" type="text/css" href="css7_15/datepicker/datepickr.min.css">
	<!--date picker ends here-->
	
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

			 var uploadfile = document.getElementById("insFiles");
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
			 var uploadfile = document.getElementById("insFiles");
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
	<div id="content-inner-main-right"
		class="small-12 medium-9 large-9 columns">
		<div class="main-content-inside">
			<div id="breadcrumbs-container"
				class="small-12 medium-12 large-12 columns">
				<ul class="breadcrumbs">
					<li><a href="#">Refer a Patient</a></li>
				</ul>
			</div>
			<!--#breadcrumbs-container-->
			<div class="panel callout">
				<form:form id="formID" method="POST" action="saveAllDetails.do"
					modelAttribute="PatientReferralInfo" autocomplete="off"
					enctype="multipart/form-data"
					onKeyPress="return disableEnterKey(event)" onsubmit="fillValues()"
					class="refer-a-patient-page-form">
					
					
		<div id="warningmsgreferpatient11">
			<p id="demo11" style="color: red"></p>
		</div>
				
		<jsp:useBean id="today" class="java.util.Date" />
		<fmt:formatDate var="todayString" value="${today}" pattern="MM/dd/yyyy" />
		
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
					
					
					<div class="small-12 medium-12 large-12 columns">
						<h3>
							<span class="circular-number">1</span>Refering Doctor
						</h3>
					</div>
					
					<div class="small-12 medium-6 large-6 columns">
						<form:select path="proId" id="referring" readonly="true">
								<form:option value="0" label="Select Referring Doctor" />
								<c:forEach items="${ProviderInfo}" var="refProviderInfo">
									<form:option value="${refProviderInfo.providerId}"
										label="${refProviderInfo.providerFirstName} ${refProviderInfo.providerLastName}" />
								</c:forEach>
							</form:select>
					</div>
					<div class="small-12 medium-6 large-6 columns">
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
					<form:hidden path="practiceInfo.practiceLocations.radLocation.locId" id="addId" />
					<form:hidden path="practiceInfo.practiceLocations.radLocation.redState.stateId" id="statId" />

					<div class="small-12 medium-12 large-12 columns">
						<h3>
							<span class="circular-number">2</span>Patient Information
						</h3>
					</div>
					<div class="small-12 medium-6 large-6 columns">
							<!--  <input type="text" placeholder="First Name">  -->
							<form:input path="patientInfo.patientFirstName" id="patientName"
								placeholder="First Name" required="true"
								class="validate[required,custom[onlyLetterNumber]] text-input"
								maxlength="160" />
					</div>
					<div class="small-12 medium-6 large-6 columns">
						<form:input path="patientInfo.patientLastName"
								id="patientLastName" placeholder="last Name" required="true"
								class="validate[required,custom[onlyLetterNumber]] text-input"
								maxlength="160" />
					</div>

					<div class="small-12 medium-6 large-6 columns">
						<input id="dob" placeholder="DOB" type="text" class="textfield validate[required]">
						<form:hidden path="patientInfo.patientDob" id="pDate" placeholder="DOB" required="true" class="validate[required] text-input" />
					</div>

					<div class="small-12 medium-6 large-6 columns">
						<form:input path="patientInfo.radLocation.locPhone"
								id="patientPhone" placeholder="Phone"
								pattern="^\d{3}-\d{3}-\d{4}$" onkeyup="formatPhoneNumber()"
								class="validate[custom[phone]] text-input" required="true"
								maxlength="15" />
					</div>

					<div class="clearfix"></div>

					<div class="small-12 medium-6 large-6 columns">
						<form:select path="patientInfo.patientGender" id="gender">
								<form:option value="G" label="Gender" />
								<form:option value="M" label="Male" />
								<form:option value="F" label="Female" />
							</form:select>
							<form:hidden path="patientInfo.patientGender" id="PGender" />
					</div>

					<div class="small-12 medium-6 large-6 columns">
					<form:input path="patientInfo.patientEmail" placeholder="Email"
								class="validate[custom[email]]" id="emailId" />
					</div>

					<div class="clearfix"></div>

					<div class="small-12 medium-6 large-6 columns">
						<p>
							<small>Is Insurance Info Available:</small>
					<!-- 		 <input name="Yes"
								type="radio" value="Yes" id="yes-radio"> -->
										<form:radiobutton
								path="patientInfo.patientInsuranceInfoList[0].patientPre1AuthReq"
								value="Y" id="insuranceyes" checked="checked"
								onclick="insuranceyess()" />
								 <label
								for="insuranceyes">Yes</label> 
								<!-- <input name="Yes" type="radio"
								value="Yes" id="no-radio"> -->
								<form:radiobutton
								path="patientInfo.patientInsuranceInfoList[0].patientPre1AuthReq"
								value="N" id="insuranceno" onClick="insurancenoo()" />
								 <label for="insuranceno">No</label>
						</p>
					</div>

					<div class="small-12 medium-6 large-6 columns">
								<form:input
								path="patientInfo.patientInsuranceInfoList[0].insuranceInfo.insuranceCompany"
								id="insuranceName" placeholder="Insurance Name" maxlength="160" />
					</div>

					<div class="clearfix"></div>

					<div class="small-12 medium-6 large-6 columns">
							<form:input
								path="patientInfo.patientInsuranceInfoList[0].patientInsuranceId"
								id="insuranceId" placeholder="Insurance ID"
								class="validate[custom[onlyLetterNumber]] text-input"
								maxlength="50" />
					</div>

					<div class="small-12 medium-6 large-6 columns">
							<form:input
								path="patientInfo.patientInsuranceInfoList[0].patientInsuranceGroup"
								placeholder="Group ID" id="group" maxlength="160" />
					</div>

					<div class="clearfix"></div>

					<div class="small-12 medium-12 large-12 columns">
						<input type="file" id="insFiles" name="insfiles" accept="image/*, .pdf,.doc,.docx,.txt,.xls,.xlsx, .ppt" id="fileToUpload" multiple>
					</div>

					<div class="small-12 medium-4 large-4 columns">
						<h3>
							<span class="circular-number">3</span>Refer To
						</h3>
					</div>

					<div style="display: none;" class="small-12 medium-8 large-8 columns  select-radio-option">
						<p>
							<!-- <input name="Yes" type="radio" value="Yes" id="specialist-radio"
								checked="checked"> -->
								 <input type="radio" name="referToRadio" value="yellow" id="noCheck" checked>
								 <label for="noCheck">Specialist</label>
							 <input type="radio" name="referToRadio" value="green" id="yesCheck">
							<label for="yesCheck">Lab</label>
						</p>
					</div>
					<div class="clearfix"></div>
						<dir id="LabDiv">
						lab<br>
						<div id="labform">
						lab form
						</div>
						</dir>
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


					<div class="small-12 medium-6 large-6 columns">
					<form:select path="practiceSpecialty.praticeSplID"
						id="practiceSpectialtyId">
						<form:option value="0" label="Select Specialty" />
					</form:select>
					</div>

					<div class="small-12 medium-6 large-6 columns">
					<form:select path="practiceInfo.practiceId" id="practiceInfoId">
						<form:option value="0" label="Select Practice" />
					</form:select>
					</div>

					<div class="clearfix"></div>

					<div class="small-12 medium-6 large-6 columns">
					<form:select path="providerInfo.providerId" id="providerInfoId">
						<form:option value="0" label="Select Provider" />
					</form:select>
					</div>

					<div class="small-12 medium-6 large-6 columns">
	
							<input type="button" onclick="callSchedule()" class="button" id="callScheduleid" value="Schedule" disabled="disabled">
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
					<div class="clearfix"></div>



					<div class="small-12 medium-12 large-12 columns">
						<h3>
							<span class="circular-number">4</span>Clinical Notes
						</h3>
					</div>

					<div class="small-12 medium-6 large-6 columns">
						<form:textarea
						path="referral_Provider_ActionList[0].providerRefReasons"
						placeholder="Referal Reason:Evaluate & Treat:"
						id="PrvoRederalReason" onfocus="return defaultReferralReason()"
						class="validate[required] text-input" maxlength="250" />
					</div>

					<div class="small-12 medium-6 large-6 columns">
						
							<form:textarea
						path="referral_Provider_ActionList[0].providerDiagCode"
						placeholder="Diagnosis Code" id="PrvoDiagosis" maxlength="50" />
					</div>

					<div class="clearfix"></div>
					<div class="small-12 medium-6 large-6 columns">
						<form:textarea path="referral_Provider_ActionList[0].providerNotes"
						placeholder="Additional Notes" cols="3" rows="" id="additionalNotes"
						maxlength="250" />
					</div>

					<div class="small-12 medium-6 large-6 columns">
						<input type="file" id="referFiles" name="referfiles" accept="image/*, .pdf,.doc,.docx,.txt,.xls,.xlsx, .ppt" multiple>
					</div>

					<div class="clearfix"></div>

					<div class="small-12 medium-12 large-12 columns">
						<input name="Submit" type="submit" value="Submit"
							class="button alignright">
					</div>
</div>
				</form:form>
				<!--.refer-a-patient-page-form-->




			</div>
			<!--.panel callout-->
		</div>
		<!--.main-content-inside-->
	</div>
	<!--#content-inner-main-right-->
	<footer id="footer-container">
	<div class="footer-inner-container">
		<div class="row"></div>
	</div>
	<!--.footer-inner-container--> </footer>
	<!--#footer-container-->
	<!--Only For DatePicker Starts Here-->
        <script src="js7_15/datepicker/datepickr.min.js"></script>
        <script>
            // Regular datepickr
            datepickr('#dob');

            // Custom date format
            datepickr('.datepickr', { dateFormat: 'd-m-Y'});

            // Min and max date
            datepickr('#minAndMax', {
                // few days ago
                minDate: new Date().getTime() - 2.592e8,
                // few days from now
                maxDate: new Date().getTime() + 2.592e8
            });

            // datepickr on an icon, using altInput to store the value
            // altInput must be a direct reference to an input element (for now)
            datepickr('.calendar-icon', { altInput: document.getElementById('calendar-input') });

            // If the input contains a value, datepickr will attempt to run Date.parse on it
            datepickr('[title="parseMe"]');

            // Overwrite the global datepickr prototype
            // Won't affect previously created datepickrs, but will affect any new ones
            datepickr.prototype.l10n.months.shorthand = ['janv', 'févr', 'mars', 'avril', 'mai', 'juin', 'juil', 'août', 'sept', 'oct', 'nov', 'déc'];
            datepickr.prototype.l10n.months.longhand = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
            datepickr.prototype.l10n.weekdays.shorthand = ['dim', 'lun', 'mar', 'mer', 'jeu', 'ven', 'sam'];
            datepickr.prototype.l10n.weekdays.longhand = ['dimanche', 'lundi', 'mardi', 'mercredi', 'jeudi', 'vendredi', 'samedi'];
            datepickr('#someFrench.sil-vous-plait', { dateFormat: '\\le j F Y' });
        </script>
<!--Ony For DatePicker Ends Here-->

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