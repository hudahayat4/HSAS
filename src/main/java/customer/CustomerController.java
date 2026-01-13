package customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("registerAccount".equals(action)) {
                registerAccount(request, response);
            } else if ("createAccount".equals(action)) {
                createAccount(request, response);
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

        Customer cust = new Customer();
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

        Customer cust = (Customer) request.getSession().getAttribute("tempCustomer");

        String custUsername = request.getParameter("custUsername");
        String custPassword = request.getParameter("custPassword");

        cust.setCustUsername(custUsername);
        cust.setCustPassword(custPassword);

        CustomerDAO.createAccount(cust);

        request.getSession().removeAttribute("tempCustomer");
        response.sendRedirect("log_in.jsp");
    }
}
