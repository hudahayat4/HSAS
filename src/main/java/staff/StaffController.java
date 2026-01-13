package staff;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;

@WebServlet("/StaffController")
@MultipartConfig(maxFileSize = 10485760)
public class StaffController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public StaffController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Staff staff = new Staff();
            staff.setName(request.getParameter("name"));
            staff.setPhoneNo(request.getParameter("PhoneNo"));
            staff.setEmail(request.getParameter("email"));
            java.sql.Date dob = java.sql.Date.valueOf(request.getParameter("DOB"));
            staff.setDOB(dob);
            staff.setNRIC(request.getParameter("NRIC"));
            staff.setRole(request.getParameter("role"));

            Part filePart = request.getPart("profilePic");
            if (filePart != null && filePart.getSize() > 0) {
                staff.setProfilePic(filePart.getInputStream());
            }

            String managerID = request.getParameter("managerID");
            if (managerID != null && !managerID.isEmpty()) {
                staff.setManagerID(Integer.parseInt(managerID));
            } else {
                staff.setManagerID(0);
            }

            StaffDAO.createAccount(staff);

            //nanti betulkan dalam kurungan () lepas dah tahu kalau success pergi mana
            response.sendRedirect("success.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            //nanti betulkan dalam kurungan () lepas dah tahu kalau fail pergi mana
            response.sendRedirect("error.jsp");
        }
    }
}
