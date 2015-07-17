function openBox1()
{
$("#scroll").show();
var obj1=document.getElementById("div1");
obj1.style.display="block";
/*var white_content=document.getElementById("white_content");
alert("open box1="+white_content)
white_content.style.display="block";*/
}

function PreviousopenBox1(){
var objp3=document.getElementById("div2");
objp3.style.display="none";
var obj1=document.getElementById("div1");
obj1.style.display="block";
/*var white_content=document.getElementById("white_content");
white_content.style.display="block";*/
}

function openBox2()
{
    document.getElementById("statId").value =document.getElementById("state").value;
    //var date=document.getElementById("date").value;
    var referring=document.getElementById("referring").value;
    var address1=document.getElementById("address1").value;
    var address2=document.getElementById("address2").value;
    var city=document.getElementById("city").value;
    var state=document.getElementById("state").value;
    var zip=document.getElementById("zip").value;
    var phone=document.getElementById("phone").value;
    var fax=document.getElementById("fax").value;
    if(referring!=0&&address1!=0&&address2!=null&&city!=null&&state!=null&&zip!=null&&phone!=null&&fax!=null)
      {
  /*if(document.getElementById("white_content")!=null && document.getElementById("white_content")!=""){
alert("byee");*/
    var obj2=document.getElementById("div1");
    obj2.style.display="none";

    var obj3=document.getElementById("div2");
    obj3.style.display="block";
      }
    else{
      
       $('#demo').append('<div></div>').html("Please select both options");
        var obj2=document.getElementById("div1");
       obj2.style.display="true";
       }
/*var white_content=document.getElementById("white_content");
alert(white_content);
white_content.style.display="block";*/
/*  }*/
}

function PreviousopenBox2()
{
var obj5=document.getElementById("div3");
obj5.style.display="none";
var obj3=document.getElementById("div2");
obj3.style.display="block";
/*var white_content=document.getElementById("white_content");
white_content.style.display="block";*/
}

function openBox3()
{
var obj4=document.getElementById("div2");
obj4.style.display="none";
$("#patientName").val('');
$("#patientLastName").val('');
$("#patientAddress1").val('');
$("#patientAddress2").val('');
$("#patientCity").val('');
$("#PatientState").val('');

$("#patientZip").val('');
$("#patientPhone").val('');
$("#patientFax").val('');
$("#gender").val('');

$("#dob").val('');
$("#ssn1").val('');

$("#insuranceId").val('');
$("#group").val('');
$("#insurancePhone").val('');
$("#preAuth").val('');
$("#PreAuthDate").val('');
$("#toDate").val('');
$("#preAuthContachNo").val('');
$("#notes").val('');
$('#pokemonRed').attr('checked', false);
$('#pokemonBlue').attr('checked', false);
$("#insuranceName").val('');
$("#confirmation").val('');

ssn1.disabled = false;
dob.disabled = false;
gender.disabled = false;
patientName.disabled = false;
patientLastName.disabled = false;
var obj5=document.getElementById("div3");
obj5.style.display="block";
/*var white_content=document.getElementById("white_content");
white_content.style.display="block";
*/
}

function PreviousopenBox3()
{
var obj5=document.getElementById("div4");
obj5.style.display="none";
var obj5=document.getElementById("div3");
obj5.style.display="block";
/*var white_content=document.getElementById("white_content");
white_content.style.display="block";*/
}
/*function openBox4()
{
var obj4=document.getElementById("div3");
obj4.style.display="none";

var obj5=document.getElementById("div4");
obj5.style.display="block";
var white_content=document.getElementById("white_content");
white_content.style.display="block";
}*/

function PreviousopenBox4()
{
var obj5=document.getElementById("div5");
obj5.style.display="none";
var obj4=document.getElementById("div4");
obj4.style.display="block";
/*var white_content=document.getElementById("white_content");
white_content.style.display="block";*/
}


/*function openBox5()
{
var obj4=document.getElementById("div4");
obj4.style.display="none";
var obj5=document.getElementById("div5");
obj5.style.display="block";
var white_content=document.getElementById("white_content");
white_content.style.display="block";
}*/
function openBox5()
{
	 var insuranceName=document.getElementById('insuranceName').value;
	
	   var insuranceId=document.getElementById('insuranceId').value;
	 
		var group=document.getElementById('group').value;
	
		var pokemonRed=document.getElementById('pokemonRed').checked;
		
		if(pokemonRed){
			
			 var preAuth=document.getElementById('preAuth').value;
			   var PreAuthDate=document.getElementById('PreAuthDate').value;
				var toDate=document.getElementById('toDate').value;
				var preAuthContachNo=document.getElementById('preAuthContachNo').value;
				var confirmation=document.getElementById('confirmation').value;
				//var notes=document.getElementById('notes').value;
				if(preAuth=="" || PreAuthDate=="" || toDate=="" || preAuthContachNo=="" || confirmation=="" || insuranceName=="" || insuranceId=="" || group=="")
				{
				$('#demo102').append('<div></div>').html("Please fill the required fields.");
				var obj102=document.getElementById("div4");
				obj102.style.display="true"; 
			
				return false;
				}
				else{
					
					var obj4=document.getElementById("div4");
					obj4.style.display="none";
					var obj5=document.getElementById("div5");
					obj5.style.display="block";
					return true;
				}
		    }
		else{
		
			if(insuranceName=="" || insuranceId=="" || group=="")
			{
			$('#demo102').append('<div></div>').html("Please fill the required fields.");
			var obj102=document.getElementById("div4");
			obj102.style.display="true"; 
			
			return false;
			}
			else{	
				
			var obj4=document.getElementById("div4");
			obj4.style.display="none";
			var obj5=document.getElementById("div5");
			obj5.style.display="block";
			return true;
			}
		}			
		/* var pokemonBlueRadio=document.getElementById('pokemonBlueRadio').checked;
		alert("noRadio"+pokemonBlueRadio) */
	
	
/*var white_content=document.getElementById("white_content");
white_content.style.display="block";*/
}
function closeDiv()
{
var obj;
obj=document.getElementById("div3");
obj.style.display="none";
/*var white_content=document.getElementById("white_content");
white_content.style.display="none";*/
}