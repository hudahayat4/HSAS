<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home - Juzcare Pharmacy</title>
<link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/footer.css">
<link rel="stylesheet" href="css/home_customer.css">
</head>
<body>
	<%@ include file="header.jsp" %>
    <div class="container mt-5">
        <div class="card main-card shadow">
            <div class="row g-0">
                <!-- Kiri -->
                <div class="col-md-7 left-section">
                    <div class="card-body">
                        <h2 class="welcome">Welcome, <%= (String)session.getAttribute("name") %></h2>
                        <hr>
                        <p class="section-title">Package Name: </p>
                        <h5 class="package_name">
                            <%= (String)session.getAttribute("packageName") %>
                        </h5>

                        <p class="section-title mt-3">Appointment Time: </p>
                        <h5 class="appointment_time">
                            <%= (String)session.getAttribute("apptTime") %>
                        </h5>

                        <p class="countdown" id="countdown">Start in ...</p>
                        <hr>
                        <h5 class="section-title">Calendar</h5>
                        <div class="calendar">Calendar widget here</div>
                    </div>
                </div>

                <!-- Kanan -->
                <div class="col-md-5 right-section d-flex align-items-center justify-content-center">
                    <img src="images/promo.jpg" alt="Promosi Farmasi" class="img-fluid promo-img">
                </div>
            </div>
        </div>
    </div>

    <%@ include file="footer.jsp" %>

    <!-- Countdown -->
    <script>
        // Ambil appointment time dari session (format: yyyy-MM-dd HH:mm:ss)
        const apptTimeStr = "<%= (String)session.getAttribute("apptTime") %>";
        if(apptTimeStr){
            // Parse string ke Date object
         const apptDate = new Date(apptTimeStr.replace(" ", "T")); 
            const countdownEl = document.getElementById("countdown");

            function updateCountdown(){
                const now = new Date().getTime();
                const distance = apptDate.getTime() - now;

                if(distance <= 0){
                    countdownEl.innerHTML = "Appointment has started!";
                    return;
                }

                const days = Math.floor(distance / (1000 * 60 * 60 * 24));
                const hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                const minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
                const seconds = Math.floor((distance % (1000 * 60)) / 1000);

                countdownEl.innerHTML = `Start in ${days} days ${hours} hours ${minutes} minutes ${seconds} seconds`;
            }

            updateCountdown();
            setInterval(updateCountdown, 1000);
        }
    </script>
</body>
</html>
