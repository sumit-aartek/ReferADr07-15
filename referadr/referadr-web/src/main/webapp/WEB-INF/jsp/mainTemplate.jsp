<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
            <tiles:insertAttribute name="header"></tiles:insertAttribute>
            <div class="row">
    
            <tiles:insertAttribute name="menu"></tiles:insertAttribute>
            <div id="content-right-main" class="small-12 medium-12 large-9 columns">
            <tiles:insertAttribute name="body"></tiles:insertAttribute>
            </div>
            </div>
        
</body>
</html>