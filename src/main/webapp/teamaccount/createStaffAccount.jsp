<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Team Account</title>

<link rel="stylesheet" href="../css/sideStaff.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
<style type="text/css">
body {
	background-color: #E8F3F3;
}

.password-toggle {
	cursor: pointer;
}

.main {
	padding-left: 2rem;
	padding-top: 1rem;
}
</style>
</head>
<body>
	<div class="wrapper">

		<%@ include file="../sideManager.jsp"%>

		<div class="main">
			<h2>Team Member's Personal</h2>
			<h2>Information</h2>
			<form action="StaffController" method="post"
				enctype="multipart/form-data">
				<input type="hidden" class="form-control" id="staffID"
					name="staffID" value="<%=session.getAttribute("staffID")%>"
					readonly>

				<div class="col-md-6">
					<label for="name" class="form-label">Full name</label> <input
						type="text" class="form-control" id="name" name="name"
						value="${param.name}">
					<c:if test="${not empty nameError}">
						<span class="text-danger" style="font-size: 0.8rem;">${nameError}</span>
					</c:if>
				</div>
				<div class="col-md-6">
					<label for="PhoneNo" class="form-label">Phone</label> <input
						type="text" class="form-control" id="PhoneNo" name="PhoneNo"
						placeholder="e.g. 0123456789"
						oninput="this.value = this.value.replace(/[^0-9]/g, '');" required>
					<div class="invalid-feedback">Please enter numbers only.</div>
				</div>
				<div class="col-md-6">
					<label for="email" class="form-label">Email address</label> <input
						type="email" class="form-control" id="email" name="email"
						value="${param.email}">
					<c:if test="${not empty emailError}">
						<span class="text-danger" style="font-size: 0.8rem;">${emailError}</span>
					</c:if>
				</div>

				<div class="col-md-6">
					<label for="DOB" class="form-label">Date of birth</label> <input
						type="date" class="form-control" id="DOB" name="DOB" required>
				</div>

				<div class="col-md-6">
					<label for="nric" class="form-label">IC number</label> <input
						type="text" class="form-control" id="nric" name="NRIC"
						maxlength="12" placeholder="YYMMDDXXXXXX" required>
				</div>
				<br>
				<div class="row">
					<div class="col-md-3">
						<select class="form-select" name="role">
							<option selected disabled>Position</option>
							<option value="STAFF">Staff</option>
							<option value="PHARMACIST">Pharmacist</option>
						</select>
					</div>

					<div class="col-md-3">
						<input class="form-control" type="file" name="profilePic">
					</div>
				</div>
				<div class="mt-4 d-flex justify-content-end gap-2">
					<button type="button" class="btn btn-primary"
						data-bs-target="#exampleModalToggle" data-bs-toggle="modal">Save</button>
					<button type="button" class="btn btn-secondary">Cancel</button>
				</div>

				<div class="modal fade" id="exampleModalToggle" aria-hidden="true"
					aria-labelledby="exampleModalToggleLabel" tabindex="-1">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">Create
									Account</h5>
							</div>
							<div class="modal-body">
								<div class="form-group">
									<label for="recipient-name" class="col-form-label">Username:</label>
									<input type="text" class="form-control" id="username"
										name="username">
								</div>
								<div class="form-group">
									<label for="message-text" class="col-form-label">Password:</label>
									<div class="input-group">
										<input type="password" class="form-control" id="password"
											name="password" readonly> <span
											class="input-group-text password-toggle"
											id="toggleStaffPassword"> <i
											class="bi bi-eye-slash-fill"></i>
										</span>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Cancel</button>
								<button type="submit" class="btn btn-primary">Save</button>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
	function setupToggle(inputId, toggleId) {
	      const input = document.getElementById(inputId);
	      const toggle = document.getElementById(toggleId);
	      toggle.addEventListener('click', () => {
	        const icon = toggle.querySelector('i');
	        if (input.type === 'password') {
	          input.type = 'text';
	          icon.classList.remove('bi-eye-slash-fill');
	          icon.classList.add('bi-eye-fill');
	        } else {
	          input.type = 'password';
	          icon.classList.remove('bi-eye-fill');
	          icon.classList.add('bi-eye-slash-fill');
	        }
	      });
	    }
	    setupToggle('password', 'toggleStaffPassword');
	    
	    var modal = document.getElementById('exampleModalToggle');

	    modal.addEventListener('show.bs.modal', function () {
	        var nric = document.getElementById('nric').value;
	        if(nric.length >= 8){
	            // Take last 8 digits of IC as password
	            document.getElementById('password').value = nric.slice(-8);
	        } else {
	            document.getElementById('password').value = nric; // fallback
	        }
	    });
	    
	    <script>
	    // 1. Phone: Block Alphabets immediately
	    document.getElementById('PhoneNo').addEventListener('input', function(e) {
	        this.value = this.value.replace(/[^0-9]/g, '');
	    });

	    // 2. IC: Auto-type YYMMDD from DOB
	    document.getElementById('DOB').addEventListener('change', function() {
	        const dateValue = this.value; // YYYY-MM-DD
	        if (dateValue) {
	            const parts = dateValue.split('-');
	            const year = parts[0].substring(2);
	            const month = parts[1];
	            const day = parts[2];
	            document.getElementById('nric').value = year + month + day;
	        }
	    });

	    // 3. Password Generation for Modal
	    var modal = document.getElementById('exampleModalToggle');
	    modal.addEventListener('show.bs.modal', function () {
	        var nric = document.getElementById('nric').value;
	        // Sets password to last 8 digits of IC
	        document.getElementById('password').value = nric.length >= 8 ? nric.slice(-8) : nric;
	    });
	</script>
</script>

</html>