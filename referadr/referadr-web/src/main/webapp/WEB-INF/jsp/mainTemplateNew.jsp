<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Connecting Doctors</title>
    <link rel="stylesheet" href="cssNEW/foundation.css" />
    <link rel="stylesheet" href="cssNEW/style.css" />
    <script src="jsNEW/modernizr.js"></script>
	
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="responsiveNEW/style.css">
	<link rel="stylesheet" type="text/css" href="responsiveNEW/responsive.css">
	<link href="cssNEW/support.css" rel="stylesheet">
	<link rel="stylesheet" href="http://fontawesome.io/assets/font-awesome/css/font-awesome.css">
	
	<!--Scripts and CSS for multiselect Dropdown only-->


    	<!--Scripts and CSS for multiselect Dropdown only-->
</head>
<body>
<div id="page-container" class="page">  
            <tiles:insertAttribute name="header"></tiles:insertAttribute>
         <div class="row">
        <div id="site-content-area">
    
            <tiles:insertAttribute name="menu"></tiles:insertAttribute>
            
            <tiles:insertAttribute name="body"></tiles:insertAttribute>
            
         
            </div>
            </div>
          </div>
          

	<script type="text/javascript" src="responsiveNEW/script.js"></script>




  <script src="jsNEW/bs.js"></script>

    
    <script src="jsNEW/foundation.min.js"></script>
    <script>
      $(document).foundation();
    </script> 
      
      	<script type="text/javascript">

function getCurrentLinkFrom(links){

    var curPage = document.URL;
    curPage = curPage.substr(curPage.lastIndexOf("/")) ;

    links.each(function(){
        var linkPage = $(this).attr("href");
        linkPage = linkPage.substr(linkPage.lastIndexOf("/"));
        if (curPage == linkPage){
            return $(this);
        }
    });
};

 /* $(document).ready(function(){
    var currentLink = getCurrentLinkFrom($("navbar a"));
    currentLink.addClass("current_link") ;
}); */ 
</script>
<script>
$(document).on('click', '.browse', function(){
  var file = $(this).parent().parent().parent().find('.file');
  file.trigger('click');
});
$(document).on('change', '.file', function(){
  $(this).parent().find('.form-control').val($(this).val().replace(/C:\\fakepath\\/i, ''));
});
</script>
      
          
</body>
</html>