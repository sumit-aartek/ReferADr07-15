
 
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <div id="main-navaigation" class="small-0 medium-0 large-3 columns">
       
		 <c:if test="${sessionScope.providePracticeId!=null}">
		  <h1 class="admin-heading">${sessionScope.practiceName}</h1>
		<a class="toggleMenu" href="#">Menu</a>
		<ul class="nav">
			<li class="inbox"><a href="practice.do">Inbox</a></li>
			<li class="inbox"><a href="draft.do">Draft</a></li>
			<li class="clearing-house"><a href="PracticeInfo.do">PRACTICE</a></li>
		<!-- 	<li class="dashboard"><a href="">Dashboard</a></li> -->
			<li class="report"><a href="report.do">Report</a></li>
			<!-- <li class="settings"><a href="">Settings</a></li> -->
			<li class="create-practice" ><a href="referPatient.do?sendBy=menu">REFER A PATIENT</a></li>
		</ul><!--ul-->
		</c:if>
		<c:if test="${sessionScope.clearingHouseId!=null}">
		 <h1>SYNERGY</h1>
		<a class="toggleMenu" href="#">Menu</a>
		<ul class="nav">
			 <li class="inbox"><a href="clearingHouse.do">Inbox</a></li>
			<li class="clearing-house"><a href="ChHome.do">Clearing House</a></li>
			<!-- <li class="dashboard"><a href="#">Dashboard</a></li> -->
			<li class="report"><a href="report.do">Report</a></li>
			<!-- <li class="settings"><a href="#">Settings</a></li> -->
			<li class="create-practice"><a href="viewPractices.do">Create a Practice</a></li>
		</ul>
	</c:if>
		<!-- onClick="openBox1()"  href=javascript:void(o) -->
      </div>