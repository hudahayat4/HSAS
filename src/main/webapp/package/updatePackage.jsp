<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>update package</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="modal fade" id="exampleModalToggle" aria-hidden="true"
		aria-labelledby="exampleModalToggleLabel" tabindex="-1">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form action="PackageController" method="post" enctype="multipart/form-data">
						<div class="mb-3">
							<label for="recipient-name" class="col-form-label">Package
								Name:</label> <input type="text" class="form-control" id="packageName"
								placeholder="Health Screening Type" name="packageName">
						</div>
						<div class="mb-3">
							<label for="message-text" class="col-form-label">Price:</label> <input
								type="number" class="form-control" id="packagePrice"
								placeholder="RM" name="packagePrice">
						</div>
						<div class="mb-3">
							<label for="formFile" class="form-label">Default file
								input example</label> <input class="form-control" type="file"
								id="packagePic" name="packagePic">
						</div>
						<div class="mb-3">
							<label for="message-text" class="col-form-label">Fasting:</label><br>
							<input class="form-check-input" type="radio" name="isbfrReq"
								id="exampleRadios1" value="YES"> 
							<label class="form-check-label" for="exampleRadios1"> Yes </label> 
							<input class="form-check-input" type="radio" name="isbfrReq" id="No" value="NO"> <label class="form-check-label"
								for="exampleRadios2"> No </label>
						</div>
						<div class="mb-3">
							<label for="message-text" class="col-form-label">Exist:</label><br>
							<input class="form-check-input" type="radio" name="isExist" id="isExist" value="YES"> 
							<label class="form-check-label" for="exampleRadios1"> Yes </label> 
							<input class="form-check-input" type="radio" name="isExist" id="No" value="NO">
							<label class="form-check-label"	for="isExist"> No </label>
						</div>
						<div class="mb-3">
							<button type="submit" class="btn w-100"
								style="background: #009FA5; color: white;">Submit</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<button class="btn" style="background: #009FA5; color: white;"
		data-bs-target="#exampleModalToggle" data-bs-toggle="modal">Update</button>
		
<div class="modal fade" id="confirmationModal" tabindex="-1" aria-labelledby="confirmationModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-sm">
    <div class="modal-content text-center">
      <div class="modal-header">
        <h5 class="modal-title" id="confirmationModalLabel">Popup update price</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <p>Confirm update package?</p>
        <small>Please do not close this page.</small>
        <div class="mt-3 d-flex justify-content-center gap-2">
          <button type="button" class="btn btn-primary">Confirm</button>
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
        </div>
      </div>
    </div>
  </div>
</div>


</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
document.querySelector('#exampleModalToggle form').addEventListener('submit', function(e) {
  e.preventDefault(); // prevent actual submission
  const confirmationModal = new bootstrap.Modal(document.getElementById('confirmationModal'));
  confirmationModal.show();
});
</script>
</html>