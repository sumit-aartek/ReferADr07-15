<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js" lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Refer A Doctor | Doctors</title>
	<!-- jQuery -->
<script src="js7_15/jquery.1.9.1.min.js" type="text/javascript"></script>
<link rel="stylesheet" href="css7_15/foundation.css" />
<link rel="stylesheet" href="css7_15/style.css" />
<link rel="stylesheet" href="css7_15/support.css" />
<link rel="stylesheet" href="http://fontawesome.io/assets/font-awesome/css/font-awesome.css">
<script src="js7_15/vendor/modernizr.js"></script>

</head>
<body>
	<div class="page-id-1 main-page" id="page-container">
		<div id="inner-page-container" class="max-row">
			<tiles:insertAttribute name="header"></tiles:insertAttribute>
			<!--MAIN CONTENT BEGINS HERE-->
			<div class="content-main-container">
				<div class="main-content-inner-container">
					<div class="row">

						<tiles:insertAttribute name="menu"></tiles:insertAttribute>

						<tiles:insertAttribute name="body"></tiles:insertAttribute>


					</div>
					<!--.row-->
				</div>
				<!--.main-content-inner-container-->
			</div>
			<!--.content-main-container-->
			<!--MAIN CONTENT ENDS HERE-->
		</div>
		<!--#inner-page-container-->
	</div>
	<!--#page-container-->


	<script src="js7_15/foundation.min.js"></script>
	<script>
		$(document).foundation();
	</script>

	<!--SCRIPT FOR SIDE MENU BEGINS-->
	<!-- prefix free to deal with vendor prefixes -->
	<script src="http://thecodeplayer.com/uploads/js/prefixfree-1.0.7.js"
		type="text/javascript" type="text/javascript"></script>


	<script>
		/*jQuery time*/
		$(document).ready(function() {
			$("#accordian h3").click(function() {
				//slide up all the link lists
				$("#accordian ul ul").slideUp();
				//slide down the link list below the h3 clicked - only if its closed
				if (!$(this).next().is(":visible")) {
					$(this).next().slideDown();
				}
			})
		})
	</script>
	<!--SCRIPT FOR SIDE MENU ENDS-->

</body>
</html>