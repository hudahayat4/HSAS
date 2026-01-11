<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Juzcare Pharmacy - Login</title>
<link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="css/stylesheet.css">
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
                    <form action="#!">
                      <div class="mb-3">
                        <label for="username" class="form-label">Username</label>
                        <input type="text" class="form-control" id="username" required>
                      </div>
                      <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <input type="password" class="form-control" id="password" required>
                      </div>
                      <div class="d-grid mb-3">
                        <button type="submit" class="btn btn-dark btn-lg">Log in</button>
                      </div>
                      <p class="text-center mb-2">
                        <a href="#!" class="link-primary text-decoration-none">Forgot password?</a>
                      </p>
                      <p class="text-center mb-0">
                        <a href="register.jsp" class="link-primary text-decoration-none">Create New Account</a>
                      </p>
                    </form>
                  </div>

                  <!-- FORM STAFF -->
                  <div id="staffForm" style="display: none;">
                    <form action="#!">
                      <div class="mb-3">
                        <label for="staffUsername" class="form-label">Username</label>
                        <input type="text" class="form-control" id="staffUsername" required>
                      </div>
                      <div class="mb-3">
                        <label for="staffPassword" class="form-label">Password</label>
                        <input type="password" class="form-control" id="staffPassword" required>
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
  </script>
</body>
</html>
