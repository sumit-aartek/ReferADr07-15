<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
    
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Connecting Doctors</title>
</head>

		<!--HEADER BEGINS HERE-->
		<header id="header-container">
			<div class="header-inner-container">
				<div class="row">
					
					<div id="logo" class="small-12 medium-12 large-6 columns">
						<img src="img7_15/logo.png" class="logo" />
					</div><!--#logo-->
					<div id="header-right" class="small-12 medium-12 large-6 columns">
						<ul>
							<li><a href="#" id="showSupport"><i class="fa fa-life-ring"></i>Support</a></li>
							<li class="center-li-header-right"><a href="#"><i class="fa fa-user"></i>My Account</a></li>
							<li><a href="logout.do"><i class="fa fa-power-off"></i>Log Out</a></li>
						</ul>
					</div><!--#header-right-->
					
				</div><!--.row-->
			</div><!--.header-inner-container-->
		</header><!--#header-container-->
		<!--HEADER ENDS HERE-->
		
	 <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" style="display: none;">
      <div class="modal-dialog">
        <div class="modal-content large-12 columns">
          <div class="modal-header">
            <button type="button" id="closeSupport1" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><img class="close" src="bootstrap/alert_boxes_close_ico.png"></span></button>
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
            <button type="button" id="closeSupport" class="btn btn-default" data-dismiss="modal">Cancel</button>
            <button type="button" class="btn btn-primary" id="support">Create</button>
          </div>
            </form>
            <p id="demo222" style="color: red"></p>
          </div>
          </div>
      </div>
    </div><!-- /.modal -->
  <!-- 	<h1>Support</h1> -->

    <script type="text/javascript">
$("#showSupport").click(function(){
	 var exampleModal = document.getElementById("exampleModal");
	 exampleModal.style.display = "block";
}),

$("#closeSupport").click(function(){
	 var exampleModal = document.getElementById("exampleModal");
	 exampleModal.style.display = "none";
}),

$("#closeSupport1").click(function(){
	 var exampleModal = document.getElementById("exampleModal");
	 exampleModal.style.display = "none";
}),
    
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
		
</html>