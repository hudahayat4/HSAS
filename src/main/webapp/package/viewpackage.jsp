<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Health Screening Packages</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/viewpackage.css">
    <link rel="stylesheet" href="../css/footer.css">
    <link rel="stylesheet" href="../css/header.css">
</head>
<body>
<%@ include file="../header.jsp" %>

<div class="main-container">
    <div class="content-wrapper">
      <div class="content-card">
            <h1 class="page-title">Health Screening Packages</h1>
       
        <div class="search-container">
            <i class="fa-solid fa-magnifying-glass"></i>
            <input type="text" placeholder="Search" id="package-search">
        </div>

        <div class="packages-list">
            
            <div class="package-card">
                <div class="package-image">
                    <img src="../image/lipidProfile.jpg" alt="Lipid Profile">
                </div>
                <div class="package-details">
                    <p class="package-label">Package Name : <strong>Lipid Profile</strong></p>
                    <p class="package-price">Price: RM25</p>
                </div>
            </div>

            <div class="package-card">
                <div class="package-image">
                    <img src="../image/hba1c.jpg" alt="HbA1c">
                </div>
                <div class="package-details">
                    <p class="package-label">Package Name : <strong>HBA1C</strong></p>
                    <p class="package-price">Price: RM40</p>
                </div>
            </div>

            <div class="package-card">
                <div class="package-image">
                     <img src="../image/uricAcid.jpg" alt="Uric Acid">
                </div>
                <div class="package-details">
                    <p class="package-label">Package Name : <strong>Blood Uric Acid</strong></p>
                    <p class="package-price">Price: 9</p>
                </div>
            </div>
        </div>
        </div>
    </div>
</div>

<script>
    // Tunggu sehingga halaman siap dimuatkan
    document.addEventListener('DOMContentLoaded', function() {
        const searchInput = document.getElementById('package-search');
        const packageCards = document.querySelectorAll('.package-card');

        // Fungsi ini akan jalan setiap kali anda menaip dalam search bar
        searchInput.addEventListener('input', function() {
            const filter = searchInput.value.toLowerCase().trim();

            packageCards.forEach(card => {
                // Ambil teks nama package di dalam tag <strong>
                const packageName = card.querySelector('.package-label strong').innerText.toLowerCase();

                // Jika nama ada dalam carian, tunjuk. Jika tak, sorok.
                if (packageName.includes(filter)) {
                    card.style.display = ""; // Kembali kepada gaya asal (flex/block)
                } else {
                    card.style.display = "none"; // Sorokkan terus
                }
            });
        });
    });
</script>




<%@ include file="../footer.jsp" %>
</body>
</html>