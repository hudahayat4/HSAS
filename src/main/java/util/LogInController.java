package util;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import customer.customer;
import customer.CustomerDAO;
import Staff.Staff;
import Staff.StaffDAO;

@WebServlet("/LogInController")
public class LogInController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LogInController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            if ("loginCustomer".equals(action)) {
                loginCustomer(request, response);
            } else if ("loginStaff".equals(action)) {
                loginStaff(request, response);
            } else {
                response.sendRedirect("log_in.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
    
    //Log In Customer
    private void loginCustomer(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String username = request.getParameter("custUsername");
        String password = request.getParameter("custPassword");

        customer cust = new customer();
        cust.setCustUsername(username);
        cust.setCustPassword(password);

        cust = CustomerDAO.loginCustomer(cust);

        if (cust != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("custUsername", cust.getCustUsername());
            session.setAttribute("custEmail", cust.getCustEmail());
            response.sendRedirect("home_customer.jsp");
        } else {
            request.setAttribute("errorMessage", "Invalid customer login.");
            request.getRequestDispatcher("log_in.jsp").forward(request, response);
        }
    }

    //Log In Staff
    private void loginStaff(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String username = request.getParameter("staffUsername");
        String password = request.getParameter("staffPassword");

        Staff staff = new Staff();
        staff.setUsername(username);
        staff.setPassword(password);

        staff = StaffDAO.loginStaff(staff);

        if (staff != null) {
            HttpSession session = request.getSession(true);
<<<<<<< HEAD
=======
            session.setAttribute("staffID", staff.getStaffID());  
>>>>>>> 626682d062375a782c70d7ea83df80d15055622a
            session.setAttribute("staffUsername", staff.getUsername());
            session.setAttribute("staffRole", staff.getRole());
            response.sendRedirect("test.jsp");
        } else {
            request.setAttribute("errorMessage", "Invalid staff login.");
            request.getRequestDispatcher("log_in.jsp").forward(request, response);
        }
    }
}
