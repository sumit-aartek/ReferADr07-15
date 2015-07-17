<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
function fillData(){
	
	
	
	<c:forEach items="${selectedLabSerivesList}" var="labServices">
	
	$("#labServiceId_${labServices.lab_service_map_id}").prop('checked',true);
	</c:forEach>
	
	<c:forEach items="${selectedLabSubSerivesList}" var="labSubServices">

	$("#labSubServiceId_${labSubServices.lab_sub_service_map_id}").prop('checked',true);
	</c:forEach>
	
	

document.getElementById('labICDNote').value='${patientReferralInfo2.labICDNote}';
document.getElementById('labNotes').value='${patientReferralInfo2.labNotes}';
document.getElementById('labRefPhy').value='${patientReferralInfo2.labRefPhy}';
	
}



</script>




</head>

<body onload="fillData()">
	  <div id="content-right-main" class="small-12 medium-12 large-10 columns">
	  	

		

		<div id="main-content-full" class="small-12 medium-12 large-12 columns">


		
          <form method="post" class="checkbox-req">
		  
		  	<div class="small-12 medium-12 large-6 columns">
            <div class="left-form-heading">
                <input id="checkbox187" type="checkbox">
                <label for="checkbox187"><h3>Complete Wellness Baseline 1L,3TT</h3>
                <p>(Perform All panels Listed Below)</p></label>
                
            </div><!----left-form-heading--------->
            </div>
            
            <div class="small-12 medium-12 large-6 columns">
            <div class="right-form-heading">
                <input id="checkbox188" type="checkbox">
                <label for="checkbox188"><h3>Complete Wellness Followup 1L,3TT</h3>
                <p>(Perform All panels Listed Below)</p></label>
                
            </div><!------right-form-heading------->
            </div>
            <div class="space-padding-margin"></div><!------space-padding-margin--------->
              
             							<div id="mycustompadding"
								class="small-12 medium-12 large-5 columns custom-1">
								<div id="hematology" class="small-12 medium-4 large-4 columns">
									<div class="hematology-check">
										<div class="bg-label">
											<input id="labServiceId_2"   name="lab_service_desc" value="2" type="checkbox"><label
												for="checkbox1">Hematology</label>
										</div>
										<!--------bg-label----->
										<div class="hema-check">
											<input id="labSubServiceId_22"   name="lab_sub_service_desc" value="22" type="checkbox"><label
												for="checkbox2">CBC</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_23"   name="lab_sub_service_desc" value="23" type="checkbox"><label
												for="checkbox3">Ferritin</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_24"   name="lab_sub_service_desc" value="24" type="checkbox"><label
												for="checkbox4">Vitamin B12</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_25"   name="lab_sub_service_desc" value="25" type="checkbox"><label
												for="checkbox5">Vitamin D</label>
											<div class="clear-both"></div>
											<label>1L, 1TT</label>
										</div>
										<!-----hema-check------->
									</div>
									<!---------hematology check------>
									<div class="inflammation">
										<div class="inlammation-heading">
											<input id="labServiceId_3"   name="lab_service_desc" value="3" type="checkbox"><label
												for="checkbox6">Inflammation</label>
										</div>
										<div class="left-inflam-check">
											<input id="labSubServiceId_26"   name="lab_sub_service_desc" value="26" type="checkbox"><label
												for="checkbox7">CRP</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_27"   name="lab_sub_service_desc" value="27" type="checkbox"><label
												for="checkbox8">Homosysteine</label>
										</div>
										<!--------left inflam check----->
										<div class="right-inflam-check">
											<label>1TT</label>
										</div>
										<!------right inflam check-------->
									</div>
									<!-----hema-check------->
								</div>
								<div id="metabolic" class="small-12 medium-8 large-8 columns">
									<div class="metabolic-check">
										<div class="bg-label-1">
											<input id="labServiceId_1"   name="lab_service_desc" value="1" type="checkbox"><label
												for="labServiceId_1">Metabolic</label>
										</div>
										<!--------bg-label----->
										<div id="meta-left" class="small-6 medium-6 large-6 columns">
											<input id="labSubServiceId_1"   name="lab_sub_service_desc" value="1" type="checkbox"><label
												for="labSubServiceId">Renal Function</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_2"   value="2" name="lab_sub_service_desc" type="checkbox"><label
												for="labSubServiceId_2">BUN</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_3"   value="3" name="lab_sub_service_desc" type="checkbox"><label
												for="labSubServiceId_3">Creatinine</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_4"   value="4"  name="lab_sub_service_desc" type="checkbox"><label
												for="labSubServiceId_4">eGFR</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_5"   value="5" name="lab_sub_service_desc" type="checkbox"><label
												for="labSubServiceId_5">Sodium</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_6"   value="6" name="lab_sub_service_desc" type="checkbox"><label
												for="labSubServiceId_6">Potassium</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_7"   value="7" name="lab_sub_service_desc" type="checkbox"><label
												for="labSubServiceId_7">Anion Gap</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_8"   value="8" name="lab_sub_service_desc" type="checkbox"><label
												for="labSubServiceId_8">Chloride</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_9"   value="9" name="lab_sub_service_desc" type="checkbox"><label
												for="labSubServiceId_9">Bicarbonate (CO3)</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_10"     value="10" name="lab_sub_service_desc" type="checkbox"><label
												for="labSubServiceId_10">Calcium</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_11"   value="11" name="lab_sub_service_desc" type="checkbox"><label
												for="labSubServiceId_11">Phosphorus</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_12"   value="12" name="lab_sub_service_desc" type="checkbox"><label
												for="labSubServiceId_12">Magnesium</label>
										</div>

										<div id="meta-right" class="small-6 medium-6 large-6 columns">
											<input id="labSubServiceId_13"   value="13" name="lab_sub_service_desc" type="checkbox"><label
												for="labSubServiceId_13">Magnesium</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_14"    value="14" name="lab_sub_service_desc" type="checkbox"><label
												for="labSubServiceId_14">ALT</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_15"   value="15"  name="lab_sub_service_desc" type="checkbox"><label
												for="labSubServiceId_15">AST</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_16"   value="16" name="lab_sub_service_desc" type="checkbox"><label
												for="labSubServiceId_16">ALP</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_17"   value="17" name="lab_sub_service_desc" type="checkbox"><label
												for="labSubServiceId_17">Albumin</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_18"   value="18" name="lab_sub_service_desc" type="checkbox"><label
												for="labSubServiceId_18">Total bilirubin</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_19"   value="19" name="lab_sub_service_desc" type="checkbox"><label
												for="labSubServiceId_19">Direct bilirubin</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_20"   value="20" name="lab_sub_service_desc" type="checkbox"><label
												for="labSubServiceId_20">Total Protein</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_21"   value="21" name="lab_sub_service_desc" type="checkbox"><label
												for="labSubServiceId_21">Amylase</label>
											<div class="clear-both"></div>
											<label>2 TT</label>

										</div>
									</div>
									<!-------metabolic-check----->
								</div>
								<!------#metabolic------>
							</div>

							<div id="mycustompadding"
								class="small-12 medium-12 large-4 columns custom-2">
								<div id="prevention" class="small-12 medium-6 large-6 columns">
									<div class="prevention-head">
										<input id="labServiceId_4"   name="lab_service_desc" value="4" type="checkbox"><label
											for="checkbox30">Diabetes Prevention</label>
									</div>
									<!-------prevention-head------>
									<div class="prevention-check">
										<div id="prevent-left"
											class="small-6 medium-6 large-6 columns">
											<input id="labSubServiceId_28"   name="lab_sub_service_desc" value="28" type="checkbox"><label
												for="checkbox31">Glucose</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_29"   name="lab_sub_service_desc" value="29" type="checkbox"><label
												for="checkbox32">HBA 1C</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_30"   name="lab_sub_service_desc" value="30" type="checkbox"><label
												for="checkbox33">Avg Glucose</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_31"   name="lab_sub_service_desc" value="31" type="checkbox"><label
												for="checkbox34">Insulin</label>
										</div>
										<!-------#prevent-left---------->
										<div id="prevent-right"
											class="small-6 medium-6 large-6 columns">
											<input id="labSubServiceId_32"   name="lab_sub_service_desc" value="32" type="checkbox"><label
												for="checkbox35">Growth Harmone</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_33"   name="lab_sub_service_desc" value="33" type="checkbox"><label
												for="checkbox36">Cortisol</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_34"   name="lab_sub_service_desc" value="34" type="checkbox"><label
												for="checkbox37">C peptide</label>
											<div class="clear-both"></div>
											<label>1L, 1 TT</label>
										</div>
										<!-------#prevent-right---------->
									</div>
									<!--------prevention-check------>
								</div>
								<!----#prevention----->

								<div id="card" class="small-12 medium-6 large-6 columns">
									<div class="card-head">
										<input id="labServiceId_5"   name="lab_service_desc" value="5" type="checkbox"><label
											for="checkbox38">Cardiovascular</label>
									</div>
									<!-------card-head------>
									<div class="card-check">
										<div id="card-left" class="small-6 medium-6 large-6 columns">
											<input id="labSubServiceId_35"   name="lab_sub_service_desc" value="35" type="checkbox"><label
												for="checkbox39">HDL</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_36"   name="lab_sub_service_desc" value="36" type="checkbox"><label
												for="checkbox40">LDL</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_37"   name="lab_sub_service_desc" value="37" type="checkbox"><label
												for="checkbox41">Cholesterol</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_38"   name="lab_sub_service_desc" value="38" type="checkbox"><label
												for="checkbox42">Triglycerides</label>
										</div>
										<!-------#card-left---------->
										<div id="card-right" class="small-6 medium-6 large-6 columns">
											<input id="labSubServiceId_39"   name="lab_sub_service_desc" value="39" type="checkbox"><label
												for="checkbox43">Lp(a)</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_40"   name="lab_sub_service_desc" value="40" type="checkbox"><label
												for="checkbox44">Apo A</label>
											<div class="clear-both"></div>
											<input id="labSubServiceId_41"   name="lab_sub_service_desc" value="41" type="checkbox"><label
												for="checkbox45">Apo B</label>
											<div class="clear-both"></div>
											<label>1 TT</label>
										</div>
										<!-------#card-right---------->
									</div>
									<!--------card-check------>
								</div>
								<!---------#card----->

								<div id="other" class="small-12 medium-12 large-12 columns">
									<div class="other-head">
										<input id="checkbox46" type="checkbox"><label
											for="checkbox46">Other</label>
									</div>
									<!-------other-head------>
									<div id="other-left" class="small-6 medium-6 large-6 columns">
										<input type="text"> <input type="text">
									</div>
									<!-----#other left------>

									<div id="other-right" class="small-6 medium-6 large-6 columns">
										<input type="text"> <input type="text">
									</div>
									<!------#other right------>
								</div>
								<!--------#other----->
							</div>

							<div id="mycustompadding"
								class="small-12 medium-12 large-3 columns custom-3">
								<div class="hormone">
									<div class="hormone-head">
										<input id="labServiceId_6"   name="lab_service_desc" value="6" type="checkbox"><label
											for="checkbox47">Hormone</label>
									</div>
									<!-------hormone-head------>
									<div id="hormo-left" class="small-6 medium-6 large-6 columns">
										<input id="labSubServiceId_42"   name="lab_sub_service_desc" value="42" type="checkbox"><label
											for="checkbox48">Testosterone</label>
										<div class="clear-both"></div>
										<input id="labSubServiceId_43"   name="lab_sub_service_desc" value="43" type="checkbox"><label
											for="checkbox49">Free testosterone(adult male)</label>
										<div class="clear-both"></div>
										<input id="labSubServiceId_44"   name="lab_sub_service_desc" value="44" type="checkbox"><label
											for="checkbox50">DHEAs</label>
										<div class="clear-both"></div>
										<input id="labSubServiceId_45"   name="lab_sub_service_desc" value="45" type="checkbox"><label
											for="checkbox51">SHBG</label>
										<div class="clear-both"></div>
										<input id="labSubServiceId_46"   name="lab_sub_service_desc" value="46" type="checkbox"><label
											for="checkbox52">Cortisol</label>
										<div class="clear-both"></div>
										<input id="labSubServiceId_47"   name="lab_sub_service_desc" value="47" type="checkbox"><label
											for="checkbox53">LH</label>
										<div class="clear-both"></div>
										<input id="labSubServiceId_48"   name="lab_sub_service_desc" value="48" type="checkbox"><label
											for="checkbox54">FSH</label>
										<div class="clear-both"></div>
										<input id="labSubServiceId_49"   name="lab_sub_service_desc" value="49"" type="checkbox"><label
											for="checkbox55">Growth hormone</label>
										<div class="clear-both"></div>
										<input id="labSubServiceId_50"   name="lab_sub_service_desc" value="50" type="checkbox"><label
											for="checkbox56">Free T3</label>
									</div>
									<!-----#hormo left------->
									<div id="hormo-right" class="small-6 medium-6 large-6 columns">
										<input id="labSubServiceId_51"   name="lab_sub_service_desc" value="51" type="checkbox"><label
											for="checkbox57">Prolactin</label>
										<div class="clear-both"></div>
										<input id="labSubServiceId_52"   name="lab_sub_service_desc" value="52" type="checkbox"><label
											for="checkbox58">HCG Beta (adult female)</label>
										<div class="clear-both"></div>
										<input id="labSubServiceId_53"   name="lab_sub_service_desc" value="53" type="checkbox"><label
											for="checkbox59">Estradiol</label>
										<div class="clear-both"></div>
										<input id="labSubServiceId_54"   name="lab_sub_service_desc" value="54" type="checkbox"><label
											for="checkbox60">Progesterone</label>
										<div class="clear-both"></div>
										<input id="labSubServiceId_55"   name="lab_sub_service_desc" value="55" type="checkbox"><label
											for="checkbox61">TSH</label>
										<div class="clear-both"></div>
										<input id="labSubServiceId_56"   name="lab_sub_service_desc" value="56" type="checkbox"><label
											for="checkbox62">T3 Total</label>
										<div class="clear-both"></div>
										<input id="labSubServiceId_57"   name="lab_sub_service_desc" value="57" type="checkbox"><label
											for="checkbox63">T4 Total</label>
										<div class="clear-both"></div>
										<input id="labSubServiceId_58"   name="lab_sub_service_desc" value="58" type="checkbox"><label
											for="checkbox64">Osteocalcin</label>
										<div class="clear-both"></div>
										<input id="labSubServiceId_59"   name="lab_sub_service_desc" value="59" type="checkbox"><label
											for="checkbox65">PSA (adult male)</label> <label>1 TT</label>
									</div>
									<!-----#hormo right------->
								</div>
								<!----hormone---->
							</div>
              
              <div class="second-form-row">
              <div id="mycustompadding" class="small-12 medium-6 large-3 columns">
                  <div id="second-hema" class="small-12 medium-12 large-12 columns">
                      <div class="second-hema-head">
                          <label>Hematology</label>
                      </div><!------second hema head------->
                      <div class="second-hema-check">
                          <input id="checkbox66" type="checkbox"><label for="checkbox66">Abnormal blood chemistry 790.6</label>
                          <div class="clear-both"></div>
                              <input id="checkbox67" type="checkbox"><label for="checkbox67">Anemia, unspecified 285.9</label>
                      <div class="clear-both"></div>
                      <input id="checkbox68" type="checkbox"><label for="checkbox68">Vitamin B deficiencies 266.2</label>
                          <div class="clear-both"></div>
                          <input id="checkbox69" type="checkbox"><label for="checkbox69">Vitamin D deficiency 268.9</label>
                          <div class="clear-both"></div>
                              <input id="checkbox70" type="checkbox"><label for="checkbox70">Iron deficiency 280.9</label>
                      <div class="clear-both"></div>
                      <input id="checkbox71" type="checkbox"><label for="checkbox71">Folate-deficiency 281.2</label>
                          <div class="clear-both"></div>
                          <input id="checkbox72" type="checkbox"><label for="checkbox72">Other abnormal blood tests 790.99</label>
                          <div class="clear-both"></div>
                              <input id="checkbox73" type="checkbox"><label for="checkbox73">Routine annual health check up V70.0</label>
                      </div><!------second-hema-check------>
                  </div><!------#second-hema------->
                  
                  <div id="second-meta" class="small-12 medium-12 large-12 columns">
                      <div class="second-meta-head">
                          <label>Metabolic</label>
                      </div><!--------second-meta-head------->
                      <div class="second-meta-check">
                          <p>Metabolic</p>
                          <input id="checkbox74" type="checkbox"><label for="checkbox74">Metabolic Syndrome 277.7</label>
                          <div class="clear-both"></div>
                          <input id="checkbox75" type="checkbox"><label for="checkbox75">Metabolism endocrine, NOS 259.9</label>
                          <div class="clear-both"></div>
                          <input id="checkbox76" type="checkbox"><label for="checkbox76">Obesity, unspecifird 278.00</label>
                          <div class="clear-both"></div>
                          <input id="checkbox77" type="checkbox"><label for="checkbox77">Overweight 278.02</label>
                          <div class="clear-both"></div>
                          <input id="checkbox78" type="checkbox"><label for="checkbox78">Abnormal weight gain 783.1</label>
                          <div class="clear-both"></div>
                          <input id="checkbox79" type="checkbox"><label for="checkbox79">Nutritional deficiency V12.1</label>
                          <div class="clear-both"></div>
                          <input id="checkbox80" type="checkbox"><label for="checkbox80">Malnutrition, moderate 263.0</label>
                          <div class="clear-both"></div>
                          <input id="checkbox81" type="checkbox"><label for="checkbox81">Cushings Syndrome 255.0</label>
                          <div class="clear-both"></div>
                          <input id="checkbox82" type="checkbox"><label for="checkbox82">Glucocorticoid deficiency 255.41</label>
                          <div class="clear-both"></div>
                          <input id="checkbox83" type="checkbox"><label for="checkbox83">Kids BMI> 85% V58.53</label>
                          <div class="clear-both"></div>
                          <input id="checkbox84" type="checkbox"><label for="checkbox84">Bariatric Surgery status V45.86</label>
                          <div class="clear-both"></div>
                          <p>Liver/Reanal</p>
                          <input id="checkbox85" type="checkbox"><label for="checkbox85">Chronic nonalcoholic liver disease NOS 571.8</label>
                          <div class="clear-both"></div>
                          <input id="checkbox86" type="checkbox"><label for="checkbox86">Recurrent Cystitis 595.9</label>
                          <div class="clear-both"></div>
                          <input id="checkbox87" type="checkbox"><label for="checkbox87">Impaired renal function 589.9</label>
                          <div class="clear-both"></div>
                          <input id="checkbox88" type="checkbox"><label for="checkbox88">Chronic kidney disease, NOS 585.9</label>
                          <div class="clear-both"></div>
                          <input id="checkbox89" type="checkbox"><label for="checkbox89">Kidney stone 274.11</label>
                          <div class="clear-both"></div>
                          <input id="checkbox90" type="checkbox"><label for="checkbox90">Urinary frequency 788.41</label>
                          <div class="clear-both"></div>
                          <input id="checkbox91" type="checkbox"><label for="checkbox91">Abnormal kidney function, NOS 794.4</label>
                          <div class="clear-both"></div>
                      </div><!------second-meta-check----->
                  </div><!-----#second-meta-------->
              </div>
              
              <div id="mycustompadding" class="small-12 medium-6 large-3 columns">
                  <div id="second-meta-2" class="small-12 medium-12 large-12 columns">
                      <div class="second-meta-head-2">
                      </div><!--------second-meta-head-2------>
                      <div class="second-meta-check-2">
                          <p>Digestive Health</p>
                          <input id="checkbox92" type="checkbox"><label for="checkbox92">Gerd/ Reflux 530.81</label>
                          <div class="clear-both"></div>
                          <input id="checkbox93" type="checkbox"><label for="checkbox93">Gastritis 535.50</label>
                          <div class="clear-both"></div>
                          <input id="checkbox94" type="checkbox"><label for="checkbox94">Dyspepsia 536.8</label>
                          <div class="clear-both"></div>
                          <input id="checkbox95" type="checkbox"><label for="checkbox95">Biliary Colic 574.20</label>
                          <div class="clear-both"></div>
                          <input id="checkbox96" type="checkbox"><label for="checkbox96">Diarrhea 787.91</label>
                          <div class="clear-both"></div>
                          <input id="checkbox97" type="checkbox"><label for="checkbox97">Abdominal tenderness 789.69</label>
                          <div class="clear-both"></div>
                          <input id="checkbox98" type="checkbox"><label for="checkbox98">Abdominal Pain, NOS 789.00</label>
                          <div class="clear-both"></div>
                          <input id="checkbox99" type="checkbox"><label for="checkbox99">Nausea with Vomiting 787.01</label>
                          <div class="clear-both"></div>
                          <input id="checkbox100" type="checkbox"><label for="checkbox100">Persistent vomiting 536.2</label>
                          <div class="clear-both"></div>
                          <p>Elderly</p>
                          <input id="checkbox101" type="checkbox"><label for="checkbox101">Osteoporosis, NOS 733.00</label>
                          <div class="clear-both"></div>
                          <input id="checkbox102" type="checkbox"><label for="checkbox102">Alzheimer's 331.0</label>
                          <div class="clear-both"></div>
                          <input id="checkbox103" type="checkbox"><label for="checkbox103">Mild Cognitive Impairment 331.83</label>
                          <div class="clear-both"></div>
                          <input id="checkbox104" type="checkbox"><label for="checkbox104">Memory Loss 780.93</label>
                          <div class="clear-both"></div>
                          <input id="checkbox105" type="checkbox"><label for="checkbox105">Abdormal Gait 781.2</label>
                          <div class="clear-both"></div>
                          <input id="checkbox106" type="checkbox"><label for="checkbox106">Lack of coordination 781.3</label>
                          <div class="clear-both"></div>
                          <input id="checkbox107" type="checkbox"><label for="checkbox107">Altered mental status 780.97</label>
                          <div class="clear-both"></div>
                          <input id="checkbox108" type="checkbox"><label for="checkbox108">Dementia 290.8</label>
                          <div class="clear-both"></div>
                          <p>Dermatology</p>
                          <input id="checkbox109" type="checkbox"><label for="checkbox109">Hirsutism 704.1</label>
                          <div class="clear-both"></div>
                          <input id="checkbox110" type="checkbox"><label for="checkbox110">Pruritus, NOS 698.9</label>
                          <div class="clear-both"></div>
                          <input id="checkbox111" type="checkbox"><label for="checkbox111">Alopecia 704.00</label>
                          <div class="clear-both"></div>
                          <input id="checkbox112" type="checkbox"><label for="checkbox112">Other psoriasis 696.1</label>
                          <div class="clear-both"></div>
                          <p>Gynecology</p>
                          <input id="checkbox113" type="checkbox"><label for="checkbox113">Vaginal candidiasis 112.1</label>
                          <div class="clear-both"></div>
                          <input id="checkbox114" type="checkbox"><label for="checkbox114">Amenorrhea 626.0</label>
                          <div class="clear-both"></div>
                          <input id="checkbox115" type="checkbox"><label for="checkbox115">Vulvovaginitis 616.10</label>
                          <div class="clear-both"></div>
                          <input id="checkbox116" type="checkbox"><label for="checkbox116">Irregular menses 626.4</label>
                          <div class="clear-both"></div>
                          <input id="checkbox117" type="checkbox"><label for="checkbox117">Infertility Female 628.9</label>
                          <div class="clear-both"></div>
                          <input id="checkbox118" type="checkbox"><label for="checkbox118">Dysmenorrhea 625.3</label>
                          <div class="clear-both"></div>
                          <input id="checkbox119" type="checkbox"><label for="checkbox119">Vaginal bleeding 623.8</label>
                          <div class="clear-both"></div>
                          <input id="checkbox120" type="checkbox"><label for="checkbox120">Pregnancy V22.0</label>
                          <div class="clear-both"></div>
                          <p>Respiratory</p>
                          <input id="checkbox121" type="checkbox"><label for="checkbox121">Shortness of Breath 786.05</label>
                          <div class="clear-both"></div>
                          <input id="checkbox122" type="checkbox"><label for="checkbox122">Dyspnea 786.09</label>
                          <div class="clear-both"></div>
                          <input id="checkbox123" type="checkbox"><label for="checkbox123">Cough 786.2</label>
                          <div class="clear-both"></div>
                          <input id="checkbox124" type="checkbox"><label for="checkbox124">Lung disease, NOS 518.89</label>
                          <div class="clear-both"></div>
                          <input id="checkbox125" type="checkbox"><label for="checkbox125">Chronic Bronchitis 491.0</label>
                          <div class="clear-both"></div>
                          <input id="checkbox126" type="checkbox"><label for="checkbox126">Abnormal chest x-ray 793.19</label>
                          <div class="clear-both"></div>
                          <input id="checkbox127" type="checkbox"><label for="checkbox127">Sleep apnea 327.29</label>
                          <div class="clear-both"></div>
                      </div><!------second-meta-check-2---->
                  </div><!-----#second-meta-2------->
              </div>
              
              <div id="mycustompadding" class="small-12 medium-6 large-3 columns">
                  <div id="second-inflam" class="small-12 medium-12 large-12 columns">
                      <div class="second-inflam-head">
                          <label>Inflammation</label>
                      </div><!--------second-inflam-head------->
                      <div class="second-inflam-check">
                          <input id="checkbox128" type="checkbox"><label for="checkbox128">Neuropatty, unspecified 356.9</label>
                          <div class="clear-both"></div>
                          <input id="checkbox129" type="checkbox"><label for="checkbox129">VLong-term Use of Medication V58.69</label>
                          <div class="clear-both"></div>
                          <input id="checkbox130" type="checkbox"><label for="checkbox130">Myalgia 729.1</label>
                          <div class="clear-both"></div>
                          <input id="checkbox131" type="checkbox"><label for="checkbox131">Fatigue, malaise, weakness 780.79</label>
                          <div class="clear-both"></div>
                          <input id="checkbox132" type="checkbox"><label for="checkbox132">Acute Gout 274.01</label>
                          <div class="clear-both"></div>
                          <input id="checkbox133" type="checkbox"><label for="checkbox133">Gout Unspecified 274.9</label>
                          <div class="clear-both"></div>
                          <input id="checkbox134" type="checkbox"><label for="checkbox134">Alcoholism 303.90</label>
                          <div class="clear-both"></div>
                          <input id="checkbox135" type="checkbox"><label for="checkbox135">Tobacco Use 305.1</label>
                          <div class="clear-both"></div>
                          <input id="checkbox136" type="checkbox"><label for="checkbox136">Personal Hx of Tobacco use V15.82</label>
                          <div class="clear-both"></div>
                          <input id="checkbox137" type="checkbox"><label for="checkbox137">Fever 780.60</label>
                          <div class="clear-both"></div>
                          <input id="checkbox138" type="checkbox"><label for="checkbox138">Chills 780.64</label>
                          <div class="clear-both"></div>
                          <input id="checkbox139" type="checkbox"><label for="checkbox139">Chronic fatigue syndrome 780.71</label>
                          <div class="clear-both"></div>
                          <input id="checkbox140" type="checkbox"><label for="checkbox140">Insomnia 780.50</label>
                          <div class="clear-both"></div>
                      </div><!------second-inflam-check----->
                  </div><!-----#second-inflam-------->
                  
                  <div id="second-cardio" class="small-12 medium-12 large-12 columns">
                      <div class="second-Cardio-head">
                          <label>Cardiovascular</label>
                      </div><!--------second-cardio-head------->
                      <div class="second-cardio-check">
                          <input id="checkbox141" type="checkbox"><label for="checkbox141">Dizziness 780.4</label>
                          <div class="clear-both"></div>
                          <input id="checkbox142" type="checkbox"><label for="checkbox142">Chest Pain, NOS 786.50</label>
                          <div class="clear-both"></div>
                          <input id="checkbox143" type="checkbox"><label for="checkbox143">Hypertension, NOS 401.9</label>
                          <div class="clear-both"></div>
                          <input id="checkbox144" type="checkbox"><label for="checkbox144">Angina 413.9</label>
                          <div class="clear-both"></div>
                          <input id="checkbox145" type="checkbox"><label for="checkbox145">Cardiovascular disease 429.2</label>
                          <div class="clear-both"></div>
                          <input id="checkbox146" type="checkbox"><label for="checkbox146">Congestive Heart Failure 428.0</label>
                          <div class="clear-both"></div>
                          <input id="checkbox147" type="checkbox"><label for="checkbox147">Family History CAD V17.3</label>
                          <div class="clear-both"></div>
                          <input id="checkbox148" type="checkbox"><label for="checkbox148">Atrial Fibrillation 427.31</label>
                          <div class="clear-both"></div>
                          <input id="checkbox149" type="checkbox"><label for="checkbox149">Vascular disease 440.8</label>
                          <div class="clear-both"></div>
                          <input id="checkbox150" type="checkbox"><label for="checkbox150">Postural Hypotension 458.0</label>
                          <div class="clear-both"></div>
                          <input id="checkbox151" type="checkbox"><label for="checkbox151">Hypercholesterolemia 272.0</label>
                          <div class="clear-both"></div>
                          <input id="checkbox152" type="checkbox"><label for="checkbox152">Hypertrigylceridemia 272.1</label>
                          <div class="clear-both"></div>
                          <input id="checkbox153" type="checkbox"><label for="checkbox153">Hyperlipidemia NOS 272.4</label>
                          <div class="clear-both"></div>
                          <input id="checkbox154" type="checkbox"><label for="checkbox154">Screening for lipid disorders V77.91</label>
                          <div class="clear-both"></div>
                          <input id="checkbox155" type="checkbox"><label for="checkbox155">Palpitations 785.1</label>
                          <div class="clear-both"></div>
                          <input id="checkbox156" type="checkbox"><label for="checkbox156">Syncope 780.2</label>
                          <div class="clear-both"></div>
                      </div><!------second-cardio-check----->
                  </div><!-----#second-cardio-------->
              </div>
              
              <div id="mycustompadding" class="small-12 medium-6 large-3 columns">
                  <div id="second-diab" class="small-12 medium-12 large-12 columns">
                      <div class="second-diab-head">
                          <label>Diabetes Prevention</label>
                      </div><!--------second-diab-head------->
                      <div class="second-diab-check">
                          <input id="checkbox157" type="checkbox"><label for="checkbox157">Insulin Resistance 277.7</label>
                          <div class="clear-both"></div>
                          <input id="checkbox158" type="checkbox"><label for="checkbox158">Diabetes II, controlled 250.00</label>
                          <div class="clear-both"></div>
                          <input id="checkbox159" type="checkbox"><label for="checkbox159">Diabetes II, uncontrolled 250.02</label>
                          <div class="clear-both"></div>
                          <input id="checkbox160" type="checkbox"><label for="checkbox160">Screening for Diabetes Mellittus V77.1</label>
                          <div class="clear-both"></div>
                          <input id="checkbox161" type="checkbox"><label for="checkbox161">Prediabetes, abnormal glucose 790.29</label>
                          <div class="clear-both"></div>
                          <input id="checkbox162" type="checkbox"><label for="checkbox162">Impaired Fasting Glucose 790.21</label>
                          <div class="clear-both"></div>
                      </div><!------second-diab-check----->
                  </div><!-----#second-diab-------->
                  
                  <div id="second-hormone" class="small-12 medium-12 large-12 columns">
                      <div class="second-hormone-head">
                          <label>Hormone</label>
                      </div><!--------second-hormone-head------->
                      <div class="second-hormone-check">
                          <input id="checkbox163" type="checkbox"><label for="checkbox163">Depression, unspecified 311.0</label>
                          <div class="clear-both"></div>
                          <input id="checkbox164" type="checkbox"><label for="checkbox164">Major depressive disorder, recurrent 296.30</label>
                          <div class="clear-both"></div>
                          <input id="checkbox165" type="checkbox"><label for="checkbox165">Unspecified Psychosis 298.9</label>
                          <div class="clear-both"></div>
                          <input id="checkbox166" type="checkbox"><label for="checkbox166">Non psychotic disorder, NOS 300.9</label>
                          <div class="clear-both"></div>
                          <input id="checkbox167" type="checkbox"><label for="checkbox167">Psychosexual Dysfunction 302.70</label>
                          <div class="clear-both"></div>
                          <input id="checkbox168" type="checkbox"><label for="checkbox168">Low Libido 302.72</label>
                          <div class="clear-both"></div>
                          <input id="checkbox169" type="checkbox"><label for="checkbox169">Inhibited sexual desire 302.71</label>
                          <div class="clear-both"></div>
                          <input id="checkbox170" type="checkbox"><label for="checkbox170">Testicular dysfunction 257.2</label>
                          <div class="clear-both"></div>
                          <input id="checkbox171" type="checkbox"><label for="checkbox171">Impotence 607.84</label>
                          <div class="clear-both"></div>
                          <input id="checkbox172" type="checkbox"><label for="checkbox172">Andropause 253.4</label>
                          <div class="clear-both"></div>
                          <input id="checkbox173" type="checkbox"><label for="checkbox173">Menopause 627.2</label>
                          <div class="clear-both"></div>
                          <input id="checkbox174" type="checkbox"><label for="checkbox174">Post Menopausal, NOS 627.9</label>
                          <div class="clear-both"></div>
                          <input id="checkbox175" type="checkbox"><label for="checkbox175">Hormone disorder, unspecified 259.9</label>
                          <div class="clear-both"></div>
                          <input id="checkbox176" type="checkbox"><label for="checkbox176">Disorders of Thyroid 246.0</label>
                          <div class="clear-both"></div>
                          <input id="checkbox177" type="checkbox"><label for="checkbox177">Hypothyroidism, unspecified 244.9</label>
                          <div class="clear-both"></div>
                          <input id="checkbox178" type="checkbox"><label for="checkbox178">Hyperparathyroidism 252.00</label>
                          <div class="clear-both"></div>
                          <input id="checkbox179" type="checkbox"><label for="checkbox179">Hypoparathyroidism 252.1</label>
                          <div class="clear-both"></div>
                          <input id="checkbox180" type="checkbox"><label for="checkbox180">Screening for Prostrate Cancer V76.44</label>
                          <div class="clear-both"></div>
                          <input id="checkbox181" type="checkbox"><label for="checkbox181">Elevated PSA 790.93</label>
                          <div class="clear-both"></div>
                          <input id="checkbox182" type="checkbox"><label for="checkbox182">Prostrate Cancer 185.0</label>
                          <div class="clear-both"></div>
                          <input id="checkbox183" type="checkbox"><label for="checkbox183">BPH 600.0</label>
                          <div class="clear-both"></div>
                          <input id="checkbox184" type="checkbox"><label for="checkbox184">Alopecia 704.00</label>
                          <div class="clear-both"></div>
                          <input id="checkbox185" type="checkbox"><label for="checkbox185">Androgen insensitivity, NOS 259.50</label>
                          <div class="clear-both"></div>
                          <input id="checkbox186" type="checkbox"><label for="checkbox186">Thyroid disorder, NOS 246.8</label>
                          <div class="clear-both"></div>
                      </div><!------second-hormone-check----->
                  </div><!-----#second-hormone-------->
              </div>
              </div><!------second-form-row------->
			  
			  		  <div id="text-area-6" class="small-12 medium-12 large-6 columns">
		  <textarea name="labICDNote" id="labICDNote" cols="" rows="" placeholder="Additional ICD 9 Notes"></textarea>
		  </div><!---#text-area-6---->
		  
		  <div id="text-area-6" class="small-12 medium-12 large-6 columns">
		  <textarea name="labNotes" id="labNotes" cols="" rows="" placeholder="Notes"></textarea>
		  </div><!---#text-area-6---->
		  
		  <div id="input-4" class="small-12 medium-12 large-4 columns">
		  <input name="labRefPhy" id="labRefPhy" type="text" placeholder="Physician">
		  </div><!---#input-4---->
		  
		  <div id="input-4" class="small-12 medium-12 large-4 columns">
		  <input name="Patient Sig" type="text" placeholder="Patient Sig">
		  </div><!---#input-4---->
		  
		  <div id="input-4" class="small-12 medium-12 large-4 columns">
		  <input name="Date " type="text" value="" placeholder="Date">
		  </div><!---#input-4---->
          <!-- <div class="small-12 medium-12 large-2 offset-large-10 columns">
		              <button type="button" class="btn btn-default" data-dismiss="modal">Submit</button>
		  </div> -->
		  </form><!------checkbox-req----->

		</div><!--main-content-full-->
	  
	  </div><!--small-12 medium-12 large-10 columns-->
</body>
</html>