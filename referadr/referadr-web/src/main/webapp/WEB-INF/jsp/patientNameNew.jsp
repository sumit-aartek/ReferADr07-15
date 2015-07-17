<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Patient History</title>
<script type="text/javascript">

function callhiddendiv(divno){

var elements = document.getElementsByClassName('hiddendiv')

for (var i = 0; i < elements.length; i++){
    elements[i].style.display = 'none';
}
var divno = document.getElementById("hiddendiv"+divno);
divno.style.display = "block";

$('.hiddendiv').delay(10000).fadeOut();


/* var x = document.getElementsByClassName("hiddendiv");
x.style.display = "none"; */

}


function hideallhiddendiv(){
	var elements = document.getElementsByClassName('hiddendiv')
	for (var i = 0; i < elements.length; i++){
	    elements[i].style.display = 'none';
	}
	}

</script>
<script type="text/javascript">
	function openOne() {

		var oneflag = document.getElementById('oneflag').value;
		//alert(oneflag);
		if (oneflag == "false") {
			var one = document.getElementById("one");
			one.style.display = "block";
			document.getElementById('oneflag').value = 'true';
		}
		if (oneflag == "true") {
			var one = document.getElementById("one");
			one.style.display = "none";
			document.getElementById('oneflag').value = 'false';
		}

	}
</script>
</head>
<body>
	<div id="content-right-main"
		class="small-12 medium-12 large-10 columns">
		<div id="main-content-full"
			class="small-12 medium-12 large-12 columns">
			<h1 class="red-heading">Patient History</h1>
			<div class="refer-a-doc-new-my-account-screen">
				<div class="small-12 medium-12 large-12 columns ">


					<div class="small-12 medium-12 large-12 columns ">
						<div class="small-12 medium-12 large-6 columns ">
							<p onclick="openOne()"
								style="font-size: 24px; margin-top: 1%; color: #7C7C7C;">Patient
								Info</p>
						</div>
					</div>
					<form:form id="formID" method="POST"
						modelAttribute="PateintAndInsuranceInfoVO" autocomplete="off">
						<div id="one" class="accordion" style="display: none;">
							<input id="oneflag" value="false" type="hidden" />
							<div class="small-12 medium-12 large-6 columns">
								<label>Patient First Name</label>
								<form:input path="pateintFirstName"
									placeholder="Patient First Name" disabled="true" />
							</div>
							<div class="small-12 medium-12 large-6 columns">
								<label>Patient Last Name</label>
								<form:input path="pateintLastName"
									placeholder="Patient Last Name" disabled="true" />
							</div>

							<div class="small-12 medium-12 large-6 columns">
								<label>Phone No.</label>
								<form:input path="locPhone" placeholder="Phone" disabled="true" />
							</div>

							<div class="small-12 medium-12 large-6 columns">
								<label>Email ID</label>
								<form:input path="pateintEmail" placeholder="Email ID"
									disabled="true" />
							</div>
							<div class="small-12 medium-12 large-6 columns">
								<label>Gender</label>
								<form:select path="patientGender" id="gender" required="true"
									disabled="true">
									<form:option value="G" label="Gender" />
									<form:option value="M" label="Male" />
									<form:option value="F" label="Female" />
								</form:select>
							</div>

							<div class="small-12 medium-12 large-6 columns">
								<label>Date of Birth</label>
								<fmt:parseDate value="${PateintAndInsuranceInfoVO.patientDob}"
									type="DATE" pattern="yyyy-MM-dd" var="formatedDate" />
								<fmt:formatDate value="${formatedDate}" var="dateFor"
									type="date" pattern="MM-dd-yyyy" />
								<input type="text" value="${dateFor}" disabled="true" />
							</div>
							<hr>
							<div class="small-12 medium-12 large-6 columns">
								<label>Insurance Name</label>
								<form:input path="insuranceCompany" placeholder="Insurance Name"
									disabled="true" />
							</div>

							<div class="small-12 medium-12 large-6 columns">
								<label>Insurance ID</label>
								<form:input path="insuranceId" placeholder="Insurance ID"
									disabled="true" />
							</div>

							<div class="small-12 medium-12 large-6 columns">
								<label>Group</label>
								<form:input path="patientInsuranceGroup" placeholder="Group"
									disabled="true" />
							</div>

							<hr>

							<c:forEach var="document" items="${insdocList}">
								<c:set var="num" value="${num + 1}" />
								<div class="small-12 medium-12 large-6 columns">

									<a
										href="<c:url value="download.do">
			<c:param name="filePath" value="${document.docPath}"/>
		     <c:param name="fileName" value="${document.docName}"/>
		        </c:url>">${num}.&nbsp;${document.docName}
									</a>
								</div>
								<br />
							</c:forEach>

						</div>
					</form:form>
					<br>
					<br>
					<table border="1" cellpadding="0" cellspacing="0" width="100%">
						<tr class="first-row-of-table">
							<td>

							</td>
							<td>Practice Name</td>
							<td>Provider Name</td>
							<td>Date</td>
							<td>Referral Reason</td>
							<td>Description</td>
						</tr>
						<c:forEach items="${infos}" var="info">
						<c:set var="rownum" value="${rownum + 1}" />
							<tr>
								<td>
								
								<p style="margin-top: 1.15rem;" onclick="callhiddendiv(${rownum})">view</p>
								
											<div id="hiddendiv${rownum}" class="hiddendiv" style="display:none; position:absolute; color:#388BCF; border:1px solid #DCA; background:#E8B857;">
   											 <div class="small-12 medium-12 large-12 columns">
												  ${info.refNotes}
   											 </div><br>
    
								<c:forEach var="document" items="${info.docList}">
 								<c:set var="num" value="${num + 1}" />
								<div class="small-12 medium-12 large-12 columns">
								<a href="<c:url value="download.do">
										<c:param name="filePath" value="${document.docPath}"/>
		 								    <c:param name="fileName" value="${document.docName}"/>
		   								     </c:url>">${num}.&nbsp;${document.docName}
								 </a>
	</div><br>
</c:forEach>

								</div>
								
								
								
								</td>
								<td>${info.practiceName}</td>
								<td>${info.providerName}</td>
								<td>${info.creationDate}</td>
								<td>${info.refReason}</td>
								<td>${info.refNotes}</td>

							</tr>
						</c:forEach>
					</table>


				</div>
			</div>
			<!--.refe-a-doc-new-screen-form-->
		</div>
		<!--.refer-a-doc-new-copy-inbox-table-->
	</div>
	<!-----#main-content-full------->
	</div>
	<!-----#content right main----------->
</body>
</html>