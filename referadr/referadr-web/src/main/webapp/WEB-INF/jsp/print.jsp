<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Print</title>
    <link rel="stylesheet" href="css/foundation.css" />
    <link rel="stylesheet" href="css/style.css" />
    <script src="js/vendor/modernizr.js"></script>
	
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="responsive/style.css">
	<link rel="stylesheet" type="text/css" href="responsive/responsive.css">
		<!--Accordion CSS-->
		<link rel="stylesheet" type="text/css" href="accordian/accordian-style.css">
	<!--Accordion CSS Ends-->
	<link href="bootstrap/Untitled-1.css" rel="stylesheet">




   <script type="text/javascript">
        function PrintDiv() {
            var contents = document.getElementById("dvContents").innerHTML;
            var frame1 = document.createElement('iframe');
            frame1.name = "frame1";
            frame1.style.position = "absolute";
            frame1.style.top = "-1000000px";
            document.body.appendChild(frame1);
            var frameDoc = frame1.contentWindow ? frame1.contentWindow : frame1.contentDocument.document ? frame1.contentDocument.document : frame1.contentDocument;
            frameDoc.document.open();
            frameDoc.document.write('<html><head><title>Patient Details</title>');
            frameDoc.document.write('</head><body>');
            frameDoc.document.write(contents);
            frameDoc.document.write('</body></html>');
            frameDoc.document.close();
            setTimeout(function () {
                window.frames["frame1"].focus();
                window.frames["frame1"].print();
                document.body.removeChild(frame1);
            }, 500);
            return false;
        }
    </script>
</head>
<body>
 
<div id="dvContents" class="small-12 medium-12 large-12 columns">

<div style="border:1px solid black;height: 90%;padding: 10px 10px 10px 10px;">
<input style="margin-left: 85%;" type="button" onclick="PrintDiv();" value="Print" />

<table style="width: 100%;" border="1"  >
<tr ><td >
<label >Referring Doctor  &nbsp&nbsp  : &nbsp&nbsp  ${printVO.refproviderFirstName} ${printVO.refProviderLastName} </label>
</td><tr>
<tr ><td >
<label >Address  &nbsp&nbsp &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp: &nbsp&nbsp  ${printVO.locAddress1}, ${printVO.locCity} </label>
</td><tr>
</table>


<br>
<h4>Patient Info:</h4>
<table style="width: 100%;" border="1"><tr>
<td><label > &nbsp Name  :</label></td>
<td><label> &nbsp ${printVO.patientFirstName} ${printVO.patientLastName} </label></td>
<tr>
<tr>
<td><label > &nbsp Email Id  :</label></td>
<td><label> &nbsp ${printVO.patienEmailId} </label></td>
<tr>
<tr>
<td><label > &nbsp DOB  :</label></td>
<td><label> &nbsp ${printVO.patientDob}</label></td>
<tr>
<tr>
<td><label > &nbsp  Gender  :</label></td>
<td><label> &nbsp ${printVO.patientGender} </label></td>
<tr>
<tr>
<td><label > &nbsp  Insurance Name  :</label></td>
<td><label> &nbsp ${printVO.insuranceCompany} </label></td>
<tr>
<tr>
<td><label > &nbsp  Insurance ID  :</label></td>
<td><label> &nbsp ${printVO.patInsuranceId} </label></td>
<tr>
<tr>
<td><label > &nbsp  Group ID  :</label></td>
<td><label> &nbsp ${printVO.patInsuranceGroup} </label></td>
<tr>

</table>


<br>
<h4> Referred To:</h4>
<table style="width: 100%;" border="1">
<tr>
<td><label > &nbsp  Provider Name  :</label></td>
<td><label > &nbsp  Speciality  :</label></td>
<td><label > &nbsp  Clinic  :</label></td>
</tr>

<tr>
<td><label >  &nbsp ${printVO.providerFirstName} ${printVO.providerLastName}</label></td>
<td><label >  &nbsp ${printVO.pracSplDesc}</label></td>
<td><label > &nbsp ${printVO.practiceName}</label></td>
</tr>

</table>
<br>
<h4>Clinical Data:</h4>
<table style="width: 100%;" border="1">
<tr>
<td><label > &nbsp  Referral Reason  :</label></td>
<td><label >  &nbsp ${printVO.refProvRefReason}</label></td></tr><tr>
<td><label > &nbsp  Diagnosis Code  :</label></td>
<td><label >  &nbsp ${printVO.refProvDiagCode}</label></td></tr><tr>
<td><label > &nbsp  Notes  :</label></td><td><label > &nbsp ${printVO.refProvNotes}</label></td>
</tr>



</table>


<br><br><br><br>
<label style="margin-left: 75%;">Dr. ${printVO.refproviderFirstName} ${printVO.refProviderLastName} </label>
</div>
</div>
<br>
 
  <br>
</body>
</html>