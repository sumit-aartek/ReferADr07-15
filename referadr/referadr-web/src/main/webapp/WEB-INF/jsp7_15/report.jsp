<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<!--date picker starts here-->
		<link rel="stylesheet" type="text/css" href="css7_15/datepicker/datepickr.min.css">
	<!--date picker ends here-->
	
	
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


</script>
<script type="text/javascript">

//search functionality
function searchGenerate() {


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
											'<table id="testTable" width="100%" border="0" cellpadding="0" cellspacing="0"></table>');
							var table = $('#showDataTable').children();
							table
									.append("<thead><tr><td>S. No.</td><td>Patient Name</td><td>Insurance</td><td>Doctor Name</td><td>Referred By</td><td>Referral Date</td></tr>");
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
										+ "</td></tr></thead>"));
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
	<div id="content-inner-main-right" class="small-12 medium-9 large-9 columns">
							<div class="main-content-inside">
								<div id="breadcrumbs-container" class="small-12 medium-12 large-12 columns">
									<ul class="breadcrumbs">
									  <li><a href="reports.do">Reports</a></li>
									</ul>
								</div><!--#breadcrumbs-container-->
								
						
								<div class="panel callout">
					
															
							<div id="div2" class="black_content">
								<p id="demo2" style="color: red"></p>
							</div>
							
		
				<label> <input type="hidden" name="identifireVar"
					value="practice" id="searchName" />
				</label>
			
			

			<div>
				<div class="small-12 medium-6 large-6 columns">
				<div class="small-12 medium-12 large-12 columns">
				<input type="radio" id="patientIn" name="PracticeFlag" value="in" checked>&nbsp Referred To ${sessionScope.practiceName}
				</div>
			
				<div class="small-12 medium-12 large-12 columns">
					<select id="reportSection" onChange="showTextField()">
						<option value="0" label="Select" />
						<option value="Speciality" label=" Speciality" />
						<option value="Practice" label=" Practice" />
						<option value="Provider" label=" Provider" />
						<option value="Insurance" label=" Insurance" />
					</select>
				</div>
				
				
				
				<div class="small-12 medium-12 large-12 columns">
					<input type="text" id="startDate" placeholder="StartDate"
						 />
				</div>
				<div class="small-12 medium-12 large-12 columns">
					<input type="button" value="Generate" onclick="searchGenerate()" class="button"
						id="search" />
				</div>
				
				</div>
			
				
				</div>
				
				
				<div class="small-12 medium-6 large-6 columns">
				
					<div class="small-12 medium-12 large-12 columns">
				<input type="radio" id="patientOut" name="PracticeFlag" value="out" checked>&nbsp Referred By ${sessionScope.practiceName}
				</div>
					
					<div id="text" class="small-12 medium-12 large-12 columns">
						<input type="text" name="searchKey" placeholder="puthere"
							id="reportType" />
					</div>
				

				
					<div id="pracSpcl" class="small-12 medium-12 large-12 columns">
						<select id="practiceSpectialtyId">
							<!-- <option value="0" label="Select" /> -->
						</select>
					</div>
				

				
				<div class="small-12 medium-12 large-12 columns">
					<input type="text" id="endDate" placeholder="EndDate"
						 />
				</div>
				
				</div>
				
			<div class="doctors-table">
									<div id="showDataTable" class="small-12 medium-12 large-12 columns no-padding">
									</div>
									</div>
									<div class="small-12 medium-12 large-12 columns">
									<input type="button" class="button" id="exportButton" onclick="tableToExcel('testTable', 'Report Table')" value="Export to Excel"> 
									</div>
				&nbsp
<!-- 				<div class="clearfix"></div>
				<div class="clear-both"></div>
				<div class="small-12 medium-12 large-12 columns">
				<div id="showDataTable"></div>
				</div>
			<input type="button" id="exportButton" onclick="tableToExcel('testTable', 'Report Table')" value="Export to Excel">  -->
			</div>
									
									
									
								</div><!--.panel callout-->
							</div><!--.main-content-inside-->
						</div><!--#content-inner-main-right-->
						
							<!--Only For DatePicker Starts Here-->
        <script src="js7_15/datepicker/datepickr.min.js"></script>
        <script>
            // Regular datepickr
            datepickr('#startDate');
            datepickr('#endDate');
            // Custom date format
            datepickr('.datepickr', { dateFormat: 'd-m-Y'});

            // Min and max date
            datepickr('#minAndMax', {
                // few days ago
                minDate: new Date().getTime() - 2.592e8,
                // few days from now
                maxDate: new Date().getTime() + 2.592e8
            });

            // datepickr on an icon, using altInput to store the value
            // altInput must be a direct reference to an input element (for now)
            datepickr('.calendar-icon', { altInput: document.getElementById('calendar-input') });

            // If the input contains a value, datepickr will attempt to run Date.parse on it
            datepickr('[title="parseMe"]');

            // Overwrite the global datepickr prototype
            // Won't affect previously created datepickrs, but will affect any new ones
            datepickr.prototype.l10n.months.shorthand = ['janv', 'févr', 'mars', 'avril', 'mai', 'juin', 'juil', 'août', 'sept', 'oct', 'nov', 'déc'];
            datepickr.prototype.l10n.months.longhand = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
            datepickr.prototype.l10n.weekdays.shorthand = ['dim', 'lun', 'mar', 'mer', 'jeu', 'ven', 'sam'];
            datepickr.prototype.l10n.weekdays.longhand = ['dimanche', 'lundi', 'mardi', 'mercredi', 'jeudi', 'vendredi', 'samedi'];
            datepickr('#someFrench.sil-vous-plait', { dateFormat: '\\le j F Y' });
        </script>
<!--Ony For DatePicker Ends Here-->



						
</body>
</html>