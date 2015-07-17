<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forgot Password</title>

<script type="text/javascript">

$(function() {
	
	jQuery(document).ready(function() {
		if(${successmsg}==0){
			$('#demo').append('<div></div>').html("Email Id Doesn't exist");
			var obj=document.getElementById("errordiv");
			obj.style.display="true"; 
			document.getElementById("userName").value='';
			
			}
		else{
			$('#demo1').append('<div></div>').html("Please Check your Mail");
			var obj=document.getElementById("successdiv");
			obj.style.display="true"; 
			document.getElementById("userName").value='';
			}
	});
});
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
							<h1 class="login">Forgot Password</h1>
							<p>Enter your email below and we'll send you a link to reset your password.</p>
								<div id="errordiv">
 									<p id="demo" style="color: red"></p>
								</div>
								<div id="successdiv">
 									<p id="demo1" style="color: green;"></p>
								</div>
							<form:form action="forgotpasswordSendMail.do" modelAttribute="login" method="POST" id="login-form">
								<form:input path="userName" type="text" value="${login.userName}" Placeholder="Email Address" class="login-email"/>
								<input name="login" type="submit" value="Reset Password" class="login-submit password-reset">
								<div class="clearfix"></div>
								<a href="login.do"><label>Login</label></a>
							</form:form><!--#login-form-->
						</div><!--.login-form-container-->
					</div><!--.login-page-->
                  </div><!-----#main-content-full------->
              </div><!-----#content right main----------->
</body>
</html>