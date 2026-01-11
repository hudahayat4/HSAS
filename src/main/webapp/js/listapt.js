function viewAppointment(id) {
    // Make sure the parameter name matches what you use in JSP: request.getParameter("id")
    window.location.href = "viewapt.jsp?id=" + id;
}

function cancelAppointment(id) {
    if (confirm("Are you sure you want to cancel this appointment?")) {
        console.log("Appointment " + id + " cancelled.");
        // Add AJAX call here to delete from database
        alert("Appointment cancelled successfully.");
    }
}