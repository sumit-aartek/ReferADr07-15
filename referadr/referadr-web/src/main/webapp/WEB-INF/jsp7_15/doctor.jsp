<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<link rel="stylesheet" href="css7_15/foundation.css" />
<link rel="stylesheet" href="css7_15/style.css" />
	<script src="js7_15/jquery.1.9.1.min.js" type="text/javascript"></script>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Doctor</title>

<script type='text/javascript'>//<![CDATA[ 

  $(document).ready(function () {
        // number of records per page
        var pageSize = 8;
        // reset current page counter on load
        $("#hdnActivePage").val(1);
        // calculate number of pages
        var numberOfPages = $('table tr').length / pageSize;
        numberOfPages = numberOfPages.toFixed();
        // action on 'next' click
        
        $('.pagination a.go-ahead').click(function(){
        	// show only the necessary rows based upon activePage and Pagesize
            $("table#testTable tbody tr:nth-child(-n+" + (($("#hdnActivePage").val() * pageSize) + pageSize) + ")").show();
            $("table#testTable tbody tr:nth-child(-n+" + $("#hdnActivePage").val() * pageSize + ")").hide();
            var currentPage = Number($("#hdnActivePage").val());
            // update activepage
            $("#hdnActivePage").val(Number($("#hdnActivePage").val()) + 1);
            // check if previous page button is necessary (not on first page)
            if ($("#hdnActivePage").val() != "1") {
                $("a.go-previous").show();
                $("a.go-previous span").show();
            }
            // check if next page button is necessary (not on last page)
            if ($("#hdnActivePage").val() == numberOfPages) {
                $("a.go-ahead").hide();
                $("a.go-ahead span").hide();
            }
        });
        
        $('.pagination a.go-previous').click(function(){
        	var currentPage = Number($("#hdnActivePage").val());
            $("#hdnActivePage").val(currentPage - 1);
            // first hide all rows
            $("table#testTable tbody tr").hide();
            // and only turn on visibility on necessary rows
            $("table#testTable tbody tr:nth-child(-n+" + ($("#hdnActivePage").val() * pageSize) + ")").show();
            $("table#testTable tbody tr:nth-child(-n+" + (($("#hdnActivePage").val() * pageSize) - pageSize) + ")").hide();
            // check if previous button is necessary (not on first page)
            if ($("#hdnActivePage").val() == "1") {
                $("a.go-previous").hide();
                $("a.go-previous span").hide();
            } 
            // check if next button is necessary (not on last page)
            if ($("#hdnActivePage").val() < numberOfPages) {
                $("a.go-ahead").show();
                $("a.go-ahead span").show();
            } 
            if ($("#hdnActivePage").val() == 1) {
                $(".pagination span").hide();
            }
        });
        

    });    


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
					<li><a href="#">Our Practice</a></li>
					<li class="breadcrum-icon"><i class="fa fa-play"></i></li>
					<li><a href="doctors.html">Doctors</a></li>
				</ul>
			</div>
			<!--#breadcrumbs-container-->
			<div class="panel callout">
				<div class="small-12 medium-12 large-12 columns">
					<a href="#" class="add-staff">Add Staff</a>
				</div>

				<form:form id="formId" action="saveDoctorInfo.do"
					modelAttribute="ProviderInfo" method="POST"
					class="doctors-page-form" autocomplete="off">
					<div class="small-12 medium-6 large-5 columns">
						<!-- <input type="text" placeholder="First Name"> -->
						<form:input path="providerFirstName" placeholder="First Name"
							class="validate[required]" />
						<!-- 	 <input
							type="text" placeholder="Email ID"> -->
						<form:input path="providerEmail" placeholder="Email Id"
							class="validate[required, custom[email]]" />
						<form:hidden path="practiceInfo.practiceId"
							value="${providePracticeId }" />
					</div>

					<div class="small-12 medium-6 large-5 columns">
						<!-- 	<input type="text" placeholder="Last Name"> -->
						<form:input path="providerLastName" placeholder="Last Name"
							class="validate[required]" />
						<!-- 						
						 <select>
							<option value="Nurse">Nurse</option>
							<option value="Doctor">Doctor</option>
							<option value="Lab Assistant">Lab Assistant</option>
							<option value="Other">Other</option>
						</select> -->

						<form:select path="radCodes.codeId">
							<c:forEach items="${RadCodes}" var="proRole">

								<form:option value="${proRole.codeId}"
									label="${proRole.codeValue}" />

							</c:forEach>
						</form:select>

					</div>
					<div class="small-12 medium-12 large-2 columns">
						<input name="add" type="submit" value="add"
							class="button doctors-add-submit">
					</div>
				</form:form>
				<!--.doctors-page-form-->
				<div class="small-12 medium-12 large-12 columns">
					<div id="pagination" class="pagination">
						<a href="javascript:void(0);" style="display:none" class="go-previous"><i	class="fa fa-chevron-circle-left"></i></a> 
						<a href="javascript:void(0);" class="go-ahead"><i class="fa fa-chevron-circle-right"></i></a>
						<input type="hidden" id="hdnActivePage" value="1" />  
					</div>
					<!--#pagination-->
				</div>


				<div class="doctors-table">
					<div class="small-12 medium-12 large-12 columns no-padding">
						<table id="testTable" width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td>Name</td>
								<td>Email ID</td>
								<td>Role</td>
								<td>Action</td>
							</tr>
							<c:forEach items="${ProviderInfoList}" var="provInfo">
								<tr>
									<td>${provInfo.providerFirstName}</td>
									<td>${provInfo.providerEmail}</td>
									<td>${provInfo.radCodes.codeDesc}</td>
									<td>
											<a href="editDoctor.do?provId=${provInfo.providerId}">Edit</a>
											<!-- <a href="#">Edit</a>  -->
											<br> <a
												href="doctorTiming.do?provId=${provInfo.providerId}">Timing</a>
									</td>
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

	<footer id="footer-container">
	<div class="footer-inner-container">
		<div class="row"></div>
	</div>
	<!--.footer-inner-container--> </footer>
	<!--#footer-container-->


</body>
</html>