<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Team Member | JuzCare</title>
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/viewTeamAccount.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/sideStaff.css">
    <style>
        /* Essential modal styles kept here to ensure it works */
        .custom-modal {
            display: none;
            position: fixed;
            z-index: 9999;
            left: 0; top: 0; width: 100%; height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            align-items: center;
            justify-content: center;
        }
        .modal-box {
            background: white;
            padding: 30px;
            border-radius: 15px;
            width: 100%;
            max-width: 450px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.2);
        }
        .password-container {
        position: relative;
        display: flex;
        align-items: center;
    }

    .password-container input {
        padding-right: 40px; /* Make space for the icon */
    }

    .toggle-password {
        position: absolute;
        right: 15px;
        cursor: pointer;
        color: #666;
        z-index: 10;
    }
    </style>
</head>
<body>
<div class="wrapper">
<%@ include file="../sideStaff.jsp"%>
    <main class="content-wrapper">
        <div class="profile-container">

            <div class="profile-header">
                <div class="user-info">
                    <div class="avatar-circle">
                        <i class="fas fa-user"></i>
                    </div>
                    <div class="name-meta">
                        <h1>${ microserviceData.name }</h1>
                        <p>${ microserviceData.email }</p>
                    </div>
                </div>

                <div class="action-buttons">
                    <a href="updateTeamAccount.jsp" class="btn-edit-link">Edit</a>
                    <button type="button" class="link-password-btn" onclick="toggleModal(true)">Change Password</button>
                </div>
            </div>

            <div class="form-grid">
                <div class="form-group">
                    <label>Full Name</label> 
                    <input type="text" id="name" readonly class="locked-field">
                </div>
                <div class="form-group">
                    <label>Phone</label> 
                    <input type="text" id="phoneNo" readonly class="locked-field">
                </div>
                <div class="form-group">
                    <label>Email Address</label> 
                    <input type="email" id="email" readonly class="locked-field">
                </div>
                <div class="form-group">
                    <label>Age</label> 
                    <input type="text" value="28" readonly class="locked-field">
                </div>
                <div class="form-group">
                    <label>Date of Birth</label> 
                    <input type="text" id="DOB" readonly class="locked-field">
                </div>
                <div class="form-group">
                    <label>IC Number</label> 
                    <input type="text" id="NRIC" readonly class="locked-field">
                </div>
            </div>

            <div class="d-flex justify-content-end mt-4">
                <a href="listTeamAccount.jsp" class="btn btn-outline-danger"
                    style="border-radius: 10px; padding: 8px 30px; font-weight: bold; text-decoration: none;">
                    Back to List
                </a>
            </div>
        </div>
    </main>

    <div id="passwordModal" class="custom-modal">
    <div class="modal-box">
        <h3 class="mb-4">Change Password</h3>
        <form action="ChangePasswordServlet" method="POST">
            
            <div class="mb-3">
                <label class="form-label fw-bold">Current Password</label>
                <div class="password-container">
                    <input type="password" name="currentPassword" class="form-control" id="currentPassword" required>
                    <i class="fas fa-eye toggle-password" onclick="toggleVisibility('currentPassword', this)"></i>
                </div>
            </div>

            <div class="mb-3">
                <label class="form-label fw-bold">New Password</label>
                <div class="password-container">
                    <input type="password" name="newPassword" class="form-control" id="newPassword" required>
                    <i class="fas fa-eye toggle-password" onclick="toggleVisibility('newPassword', this)"></i>
                </div>
            </div>

            <div class="mb-4">
                <label class="form-label fw-bold">Confirm New Password</label>
                <div class="password-container">
                    <input type="password" name="confirmPassword" class="form-control" id="confirmPassword" required>
                    <i class="fas fa-eye toggle-password" onclick="toggleVisibility('confirmPassword', this)"></i>
                </div>
            </div>
            
            <div class="d-flex justify-content-end gap-2">
                <button type="button" class="btn btn-light" onclick="toggleModal(false)">Cancel</button>
                <button type="submit" class="btn btn-primary" style="background-color: #008080; border: none;">Update</button>
            </div>
        </form>
    </div>
</div>
</div>
    <script>
        // Use a single function for show and hide
        function toggleModal(show) {
            const modal = document.getElementById('passwordModal');
            if(modal) {
                modal.style.display = show ? 'flex' : 'none';
            }
        }

        // Close if user clicks outside the modal box
        window.onclick = function(event) {
            const modal = document.getElementById('passwordModal');
            if (event.target === modal) {
                toggleModal(false);
            }
        }
        
        fetch('StaffController?action=view&json=true')
        .then(res => res.json())
        .then(data => {
          document.getElementById('name').value = data.name;
          document.getElementById('email').value = data.email;
          document.getElementById('DOB').value = data.DOB;
          document.getElementById('NRIC').value = data.NRIC;
          document.getElementById('phoneNo').value = data.phoneNo;
        })
        .catch(err => console.error(err));

    </script>
</body>
</html>