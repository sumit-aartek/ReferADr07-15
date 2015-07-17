<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
	<div id="content-right-main"
		class="small-12 medium-12 large-4 large-offset-4 columns">
		<div id="main-form-container"
			class="small-12 medium-12 large-12 columns">

			<div class="login-page-logo">
				<img src="images/logo.png">
			</div>
			<!--.login-page-logo-->
			<div class="login-page">
				<div class="login-form-container">
					<h1 class="login">Login</h1>
					<Center>
						<font color="red"> ${errorMsg}</font>
					</Center>
					<form:form  method="POST" action="validateCredentials.do" modelAttribute="login" id="login-form" >
							<form:input path="userName" Placeholder="Email Address" class="login-email" value="${login.userName}"/>
							<form:password path="password" value="${login.password}" Placeholder="Password"	class="login-password" />
							<input name="login" type="submit" value="Login" class="login-submit">
						<div class="clearfix"></div>
						<p>
							By clicking Sign in, I agree to <a href="#">ReferADr's Terms,</a>
							including the payment terms, and <a href="#">Privacy Policy</a>
						</p>
						<a href="forgotpassword.do"><label>Forgot Password?</label></a>
					</form:form>
					<!--#login-form-->
				</div>
				<!--.login-form-container-->
			</div>
			<!--.login-page-->
		</div>
		<!-----#main-content-full------->
	</div>
	<!-----#content right main----------->
</body>
</html>