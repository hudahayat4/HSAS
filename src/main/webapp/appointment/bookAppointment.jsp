<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Appointment</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css"
	rel="stylesheet">
<link href="https://cdn.lineicons.com/5.0/lineicons.css"
	rel="stylesheet">

<link rel="stylesheet" href="../css/header.css">
<link rel="stylesheet" href="../css/footer.css">
<style type="text/css">
<%@ include file="../css/bookAppointment.css"%>
</style>
</head>

<body>

	<%@ include file="../header.jsp"%>



	<div class="container text-center">

		<!-- hidden inputs -->
		<input type="hidden" name="packageName" id="packageName"> <input
			type="hidden" name="appointmentDate" id="appointmentDate">
		<form method="post" action="AppointmentController">
			<!-- STEPPER -->
			<div class="stepper-wrapper">
				<div class="stepper-line"></div>

				<div class="stepper">
					<div class="step active">
						<div class="step-circle">1</div>
						<div class="step-label">Package</div>
					</div>
					<div class="step">
						<div class="step-circle">2</div>
						<div class="step-label">Date</div>
					</div>
					<div class="step">
						<div class="step-circle">3</div>
						<div class="step-label">Confirm</div>
					</div>
				</div>
			</div>

			<!-- STEP 1 : PACKAGE -->
			<div class="section active">
				<div class="row justify-content-center mb-3">
				<input type="hidden" name="packageId" id="packageId">
					<c:forEach var="p" items="${packages}">
						<div class="col-md-3 package-card" onclick="selectPackage(this, '${p.packageId}', '${p.packageName}')">
							<img src="${p.packagePic }" class="package-img">
							<div>${p.packageName }</div>
							<div class="package-price">RM${p.packagePrice }</div>
							<div class="check-icon">
								<i class="bi bi-check-lg"></i>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>

			<!-- STEP 2 : DATE -->
			<div class="section">
				<div class="row justify-content-center g-4 align-items-stretch">

					<!-- CALENDAR -->
					<div class="col-md-6 col-lg-5 d-flex">
						<div class="card h-100 w-100">
							<div class="calendar-toolbar">
								<button type="button" class="prev month-btn">
									<i class="bi bi-chevron-left"></i>
								</button>

								<div class="current-month"></div>

								<button type="button" class="seterusnya month-btn">
									<i class="bi bi-chevron-right"></i>
								</button>
							</div>

							<div class="calendar-body">
								<ul class="calendar-weekdays">
									<li>Sun</li>
									<li>Mon</li>
									<li>Tue</li>
									<li>Wed</li>
									<li>Thu</li>
									<li>Fri</li>
									<li>Sat</li>
								</ul>

								<ul class="calendar-dates"></ul>
							</div>
						</div>
					</div>

					<!-- TIME SLOTS -->
					<div class="col-md-6 col-lg-5 d-flex">
						<div class="card h-100 w-100 p-3">
							<h6 class="mb-3 text-center">Select Time</h6>

							<ul class="time-slots">
								<li>08:00</li>
								<li>08:15</li>
								<li>08:30</li>
								<li>08:45</li>
								<li>09:00</li>
								<li>09:15</li>
							</ul>
						</div>
					</div>

				</div>
			</div>

			<!-- BUTTONS -->
			<div class="d-flex justify-content-center gap-3 mt-5 mb-5">
				<button type="button" class="btn btn-secondary previous"
					onclick="prevStep()">Back</button>
				<button type="button" class="btn nexts" onclick="nextStep()">Next</button>
			</div>

		</form>
		<!-- STEP 3 : CONFIRM -->
		<div class="section">
			<h5>Confirmation</h5>
			<p>
				<strong>Package:</strong> <span id="confirmPackage">-</span>
			</p>
			<p>
				<strong>Date:</strong> <span id="confirmDate">-</span>
			</p>
		</div>
	</div>


	<%@ include file="../footer.jsp"%>
	<script type="text/javascript"><%@include file="../js/bookAppointment.js" %></script>

</body>
</html>
