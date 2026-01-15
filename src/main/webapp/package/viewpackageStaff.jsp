<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>View Package Staff</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/viewpackageStaff.css">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

</head>

<body>

<div class="main-container">
	<div class="content-wrapper">
		<div class="content-card">
	<h1 class="page-title">Health Screening Packages</h1>

	<!-- Search -->
	<div class="search-container">
    	<i class="fa-solid fa-magnifying-glass"></i>
    	<input type="text" id="package-search" placeholder="Search">
	</div>

	<!-- Package List -->
	 <c:forEach var="package" items="${packages}">
		<div class="packages-list">
    		<div class="package-card">
        		<div class="package-image">
            <!-- <img src="${pageContext.request.contextPath}/package/PackageController?action=image&id=${package.packageID}" alt="Package Image"> -->
        		</div>

        	<div class="package-details">
           		 <p>Package Name: <strong>${package.packageName}</strong></p>
            		<p>Price: RM <fmt:formatNumber value="${package.packagePrice}" type="number" minFractionDigits="2" maxFractionDigits="2"/></p>
        	</div>

        	<div class="update-btn-wrapper">
        	 <button class="btn btn-sm" style="background:#009FA5;color:white;" data-bs-toggle="modal" data-bs-target="#updateModal-${package.packageID}">Update</button>
        	</div>
    	</div>
    	</div>
    	</c:forEach>
 
</div>

<!-- Add Button -->
<div class="add-btn-wrapper">
    <button class="btn" style="background:#009FA5;color:white;" data-bs-toggle="modal" data-bs-target="#addModal">Add Package</button>
</div>

</div>
</div>
</div>

<!-- ADD MODAL -->
	<div class="modal fade" id="addModal" tabindex="-1">
	<div class="modal-dialog modal-dialog-centered">
	<div class="modal-content">
	<div class="modal-header">
    	<h5>Add Package</h5>
    	<button class="btn-close" data-bs-dismiss="modal"></button>
	</div>
		<div class="modal-body">
			<form action="PackageController" method="post" enctype="multipart/form-data">
    			<div class="mb-3">
				<label for="recipient-name" class="col-form-label">Package Name:</label>
	 			<input type="text" class="form-control" id="packageName" placeholder="Health Screening Type" name="packageName">
				</div>
				<div class="mb-3">
				<label for="message-text" class="col-form-label">Price:</label>
				<input type="number" class="form-control" id="packagePrice" placeholder="RM" name="packagePrice">
				</div>
				<div class="mb-3">
				<label for="formFile" class="form-label">Default file input example</label> 
				<input class="form-control" type="file" id="packagePic" name="packagePic">
				</div>
				<div class="mb-3">
				<label for="message-text" class="col-form-label">Fasting:</label><br>
				<input class="form-check-input" type="radio" name="isbfrReq" id="exampleRadios1" value="YES"> 
				<label class="form-check-label" for="exampleRadios1"> Yes </label> 
				<input class="form-check-input" type="radio" name="isbfrReq" id="No" value="NO"> <label class="form-check-label" for="exampleRadios2"> No </label>
				</div>
				<div class="mb-3">
				<label for="message-text" class="col-form-label">Exist:</label><br>
				<input class="form-check-input" type="radio" name="isExist" id="isExist" value="YES"> 
				<label class="form-check-label" for="exampleRadios1"> Yes </label> 
				<input class="form-check-input" type="radio" name="isExist" id="No" value="NO">
				<label class="form-check-label"	for="isExist"> No </label>
				</div>
				<div class="mb-3">
				<button type="submit" class="btn w-100" style="background: #009FA5; color: white;">Submit</button>
				</div>
			</form>
		</div>
	</div>
</div>
</div>

<!-- UPDATE MODAL -->
<div class="modal fade" id="updateModal-${package.packageID}" tabindex="-1">
<div class="modal-dialog modal-dialog-centered">
<div class="modal-content">
<div class="modal-header">
    <h5>Update Package</h5>
    <button class="btn-close" data-bs-dismiss="modal"></button>
</div>
<div class="modal-body">
<form action="PackageController" method="post" enctype="multipart/form-data">
   
   <!-- IMPORTANT -->
    <input type="hidden" name="packageID" value="${package.packageID}">
    <div class="mb-3">
	<label for="recipient-name" class="col-form-label">Package Name:</label> <input type="text" class="form-control" id="packageName" placeholder="Health Screening Type" name="packageName" value="${package.packageName}">
	</div>
	<div class="mb-3">
	<label for="message-text" class="col-form-label">Price:</label> <input
	type="number" class="form-control" id="packagePrice" placeholder="RM" name="packagePrice" value="${package.packagePrice}">
	</div>
	<div class="mb-3">
	<label for="formFile" class="form-label">Default file input example</label> <input class="form-control" type="file" id="packagePic" name="packagePic">
	</div>
	<div class="mb-3">
	<label for="message-text" class="col-form-label">Fasting:</label><br>
	<input class="form-check-input" type="radio" name="isbfrReq" id="exampleRadios1" value="YES"   ${package.isbfrReq == 'YES' ? 'checked' : ''}> Yes> 
	<label class="form-check-label" for="exampleRadios1"> Yes </label> 
	<input class="form-check-input" type="radio" name="isbfrReq" id="No" value="NO"  ${package.isbfrReq == 'NO' ? 'checked' : ''}> No>
	 <label class="form-check-label" for="exampleRadios2"> No </label>
	</div>
	<div class="mb-3">
	<label for="message-text" class="col-form-label">Exist:</label><br>
	<input class="form-check-input" type="radio" name="isExist" id="isExist" value="YES"   ${package.isExist == 'YES' ? 'checked' : ''}> Yes> 
	<label class="form-check-label" for="exampleRadios1"> Yes </label> 
	<input class="form-check-input" type="radio" name="isExist" id="No" value="NO" ${package.isExist == 'NO' ? 'checked' : ''}> No>
	<label class="form-check-label"	for="isExist"> No </label>
	</div>
	<div class="mb-3">
	<button type="submit" class="btn w-100" style="background: #009FA5; color: white;">Submit</button>
	</div>
</form>
</div>
</div>
</div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<script>
document.getElementById('package-search').addEventListener('input', function () {
    const filter = this.value.toLowerCase();
    document.querySelectorAll('.package-card').forEach(card => {
        const name = card.querySelector('strong').innerText.toLowerCase();
        card.style.display = name.includes(filter) ? 'flex' : 'none';
    });
});
</script>

</body>
</html>
