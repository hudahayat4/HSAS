<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Appointment | JuzCare</title>
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/viewapt.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
</head>
<body>

    <%@ include file="../header.jsp" %>

    <div class="appointment-container">
        
        <div class="breadcrumb-area">
            <strong>Appointment Details</strong>
        </div>

        <div class="receipt-card">
            <div class="header-center">
                <img src="${pageContext.request.contextPath}/image/logo.png" alt="JuzCare" class="logo">
                <h3 class="thank-you-text">Thank you for your appointment in JuzCare Pharmacy</h3>
            </div>

            <%-- Kita andaikan Servlet hantar objek bernama 'apt' --%>
            <div class="appointment-meta">
                <p class="section-title-small">DETAIL APPOINTMENT</p>
                <div class="date-time-row">
                    <span>📅 ${apt.apptDate}</span>
                    <span>🕒 ${apt.apptTime}</span>
                </div>
                <hr>
            </div>

            <div class="patient-info">
                <%-- Gunakan sintaks ${objek.property} untuk tarik data database --%>
                <p><strong>Name :</strong> ${apt.patientName}</p>
                <p><strong>Package :</strong> ${apt.packageName}</p>
                <p><strong>Pharmacist :</strong> ${apt.pharmacistName}</p>
                <p><strong>Price :</strong> RM ${apt.packagePrice}</p>
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
            <a href="CustomerController?action=list" class="btn-cancel-custom">Back to List</a>
            <button type="button" class="btn-medical-custom" onclick="window.print()">Print Receipt</button>
        </div>
    </div>

    <%@ include file="../footer.jsp" %>

</body>
</html>