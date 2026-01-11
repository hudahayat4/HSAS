<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/sidePharmacist.css">
<link href="https://cdn.lineicons.com/5.0/lineicons.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<title>Insert title here</title>

</head>
<body>

<div class="wrapper">
    <aside id="sidebar">
    <div class="d-flex align-items-center sidebar-header">
    	<button id="toggle-btn" type="button">
				<i class="lni lni-menu-hamburger-1"></i>
    	</button>

    	<div class="sidebar-logo-text">
    	     <img src="image/juzcarelogosquare.jpg" alt="logo" class="sidebar-logo-img">
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
       		 	<i class="lni lni-file-multiple"></i>
       		 	<span>Package</span>
       		 </a>
       	</li>
       	 <li class="sidebar-item">
       		 <a href="#" class="sidebar-link">
       		 	<i class="lni lni-comment-1-text"></i>
       		 	<span>Consultation</span>
       		 </a>
       	</li>
		 <li class="sidebar-item">
       		 <a href="#" class="sidebar-link">
       		 	<i class="lni lni-user-multiple-4"></i>
       		 	<span>Appointment</span>
       		 </a>
       	</li>
		 <li class="sidebar-item">
       		 <a href="#" class="sidebar-link">
       		 	<i class="lni lni-calendar-days"></i>
       		 	<span>Schedule</span>
       		 </a>
       	</li>
    <li class="sidebar-item">
        <a href="#" class="sidebar-link">
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
    <div class="main p-3">
  	<div class="text-center">
  		<h1>
  			Sidebar bootstrap 5
  		</h1>
  	
  	</div>
  
  </div>
    
  </div>
  
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
 <script type="text/javascript" src="javascript/sidebar.js"></script>

</body>
</html>