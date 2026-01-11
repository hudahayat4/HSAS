<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Appointment Record | JuzCare</title>
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/listapt.css">
    <link rel="stylesheet" href="../css/header.css">
    <link rel="stylesheet" href="../css/footer.css">
</head>
<body>
    <%@ include file="../header.jsp" %>

    <div class="appointment-container">
        <h2 class="section-title">Appointment Record</h2>

        <div class="appointment-list">
            
            <div class="appointment-card">
                <div class="date-badge">
                    <span class="day">Tues</span>
                    <span class="date">25</span>
                </div>

                <div class="appointment-details">
                    <div class="detail-row">
                        <span class="icon">🕒</span>
                        <span class="text">11:00 AM</span>
                    </div>
                    <div class="detail-row">
                        <span class="icon">👤</span>
                        <span class="text">Nur Nazmeen</span>
                    </div>
                </div>
                
                <div class="appointment-info">
                    <a href="#" class="test-link">Lipid Profile</a>
                    <p class="appointment-date">25/03/2025</p>
                </div>

                <div class="action-area">
                    <button class="view-btn" onclick="viewAppointment('101')" title="View">
                        <span class="eye-icon">👁️</span>
                    </button>
                    <button class="cancel-btn" onclick="cancelAppointment('101')" title="Cancel">
                        <span class="trash-icon">🗑️</span>
                    </button>
                </div>
            </div>

            <div class="appointment-card">
                <div class="date-badge">
                    <span class="day">Fri</span>
                    <span class="date">25</span>
                </div>

                <div class="appointment-details">
                    <div class="detail-row">
                        <span class="icon">🕒</span>
                        <span class="text">15:00 PM</span>
                    </div>
                    <div class="detail-row">
                        <span class="icon">👤</span>
                        <span class="text">Nurul Huda Aliya</span>
                    </div>
                </div>

                <div class="appointment-info">
                    <a href="#" class="test-link">Blood uric acid</a>
                    <p class="appointment-date">21/03/2025</p>
                </div>

                <div class="action-area">
                    <button class="view-btn" onclick="viewAppointment('102')" title="View">
                        <span class="eye-icon">👁️</span>
                    </button>
                    <button class="cancel-btn" onclick="cancelAppointment('102')" title="Cancel">
                        <span class="trash-icon">🗑️</span>
                    </button>
                </div>
            </div>

            <div class="appointment-card">
                <div class="date-badge">
                    <span class="day">Fri</span>
                    <span class="date">25</span>
                </div>

                <div class="appointment-details">
                    <div class="detail-row">
                        <span class="icon">🕒</span>
                        <span class="text">13:00 PM</span>
                    </div>
                    <div class="detail-row">
                        <span class="icon">👤</span>
                        <span class="text">Nur Afiqah</span>
                    </div>
                </div>

                <div class="appointment-info">
                    <a href="#" class="test-link">HBA1c</a>
                    <p class="appointment-date">31/03/2025</p>
                </div>

                <div class="action-area">
                    <button class="view-btn" onclick="viewAppointment('103')" title="View">
                        <span class="eye-icon">👁️</span>
                    </button>
                    <button class="cancel-btn" onclick="cancelAppointment('103')" title="Cancel">
                        <span class="trash-icon">🗑️</span>
                    </button>
                </div>
            </div>

        </div>
    </div>

    <%@ include file="../footer.jsp" %>

    <script src="listapt.js"></script>
</body>
</html>