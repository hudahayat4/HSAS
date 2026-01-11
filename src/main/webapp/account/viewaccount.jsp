<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Profile | JuzCare</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/viewTeamAccount.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css">

    <style>
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
        /* Style for Eye Icon positioning */
        .password-wrapper {
            position: relative;
            display: flex;
            align-items: center;
        }
        .password-wrapper input {
            padding-right: 40px;
        }
        .toggle-password {
            position: absolute;
            right: 12px;
            cursor: pointer;
            color: #666;
            z-index: 10;
        }
    </style>
</head>
<body>
    <%@ include file="../header.jsp" %>

    <main class="content-wrapper">
        <div class="profile-container">
            <div class="profile-header">
                <div class="user-info">
                    <div class="avatar-circle"><i class="fas fa-user"></i></div>
                    <div class="name-meta">
                        <h1>ALI BIN RAFI</h1>
                        <p>ali@email.com</p>
                    </div>
                </div>

                <div class="action-buttons">
                    <a href="updateprofile.jsp" class="btn-edit-link">Edit</a>
                    <button type="button" class="link-password-btn" onclick="toggleModal(true)">Change Password</button>
                </div>
            </div>

            <div class="form-grid">
                <div class="form-group"><label>Full Name</label> <input type="text" value="Amelia Henderson" readonly class="locked-field"></div>
                <div class="form-group"><label>Phone</label> <input type="text" value="017445663" readonly class="locked-field"></div>
                <div class="form-group"><label>Email Address</label> <input type="email" value="admin@gmail.com" readonly class="locked-field"></div>
                <div class="form-group"><label>Age</label> <input type="text" value="28" readonly class="locked-field"></div>
                <div class="form-group"><label>Date of Birth</label> <input type="text" value="30/6/1997" readonly class="locked-field"></div>
                <div class="form-group"><label>IC Number</label> <input type="text" value="970630-05-9797" readonly class="locked-field"></div>
            </div>

            <div class="d-flex justify-content-end mt-4">
                <a href="listTeamAccount.jsp" class="btn btn-outline-danger" style="border-radius: 10px; padding: 8px 30px; font-weight: bold; text-decoration: none;">Back to List</a>
            </div>
        </div>
    </main>

    <%@ include file="../footer.jsp" %>

    <div id="passwordModal" class="custom-modal">
        <div class="modal-box">
            <h3 class="mb-4 text-center">Change Password</h3>
            <form action="ChangePasswordServlet" method="POST">
                
                <div class="mb-3">
                    <label class="form-label fw-bold">Current Password</label>
                    <div class="password-wrapper">
                        <input type="password" name="currentPassword" id="currentPassword" class="form-control" required>
                        <i class="fas fa-eye toggle-password" onclick="toggleVisibility('currentPassword', this)"></i>
                    </div>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-bold">New Password</label>
                    <div class="password-wrapper">
                        <input type="password" name="newPassword" id="newPassword" class="form-control" required>
                        <i class="fas fa-eye toggle-password" onclick="toggleVisibility('newPassword', this)"></i>
                    </div>
                </div>

                <div class="mb-4">
                    <label class="form-label fw-bold">Confirm New Password</label>
                    <div class="password-wrapper">
                        <input type="password" name="confirmPassword" id="confirmPassword" class="form-control" required>
                        <i class="fas fa-eye toggle-password" onclick="toggleVisibility('confirmPassword', this)"></i>
                    </div>
                </div>
                
               <div class="d-flex justify-content-end gap-2 modal-footer-custom">
				    <button type="button" class="btn modal-btn-white" onclick="toggleModal(false)">
				        Cancel
				    </button>
				    <button type="submit" class="btn modal-btn-white modal-btn-update">
				        Update
				    </button>
			 </div>

            </form>
        </div>
    </div>

    <script>
        // Open/Close Modal
        function toggleModal(show) {
            const modal = document.getElementById('passwordModal');
            if(modal) {
                modal.style.display = show ? 'flex' : 'none';
            }
        }

        // Toggle Eye Icon
        function toggleVisibility(inputId, iconElement) {
            const input = document.getElementById(inputId);
            if (input.type === "password") {
                input.type = "text";
                iconElement.classList.replace("fa-eye", "fa-eye-slash");
            } else {
                input.type = "password";
                iconElement.classList.replace("fa-eye-slash", "fa-eye");
            }
        }

        // Close on outside click
        window.onclick = function(event) {
            const modal = document.getElementById('passwordModal');
            if (event.target === modal) {
                toggleModal(false);
            }
        }
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>