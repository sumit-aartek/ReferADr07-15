<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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

<head>
</head>
<body>
<Center></Center>
<br>

<Center>
<font color="red"> ${errorMsg}</font>
<h2>Reset Your Password</h2>
</Center>
<form:form method="POST" action="UpdatePassword.do" modelAttribute="login">
<form:hidden path="id" id="loginid"/>
<Center>
<div id="errordiv">
 <p id="demo" style="color: red"></p>
</div>
<table>
<tr>
 <td><label >New password</label></td> 
 <td><input type="password" id="newPass"/></td>
 </tr>
<tr>
 <td><form:label path="password" >Confirm new password</form:label></td> 
 <td><form:password path="password" id="rePass"/></td>
 </tr>
<tr>
  <td colspan="2" align="center"><input type="submit" onclick="return checkpass()" value="Submit"/></td>  
 </tr>
 </table>
</Center> 
</form:form>
</body>
</html>