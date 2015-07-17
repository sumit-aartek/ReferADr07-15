<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
<Center></Center>
<br>

<Center>
<font color="red"> ${errorMsg}</font>
<h2>Forgot Password</h2>
</Center>
<form:form method="POST" action="forgotpasswordSendMail.do" modelAttribute="login">
<Center>
<div id="errordiv">
 <p id="demo" style="color: red"></p>
</div>
<div id="successdiv">
 <p id="demo1" style="color: green;"></p>
</div>
<table>
<tr>
 <td><form:label path="userName">User Name</form:label></td> 
 <td><form:input path="userName" value="${login.userName}"/></td>
 </tr>

<tr>
  <td colspan="2" align="center"><input type="submit" value="Submit"/></td>  
 </tr>
 </table>
</Center> 
</form:form>
</body>
</html>