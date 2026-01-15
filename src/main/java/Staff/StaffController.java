package Staff;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import util.ConnectionManager;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import customer.CustomerDAO;
import customer.customer;

@WebServlet("/teamaccount/StaffController")
@MultipartConfig(maxFileSize = 10485760)
public class StaffController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public StaffController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        try {
            if ("view".equals(action)) {
                viewProfileAccount(request, response);
            } else if ("edit".equals(action)) {
                // Cukup panggil method ini sahaja, jangan tulis logik yang sama dua kali
                updateProfileAccount(request, response);
            } else {
                response.sendRedirect("log_in.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }


    private void updateProfileAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("staffID") != null) {
            int staffId = (int) session.getAttribute("staffID");
            Staff s = StaffDAO.getStaffById(staffId);
            request.setAttribute("staff", s); // <--- SANGAT PENTING untuk paparkan data kat form
            request.getRequestDispatcher("updateStaffAccount.jsp").forward(request, response);
        } else {
            response.sendRedirect("log_in.jsp");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println(">>> ENTER doPost");
        String action = request.getParameter("action");

        // --- LOGIK 1: UPDATE PROFILE ---
        if ("updateProfile".equals(action)) {
            try {
                HttpSession session = request.getSession(false);
                Integer staffID = (session != null) ? (Integer) session.getAttribute("staffID") : null;
                
                if (staffID != null) {
                    Staff s = new Staff();
                    s.setStaffID(staffID);
                    s.setPhoneNo(request.getParameter("PhoneNo"));
                    s.setEmail(request.getParameter("email"));
                    
                    StaffDAO.updateStaffProfile(s);
                    response.sendRedirect("StaffController?action=view&status=success");
                    return; 
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Update failed");
                return;
            }
        }

        // --- LOGIK 2: CHANGE PASSWORD (Gantikan Servlet asing tadi) ---
        else if ("changePassword".equals(action)) {
            try {
                HttpSession session = request.getSession(false);
                Integer staffID = (session != null) ? (Integer) session.getAttribute("staffID") : null;
                
                String newPass = request.getParameter("newPassword");
                String confirmPass = request.getParameter("confirmPassword");

                if (staffID != null && newPass != null && newPass.equals(confirmPass)) {
                    boolean success = StaffDAO.updatePassword(staffID, newPass);
                    if (success) {
                        response.sendRedirect("StaffController?action=view&status=passwordUpdated");
                    } else {
                        response.sendRedirect("StaffController?action=view&status=error");
                    }
                } else {
                    response.sendRedirect("StaffController?action=view&status=mismatch");
                }
                return;
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Password change failed");
                return;
            }
        }

        // --- LOGIK 3: CREATE ACCOUNT (Kod asal anda) ---
        try {
            createStaffAccount(request, response);
            response.sendRedirect(request.getContextPath() + "/log_in.jsp?status=success");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Create failed");
        }
    }
	
	private void viewProfileAccount(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    // 1️⃣ Session check
	    HttpSession session = request.getSession(false);
	    if (session == null || session.getAttribute("staffID") == null) {
	        response.sendRedirect(request.getContextPath() + "/log_in.jsp");
	        return;
	    }

	    int staffId = (int) session.getAttribute("staffID");
	    Staff staff = StaffDAO.getStaffById(staffId);

	    if (staff == null) {
	        response.sendRedirect(request.getContextPath() + "/log_in.jsp");
	        return;
	    }

	    Map<String, String> staffMap = new HashMap<>();
        staffMap.put("staffID", String.valueOf(staff.getStaffID()));
        staffMap.put("name", staff.getName());
        staffMap.put("email", staff.getEmail());
        staffMap.put("DOB", staff.getDOB() != null ? staff.getDOB().toString() : "");
        staffMap.put("NRIC", staff.getNRIC() != null ? staff.getNRIC() : "");
        staffMap.put("phoneNo", staff.getPhoneNo() != null ? staff.getPhoneNo() : "");

        String jsonParam = request.getParameter("json");
        if ("true".equalsIgnoreCase(jsonParam)) {
            // Return JSON directly
            StringBuilder json = new StringBuilder("{");
            int i = 0;
            for (Map.Entry<String, String> entry : staffMap.entrySet()) {
                json.append("\"").append(entry.getKey()).append("\":\"")
                    .append(entry.getValue()).append("\"");
                if (i < staffMap.size() - 1) json.append(",");
                i++;
            }
            json.append("}");

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.write(json.toString());
            out.flush();
            return; // IMPORTANT: stop further processing
        }

        // 4️⃣ Otherwise, forward to JSP
        request.setAttribute("staff", staff);
        request.setAttribute("microserviceData", staffMap);
        request.getRequestDispatcher("viewStaffAccount.jsp").forward(request, response);
    }




	private void createStaffAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
		// TODO Auto-generated method stub
		System.out.print("Connected");
		String name = request.getParameter("name");
		String phoneNo = request.getParameter("PhoneNo");
		String email = request.getParameter("email");
		Date DOB = Date.valueOf(request.getParameter("DOB"));
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String NRIC = request.getParameter("NRIC");
		String role = request.getParameter("role");
		Part filePart = request.getPart("profilePic");
		Integer loggedInStaffID = (Integer) request.getSession().getAttribute("staffID");
        InputStream inputStream = null;
        if (filePart != null) {
            inputStream = filePart.getInputStream();
        }
        
        Staff staff = new Staff();
        staff.setName(name);
        staff.setPhoneNo(phoneNo);
        staff.setEmail(email);
        staff.setDOB(DOB);
        staff.setUsername(username);
        staff.setPassword(password);
        staff.setNRIC(NRIC);
        staff.setRole(role);
        staff.setProfilePic(inputStream);
        staff.setManagerID(loggedInStaffID);
        
        
        StaffDAO.createStaffAccount(staff);
	}
	


	
}
