package Staff;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;

@WebServlet("/StaffController")
@MultipartConfig(maxFileSize = 10485760)
public class StaffController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public StaffController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	String action = request.getParameter("action");
    	try {
    		switch(action) {
    		case "view" :
    			viewProfileAccount(request,response);
    			break;
    		case "list" :
    			listAccount(request,response);
    			break;
    		case "update" :
    			updateAccount(request,response);
    			break;
    		default:
    			listAccount(request,response);
    			break;
    		}
    	}catch(SQLException ex) {
    		throw new ServletException(ex);
    	}
    	
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
	
	private void viewProfileAccount(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    HttpSession session = request.getSession(false);

	    if (session == null || session.getAttribute("staffID") == null) {
	        response.sendRedirect("login.jsp");
	        return;
	    }

	    int staffID = (int) session.getAttribute("staffID");

	    try {
	        // 🔹 Microservice GET (same as curl GET)
	        HttpClient client = HttpClient.newHttpClient();

	        HttpRequest httpRequest = HttpRequest.newBuilder()
	            .uri(URI.create("https://petstore.swagger.io/v2/user/" + staffID))
	            .header("accept", "application/json")
	            .GET()
	            .build();

	        HttpResponse<String> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

	        // 🔹 Response from microservice
	        String json = httpResponse.body();

	        // Send to JSP
	        request.setAttribute("microserviceData", json);

	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("microserviceData", "ERROR calling microservice");
	    }

	    request.getRequestDispatcher("viewProfileAccount.jsp")
	           .forward(request, response);
	}

}
