<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script src="js/page-js/nextPrevious.js"></script>
<script src="js/page-js/validationForFisrtNext.js"></script>
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
	 <script type="text/javascript">
window.onload = function () {

	var headersearch = document.getElementById("headersearch");
	headersearch.style.display = "block";
	
  $(".pagebanner").hide();
  $(".strong").hide();
  var pagelinkValue = $('.pagelinks').map(function(){
  return $(this).text();
  }).get();
  if(pagelinkValue==1)
  $(".pagebanner").hide();
  };
</script>
 <style type="text/css">
 a[title] {
    display : none;
}
strong {
    display : none;
}
 </style>
        <script type="text/javascript">
     function selectaction(refid,add){
    var selection=	 document.getElementById('actionselect'+refid+''+add+'').value;
//alert('a'+refid+''+add+'');
if(selection=='Select'){
return false;
}
	if(selection=='Refer'){
	window.location.href = document.getElementById('Refer'+refid+''+add+'').href;
	 return false;
	}
		if(selection=='Update'){
		window.location.href = document.getElementById('Update'+refid+''+add+'').href;
		 return false;
		 }
		if(selection=='Print'){
			window.location.href = document.getElementById('Print'+refid+''+add+'').href;
			 return false;}
     }
		</script>
  </head>
  <body>
    <!--Main Site Goes Here-->
    <div class="row">
		<div id="practice-area-content" class="small-12 medium-12 large-12 columns">
			<%-- <form id="form1" name="form1" action="searchPracticePatient.do" method="post">
			
				<div class="small-12 medium-9 large-6 columns">
				<label>
					<input type="text" name="keywords" id="searchName"/>
					<input type="hidden" name="identifireVar" value="ch" id="searchName"/>
				</label>
				</div>
				<div class="small-12 medium-9 large-3 columns">
	                    <select id="select" name="typeSelect">
                        <option value="practice/CH">Practice/CH</option>
                        <option value="PatientName">Patient Name</option>
                       </select>
				</div>
				
				<div class="small-12 medium-3 large-3 columns">
				  <label>
				  <input type="Submit" value="Search">
				  </label>
				</div>
			</form>
			 <p>&nbsp;</p> --%>
			  <div class="practice-table">
			 <c:set var="count" value="0" scope="page" />
			  <c:if test="${sessionScope.param=='chInbox'}">
      <display:table name="referralInfoList" pagesize="4" uid="refInfo" requestURI="clearingHouse.do" cellpadding="0" cellspacing="0">
    <display:setProperty name="paging.banner.page.separator" value=""/>
      <c:set var="count" value="${count+1}" scope="page" />
      ${count}
      <display:column property="practiceName" title="Practice Name">
    <%--   <a href="patientNameView.do?patientRefInfo=${refInfo.refId}">${refInfo.practiceName}</a> --%>
      </display:column>
       <display:column  title="Pateint Name">
       <a href="patientNameView.do?patientRefInfo=${refInfo.refId}">${refInfo.pateintFirstName}</a>
       </display:column>
         <display:column property="creationDate" title="Date"/>
           <display:column property="proNotes" title="Message"/>
            <display:column title="Action">
             <a id="Refer${refInfo.refId}${refInfo.currentStatusId}${refInfo.provRefActionId}"  href="refralReasonsCh.do?referCurrentStatusId=${refInfo.currentStatusId}&patientReferralId=${refInfo.refId}"></a>
				  <%-- <c:forEach items="${refInfo.patientReferralInfo.referral_Provider_ActionList}" var="ref"> --%>
					  <a id="Update${refInfo.refId}${refInfo.currentStatusId}${refInfo.provRefActionId}"  href="chUpdateRefralView.do?patientReferralId=${refInfo.refId}&providerId=${refInfo.provRefActionId}"></a>
			<a id="Print${refInfo.refId}${refInfo.currentStatusId}${refInfo.provRefActionId}" href="print.do?patientReferralId=${refInfo.refId}&providerId=${refInfo.provRefActionId}"></a> 
				<select id="actionselect${refInfo.refId}${refInfo.currentStatusId}${refInfo.provRefActionId}"  onchange="return selectaction(${refInfo.refId},${refInfo.currentStatusId}${refInfo.provRefActionId})">
			<option>Select</option>
			<option>Refer</option>
			<option>Update</option>
			<option>Print</option>
			
			</select>	<%-- </c:forEach> --%>
				<%-- </c:if>
 --%>					<%-- <c:set var="len" value="${len+1}"/> --%>
					<%-- </c:forEach> --%>
				  
            <%-- </display:column>
             <display:column> --%>
              
             </display:column>
          
      </display:table></c:if>
      <c:if test="${sessionScope.param=='search'}">
      <display:table name="referralInfoList" pagesize="4" uid="refInfo" requestURI="searchPracticePatient.do" cellpadding="0" cellspacing="0">
     <display:setProperty name="paging.banner.page.separator" value=""/>
      <c:set var="count" value="${count+1}" scope="page" />
      ${count}
      <display:column title="Practice Name">
      <a href="patientNameView.do?patientRefInfo=${refInfo.refId}">${refInfo.practiceName}</a>
      </display:column>
       <display:column  title="Patient Name">
       <a href="patientNameView.do?patientRefInfo=${refInfo.refId}">${refInfo.pateintFirstName}</a>
       </display:column>
         <display:column property="creationDate" title="Date"/>
           <display:column property="proNotes" title="Message"/>
            <display:column title="Action">
                 <a id="Refer${refInfo.refId}${refInfo.currentStatusId}${refInfo.provRefActionId}"  href="refralReasonsCh.do?referCurrentStatusId=${refInfo.currentStatusId}&patientReferralId=${refInfo.refId}"></a>
				  <%-- <c:forEach items="${refInfo.patientReferralInfo.referral_Provider_ActionList}" var="ref"> --%>
					  <a id="Update${refInfo.refId}${refInfo.currentStatusId}${refInfo.provRefActionId}"  href="chUpdateRefralView.do?patientReferralId=${refInfo.refId}&providerId=${refInfo.provRefActionId}"></a>
			<a id="Print${refInfo.refId}${refInfo.currentStatusId}${refInfo.provRefActionId}" href="print.do?patientReferralId=${refInfo.refId}&providerId=${refInfo.provRefActionId}"></a> 
				<select id="actionselect${refInfo.refId}${refInfo.currentStatusId}${refInfo.provRefActionId}"  onchange="return selectaction(${refInfo.refId},${refInfo.currentStatusId}${refInfo.provRefActionId})">
			<option>Select</option>
			<option>Refer</option>
			<option>Update</option>
			<option>Print</option>
         <%--  <a class="button-in-table" href="refralReasonsCh.do?referCurrentStatusId=${refInfo.currentStatusId}&patientReferralId=${refInfo.refId}">Refer</a>
				  <c:forEach items="${refInfo.patientReferralInfo.referral_Provider_ActionList}" var="ref"> 
					  <a class="button-in-table" href="chUpdateRefralView.do?patientReferralId=${refInfo.refId}&providerId=${refInfo.provRefActionId}">Update</a>--%>
				<%-- </c:forEach> --%>
				<%-- </c:if>
 --%>					<%-- <c:set var="len" value="${len+1}"/> --%>
					<%-- </c:forEach> --%>
				  
            <%-- </display:column>
             <display:column> --%>
              
             </display:column>
          
       </display:table>  </c:if> 
      
      
         </div>
			
			
			
			
			<%-- <div class="practice-table">
				<table width="200" border="1" cellpadding="0" cellspacing="0">
				  <tr class="first-row-heading-table">
					<td>Practice Name</td>
					<td>Patient Name</td>
					<td>Date</td>
					<td>Message</td>
					<td>Action</td>
				  </tr>
				  <c:forEach items="${referralInfoList}" var="refInfo">
				    <tr>
					<td><a href="patientNameView.do?patientRefInfo=${refInfo.refId}">${refInfo.practiceName}</a></td>
					<td><a href="patientNameView.do?patientRefInfo=${refInfo.refId}">${refInfo.pateintFirstName}</a></td>
					<td>${refInfo.creationDate}</td>
					<c:set var="len" value="0"/>
					<c:forEach items="${refInfo.patientReferralInfo.referral_Provider_ActionList}" var="ref">
					<c:if test="${len==refInfo.patientReferralInfo.referral_Provider_ActionList.size()-1}">
					<td>${refInfo.proNotes}</td>
					<td>${ref.providerRefReasons}</td>
					
				  <td><a class="button-in-table" href="refralReasonsCh.do?referCurrentStatusId=${refInfo.currentStatusId}&patientReferralId=${refInfo.refId}">Refer</a>
				  <c:forEach items="${refInfo.patientReferralInfo.referral_Provider_ActionList}" var="ref">
					 
					 <a class="button-in-table" href="chUpdateRefralView.do?patientReferralId=${refInfo.refId}&providerId=${refInfo.provRefActionId}">Update</a>
				</c:forEach>
				</c:if>
					<c:set var="len" value="${len+1}"/>
					</c:forEach>
				  </td>
				  </tr>
				  </c:forEach>
			  </table>
			</div>  --%> <!--practice-table-->
		</div><!--administrators-form-->
    </div><!--row-->
    <!--Main Site Ends Here-->
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="responsive/script.js"></script>
    <script src="js/vendor/jquery.js"></script>
    <script src="js/foundation.min.js"></script>
    <script>
      $(document).foundation();
    </script>
  </body>
</html>
