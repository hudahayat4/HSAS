<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/sideStaff.css">
<link href="https://cdn.lineicons.com/5.0/lineicons.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
<title>sidebar staff</title>
</head>
<body>
    <aside id="sidebar">
    <div class="d-flex align-items-center sidebar-header">
    	<button id="toggle-btn" type="button">
				<i class="lni lni-menu-hamburger-1"></i>
    	</button>

    	<div class="sidebar-logo-text">
    	     <img src="image/juzcarelogosquare.jpg" alt="logo" class="sidebar-logo-img">
    	     <p>JUZCARE'S MANAGER</p>
       		 <%= request.getParameter("name") %>
    	</div>
	</div>
      <ul class="sidebar-nav">
      <li class="sidebar-item">
       		 <a href="#" class="sidebar-link">
       		 	<i class="lni lni-dashboard-square-1"></i>
       		 	<span>Dashboard</span>
       		 </a>
       	</li>
        <li class="sidebar-item">
       		 <a href="#" class="sidebar-link">
       		 	<i class="lni lni-hourglass"></i>
       		 	<span>Pending Accounts</span>
       		 </a>
       	</li>
       	 <li class="sidebar-item">
       		 <a href="#" class="sidebar-link">
       		 	<i class="bi bi-person-vcard"></i>
       		 	<span>Accounts</span>
       		 </a>
       	</li>
       	<li class="sidebar-item">
       		 <a href="teamaccount/listStaffAccount.jsp" class="sidebar-link">
       		 	<i class="lni lni-hierarchy-1"></i>
       		 	<span>Team</span>
       		 </a>
       	</li>
		 <li class="sidebar-item">
       		 <a href="#" class="sidebar-link">
       		 	<i class="lni lni-sliders-horizontal-square-2"></i>
       		 	<span>Access Control</span>
       		 </a>
       	</li>
    <li class="sidebar-item">
        <a href="teamaccount/StaffController?action=view" class="sidebar-link">
            <i class="lni lni-gear-1"></i>
            <span>Settings</span>
        </a>
    </li>
     </ul>
     <div class="sidebar-footer">
     	<a href="#" class="sidebar-link">
            <i class="lni lni-question-mark-circle"></i>
            <span>Help</span>
        </a>
         <a href="#" class="sidebar-link">
            <i class="lni lni-exit"></i>
            <span>Log out</span>
        </a>
     </div>
      
    </aside>
  
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
 <script type="text/javascript" src="js/sidebar.js"></script>

</body>
</html>