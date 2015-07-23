<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="content-inner-main-right" class="small-12 medium-9 large-9 columns">
							<div class="main-content-inside">
								<div id="breadcrumbs-container" class="small-12 medium-12 large-12 columns">
									<ul class="breadcrumbs">
									  <li><a href="#">Referrals</a></li>
									  <li class="breadcrum-icon"><i class="fa fa-play"></i></li>
									  <li><a href="PracticeInfo.do">Practice Info</a></li>
									</ul>
								</div><!--#breadcrumbs-container-->
								<div class="panel callout">
																		
									<form:form action="updatePracticeInfo.do" modelAttribute="practiceInfo" method="POST" class="practiceinfo-page-form">
										<div class="small-12 medium-12 large-8 columns">
											
											 <form:input path="practiceName" placeholder="Name" value="${practiceInfo.practiceName}" readonly="true" />
                                            <input type="text" placeholder="Description">
                                           <form:input placeholder="Address 1" path="practiceLocations.radLocation.locAddress1" value="${practiceInfo.practiceLocations.radLocation.locAddress1}"/>
                                         <form:input placeholder="Address 2" path="practiceLocations.radLocation.locAddress2" value="${practiceInfo.practiceLocations.radLocation.locAddress2}"/>
										</div>
										<form:hidden path="practiceId" value="${practiceInfo.practiceId}"/>
 <form:hidden path="practiceLocations.precticeLocationId" value="${practiceInfo.practiceLocations.precticeLocationId}"/>
 <form:hidden path="practiceLocations.radLocation.locId" value="${practiceInfo.practiceLocations.radLocation.locId}"/>

										<div class="small-12 medium-12 large-4 columns">
                                            <img class="select-dp" src="img7_15/gravatar.jpg">
											<input type="file" name="fileToUpload" id="fileToUpload" multiple>
										</div>
                                        
                                        <div class="clearfix"></div>
                                        
                                        <div class="small-12 medium-12 large-4 columns">
                                          <form:input  placeholder="City" path="practiceLocations.radLocation.locCity" value="${practiceInfo.practiceLocations.radLocation.locCity}"/>
                                        </div>
                                        <div class="small-12 medium-12 large-4 columns">
                                            
                                        <form:select path="practiceLocations.radLocation.redState.stateId">
											 <c:forEach items="${states}" var="state">
											 <c:choose>
											 <c:when test="${state.stateName == practiceInfo.practiceLocations.radLocation.locState}">
 											 <form:option value="${state.stateId}" selected="true">${state.stateName}</form:option>
 											 </c:when>
 											 <c:otherwise>
 											 <form:option value="${state.stateId}">${state.stateName}</form:option>
											 </c:otherwise>
											 </c:choose>
											 </c:forEach>
											 </form:select>
                                        </div>
                                        <div class="small-12 medium-12 large-4 columns">
                                           <form:input placeholder="Zip" path="practiceLocations.radLocation.locZip" value="${practiceInfo.practiceLocations.radLocation.locZip}"/>
                                        </div>
                                        <div class="clearfix"></div>
                                        <div class="small-12 medium-12 large-4 columns">
                                           <form:input placeholder="Phone" path="practiceLocations.radLocation.locPhone" value="${practiceInfo.practiceLocations.radLocation.locPhone}"/>
                                        </div>
                                        
                                        <div class="small-12 medium-12 large-4 columns">
                                           <form:input placeholder="Fax" path="practiceLocations.radLocation.locFax" value="${practiceInfo.practiceLocations.radLocation.locFax}"/>
                                        </div>
                                        
                                        <div class="small-12 medium-12 large-4 columns">
                                            <form:input  placeholder="www.url.com" path="practiceLocations.radLocation.locWebsite" value="${practiceInfo.practiceLocations.radLocation.locWebsite}"/>
                                        </div>
                                        
                                        <div class="small-12 medium-12 large-12 columns">
                                      <%--   <c:if test="${sessionScope.clearingHouseId==null}"> --%>
										<input name="Submit" type="Submit" value="Submit" class="button alignright">
									<%-- 	</c:if> --%>
									    </div>
									    </form:form>
									<!--.inbound-page-form-->
								
								
								</div><!--.panel callout-->
							</div><!--.main-content-inside-->
						</div><!--#content-inner-main-right-->


</body>
</html>