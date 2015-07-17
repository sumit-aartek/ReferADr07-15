
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${sessionScope.providePracticeId!=null}">



<div id="menu-container" class="small-12 medium-3 large-3 columns">
	<div id="accordian">
		<ul>
			<li>
				<h3>
					<span><i class="fa fa-pencil-square-o"></i></span><a
						href="referPatient.do?sendBy=menu">Refer A Patient</a>
				</h3>
			</li>
			<!-- we will keep this LI open by default -->
			<li>
				<h3>
					<span><i class="fa fa-envelope"></i></span>Referrals
				</h3>
				<ul>
					<li><a href="practice.do">Inbound</a></li>
					<li><a href="draft.do">Draft</a></li>
					<li><a href="sendBox.do">Sent</a></li>
					<li><a href="#">Diagnostic</a></li>
				</ul>
			</li>
			<li class="active">
				<h3>
					<span><i class="fa fa-university"></i></span>Our Practice
				</h3>
				<ul>
					<li><a href="practiceInfo.do">Practice Info</a></li>
					<li class="list-menu-active"><a href="doctor.do">Doctors</a></li>
					<li><a href="#">Insurance</a></li>
					<li><a href="#">Our Network</a></li>
				</ul>
			</li>
			<li>
				<h3>
				<a href="report.do">
					<span><i class="fa fa-file-text-o"></i></span>Reports</a>
				</h3>
			</li>
			<li>
				<h3>
					<span><i class="fa fa-calendar"></i></span>Calendar
				</h3>
			</li>
		</ul>
	</div>
	<!--#accordian-->

</div>




</c:if>
<!--#menu-container-->