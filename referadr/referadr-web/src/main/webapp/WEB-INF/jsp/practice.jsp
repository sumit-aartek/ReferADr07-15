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
<link rel="stylesheet" type="text/css" href="css/jquery.datetimepicker.css"/>
<!doctype html>
<html class="no-js" lang="en">
   <script>
     
      function submit()
      {
    	
    	  /* var searchContent=document.getElementById('searchName').value;
    	  var practiceOrPatientName= document.getElementById('select').value;
    	  alert(searchContent);
    	  alert(practiceOrPatientName);
    	  document.form1.action="searchPracticePatient.do" */
      }
    </script>
    
    
       <script type="text/javascript">
     function selectaction(refid,providerId,add){
    var selection=	 document.getElementById('actionselect'+refid+''+add+'').value;
//alert('a'+refid+''+add+'');
if(selection=='_Select_'){
return false;
}
	if(selection=='Refer'){
	window.location.href = document.getElementById('a'+refid+''+add+'').href;
	 return false;
	}
		if(selection=='Update'){
		window.location.href = document.getElementById('b'+refid+''+add+'').href;
		 return false;}
			if(selection=='Print'){
			window.location.href = document.getElementById('c'+refid+''+add+'').href;
			 return false;}
			 
				if(selection=='Schedule'){
				var schedulefull = document.getElementById("shaduleModal");
				schedulefull.style.display = "block";
				document.getElementById("sheduleReferralId").value=refid;
				$.ajax({
					url : "findWithProvider.do?refId="
							+ encodeURIComponent(refid),
					
					type : "GET",
					contentType : "application/json; charset=utf-8",
					success : function(t) {
				//alert("success");
				document.getElementById("sheduleWithProviderId").value=t;

////////////////////
							$.ajax({
							url : "findProviderPatientSchedule.do?refId="+ encodeURIComponent(refid)+"&withProvId="+ encodeURIComponent(t),
					
							type : "GET",
							contentType : "application/json; charset=utf-8",
							success : function(h) {
							//alert("success=>"+h.creationDate);
							document.getElementById("datetimepicker").value=h.creationDate+" "+h.startTime;
							},
							error : function() {
							//alert("error");
								
							}
							})

//////////////////////

				
					},
					error : function() {
					//alert("error");
						document.getElementById("sheduleWithProviderId").value="0";	
					}
				})
				
				
			
				
				//alert(providerId);
				document.getElementById("sheduleProviderId").value=providerId;
				}
				


return false;
         }


     function closediv(){
    		var schedulefull = document.getElementById("shaduleModal");
			schedulefull.style.display = "none";
         }
    </script>
    
    
    
    
     <script type="text/javascript">
function parapass(refid,providerId)
{
//alert(refid);
document.getElementById("sheduleReferralId").value=refid;
$.ajax({
	url : "findWithProvider.do?refId="
			+ encodeURIComponent(refid),
	
	type : "GET",
	contentType : "application/json; charset=utf-8",
	success : function(t) {
//alert("success");
document.getElementById("sheduleWithProviderId").value=t;
	},
	error : function() {
	//alert("error");
		document.getElementById("sheduleWithProviderId").value="0";	
	}
})
//alert(providerId);
document.getElementById("sheduleProviderId").value=providerId;
	}
     </script>
     
         <script type="text/javascript">

        function setProviderId(){
        	document.getElementById("sheduleWithProviderId").value=document.getElementById("extraproviderlist").value;
        	

            }



         
function saveShedule()
{
	var dateTime=document.getElementById("datetimepicker").value;
var refId=document.getElementById("sheduleReferralId").value;
var provId=document.getElementById("sheduleProviderId").value;
var withProvId=document.getElementById("sheduleWithProviderId").value;
var notes=document.getElementById("sheduleNotes").value;

//alert(dateTime+"  "+refId+"  "+provId+"  "+notes);

if(withProvId==""||withProvId==null||withProvId==0||withProvId=="undefined"){
	var schedulefull = document.getElementById("all");
	schedulefull.style.display = "block";
	return false;	
}
else{
	var schedulefull = document.getElementById("all");
	schedulefull.style.display = "none";

$.ajax({
	url : "sheduleAction.do?dateTime="
			+ encodeURIComponent(dateTime)
			+ '&refId='
			+ encodeURIComponent(refId)
			+ '&provId=' + encodeURIComponent(provId)
			+ '&withProvId=' + encodeURIComponent(withProvId)
			+ '&notes=' + encodeURIComponent(notes),
	type : "GET",
	contentType : "application/json; charset=utf-8",
	success : function(t) {
	//alert("Scheduled");
	var schedulefull = document.getElementById("shaduleModal");
	schedulefull.style.display = "none";
	window.location.href = document.getElementById('callpractice').href;
	},
	error : function() {
		//alert("error");	
	}
})
}
return true;
	}
     </script>
     
     
    
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
  var schedulefull = document.getElementById("all");
	schedulefull.style.display = "none";
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
  </head>
  <body>
    <!--Main Site Goes Here-->
      <a id='callpractice'  href="practice.do"></a>
    <div class="row">
		<div id="practice-area-content" class="small-12 medium-12 large-12 columns">
			<form id="form1" action="searchPracticePatient.do" name="form1" method="post">
				<!-- <div class="small-12 medium-9 large-6 columns">
				<label>
					<input type="text" name="keywords" id="searchName"/>
					<input type="hidden" name="identifireVar" value="practice" id="searchName"/>
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
				</div> -->
			</form>
			<!--  <p>&nbsp;</p> -->
			  <div class="practice-table">
			  <h1 class="doctor-staff">Inbox</h1>
			 <c:set var="count" value="0" scope="page" />
			<%--  <c:if test="${sessionScope.param==practiceInbox}">
      <display:table name="referralInfoList" pagesize="4" uid="refInfo" requestURI="practice.do" cellpadding="0" cellspacing="0">
      </display:table></c:if> --%>
       <c:if test="${sessionScope.param=='practiceInbox'}">
      <display:table name="referralInfoList" pagesize="4" uid="refInfo" requestURI="practice.do" cellpadding="0" cellspacing="0">
      <display:setProperty name="paging.banner.page.separator" value=""/>
      <c:set var="count" value="${count+1}" scope="page" />
      ${count}
      <display:column property="practiceName" title="Practice Name">
    
      </display:column>
       <display:column  title="Patient Name">
       <a href="patientNameView.do?patientRefInfo=${refInfo.refId}">${refInfo.pateintFirstName}</a>
       </display:column>
         <display:column property="creationDate" title="Date"/>
           <display:column property="proNotes" title="Message"/>
            <display:column title="Action">
         <a id="a${refInfo.refId}${refInfo.currentStatusId}${refInfo.provRefActionId}"  href="refralReasonsView.do?referCurrentStatusId=${refInfo.currentStatusId}&patientReferralId=${refInfo.refId}"></a>
		  <a id="b${refInfo.refId}${refInfo.currentStatusId}${refInfo.provRefActionId}"  href="updateRefralView.do?patientReferralId=${refInfo.refId}&providerId=${refInfo.provRefActionId}"></a>
		  <a id="c${refInfo.refId}${refInfo.currentStatusId}${refInfo.provRefActionId}" href="print.do?patientReferralId=${refInfo.refId}&providerId=${refInfo.provRefActionId}"></a> 
			
			
			<select id="actionselect${refInfo.refId}${refInfo.currentStatusId}${refInfo.provRefActionId}"  onchange="return selectaction(${refInfo.refId},${refInfo.providerId},${refInfo.currentStatusId}${refInfo.provRefActionId})">
			<option>Select</option>
			<option>Refer</option>
			<option>Update</option>
			<option>Print</option>
			<option>Schedule</option>
			</select>
			
	<%-- 			
  <c:if test="${sessionScope.login!=null}"> 	
  <a class="button-in-table" data-toggle="modal" data-target="#shaduleModal" onclick="parapass(${refInfo.refId},${refInfo.providerId})" data-whatever="@mdo">Schedule</a></c:if>
  --%>
 
 
 <div class="modal fade" id="shaduleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" style="display: none;">
      <div class="modal-dialog">
        <div class="modal-content large-12 columns">
          <div class="modal-header">
            <button onclick="closediv()" type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><img class="close" src="bootstrap/alert_boxes_close_ico.png"></span></button>
            <h4 class="modal-title" id="exampleModalLabel">Schedule</h4>
          </div>
          <div id="mailsent" class="modal-body">
            <form>
              <div class="form-group">
              
              
              
              <div id="all">
     <div class="small-12 medium-12 large-12 columns" id='providerdropdowm'>
     	Please select referral provider
								
						<select class="form-control" onchange="setProviderId()" id="extraproviderlist">
						<option value="0">
										-Select A Provider-</option>
						<c:forEach items="${ProviderInfoForSchedule}" var="refProviderInfoo">
									<option value="${refProviderInfoo.providerId}">
										${refProviderInfoo.providerFirstName}</option>
								</c:forEach>
						</select>
						
						
						</div>
</div>
              
              
              
              
              
              
              
              	<input type="hidden" class="form-control"  id="sheduleWithProviderId"/>
              			<input type="hidden" class="form-control"  id="sheduleReferralId"/>
				<input type="hidden" class="form-control"  id="sheduleProviderId"/>
                <label for="recipient-name" class="control-label" >Select Date & Time</label>        
				<input type="text" class="form-control"  id="datetimepicker"/><br>
		
              </div>
              <div class="form-group">
                <label for="message-text" class="control-label">Notes</label>
                <textarea class="form-control" id="sheduleNotes" ></textarea>
              </div>
			  <div class="modal-footer">
           
            <button type="button" class="btn btn-primary" onclick="return saveShedule()" id="sheduleButton">Save</button>
            <br><br>
          </div>
            </form>
            <p id="demo222" style="color: red"></p>
          </div>
          
        </div>
      </div>
    </div><!-- /.modal -->
  <!-- 	<h1>Support</h1> -->
 
				
				<%-- </c:forEach> --%>
				<%-- </c:if>
 --%>					<%-- <c:set var="len" value="${len+1}"/> --%>
					<%-- </c:forEach> --%>
				  
            <%-- </display:column>
             <display:column> --%>
             </display:column>
       </display:table>  </c:if>  
        <c:if test="${sessionScope.param=='search'}">
      <display:table name="referralInfoList" pagesize="4" uid="refInfo" requestURI="searchPracticePatient.do" cellpadding="0" cellspacing="0">
     <display:setProperty name="paging.banner.page.separator" value=""/>
      <c:set var="count" value="${count+1}" scope="page" />
      ${count}
      <display:column property="practiceName" title="Practice Name"/>
    
       <display:column  title="Pateint Name">
       <a href="patientNameView.do?patientRefInfo=${refInfo.refId}">${refInfo.pateintFirstName}</a>
       </display:column>
         <display:column property="creationDate" title="Date"/>
           <display:column property="proNotes" title="Message"/>
            <display:column title="Action">
            
            
                 <a id="a${refInfo.refId}${refInfo.currentStatusId}${refInfo.provRefActionId}"  href="refralReasonsView.do?referCurrentStatusId=${refInfo.currentStatusId}&patientReferralId=${refInfo.refId}"></a>
		  <a id="b${refInfo.refId}${refInfo.currentStatusId}${refInfo.provRefActionId}"  href="updateRefralView.do?patientReferralId=${refInfo.refId}&providerId=${refInfo.provRefActionId}"></a>
		  <a id="c${refInfo.refId}${refInfo.currentStatusId}${refInfo.provRefActionId}" href="print.do?patientReferralId=${refInfo.refId}&providerId=${refInfo.provRefActionId}"></a> 
			
			
			<select id="actionselect${refInfo.refId}${refInfo.currentStatusId}${refInfo.provRefActionId}"  onchange="return selectaction(${refInfo.refId},${refInfo.providerId},${refInfo.currentStatusId}${refInfo.provRefActionId})">
			<option>Select</option>
			<option>Refer</option>
			<option>Update</option>
			<option>Print</option>
			<option>Schedule</option>
			</select>
            
            
            
            
            
            
       <%--   <a class="button-in-table" href="refralReasonsView.do?referCurrentStatusId=${refInfo.currentStatusId}&patientReferralId=${refInfo.refId}">Refer</a>
				   <c:forEach items="${refInfo.patientReferralInfo.referral_Provider_ActionList}" var="ref"> 
					 
					 <a class="button-in-table" href="updateRefralView.do?patientReferralId=${refInfo.refId}&providerId=${refInfo.provRefActionId}">Update</a>--%>
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
					
				  <td><a class="button-in-table" href="refralReasonsView.do?referCurrentStatusId=${refInfo.currentStatusId}&patientReferralId=${refInfo.refId}">Refer</a>
				  <c:forEach items="${refInfo.patientReferralInfo.referral_Provider_ActionList}" var="ref">
					 
					 <a class="button-in-table" href="updateRefralView.do?patientReferralId=${refInfo.refId}&providerId=${refInfo.provRefActionId}">Update</a>
				</c:forEach>
				</c:if>
					<c:set var="len" value="${len+1}"/>
					</c:forEach>
				  </td>
				  </tr>
				  </c:forEach>
			  </table>
			</div> --%><!--practice-table-->
		
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
    <script src="js/page-js/jquery.js"></script>
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
 jQuery('#datetimepicker').datetimepicker({
	 datepicker:true,
	 format:'Y-m-d H:i',
	 allowTimes:[
	  '10:10', '10:20', '10:30', '10:40', '10:50', '11:00',
	  '11:10', '11:20', '11:30', '11:40', '11:50', '12:00',  
	  '12:10', '12:20', '12:30', '12:40', '12:50', '13:00',
	  '13:10', '13:20', '13:30', '13:40', '13:50', '14:00',
	  '14:10', '14:20', '14:30', '14:40', '14:50', '15:00',  
	  '15:10', '15:20', '15:30', '15:40', '15:50', '16:00',
	  '16:10', '16:20', '16:30', '16:40', '16:50', '17:00',
	  '17:10', '17:20', '17:30', '17:40', '17:50', '18:00'  
	
	 ]
	});
</script>
</html>
