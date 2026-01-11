function handleCancel(id) {
    const confirmAction = confirm("Are you sure you want to cancel appointment #" + id + "?");
    
    if (confirmAction) {
        // Redirect to your servlet to handle the database deletion/update
        window.location.href = "AppointmentServlet?action=cancel&id=" + id;
    }
}