<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html class="no-js" lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Connecting Doctors</title>
<script src="js/jquery.1.9.1.min.js"></script>
<script src="js/vendor/jquery.ui.widget.js"></script>

<link rel="stylesheet" href="doctor-css/foundation.css" />
<link rel="stylesheet" href="css/style.css" />
<script src="doctor-js/vendor/modernizr.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="responsive/style.css">
<link rel="stylesheet" type="text/css" href="responsive/responsive.css">
	<link href="bootstrap/Untitled-1.css" rel="stylesheet">
	
<link rel="stylesheet" href="jQuery/css/validationEngine.jquery.css" type="text/css" />
<script src="jQuery/js/languages/jquery.validationEngine-en.js" type="text/javascript" charset="utf-8"></script>
<script src="jQuery/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
	
<!--Accordion CSS-->
		<link rel="stylesheet" type="text/css" href="accordian/accordian-style.css">
	<!--Accordion CSS Ends-->
	<script type="text/javascript" src="jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="js/jsp-js/visitreasons.js"></script>
	<script type="text/javascript" src="js/jsp-js/script.js"></script>
	<!--===========================start script=======================================  -->
	<style type="text/css">
	 #float{
	 height: 120px;
	 overflow-y:scroll;
	 }
	
	</style>
	<script>
	var visitidlist=-1;
    jQuery(document).ready(function() {
    	
    	count=0;
    	radioButton=0;
    	 var counter = 2;
    	 var counter1 = 2;
    	 var counter2 = 2;
    	 var counter3 = 2;
    	 $("#formId").validationEngine();
    	
			 $("#addButton").click(function () {
    			
    			var newTextBoxDiv = $(document.createElement('div'))
    				.attr("id", 'TextBoxDiv1' + counter);
    	 
    			newTextBoxDiv.after().html(
    		      '<input type="text" placeholder="Texas Tech University"  name="radProvEduction" class="validate[required]" id="provEduction' + counter + '" value="" maxlength="140" >');
    	 
    			newTextBoxDiv.appendTo("#TextBoxesGroup11");
    			counter++;
    	    });
    	  $("#removeButton").click(function () {
    			if(counter==1){
    	          return false;
    			}   
    			counter--;
    	        $("#TextBoxDiv1" + counter).remove();
    	     });
    	//Use this function to retrieve the values.
    	     $("#getButtonValue").click(function () {
    			var msg = '';
    			for(i=1; i<counter; i++){
    				msg += "\n Textbox #" + i + " : " + $('#textbox' + i).val();
    			}
    	    	
    	     }); 
    	     $("#addButton1").click(function () {
    	    		
    	 		var newTextBoxDiv = $(document.createElement('div'))
    	 			.attr("id", 'TextBoxDiv2' + counter1);
    	  
    	 		newTextBoxDiv.after().html('<input type="text" placeholder="Hospital Affilations"  name="radProvHospital" class="validate[required]" id="proHospital' + counter1 + '" value="" maxlength="140" >');
    	  
    	 		newTextBoxDiv.appendTo("#TextBoxesGroup1");
    	 		counter1++;
    	     });
    	     $("#removeButton1").click(function () {
    	 		if(counter1==1){
    	           return false;
    	 		}   
    	 		counter1--;
    	         $("#TextBoxDiv2" + counter1).remove();
    	      });
				
    	   //Use this function to retrieve the values.
    	     $("#getButtonValue1").click(function () {
    	 		var msg = '';
    	 		for(i=1; i<counter1; i++){
    	 			msg += "\n Textbox #" + i + " : " + $('#textbox' + i).val();
    	 		}
    	     	
    	      });
    	 
    	     $("#addButton2").click(function () {
 	    		
     	 		var newTextBoxDiv = $(document.createElement('div'))
     	 			.attr("id", 'TextBoxDiv3' + counter2);
     	  
     	 		newTextBoxDiv.after().html('<input type="text" placeholder="Professional Memberships"  name="radProviderMemberships[0].provMembership" class="validate[required]" id="proMemberShip' + counter2 + '" value="" maxlength="140" >');
     	  
     	 		newTextBoxDiv.appendTo("#TextBoxesGroup2");
     	 		counter2++;
     	     });
     	     $("#removeButton2").click(function () {
     	 		if(counter2==1){
     	           return false;
     	 		}   
     	 		counter2--;
     	         $("#TextBoxDiv3" + counter2).remove();
     	      });
 				
     	   //Use this function to retrieve the values.
     	     $("#getButtonValue2").click(function () {
     	 		var msg = '';
     	 		for(i=1; i<counter2; i++){
     	 			msg += "\n Textbox #" + i + " : " + $('#textbox' + i).val();
     	 		}
     	     	
     	      });
     	   
 		$("#addButton3").click(function () {
 	    		
     	 		var newTextBoxDiv = $(document.createElement('div'))
     	 			.attr("id", 'TextBoxDiv4' + counter3);
     	  
     	 		newTextBoxDiv.after().html('<input type="text" placeholder="Publications"  name="radProviderPublications[0].provPublication" class="validate[required]" id="proPublication' + counter3 + '" value="" maxlength="140" >');
     	  
     	 		newTextBoxDiv.appendTo("#TextBoxesGroup3");
     	 		counter3++;
     	     });
     	     $("#removeButton3").click(function () {
     	 		if(counter3==1){
     	           return false;
     	 		}   
     	 		counter3--;
     	         $("#TextBoxDiv4" + counter3).remove();
     	      });
 				
     	   //Use this function to retrieve the values.
     	     $("#getButtonValue3").click(function () {
     	 		var msg = '';
     	 		for(i=1; i<counter3; i++){
     	 			msg += "\n Textbox #" + i + " : " + $('#textbox' + i).val();
     	 		}
     	     	
     	      });
    	     itratValue(); 
    	    
 	    });
</script>
	
	<script>  
function sendInfo(id)  
{  
  $('#test').empty();
  $.ajax({
      url : "visitorreason.do?radioId=" + id,
      type : "GET",
      contentType : "application/json; charset=utf-8",
      success : function(t) {
    	  var e = t.visitorreason;
    	  for (i = 0; i < e.length; i++)
    		  {
    	   var container = $('#test');
    	   var inputs = container.find('input');
    	  	  $('<input />', { type: 'checkbox',name:'radVisitReasons', id: 'visit_'+e[i].splVisitRsnId, value: e[i].splVisitRsnId }).appendTo(container);
    	      $('<label />', { 'for': 'visit_', text: e[i].visitReason }).appendTo(container);
    	      $('</br>', {}).appendTo(container);
    	     
if(visitidlist!=null){ 
      	      for(var k=0;k<visitidlist.length;k++){
    	      if(visitidlist[k]==e[i].splVisitRsnId){
				document.getElementById('visit_'+visitidlist[k]).checked=true;
        	      }}}
      } },
      error : function() {
    	  alert("Unable to get request");
      }
    });
    
}  
  
</script> 
	<!--===========================End Script=======================================  -->
<script type="text/javascript">
 
$(document).ready(function(){
	hideInfoForManAndRec();
    var counter = 2;
    $("#addButton").click(function () {
	
		var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'TextBoxDiv' + counter);
 
		newTextBoxDiv.after().html('<label>Textbox #'+ counter + ' : </label>' +
	      '<input type="text" name="textbox' + counter + 
	      '" id="textbox' + counter + '" value="" >');
 
		newTextBoxDiv.appendTo("#TextBoxesGroup");
		counter++;
    });
 
     $("#removeButton").click(function () {
		if(counter==1){
          return false;
		}   
		counter--;
        $("#TextBoxDiv" + counter).remove();
     });
 
	//Use this function to retrieve the values.
     $("#getButtonValue").click(function () {
		var msg = '';
		for(i=1; i<counter; i++){
			msg += "\n Textbox #" + i + " : " + $('#textbox' + i).val();
		}
    	
     });
	
     //itratValue();
  });
</script>
<script type="text/javascript">
var suffixCounting=0;
var SpecialityadioButton=0;
function itratValue(){
	//alert("hi");
	
	var provRoleId="${list[3]}";
	if(provRoleId==477 || provRoleId==478){
	var provId="${list[0]}";
	var provName="${list[1]}";
	var provLastName="${list[2]}";
	var provEmail="${list[5]}";
	
	var provGendId="${list[4]}";
	document.getElementById('firstName').value=provName;
	 document.getElementById('provLastName').value=provLastName;  
	 document.getElementById('provEmailId').value=provEmail;
	 document.getElementById('providerId').value=provId;
	 document.getElementById('provRoleId').value=provRoleId;
	 if(provGendId=='M'){
	     document.getElementById('male').checked=true;
	 }else if(provGendId=='F'){
		 document.getElementById('female').checked=true;	 
	 }
	
	}else{
	
		var provId="${list[0]}";
		var provName="${list[1]}";
		var provLastName="${list[2]}";
		var provEmail="${list[5]}";
		
		var provGendId="${list[4]}";
	var proProfSta ="${list[7]}";
	var proNpiNo ="${list[6]}";
	var proHospitalN ="${list[11]}";
	var proEduc ="${list[14]}";
	var proMemberShip ="${list[12]}";
	var proPublication ="${list[13]}";
	var patientCat ="${list[8]}";
	var provSuffix ="${list[16]}";
	var provSpeciality ="${list[10]}";
	var provLanguage ="${list[17]}";
	var proCertification ="${list[15]}";
	var proVistReason ="${list[9]}";

visitidlist=${listVisitId};

//alert("visitidlist"+visitidlist);
	var proEducation = proEduc.split(","); 
	var eduLength=proEducation.length;
	//alert("provRoleId=="+provRoleId);
	var boradLength=proCertification.length;
	
	 var proSuffix=provSuffix.length;
	if(proSuffix>0)
	 {suffixCounting++;}
//alert("boardCertification"+boradLength);
//alert("sufix=="+proSuffix);
var provSpecialityLen=provSpeciality.length;
if(provSpecialityLen>0){
	 SpecialityadioButton++;
}
	
	for(j=0;j<eduLength;j++){
		
	  if(j!=0){ 
		  var divValue=('<input type="text" placeholder="Texas Tech University" name="radProvEduction" class="validate[required]" id="provEduction'+(j+1)+'" value="" maxlength="140" >');
	   $("#TextBoxDiv1").append(divValue);
	   document.getElementById('provEduction'+(j+1)).value=proEducation[j];
	  }
	 // alert("provEduction"+(j+1));
	  document.getElementById('provEduction'+(j+1)).value=proEducation[j];
	  
	}
	var proMember=proMemberShip.split(","); 
	var memLength=proMember.length;
	for(k=0;k<memLength;k++){
		
		  if(k!=0){ 
			  var divValue=('<input type="text" placeholder="Professional Memberships"  name="radProviderMemberships[0].provMembership" class="validate[required]" id="proMemberShip'+(k+1) +'" value="" maxlength="140" >');
			  $("#TextBoxDiv3").append(divValue);
			  document.getElementById('proMemberShip'+(k+1)).value=proMember[k];
		  }
		  document.getElementById('proMemberShip'+(k+1)).value=proMember[k];
		  }
	   var proPub=proPublication.split(","); 
	   var publiLength=proPub.length;
	   for(l=0;l<publiLength;l++){
		
		  if(l!=0){ 
			  var divValue=('<input type="text" placeholder="Publications"  name="radProviderPublications[0].provPublication" class="validate[required]" id="proPublication'+(l+1)+'" value="" maxlength="140" >');
			  $("#TextBoxDiv4").append(divValue);
			  document.getElementById('proPublication'+(l+1)).value=proPub[l];
		  }
		  document.getElementById('proPublication'+(l+1)).value=proPub[l];
		  }
	   var proHospital=proHospitalN.split(","); 
	   var provHosLength=proHospital.length;
	   for(m=0;m<provHosLength;m++){
		
		  if(m!=0){ 
			  var divValue=('<input type="text" placeholder="Hospital Affilations"  name="radProvHospital" class="validate[required]" id="proHospital' + (m+1) + '" value="" maxlength="140" >');
			  $("#TextBoxDiv2").append(divValue);
			  document.getElementById('proHospital'+(m+1)).value=proHospital[m];
		  }
		  document.getElementById('proHospital'+(m+1)).value=proHospital[m];
		  }
	 document.getElementById('firstName').value=provName;
	 document.getElementById('provLastName').value=provLastName;  
	 document.getElementById('provEmailId').value=provEmail;
	 document.getElementById('providerId').value=provId;
	 document.getElementById('provRoleId').value=provRoleId;
	 if(provGendId=='M'){
	     document.getElementById('male').checked=true;
	 }else if(provGendId=='F'){
		 document.getElementById('female').checked=true;	 
	 }
	 document.getElementById('providerEditId').value=provId;
	 document.getElementById('providerMemId').value=provId;
	
	 document.getElementById('proSta').value=proProfSta;
	 document.getElementById('npiNo').value=proNpiNo;                      
	
	 var listSuffixId=${listSuffixId};
	 for(var i=0;i<listSuffixId.length;i++){
document.getElementById('checkbox_suffix_'+listSuffixId[i]).checked=true;
		 }
	// document.getElementById('checkbox_suffix_'+provSuffix).checked=true;
	 var listLangSpokenId=${listLangSpokenId};
	 for(var j=0;j<listLangSpokenId.length;j++){
	 document.getElementById('proLanguage_'+listLangSpokenId[j]).checked=true;
	 }
	 var listCertificationId=${listCertificationId};
	 for(var l=0;l<listCertificationId.length;l++){
	 document.getElementById('boardCertification_'+listCertificationId[l]).checked=true;
	 }
	 if(provSpeciality!=null){
		 document.getElementById('provSpeciality_'+provSpeciality).checked=true;
		 sendInfo(provSpeciality); 
	 }
	
	 
	 
	 if(patientCat==0){
		 document.getElementById('Adult').checked=true;
		 
	 }  else if(patientCat==1){
		 document.getElementById('Padiatric').checked=true;
		 
	 }else if(patientCat==2){
		 document.getElementById('BothPadiatric').checked=true;
	 }
	}
	 }
	 
</script>
<script type="text/javascript">
function  hideInfoForManAndRec(){
	var provRoleId="${list[3]}";
	
	
	 if(provRoleId==477 || provRoleId==478){
		// alert("hi=="+provRoleId);
		  document.getElementById("proSta").style.display = 'none';
		 document.getElementById("accordion").style.display = 'none';
		 document.getElementById("Adult").style.display = 'none'; 
		// document.getElementsByClassName("abc").hide();
		 $('.all').hide();
	// document.getElementById("all").style.display = 'none';
		
	} 
	
}
</script>
<script >
function callme()
{
var output = document.getElementById('image');
   output.src = URL.createObjectURL(event.target.files[0]);
}
</script>
<script type="text/javascript">
counter=1;
count=124;
function checkRequired(){
	var provRoleId="${list[3]}";
	
	if(provRoleId!=477 && provRoleId!=478){
	
		 var firstName=document.getElementById("firstName").value;
		
		 var provEmailId=document.getElementById("provEmailId").value;
		 
	 var eduction=document.getElementById("provEduction1").value;
	 var npiNo=document.getElementById("npiNo").value;
	 if(firstName==''){
		 $('#demo').append('<div></div>').html("Please Write a first Name");  
		 return false;
	}
	/*  else if(male || female){
		 $('#demo').append('<div></div>').html("Please Select Gender");  
		 return false;
	} */
	 else if(provEmailId==''){
		 $('#demo').append('<div></div>').html("Please Write an EmailId");  
		 return false;
	}
	else if(npiNo==''){
		 $('#demo').append('<div></div>').html("Please Fill Npi No");  
		 return false;
	} else if(suffixCounting==0){
		$('#demo').append('<div></div>').html("Please select at least one suffixe value"); 
		return false;
	}else if(SpecialityadioButton==0) {
		$('#demo').append('<div></div>').html("Please select at one Speciality"); 
		return false;
	}
	 else if(eduction==''){
		 $('#demo').append('<div></div>').html("Please Fill At least one Education Value");  
		 return false;
	}
	}
	else{
		 var firstName=document.getElementById("firstName").value;
		 var male=document.getElementById("male").checked;
		 var female=document.getElementById("female").checked;
		 var provEmailId=document.getElementById("provEmailId").value;
		 if(firstName==''){
			 $('#demo').append('<div></div>').html("Please Write a first Name");  
			 return false;
		}
		/*  else if(male || female){
			 $('#demo').append('<div></div>').html("Please Select Gender");  
			 return false;
		} */
		 else if(provEmailId==''){
			 $('#demo').append('<div></div>').html("Please Write an EmailId");  
			 return false;
		}
	}
	return true;
}
function increCount(){
	suffixCounting++;
}
</script>
<script type="text/javascript">


</script>
<script type="text/javascript">
function radioCheck(){
	SpecialityadioButton=1;
}
</script>
</head>
<body>
<div class="row">
 <form:form id="formId" action="saveAllProviderInfo.do" method="POST" modelAttribute="ProviderInfo" autocomplete="off" enctype="multipart/form-data">
 <form:hidden path="providerId" id="providerId"/>
 <form:hidden path="radCodes.codeId" id="provRoleId"/>
 <form:hidden path="radProviderPublications[0].provider.providerId" id="providerEditId"/>
 <form:hidden path="radProviderMemberships[0].provider.providerId" id="providerMemId"/>
 <form:hidden path="practiceInfo.practiceId" value="${providePracticeId}"/>
 <form:hidden path="providerLastName" id="provLastName"/>
 <form:hidden path="imgPath" id="imagePath" />
  <!--main-navaigation-->
  <!--  <div id="content-right-main" class="small-12 medium-12 large-9 columns"> -->
    <div id="secondary-menu" class="small-12 medium-12 large-12 columns">
      <ul>
        <li><a href="PracticeInfo.do">PRACTICE</a></li>
    <li><a href="doctor.do">DOCTORS</a></li>
    <li><a href="practiceInsurance.do">INSURANCE</a></li>
    <li><a href="#">BILLING</a></li>
      </ul>
   
  </div>
   
    
    <div id="main-site-container" class="small-12 medium-12 large-12 columns">
      <div id="scroll" class="small-12 medium-12 large-8 columns">
      <p id="demo" style="color:red" ></p>
        <div id="speciality-panel" class="panel">
          <h1 class="practice">Doctors And Staff</h1>
          
          
          <form:input path="providerFirstName" placeholder="Provider Name" class="validate[required,custom[onlyLetterNumber]] text-input" id="firstName"/>
          
          
          <label>Gender(Required)</label>
          <form:radiobutton   value="M" id="male" path="providerGender" class="validate[required]"></form:radiobutton>
          <label for="pokemonRed">Male</label>
         <form:radiobutton   value="F" id="female" path="providerGender" class="validate[required]"></form:radiobutton>
          <label for="pokemonBlue">Female</label>
          
          
          <form:input path="providerEmail" placeholder="Provider Email ID" class="validate[required, custom[email]]" id="provEmailId"/>
          <div class="all">
          <div >
          <label>Patients Accepted</label>
           <form:radiobutton   value="0" id="Adult" path="practicePatientCat"></form:radiobutton>
           <label for="pokemonRed">Adult</label>
          <form:radiobutton   value="1" id="Padiatric" path="practicePatientCat"></form:radiobutton>
          <label for="pokemonBlue">Pediatric</label>
          <form:radiobutton   value="2" id="BothPadiatric" path="practicePatientCat"></form:radiobutton>
          <label for="pokemongreen">Both</label>
		  <form:textarea path="providerNotes" placeholder="Professional Statement"  maxlength="140" id="proSta" />
  </div>
  <div id="padding-0" class="small-10 medium-12 large-12 columns">
      
      

		        <div id="accordion">
  <ul>
    <li>
	 <label class="heading">NPI Number</label>
		 <a href="#one" class="newone">Edit</a>
		   <div id="one" class="accordion">
<div  class="panel">
 <form:input path="providerNpiNum" placeholder="Provider Npi Num"  maxlength="10" id="npiNo"/>

            
			</div></div></li></ul>
          </div>
		  </div>
		  <hr>
		   <div id="padding-0" class="small-10 medium-12 large-12 columns" id="suffix">
		    <div id="accordion" class="a">
  <ul>
    <li>
            <label class="heading">Professional Suffixes (Required)</label>
			<a href="#two" class="newone">Edit</a>
			 <div id="two" class="accordion">
<div id="float" class="panel">
<c:forEach items="${RadSuffix}" var="radSuffix">
  
          <form:checkbox id="checkbox_suffix_${radSuffix.suffixId}" path="radProviderSuffixes" value="${radSuffix.suffixId}"  onchange="increCount()"></form:checkbox> &nbsp;&nbsp;${radSuffix.suffixValue}</br>
          <%-- <label for="checkbox2">${radSuffix[1]}</label> --%>
 </c:forEach>

          </div></div></li></ul>
          </div>
		  </div>
          <hr>
          <div id="padding-0" class="small-10 medium-12 large-12 columns">
		  <div id="accordion">
  <ul>
    <li>
            <label class="heading">Specialities (Required)</label>
				<a href="#three" class="newone">Edit</a>
			 <div id="three" class="accordion">
<div id="float" class="panel">
          <c:forEach items="${RadProviderSpeciality}" var="radProviderSpeciality">
         <form:radiobutton path="radProvSpleatiy" value="${radProviderSpeciality.provSplId}" id="provSpeciality_${radProviderSpeciality.provSplId}"  onchange="sendInfo(${radProviderSpeciality.provSplId})" onClick="radioCheck()"></form:radiobutton>
    <label for="checkbox2">${radProviderSpeciality.provSplDesc}</label>
 </br>
  </c:forEach>

          </div></div></li></ul>
          </div>
		  </div>
          <hr>
          <div id="padding-0" class="small-10 medium-12 large-12 columns">
		    <div id="accordion">
  <ul>
    <li>
            <label class="heading">Visit Reasons (Required)</label>
			<a href="#four" class="newone">Edit</a>
			 <div id="four" class="accordion">
<div id="float" class="panel">
<div id="test">

</div>		   
    </div></div></li></ul>
          </div>
		  </div>
          <hr>
          <div id="padding-0" class="small-10 medium-12 large-12 columns">
		    <div id="accordion" >
  <ul>
    <li>
            <label class="heading">Education (Required)</label>
			<a href="#five" class="newone">Edit</a>
			 <div id="five" class="accordion">
<div id="float" class="panel">
<div id='TextBoxesGroup11'>
	<div id="TextBoxDiv1">
 <form:input path="radProvEduction" placeholder="Texas Tech University" id="provEduction1"  maxlength="140"></form:input>
 </div></div>
<input type='button' value='Remove' id='removeButton' class="small radius button">
          <input type='button' value='Add More' id='addButton' class="small radius button">

            
          </div></div></li></ul>
          </div>
		  </div>
 
          <hr>
          <div id="padding-0" class="small-10 medium-12 large-12 columns">
		    <div id="accordion" >
  <ul>
    <li>
            <label class="heading">Hospital Affilations</label>
			<a href="#six" class="newone">Edit</a>
			 <div id="six" class="accordion">
<div id="float" class="panel">
<div id='TextBoxesGroup1'>
	<div id="TextBoxDiv2">
 <form:input path="radProvHospital" placeholder="Hospital Affilations"  maxlength="140" id="proHospital1"/>
  </div></div>
  <input type='button' value='Remove' id='removeButton1' class="small radius button">
          <input type='button' value='Add More' id='addButton1' class="small radius button">
  
<label></label>
         </div></div></li></ul>
          </div>
		  </div>
          
          <hr>
           <div id="padding-0" class="small-10 medium-12 large-12 columns">
		    <div id="accordion" >
  <ul>
    <li>
            <label class="heading">Languages Spoken</label>
			<a href="#seven" class="newone">Edit</a>
			 <div id="seven" class="accordion">
<div id="float" class="panel">
<c:forEach items="${RadLanguage}" var="radLanguage">
  
          <form:checkbox path="radprovLanguage" id="proLanguage_${radLanguage.languageId}"  value="${radLanguage.languageId}"></form:checkbox>&nbsp;&nbsp;${radLanguage.languageDesc}</br>
         <%--  <label for="checkbox2">${radLanguage[1]}</label> --%>
 </br>
 </c:forEach>
            
         </div></div></li></ul>
          </div>
		  </div>
          <hr>
        <div id="padding-0" class="small-10 medium-12 large-12 columns">
		    <div id="accordion" class="g">
  <ul>
    <li>
            <label class="heading">Board Certifications</label>
			<a href="#eight" class="newone">Edit</a>
			 <div id="eight" class="accordion">
<div id="float" class="panel">
<c:set var="count" value="0" scope="page" />
  <c:forEach items="${RadBoardCertifications}" var="radboardcertifications"> 
          <form:checkbox path="boardCertifications"   id="boardCertification_${(RadBoardCertifications[count]).boardCertId}"  value="${(RadBoardCertifications[count]).boardCertId}" />&nbsp;&nbsp;${(RadBoardCertifications[count]).boardCertDesc}</br>
          <%-- <label for="checkbox2"> ${(RadBoardCertifications[count])[1]}</label></br> --%>
    <c:set var="count" value="${count + 1}" scope="page" />
  </c:forEach>  
          </div></div></li></ul>
          </div>
		  </div>
          <hr>
         <div id="padding-0" class="small-10 medium-12 large-12 columns">
		    <div id="accordion" class="h">
  <ul>
    <li>
            <label class="heading">Professional Memberships</label>
         <a href="#nine" class="newone">Edit</a>
			 <div id="nine" class="accordion">
<div id="float" class="panel">
<div id='TextBoxesGroup2'>
	<div id="TextBoxDiv3">
 <form:input path="radProviderMemberships[0].provMembership" placeholder="provider member ship" id="proMemberShip1"  maxlength="140"/>
 <%-- <form:input path="radProviderMemberships[0].provMembership" placeholder="173764528" class="validate[required] text-input"/> --%>
</div></div>
<input type='button' value='Remove' id='removeButton2' class="small radius button">
          <input type='button' value='Add More' id='addButton2' class="small radius button">
<label></label>
          </div></div></li></ul>
          </div>
		  </div>
          <hr>
          <div id="padding-0" class="small-10 medium-12 large-12 columns">
		    <div id="accordion" >
  <ul>
    <li>
            <label class="heading">Awards And Publications</label>
          <a href="#ten" class="newone">Edit</a>
			 <div id="ten" class="accordion">
<div id="float" class="panel">
<div id='TextBoxesGroup3'>
	<div id="TextBoxDiv4">
<%-- <c:set var="count" value="0" scope="page" /> --%>
 <form:input path="radProviderPublications[0].provPublication" placeholder="Publications" id="proPublication1" maxlength="140"/>
 
</div></div>
<input type='button' value='Remove' id='removeButton3' class="small radius button">
          <input type='button' value='Add More' id='addButton3' class="small radius button">
<label></label>
<%--  <c:set var="count" value="${count + 1}" scope="page" /> --%>
          </div></div></li></ul>
          </div>
		  </div></div>
          <hr>
<div id="padding-0" class="buttons-blue-container">
<input type="submit" name="Submit" value="Save" class="small radius button" onClick="return checkRequired()">
</div>
         
        </div>
      </div>
      <div class="small-12 medium-12 large-4 columns">
        <div id="speciality-panel" class="panel">
          <div class="image"> <!-- <img src="postureperfectnet_15_LOGO.png" /> --> 
	<!--   <img id="preview" scr="" /> -->
	   <img id="preview" src="${imagePath}">
	  </div>
          <div id="filetype"></div>
         <!--  <div id="filedim"></div> -->
        </div>
        <div id="error"></div>
        <div id="error2"></div>
        <div id="abort"></div>
        <div id="warnsize"></div>
           <div id="progress_info">
          <div id="upload_response"></div>
        </div>
          <div class="upload">
            <!-- <input type="file" name="Attach Docs" value="Attach Docs" id="fileToUpload" class="small radius button" multiple> -->
          <input type="file" name="image_file" id="image_file" onchange="fileSelected();" />
          <span id="docFileNames">No Files Selected</span>
          </div>
        </div>
      </div>
    </div>
    <!--#main-site-container-->
   
  <!--#content-right-main-->
  </form:form>
</div>
<script src="js/foundation.min.js"></script>
<script>
      $(document).foundation();
      
      $(function() { $('#image_file').change(function (){
  	  	$("#docFileNames").empty();
  		var files = $('#image_file')[0].files;
  		for (var i = 0; i < files.length; i++) {
  			var $p = $("<p></p>").text(files[i].name).appendTo("#docFileNames");
  		}
  	});
     });
    </script>
</body >
</html>