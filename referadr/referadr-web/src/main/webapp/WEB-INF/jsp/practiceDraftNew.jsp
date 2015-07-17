<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Draft</title>

</head>
<body>
 <a id='callpractice'  href="practice.do"></a>
        
               <div id="content-right-main" class="small-12 medium-12 large-10 columns">
                  <div id="main-content-full" class="small-12 medium-12 large-12 columns">

			        <h1 class="red-heading">Draft</h1>
    
					<div class="refer-a-doc-new-copy-inbox-table">
					
					<c:set var="count" value="0" scope="page" />
					
					
					       <c:if test="${sessionScope.param=='practiceDraft'}">
      <display:table name="referralInfoList" pagesize="4" uid="refInfo" requestURI="draft.do" cellpadding="0" cellspacing="0">
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

					</div><!--.refer-a-doc-new-copy-inbox-table-->                  
                  </div><!-----#main-content-full------->
              </div><!-----#content right main----------->
      
    

</body>
</html>