<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Data Logic
    String appointmentId = request.getParameter("id");
    String patientName = "Nur Nazmeen";
    String packageName = "Lipid Profile";
    String apptDate = "25 March 2025";
    String apptTime = "11:00 AM";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Appointment | JuzCare</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/viewapt.css">
    <link rel="stylesheet" href="../css/footer.css">
    <link rel="stylesheet" href="../css/header.css">
    <style type="text/css">
    <%@ include file="../css/viewapt.css" %>
    </style>
</head>
<body>

    <%@ include file="../header.jsp" %>

    <div class="appointment-container">
        
        <div class="breadcrumb-area">
            <strong>Appointment</strong>
        </div>

        <div class="receipt-card">
            <div class="header-center">
                <img src="../image/logo.png" alt="JuzCare" class="logo">
                <h3 class="thank-you-text">Thank you for your appointment in JuzCare Pharmacy</h3>
            </div>

            <div class="appointment-meta">
                <p class="section-title-small">DETAIL APPOINTMENT</p>
                <div class="date-time-row">
                    <span>📅 <%= apptDate %></span>
                    <span>🕒 <%= apptTime %></span>
                </div>
                <hr>
            </div>

            <div class="patient-info">
                <p><strong>Name :</strong> <%= patientName %></p>
                <p><strong>Package :</strong> <%= packageName %></p>
                <p><strong>Pharmacist :</strong> Dr. Ali bin Abdullah</p>
                <p><strong>Price :</strong> RM 25.00</p>
            </div>

            <div class="terms">
                <p><strong>Term &amp; Condition :</strong></p>
                <ol>
                    <li>Your appointment is confirmed once booking is made.</li>
                    <li>Cancellations must be 24 hours before.</li>
                </ol>
            </div>
        </div>

        <div class="button-group-action">
            <a href="listapt.jsp" class="btn-cancel-custom">Back to List</a>
            <button type="button" class="btn-medical-custom" onclick="window.print()">Print Receipt</button>
        </div>
    </div>

    <%@ include file="../footer.jsp" %>

</body>
</html>