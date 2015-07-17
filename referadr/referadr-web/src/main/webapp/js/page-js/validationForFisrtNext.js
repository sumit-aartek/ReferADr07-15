 var tcount=0;
  function stoppedTyping(){
	 
	if(tcount!=0){tcount=0;}
	
	var date=document.getElementById("date").value;
	if(date!=""){
	tcount=tcount+1;
	}
	var referring=document.getElementById("referring").value;
	if(referring!=""){tcount=tcount+1;}
	var address1=document.getElementById("address1").value;
	if(address1!=""){tcount=tcount+1;}
	var address2=document.getElementById("address2").value;
	if(address2!=""){tcount=tcount+1;}
	var city=document.getElementById("city").value;
	if(city!=""){tcount=tcount+1;}
	var state=document.getElementById("state").value;
	if(state!=""){tcount=tcount+1;}
	var zip=document.getElementById("zip").value;
	if(zip!=""){tcount=tcount+1;}
	var phone=document.getElementById("phone").value;
	if(phone!=""){tcount=tcount+1;}
	var fax=document.getElementById("fax").value;
	if(fax!=""){tcount=tcount+1;}
	if(tcount==9) { 
            document.getElementById('start_button').disabled = false; 
        } else { 
	        document.getElementById('start_button').disabled = true;
        }
    }
  
   
  
function verify(){
		if(tcount==9){ tcount=0;openBox2();}

    }
	
	
  function stoppedTyping1(){
	if(tcount!=0){tcount=0;}
	
	var patientLastName=document.getElementById("patientLastName").value;
	if(patientLastName!=""){tcount=tcount+1;}
	var dateOfBirth=document.getElementById("dateOfBirth").value;
	if(dateOfBirth!=""){tcount=tcount+1;}
	var ssn=document.getElementById("ssn").value;
	if(ssn!=""){tcount=tcount+1;}
	if(tcount>0) { 
	        document.getElementById('margin-right').disabled = false; 
        } else { 
	        document.getElementById('margin-right').disabled = true;
        }
	if(tcount==3) { 
            document.getElementById('start_button').disabled = false; 
        } else { 
	        document.getElementById('start_button').disabled = true;
        }
    }
function verify1(){
		if(tcount==3){openBox3();}
    }
 function verifyField(){
	
	  if(tcount==0){alert("Please Fill At Least One");}
	  }
	
	
	  function stoppedTyping2(){
	if(tcount!=0){tcount=0;}
	
	var patientName=document.getElementById("patientName").value;
	if(patientName!=""){tcount=tcount+1;}
	var address1=document.getElementById("address1").value;
	if(address1!=""){tcount=tcount+1;}
	var address2=document.getElementById("address2").value;
	if(address2!=""){tcount=tcount+1;}
	var city=document.getElementById("city").value;
	if(city!=""){tcount=tcount+1;}
	var state=document.getElementById("state").value;
	if(state!=""){tcount=tcount+1;}
	var zip=document.getElementById("zip").value;
	if(zip!=""){tcount=tcount+1;}
	var phone=document.getElementById("phone").value;
	if(phone!=""){tcount=tcount+1;}
	var fax=document.getElementById("fax").value;
	if(fax!=""){tcount=tcount+1;}
	var gender=document.getElementById("gender").value;
	if(gender!=""){tcount=tcount+1;}
	var dob=document.getElementById("dob").value;
	if(dob!=""){tcount=tcount+1;}
	var ssn1=document.getElementById("ssn1").value;
	if(ssn1!=""){tcount=tcount+1;}

	
	if(tcount==11) { 
            document.getElementById('start_button').disabled = false; 
        } else { 
	        document.getElementById('start_button').disabled = true;
        }
    }
function verify2(){
		if(tcount==11){openBox4();}

    }
	
	function stoppedTyping3(){
	if(tcount!=0){tcount=0;}
	
	var insuranceName=document.getElementById("insuranceName").value;
	if(insuranceName!=""){tcount=tcount+1;}
	var address1=document.getElementById("address1").value;
	if(address1!=""){tcount=tcount+1;}
	var address2=document.getElementById("address2").value;
	if(address2!=""){tcount=tcount+1;}
	var city=document.getElementById("city").value;
	if(city!=""){tcount=tcount+1;}
	var state=document.getElementById("state").value;
	if(state!=""){tcount=tcount+1;}
	var zip=document.getElementById("zip").value;
	if(zip!=""){tcount=tcount+1;}
	var phone=document.getElementById("phone").value;
	if(phone!=""){tcount=tcount+1;}
	var fax=document.getElementById("fax").value;
	if(fax!=""){tcount=tcount+1;}
	var gender=document.getElementById("gender").value;
	if(gender!=""){tcount=tcount+1;}
	var dob=document.getElementById("dob").value;
	if(dob!=""){tcount=tcount+1;}
	var ssn1=document.getElementById("ssn1").value;
	if(ssn1!=""){tcount=tcount+1;}

	
	if(tcount==11) { 
            document.getElementById('start_button').disabled = false; 
        } else { 
	        document.getElementById('start_button').disabled = true;
        }
    }
function verify3(){
		if(tcount==11){openBox5();}

    }