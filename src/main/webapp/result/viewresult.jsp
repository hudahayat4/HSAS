<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
     <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
     <link rel="stylesheet" href="../css/sideStaff.css">
 <style>
@import
	url('https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800&display=swap')
	;
</style>

<title>view result</title>
</head>
<body>
<div class="wrapper">
<%@ include file="../sidePharmacist.jsp"%>
	<h5 class="text-center fw-bold mb-4" style="color: #17a2b8;">Result</h5>
	<div class="container mt-5" style="max-width: 900px;">
		<div class="card mb-4">
			<div class="card-body">
				<div class="row mb-2">
					<div class="col-md-4 fw-bold">Appointment Date :</div>
					<div class="col-md-8 text-start">16/11/2025</div>
				</div>
				<div class="row mb-2">
					<div class="col-md-4 fw-bold">Appointment Time :</div>
					<div class="col-md-8 text-start">10.00 AM</div>
				</div>
				<div class="row mb-2">
					<div class="col-md-4 fw-bold">Pharmacist Name :</div>
					<div class="col-md-8 text-start">Dr. Ali</div>
				</div>
				<div class="d-grid gap-2 d-md-flex justify-content-md-end">
 					 <button id="viewResultBtn" class="btn " type="button" style="background-color: #17a2b8; color: white;">View results</button>
				</div>
			</div>
		</div>
<!-- RESULT SECTION (HIDDEN INITIALLY) -->
<div id="resultSection" class="d-none">

    <c:choose>

        <!-- URIC -->
        <c:when test="${packageType == 'URIC'}">
            <div class="card mb-4 shadow-sm">
                <div class="card-body">
                    <h6 class="fw-bold mb-3">Uric Acid Result</h6>

                    <div class="row mb-2">
                        <div class="col-md-4 fw-bold">Uric Acid Level :</div>
                        <div class="col-md-8">${uricLevel} mg/dL</div>
                    </div>
                </div>
            </div>
        </c:when>

        <!-- LIPID -->
        <c:when test="${packageType == 'LIPID'}">
            <div class="card mb-4 shadow-sm">
                <div class="card-body">
                    <h6 class="fw-bold mb-3">Lipid Profile</h6>

                    <div class="row mb-2">
                        <div class="col-md-4 fw-bold">HDL :</div>
                        <div class="col-md-8">${hdl} mg/dL</div>
                    </div>

                    <div class="row mb-2">
                        <div class="col-md-4 fw-bold">LDL :</div>
                        <div class="col-md-8">${ldl} mg/dL</div>
                    </div>
                </div>
            </div>
        </c:when>

        <!-- HBA1C -->
        <c:when test="${packageType == 'HBA1C'}">
            <div class="card mb-4 shadow-sm">
                <div class="card-body">
                    <h6 class="fw-bold mb-3">HbA1c Result</h6>

                    <div class="row mb-2">
                        <div class="col-md-4 fw-bold">HbA1c Value :</div>
                        <div class="col-md-8">${hba1cValue} %</div>
                    </div>
                </div>
            </div>
        </c:when>

    </c:choose>

    <!-- Comment -->
    <div class="card mb-4 shadow-sm">
        <div class="card-body">
            <h6 class="fw-bold">Pharmacist Comment</h6>
            <p>${comment}</p>
        </div>
    </div>

</div>
</div>
</div>
</body>
<script src="${pageContext.request.contextPath}/js/viewresult.js"></script>
</html>