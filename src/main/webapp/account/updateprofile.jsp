<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Profile | JuzCare</title>
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/updateprofile.css?v=2">
    <link rel="stylesheet" href="../css/footer.css">
    <link rel="stylesheet" href="../css/header.css">
</head>
<body>

    <%@ include file="../header.jsp" %>

    <main class="content-wrapper">
        <div class="profile-container">
            <div class="profile-header">
                <div class="user-info">
                   <div class="avatar-circle">
                        <i class="fas fa-user"></i>
                   </div>
                    <div class="name-meta">
                        <h1>AMELIA HENDERSON</h1>
                        <p>manager@gmail.com</p>
                    </div>
                </div>
                
            </div>

            <form action="UpdateProfileServlet" method="POST">
                <div class="form-grid">
                    <div class="form-group">
                        <label>Full Name</label>
                        <input type="text" name="firstName" value="Amelia Henderson" readonly class="locked-field">
                    </div>
                    

                    <div class="form-group">
                        <label>Phone</label>
                        <input type="text" name="phone" value="017445663" class="editable-field" required>
                    </div>
                    <div class="form-group">
                        <label>Email Address</label>
                        <input type="email" name="email" value="admin@gmail.com" class="editable-field" required>
                    </div>

                    <div class="form-group">
                        <label>Age</label>
                        <input type="text" value="28" readonly class="locked-field">
                    </div>
                    
                    <div class="form-group">
    					<label>Date of Birth</label>
    					<input type="text" name="dob" value="30/6/1997" readonly class="locked-field">
					</div>
                
                    <div class="form-group">
                        <label>IC Number</label>
                        <input type="text" value="970630-05-9797" readonly class="locked-field">
                    </div>
                </div>

                <div class="btn-save-container">
                	<a href="viewaccount.jsp" class="btn-cancel-link">Cancel</a>
                    <button type="submit" class="btn-save-main">Save Changes</button>
                    
               
                </div>
            </form>
        </div>
    </main>

    <%@ include file="../footer.jsp" %>
</body>
</html>