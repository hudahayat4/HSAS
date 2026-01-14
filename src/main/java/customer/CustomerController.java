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

@WebServlet("/CustomerController")
@MultipartConfig(maxFileSize = 10485760)
public class CustomerController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CustomerController() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("customerId") == null) {
            response.sendRedirect("/log_in.jsp");
            return;
        }

        int customerId = (int) session.getAttribute("customerId");
        String action = request.getParameter("action");

//        CustomerDAO dao = new CustomerDAO();
        customer customer = CustomerDAO.getCustomerById(customerId);

        if (customer == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        if ("view".equals(action)) {
            request.setAttribute("customer", customer);
            request.getRequestDispatcher("account/viewaccount.jsp")
                   .forward(request, response);

        } else if ("edit".equals(action)) {
            request.setAttribute("customer", customer);
            request.getRequestDispatcher("account/updateprofile.jsp")
                   .forward(request, response);

        } else {
            response.sendRedirect(request.getContextPath() + "/CustomerController?action=view");
        }
    }

  

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("registerAccount".equals(action)) {
                registerAccount(request, response);
            } else if ("createAccount".equals(action)) {
                createAccount(request, response);
            }else if ("updateAccount".equals(action)) {

                HttpSession session = request.getSession();
                int customerId = (int) session.getAttribute("customerId");

                String cusNRIC = request.getParameter("cusNRIC");
                String custPhoneNo = request.getParameter("custPhoneNo");
                String custEmail = request.getParameter("custEmail");

                customer c = new customer();
                c.setCusNRIC(cusNRIC);
                c.setCustPhoneNo(custPhoneNo);
                c.setCustEmail(custEmail);

                CustomerDAO dao = new CustomerDAO();
                CustomerDAO.updateProfile(c);

                // Redirect back to view profile
                response.sendRedirect(
                    request.getContextPath() +
                    "/CustomerController?action=view"
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }


	
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
        response.sendRedirect("log_in.jsp");
    }
}
