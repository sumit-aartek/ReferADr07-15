  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  
  	      
  	       <c:if test="${sessionScope.clearingHouseId!=null}">
		<div id="main-navigation" class="small-12 medium-12 large-2 columns no-padding">
		<div class="menu-side-navigation">
			<ul class="accordion" data-accordion>
				 <li class="accordian-main-li">
				<a href="viewPractices.do" class="menu-refer-a-patient">
                     <span class="refer-patient-ico">
                         <i class="fa fa-pencil-square-o"></i>
                     </span>
				Create a Practice</a>
			  </li>
			  <li class="accordion-navigation accordian-main-li">
				<a href="#panel1a" class="menu-referrals">
					<span class="referrals-ico"><i class="fa fa-envelope"></i></span>
				Referrals</a>
				<div id="panel1a" class="div-sub-menu content referrals-sub">
					<a href="clearingHouse.do">Inbound</a>
					<a href="#">Draft</a>
					<a href="#">Sent</a>
					<a href="#">Diagnostic</a>
				</div>
			  </li>
			  <li class="accordion-navigation accordian-main-li">
				<a href="#panel2a" class="menu-our-practice">
					<span class="practice-ico"><i class="fa fa-university"></i></span>
				Our Clearing House</a>
				<div id="panel2a" class="div-sub-menu content practice-sub">
					<a href="ChHome.do">Clearing House</a>
					<a href="viewCHAdmins.do">Administrators</a>
					<a href="viewPractices.do">Practices</a>
					<a href="masterData.do">Master Data</a>
				</div>
			  </li>
			  <li class="accordian-main-li">
              	<a href="#" class="menu-reports"><span class="menu-report-ico">
                      <i class="fa fa-file-text-o"></i>
                  </span>Reports</a>
			  </li>
			  <li class="accordian-main-li">
                  
				<a href="#panel4a" class="menu-calendar">
					<span class="menu-calendar-ico"><i class="fa fa-calendar"></i></span>
				Calendar</a>
			  </li>
			</ul>
		
		</div><!--.menu-side-navigation-->
	</div><!--#main-navigation-->
	</c:if>
	