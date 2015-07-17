<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html class="no-js" lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Connecting Doctors</title>
    
     <script src="js/jquery.1.9.1.min.js"></script>
	<script src="js/vendor/jquery.ui.widget.js"></script>

	<script src="js/page-js/jquery.fileupload.js"></script>
	<script src="js/page-js/update_uploadfunction.js"></script>
	
	
    <link rel="stylesheet" href="css/foundation.css" />
    <link rel="stylesheet" href="css/style.css" />
    <script src="js/vendor/modernizr.js"></script>
	
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="responsive/style.css">
	<link rel="stylesheet" type="text/css" href="responsive/responsive.css">
	
	<style type="text/css">
	input[type="file"] {
  	width: 46%; }
	</style>
	<script type="text/javascript">
	   function removeNotes()
	   {
		   document.getElementById('PrvoRederalNotes').value="";
	   }
	
       $(function() { $('#updateFiles').change(function (){
		  	$("#updateFileNames").empty();
    		var files = $('#updateFiles')[0].files;
    		for (var i = 0; i < files.length; i++) {
				var $p = $("<p></p>").text(files[i].name).appendTo("#updateFileNames");
    		}
		});
	   });

	
	</script>
  </head>
  <body onload="removeNotes()">
  <div class="row"> 
    <h1 align="center">Update Records</h1>
 <!--   <div id="secondary-menu" class="small-12 medium-12 large-12 columns">
   <ul>
    <li><a href="ChHome.do">Clearing House</a></li>
    <li><a href="viewCHAdmins.do">Administrators</a></li>
   <li><a href="viewPractices.do">Practices</a></li>
   <li><a href="masterData.do">Master Data</a></li>
   </ul>
  </div> -->

<div id="form-main-container-of-fancybox" style="margin-left:14%;" class="small-12 medium-12 large-8 columns">

<div id="float" class="panel">

<form:form id="formID" method="POST" action="chUpdateAdditionalNotes.do" modelAttribute="Referral_Provider_Action" autocomplete="off" name="form" target="_top" enctype="multipart/form-data" >
<!-- 
<div class="small-12 medium-12 large-12 columns">
<textarea placeholder="Referal Reason"></textarea>
</div>

<div class="small-12 medium-12 large-12 columns">
<textarea placeholder="Diagnosis Code"></textarea>
</div> -->

<div class="small-12 medium-12 large-12 columns">
<form:textarea path="providerNotes" placeholder="Additional Notes" id="PrvoRederalNotes" />
</div>
<form:hidden path="refProviderActionId" value="${providerActionId}"/>
<form:hidden path="patientReferralInfo.refId" value="${patientReferralId}"/>
<form:hidden path="providerInfo.providerId" value="${Referral_Provider_Action.patientReferralInfo.providerInfo.providerId}"/>
<form:hidden path="patientReferralInfo.chInfo.id" value="${Referral_Provider_Action.patientReferralInfo.chInfo.id}"/>
<div class="buttons-blue-container">
	<input id="updateFiles" type="file" accept="image/*, .pdf,.doc,.docx,.txt,.xls,.xlsx, .ppt" name="updatefiles" multiple >
	<span id="updateFileNames">No Files Selected</span>
</div>	

<hr>
<div class="buttons-blue-container">
<input type="submit" name="Submit" value="Submit" class="small radius button">
</div>
</form:form>
</div>
</div><!--#form-main-container-of-fancybox-->
    <!-- <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.2.min.js"></script> -->
	<script type="text/javascript" src="responsive/script.js"></script>
   <!--  <script src="js/vendor/jquery.js"></script> -->
    <script src="js/foundation.min.js"></script>
    <script>
      $(document).foundation();
    </script>
  </body>
</html>
