<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
						<div id="content-inner-main-right" class="small-12 medium-9 large-9 columns">
							<div class="main-content-inside">
								<div id="breadcrumbs-container" class="small-12 medium-12 large-12 columns">
									<ul class="breadcrumbs">
									  <li><a href="#">Referrals</a></li>
									  <li class="breadcrum-icon"><i class="fa fa-play"></i></li>
									  <li><a href="draft.html">Draft</a></li>
									</ul>
								</div><!--#breadcrumbs-container-->
								<div class="panel callout">
																		
									<form action="" method="get" class="inbound-page-form">
										<div class="small-12 medium-12 large-8 columns">
											<input type="search" placeholder="Search" id="search-field">
										</div>

										<div class="small-12 medium-12 large-4 columns">
											<select>
											  <option value="Nurse">Practice/CH</option>
											  <option value="Doctor">Doctor</option>
											  <option value="Lab Assistant">Lab Assistant</option>
											  <option value="Other">Other</option>
											</select>
											
										</div>
									</form><!--.inbound-page-form-->
																	
									
								
								<div class="small-12 medium-12 large-12 columns">
									<div id="pagination">
										<a href="#" class="pagination-icon"><i class="fa fa-chevron-circle-left"></i></a>
										<a href="#">1</a>
										<a href="#">2</a>
										<a href="#">3</a>
										<a href="#" class="pagination-icon"><i class="fa fa-chevron-circle-right"></i></a>
									</div><!--#pagination-->
								</div>
								
								<div class="doctors-table">
									<div class="small-12 medium-12 large-12 columns no-padding">
									<table width="100%" cellpadding="0" cellspacing="0">
									  <tr>
										<td width="113">Practice Name</td>
										<td width="110">Patient Name</td>
										<td>Message</td>
										<td width="100">Date</td>
										<td width="80">Action</td>
									  </tr>
										<c:forEach items="${referralInfoList}" var="provInfo">
								<tr>
									<td>${provInfo.practiceName}</td>
									<td>${provInfo.patientFirstName}</td>
									<td>${provInfo.proNotes}</td>
									<td>${provInfo.creationDate}</td>
									<td>
											<a href="sendPatientToReferPatient.do?draftId=${provInfo.refId}">Refer</a>
											
										
									</td>
								</tr>
							</c:forEach>
						
									  
									</table>

									</div>
								</div><!--.doctors-table-->
								
								</div><!--.panel callout-->
							</div><!--.main-content-inside-->
						</div><!--#content-inner-main-right-->
</body>
</html>