<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html class="no-js" lang="en">
  <head>
  <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Connecting Doctors</title>
    <link rel="stylesheet" href="css/foundation.css" />
    <link rel="stylesheet" href="css/style.css" />
    <script src="js/vendor/modernizr.js"></script>
	
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="responsive/style.css">
	<link rel="stylesheet" type="text/css" href="responsive/responsive.css">
		<!--Accordion CSS-->
		<link rel="stylesheet" type="text/css" href="accordian/accordian-style.css">
	<!--Accordion CSS Ends-->
	<link href="bootstrap/Untitled-1.css" rel="stylesheet">
	<script>

	function openThree(count){
//alert(count);
var threeflag=document.getElementById('threeflag'+count).value;
//alert(threeflag);
if(threeflag=="false"){
	   var three = document.getElementById("three"+count);
	   three.style.display = "block";
	   document.getElementById('threeflag'+count).value='true';
	   $('#pthree'+count).text('HIDE');
	   
	}
if(threeflag=="true"){
	 var three = document.getElementById("three"+count);
	 three.style.display = "none";
	   document.getElementById('threeflag'+count).value='false';
	   $('#pthree'+count).text('SHOW');
	}

		}


	
	function openOne(){
		
		var oneflag=document.getElementById('oneflag').value;
		//alert(oneflag);
		if(oneflag=="false"){
			   var one = document.getElementById("one");
			   one.style.display = "block";
			   document.getElementById('oneflag').value='true';
			}
		if(oneflag=="true"){
			 var one = document.getElementById("one");
			   one.style.display = "none";
			   document.getElementById('oneflag').value='false';
			}

		}
	</script>
	
	
  </head>
  <body>

<div class="row"> 
    

	
		

<div id="form-main-container-of-fancybox" class="small-12 medium-12 large-12 columns"> <!-- It is open by me for patientName.jsp -->
<h1 align="center">Referral History</h1>
<div id="accordion">
<form:form id="formID" method="POST" modelAttribute="PateintAndInsuranceInfoVO" autocomplete="off">
  <ul>
    <li>
  
      <p onclick="openOne()" style="font-size: 24px;margin-left: 10px;">Patient Info</p>
      
	  <div id="one" class="accordion">
<input id="oneflag" value="false" type="hidden"/>

<div id="float" class="panel">

<div class="small-12 medium-12 large-6 columns">
<label>Patient First Name</label>
<form:input path="pateintFirstName" placeholder="Patient First Name" disabled="true"/>
</div>
<div class="small-12 medium-12 large-6 columns">
<label>Patient Last Name</label>
<form:input path="pateintLastName" placeholder="Patient Last Name" disabled="true"/>
</div>
<div class="small-12 medium-12 large-6 columns">
<form:input path="locAddress1" placeholder="Address1" style="display:none" disabled="true"/>
</div>

<div class="small-12 medium-12 large-6 columns">
<form:input path="locAddress2"  placeholder="Address2" style="display:none" disabled="true"/>
</div>

<div id="padding-0" class="small-12 medium-12 large-8 columns">
<div class="small-12 medium-12 large-4 columns">
<form:input path="locCity" placeholder="City" style="display:none" disabled="true"/>
</div>

<div class="small-12 medium-12 large-4 columns">
	<%-- <form:select style="display:none" path="stateId" id="PatientState"
								onkeyup="stoppedTyping()" disabled="true">
								<form:option value="0" label="Select" />
								<c:forEach items="${stateList}" var="state">
									<form:option value="${state.stateId}" label="${state.stateName}" />
								</c:forEach>
							</form:select> --%>
</div>

<div class="small-12 medium-12 large-4 columns">
<form:input path="locZip" style="display:none" placeholder="Zip" disabled="true"/>
</div>

</div>



<div class="small-12 medium-12 large-6 columns">
<label>Phone No.</label>
<form:input path="locPhone" placeholder="Phone" disabled="true"/>
</div>

<div class="small-12 medium-12 large-6 columns">
<label>Email ID</label>
<form:input path="pateintEmail" placeholder="Email ID" disabled="true"/>
</div>

<%-- <div class="small-12 medium-12 large-4 columns">
<form:input path="patientReferralInfo.patientInfo.radLocation.locFax"  placeholder="Fax" disabled="true"/>
</div> --%>

<div class="small-12 medium-12 large-6 columns">
<form:input path="locFax" style="display:none" placeholder="Fax" disabled="true"/>
</div>
<div class="small-12 medium-12 large-6 columns">
<label>Gender</label>
<form:select path="patientGender" id="gender"  required="true" disabled="true">
							<form:option value="G" label="Gender" />
							<form:option value="M" label="Male" />
							<form:option value="F" label="Female" />
							</form:select>
</div>

<div class="small-12 medium-12 large-6 columns">
<label>Date of Birth</label>
<fmt:parseDate value="${PateintAndInsuranceInfoVO.patientDob}" type="DATE" pattern="yyyy-MM-dd" var="formatedDate"/>
 <fmt:formatDate value="${formatedDate}" var="dateFor" type="date" pattern="MM-dd-yyyy" />
<input type="text" value="${dateFor}" disabled="true"/>
</div>

<div class="small-12 medium-12 large-6 columns">
<form:input path="patientSSN" style="display:none" placeholder="SSN" disabled="true"/>
</div>
<div class="small-12 medium-12 large-6 columns">
<label>Insurance Name</label>
<form:input path="insuranceCompany"  placeholder="Insurance Name" disabled="true"/>
</div>

<div class="small-12 medium-12 large-6 columns">
<label>Insurance ID</label>
<form:input path="insuranceId" placeholder="Insurance ID" disabled="true"/>
</div>

<div class="small-12 medium-12 large-6 columns">
<label>Group</label>
<form:input path="patientInsuranceGroup" placeholder="Group" disabled="true"/>
</div>

<div class="small-12 medium-12 large-6 columns">
<form:input path="patientInsurancePhone" style="display:none" placeholder="Insurance Phone" disabled="true"/>
</div>

<div class="small-12 medium-12 large-6 columns">
<input type="text" placeholder="Claim" style="display:none" disabled="true">
</div>

<div id="padding-0" class="small-12 medium-12 large-12 columns">
<div class="small-12 medium-12 large-6 columns">
				      <label></label>
				      <form:radiobutton path="patientPre1AuthReq" style="display:none" name="pokemon" value="Y" id="pokemonRed" disabled="true"/><label for="pokemonRed"></label>
				      <form:radiobutton path="patientPre1AuthReq" style="display:none" name="pokemon" value="N" id="pokemonBlue" disabled="true"/><label for="pokemonBlue"></label>
				    </div>
					
<div class="small-12 medium-12 large-6 columns">
<%-- <form:input path="patientPreAuthId" style="display:none"placeholder="Pre AUTH" disabled="true"/> --%>
</div></div>

<div id="padding-0" class="small-12 medium-12 large-12 columns">
<div class="small-12 medium-12 large-6 columns">
<%-- <form:input path="patientPreAuthStartDate"  style="display:none"placeholder="Pre AUTH Date : From" disabled="true"/> --%>
</div>

<div class="small-12 medium-12 large-6 columns">
<form:input path="patientPreAuthEndDate" style="display:none" placeholder="To" disabled="true"/>
</div></div>

<div id="padding-0" class="small-12 medium-12 large-12 columns">
<div class="small-12 medium-12 large-6 columns">
<form:input path="patientPreAuthContactName" style="display:none" placeholder="Contact Name" disabled="true"/>
</div>

<div class="small-12 medium-12 large-6 columns">
<form:input path="patientPreauthConfirmation" style="display:none" placeholder="Confirmation" disabled="true"/>
</div></div>

<div id="padding-0" class="small-12 medium-12 large-12 columns">
<div class="small-12 medium-12 large-6 columns">
<form:textarea path="patientInsuranceNotes" style="display:none" placeholder="Notes" disabled="true"/>
</div>
<c:forEach var="document" items="${insdocList}">
 <c:set var="num" value="${num + 1}" />
<div class="small-12 medium-12 large-6 columns">

		<a href="<c:url value="download.do">
			<c:param name="filePath" value="${document.docPath}"/>
		     <c:param name="fileName" value="${document.docName}"/>
		        </c:url>">${num}.&nbsp;${document.docName}
		 </a>
	</div><br/>
</c:forEach>

<!-- <label style="margin-left: 3%;">Schedules:</label> -->

<c:forEach var="scheduleList" items="${scheduleList}">
<div class="small-12 medium-12 large-6 columns">
<input type="text" value="${scheduleList.scheduleDate}" disabled="true"/>
</div>
<div class="small-12 medium-12 large-6 columns">
<input type="text" value="${scheduleList.scheduleNotes}" disabled="true"/>
</div>
</c:forEach>


<!-- <div class="small-12 medium-12 large-6 columns">
<input type="file" name="Attach Docs" value="Attach Docs" id="fileToUpload" class="small radius button" multiple>
</div> --></div>


</div>

      </div>
     
    </li>
    

<li>
    
</form:form>
      </div>
    </li>
  </ul>
</div>

<div id="custom-table" class="row">
<table width="100%" border="1" cellpadding="0" cellspacing="0">
  <tr class="first-row-heading-table">
    <td></td>
	<td>Practice/CH Name </td>
    <td>Provider/CH Admin Name </td>
    <td>Date</td>
    <td>Notes</td>
  </tr>
 
 <c:forEach items="${infos}" var="info" ><!-- ############################ -->
          <c:set var="index" value="0"/>
         <%-- <c:forEach items="${info.referral_Provider_ActionList}" var="referral_Provider_Action"> --%>
  <tr>
<td>
 <div id="accordion" class="with-custom-designs">
  <ul>
    <li>
    <c:set var="count" value="${count + 1}" scope="page" />
     
	<div id="referNav">
<%-- <a href="#three${count}" class=""></a> --%>
<p style="text-align: center;" id="pthree${count}" onclick="openThree('${count}')">SHOW</p>
<input id="threeflag${count}" value="false" type="hidden"/>
      </div>
	  <div id="three${count}" class="accordion">
	  <div id="float" class="panel">
<div class="small-12 medium-12 large-12 columns">
<textarea placeholder="${info.refReason}" readonly></textarea>
</div>

<div class="small-12 medium-12 large-12 columns">
<textarea placeholder="${info.refDigCode}" readonly ></textarea>
</div>

<div class="small-12 medium-12 large-12 columns">
<textarea placeholder="${info.refNotes}" readonly ></textarea>
</div>
<%--  <c:set var="count" value="${count + 1}" scope="page" /> --%>

<c:forEach var="document" items="${info.docList}">
 <c:set var="num" value="${num + 1}" />
<div class="small-12 medium-12 large-12 columns">

		<a href="<c:url value="download.do">
			<c:param name="filePath" value="${document.docPath}"/>
		     <c:param name="fileName" value="${document.docName}"/>
		        </c:url>">${num}.&nbsp;${document.docName}
		 </a>
	</div><br/>
</c:forEach>

<c:set var="num" value="0" />
<div class="small-6 medium-6 large-6 columns">
<%-- <label class="align-right">File Name:${info.patientInfo.patientInsuranceInfoList[0].attachDocumentList[0].documentName}</label>
 --%></div>
</div></div></li></ul></div>
</td>	  
    <td>${info.practiceName}</td>
    <td>${info.providerName}</td>
    <td>${info.creationDate}</td>
    <td>${info.refNotes}</td>
  </tr>
  
  <c:set var="index" value="${index+1}"/>
  <%-- </c:forEach> --%>
  </c:forEach>
</table>
</div>
</div><!--#form-main-container-of-fancybox-->
  </div>
  </div>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="responsive/script.js"></script>
<script src="bootstrap/jq.js"></script>
  <script src="bootstrap/bs.js"></script>
    <script src="js/vendor/jquery.js"></script>
    <script src="js/foundation.min.js"></script>
    <script>
      $(document).foundation();
    </script>
	<script>
$(document).ready(function() { 


    $('#referNav a').on('click', changeClass);
});

function changeClass() {
   $('#referNav a').removeClass('active');
    $(this).addClass('active');
}
</script>
  </body>
</html>
