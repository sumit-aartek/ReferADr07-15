<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
            <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script src="js7_15/jquery.1.9.1.min.js" type="text/javascript"></script>
<script src="js7_15/jsp-js/pagination.js" type="text/javascript"></script>
<script type="text/javascript">
	var _gaq = _gaq || [];
	_gaq.push([ '_setAccount', 'UA-36251023-1' ]);
	_gaq.push([ '_setDomainName', 'jqueryscript.net' ]);
	_gaq.push([ '_trackPageview' ]);

	(function() {
		var ga = document.createElement('script');
		ga.type = 'text/javascript';
		ga.async = true;
		ga.src = ('https:' == document.location.protocol ? 'https://ssl'
				: 'http://www')
				+ '.google-analytics.com/ga.js';
		var s = document.getElementsByTagName('script')[0];
		s.parentNode.insertBefore(ga, s);
	})();
</script>

</head>
<body>
						<div id="content-inner-main-right" class="small-12 medium-9 large-9 columns">
							<div class="main-content-inside">
								<div id="breadcrumbs-container" class="small-12 medium-12 large-12 columns">
									<ul class="breadcrumbs">
									  <li><a href="#">Referrals</a></li>
									  <li class="breadcrum-icon"><i class="fa fa-play"></i></li>
									  <li><a href="sent.html">Sent</a></li>
									</ul>
								</div><!--#breadcrumbs-container-->
								<div class="panel callout">
	                  <div class="small-12 medium-12 large-12 columns">
									<div id="pagination" class="pagination">
						<a href="javascript:void(0);" style="display: none"
							class="go-previous"><i class="fa fa-chevron-circle-left"></i></a>
						<a href="javascript:void(0);" class="go-ahead"> 
						<i	class="fa fa-chevron-circle-right"></i></a>
					    <input type="hidden" id="hdnActivePage" value="1" />
					</div>
					<!--#pagination-->
				</div>
							
								
								<div class="doctors-table">
									<div class="small-12 medium-12 large-12 columns no-padding">
									<table width="100%" id="testTable" cellpadding="0" cellspacing="0"  requestURI="sendBox.do">
									<thead>
									  <tr>
										<td width="113">Practice Name</td>
										<td width="110">Patient Name</td>
										<td>Message</td>
                                        <td width="120">Creation Date</td>
									  </tr>
						            </thead>
						            <c:forEach items="${referralInfoList}" var="refInfo" >
						            
						                 <tr>
						                   <td>${refInfo.practiceName}</td>
						                   <td> ${refInfo.pateintFirstName}&nbsp ${refInfo.pateintLastName} </td>
						                   <td>${refInfo.proNotes}</td>
									       <td>${refInfo.creationDate}</td>
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