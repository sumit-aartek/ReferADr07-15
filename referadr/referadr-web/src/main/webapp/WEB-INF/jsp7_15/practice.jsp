<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script src="js7_15/jsp-js/pagination.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="css/jquery.datetimepicker.css"/>


    	 <script type="text/javascript">
window.onload = function () {
  var schedulefull = document.getElementById("all");
	schedulefull.style.display = "none";
  };
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

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-36251023-1']);
  _gaq.push(['_setDomainName', 'jqueryscript.net']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();
  

</script>





</head>
<body>

	<div id="content-inner-main-right"
		class="small-12 medium-9 large-9 columns">
		<div class="main-content-inside">
			<div id="breadcrumbs-container"
				class="small-12 medium-12 large-12 columns">
				<ul class="breadcrumbs">
					<li><a href="#">Referrals</a></li>
					<li class="breadcrum-icon"><i class="fa fa-play"></i></li>
					<li><a href="inbound.html">Inbound</a></li>
				</ul>
			</div>
			<!--#breadcrumbs-container-->
			<div class="panel callout">

				<form id="form1"  action="searchPracticePatient.do"  method="POST" class="inbound-page-form">
					<div class="small-12 medium-12 large-6 columns">
					<input type="text" name="keywords" id="searchName" placeholder="Search">
					
			
					</div>

					<div class="small-12 medium-12 large-3 columns">
					       <select id="select" name="typeSelect">
							<option value="practice/CH">Practice/CH</option>
							<option value="PatientName">Patient Name</option>
            	           </select>
					</div>
					<div class="small-12 medium-12 large-3 columns">
					 <input type="submit" value="Search" class="button">
					 </div>
				</form>
				
			
				<!--.inbound-page-form-->

				<div class="small-12 medium-12 large-12 columns">
					<div id="pagination" class="pagination">
						<a href="javascript:void(0);" style="display: none"
							class="go-previous"><i class="fa fa-chevron-circle-left"></i></a>
						<a href="javascript:void(0);" class="go-ahead"> <i
							class="fa fa-chevron-circle-right"></i></a> <input type="hidden"
							id="hdnActivePage" value="1" />
					</div>
					<!--#pagination-->
				</div>
				
				
				<div class="doctors-table">
					<div class="small-12 medium-12 large-12 columns no-padding">
					

							<table id="testTable" width="100%" cellpadding="0" cellspacing="0" >
								<thead>
									<tr>
										<td width="113">Practice Name</td>
										<td width="110">Patient Name</td>
										<td width="100">Date</td>
										<td>Message</td>
										<td width="110">Action</td>
									</tr>
								</thead>

								<c:forEach items="${referralInfoList}" var="refInfo">
									<tr>
										<td>${refInfo.practiceName}</td>
										<td><a
											href="patientNameView.do?patientRefInfo=${refInfo.refId}">${refInfo.pateintFirstName}&nbsp${refInfo.pateintLastName}</a>
										</td>
										<td>${refInfo.creationDate}</td>
										<td>${refInfo.proNotes}</td>
										<td><a
											id="a${refInfo.refId}${refInfo.currentStatusId}${refInfo.provRefActionId}"
											href="refralReasonsView.do?referCurrentStatusId=${refInfo.currentStatusId}&patientReferralId=${refInfo.refId}"></a>
											<a
											id="b${refInfo.refId}${refInfo.currentStatusId}${refInfo.provRefActionId}"
											href="updateRefralView.do?patientReferralId=${refInfo.refId}&providerId=${refInfo.provRefActionId}"></a>
											<a
											id="c${refInfo.refId}${refInfo.currentStatusId}${refInfo.provRefActionId}"
											href="print.do?patientReferralId=${refInfo.refId}&providerId=${refInfo.provRefActionId}"></a>

											<select
											id="actionselect${refInfo.refId}${refInfo.currentStatusId}${refInfo.provRefActionId}"
											onchange="return selectaction(${refInfo.refId},${refInfo.providerId},${refInfo.currentStatusId}${refInfo.provRefActionId})">
												<option>Select</option>
												<option>Refer</option>
												<option>Update</option>
												 <option>Print</option> 
												 <option>Schedule</option>
										</select>

											<div class="modal fade" id="shaduleModal" tabindex="-1"
												role="dialog" aria-labelledby="exampleModalLabel"
												aria-hidden="true" style="display: none;">
												<div class="modal-dialog">
													<div class="modal-content large-12 columns">
														<div class="modal-header">
															<button onclick="closediv()" type="button" class="close"
																data-dismiss="modal" aria-label="Close">
																<span aria-hidden="true"><img class="close"
																	src="bootstrap/alert_boxes_close_ico.png"></span>
															</button>
															<h4 class="modal-title" id="exampleModalLabel">Schedule</h4>
														</div>
														<div id="mailsent" class="modal-body">
															<form>
																<div class="form-group">

																	<div id="all">
																		<div class="small-12 medium-12 large-12 columns"
																			id='providerdropdowm'>
																			Please select referral provider <select
																				class="form-control" onchange="setProviderId()"
																				id="extraproviderlist">
																				<option value="0">-Select A Provider-</option>
																				<c:forEach items="${ProviderInfoForSchedule}"
																					var="refProviderInfoo">
																					<option value="${refProviderInfoo.providerId}">
																						${refProviderInfoo.providerFirstName}</option>
																				</c:forEach>
																			</select>


																		</div>
																	</div>

																	<input type="hidden" class="form-control"
																		id="sheduleWithProviderId" /> <input type="hidden"
																		class="form-control" id="sheduleReferralId" /> <input
																		type="hidden" class="form-control"
																		id="sheduleProviderId" /> <label for="recipient-name"
																		class="control-label">Select Date & Time</label> <input
																		type="text" class="form-control" id="datetimepicker" /><br>

																</div>
																<div class="form-group">
																	<label for="message-text" class="control-label">Notes</label>
																	<textarea class="form-control" id="sheduleNotes"></textarea>
																</div>
																<div class="modal-footer">

																	<button type="button" class="btn btn-primary"
																		onclick="return saveShedule()" id="sheduleButton">Save</button>

																	<br>
																</div>
															</form>
															<p id="demo222" style="color: red"></p>
														</div>

													</div>
												</div>
											</div>
											<!-- /.modal --></td>
									</tr>
								</c:forEach>


							</table>

					

					</div>
				</div>
				<!--.doctors-table-->

			</div>
			<!--.panel callout-->
		</div>
		<!--.main-content-inside-->
	</div>
	<!--#content-inner-main-right-->



</body>
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