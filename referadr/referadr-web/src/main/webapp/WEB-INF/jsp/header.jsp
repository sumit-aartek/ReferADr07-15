<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
 <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Connecting Doctors</title>
    <link rel="stylesheet" href="css/foundation.css" />
    <link rel="stylesheet" href="css/style.css" />
    <script src="js/vendor/modernizr.js"></script>
	<script src="js/page-js/nextPrevious.js"></script>
	<script src="js/page-js/validationForFisrtNext.js"></script>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="responsive/style.css">
	<link rel="stylesheet" type="text/css" href="responsive/responsive.css">
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<link href="bootstrap/Untitled-1.css" rel="stylesheet">



	<link rel="stylesheet" href="css/notifications.css" />
	<script type="text/javascript" src="js/jsp-js/desktop-notify-min.js"></script>
	    <script>
	   var globaluserName='';
	   var lastDateTime='';
        function NotificationCenter($scope) {
            var permissionLevels = {};
            permissionLevels[notify.PERMISSION_GRANTED] = 0;
            permissionLevels[notify.PERMISSION_DEFAULT] = 1;
            permissionLevels[notify.PERMISSION_DENIED] = 2;

            $scope.isSupported = notify.isSupported;
            $scope.permissionLevel = permissionLevels[notify.permissionLevel()];

            $scope.getClassName = function() {
                if ($scope.permissionLevel === 0) {
                    return "allowed"
                } else if ($scope.permissionLevel === 1) {
                    return "default"
                } else {
                    return "denied"
                }
            }

            $scope.callback = function() {
                console.log("test");
            }

            $scope.requestPermissions = function() {
                notify.requestPermission(function() {
                    $scope.$apply($scope.permissionLevel = permissionLevels[notify.permissionLevel()]);
                })
            }
        }

        function show(updatetoglobaluserName) {
		//alert(updatetoglobaluserName);
		   

		   $.ajax({
		          url : "getUpdatesNotifications.do?userName=" + updatetoglobaluserName +"&lastDateTime="+lastDateTime,
		          type : "GET",
		          contentType : "application/json; charset=utf-8",
		          success : function(t) {
		        	  lastDateTime= t.lastDateTimelist[0];
		        	  //alert(lastDateTime);
		        	  var notificationlist = t.notificationList;
		        	  for (i = 0; i < notificationlist.length; i++){
      	            	var value=notificationlist[i];
if(value=='newReferal'){
	var msg="You have a new referral. Please check your inbox";
notify.createNotification("Notification", {body:msg, icon: "img/alert.ico"})
}
if(value=='update'){
	var msg="update";
	notify.createNotification("Notification", {body:msg, icon: "img/alert.ico"})
	}
if(value=='schedule'){
	var msg="You have a new patient scheduled";
	notify.createNotification("Notification", {body:msg, icon: "img/alert.ico"})
	}
if(value=='referedPatientGotUpdated'){
	var msg="You have a update report sent to you about a referral you made. Please check your inbox";
	notify.createNotification("Notification", {body:msg, icon: "img/alert.ico"})
	}
if(value=='ReferedPatientGotScheduled'){
	var msg="You have a schedule report sent to you about a referral you made. Please check your inbox";
	notify.createNotification("Notification", {body:msg, icon: "img/alert.ico"})
	}

		        	  }
		          },
		          error : function() {
		          }
		        })
		   
		
           
        }
    </script>
	
	<script type="text/javascript">
	function startNotificationloop(userName){
		//alert("userName="+userName);
		
		
		globaluserName=userName;
		
	setInterval(function(){show(globaluserName)},10000);
	}
	</script>
	
	
	
	<script type="text/javascript">

$(function() {
		
		jQuery(document).ready(function() {
			if(${sessionScope.login!=null && sessionScope.login.roleId==2}){
			//	alert('hi');
				startNotificationloop('${sessionScope.login.userName}');
			
			}
		});
	});

	
	</script>
	
  </head>
  
   <div id="top-bar" class="top-header">
  	<div class="row">
		<div class="top-nav">
			<ul>
			<c:if test="${sessionScope.login!=null}"> 
				<li><a type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">Support</a> |</li>
				<li><a href="#">My Account</a> |</li>
				<li><a href="logout.do">Log Out</a></li>
				</c:if>
			</ul>
		 </div><!--.top-nav-->
	  </div><!--.row-->
  </div>
  
<div id="header" class="row">  

  <div id="logo" class="small-12 medium-12 large-3 columns">
  	<img src="img/logo.png" />
  	
  <%-- 	<c:if test="${sessionScope.login!=null}"> 	
  	<div  class="small-12 medium-12 large-4 columns">
  		<a href="logout.do">Logout</a></div></c:if> --%>
				
 
  </div><!--small-12 medium-12 large-6 columns-->
  
  
  
  
  
  
  
  <div id="contact-us" class="small-12 medium-12 large-9 columns">
  <form id="form1" action="searchPracticePatient.do" name="form1" method="post">
  <c:if test="${sessionScope.login!=null}">
  <div id="padding-0" class="small-12 medium-12 large-12 columns">
			<div id="headersearch" style="display: none;">
			<div id="padding-0" class="small-12 medium-12 large-6 columns">
				<label>
					<input type="text" name="keywords" id="searchName" placeholder="Search"/>
					
				</label>
			</div>
			<div id="padding-0" class="small-12 medium-12 large-4 columns">
				 <select id="select" name="typeSelect">
                        <option value="practice/CH">Practice/CH</option>
                        <option value="PatientName">Patient Name</option>
                       </select>
			</div>
			<div id="full-width" class="small-12 medium-12 large-4 columns">
				 <input type="Submit" value="Search">
			</div>
		</div>
  </div></c:if>
  
  </form>
  
  <c:if test="${sessionScope.login!=null}"> 	
  <!--  <a type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">Support</a> --> </c:if>
 <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" style="display: none;">
      <div class="modal-dialog">
        <div class="modal-content large-12 columns">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><img class="close" src="bootstrap/alert_boxes_close_ico.png"></span></button>
            <h4 class="modal-title" id="exampleModalLabel">Create Support ticket</h4>
          </div>
          <div id="mailsent" class="modal-body">
            <form>
              <div class="form-group">
                <label for="recipient-name" class="control-label" >Ticket Subject</label>
                <input type="text" class="form-control" id="recipient-name">
              </div>
              <div class="form-group">
                <label for="message-text" class="control-label">Problem Description</label>
                <textarea class="form-control" id="message-text" gramm="" txt_gramm_id="75bbb9c6-1777-d23e-5118-a2276d11afea"></textarea>
              </div>
			  <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
            <button type="button" class="btn btn-primary" id="support">Create</button>
          </div>
            </form>
            <p id="demo222" style="color: red"></p>
          </div>
          </div>
       
      </div>
    </div><!-- /.modal -->
  <!-- 	<h1>Support</h1> -->
  	</div>
  
 

 	<c:if test="${sessionScope.login!=null}"> 	
  	<div  class="small-12 medium-12 large-2 columns">
  	<!--	 <a href="logout.do">Logout</a>
  		 <a type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">Support</a> -->
  		</div></c:if>
  		 </div>
  		 <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="responsive/script.js"></script>

<script src="bootstrap/jq.js"></script>


  <script src="bootstrap/bs.js"></script>

    <script src="js/vendor/jquery.js"></script>
    <script src="js/foundation.min.js"></script>
    <script>
      $(document).foundation();
    </script>
    <script type="text/javascript">
 $("#support").click(function() {
        var subject = $("#recipient-name").val();
        var description=$("#message-text").val();
        if(subject!="" ||description!=""){
        $.ajax({
          url : "supportAction.do?subjecName=" + encodeURIComponent(subject) + '&descriptions='+ encodeURIComponent(description),
          type : "GET",
          contentType : "application/json; charset=utf-8",
          success : function(t) {
        	  $('#demo222').append('<div></div>').html("Thank You for your submission. One of our customer reps will get back to you soon.");
        		var obj101=document.getElementById("mailsent");
        		obj101.style.display="true"; 
        
        		
        	    document.getElementById('recipient-name').value="";
        	    document.getElementById('message-text').value="";
        		
        		
          },
          error : function() {
          }
        })//close ajax
        }//if
        else{
          //alert("wrong");
          $('#demo222').append('<div></div>').html("Something went wrong. Please try again.");
            var obj2=document.getElementById("mailsent");
           obj2.style.display="true";
        }
        
      })
      </script>
  