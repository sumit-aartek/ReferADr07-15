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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Send Box</title>
</head>
<body>
               <div id="content-right-main" class="small-12 medium-12 large-10 columns">
                  <div id="main-content-full" class="small-12 medium-12 large-12 columns">

			        <h1 class="red-heading">Send Box</h1>
    
					<div class="refer-a-doc-new-copy-inbox-table">
					
					<c:set var="count" value="0" scope="page" />
					
					
					      
      <display:table name="referralInfoList" pagesize="8" uid="refInfo" requestURI="sendBox.do" cellpadding="0" cellspacing="0">
      <display:setProperty name="paging.banner.page.separator" value=""/>
      <c:set var="count" value="${count+1}" scope="page" />
      ${count}
     <display:column property="practiceName" title="Practice Name"/>
    
       <display:column  title="Patient Name">
       ${refInfo.pateintFirstName}&nbsp ${refInfo.pateintLastName}
       </display:column>
   
     
           <display:column property="proNotes" title="Message"/>
            <display:column property="creationDate" title="Creation Date"/>
         
       </display:table>  

					</div><!--.refer-a-doc-new-copy-inbox-table-->                  
                  </div><!-----#main-content-full------->
              </div><!-----#content right main----------->
</body>
</html>