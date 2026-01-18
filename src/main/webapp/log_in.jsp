<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Juzcare Pharmacy - Login</title>
<link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">

<link rel="stylesheet" type="text/css" href="css/stylesheet.css">
<style>
  .password-toggle {
    cursor: pointer;
  }
</style>
</head>
<body>
  <section class="min-vh-100 d-flex align-items-center bg-page">
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-lg-8">
          <div class="card border-light-subtle shadow-sm h-100">
            <div class="row g-0 align-items-stretch h-100">
              
              <!-- GAMBAR -->
              <div class="col-md-5 text-center card-image-bg h-100">
                <img src="image/Juzcare.jpg" alt="Juzcare Logo" class="img-fluid p-3">
              </div>
              
              <!-- FORM -->
              <div class="col-md-7 h-100">
                <div class="card-body">
                  <h2 class="h4 text-center mb-2">Log in to Juzcare Pharmacy</h2>
                  <p class="mb-4 text-secondary text-center">Welcome back!</p>

                  <!-- PILIH CUSTOMER / STAFF -->
                  <div class="mb-4">
                    <div class="row g-2">
                      <div class="col-6">
                        <button type="button" class="btn btn-role active" id="customerBtn">CUSTOMER</button>
                      </div>
                      <div class="col-6">
                        <button type="button" class="btn btn-role" id="staffBtn">STAFF</button>
                      </div>
                    </div>
                  </div>

                  <!-- FORM CUSTOMER -->
                  <div id="customerForm">
					<form action="${pageContext.request.contextPath}/LogInController" method="post">
                    <input type="hidden" name="action" value="loginCustomer">
                      <div class="mb-3">
                        <label for="custUsername" class="form-label">Username</label>
                        <input type="text" class="form-control" id="custUsername" name="custUsername" required>
                      </div>
                      <div class="mb-3">
                        <label for="custPassword" class="form-label">Password</label>
                        <div class="input-group">
                          <input type="password" class="form-control" id="custPassword" name="custPassword" required>
                          <span class="input-group-text password-toggle" id="toggleCustPassword">
                            <i class="bi bi-eye-slash-fill"></i>
                          </span>
                        </div>
                      </div>
                      <div class="d-grid mb-3">
                        <button type="submit" class="btn btn-dark btn-lg">Log in</button>
                      </div>
                      <p class="text-center mb-2">
                        <a href="#!" class="link-primary text-decoration-none">Forgot password?</a>
                      </p>
                      <p class="text-center mb-0">
                        <a href="account/register.jsp" class="link-primary text-decoration-none">Create New Account</a>
                      </p>
                    </form>
                  </div>

                  <!-- FORM STAFF -->
                  <div id="staffForm" style="display: none;">
					<form action="${pageContext.request.contextPath}/LogInController" method="post">
                    <input type="hidden" name="action" value="loginStaff">
                      <div class="mb-3">
                        <label for="Username" class="form-label">Username</label>
                        <input type="text" class="form-control" id="Username" name="staffUsername" required>
                      </div>
                      <div class="mb-3">
                        <label for="Password" class="form-label">Password</label>
                        <div class="input-group">
                          <input type="password" class="form-control" id="Password" name="staffPassword" required>
                          <span class="input-group-text password-toggle" id="toggleStaffPassword">
                            <i class="bi bi-eye-slash-fill"></i>
                          </span>
                        </div>
                      </div>
                      <div class="d-grid mb-3">
                        <button type="submit" class="btn btn-dark btn-lg">Log in</button>
                      </div>
                      <p class="text-center mb-0">
                        <a href="#!" class="link-primary text-decoration-none">Forgot password?</a>
                      </p>
                    </form>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>

  <script>
    const customerBtn = document.getElementById('customerBtn');
    const staffBtn = document.getElementById('staffBtn');
    const customerForm = document.getElementById('customerForm');
    const staffForm = document.getElementById('staffForm');

    customerBtn.addEventListener('click', () => {
      customerBtn.classList.add('active');
      staffBtn.classList.remove('active');
      customerForm.style.display = 'block';
      staffForm.style.display = 'none';
    });

    staffBtn.addEventListener('click', () => {
      staffBtn.classList.add('active');
      customerBtn.classList.remove('active');
      staffForm.style.display = 'block';
      customerForm.style.display = 'none';
    });

    //password visibility
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

    setupToggle('custPassword', 'toggleCustPassword');
    setupToggle('Password', 'toggleStaffPassword');
  </script>
</body>
</html>
