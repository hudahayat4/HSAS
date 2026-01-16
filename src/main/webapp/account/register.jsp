<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Juzcare Pharmacy - Register</title>
<link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/stylesheet.css">
</head>
<body>
	<section class="bg-light p-3 p-md-4 p-xl-5 min-vh-100 d-flex align-items-center bg-cover">
	  <div class="container">
	    <div class="row justify-content-center">
	      <div class="col-12 col-md-8 col-lg-6">
	        <div class="card border-light-subtle shadow-sm">
	        	<div class="card-body p-3 p-md-4 p-xl-5">
	        		<h2 class="h4 text-center mb-4">Create an Account</h2>                   
	                <p class="mb-4 text-secondary text-center">Already have an account? <a href="log_in.jsp" class="link-primary text-decoration-none">Log in</a></p>
						<form action="CustomerController" method="post" enctype="multipart/form-data">
						<input type="hidden" name="action" value="registerAccount">
	                    <div class="row gy-3 overflow-hidden">
	                      <div class="col-12">
	                        <div class="form-floating mb-3">
	                          <input type="text" class="form-control" name="custName" id="custName" placeholder="Name" required>
	                          <label for="custName" class="form-label">Name</label>
	                        </div>
	                      </div>
	                      <div class="col-12">
	                        <div class="form-floating mb-3">
	                          <input type="email" class="form-control" name="custEmail" id="custEmail" placeholder="name@example.com" required>
	                          <label for="custEmail" class="form-label">Email</label>
	                        </div>
	                      </div>
	                      <div class="col-12">
	                        <div class="form-floating mb-3">
	                          <input type="text" class="form-control" name="custPhoneNo" id="custPhoneNo" placeholder="Phone" required>
	                          <label for="custPhoneNo" class="form-label">Phone No</label>
	                        </div>
	                      </div>
	                      <div class="col-12">
	                        <div class="form-floating mb-3">
	                          <input type="date" class="form-control" name="DOB" id="DOB" required>
	                          <label for="DOB" class="form-label">Date of Birth</label>
	                        </div>
	                      </div>
	                      <div class="col-12">
	                        <div class="form-floating mb-3">
	                          <input type="text" class="form-control" name="cusNRIC" id="cusNRIC" placeholder="IC Number" inputmode="numeric" maxlength="14" required>
	                          <label for="cusNRIC" class="form-label">IC Number</label>
	                        </div>
	                      </div>
	                      <div class="col-12">
	                      	<div class="mb-3">
	                      		<label for="custProfilePic" class="form-label">Upload Image</label>
	                      		<div class="mb-3 position-relative">
	                      			<input type="file" class="form-control" id="custProfilePic" name="custProfilePic" accept="image/*" required>
	                      		</div>
	                      		<small class="text-muted">Max file size: 10MB</small>
	                      	</div>
	                      </div>
	                      <div class="col-12">
	                        <div class="form-check">
	                          <input class="form-check-input" type="checkbox" value="" name="iAgree" id="iAgree" required>
	                          <label class="form-check-label text-secondary" for="iAgree">
	                            I agree to the <a href="#!" class="link-primary text-decoration-none">terms and conditions</a>
	                          </label>
	                        </div>
	                      </div>
	                      <div class="col-12">
	                        <div class="d-grid">
	                          <button class="btn btn-dark btn-lg" type="submit">Register</button>
	                        </div>
	                      </div>
	                      <div class="col-12">
	                        <div class="d-grid">
	                          <button class="btn btn-outline-clear btn-lg" type="reset">Clear Form</button>
	                        </div>
	                      </div>
	                    </div>
	                  </form>
	            </div>
	        </div>
	      </div>
	    </div>
	  </div>
	</section>
</body>
</html>