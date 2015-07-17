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
<link rel="stylesheet" type="text/css" href="css/jquery.datetimepicker.css"/>
<!doctype html>
<html class="no-js" lang="en">
  
    
       	 <script type="text/javascript">
window.onload = function () {
	
  $(".pagebanner").hide();
  $(".strong").hide();
  var pagelinkValue = $('.pagelinks').map(function(){
  return $(this).text();
  }).get();
  if(pagelinkValue==1)
  $(".pagebanner").hide();

  var headersearch = document.getElementById("headersearch");
	headersearch.style.display = "block";
  };
</script>     
 
    
    
    
    


     
     








 <style type="text/css">
 a[title] {
    display : none;
}
strong {
    display : none;
}
 </style>
    

    
    
    
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Connecting Doctors</title>
    <link rel="stylesheet" href="css/foundation.css" />
    <link rel="stylesheet" href="css/style.css" />
    <script src="js/vendor/modernizr.js"></script>
	
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="responsive/style.css">
	<link rel="stylesheet" type="text/css" href="responsive/responsive.css">
  </head>
  <body>
    <!--Main Site Goes Here-->
      <a id='callpractice'  href="practice.do"></a>
    <div class="row">
		<div id="practice-area-content" class="small-12 medium-12 large-12 columns">
			<form id="form1" action="searchPracticePatient.do" name="form1" method="post">
				<!-- <div class="small-12 medium-9 large-6 columns">
				<label>
					<input type="text" name="keywords" id="searchName"/>
					<input type="hidden" name="identifireVar" value="practice" id="searchName"/>
				</label>
				</div>
				<div class="small-12 medium-9 large-3 columns">
	                    <select id="select" name="typeSelect">
                        <option value="practice/CH">Practice/CH</option>
                        <option value="PatientName">Patient Name</option>
                       </select>
				</div>
				
				<div class="small-12 medium-3 large-3 columns">
				  <label>
				  <input type="Submit" value="Search">
				  </label>
				</div> -->
			</form>
			<!--  <p>&nbsp;</p> -->
			  <div class="practice-table">
			  <h1 class="doctor-staff">Draft</h1>
			 <c:set var="count" value="0" scope="page" />
		
       <c:if test="${sessionScope.param=='practiceDraft'}">
      <display:table name="referralInfoList" pagesize="10" uid="refInfo" requestURI="draft.do" cellpadding="0" cellspacing="0">
      <display:setProperty name="paging.banner.page.separator" value=""/>
      <c:set var="count" value="${count+1}" scope="page" />
      ${count}
      <display:column property="practiceName" title="Provider Name"/>
    
       <display:column property="pateintFirstName" title="Patient Name"/>
   
     
           <display:column property="proNotes" title="Message"/>
            <display:column property="creationDate" title="Creation Date"/>
            <display:column title="Action">
         <a  href="sendPatientToReferPatient.do?draftId=${refInfo.refId}">Refer</a>
		  </display:column>
       </display:table>  </c:if>  
  
       </div>
		
		
		</div><!--administrators-form-->
    </div><!--row-->
    <!--Main Site Ends Here-->
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="responsive/script.js"></script>
    <script src="js/vendor/jquery.js"></script>
    <script src="js/foundation.min.js"></script>
    <script>
      $(document).foundation();
    </script>
  </body>
    

</html>
