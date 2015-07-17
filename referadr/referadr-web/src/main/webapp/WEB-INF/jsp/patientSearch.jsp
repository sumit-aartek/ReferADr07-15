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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Patient Search</title>

<script type="text/javascript">
function openBox2(data)
{
	
	
	
 var patitentName=document.getElementById('patientName');
 var patitentLastName=document.getElementById('patientLastName');
 var address1 =document.getElementById('address1');
 var address2=document.getElementById('address2');
 var city=document.getElementById('city');
 var state=document.getElementById('state');
 var zip=document.getElementById('zip');
 var phone=document.getElementById('phone');
 var fax=document.getElementById('fax');
 var dob=document.getElementById('dob');
 var ssn1=document.getElementById('ssn1');
 var gender=document.getElementById('gender');
 
 <c:forEach items="${allPatientInfoList}" var="setValue">
 	if ("${setValue.patientId}" == data){
 	 patitentName.value="${setValue.patientFirstName}";
 	 patitentLastName.value="${setValue.patientLastName}";
	 address1.value="${setValue.patientAddress1}";
	 address2.value="${setValue.patientAddress2}";
	 city.value="${setValue.patientAddressCity}";
	 state.value="${setValue.patientAddressState}";
	 zip.value="${setValue.patientAddressZip}";
	 phone.value="${setValue.patientAddressPhone1}";
	 fax.value="${setValue.patientAddressFax}";
	 dob.value="${setValue.patientDateOfBirth}";
	 ssn1.value="${setValue.patientSsn}";
	 gender.value="${setValue.patientGender}";
 	}
 	
 	
 </c:forEach>
 
 var obj4=document.getElementById("div2");
 obj4.style.display="none";
 
 var obj5=document.getElementById("div3");
	obj5.style.display="block";
	var white_content=document.getElementById("white_content");
	white_content.style.display="block";
 
}

</script>
</head>
<body>
<form:form method="POST" action="patientSearchAction.do" modelAttribute="PatientInfo" autocomplete="off">
<div id="secondary-menu" class="small-12 medium-12 large-12 columns">
			<ul>
				<li><a href="#">PRACTICE</a></li>
    <li><a href="doctor.do">DOCTORS</a></li>
    <li><a href="#">INSURANCE</a></li>
    <li><a href="#">BILLING</a></li>
			</ul>
		</div>
<div id="div2" class="black_content" >
<div class="small-12 medium-4 large-4 columns">
<form:input path="patientFirstName" placeholder="Patient Last Name" />
</div>
<div class="small-12 medium-4 large-4 columns">
<form:input path="patientDateOfBirth" placeholder="DOB"/>
</div>
<div class="small-12 medium-4 large-4 columns">
<form:input path="patientSsn" placeholder="SSN"/>
</div><br>
 <input type="submit" value="Search"  />
 <div class="row">
 <br><br>
 </div>
 
<table width="100%" border="1" cellpadding="0" cellspacing="0">
 
   
        <tr class="first-row-heading-table">
       	     <td>S.NO</td>
       	      
            <td> Name</td>
            <td>Dob</td>
            <td>SSN</td>
            
        </tr> <c:forEach items="${allPatientInfoList}" var="cat">
        <tr >
            <td><c:out value="${cat.patientId}"/></td>
            <td><a href="#"  onclick="openBox2(${cat.patientId})"><c:out value="${cat.patientFirstName}"/></a></td>
            <td><c:out value="${cat.patientDateOfBirth}"/></td>
            <td><c:out value="${cat.patientSsn}"/></td>
          <%-- <input type="hidden" id="hide" value="${cat.patientId}"></input> --%>
         <%--  <c:out value="${cat.patientId}"></c:out> --%>
     </tr></c:forEach>
</table>
</div>
<input type="hidden" id="s" name="firstName">

<%-- <c:out value="${param.firstName}"></c:out> --%>
<%--  <c:out value="${myItem.patientFirstName}"></c:out> --%>
<div id="div3" class="black_content" style="display: none">
<div class="small-12 medium-12 large-6 columns" >
<form:input path="" type="text" id="patientName"  placeholder="Patient First Name" onkeyup="stoppedTyping2()" />

</div>
<div class="small-12 medium-12 large-6 columns" >
<form:input path="" type="text" id="patientLastName"  placeholder="Patient Last Name" onkeyup="stoppedTyping2()" />
</div>
<div class="small-12 medium-12 large-6 columns">

<form:input path="" id="address1" placeholder="Address1" onkeyup="stoppedTyping2()" />
</div>

<div class="small-12 medium-12 large-6 columns">
<form:input path=""  id="address2" placeholder="Address2" onkeyup="stoppedTyping2()" />
</div>

<div id="padding-0" class="small-12 medium-12 large-8 columns">
<div class="small-12 medium-12 large-4 columns" >
<input path="" type="text" id="city" placeholder="City" onkeyup="stoppedTyping2()" >
</div>

<div class="small-12 medium-12 large-4 columns">
<form:input path=""  id="state" placeholder="State" onkeyup="stoppedTyping2()" />
</div>

<div class="small-12 medium-12 large-4 columns">
<form:input path=""  id="zip" placeholder="Zip" onkeyup="stoppedTyping2()" />
</div>

</div>

<div id="padding-0" class="small-12 medium-12 large-4 columns">

<div class="small-12 medium-12 large-6 columns">
<form:input path=""  id="phone" placeholder="Phone" onkeyup="stoppedTyping2()" />
</div>

<div class="small-12 medium-12 large-6 columns">
<form:input path=""  id="fax" placeholder="Fax" onkeyup="stoppedTyping2()" />
</div>
</div>

<div class="small-12 medium-12 large-4 columns">
<select path="patientGender" id="gender"  onkeyup="stoppedTyping2()" required>
				        <option value="-1">Select</option>
				        <option value="M">Male</option>
				        <option value="F">Female</option>
				      </select>
</div>

<div class="small-12 medium-12 large-4 columns">
<form:input path=""  id="dob" placeholder="DOB" onkeyup="stoppedTyping2()" />
</div>

<div class="small-12 medium-12 large-4 columns">
<form:input path="" type="text" id="ssn1" placeholder="SSN" onkeyup="stoppedTyping2()"/>
</div>

</div>
</form:form>
</body>
</html>