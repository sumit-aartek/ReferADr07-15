$(function() {
  $("#address1").change(function() {
        var address1Val = $("#address1").val();
        if(address1Val!=0){$(function() {
        	  $("#address1").change(function() {
        	        var address1Val = $("#address1").val();
        	        if(address1Val!=0){
        	        $.ajax({
        	          url : "fullAddressByaddress1.do?address1=" + address1Val,
        	          type : "GET",
        	          contentType : "application/json; charset=utf-8",
        	          success : function(t) {
        	        	  var e = t.fullAddressByAddress1;
        	        	  document.getElementById('address2').value=e[0].locAddress2;
        	            //  document.getElementById('city').value=e[0].locCity;
        	           //   document.getElementById('zip').value=e[0].locZip;
        	              document.getElementById('phone').value=e[0].locPhone;
        	            //  document.getElementById('fax').value=e[0].locFax;
        	            //  document.getElementById('state').value=e[0].redState.stateId;
        	              document.getElementById('addId').value=e[0].locId;
        	          },
        	          error : function() {
        	          }
        	        })
        	        }else{
        	          document.getElementById('address2').value="";
        	         // document.getElementById('city').value="";
        	        //  document.getElementById('zip').value="";
        	       //   document.getElementById('phone').value="";
        	         // document.getElementById('fax').value="";
        	         // document.getElementById('state').value="";
        	        }
        	      }),
        	      
        	      
        	      
        	      
        	      
        	      
        	      
        	      
        	      
        	      $("#chearingHouseId").change(function() {
        	        var chearingHouseId = $("#chearingHouseId").val();
        	        if(chearingHouseId!=0){
        	        $("#practiceSpectialtyId").text(""), $("#practiceSpectialtyId").append($("<option value='0'></option>").text("Select Specialty"));
        	        var chearingHouseId = $("#chearingHouseId").val();
        	        $.ajax({
        	          url : "practiceSpecialtyBychearingHouse.do?chearingHouseId=" + chearingHouseId,
        	          type : "GET",
        	          contentType : "application/json; charset=utf-8",
        	          success : function(t) {
        	            var e = t.practiceSpecialtyList;
        	            var practiceSpecialityValue=t.practiceSpecialtyList[0];
        	            for (i = 0; i < e.length; i++){
        	            	var spl=e[i]
        	                          
        	              $("#practiceSpectialtyId").append($("<option value='"+ spl.praticeSplID+ "'></option>").text(spl.praticeSplDesc))
        	            }
        	            if(e.length==1){
        			           
        		            document.getElementById('practiceSpectialtyId').value=practiceSpecialityValue.praticeSplID;
        		            //here we are puting ajax code for practice dropdown
        		            }
        	            var practiceSpectialtyId = $("#practiceSpectialtyId").val();
        	            var chearingHouseId = $("#chearingHouseId").val();
        	            if(practiceSpectialtyId!=0){
        	            $("#practiceInfoId").text(""), $("#practiceInfoId").append($("<option value='0'></option>").text("Select Practice"));
        	            $.ajax({
        	              url : "PracticeInfoBypracticeSpecialty.do?practiceSpectialtyId=" +practiceSpectialtyId+"&chearingHouseId="+chearingHouseId,
        	              type : "GET",
        	              contentType : "application/json; charset=utf-8",
        	              success : function(t) {
        	                var e = t.practiceInfoList;
        	                var practiceInfoValue=t.practiceInfoList[0];
        	                for (i = 0; i < e.length; i++){
        	                	var practiceInfo=e[i];
        	                  $("#practiceInfoId").append($("<option value='"+ practiceInfo.practiceId+ "'></option>").text(practiceInfo.practiceName))
        	                }
        	                if(e.length==1){
        	    		           
        	    	            document.getElementById('practiceInfoId').value=practiceInfoValue.practiceId;
        	    	            //here we are puting ajax code for practice dropdown
        	    	            } 
        	                var practiceInfoId = $("#practiceInfoId").val();
        	                if(practiceInfoId!=0){
        	                $("#providerInfoId").text(""), $("#providerInfoId").append($("<option value='0'></option>").text("Select Provider"));
        	                $.ajax({
        	                  url : "providerInfoByPracticeInfo.do?practiceInfoId=" +practiceInfoId,
        	                  type : "GET",
        	                  contentType : "application/json; charset=utf-8",
        	                  success : function(t) {
        	                    var e = t.practiceInfoList;
        	                    var providerInfoValue=t.practiceInfoList[0];
        	                    for (i = 0; i < e.length; i++){
        	                    	var pracInfo=e[i]
        	                      $("#providerInfoId").append($("<option value='"+ pracInfo.providerId+ "'></option>").text(pracInfo.providerFirstName))
        	                    }
        	                    if(e.length==1){
        	        		           
        	        	            document.getElementById('providerInfoId').value=providerInfoValue.providerId;
        	        	            //here we are puting ajax code for practice dropdown
        	        	            }  
        	                    },
        	                  error : function() {
        	                  }
        	                })
        	                }else{
        	                  $("#providerInfoId").text(""), $("#providerInfoId").append($("<option value='0'></option>").text("Select Provider"));
        	                }
        	                },
        	              error : function() {
        	              }
        	            })
        	            }else{
        	              $("#practiceInfoId").text(""), $("#practiceInfoId").append($("<option value='0'></option>").text("Select Practice"));
        	            }
        	          },
        	          error : function() {
        	          }
        	        })
        	        }else{
        	          $("#practiceSpectialtyId").text(""), $("#practiceSpectialtyId").append($("<option value='0'></option>").text("Select Specialty"));
        	          $("#practiceInfoId").text(""), $("#practiceInfoId").append($("<option value='0'></option>").text("Select Practice"));
        	          $("#providerInfoId").text(""), $("#providerInfoId").append($("<option value='0'></option>").text("Select Provider"));
        	        }
        	      }),
        	      
        	      
        	      
        	      
        	      
        	      
        	      
        	      
        	      
        	      
        	      $("#practiceSpectialtyId").change(function() {
        	        var practiceSpectialtyId = $("#practiceSpectialtyId").val();
        	        var chearingHouseId = $("#chearingHouseId").val();
        	        if(practiceSpectialtyId!=0){
        	        $("#practiceInfoId").text(""), $("#practiceInfoId").append($("<option value='0'></option>").text("Select Practice"));
        	        $.ajax({
        	          url : "PracticeInfoBypracticeSpecialty.do?practiceSpectialtyId=" +practiceSpectialtyId+"&chearingHouseId="+chearingHouseId,
        	          type : "GET",
        	          contentType : "application/json; charset=utf-8",
        	          success : function(t) {
        	            var e = t.practiceInfoList;
        	            var practiceInfoValue=t.practiceInfoList[0];
        	            for (i = 0; i < e.length; i++){
        	            	var practiceInfo=e[i];
        	              $("#practiceInfoId").append($("<option value='"+ practiceInfo.practiceId+ "'></option>").text(practiceInfo.practiceName))
        	            }
        	            if(e.length==1){
        			           
        		            document.getElementById('practiceInfoId').value=practiceInfoValue.practiceId;
        		            //here we are puting ajax code for practice dropdown
        		            }  
        	            var practiceInfoId = $("#practiceInfoId").val();
        	            if(practiceInfoId!=0){
        	            $("#providerInfoId").text(""), $("#providerInfoId").append($("<option value='0'></option>").text("Select Provider"));
        	            $.ajax({
        	              url : "providerInfoByPracticeInfo.do?practiceInfoId=" +practiceInfoId,
        	              type : "GET",
        	              contentType : "application/json; charset=utf-8",
        	              success : function(t) {
        	                var e = t.practiceInfoList;
        	                var providerInfoValue=t.practiceInfoList[0];
        	                for (i = 0; i < e.length; i++){
        	                	var pracInfo=e[i]
        	                  $("#providerInfoId").append($("<option value='"+ pracInfo.providerId+ "'></option>").text(pracInfo.providerFirstName))
        	                }
        	                if(e.length==1){
        	    		           
        	    	            document.getElementById('providerInfoId').value=providerInfoValue.providerId;
        	    	            //here we are puting ajax code for practice dropdown
        	    	            }  
        	                },
        	              error : function() {
        	              }
        	            })
        	            }else{
        	              $("#providerInfoId").text(""), $("#providerInfoId").append($("<option value='0'></option>").text("Select Provider"));
        	            }
        	            },
        	          error : function() {
        	          }
        	        })
        	        }else{
        	          $("#practiceInfoId").text(""), $("#practiceInfoId").append($("<option value='0'></option>").text("Select Practice"));
        	          $("#providerInfoId").text(""), $("#providerInfoId").append($("<option value='0'></option>").text("Select provider"));
        	        }
        	      }),
        	      
        	      
        	      
        	      
        	      $("#practiceInfoId").change(function() {
        	        var practiceInfoId = $("#practiceInfoId").val();
        	        if(practiceInfoId!=0){
        	        $("#providerInfoId").text(""), $("#providerInfoId").append($("<option value='0'></option>").text("Select provider"));
        	        $.ajax({
        	          url : "providerInfoByPracticeInfo.do?practiceInfoId=" +practiceInfoId,
        	          type : "GET",
        	          contentType : "application/json; charset=utf-8",
        	          success : function(t) {
        	            var e = t.practiceInfoList;
        	            var providerInfoValue=t.practiceInfoList[0];
        	            for (i = 0; i < e.length; i++){
        	            	var pracInfo=e[i]
        	              $("#providerInfoId").append($("<option value='"+ pracInfo.providerId+ "'></option>").text(pracInfo.providerFirstName))
        	            }
        	            if(e.length==1){
        			           
        		            document.getElementById('providerInfoId').value=providerInfoValue.providerId;
        		            //here we are puting ajax code for practice dropdown
        		            }  
        	            },
        	          error : function() {
        	          }
        	        })
        	        }else{
        	          $("#providerInfoId").text(""), $("#providerInfoId").append($("<option value='0'></option>").text("Select Provider"));
        	        }
        	      }),
        	      /*$("#search").click(function() {
        	        var searchKey = $("#lastName").val();
        	        $.ajax({
        	          url : "patientSearchAction.do?searchKeyword=" + searchKey,
        	          type : "GET",
        	          contentType : "application/json; charset=utf-8",
        	          success : function(t) {
        	            var e= t.practiceInfoList;
        	            showData=e;
        	            $('#showDataTable').append('<table width="100%" border="1" cellpadding="0" cellspacing="0"></table>');
        	            var table = $('#showDataTable').children();
        	            table.append("<tr class='first-row-heading-table'><td>SNO</td><td>First Name</td><td>DOB</td><td>SSN</td></tr>");
        	            for (i = 0; i < e.length; i++)
        	              {
        	              table.append($("<tr><td><a href='#' onclick='openSearchPage("+e[i].patientId+")'>"+e[i].patientId+"</a></td><td>"+e[i].patientFirstName+"</td><td>"+e[i].patientDob+"</td><td>"+e[i].patientSSN+"</td></tr>"));
        	              }
        	            
        	          },
        	          error : function() {
        	          }
        	        })//'id='+ encodeURIComponent(id) + '&name='+ encodeURIComponent(name)
        	      })*/
        	      $("#search").click(function() {
        	    	
        	        var searchKey = $("#lastName").val();
        	        var patientDateOb=$("#patientDateOb").val();
        	        var patientSsn=$("#patientSsn").val();
        	    /*    if(searchKey!="" && patientDateOb!=""){*/
        	        	
        	      
        	        if(searchKey!="" ||patientDateOb!=""||patientSsn!=""){
        	        	
        	        	var ShowAllPatientFields = document.getElementById("allDetails");
        	        	  ShowAllPatientFields.style.display = "block";
        	        	
        	        	        $.ajax({
        	          url : "patientSearchAction.do?searchKeyword=" + encodeURIComponent(searchKey) + '&dob='+ encodeURIComponent(patientDateOb) + '&patientSsn='+encodeURIComponent(patientSsn),
        	          type : "GET",
        	          contentType : "application/json; charset=utf-8",
        	          success : function(t) {
        	            var e= t.practiceInfoList;
        	            showData=e;
        	            $('#showDataTable').empty();
        	          
        	          
        	            if(e.length>1){
        	            if(showData!=null && showData!=""){
        	            	
        	            $('#showDataTable').append('<table width="100%" border="1" cellpadding="0" cellspacing="0"></table>');
        	          
        	            var table = $('#showDataTable').children();
        	            table.append("<tr class='first-row-heading-table'><td>SNO</td><td>Patient Name</td><td>DOB</td></tr>");
        	            }
        	            if(e!=null && e!=""){
        	              
        	            for (i = 0; i < e.length; i++)
        	              {
        	              
        	              table.append($("<tr><td><a href='#' onclick='openSearchPage("+e[i].patientId+")'>"+e[i].patientId+"</a></td><td><a href='#' onclick='openSearchPage("+e[i].patientId+")'> "+e[i].patientFirstName +" "+e[i].patientLastName +"</a>"+"</td><td>"+e[i].patientDob+"</td></tr>"));
        	              }
        	            }
        	            }
        	            if(e.length==1){
        	        	if (showData != null && showData != "") {
        	    			
        	    			var patitentName = document.getElementById('patientName');
        	    			var patitentLastName = document.getElementById('patientLastName');
        	    			var phone = document.getElementById('patientPhone');
        	    			var gender = document.getElementById('gender');
        	    			var dob = document.getElementById('dob');
        	    			var emailId = document.getElementById('emailId');
        	    			/* var address1 = document.getElementById('patientAddress1');
        	    			var address2 = document.getElementById('patientAddress2');
        	    			var city = document.getElementById('patientCity');
        	    			var state = document.getElementById('PatientState');
        	    			var zip = document.getElementById('patientZip');
        	    			
        	    			var fax = document.getElementById('patientFax');
        	    			
        	    			var ssn1 = document.getElementById('ssn1'); */
        	    			
        	    			/*  var ssn1=document.getElementById('ssn1').disabled = true;  */
        	    			for (i = 0; i < showData.length; i++) {
        	    				locId.value = showData[i].radLocation.locId;
        	    				patientId.value = showData[i].patientId;
        	    				//PatientState.value=showData[i].radLocation.redState.stateId;
        	    				patientIdHidden.value = showData[i].patientId;
        	    				if (showData[i].patientId == e[i].patientId) {
        	    					patitentName.value = showData[i].patientFirstName;
        	    					patitentLastName.value = showData[i].patientLastName;
        	    					gender.value = showData[i].patientGender;
        	    					dob.value = showData[i].patientDob;
        	    					phone.value = showData[i].radLocation.locPhone;
        	    					emailId.value = showData[i].patientEmail;
        	    					
        	    				//	address1.value = showData[i].radLocation.locAddress1;
        	    				//	address2.value = showData[i].radLocation.locAddress2;
        	    				//	city.value = showData[i].radLocation.locCity;
        	    				//	zip.value = showData[i].radLocation.locZip;
        	    					
        	    				//	fax.value = showData[i].radLocation.locFax;
        	    					
        	    			//		ssn1.value = showData[i].patientSSN;
        	    					
        	    				//	state.value = showData[i].radLocation.redState.stateId;
        	    				//	ssn1.disabled = true;
        	    					gender.disabled = true;
        	    					dob.disabled = true;
        	    					patientName.disabled = true;
        	    					patientLastName.disabled = true;
        	    					
        	    				}
        	    			}
        	    			var insuranceId = document.getElementById('insuranceId');
        	    			var group = document.getElementById('group');
        	    			var insurancePhone = document.getElementById('insurancePhone');
        	    			var insuranceName = document.getElementById('insuranceName');
        	    			
        	    			/* var preAuth = document.getElementById('preAuth');
        	    			var PreAuthDate = document.getElementById('PreAuthDate');
        	    			var toDate = document.getElementById('toDate');
        	    			var preAuthContachNo = document.getElementById('preAuthContachNo');
        	    			var notes = document.getElementById('notes');
        	    			var pokemonRed = document.getElementById('pokemonRed');
        	    			var pokemonBlue = document.getElementById('pokemonBlue'); */
        	    			
        	    			
        	    			//var confirmation = document.getElementById('confirmation');
        	    			if (showData != null && showData != "") {
        	    				for (i = 0; i < showData.length; i++) {
        	    					if (showData[i].patientId == e[i].patientId) {
        	    						var a = showData[i].patientInsuranceInfoList;
        	    						insuranceId.value = a[0].patientInsuranceId;
        	    						group.value = a[0].patientInsuranceGroup;
        	    					//	insurancePhone.value = a[0].patientInsurancePhone;
        	    					//	preAuth.value = a[0].patientPreAuthId;

        	    						//TODO :- Create a separte function for get Date in MM/DD/YYYY format 
        	    						var showStartDate = new Date(
        	    								a[0].patientPreAuthStartDate);
        	    						var newStartDate = showStartDate.getMonth()+1 + "/"
        	    								+ showStartDate.getDate() + "/"
        	    								+ showStartDate.getFullYear();

        	    						var showEndDate = new Date(a[0].patientPreAuthEndDate);
        	    						var newEndDate = showEndDate.getMonth()+1 + "/"
        	    								+ showEndDate.getDate() + "/"
        	    								+ showEndDate.getFullYear();

        	    						/* PreAuthDate.value = newStartDate;
        	    					toDate.value = newEndDate;

        	    						preAuthContachNo.value = a[0].patientPreAuthContactName;
        	    						notes.value = a[0].patientInsuranceNotes; */
        	    						
        	    						/* if (a[0].patientPre1AuthReq == 'Y') {
        	    							
        	    							$('#pokemonRed').attr('checked', true);
        	    							document.getElementById('preAuth').disabled = false;
        	    							document.getElementById('PreAuthDate').disabled = false;
        	    							document.getElementById('toDate').disabled = false;
        	    							document.getElementById('preAuthContachNo').disabled = false;
        	    							  document.getElementById('confirmation').disabled = false;
        	    							  var obj103=document.getElementById("preAuth");
        	    								obj103.style.display="block"; 
        	    								var obj104=document.getElementById("PreAuthDate");
        	    								obj104.style.display="block"; 
        	    								var obj105=document.getElementById("toDate");
        	    								obj105.style.display="block"; 
        	    								var obj106=document.getElementById("preAuthContachNo");
        	    								obj106.style.display="block"; 
        	    								var obj107=document.getElementById("confirmation");
        	    					/* 			obj107.style.display="block";  */
        	    						/* } else {
        	    							$('#pokemonBlue').attr('checked', true);
        	    							 var obj103=document.getElementById("preAuth");
        	    								obj103.style.display="none"; 
        	    								var obj104=document.getElementById("PreAuthDate");
        	    								obj104.style.display="none"; 
        	    								var obj105=document.getElementById("toDate");
        	    								obj105.style.display="none"; 
        	    								var obj106=document.getElementById("preAuthContachNo");
        	    								obj106.style.display="none"; 
        	    								var obj107=document.getElementById("confirmation");
        	    								obj107.style.display="none"; 
        	    						}  */
        	    						insuranceName.value = a[0].insuranceInfo.insuranceCompany;
        	    						
        	    						insuranceIdHideen.value = a[0].insuranceInfo.insuranceId;
        	    						patientInsuranceInfoIdHidden.value = a[0].patientInsuranceInfoId;
        	    					}
        	    				}
        	    			}

        	    			var obj4 = document.getElementById("div2");
        	    			obj4.style.display = "true";
        	    			var obj5 = document.getElementById("div3");
        	    			obj5.style.display = "block";
        	    			/* var white_content=document.getElementById("white_content");
        	    			white_content.style.display="block"; */
        	    		} 
        	            }
        	            
        	            
        	            
        	            var obj22=document.getElementById("demo2");
        	               obj22.style.display="none";
        	            /*else{
        	              
        	               $('#demo2').append('<div></div>').html("Please give any one information ");
        	                  var obj2=document.getElementById("div2");
        	                 obj2.style.display="true";
        	            }*/
        	            
        	          },
        	          error : function() {
        	        	
        	        	  $('#showDataTable').empty();
        	        	
        	             
        	            	var patitentName1 = document.getElementById('patientName');
        	            	patitentName1.value="";
        	    			var patitentLastName1 = document.getElementById('patientLastName');
        	    			patitentLastName1.value="";
        	    			var phone1 = document.getElementById('patientPhone');
        	    			phone1.value="";
        	    			var gender1 = document.getElementById('gender');
        	    			gender1.value='G';
        	    			var dob1 = document.getElementById('dob');
        	    			dob1.value='';
        	    			var emailId1 = document.getElementById('emailId');
        	    			emailId1.value="";
        	    			var insuranceName1 = document.getElementById('insuranceName');
        	    			insuranceName1.value="";
        	    			var insuranceId1 = document.getElementById('insuranceId');
        	    			insuranceId1.value="";
        	    			var group1 = document.getElementById('group');
        	    			group1.value="";
        	    			
        	    			patitentName1.disabled = false;
        	    			patitentLastName1.disabled = false;
        	    			gender1.disabled = false;
        					dob1.disabled = false;
        					patientName1.disabled = false;
        					patientLastName1.disabled = false;
        	    		
        	    			/*  var ShowAllPatientFields = document.getElementById("allDetails");
        	            	  ShowAllPatientFields.style.display = "none";*/
        	          }
        	        })//close ajax
        	        }//if
        	        else{
        	        	   var ShowAllPatientFields = document.getElementById("allDetails");
        	            	  ShowAllPatientFields.style.display = "none";
        	          $('#demo2').append('<div></div>').html("Please give any one information ");
        	            var obj2=document.getElementById("div2");
        	           obj2.style.display="true";
        	        
        	         
        	        }
        	    /* return true;
        	        }else{
        	    	  $('#demo2').append('<div></div>').html("Please Provide Both LastName & DOB!");
        	          var obj2=document.getElementById("div2");
        	         obj2.style.display="true";
        	         return false;
        	      }*/
        	      })
        	});


        	//////////////////////
        	function Refralreasonvalidation()
        	{
        	  var chearingHouseId=document.getElementById("chearingHouseId").value;
        	    var practiceSpectialtyId=document.getElementById("practiceSpectialtyId").value;
        	    var PrvoRederalReason=document.getElementById("PrvoRederalReason").value;
        	    var providerID=document.getElementById("providerID").value;
        	   
        	   if(chearingHouseId!=0&&practiceSpectialtyId!=0&&PrvoRederalReason!=null&&providerID!=0)
        	      {
        	 return true;
        	      }
        	    else{
        	    	if(providerID==0){
        	    		var obj4 = document.getElementById("providerdropdowm");
        	    		obj4.style.display = "block";
        	    		}
        	    	
        	$('#demo10').append('<div></div>').html("Please fill the required fields.");
        	var obj10=document.getElementById("warningmsg");
        	obj10.style.display="true"; 
        	return false;
        	       }

        	}
        	function Refralreasonvalidation1()
        	{
        		 var referring=document.getElementById("referring").value;
        		 var address1=document.getElementById("address1").value;
        		
        	      var chearingHouseId=document.getElementById("chearingHouseId").value;
        	        var practiceSpectialtyId=document.getElementById("practiceSpectialtyId").value;
        	        
        	       /* var gender=document.getElementById("gender").value;*/
        	        
        	        
        	      
        	       
        	       if(referring==0)
        	          {
        	    	   $('#demo11').append('<div></div>').html("Please Select a Referring.");
        	    	    var obj10=document.getElementById("warningmsgreferpatient11");
        	    	    obj10.style.display="true"; 
        	    	    return false;
        	          }
        	        
        	       if(address1==0)
        	       {
        	 	   $('#demo11').append('<div></div>').html("Please Select an Address.");
        	 	    var obj10=document.getElementById("warningmsgreferpatient11");
        	 	    obj10.style.display="true"; 
        	 	    return false;
        	       }
        	       if(chearingHouseId==0)
        	       {
        	 	   $('#demo11').append('<div></div>').html("Please select an Clearing House ID.");
        	 	    var obj10=document.getElementById("warningmsgreferpatient11");
        	 	    obj10.style.display="true"; 
        	 	    return false;
        	       }
        	       if(practiceSpectialtyId==0)
        	       {
        	 	   $('#demo11').append('<div></div>').html("Please select an Practice speciality.");
        	 	    var obj10=document.getElementById("warningmsgreferpatient11");
        	 	    obj10.style.display="true"; 
        	 	    return false;
        	       }
        	      /* if(gender=='G')
        	       {
        	 	   $('#demo11').append('<div></div>').html("Please select a Gender");
        	 	    var obj10=document.getElementById("warningmsgreferpatient11");
        	 	    obj10.style.display="true"; 
        	 	    return false;
        	       }*/
        	       return true;

        	}

        	function HidePatientInfo(){
        		
        	}

        $.ajax({
          url : "fullAddressByaddress1.do?address1=" + address1Val,
          type : "GET",
          contentType : "application/json; charset=utf-8",
          success : function(t) {
        	  var e = t.fullAddressByAddress1;
        	  document.getElementById('address2').value=e[0].locAddress2;
            //  document.getElementById('city').value=e[0].locCity;
           //   document.getElementById('zip').value=e[0].locZip;
              document.getElementById('phone').value=e[0].locPhone;
            //  document.getElementById('fax').value=e[0].locFax;
            //  document.getElementById('state').value=e[0].redState.stateId;
              document.getElementById('addId').value=e[0].locId;
          },
          error : function() {
          }
        })
        }else{
          document.getElementById('address2').value="";
         // document.getElementById('city').value="";
        //  document.getElementById('zip').value="";
       //   document.getElementById('phone').value="";
         // document.getElementById('fax').value="";
         // document.getElementById('state').value="";
        }
      }),
      
      
      
      
      
      
      
      
      
      $("#chearingHouseId").change(function() {
        var chearingHouseId = $("#chearingHouseId").val();
        if(chearingHouseId!=0){
        $("#practiceSpectialtyId").text(""), $("#practiceSpectialtyId").append($("<option value='0'></option>").text("Select Specialty"));
        var chearingHouseId = $("#chearingHouseId").val();
        $.ajax({
          url : "practiceSpecialtyBychearingHouse.do?chearingHouseId=" + chearingHouseId,
          type : "GET",
          contentType : "application/json; charset=utf-8",
          success : function(t) {
            var e = t.practiceSpecialtyList;
            var practiceSpecialityValue=t.practiceSpecialtyList[0];
            for (i = 0; i < e.length; i++){
            	var spl=e[i]
                          
              $("#practiceSpectialtyId").append($("<option value='"+ spl.praticeSplID+ "'></option>").text(spl.praticeSplDesc))
            }
            if(e.length==1){
		           
	            document.getElementById('practiceSpectialtyId').value=practiceSpecialityValue.praticeSplID;
	            //here we are puting ajax code for practice dropdown
	            }
            var practiceSpectialtyId = $("#practiceSpectialtyId").val();
            var chearingHouseId = $("#chearingHouseId").val();
            if(practiceSpectialtyId!=0){
            $("#practiceInfoId").text(""), $("#practiceInfoId").append($("<option value='0'></option>").text("Select Practice"));
            $.ajax({
              url : "PracticeInfoBypracticeSpecialty.do?practiceSpectialtyId=" +practiceSpectialtyId+"&chearingHouseId="+chearingHouseId,
              type : "GET",
              contentType : "application/json; charset=utf-8",
              success : function(t) {
                var e = t.practiceInfoList;
                var practiceInfoValue=t.practiceInfoList[0];
                for (i = 0; i < e.length; i++){
                	var practiceInfo=e[i];
                  $("#practiceInfoId").append($("<option value='"+ practiceInfo.practiceId+ "'></option>").text(practiceInfo.practiceName))
                }
                if(e.length==1){
    		           
    	            document.getElementById('practiceInfoId').value=practiceInfoValue.practiceId;
    	            
    	            
    	            var insurancename=document.getElementById('insuranceName').value;
    	        	// alert(insurancename);
    	            var practiceInfoId = $("#practiceInfoId").val();
    	            var searchValuee='';
    	            var flag=false;
    	            $.ajax({
    	                url :  "practiceInsuranceList.do?searchValue=" + searchValuee+"&practiceID="+practiceInfoId,
    	                type : "GET",
    	                contentType : "application/json; charset=utf-8",
    	                success : function(t) {
    	                	//alert("success");
    	                	//alert(t);
    	                	for (i = 0; i < t.length; i++){
    	                		if(t[i]==insurancename){
    	                		//	alert(t[i]);
    	                			flag=true;
    	                		}
    	                	}
    	                	if(flag){
    	                		//no need to remove insurance
    	                	}
    	                	else{
    	                		//ask to remove
    	                		//alert(insurancename);
    	                		if(insurancename!=''){
    	                		 var insuranceVarification = document.getElementById("insuranceVarification");
    	                		 insuranceVarification.style.display = "block";
    	                		 document.getElementById('insname').innerHTML='Selected practice does not accept '+insurancename;
    	                		}
    	                		// alert(document.getElementById('insname').value);
    	                	}
    	                  },
    	                error : function() {
    	                	//alert("error");
    	                }
    	              })
    	            
    	            
    	            
    	            
    	            
    	            //here we are puting ajax code for practice dropdown
    	            } 
                var practiceInfoId = $("#practiceInfoId").val();
                if(practiceInfoId!=0){
                $("#providerInfoId").text(""), $("#providerInfoId").append($("<option value='0'></option>").text("Select Provider"));
                $.ajax({
                  url : "providerInfoByPracticeInfo.do?practiceInfoId=" +practiceInfoId,
                  type : "GET",
                  contentType : "application/json; charset=utf-8",
                  success : function(t) {
                    var e = t.practiceInfoList;
                    var providerInfoValue=t.practiceInfoList[0];
                    for (i = 0; i < e.length; i++){
                    	var pracInfo=e[i]
                      $("#providerInfoId").append($("<option value='"+ pracInfo.providerId+ "'></option>").text(pracInfo.providerFirstName))
                    }
                    if(e.length==1){
        		           
        	            document.getElementById('providerInfoId').value=providerInfoValue.providerId;
        	            //here we are puting ajax code for practice dropdown
        	            }  
                    },
                  error : function() {
                  }
                })
                }else{
                  $("#providerInfoId").text(""), $("#providerInfoId").append($("<option value='0'></option>").text("Select Provider"));
                }
                },
              error : function() {
              }
            })
            }else{
              $("#practiceInfoId").text(""), $("#practiceInfoId").append($("<option value='0'></option>").text("Select Practice"));
            }
          },
          error : function() {
          }
        })
        }else{
          $("#practiceSpectialtyId").text(""), $("#practiceSpectialtyId").append($("<option value='0'></option>").text("Select Specialty"));
          $("#practiceInfoId").text(""), $("#practiceInfoId").append($("<option value='0'></option>").text("Select Practice"));
          $("#providerInfoId").text(""), $("#providerInfoId").append($("<option value='0'></option>").text("Select Provider"));
        }
      }),
      
      
      
      
      
      
      
      
      
      
      $("#practiceSpectialtyId").change(function() {
        var practiceSpectialtyId = $("#practiceSpectialtyId").val();
        var chearingHouseId = $("#chearingHouseId").val();
        if(practiceSpectialtyId!=0){
        $("#practiceInfoId").text(""), $("#practiceInfoId").append($("<option value='0'></option>").text("Select Practice"));
        $.ajax({
          url : "PracticeInfoBypracticeSpecialty.do?practiceSpectialtyId=" +practiceSpectialtyId+"&chearingHouseId="+chearingHouseId,
          type : "GET",
          contentType : "application/json; charset=utf-8",
          success : function(t) {
            var e = t.practiceInfoList;
            var practiceInfoValue=t.practiceInfoList[0];
            for (i = 0; i < e.length; i++){
            	var practiceInfo=e[i];
              $("#practiceInfoId").append($("<option value='"+ practiceInfo.practiceId+ "'></option>").text(practiceInfo.practiceName))
            }
            if(e.length==1){
		           
	            document.getElementById('practiceInfoId').value=practiceInfoValue.practiceId;
	            
	            
	            
	            var insurancename=document.getElementById('insuranceName').value;
	        	// alert(insurancename);
	            var practiceInfoId = $("#practiceInfoId").val();
	            var searchValuee='';
	            var flag=false;
	            $.ajax({
	                url :  "practiceInsuranceList.do?searchValue=" + searchValuee+"&practiceID="+practiceInfoId,
	                type : "GET",
	                contentType : "application/json; charset=utf-8",
	                success : function(t) {
	                	//alert("success");
	                	//alert(t);
	                	for (i = 0; i < t.length; i++){
	                		if(t[i]==insurancename){
	                		//	alert(t[i]);
	                			flag=true;
	                		}
	                	}
	                	if(flag){
	                		//no need to remove insurance
	                	}
	                	else{
	                		//ask to remove
	                		//alert(insurancename);
	                		if(insurancename!=''){
	                		 var insuranceVarification = document.getElementById("insuranceVarification");
	                		 insuranceVarification.style.display = "block";
	                		 document.getElementById('insname').innerHTML='Selected practice does not accept '+insurancename;
	                		}
	                		// alert(document.getElementById('insname').value);
	                	}
	                  },
	                error : function() {
	                	//alert("error");
	                }
	              })
	            
	            
	            
	            
	            
	            
	            //here we are puting ajax code for practice dropdown
	            }  
            var practiceInfoId = $("#practiceInfoId").val();
            if(practiceInfoId!=0){
            $("#providerInfoId").text(""), $("#providerInfoId").append($("<option value='0'></option>").text("Select Provider"));
            $.ajax({
              url : "providerInfoByPracticeInfo.do?practiceInfoId=" +practiceInfoId,
              type : "GET",
              contentType : "application/json; charset=utf-8",
              success : function(t) {
                var e = t.practiceInfoList;
                var providerInfoValue=t.practiceInfoList[0];
                for (i = 0; i < e.length; i++){
                	var pracInfo=e[i]
                  $("#providerInfoId").append($("<option value='"+ pracInfo.providerId+ "'></option>").text(pracInfo.providerFirstName))
                }
                if(e.length==1){
                	/* var callScheduleid=document.getElementById("callScheduleid");
            	   	   callScheduleid.style.display="block";*/
                	  document.getElementById("callScheduleid").disabled = false;
    	            document.getElementById('providerInfoId').value=providerInfoValue.providerId;
    	            document.getElementById("scheduleDate").value='';
    	        	document.getElementById("scheduleTime").value='';
    	        	document.getElementById("scheduleNotes").value='';
    	        	
    	        	document.getElementById("datetimepicker").value='';
    	        	document.getElementById("timeselect").value='';
    	        	document.getElementById("sheduleNotes").value='';
    	        	document.getElementById("scheduleFlag").value='false';
    	            
    	            
    	            
    	            
    	            
    	            //here we are puting ajax code for practice dropdown
    	            }  
                else{
                	/* var callScheduleid=document.getElementById("callScheduleid");
            	   	   callScheduleid.style.display="none";*/
                	  document.getElementById("callScheduleid").disabled = true;
            	   	 document.getElementById("scheduleDate").value='';
     	        	document.getElementById("scheduleTime").value='';
     	        	document.getElementById("scheduleNotes").value='';
     	        	
     	        	document.getElementById("datetimepicker").value='';
     	        	document.getElementById("timeselect").value='';
     	        	document.getElementById("sheduleNotes").value='';
     	       	document.getElementById("scheduleFlag").value='false';
                }
                },
              error : function() {
              }
            })
            }else{
            	/* var callScheduleid=document.getElementById("callScheduleid");
        	   	   callScheduleid.style.display="none";*/
            	  document.getElementById("callScheduleid").disabled = true;
        	   	 document.getElementById("scheduleDate").value='';
 	        	document.getElementById("scheduleTime").value='';
 	        	document.getElementById("scheduleNotes").value='';
 	        	
 	        	document.getElementById("datetimepicker").value='';
 	        	document.getElementById("timeselect").value='';
 	        	document.getElementById("sheduleNotes").value='';
 	       	document.getElementById("scheduleFlag").value='false';
              $("#providerInfoId").text(""), $("#providerInfoId").append($("<option value='0'></option>").text("Select Provider"));
            }
            },
          error : function() {
          }
        })
        }else{
//        	 var callScheduleid=document.getElementById("callScheduleid");
//    	   	   callScheduleid.style.display="none";
        	  document.getElementById("callScheduleid").disabled = true;
    	   	 document.getElementById("scheduleDate").value='';
	        	document.getElementById("scheduleTime").value='';
	        	document.getElementById("scheduleNotes").value='';
	        	
	        	document.getElementById("datetimepicker").value='';
	        	document.getElementById("timeselect").value='';
	        	document.getElementById("sheduleNotes").value='';
	        	document.getElementById("scheduleFlag").value='false';
          $("#practiceInfoId").text(""), $("#practiceInfoId").append($("<option value='0'></option>").text("Select Practice"));
          $("#providerInfoId").text(""), $("#providerInfoId").append($("<option value='0'></option>").text("Select provider"));
        }
      }),
      
      $("#providerInfoId").change(function() {
    	 // alert('providerInfoId');
    	  var providerInfoId=document.getElementById("providerInfoId").value;
    	  if(providerInfoId!=0){
    	 /* var callScheduleid=document.getElementById("callScheduleid");
   	   callScheduleid.style.display="block"; */
    		  document.getElementById("callScheduleid").disabled = false;
   	 document.getElementById("scheduleDate").value='';
 	document.getElementById("scheduleTime").value='';
 	document.getElementById("scheduleNotes").value='';
 	
 	document.getElementById("datetimepicker").value='';
 	document.getElementById("timeselect").value='';
 	document.getElementById("sheduleNotes").value='';
	document.getElementById("scheduleFlag").value='false';
    	  }
    	  else{
    	/*	  var callScheduleid=document.getElementById("callScheduleid");
    	   	   callScheduleid.style.display="none"; */
    		  document.getElementById("callScheduleid").disabled = true;
    	   	 document.getElementById("scheduleDate").value='';
	        	document.getElementById("scheduleTime").value='';
	        	document.getElementById("scheduleNotes").value='';
	        	
	        	document.getElementById("datetimepicker").value='';
	        	document.getElementById("timeselect").value='';
	        	document.getElementById("sheduleNotes").value='';
    	  }
      }),
      
      
      $("#practiceInfoId").change(function() {
    	  
    	 var insurancename=document.getElementById('insuranceName').value;
    	// alert(insurancename);
        var practiceInfoId = $("#practiceInfoId").val();
        var searchValuee='';
        var flag=false;
        $.ajax({
            url :  "practiceInsuranceList.do?searchValue=" + searchValuee+"&practiceID="+practiceInfoId,
            type : "GET",
            contentType : "application/json; charset=utf-8",
            success : function(t) {
            	//alert("success");
            	//alert(t);
            	for (i = 0; i < t.length; i++){
            		if(t[i]==insurancename){
            		//	alert(t[i]);
            			flag=true;
            		}
            	}
            	if(flag){
            		//no need to remove insurance
            	}
            	else{
            		//ask to remove
            		//alert(insurancename);
            		if(insurancename!=''){
            		 var insuranceVarification = document.getElementById("insuranceVarification");
            		 insuranceVarification.style.display = "block";
            		 document.getElementById('insname').innerHTML='Selected practice does not accept '+insurancename;
            		}
            		// alert(document.getElementById('insname').value);
            	}
              },
            error : function() {
            	//alert("error");
            }
          })
        
        
        
        if(practiceInfoId!=0){
        $("#providerInfoId").text(""), $("#providerInfoId").append($("<option value='0'></option>").text("Select provider"));
        $.ajax({
          url : "providerInfoByPracticeInfo.do?practiceInfoId=" +practiceInfoId,
          type : "GET",
          contentType : "application/json; charset=utf-8",
          success : function(t) {
            var e = t.practiceInfoList;
            var providerInfoValue=t.practiceInfoList[0];
            for (i = 0; i < e.length; i++){
            	var pracInfo=e[i]
              $("#providerInfoId").append($("<option value='"+ pracInfo.providerId+ "'></option>").text(pracInfo.providerFirstName))
            }
            if(e.length==1){
            	/* var callScheduleid=document.getElementById("callScheduleid");
      	   	   callScheduleid.style.display="block";*/
            	  document.getElementById("callScheduleid").disabled = false;
      	   	 document.getElementById("scheduleDate").value='';
	        	document.getElementById("scheduleTime").value='';
	        	document.getElementById("scheduleNotes").value='';
	        	
	        	document.getElementById("datetimepicker").value='';
	        	document.getElementById("timeselect").value='';
	        	document.getElementById("sheduleNotes").value='';
	        	document.getElementById("scheduleFlag").value='false';
	            document.getElementById('providerInfoId').value=providerInfoValue.providerId;
	            //here we are puting ajax code for practice dropdown
	            }  
            else{
            	/* var callScheduleid=document.getElementById("callScheduleid");
      	   	   callScheduleid.style.display="none"; */ 
            	
            	  document.getElementById("callScheduleid").disabled = true;
      	   	 document.getElementById("scheduleDate").value='';
	        	document.getElementById("scheduleTime").value='';
	        	document.getElementById("scheduleNotes").value='';
	        	
	        	document.getElementById("datetimepicker").value='';
	        	document.getElementById("timeselect").value='';
	        	document.getElementById("sheduleNotes").value='';
	        	document.getElementById("scheduleFlag").value='false';
            }
            },
          error : function() {
          }
        })
        }else{
          $("#providerInfoId").text(""), $("#providerInfoId").append($("<option value='0'></option>").text("Select Provider"));
         /* var callScheduleid=document.getElementById("callScheduleid");
 	   	   callScheduleid.style.display="none";*/
          document.getElementById("callScheduleid").disabled = true;
 	   	 document.getElementById("scheduleDate").value='';
     	document.getElementById("scheduleTime").value='';
     	document.getElementById("scheduleNotes").value='';
     	
     	document.getElementById("datetimepicker").value='';
     	document.getElementById("timeselect").value='';
     	document.getElementById("sheduleNotes").value='';
    	document.getElementById("scheduleFlag").value='false';
        }
      }),
      /*$("#search").click(function() {
        var searchKey = $("#lastName").val();
        $.ajax({
          url : "patientSearchAction.do?searchKeyword=" + searchKey,
          type : "GET",
          contentType : "application/json; charset=utf-8",
          success : function(t) {
            var e= t.practiceInfoList;
            showData=e;
            $('#showDataTable').append('<table width="100%" border="1" cellpadding="0" cellspacing="0"></table>');
            var table = $('#showDataTable').children();
            table.append("<tr class='first-row-heading-table'><td>SNO</td><td>First Name</td><td>DOB</td><td>SSN</td></tr>");
            for (i = 0; i < e.length; i++)
              {
              table.append($("<tr><td><a href='#' onclick='openSearchPage("+e[i].patientId+")'>"+e[i].patientId+"</a></td><td>"+e[i].patientFirstName+"</td><td>"+e[i].patientDob+"</td><td>"+e[i].patientSSN+"</td></tr>"));
              }
            
          },
          error : function() {
          }
        })//'id='+ encodeURIComponent(id) + '&name='+ encodeURIComponent(name)
      })*/
      $("#search").click(function() {
    		
        var searchKey = $("#lastName").val();
        var patientDateOb=$("#patientDateOb").val();
      //  var patientSsn=$("#patientSsn").val();
    /*    if(searchKey!="" && patientDateOb!=""){*/
        
      
        if(searchKey!="" ||patientDateOb!=""){
        	
        	var ShowAllPatientFields = document.getElementById("allDetails");
        	  ShowAllPatientFields.style.display = "block";
        	
        	        $.ajax({
          url : "patientSearchAction.do?searchKeyword=" + encodeURIComponent(searchKey) + '&dob='+ encodeURIComponent(patientDateOb),
          type : "GET",
          contentType : "application/json; charset=utf-8",
          success : function(t) {
            var e= t.practiceInfoList;
            showData=e;
            
            //vipul code
          
            var d= t.allInsDocList;
            showDocData=d;
            $('#showDocTable').empty();
            
            
            
            $('#showDataTable').empty();
        	
        	
          if(e==''){
        	  var patitentName1 = document.getElementById('patientName');
        		
            	patitentName1.value="";
      			var patitentLastName1 = document.getElementById('patientLastName');
      			patitentLastName1.value="";
      			var phone1 = document.getElementById('patientPhone');
      			phone1.value="";
      			var gender1 = document.getElementById('gender');
      		
      			gender1.value='G';
      			var dob1 = document.getElementById('dob');
      			dob1.value='';
      			var emailId1 = document.getElementById('emailId');
      			emailId1.value="";
      			var insuranceName1 = document.getElementById('insuranceName');
      			insuranceName1.value="";
      			var insuranceId1 = document.getElementById('insuranceId');
      			insuranceId1.value="";
      			var group1 = document.getElementById('group');
      			group1.value="";
      			gender1.disabled = false;
      			dob1.disabled = false;
      			patitentName1.disabled = false;
      			patitentLastName1.disabled = false;
      			insuranceName1.disabled = false;
          }
          
          if(e!=''){
            if(e.length>1){
            	
            if(showData!=null && showData!=""){
            	
            $('#showDataTable').append('<table width="100%" border="1" cellpadding="0" cellspacing="0"></table>');
          
            var table = $('#showDataTable').children();
            table.append("<tr class='first-row-heading-table'><td>SNO</td><td>Patient Name</td><td>DOB</td></tr>");
            
            }
            if(e!=null && e!=""){
            	
            for (i = 0; i < e.length; i++)
              {
              table.append($("<tr><td><a href='#' onclick='openSearchPage("+e[i].patientId+")'>"+e[i].patientId+"</a></td><td><a href='#' onclick='openSearchPage("+e[i].patientId+")'> "+e[i].patientFirstName +" "+e[i].patientLastName +"</a>"+"</td><td>"+e[i].patientDob+"</td></tr>"));
              }
            document.getElementById('patientName').value="";
            document.getElementById('patientLastName').value="";
            document.getElementById('dob').value="";
            document.getElementById('patientPhone').value="";
            document.getElementById('gender').value='G';
            document.getElementById('emailId').value="";
            document.getElementById('insuranceName').value="";
            document.getElementById('insuranceId').value="";
            document.getElementById('group').value="";
            document.getElementById('patientName').disabled = false;
            document.getElementById('patientLastName').disabled = false;
            document.getElementById('dob').disabled = false;
            document.getElementById('gender').disabled = false;
            document.getElementById('insuranceName').disabled = false;
            }
            }
          }
          if(e!=''){
            if(e.length==1){
            	//vipul code
            	 if(d!=undefined){
            	 $('#showDataTable').empty();
                 if (showDocData != null && showDocData != "") {
                    $('#showDocTable').append('<table width="100%" border="0" cellpadding="0" cellspacing="0"></table>');
                          var doctable = $('#showDocTable').children();
                         // doctable.append("<tr class='first-row-heading-table'><td>Insurance Documents</td></tr>");
                  
                   for(var i = 0, size = showDocData.length; i < size ; i++){
                          for(var j = 0, size1 = showDocData[i].length; j < size1 ; j++){
                          
                       //  table.append($("<tr><td><a href='#' onclick='openSearchPage("+e[i].patientId+")'>"+e[i].patientId+"</a></td><td><a href='#' onclick='openSearchPage("+e[i].patientId+")'> "+e[i].patientFirstName +" "+e[i].patientLastName +"</a>"+"</td><td>"+e[i].patientDob+"</td></tr>"));
                       //  $('#showInsDocs').html('<a href=download.do?filePath=' + docattacheList.docPath + '&fileName='+docattacheList.docName+'>Products</a>');
                         doctable.append($('<tr><td><a href=download.do?filePath=' + showDocData[i][j].docPath + '&fileName='+showDocData[i][j].docName+'>'+showDocData[i][j].docName+'</a></td></tr>'))
                          }
                          } 

                  
                 }
            	 }
        	if (showData != null && showData != "") {
    			
    			var patitentName = document.getElementById('patientName');
    			var patitentLastName = document.getElementById('patientLastName');
    			var phone = document.getElementById('patientPhone');
    			var gender = document.getElementById('gender');
    			var dob = document.getElementById('dob');
    			var emailId = document.getElementById('emailId');
    			/* var address1 = document.getElementById('patientAddress1');
    			var address2 = document.getElementById('patientAddress2');
    			var city = document.getElementById('patientCity');
    			var state = document.getElementById('PatientState');
    			var zip = document.getElementById('patientZip');
    			
    			var fax = document.getElementById('patientFax');
    			
    			var ssn1 = document.getElementById('ssn1'); */
    			
    			/*  var ssn1=document.getElementById('ssn1').disabled = true;  */
    			for (i = 0; i < showData.length; i++) {
    				locId.value = showData[i].radLocation.locId;
    				patientId.value = showData[i].patientId;
    				//PatientState.value=showData[i].radLocation.redState.stateId;
    				patientIdHidden.value = showData[i].patientId;
    				if (showData[i].patientId == e[i].patientId) {
    					patitentName.value = showData[i].patientFirstName;
    					patitentLastName.value = showData[i].patientLastName;
    					gender.value = showData[i].patientGender;
    					dob.value = showData[i].patientDob;
    					phone.value = showData[i].radLocation.locPhone;
    					emailId.value = showData[i].patientEmail;
    					
    				//	address1.value = showData[i].radLocation.locAddress1;
    				//	address2.value = showData[i].radLocation.locAddress2;
    				//	city.value = showData[i].radLocation.locCity;
    				//	zip.value = showData[i].radLocation.locZip;
    					
    				//	fax.value = showData[i].radLocation.locFax;
    					
    			//		ssn1.value = showData[i].patientSSN;
    					
    				//	state.value = showData[i].radLocation.redState.stateId;
    				//	ssn1.disabled = true;
    					gender.disabled = true;
    					dob.disabled = true;
    					patientName.disabled = true;
    					patientLastName.disabled = true;
    					
    				}
    			}
    			var insuranceId = document.getElementById('insuranceId');
    			var group = document.getElementById('group');
    			var insurancePhone = document.getElementById('insurancePhone');
    			var insuranceName = document.getElementById('insuranceName');
    			
    			/* var preAuth = document.getElementById('preAuth');
    			var PreAuthDate = document.getElementById('PreAuthDate');
    			var toDate = document.getElementById('toDate');
    			var preAuthContachNo = document.getElementById('preAuthContachNo');
    			var notes = document.getElementById('notes');
    			var pokemonRed = document.getElementById('pokemonRed');
    			var pokemonBlue = document.getElementById('pokemonBlue'); */
    			
    			
    			//var confirmation = document.getElementById('confirmation');
    			if (showData != null && showData != "") {
    				for (i = 0; i < showData.length; i++) {
    					if (showData[i].patientId == e[i].patientId) {
    						var a = showData[i].patientInsuranceInfoList;
    						insuranceId.value = a[0].patientInsuranceId;
    						group.value = a[0].patientInsuranceGroup;
    					//	insurancePhone.value = a[0].patientInsurancePhone;
    					//	preAuth.value = a[0].patientPreAuthId;

    						//TODO :- Create a separte function for get Date in MM/DD/YYYY format 
    						var showStartDate = new Date(
    								a[0].patientPreAuthStartDate);
    						var newStartDate = showStartDate.getMonth()+1 + "/"
    								+ showStartDate.getDate() + "/"
    								+ showStartDate.getFullYear();

    						var showEndDate = new Date(a[0].patientPreAuthEndDate);
    						var newEndDate = showEndDate.getMonth()+1 + "/"
    								+ showEndDate.getDate() + "/"
    								+ showEndDate.getFullYear();

    						/* PreAuthDate.value = newStartDate;
    					toDate.value = newEndDate;

    						preAuthContachNo.value = a[0].patientPreAuthContactName;
    						notes.value = a[0].patientInsuranceNotes; */
    						
    						/* if (a[0].patientPre1AuthReq == 'Y') {
    							
    							$('#pokemonRed').attr('checked', true);
    							document.getElementById('preAuth').disabled = false;
    							document.getElementById('PreAuthDate').disabled = false;
    							document.getElementById('toDate').disabled = false;
    							document.getElementById('preAuthContachNo').disabled = false;
    							  document.getElementById('confirmation').disabled = false;
    							  var obj103=document.getElementById("preAuth");
    								obj103.style.display="block"; 
    								var obj104=document.getElementById("PreAuthDate");
    								obj104.style.display="block"; 
    								var obj105=document.getElementById("toDate");
    								obj105.style.display="block"; 
    								var obj106=document.getElementById("preAuthContachNo");
    								obj106.style.display="block"; 
    								var obj107=document.getElementById("confirmation");
    					/* 			obj107.style.display="block";  */
    						/* } else {
    							$('#pokemonBlue').attr('checked', true);
    							 var obj103=document.getElementById("preAuth");
    								obj103.style.display="none"; 
    								var obj104=document.getElementById("PreAuthDate");
    								obj104.style.display="none"; 
    								var obj105=document.getElementById("toDate");
    								obj105.style.display="none"; 
    								var obj106=document.getElementById("preAuthContachNo");
    								obj106.style.display="none"; 
    								var obj107=document.getElementById("confirmation");
    								obj107.style.display="none"; 
    						}  */
    						insuranceName.value = a[0].insuranceInfo.insuranceCompany;
    						
    						insuranceIdHideen.value = a[0].insuranceInfo.insuranceId;
    						patientInsuranceInfoIdHidden.value = a[0].patientInsuranceInfoId;
    						insuranceName.disabled = true;
    					}
    				}
    			}

    			var obj4 = document.getElementById("div2");
    			obj4.style.display = "true";
    			var obj5 = document.getElementById("div3");
    			obj5.style.display = "block";
    			/* var white_content=document.getElementById("white_content");
    			white_content.style.display="block"; */
    		} 
          }
          }
            
            var obj22=document.getElementById("demo2");
               obj22.style.display="none";
            /*else{
              
               $('#demo2').append('<div></div>').html("Please give any one information ");
                  var obj2=document.getElementById("div2");
                 obj2.style.display="true";
            }*/
            
          },
          error : function() {
        	       var table = $('#showDataTable').children();
        	       table.hide();
            	var patitentName1 = document.getElementById('patientName');
            	patitentName1.value="";
    			var patitentLastName1 = document.getElementById('patientLastName');
    			patitentLastName1.value="";
    			var phone1 = document.getElementById('patientPhone');
    			phone1.value="";
    			var gender1 = document.getElementById('gender');
    			gender1.value='G';
    			var dob1 = document.getElementById('dob');
    			dob1.value='';
    			var emailId1 = document.getElementById('emailId');
    			emailId1.value="";
    			var insuranceName1 = document.getElementById('insuranceName');
    			insuranceName1.value="";
    			var insuranceId1 = document.getElementById('insuranceId');
    			insuranceId1.value="";
    			var group1 = document.getElementById('group');
    			group1.value="";
    			
    			patitentName1.disabled = false;
    			patitentLastName1.disabled = false;
    			gender1.disabled = false;
				dob1.disabled = false;
				patientName1.disabled = false;
				patientLastName1.disabled = false;
				insuranceName1.disabled = false;
    		
    			/*  var ShowAllPatientFields = document.getElementById("allDetails");
            	  ShowAllPatientFields.style.display = "none";*/
          }
        })//close ajax
        }//if
        else{
        	   var ShowAllPatientFields = document.getElementById("allDetails");
            	  ShowAllPatientFields.style.display = "none";
          $('#demo2').append('<div></div>').html("Please give any one information ");
            var obj2=document.getElementById("div2");
           obj2.style.display="true";
        
         
        }
    /* return true;
        }else{
    	  $('#demo2').append('<div></div>').html("Please Provide Both LastName & DOB!");
          var obj2=document.getElementById("div2");
         obj2.style.display="true";
         return false;
      }*/
      })
});


//////////////////////
function Refralreasonvalidation()
{
  var chearingHouseId=document.getElementById("chearingHouseId").value;
    var practiceSpectialtyId=document.getElementById("practiceSpectialtyId").value;
    var PrvoRederalReason=document.getElementById("PrvoRederalReason").value;
    var providerID=document.getElementById("providerID").value;
   
   if(chearingHouseId!=0&&practiceSpectialtyId!=0&&PrvoRederalReason!=null&&providerID!=0)
      {
 return true;
      }
    else{
    	if(providerID==0){
    		var obj4 = document.getElementById("providerdropdowm");
    		obj4.style.display = "block";
    		}
    	
$('#demo10').append('<div></div>').html("Please fill the required fields.");
var obj10=document.getElementById("warningmsg");
obj10.style.display="true"; 
return false;
       }

}
function Refralreasonvalidation1()
{
	 var referring=document.getElementById("referring").value;
	 var address1=document.getElementById("address1").value;
	
      var chearingHouseId=document.getElementById("chearingHouseId").value;
        var practiceSpectialtyId=document.getElementById("practiceSpectialtyId").value;
        
    /*    var gender=document.getElementById("gender").value;*/
        
        
      
       
       if(referring==0)
          {
    	   $('#demo11').append('<div></div>').html("Please Select a Referring.");
    	    var obj10=document.getElementById("warningmsgreferpatient11");
    	    obj10.style.display="true"; 
    	    return false;
          }
        
       if(address1==0)
       {
 	   $('#demo11').append('<div></div>').html("Please Select an Address.");
 	    var obj10=document.getElementById("warningmsgreferpatient11");
 	    obj10.style.display="true"; 
 	    return false;
       }
       if(chearingHouseId==0)
       {
 	   $('#demo11').append('<div></div>').html("Please select an Clearing House ID.");
 	    var obj10=document.getElementById("warningmsgreferpatient11");
 	    obj10.style.display="true"; 
 	    return false;
       }
       if(practiceSpectialtyId==0)
       {
 	   $('#demo11').append('<div></div>').html("Please select an Practice speciality.");
 	    var obj10=document.getElementById("warningmsgreferpatient11");
 	    obj10.style.display="true"; 
 	    return false;
       }
      /* if(gender=='G')
       {
 	   $('#demo11').append('<div></div>').html("Please select a Gender");
 	    var obj10=document.getElementById("warningmsgreferpatient11");
 	    obj10.style.display="true"; 
 	    return false;
       }*/
       return true;

}



function formatPhoneNumber() {
	
	if(event.keyCode==8){
		return null;
	}
	
	var phoneNumber=document.getElementById("patientPhone").value;
    var rawPhoneNumber = phoneNumber.replace("(", "").replace(")", "").replace(/-/g, "").replace(/ /g, "");
    if (isNaN(rawPhoneNumber)) {
        return null;
    }
    if (rawPhoneNumber.length >3 && rawPhoneNumber.length<6) {
    	document.getElementById("patientPhone").value=rawPhoneNumber.substring(0, 3) + "-" + rawPhoneNumber.substring(3, 6) 
    	
    }
    if (rawPhoneNumber.length >=6) {
    	//alert("ghkasjhd="+ rawPhoneNumber.substring(0, 3) + "-" + rawPhoneNumber.substring(3, 6) + "-" + rawPhoneNumber.substring(6, 10));
    	document.getElementById("patientPhone").value=rawPhoneNumber.substring(0, 3) + "-" + rawPhoneNumber.substring(3, 6) + "-" + rawPhoneNumber.substring(6, 10);
    }
   
}



function formatDate() {
	
	if(event.keyCode==8){
		return null;
	}
	
	var dob=document.getElementById("dob").value;
    var rawdob = dob.replace("(", "").replace(")", "").replace("/", "").replace(/ /g, "");
    if (isNaN(rawdob)) {
        return null;
    }
    if (rawdob.length >2 && rawdob.length<4) {
    	document.getElementById("dob").value=rawdob.substring(0, 2) + "/" + rawdob.substring(2, 4) 
    	
    }
    if (rawdob.length >=4) {
    	//alert("ghkasjhd="+ rawPhoneNumber.substring(0, 3) + "-" + rawPhoneNumber.substring(3, 6) + "-" + rawPhoneNumber.substring(6, 10));
    	document.getElementById("dob").value=rawdob.substring(0, 2) + "/" + rawdob.substring(2, 4) + "/" + rawdob.substring(4, 8);
    }
   
}

function HidePatientInfo(){
	
}

function callSchedule(){
	//alert('hi');
	var schedulefull = document.getElementById("shaduleModal");
	schedulefull.style.display = "block";
	var provID=document.getElementById("providerInfoId").value;
	//alert('hi=>'+providerInfoId);
    $.ajax({
        url : "getproviderDayOffDetails.do?practiceInfoId="+provID,
        type : "GET",
        contentType : "application/json; charset=utf-8",
        success : function(t) {
        var e=t.fullAddressByAddress1;
        for(var i=0;i<7;i++){
        // alert('success'+e[i].dayID);
        // alert('is off=>'+e[i].checkBox);
         if(e[i].checkBox){
         document.getElementById("day"+e[i].dayID).checked=false;
         }else{
        	 document.getElementById("day"+e[i].dayID).checked=true;
         }
         
        }
          },
        error : function() {
        }
      })
	
}

function saveShedule(){
	//alert('hi');
	var Date=document.getElementById("datetimepicker").value;
	var Time=document.getElementById("timeselect").value;
	var notes=document.getElementById("sheduleNotes").value;
	//alert('hi'+Date+Time+notes);
	
	if(Date==''||Time==''||notes==''){
		//alert('hi false'+Date+Time+notes);
		return false;
	}
	
	document.getElementById("scheduleDate").value=Date;
	document.getElementById("scheduleTime").value=Time;
	document.getElementById("scheduleNotes").value=notes;
	document.getElementById("scheduleFlag").value='true';
	
	document.getElementById("datetimepicker").value='';
	document.getElementById("timeselect").value='';
	document.getElementById("sheduleNotes").value='';
	
	var schedulefull = document.getElementById("shaduleModal");
	schedulefull.style.display = "none";

	
	return true;
}

function dateselected(){
	
	var Date=document.getElementById("datetimepicker").value;
	var provID=document.getElementById("providerInfoId").value;
	//alert('hi='+Date);
	   $.ajax({
	        url : "getProviderTimeSlots.do?practiceInfoId="+provID+"&scheduleDate="+Date,
	        type : "GET",
	        contentType : "application/json; charset=utf-8",
	        success : function(t) {
	      // alert('success');
	        	var timinglist=t.timings;
	        	//alert(timinglist);
	        	
	        	/*var select = document.getElementById("timeselect");
	        	var length = select.options.length;
	        	alert(length);
	        	for (i = 0; i < length; i++) {
	        	  select.options[i] = null;
	        	}*/
	        	
	        	$('#timeselect').empty();
	        	
	        	 for (var cur = 0; cur < timinglist.length; cur++){
	        	
	        	$("#timeselect").append($("<option value='"+timinglist[cur]+"'></option>").text(timinglist[cur]));
	        	
	        	 }
	        	 
	        	
	        	
	          },
	        error : function() {
	        	//alert('error');
	        }
	      })
	
}
