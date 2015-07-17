<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Clearing House</title>


<script type="text/javascript">
$(document).ready(function(){

	 var Speciality = document.getElementById("SpecialityDiv");
	 Speciality.style.display = "block";
	 var Lab = document.getElementById("LabDiv");
	 Lab.style.display = "none";
	
    $('input[type="radio"]').click(function(){
        if($(this).attr("value")=="Speciality"){
        	 
        	 var Speciality = document.getElementById("SpecialityDiv");
        	 Speciality.style.display = "block";
        	 var Lab = document.getElementById("LabDiv");
        	 Lab.style.display = "none";
        }
        if($(this).attr("value")=="Lab"){
         
          var Speciality = document.getElementById("SpecialityDiv");
     	 Speciality.style.display = "none";
     	 var Lab = document.getElementById("LabDiv");
     	 Lab.style.display = "block";
        }

         });
});
</script>


<script type="text/javascript">
$(document).ready(function(){
	
	 var Speciality = document.getElementById("SpecialityTableDiv");
	 Speciality.style.display = "block";
	 var Lab = document.getElementById("LabTableDiv");
	 Lab.style.display = "none";
   $('input[type="radio"]').click(function(){
            if($(this).attr("value")=="SpecialityTable"){
            	
            	 var Speciality = document.getElementById("SpecialityTableDiv");
            	 Speciality.style.display = "block";
            	 var Lab = document.getElementById("LabTableDiv");
            	 Lab.style.display = "none";
            }
            if($(this).attr("value")=="LabTable"){
             
              var Speciality = document.getElementById("SpecialityTableDiv");
         	 Speciality.style.display = "none";
         	 var Lab = document.getElementById("LabTableDiv");
         	 Lab.style.display = "block";
            }
   });
});
</script>




</head>
<body>
	<div id="content-right-main"
		class="small-12 medium-12 large-10 columns">
		<div id="main-content-full"
			class="small-12 medium-12 large-12 columns">

			<div class="network-page">
				<div class="tabs-container">
					<ul class="tabs" data-tab>
						<li class="tab-title active"><a href="#panel1">My Network</a></li>
						<li class="tab-title"><a href="#panel2">Add To My Network</a></li>
					</ul>
					<div class="tabs-content">
						<div class="content active" id="panel1">
							<div class="large-12 columns" id="form-wraper-network">
								<%-- 	<form action="" method="get" class="tabs-network-form"> --%>
								<div class="small-12 medoim-12 large-12 columns">
									<input name="speciality" type="radio" value="SpecialityTable" id="speciality-radio"
										checked><label for="speciality-radio">Speciality</label>
									<input name="speciality" type="radio" value="LabTable" id="lab-radio"><label
										for="lab-radio">Lab</label>
								</div>
								
								<div class="clearfix"></div>
<div id="SpecialityTableDiv">
								<div class="refer-a-doc-new-copy-inbox-table">
									<c:if test="${!empty chPracticesInfo}">
										<table width="100%" cellpadding="0" cellspacing="0">
											<tr class="first-row-of-table">
												<td>Practice Name</td>
												<td>Category</td>
												<td>Speciality</td>
											</tr>
											<c:forEach items="${chPracticesInfo}" var="chPracticeInfo">
												<tr>
													<td><a
														href="PracticeInfoFromCh.do?practiceId=${chPracticeInfo.practiceID}">${chPracticeInfo.name}</a></td>
													<td>${chPracticeInfo.category}</td>
													<td><c:forEach items="${chPracticeInfo.sp}" var="sp">
					${sp} &nbsp; 
				</c:forEach></td>
												</tr>

											</c:forEach>
										</table>
									</c:if>
								</div>
</div>
<div id="LabTableDiv">
Lab Table

</div>


								<%-- </form><!--.tabs-network-form--> --%>
							</div>
							<!--#form-wraper-network-->
						</div>
						<div class="content" id="panel2">
							<div class="large-12 columns" id="form-wraper-network">

									<div class="small-12 medoim-12 large-12 columns">
									<input name="speciality" type="radio" value="Speciality" id="speciality-radio" checked><label for="speciality-radio">Speciality</label>
									<input name="speciality" type="radio" value="Lab" id="lab-radio"><label for="lab-radio">Lab</label>
									</div>

								<div class="clearfix"></div>


<div id="SpecialityDiv">


								<form:form method="POST" action="addPractice.do"
									class="tabs-network-form" id="addtonetwork">
									<div id="drops" class="small-12 medium-12 large-12 columns">
										<input class="sb_input" type="text"/ placeholder="Select Specialties">
										<ul class="sb_dropdown" style="display: none;">
											<li class="sb_filter">You can select multiple specialty</li>
											<li><input type="checkbox" /><label for="all"><strong>All
														Specialties</strong></label></li>
											<c:forEach items="${practiceSpecialties}" var="speciality">
											
												<li><input type="checkbox" value="${speciality}" name="sp"/><label
													for="${speciality}">${speciality}</label></li>
											</c:forEach>
										</ul>
									</div>

									<div id="input-1" class="small-12 medium-12 large-12 columns">
										<form:input path="name" value="${chpsp.name}"
											placeholder="Practice Name" />
									</div>
									<div id="input-1" class="small-12 medium-12 large-12 columns">
										<form:input path="emailId" value="${chpsp.emailId}"
											placeholder="Email" />
									</div>

									<div id="input-1" class="small-12 medium-12 large-12 columns">
										<form:select path="categoryId">
											<form:option value="0">Select a Category</form:option>
											<c:forEach items="${categories}" var="category">
												<form:option value="${category.codeId}">${category.codeDesc}</form:option>
											</c:forEach>
										</form:select>
									</div>

									<div class="clearfix"></div>
									<div class="large-12 columns">
										<input name="submit" type="submit" value="Submit"
											class="alignright">
									</div>

								</form:form>
								
								</div>
								
								<div id="LabDiv">
								<form action="" method="get" class="tabs-network-form" id="addtonetwork1">
									<div id="drops" class="small-12 medium-12 large-12 columns">
											<input class="sb_input" type="text"/ placeholder="Select Categories">
											<ul class="sb_dropdown" style="display:none;">
												<li class="sb_filter">You can select multiple categories</li>
												<li><input type="checkbox"/><label for="all"><strong>All Categories</strong></label></li>
												<li><input type="checkbox"/><label for="Spine Surgery">Spine Surgery</label></li>
												<li><input type="checkbox"/><label for="Gynecology">Gynecology</label></li>
												<li><input type="checkbox"/><label for="Podiatry">Podiatry</label></li>
												<li><input type="checkbox"/><label for="Pulmonary">Pulmonary</label></li>
												<li><input type="checkbox"/><label for="Orthopedic">Orthopedic</label></li>
												<li><input type="checkbox"/><label for="Auto Accident">Auto Accident</label></li>
												<li><input type="checkbox"/><label for="Heart Specialist">Heart Specialist</label></li>
												<li><input type="checkbox"/><label for="ENT">ENT</label></li>
												<li><input type="checkbox"/><label for="Rehabilitation">Rehabilitation</label></li>
												<li><input type="checkbox"/><label for="Neurology">Neurology</label></li>
												<li><input type="checkbox"/><label for="Chiropractic">Chiropractic</label></li>
												<li><input type="checkbox"/><label for="Jewelry">Vascular</label></li>
												<li><input type="checkbox"/><label for="Workers Comp">Workers Comp </label></li>
												<li><input type="checkbox"/><label for="Sleep">Sleep</label></li>
												<li><input type="checkbox"/><label for="Workers Comp">Workers Comp </label></li>
												<li><input type="checkbox"/><label for="Urology">Urology</label></li>
												<li><input type="checkbox"/><label for="Pain Management">Pain Management</label></li>
												<li><input type="checkbox"/><label for="Other">Other</label></li>
											</ul>
										</div>

										<div id="input-1" class="small-12 medium-12 large-12 columns">
											<input type="text" placeholder="Practice Name">    
										</div>
										<div id="input-1" class="small-12 medium-12 large-12 columns">
											<input type="text" placeholder="Email">    
										</div>
										<div class="clearfix"></div>
										<div class="large-12 columns">
											<input name="submit" type="button" value="Submit" class="alignright">
										</div>

									</form>
								
								</div>
								
								<!--.tabs-network-form-->
							</div>
							<!--#form-wraper-network-->
						</div>
					</div>
				</div>
				<!--.tabs-container-->
			</div>
			<!--.network-page-->

		</div>
		<!-----#main-content-full------->
	</div>
	<!-----#content right main----------->
</body>

        <script type="text/javascript">
            $(function() {
				/**
				* the element
				*/
				var $ui 		= $('#ui_element');
				
				/**
				* on focus and on click display the dropdown, 
				* and change the arrow image
				*/
				$ui.find('.sb_input').bind('focus click',function(){
					$ui.find('.sb_down')
					   .addClass('sb_up')
					   .removeClass('sb_down')
					   .andSelf()
					   .find('.sb_dropdown')
					   .show();
				});
				
				/**
				* on mouse leave hide the dropdown, 
				* and change the arrow image
				*/
				$ui.bind('mouseleave',function(){
					$ui.find('.sb_up')
					   .addClass('sb_down')
					   .removeClass('sb_up')
					   .andSelf()
					   .find('.sb_dropdown')
					   .hide();
				});
				
				/**
				* selecting all checkboxes
				*/
				$ui.find('.sb_dropdown').find('label[for="all"]').prev().bind('click',function(){
					$(this).parent().siblings().find(':checkbox').attr('checked',this.checked).attr('disabled',this.checked);
				});
            });
        </script>
		<!--FOR ADD TO NETWORK BEGINS-->
		        <script type="text/javascript">
            $(function() {
				/**
				* the element
				*/
				var $ui 		= $('#addtonetwork');
				var $ui1 		= $('#addtonetwork1');
				/**
				* on focus and on click display the dropdown, 
				* and change the arrow image
				*/
				$ui.find('.sb_input').bind('focus click',function(){
					$ui.find('.sb_down')
					   .addClass('sb_up')
					   .removeClass('sb_down')
					   .andSelf()
					   .find('.sb_dropdown')
					   .show();
				});

				$ui1.find('.sb_input').bind('focus click',function(){
					$ui1.find('.sb_down')
					   .addClass('sb_up')
					   .removeClass('sb_down')
					   .andSelf()
					   .find('.sb_dropdown')
					   .show();
				});
				
				/**
				* on mouse leave hide the dropdown, 
				* and change the arrow image
				*/
				$ui.bind('mouseleave',function(){
					$ui.find('.sb_up')
					   .addClass('sb_down')
					   .removeClass('sb_up')
					   .andSelf()
					   .find('.sb_dropdown')
					   .hide();
				});


				$ui1.bind('mouseleave',function(){
					$ui1.find('.sb_up')
					   .addClass('sb_down')
					   .removeClass('sb_up')
					   .andSelf()
					   .find('.sb_dropdown')
					   .hide();
				});
				/**
				* selecting all checkboxes
				*/
				$ui.find('.sb_dropdown').find('label[for="all"]').prev().bind('click',function(){
					$(this).parent().siblings().find(':checkbox').attr('checked',this.checked).attr('disabled',this.checked);
				});

				$ui1.find('.sb_dropdown').find('label[for="all"]').prev().bind('click',function(){
					$(this).parent().siblings().find(':checkbox').attr('checked',this.checked).attr('disabled',this.checked);
				});
				
            });
        </script>


</html>