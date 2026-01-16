<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Appointment Record | JuzCare</title>
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/listapt.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
</head>
<body>
    <%@ include file="../header.jsp" %>

    <div class="appointment-container">
        <h2 class="section-title" style="text-align:center; margin: 30px 0; color: #333;">Appointment Record</h2>

        <div class="appointment-list">
            <%-- LOOP DATA DARI DATABASE --%>
            <%-- Pastikan 'appointments' adalah nama attribute yang dihantar dari Servlet --%>
            <c:forEach var="apt" items="${appointments}">
                <div class="appointment-card">
                    <div class="appointment-date">
                        <%-- Tukar property name mengikut class Model anda --%>
                        <span class="day">${apt.dayName}</span> 
                        <span class="date">${apt.dayDate}</span>
                    </div>

                    <div class="appointment-info">
                        <div class="time-row">
                            <i class="far fa-clock"></i>
                            <span class="text">${apt.time}</span>
                        </div>
                        <div class="patient-row">
                            <i class="fas fa-user"></i>
                            <span class="text">${apt.patientName}</span>
                        </div>
                    </div>
                    
                    <div class="appointment-package">
                        <a href="#" class="package-name">${apt.packageName}</a>
                        <p class="package-date">${apt.fullDate}</p>
                    </div>

                    <div class="appointment-action">
                        <button class="btn-view" onclick="viewAppointment('${apt.aptID}')" title="View">
                            <i class="fas fa-eye"></i>
                        </button>
                        <button class="btn-delete" onclick="cancelAppointment('${apt.aptID}')" title="Cancel">
                            <i class="fas fa-trash-alt"></i>
                        </button>
                    </div>
                </div>
            </c:forEach>

            <%-- Jika tiada data --%>
            <c:if test="${empty appointments}">
                <div style="text-align:center; padding: 50px; color: #888;">
                    <p>No appointment records found.</p>
                </div>
            </c:if>
        </div>
    </div>

    <%@ include file="../footer.jsp" %>

    <script src="${pageContext.request.contextPath}/js/listapt.js"></script>
</body>
</html>