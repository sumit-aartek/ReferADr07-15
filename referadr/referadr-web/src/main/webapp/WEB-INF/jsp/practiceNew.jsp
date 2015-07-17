<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<link rel="stylesheet" type="text/css" href="css/jquery.datetimepicker.css"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
							//alert("success=>"+h.creationDate+"...start..."+h.startTime);
							if(h.creationDate==null || h.startTime==null){
								document.getElementById("datetimepicker").value=" Not Scheduled,You can schedule now.";
								}else{
							document.getElementById("datetimepicker").value=h.creationDate+" "+h.startTime;
								}
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
	
	
/* 	var headersearch = document.getElementById("headersearch");
	headersearch.style.display = "block"; */
	
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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Connecting Doctors</title>

</head>
<body>
 <a id='callpractice'  href="practice.do"></a>
        
               <div id="content-right-main" class="small-12 medium-12 large-10 columns">
                  <div id="main-content-full" class="small-12 medium-12 large-12 columns">


		<div class="search-top-container">
		<form id="form1" action="searchPracticePatient.do" name="form1" method="post">
						<div class="small-12 medium-12 large-6 columns no-padding">
							<input type="text" name="keywords" id="searchName" placeholder="Search">
						</div>
						<div class="small-12 medium-12 large-4 columns no-padding padding-left-select-field">
						 <select id="select" name="typeSelect">
							<option value="practice/CH">Practice/CH</option>
							<option value="PatientName">Patient Name</option>
            	         </select>
						</div>
						<div class="small-12 medium-12 large-2 columns no-padding padding-left-select-field">
						 <input type="Submit" value="Search" class="submit-button-of-search">
						</div>
						</form>
					</div><!--.search-top-container-->


			        <!-- <h1 class="red-heading">Inbox</h1> -->
    
					<div class="refer-a-doc-new-copy-inbox-table">
					
					<c:set var="count" value="0" scope="page" />
					
					
					       <c:if test="${sessionScope.param=='practiceInbox'}">
      <display:table name="referralInfoList" pagesize="4" uid="refInfo" requestURI="practice.do" cellpadding="0" cellspacing="0">
      <display:setProperty name="paging.banner.page.separator" value=""/>
      <c:set var="count" value="${count+1}" scope="page" />
      ${count}
      <display:column property="practiceName" title="Practice Name">
    
      </display:column>
       <display:column  title="Patient Name">
       <a href="patientNameView.do?patientRefInfo=${refInfo.refId}">${refInfo.pateintFirstName} ${refInfo.pateintLastName}</a> 
      <%--  <a href="#">${refInfo.pateintFirstName}</a> --%>
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
			<!-- <option>Print</option> -->
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
         
         <br>
          </div>
            </form>
            <p id="demo222" style="color: red"></p>
          </div>
          
        </div>
      </div>
    </div><!-- /.modal -->
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
       <%-- <a href="#">${refInfo.pateintFirstName}</a> --%>
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
			<!-- <option>Print</option> -->
			<option>Schedule</option>
			</select>
			
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
         
         <br>
          </div>
            </form>
            <p id="demo222" style="color: red"></p>
          </div>
          
        </div>
      </div>
    </div><!-- /.modal -->
        
             </display:column>
          
       </display:table>  </c:if> 
       

					</div><!--.refer-a-doc-new-copy-inbox-table-->                  
                  </div><!-----#main-content-full------->
              </div><!-----#content right main----------->

</body>


<script src="js/page-js/jquery.js"></script>
<script src="js/page-js/jquery.datetimepicker.js"></script>

<script>
 jQuery('#datetimepicker').datetimepicker({
	 datepicker:true,
	 format:'Y-m-d H:i',
	 allowTimes:[
	  '10:00', '10:30',
	  '11:00', '11:30',  
	  '12:00', '12:30',
	  '13:00', '13:30',
	  '14:00', '14:30',  
	  '15:00', '15:30',
	  '16:00', '16:30',
	  '17:00', '17:30', '18:00'  
	
	 ]
	});
</script>


</html>