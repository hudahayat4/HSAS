package customer;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;

@WebServlet("/account/CustomerController")
@MultipartConfig(maxFileSize = 10485760)
public class CustomerController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CustomerController() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       String action = request.getParameter("action");
        
    	try {

        if ("view".equals(action)) {
        	viewaccount(request,response);
        } else if ("edit".equals(action)) {
            updateaccount(request,response);
        } else {
            response.sendRedirect("log_in.jsp");
        }
    } catch (Exception ex) {
    	ex.printStackTrace();
		throw new ServletException(ex);
    }
   }

    private void updateaccount(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);
        
        // 1. Ambil ID dari session (siapa yang tengah login)
        int cusID = (int) session.getAttribute("cusID");
        
        // 2. TERUS panggil DAO. Tak perlu buat 'new customer()' sendiri.
        // DAO akan pulangkan objek customer yang lengkap dengan data dari DB.
        customer c = CustomerDAO.getCustomerById(cusID);
        
        // 3. Simpan objek tadi untuk kegunaan JSP
        request.setAttribute("customer", c);
        
        // 4. Pergi ke page edit (Guna forward, jangan lupa .forward)
        request.getRequestDispatcher("updateaccount.jsp").forward(request, response);
    }

	private void viewaccount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
	    if (session == null || session.getAttribute("cusID") == null) {
	        response.sendRedirect(request.getContextPath() + "/log_in.jsp");
	        return;
	    }
	    int cusID = (int) session.getAttribute("cusID");
		customer c = CustomerDAO.getCustomerById(cusID);
		
	    if (c == null) {
	        response.sendRedirect(request.getContextPath() + "/log_in.jsp");
	        return;
	    }
	    
		request.setAttribute("customer", c);
		RequestDispatcher dispatcher = request.getRequestDispatcher("viewaccount.jsp");
        dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    String action = request.getParameter("action");

	    try {
	        if ("registerAccount".equals(action)) {
	            registerAccount(request, response);
	        } else if ("createAccount".equals(action)) {
	            createAccount(request, response);
	        } else if ("updateAccount".equals(action)) {
	            HttpSession session = request.getSession();
	            int cusID = (int) session.getAttribute("cusID");

	            String cusNRIC = request.getParameter("cusNRIC");
	            String custPhoneNo = request.getParameter("custPhoneNo");
	            String custEmail = request.getParameter("custEmail");

	            customer c = new customer();
	            c.setCusID(cusID);
	            c.setCusNRIC(cusNRIC);
	            c.setCustPhoneNo(custPhoneNo);
	            c.setCustEmail(custEmail);

	            CustomerDAO.updateprofile(c);
	            response.sendRedirect("CustomerController?action=view&status=success");
	            
	        } else if ("changePassword".equals(action)) {
	            try {
	                HttpSession session = request.getSession();
	                int customerId = (int) session.getAttribute("cusID"); 

	                String currentPass = request.getParameter("currentPassword");
	                String newPass = request.getParameter("newPassword");
	                String confirmPass = request.getParameter("confirmPassword");

	                customer c = CustomerDAO.getCustomerById(customerId);

	                if (c != null && c.getCustPassword().equals(currentPass)) {
	                    if (newPass.equals(confirmPass)) {
	                        CustomerDAO.updatePassword(customerId, newPass);
	                        response.sendRedirect("CustomerController?action=view&status=passwordUpdated");
	                    } else {
	                        response.sendRedirect("CustomerController?action=view&status=mismatch");
	                    }
	                } else {
	                    response.sendRedirect("CustomerController?action=view&status=wrongpass");
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	                throw new ServletException(e);
	            } 
	        } 
	    } catch (SQLException e) { // Ini untuk tangkap ralat registerAccount/updateAccount
	        e.printStackTrace();
	        throw new ServletException(e);
	    } // Penutup Try Utama
	} // Penutup Method doPost
	
    private void registerAccount(HttpServletRequest request, HttpServletResponse response) 
    		throws IOException, ServletException {

        String cusNRIC = request.getParameter("cusNRIC");
        String custName = request.getParameter("custName");
        String custEmail = request.getParameter("custEmail");
        java.sql.Date dob = java.sql.Date.valueOf(request.getParameter("DOB"));
        String custPhoneNo = request.getParameter("custPhoneNo");

        Part filePart = request.getPart("custProfilePic");
        InputStream inputStream = null;
        if (filePart != null) {
            inputStream = filePart.getInputStream();
        }

        customer cust = new customer();
        cust.setCusNRIC(cusNRIC);
        cust.setCustName(custName);
        cust.setCustEmail(custEmail);
        cust.setDOB(dob);
        cust.setCustPhoneNo(custPhoneNo);
        cust.setCustProfilePic(inputStream);

        request.getSession().setAttribute("tempCustomer", cust);

        response.sendRedirect("createAccount.jsp");
    }

    private void createAccount(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        customer cust = (customer) request.getSession().getAttribute("tempCustomer");

        String custUsername = request.getParameter("custUsername");
        String custPassword = request.getParameter("custPassword");

        cust.setCustUsername(custUsername);
        cust.setCustPassword(custPassword);

        CustomerDAO.createAccount(cust);

        request.getSession().removeAttribute("tempCustomer");
        response.sendRedirect("../log_in.jsp");
    }
}
