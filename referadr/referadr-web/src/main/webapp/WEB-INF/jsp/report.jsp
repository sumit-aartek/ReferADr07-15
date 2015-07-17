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
		var objButton = document.getElementById("exportButton");
		objButton.style.display = "none";
		var hideTextField = document.getElementById("text");
		hideTextField.style.display = "none";
		var specialityInfo = document.getElementById("pracSpcl");
		specialityInfo.style.display = "none";
	});
</script>


<script>
	function showTextField() {
		var selectType = document.getElementById('reportSection').value;
		if (selectType == "Speciality") {
			var specialityInfo = document.getElementById("pracSpcl");
			specialityInfo.style.display = "block";
			var hidePatientInfo = document.getElementById("text");
			hidePatientInfo.style.display = "none";
			$('#practiceSpectialtyId').empty();
			$
					.ajax({
						url : "practiceSpecialtyByPractice.do",
						type : "GET",
						contentType : "application/json; charset=utf-8",
						success : function(t) {
							var e = t.practiceSpecialtyList;
							var practiceSpecialityValue = t.practiceSpecialtyList[0];

							for (i = 0; i < e.length; i++) {
								var spl = e[i]
								$("#practiceSpectialtyId")
										.append(
												$(
														"<option value='"+ spl.praticeSplID+ "'></option>")
														.text(
																spl.praticeSplDesc))
							}

							if (e.length == 1) {
								document.getElementById('practiceSpectialtyId').value = practiceSpecialityValue.praticeSplID;
								//here we are puting ajax code for practice dropdown
							}
						},
						error : function() {
							$("#practiceSpectialtyId").text(""), $(
									"#practiceSpectialtyId").append(
									$("<option value='0'></option>").text(
											"Select Specialty"));
						}
					})
		} else {
			var hidePatientInfo = document.getElementById("text");
			hidePatientInfo.style.display = "block";
			var specialityInfo = document.getElementById("pracSpcl");
			specialityInfo.style.display = "none";
			document.getElementsByName('searchKey')[0].placeholder = selectType;
		}
	}

	//search functionality
	function search() {

		var patientIn = document.getElementById("patientIn").checked;
		
		var keyToSearch = $("#reportSection").val();
		var valueToSearch
		if (keyToSearch == "Speciality") {
			valueToSearch = $("#practiceSpectialtyId").val();
		} else {
			valueToSearch = $("#reportType").val();
		}
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();
		if (startDate == "" && endDate != "") {
			$('#demo2').append('<div></div>').html("Please Select Start Date ");
			var obj2 = document.getElementById("div2");
			obj2.style.display = "true";
		} else {
			$('#demo2').append('<div></div>').html("");
			var obj2 = document.getElementById("div2");
			obj2.style.display = "true";
			$
					.ajax({
						url : "reportSearchAction.do?keyToSearch="
								+ encodeURIComponent(keyToSearch)
								+ '&valueToSearch='
								+ encodeURIComponent(valueToSearch)
								+ '&flag=' + encodeURIComponent(patientIn)
								+ '&startDate=' + encodeURIComponent(startDate)
								+ '&endDate=' + encodeURIComponent(endDate),
						type : "GET",
						contentType : "application/json; charset=utf-8",
						success : function(t) {
							var objButton = document.getElementById("exportButton");
							objButton.style.display = "block";
							
							var e = t.allDetailsList;
							var showData = e;
							$('#showDataTable').empty();
							if (showData != null && showData != "") {
								$('#showDataTable')
										.append(
												'<table id="testTable" width="100%" border="1" cellpadding="0" cellspacing="0"></table>');
								var table = $('#showDataTable').children();
								table
										.append("<tr class='first-row-heading-table'><td>S. No.</td><td>Patient Name</td><td>Insurance</td><td>Doctor Name</td><td>Referred By</td><td>Referral Date</td></tr>");
								for (i = 0; i < e.length; i++) {
									var j = i + 1;
									table.append($("<tr><td>" + j + "</td><td>"
											+ e[i].patientFirstName
											+ " "+e[i].patientLastName+"</td><td>"
											+ e[i].insuranceCompany
											+ "</td><td>"
											+ e[i].doctorFirstName
											+ " "+e[i].doctorLastName+"</td><td>" + e[i].practiceName
											+ "</td><td>" + e[i].creationDate
											+ "</td></tr>"));
								}
							}else{	
								var objButton = document.getElementById("exportButton");
								objButton.style.display = "none";

									}
						},
						error : function() {
							
						}
					})
		}

	}
</script>

<script>
var tableToExcel = (function() {
	  var uri = 'data:application/vnd.ms-excel;base64,'
	    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>'
	    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
	    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
	  return function(table, name) {
	    if (!table.nodeType) table = document.getElementById(table)
	    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
	    window.location.href = uri + base64(format(template, ctx))
	  }
	})()

</script>


</head>
<body>
	<!--Main Site Goes Here-->
	<div class="row">
		<div id="practice-area-content"
			class="small-12 medium-12 large-12 columns">
			<div id="div2" class="black_content">
				<p id="demo2" style="color: red"></p>

			</div>
			<div class="small-12 medium-9 large-6 columns">
				<label> <input type="hidden" name="identifireVar"
					value="practice" id="searchName" />
				</label>
			</div>
			<div class="small-12 medium-9 large-3 columns"></div>

			<div>
				<div class="small-12 medium-6 large-6 columns">
				<input type="radio" id="patientIn" name="PracticeFlag" value="in" checked>&nbsp Referred To ${sessionScope.practiceName}
				</div><div class="small-12 medium-6 large-6 columns">
				<input type="radio" id="patientOut" name="PracticeFlag" value="out" checked>&nbsp Referred By ${sessionScope.practiceName}
				</div>
				<div class="small-12 medium-6 large-6 columns">
					<select id="reportSection" onChange="showTextField()">
						<option value="0" label="Select" />
						<option value="Speciality" label=" Speciality" />
						<option value="Practice" label=" Practice" />
						<option value="Provider" label=" Provider" />
						<option value="Insurance" label=" Insurance" />
					</select>
				</div>
				<div id="text">
					<div class="small-12 medium-4 large-6 columns">
						<input type="text" name="searchKey" placeholder="puthere"
							id="reportType" />
					</div>
				</div>

				<div id="pracSpcl">
					<div class="small-12 medium-6 large-6 columns">
						<select id="practiceSpectialtyId">
							<option value="0" label="Select" />
						</select>
					</div>
				</div>

				<div class="small-12 medium-12 large-6 columns">
					<input type="date" id="startDate" placeholder="MM/DD/YYYY"
						class="textfield validate[custom[date]]" />
				</div>
				<div class="small-12 medium-12 large-6 columns">
					<input type="date" id="endDate" placeholder="MM/DD/YYYY"
						class="textfield validate[custom[date]]" />
				</div>
				<div class="small-12 medium-12 large-6 columns">
					<input type="button" value="Generate" onclick="search()"
						id="search" "/>
				</div>
				&nbsp
				<div id="showDataTable"></div>
			<input type="button" id="exportButton" onclick="tableToExcel('testTable', 'Report Table')" value="Export to Excel"> 
			</div>
		</div>
		<!--administrators-form-->
	</div>
	<!--row-->
	<!--Main Site Ends Here-->
</body>
</html>
