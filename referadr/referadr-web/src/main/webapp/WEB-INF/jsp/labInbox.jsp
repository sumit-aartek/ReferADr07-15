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

    	 <script type="text/javascript">
window.onload = function () {
	
	
/* 	var headersearch = document.getElementById("headersearch");
	headersearch.style.display = "block"; */
	
  $(".pagebanner").hide();
  $(".strong").hide();
  var pagelinkValue = $('.pagelinks').map(function(){
  return $(this).text();
  }).get();
  if(pagelinkValue==1)
  $(".pagebanner").hide();
  var schedulefull = document.getElementById("all");
	schedulefull.style.display = "none";
  };
</script>
<body>



<div id="content-right-main" class="small-12 medium-12 large-10 columns">
                  <div id="main-content-full" class="small-12 medium-12 large-12 columns">
  <h1 class="red-heading">Lab Inbox</h1>

  <div class="refer-doctor">

     <display:table name="patientReferralList" pagesize="8" uid="refInfo" requestURI="labInbox.do" cellpadding="0" cellspacing="0">
     <display:setProperty name="paging.banner.page.separator" value=""/>
      <c:set var="count" value="${count+1}" scope="page" />
      ${count}
      <display:column property="practiceInfo.practiceName" title="Practice Name"/>
    
       <display:column  title="Pateint Name">
      <%-- <a href="patientNameView.do?patientRefInfo=${refInfo.refId}">${refInfo.pateintFirstName}</a>  --%>
      <a href="showSelectLabServices.do?labReffId=${refInfo.labReffId}">${refInfo.patientInfo.patientFirstName}&nbsp${refInfo.patientInfo.patientLastName}</a>
        <%-- <a href="#">${refInfo.patientInfo.patientFirstName}</a>  --%>
       </display:column>
         <display:column property="reffCreationDate" title="Date"/>
           <display:column property="labNotes" title="Message"/>
            <display:column title="Action">
            
Action
        
             </display:column>
          
       </display:table> 


<%-- <form:form action="showSelectLabServices.do" method="POST" modelAttribute="PatientReferralInfo" autocomplete="off">
    <div id="drops" class="small-12 medium-6 large-6 columns">
   <form:input path="labReffId"  value="13"/>
   </div>
   <div id="drops" class="small-12 medium-6 large-6 columns">
   <input type="submit" value="submit"/>
   </div>
</form:form> --%>



</div>
</div>
</div>


</body>
</html>