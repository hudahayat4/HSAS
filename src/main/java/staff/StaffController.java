package staff;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet("/teamaccount/StaffController")
@MultipartConfig(maxFileSize = 10485760)
public class StaffController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public StaffController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println(">>> ENTER doPost");

        try {
            createStaffAccount(request, response);
            System.out.println(">>> createStaffAccount finished");
        } catch (Exception e) {
            System.out.println(">>> EXCEPTION CAUGHT");
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Staff failed");
        }
    }

	private void createStaffAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
		// TODO Auto-generated method stub
		System.out.print("Connected");
		String name = request.getParameter("name");
		String phoneNo = request.getParameter("PhoneNo");
		String email = request.getParameter("email");
		Date DOB = Date.valueOf(request.getParameter("DOB"));
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
        staff.setNRIC(NRIC);
        staff.setRole(role);
        staff.setProfilePic(inputStream);
        staff.setManagerID(loggedInStaffID);
        
        
        StaffDAO.createStaffAccount(staff);
	}
}
