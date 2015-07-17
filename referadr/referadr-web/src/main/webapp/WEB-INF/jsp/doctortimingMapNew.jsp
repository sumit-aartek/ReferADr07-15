<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Connecting Doctors</title>


<link rel="stylesheet" type="text/css"
	href="css/jquery.datetimepicker.css" />

<link rel="stylesheet" href="cssNEW/foundation.css" />
<link rel="stylesheet" href="cssNEW/style.css" />

<!-- <script src="js/jqueryNew.js"></script> -->

<script src="js/vendor/jquery.ui.widget.js"></script>
<script src="js/jquery.1.9.1.min.js"></script>
<!-- <script src="js/page-js/jquery.fileupload.js"></script>
 <script src="js/page-js/myuploadfunction.js"></script>
<script src="js/page-js/refer_myuploadfunction.js"></script>  -->
<script type="text/javascript" src="js/jsp-js/referPatientMain.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="jQuery/css/validationEngine.jquery.css"
	type="text/css" />
<link rel="stylesheet" type="text/css"
	href="css/jquery.datetimepicker.css" />
<link rel="stylesheet" type="text/css" href="responsiveNEW/style.css">
<link rel="stylesheet" type="text/css"
	href="responsiveNEW/responsive.css">
<!-- <script src="js/vendor/modernizr.js"></script> -->
<script src="js/page-js/nextPrevious.js"></script>


<script src="jQuery/js/languages/jquery.validationEngine-en.js"
	type="text/javascript" charset="utf-8"></script>
<script src="jQuery/js/jquery.validationEngine.js"
	type="text/javascript" charset="utf-8"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>





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
			newTextBoxDiv.after().html('<input type="text"  class="abc" required="true" name="doctorTimingVO[${list.dayID}].startTime" value="${list.startTime}"  >');
			newTextBoxDiv.appendTo("#startdiv${list.dayID}");

			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'bb' +${list.dayID}+counter1 );
		newTextBoxDiv.after().html(' <input type="text"  class="abc" required="true" name="doctorTimingVO[${list.dayID}].endTime" value="${list.endTime}"  > ');
		newTextBoxDiv.appendTo("#enddiv${list.dayID}");
//alert('moncounter='+counter1)
				}
			if('${list.dayID}'==2){
				counter2++;
				var newTextBoxDiv = $(document.createElement('div'))
				.attr("id", 'aa' +${list.dayID}+counter2 );
			newTextBoxDiv.after().html(' <input type="text"  class="abc" required="true" name="doctorTimingVO[${list.dayID}].startTime" value="${list.startTime}"  > ');
			newTextBoxDiv.appendTo("#startdiv${list.dayID}");

			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'bb' +${list.dayID}+counter2 );
		newTextBoxDiv.after().html(' <input type="text"  class="abc" required="true" name="doctorTimingVO[${list.dayID}].endTime" value="${list.endTime}"  > ');
		newTextBoxDiv.appendTo("#enddiv${list.dayID}");
//alert('tuecounter='+counter2)
				}
			if('${list.dayID}'==3){
				counter3++;
				var newTextBoxDiv = $(document.createElement('div'))
				.attr("id", 'aa' +${list.dayID}+counter3 );
			newTextBoxDiv.after().html(' <input type="text"  class="abc" required="true" name="doctorTimingVO[${list.dayID}].startTime" value="${list.startTime}"  > ');
			newTextBoxDiv.appendTo("#startdiv${list.dayID}");

			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'bb' +${list.dayID}+counter3 );
		newTextBoxDiv.after().html(' <input type="text"  class="abc" required="true" name="doctorTimingVO[${list.dayID}].endTime" value="${list.endTime}"  > ');
		newTextBoxDiv.appendTo("#enddiv${list.dayID}");
//alert('wedcounter='+counter3)
				}
			if('${list.dayID}'==4){
				counter4++;
				var newTextBoxDiv = $(document.createElement('div'))
				.attr("id", 'aa' +${list.dayID}+counter4 );
			newTextBoxDiv.after().html(' <input type="text"  class="abc" required="true" name="doctorTimingVO[${list.dayID}].startTime" value="${list.startTime}"  > ');
			newTextBoxDiv.appendTo("#startdiv${list.dayID}");

			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'bb' +${list.dayID}+counter4 );
		newTextBoxDiv.after().html(' <input type="text"  class="abc" required="true" name="doctorTimingVO[${list.dayID}].endTime" value="${list.endTime}"  > ');
		newTextBoxDiv.appendTo("#enddiv${list.dayID}");
//alert('thucounter='+counter4)
				}
			if('${list.dayID}'==5){
				counter5++;
				var newTextBoxDiv = $(document.createElement('div'))
				.attr("id", 'aa' +${list.dayID}+counter5 );
			newTextBoxDiv.after().html(' <input type="text"  class="abc" required="true" name="doctorTimingVO[${list.dayID}].startTime" value="${list.startTime}"  > ');
			newTextBoxDiv.appendTo("#startdiv${list.dayID}");

			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'bb' +${list.dayID}+counter5 );
		newTextBoxDiv.after().html(' <input type="text"  class="abc" required="true" name="doctorTimingVO[${list.dayID}].endTime" value="${list.endTime}"  > ');
		newTextBoxDiv.appendTo("#enddiv${list.dayID}");
//alert('fricounter='+counter5)
				}
			if('${list.dayID}'==6){
				counter6++;
				var newTextBoxDiv = $(document.createElement('div'))
				.attr("id", 'aa' +${list.dayID}+counter6 );
			newTextBoxDiv.after().html(' <input type="text"  class="abc" required="true" name="doctorTimingVO[${list.dayID}].startTime" value="${list.startTime}"  > ');
			newTextBoxDiv.appendTo("#startdiv${list.dayID}");

			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'bb' +${list.dayID}+counter6 );
		newTextBoxDiv.after().html(' <input type="text"  class="abc" required="true" name="doctorTimingVO[${list.dayID}].endTime" value="${list.endTime}"  > ');
		newTextBoxDiv.appendTo("#enddiv${list.dayID}");
//alert('satcounter='+counter6)
				}
			if('${list.dayID}'==7){
				counter7++;
				var newTextBoxDiv = $(document.createElement('div'))
				.attr("id", 'aa' +${list.dayID}+counter7 );
			newTextBoxDiv.after().html(' <input type="text"  class="abc" required="true" name="doctorTimingVO[${list.dayID}].startTime" value="${list.startTime}"  > ');
			newTextBoxDiv.appendTo("#startdiv${list.dayID}");

			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'bb' +${list.dayID}+counter7 );
		newTextBoxDiv.after().html(' <input type="text"  class="abc" required="true" name="doctorTimingVO[${list.dayID}].endTime" value="${list.endTime}"  > ');
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
newTextBoxDiv.after().html(' <input type="text"   class="abc" required="true" name="doctorTimingVO['+j+'].startTime"  > ');
newTextBoxDiv.appendTo("#startdiv"+j);

var newTextBoxDiv = $(document.createElement('div'))
.attr("id", 'bb' +j+'1' );
newTextBoxDiv.after().html(' <input type="text"   class="abc" required="true" name="doctorTimingVO['+j+'].endTime"   > ');
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
			counter1=1;
			}
			if(dayid==2){
				counter2=1;
				}
			if(dayid==3){
				counter3=1;
				}
			if(dayid==4){
				counter4=1;
				}

			if(dayid==5){
				counter5=1;
				}
			if(dayid==6){
				counter6=1;
				}
			if(dayid==7){
				counter7=1;
				}
			//alert('counter1=>'+counter1);
			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'aa' +dayid+'1' );
			newTextBoxDiv.after().html(' <input type="text"   class="abc" required="true" name="doctorTimingVO['+dayid+'].startTime"  > ');
			newTextBoxDiv.appendTo("#startdiv"+dayid);

			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'bb' +dayid+'1' );
			newTextBoxDiv.after().html(' <input type="text"   class="abc" required="true" name="doctorTimingVO['+dayid+'].endTime"   > ');
			newTextBoxDiv.appendTo("#enddiv"+dayid);
			dynamicDate();
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
		newTextBoxDiv.after().html(' <input type="text"   class="abc" required="true" name="doctorTimingVO['+dayid+'].startTime"  > ');
		newTextBoxDiv.appendTo("#startdiv"+dayid);

		var newTextBoxDiv = $(document.createElement('div'))
		.attr("id", 'bb' +dayid+counter1 );
		newTextBoxDiv.after().html(' <input type="text"   class="abc" required="true" name="doctorTimingVO['+dayid+'].endTime"   > ');
		newTextBoxDiv.appendTo("#enddiv"+dayid);
		}
		if(dayid==2){
			counter2++;
			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'aa' +dayid+counter2 );
			newTextBoxDiv.after().html(' <input type="text"   class="abc" required="true" name="doctorTimingVO['+dayid+'].startTime"  > ');
			newTextBoxDiv.appendTo("#startdiv"+dayid);

			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'bb' +dayid+counter2 );
			newTextBoxDiv.after().html(' <input type="text"   class="abc" required="true" name="doctorTimingVO['+dayid+'].endTime"   > ');
			newTextBoxDiv.appendTo("#enddiv"+dayid);
			}
		if(dayid==3){
			counter3++;
			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'aa' +dayid+counter3 );
			newTextBoxDiv.after().html(' <input type="text"   class="abc" required="true" name="doctorTimingVO['+dayid+'].startTime"  > ');
			newTextBoxDiv.appendTo("#startdiv"+dayid);

			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'bb' +dayid+counter3 );
			newTextBoxDiv.after().html(' <input type="text"   class="abc" required="true" name="doctorTimingVO['+dayid+'].endTime"   > ');
			newTextBoxDiv.appendTo("#enddiv"+dayid);
			}
		if(dayid==4){
			counter4++;
			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'aa' +dayid+counter4 );
			newTextBoxDiv.after().html(' <input type="text"   class="abc" required="true" name="doctorTimingVO['+dayid+'].startTime"  > ');
			newTextBoxDiv.appendTo("#startdiv"+dayid);

			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'bb' +dayid+counter4 );
			newTextBoxDiv.after().html(' <input type="text"   class="abc" required="true" name="doctorTimingVO['+dayid+'].endTime"   > ');
			newTextBoxDiv.appendTo("#enddiv"+dayid);
			}

		if(dayid==5){
			counter5++;
			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'aa' +dayid+counter5 );
			newTextBoxDiv.after().html(' <input type="text"  class="abc" required="true" name="doctorTimingVO['+dayid+'].startTime"  > ');
			newTextBoxDiv.appendTo("#startdiv"+dayid);

			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'bb' +dayid+counter5 );
			newTextBoxDiv.after().html(' <input type="text"  class="abc" required="true" name="doctorTimingVO['+dayid+'].endTime"   > ');
			newTextBoxDiv.appendTo("#enddiv"+dayid);
			}
		if(dayid==6){
			counter6++;
			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'aa' +dayid+counter6 );
			newTextBoxDiv.after().html(' <input type="text"  class="abc" required="true" name="doctorTimingVO['+dayid+'].startTime"  > ');
			newTextBoxDiv.appendTo("#startdiv"+dayid);

			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'bb' +dayid+counter6 );
			newTextBoxDiv.after().html(' <input type="text"  class="abc" required="true" name="doctorTimingVO['+dayid+'].endTime"   > ');
			newTextBoxDiv.appendTo("#enddiv"+dayid);
			}
		if(dayid==7){
			counter7++;
			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'aa' +dayid+counter7 );
			newTextBoxDiv.after().html(' <input type="text"  class="abc" required="true" name="doctorTimingVO['+dayid+'].startTime"  > ');
			newTextBoxDiv.appendTo("#startdiv"+dayid);

			var newTextBoxDiv = $(document.createElement('div'))
			.attr("id", 'bb' +dayid+counter7 );
			newTextBoxDiv.after().html(' <input type="text"  class="abc" required="true" name="doctorTimingVO['+dayid+'].endTime"   > ');
			newTextBoxDiv.appendTo("#enddiv"+dayid);
			}
		
		dynamicDate();
	return true;	
	
}
	</script>





</head>


<body>
<form:form id="formId" action="SaveDoctorTiming.do" method="POST" modelAttribute="DoctorTimingVO" autocomplete="off">
            
            <form:hidden path="providerID" id="providerId"/>
              <div id="content-right-main" class="small-12 medium-12 large-10 columns">
                  <div id="main-content-full" class="small-12 medium-12 large-12 columns">
					<h1 class="red-heading">Hours</h1>
									
						<div class="hours-doctor-timings">
						
						
						<c:forEach var="dayID" begin="1" end="7">
						
							<div class="small-12 medium-6 large-2 columns">
								    <c:if test="${dayID == 1}">
                					  <h4>Monday</h4>
                					  </c:if>
                  
               					    <c:if test="${dayID == 2}">
                				  <h4>Tuesday</h4>
                					  </c:if>
                  
                 					  <c:if test="${dayID == 3}">
                 				 <h4>Wednesday</h4>
                				  </c:if>
                  
                				   <c:if test="${dayID == 4}">
                 					 <h4>Thursday</h4>
                 					 </c:if>
                  
                 					  <c:if test="${dayID == 5}">
                					  <h4>Friday</h4>
                 					 </c:if>
                  
                 					  <c:if test="${dayID == 6}">
                  					<h4>Saturday</h4>
                					  </c:if>
                  
                 					  <c:if test="${dayID == 7}">
                						 <h4>Sunday</h4>
                 					 </c:if>
							</div>
							<div class="small-12 medium-6 large-3 columns" id="startdiv${dayID}">
								<label>From</label>
								<!-- <input name="from" type="text" placeholder="07:00"> -->
							</div>
							<div class="small-12 medium-6 large-3 columns" id="enddiv${dayID}">
								<label>To</label>
								<!-- <input name="to" type="text" placeholder="12:30"> -->
							</div>
							<div class="small-12 medium-6 large-2 columns" id="href${dayID}">
							  <a href="#" onclick="removeclicked('${dayID}')" id="remove${dayID}">Remove</a>&nbsp
                  <a href="#" onclick="return addclicked('${dayID}')" id="addmore${dayID}">Add More</a>
							</div>
							<div class="small-12 medium-6 large-2 columns">
					<%-- 	 <form:checkbox path="doctorTimingVO[${dayID}].checkBox" id="checkbox${dayID}" onclick="closecheckboxclicked('${dayID}')" class="checkboxclose" /> 
                  <label for="checkbox${dayID}">Is Day Off</label> --%>
                  <input name="doctorTimingVO[${dayID}].checkBox" type="checkbox" onclick="closecheckboxclicked('${dayID}')"  id="checkbox${dayID}">
								<label for="checkbox${dayID}">Is Day Off</label>
                  
							</div>
							<hr>
							<div class="clearfix"></div>
							
							
							 </c:forEach>
							
							
							
							
							<div class="small-12 medium-12 large-2 large-offset-10 columns">
		              <button type="submit" class="btn btn-default" data-dismiss="modal">Submit</button>
		  </div>
						</div><!--.hours-doctor-timings-->
					</form:form>
					                      
                  </div><!-----#main-content-full------->
              </div><!-----#content right main----------->
</body>
<script src="js/page-js/jquery.datetimepicker.js"></script>

<script type="text/javascript">
jQuery(document).ready(function() {
$.getScript("js/page-js/jquery.datetimepicker.js", function(){

	 
	   jQuery('.abc').datetimepicker({
			  datepicker:false,
			  format:'H:i',
			  step:30 
			});
	   // Use anything defined in the loaded script...
	});
});


function dynamicDate() {
	$.getScript("js/page-js/jquery.datetimepicker.js", function(){

		   
		   jQuery('.abc').datetimepicker({
				  datepicker:false,
				  format:'H:i',
				  step:30 
				});
		   // Use anything defined in the loaded script...
		});
}


</script>
</html>