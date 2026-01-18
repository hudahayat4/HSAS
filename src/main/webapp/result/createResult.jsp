<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="../css/sideStaff.css">
<title>Create Result</title>
<style>
@import
	url('https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800&display=swap')
	;
</style>
</head>
<body>
<div class="wrapper">
<%@ include file="../sidePharmacist.jsp"%>
	<h5 class="text-center fw-bold mb-4" style="color: #17a2b8;">Add
		Result</h5>
	<div class="container mt-5" style="max-width: 900px;">
		<div class="card mb-4">
			<div class="card-body">
				<div class="row mb-2">
					<div class="col-md-4 fw-bold">Appointment Date :</div>
					<div class="col-md-8 text-start">16/11/2004</div>
				</div>
				<div class="row mb-2">
					<div class="col-md-4 fw-bold">Appointment Time :</div>
					<div class="col-md-8 text-start">10.00 AM</div>
				</div>
				<div class="row mb-2">
					<div class="col-md-4 fw-bold">Pharmacist Name :</div>
					<div class="col-md-8 text-start">Dr. Ali</div>
				</div>
			</div>
		</div>

		<form action="" method="post">
			<div class="card">
				<div class="card-body">
					<div class="mb-3">
						<label class="form-label">Date : </label> <input type="text"
							name="" class="form-control rounded-pill" placeholder="ENTER">
					</div>
					<c:choose>

						<c:when test="${packageType == 'URIC'}">
							<div class="mb-3">
								<label class="form-label">Uric Acid Result</label> <input
									type="text" name="uricLevel" class="form-control rounded-pill">
							</div>
						</c:when>

						<c:when test="${packageType == 'LIPID'}">
							<div class="mb-3">
								<label class="form-label">HDL</label> <input type="text"
									name="hdl" class="form-control rounded-pill">
							</div>

							<div class="mb-3">
								<label class="form-label">LDL</label> <input type="text"
									name="ldl" class="form-control rounded-pill">
							</div>
						</c:when>

						<c:when test="${packageType == 'HBA1C'}">
							<div class="mb-3">
								<label class="form-label">HbA1c Value</label> <input type="text"
									name="hba1cValue" class="form-control rounded-pill">
							</div>
						</c:when>

					</c:choose>
					<div class="mb-3">
						<label class="form-label">Comment :</label>
						<textarea name="comment" class="form-control rounded-pill"
							rows="3" cols="40" placeholder="ENTER"></textarea>
					</div>
				</div>
			</div>
			<div class="text-center mt-4">
				<a href="appointmentList.jsp" class="btn px-4 me-3"
					style="background-color: #17a2b8; color: white;">Back</a>
				<button type="submit" class="btn px-4"
					style="border: 1px solid #17a2b8; color: #17a2b8; background-color: transparent;">Submit</button>
			</div>
		</form>
	</div>
</div>
</body>
</html>