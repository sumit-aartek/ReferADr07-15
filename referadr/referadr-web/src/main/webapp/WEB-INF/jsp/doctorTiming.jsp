<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html class="no-js" lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Connecting Doctors</title>
<script src="js/jquery.1.9.1.min.js"></script>
<script src="js/vendor/jquery.ui.widget.js"></script>

<link rel="stylesheet" href="doctor-css/foundation.css" />
<link rel="stylesheet" href="css/style.css" />
<script src="doctor-js/vendor/modernizr.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="responsive/style.css">
<link rel="stylesheet" type="text/css" href="responsive/responsive.css">
	<link href="bootstrap/Untitled-1.css" rel="stylesheet">
	
	<link rel="stylesheet" href="css/default.time.css" />

	
<!--Accordion CSS-->
		<link rel="stylesheet" type="text/css" href="accordian/accordian-style.css">
	<!--Accordion CSS Ends-->
	<script type="text/javascript" src="jquery-1.3.2.min.js"></script>

	<script type="text/javascript" src="js/jsp-js/script.js"></script>
	
	<!--===========================start script=======================================  -->

	<script>
	var counter1=0;
	var counter2=0;
	var counter3=0;
	var counter4=0;
	var counter5=0;
	var counter6=0;
	var counter7=0;
	$(function() {
		
		jQuery(document).ready(function() {
			var providerID="${providerID}";
			//alert(providerID);
			document.getElementById('providerId').value=providerID;
			var size ="${doctorTimingVOs.size()}" ;
			var doctorTimingVOs ="${doctorTimingVOs}" ;
			
			var day ="${doctorTimingVOs[0]}" ;



		 	<c:forEach items="${DayOffdoctorTimingVOs}" var="daylist" >   
		 	//alert("adasdasdasds=>${daylist.checkBox}");
			 if(${daylist.checkBox}){
				document.getElementById('checkbox${daylist.dayID}').checked=true;
				}
			else{
				document.getElementById('checkbox${daylist.dayID}').checked=false;
				} 
			</c:forEach>
			 
			<c:forEach items="${doctorTimingVOs}" var="list" >        
			//alert("${list.dayID}");

			
			if('${list.dayID}'==1){
				counter1++;
				var newTextBoxDiv = $(document.createElement('div'))
				.attr("id", 'aa' +${list.dayID}+counter1 );
			newTextBoxDiv.after().html('<fieldset><input type="text" class="picker__input picker__input--active" name="doctorTimingVO[${list.dayID}].startTime" value="${list.startTime}" id="input_from"></fieldset>');
			newTextBoxDiv.appendTo("#startdiv${list.dayID}");

			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'bb' +${list.dayID}+counter1 );
		newTextBoxDiv.after().html('<fieldset><input type="text" class="picker__input picker__input--active" name="doctorTimingVO[${list.dayID}].endTime" value="${list.endTime}" id="input_to"></fieldset>');
		newTextBoxDiv.appendTo("#enddiv${list.dayID}");
//alert('moncounter='+counter1)
				}
			if('${list.dayID}'==2){
				counter2++;
				var newTextBoxDiv = $(document.createElement('div'))
				.attr("id", 'aa' +${list.dayID}+counter2 );
			newTextBoxDiv.after().html('<fieldset><input type="text" class="picker__input picker__input--active" name="doctorTimingVO[${list.dayID}].startTime" value="${list.startTime}" id="input_from"></fieldset>');
			newTextBoxDiv.appendTo("#startdiv${list.dayID}");

			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'bb' +${list.dayID}+counter2 );
		newTextBoxDiv.after().html('<fieldset><input type="text" class="picker__input picker__input--active" name="doctorTimingVO[${list.dayID}].endTime" value="${list.endTime}" id="input_to"></fieldset>');
		newTextBoxDiv.appendTo("#enddiv${list.dayID}");
//alert('tuecounter='+counter2)
				}
			if('${list.dayID}'==3){
				counter3++;
				var newTextBoxDiv = $(document.createElement('div'))
				.attr("id", 'aa' +${list.dayID}+counter3 );
			newTextBoxDiv.after().html('<fieldset><input type="text" class="picker__input picker__input--active" name="doctorTimingVO[${list.dayID}].startTime" value="${list.startTime}" id="input_from"></fieldset>');
			newTextBoxDiv.appendTo("#startdiv${list.dayID}");

			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'bb' +${list.dayID}+counter3 );
		newTextBoxDiv.after().html('<fieldset><input type="text" class="picker__input picker__input--active" name="doctorTimingVO[${list.dayID}].endTime" value="${list.endTime}" id="input_to"></fieldset>');
		newTextBoxDiv.appendTo("#enddiv${list.dayID}");
//alert('wedcounter='+counter3)
				}
			if('${list.dayID}'==4){
				counter4++;
				var newTextBoxDiv = $(document.createElement('div'))
				.attr("id", 'aa' +${list.dayID}+counter4 );
			newTextBoxDiv.after().html('<fieldset><input type="text" class="picker__input picker__input--active" name="doctorTimingVO[${list.dayID}].startTime" value="${list.startTime}" id="input_from"></fieldset>');
			newTextBoxDiv.appendTo("#startdiv${list.dayID}");

			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'bb' +${list.dayID}+counter4 );
		newTextBoxDiv.after().html('<fieldset><input type="text" class="picker__input picker__input--active" name="doctorTimingVO[${list.dayID}].endTime" value="${list.endTime}" id="input_to"></fieldset>');
		newTextBoxDiv.appendTo("#enddiv${list.dayID}");
//alert('thucounter='+counter4)
				}
			if('${list.dayID}'==5){
				counter5++;
				var newTextBoxDiv = $(document.createElement('div'))
				.attr("id", 'aa' +${list.dayID}+counter5 );
			newTextBoxDiv.after().html('<fieldset><input type="text" class="picker__input picker__input--active" name="doctorTimingVO[${list.dayID}].startTime" value="${list.startTime}" id="input_from"></fieldset>');
			newTextBoxDiv.appendTo("#startdiv${list.dayID}");

			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'bb' +${list.dayID}+counter5 );
		newTextBoxDiv.after().html('<fieldset><input type="text" class="picker__input picker__input--active" name="doctorTimingVO[${list.dayID}].endTime" value="${list.endTime}" id="input_to"></fieldset>');
		newTextBoxDiv.appendTo("#enddiv${list.dayID}");
//alert('fricounter='+counter5)
				}
			if('${list.dayID}'==6){
				counter6++;
				var newTextBoxDiv = $(document.createElement('div'))
				.attr("id", 'aa' +${list.dayID}+counter6 );
			newTextBoxDiv.after().html('<fieldset><input type="text" class="picker__input picker__input--active" name="doctorTimingVO[${list.dayID}].startTime" value="${list.startTime}" id="input_from"></fieldset>');
			newTextBoxDiv.appendTo("#startdiv${list.dayID}");

			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'bb' +${list.dayID}+counter6 );
		newTextBoxDiv.after().html('<fieldset><input type="text" class="picker__input picker__input--active" name="doctorTimingVO[${list.dayID}].endTime" value="${list.endTime}" id="input_to"></fieldset>');
		newTextBoxDiv.appendTo("#enddiv${list.dayID}");
//alert('satcounter='+counter6)
				}
			if('${list.dayID}'==7){
				counter7++;
				var newTextBoxDiv = $(document.createElement('div'))
				.attr("id", 'aa' +${list.dayID}+counter7 );
			newTextBoxDiv.after().html('<fieldset><input type="text" class="picker__input picker__input--active" name="doctorTimingVO[${list.dayID}].startTime" value="${list.startTime}" id="input_from"></fieldset>');
			newTextBoxDiv.appendTo("#startdiv${list.dayID}");

			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'bb' +${list.dayID}+counter7 );
		newTextBoxDiv.after().html('<fieldset><input type="text" class="picker__input picker__input--active" name="doctorTimingVO[${list.dayID}].endTime" value="${list.endTime}" id="input_to"></fieldset>');
		newTextBoxDiv.appendTo("#enddiv${list.dayID}");
//alert('suncounter='+counter7)
				}

			
		
			
			</c:forEach>

if(size==0){
	counter1=1;
	counter2=1;
	counter3=1;
	counter4=1;
	counter5=1;
	counter6=1;
	counter7=1;
	for(var j=1;j<=7;j++){

		document.getElementById('checkbox'+j).checked=false;
		
var newTextBoxDiv = $(document.createElement('div'))
.attr("id", 'aa' +j+'1');
newTextBoxDiv.after().html('<fieldset><input type="text"  class="picker__input picker__input--active" name="doctorTimingVO['+j+'].startTime" id="input_from"></fieldset>');
newTextBoxDiv.appendTo("#startdiv"+j);

var newTextBoxDiv = $(document.createElement('div'))
.attr("id", 'bb' +j+'1' );
newTextBoxDiv.after().html('<fieldset><input type="text"  class="picker__input picker__input--active" name="doctorTimingVO['+j+'].endTime"  id="input_to"></fieldset>');
newTextBoxDiv.appendTo("#enddiv"+j);
		}
}
			
			

			
			//var doctorTimingVOs="${doctorTimingVOs[0].dayID}";
//alert(doctorTimingVOs);




			
		});
	});
	</script>
	<script type="text/javascript">
function closecheckboxclicked(dayid){

		//alert('checkbox'+dayid+'=>'+document.getElementById('checkbox'+dayid).checked );
		var status=document.getElementById('checkbox'+dayid).checked;
		//alert(status);
		if(status){



			
			if(dayid==1){
				for(counter1;counter1>0;counter1--){

					 $("#aa" + dayid+counter1).remove();
					 $("#bb" + dayid+counter1).remove();
					} 
			}

			if(dayid==2){
				for(counter2;counter2>0;counter2--){

					 $("#aa" + dayid+counter2).remove();
					 $("#bb" + dayid+counter2).remove();
					} 
			}

			if(dayid==3){
				for(counter3;counter3>0;counter3--){

					 $("#aa" + dayid+counter3).remove();
					 $("#bb" + dayid+counter3).remove();
					} 
			}
			
			if(dayid==4){
				for(counter4;counter4>0;counter4--){

					 $("#aa" + dayid+counter4).remove();
					 $("#bb" + dayid+counter4).remove();
					} 
			}
			if(dayid==5){
				for(counter5;counter5>0;counter5--){

					 $("#aa" + dayid+counter5).remove();
					 $("#bb" + dayid+counter5).remove();
					} 
			}
			if(dayid==6){
				for(counter6;counter6>0;counter6--){

					 $("#aa" + dayid+counter6).remove();
					 $("#bb" + dayid+counter6).remove();
					} 
			}
			if(dayid==7){
				for(counter7;counter7>0;counter7--){

					 $("#aa" + dayid+counter7).remove();
					 $("#bb" + dayid+counter7).remove();
					} 
			}
			
			}
		else{
			if(dayid==1){
			counter1++;
			}
			if(dayid==2){
				counter2++;
				}
			if(dayid==3){
				counter3++;
				}
			if(dayid==4){
				counter4++;
				}

			if(dayid==5){
				counter5++;
				}
			if(dayid==6){
				counter6++;
				}
			if(dayid==7){
				counter7++;
				}
			//alert('counter1=>'+counter1);
			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'aa' +dayid+'1' );
			newTextBoxDiv.after().html('<fieldset><input type="text"  class="picker__input picker__input--active" name="doctorTimingVO['+dayid+'].startTime" id="input_from"></fieldset>');
			newTextBoxDiv.appendTo("#startdiv"+dayid);

			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'bb' +dayid+'1' );
			newTextBoxDiv.after().html('<fieldset><input type="text"  class="picker__input picker__input--active" name="doctorTimingVO['+dayid+'].endTime"  id="input_to"></fieldset>');
			newTextBoxDiv.appendTo("#enddiv"+dayid);
			
			}
}

	</script>
	
	
	<script type="text/javascript">
function removeclicked(dayid){

	if(dayid==1){
		 $("#aa" + dayid+counter1).remove();
		 $("#bb" + dayid+counter1).remove();
				counter1--;
				if(counter1==0){
					document.getElementById('checkbox'+dayid).checked=true;
					}
	}
	if(dayid==2){
		 $("#aa" + dayid+counter2).remove();
		 $("#bb" + dayid+counter2).remove();
		counter2--;
		if(counter2==0){
			document.getElementById('checkbox'+dayid).checked=true;
			}
}
	if(dayid==3){
		 $("#aa" + dayid+counter3).remove();
		 $("#bb" + dayid+counter3).remove();
		counter3--;
		if(counter3==0){
			document.getElementById('checkbox'+dayid).checked=true;
			}
}
	if(dayid==4){
		 $("#aa" + dayid+counter4).remove();
		 $("#bb" + dayid+counter4).remove();
		counter4--;
		if(counter4==0){
			document.getElementById('checkbox'+dayid).checked=true;
			}
}
	if(dayid==5){
		 $("#aa" + dayid+counter5).remove();
		 $("#bb" + dayid+counter5).remove();
		counter5--;
		if(counter5==0){
			document.getElementById('checkbox'+dayid).checked=true;
			}
}
	if(dayid==6){
		 $("#aa" + dayid+counter6).remove();
		 $("#bb" + dayid+counter6).remove();
		counter6--;
		if(counter6==0){
			document.getElementById('checkbox'+dayid).checked=true;
			}
}
	if(dayid==7){
		 $("#aa" + dayid+counter7).remove();
		 $("#bb" + dayid+counter7).remove();
		counter7--;
		if(counter7==0){
			document.getElementById('checkbox'+dayid).checked=true;
			}
}
	
			
	
	
	
}
	</script>
	
	
	
		<script type="text/javascript">
function addclicked(dayid){

	var status=document.getElementById('checkbox'+dayid).checked;
if(status){
	return false;
}
	
	if(dayid==1){
		counter1++;
			var newTextBoxDiv = $(document.createElement('div'))
		.attr("id", 'aa' +dayid+counter1 );
		newTextBoxDiv.after().html('<fieldset><input type="text"  class="picker__input picker__input--active" name="doctorTimingVO['+dayid+'].startTime" id="input_from"></fieldset>');
		newTextBoxDiv.appendTo("#startdiv"+dayid);

		var newTextBoxDiv = $(document.createElement('div'))
		.attr("id", 'bb' +dayid+counter1 );
		newTextBoxDiv.after().html('<fieldset><input type="text"  class="picker__input picker__input--active" name="doctorTimingVO['+dayid+'].endTime"  id="input_to"></fieldset>');
		newTextBoxDiv.appendTo("#enddiv"+dayid);
		}
		if(dayid==2){
			counter2++;
			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'aa' +dayid+counter2 );
			newTextBoxDiv.after().html('<fieldset><input type="text"  class="picker__input picker__input--active" name="doctorTimingVO['+dayid+'].startTime" id="input_from"></fieldset>');
			newTextBoxDiv.appendTo("#startdiv"+dayid);

			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'bb' +dayid+counter2 );
			newTextBoxDiv.after().html('<fieldset><input type="text"  class="picker__input picker__input--active" name="doctorTimingVO['+dayid+'].endTime"  id="input_to"></fieldset>');
			newTextBoxDiv.appendTo("#enddiv"+dayid);
			}
		if(dayid==3){
			counter3++;
			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'aa' +dayid+counter3 );
			newTextBoxDiv.after().html('<fieldset><input type="text"  class="picker__input picker__input--active" name="doctorTimingVO['+dayid+'].startTime" id="input_from"></fieldset>');
			newTextBoxDiv.appendTo("#startdiv"+dayid);

			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'bb' +dayid+counter3 );
			newTextBoxDiv.after().html('<fieldset><input type="text"  class="picker__input picker__input--active" name="doctorTimingVO['+dayid+'].endTime"  id="input_to"></fieldset>');
			newTextBoxDiv.appendTo("#enddiv"+dayid);
			}
		if(dayid==4){
			counter4++;
			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'aa' +dayid+counter4 );
			newTextBoxDiv.after().html('<fieldset><input type="text"  class="picker__input picker__input--active" name="doctorTimingVO['+dayid+'].startTime" id="input_from"></fieldset>');
			newTextBoxDiv.appendTo("#startdiv"+dayid);

			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'bb' +dayid+counter4 );
			newTextBoxDiv.after().html('<fieldset><input type="text"  class="picker__input picker__input--active" name="doctorTimingVO['+dayid+'].endTime"  id="input_to"></fieldset>');
			newTextBoxDiv.appendTo("#enddiv"+dayid);
			}

		if(dayid==5){
			counter5++;
			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'aa' +dayid+counter5 );
			newTextBoxDiv.after().html('<fieldset><input type="text"  class="picker__input picker__input--active" name="doctorTimingVO['+dayid+'].startTime" id="input_from"></fieldset>');
			newTextBoxDiv.appendTo("#startdiv"+dayid);

			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'bb' +dayid+counter5 );
			newTextBoxDiv.after().html('<fieldset><input type="text"  class="picker__input picker__input--active" name="doctorTimingVO['+dayid+'].endTime"  id="input_to"></fieldset>');
			newTextBoxDiv.appendTo("#enddiv"+dayid);
			}
		if(dayid==6){
			counter6++;
			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'aa' +dayid+counter6 );
			newTextBoxDiv.after().html('<fieldset><input type="text"  class="picker__input picker__input--active" name="doctorTimingVO['+dayid+'].startTime" id="input_from"></fieldset>');
			newTextBoxDiv.appendTo("#startdiv"+dayid);

			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'bb' +dayid+counter6 );
			newTextBoxDiv.after().html('<fieldset><input type="text"  class="picker__input picker__input--active" name="doctorTimingVO['+dayid+'].endTime"  id="input_to"></fieldset>');
			newTextBoxDiv.appendTo("#enddiv"+dayid);
			}
		if(dayid==7){
			counter7++;
			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'aa' +dayid+counter7 );
			newTextBoxDiv.after().html('<fieldset><input type="text"  class="picker__input picker__input--active" name="doctorTimingVO['+dayid+'].startTime" id="input_from"></fieldset>');
			newTextBoxDiv.appendTo("#startdiv"+dayid);

			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'bb' +dayid+counter7 );
			newTextBoxDiv.after().html('<fieldset><input type="text"  class="picker__input picker__input--active" name="doctorTimingVO['+dayid+'].endTime"  id="input_to"></fieldset>');
			newTextBoxDiv.appendTo("#enddiv"+dayid);
			}
		
	
	return true;	
	
}
	</script>
</head>
<body>
<form:form id="formId" action="SaveDoctorTiming.do" method="POST" modelAttribute="DoctorTimingVO" autocomplete="off">
<form:hidden path="providerID" id="providerId"/>
<div id="content-right-main" class="small-12 medium-12 large-12 columns">
<h1>Hours</h1>
              <hr>
             <c:forEach var="dayID" begin="1" end="7">
  

              
          <div class="omer-time">
              <div class="small-12 medium-12 large-2 columns">
                  <c:if test="${dayID == 1}">
                  <h3>Monday</h3>
                  </c:if>
                  
                   <c:if test="${dayID == 2}">
                  <h3>Tuesday</h3>
                  </c:if>
                  
                   <c:if test="${dayID == 3}">
                  <h3>Wednesday</h3>
                  </c:if>
                  
                   <c:if test="${dayID == 4}">
                  <h3>Thursday</h3>
                  </c:if>
                  
                   <c:if test="${dayID == 5}">
                  <h3>Friday</h3>
                  </c:if>
                  
                   <c:if test="${dayID == 6}">
                  <h3>Saturday</h3>
                  </c:if>
                  
                   <c:if test="${dayID == 7}">
                  <h3>Sunday</h3>
                  </c:if>
                  
                  
                  
              </div>
            
              <div class="small-12 medium-12 large-3 columns" id="startdiv${dayID}">
                 From:
                 <%--   <div id=aa${dayID}>
					<fieldset>
						  <form:input path="doctorTimingVO[${dayID}].startTime"  id="input_from" />
					</fieldset>
					</div>  --%> 
              </div>
              
              <div class="small-12 medium-12 large-3 columns" id="enddiv${dayID}">
				To:
				 <%--  <div id=bb${dayID}>
				<fieldset>
 					 <form:input path="doctorTimingVO[${dayID}].endTime"  id="input_to" />
				</fieldset>
				</div>  --%> 
              </div>
              
              <div class="small-12 medium-12 large-2 columns" id="href${dayID}">
                  <a href="#" onclick="removeclicked('${dayID}')" id="remove${dayID}">Remove</a><br>
                  <a href="#" onclick="return addclicked('${dayID}')" id="addmore${dayID}">Add More</a>
              </div>
              
              <div class="small-12 medium-12 large-2 columns">
                  <form:checkbox path="doctorTimingVO[${dayID}].checkBox" id="checkbox${dayID}" onclick="closecheckboxclicked('${dayID}')" class="checkboxclose" /> 
                  <label for="checkbox1">Is Day Off</label>
              </div>
          </div><!---omer-time----->
         <hr>
          </c:forEach>
          <input type="submit" value="save" name="submit" class="small next"/>	
	  </div><!--#content-right-main-->

</form:form>

</body >

 <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.2.min.js"></script>
<script src="js/page-js/jquery.js"></script>
<script src="js/foundation.min.js"></script>
 <script src="js/time/picker.time.js"></script>
<script src="bootstrap/jq.js"></script>
  <script src="bootstrap/bs.js"></script>


  <script>
      $(document).foundation();
    </script>
</html>