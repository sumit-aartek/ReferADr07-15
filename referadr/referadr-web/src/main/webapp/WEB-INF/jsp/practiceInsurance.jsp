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

<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="responsive/script.js"></script>
<script src="js/vendor/jquery.js"></script>
<script src="js/foundation.min.js"></script>

<!doctype html>
<html class="no-js" lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Reports</title>
<link rel="stylesheet" href="css/foundation.css" />
<link rel="stylesheet" href="css/style.css" />
<script src="js/vendor/modernizr.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="responsive/style.css">
<link rel="stylesheet" type="text/css" href="responsive/responsive.css">

<script>
	$(document).foundation();
</script>

<script type="text/javascript">
	$(document).ready(function() {
		var insidlist=${inslist};
		var selectedinslist=${selectedinslist};
		
		//alert(insidlist);
		
		 for(var i=0;i<insidlist.length;i++){
$("#selected_insuranceId_"+insidlist[i]).hide();
			 }
		 
		 for(var i=0;i<selectedinslist.length;i++){
			 $("#selected_insuranceId_"+selectedinslist[i]).show();
			 document.getElementById('checkbox_insuranceId_'+selectedinslist[i]).checked=true;
			 			 }
/* 		var hideTextField = document.getElementById("text");
		hideTextField.style.display = "none"; */
		
	});
</script>


<script>
	function addDeletePracticeIns(insuranceId) {
		//alert("hi");
		
	//	alert(insuranceId);
		var dosecheckboxselected = document.getElementById('checkbox_insuranceId_'+insuranceId).checked;
	//	alert(dosecheckboxselected);

if(dosecheckboxselected){
//if selected
	$.ajax({
		url : "insertPacticeIns.do?insId="+insuranceId,
		type : "GET",
		contentType : "application/json; charset=utf-8",
		success : function(t) {

//alert("success");
$("#selected_insuranceId_"+insuranceId).show();
		},
		error : function() {
		//alert("error");
		}
	}) 
}else{
//if deselected
		$.ajax({
		url : "deletePacticeIns.do?insId="+insuranceId,
		type : "GET",
		contentType : "application/json; charset=utf-8",
		success : function(t) {

//alert("deletesuccess");
$("#selected_insuranceId_"+insuranceId).hide();
		},
		error : function() {
			//alert("error");
		}
	})
}
	}


</script>

</head>
<body>
	<!--Main Site Goes Here-->
	<div class="row" style="margin-left: 2px;">
		<div class="col" style="border: 1px solid; ">
		<div id="allInsuranceListDiv" style=" margin-left: 2px; margin-top: 2px; margin-bottom: 2px; overflow-y: auto; height: 500px;">
		
		
		
		 <c:forEach items="${allInsuranceList}" var="allInsuranceList">
         <input TYPE=checkbox onclick="addDeletePracticeIns(${allInsuranceList.insuranceId})" id="checkbox_insuranceId_${allInsuranceList.insuranceId}" VALUE="${allInsuranceList.insuranceId}" > ${allInsuranceList.insuranceName} <BR>
 </c:forEach> 
		
		</div>
		</div>
		<div class="col" style="border: 1px solid;">
		<div id="practiceInsuranceListDiv" style=" margin-left: 2px; margin-top: 2px; margin-bottom: 2px; overflow-y: auto; height: 500px;">
		
 <c:forEach items="${allInsuranceList}" var="allInsuranceList">
         <%-- <input TYPE=checkbox onclick="addDeletePracticeIns(${allInsuranceList.insuranceId})" id="checkbox_insuranceId_${allInsuranceList.insuranceId}" VALUE="${allInsuranceList.insuranceId}" > ${allInsuranceList.insuranceName} <BR> --%>
<label id="selected_insuranceId_${allInsuranceList.insuranceId}">${allInsuranceList.insuranceName}</label>
 </c:forEach> 

		</div>
		</div>
		<!--administrators-form-->
	</div>
	<!--row-->
	<!--Main Site Ends Here-->
</body>
</html>
