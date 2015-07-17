<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reset Your Password</title>

<script type="text/javascript">

$(function() {
	
	jQuery(document).ready(function() {
		document.getElementById('loginid').value='${loginId}'
	});
});
</script>
<script type="text/javascript">
function checkpass(){

if(document.getElementById('newPass').value==document.getElementById('rePass').value){
return true;
}
$('#demo').append('<div></div>').html("Both Fields should be Same.");
var obj=document.getElementById("errordiv");
obj.style.display="true"; 

return false;
}


</script>


</head>
<body>
	     <div id="content-right-main" class="small-12 medium-12 large-4 large-offset-4 columns">
                  <div id="main-form-container" class="small-12 medium-12 large-12 columns">
    				<div class="login-page-logo">
						<img src="images/logo.png">
					</div><!--.login-page-logo-->
					<div class="login-page">
						<div class="login-form-container">
							<h1 class="login">Reset Password</h1>
							<div id="errordiv">
						<p id="demo" style="color: red"></p>
					</div>
							<form:form method="POST" action="UpdatePassword.do" modelAttribute="login" id="login-form">
						<form:hidden path="id" id="loginid" />
							<input type="password" id="newPass" Placeholder="New password"
							class="login-password">
						<form:password path="password" id="rePass" type="password"
							Placeholder="Confirm new password" class="login-password" />
								<input name="login" type="submit" onclick="return checkpass()" value="Reset Password" class="login-submit password-reset">
								<div class="clearfix"></div>
								<a href="login.html"><label>Login</label></a>
							</form:form><!--#login-form-->
						</div><!--.login-form-container-->
					</div><!--.login-page-->
                  </div><!-----#main-content-full------->
              </div><!-----#content right main----------->
</body>
</html>