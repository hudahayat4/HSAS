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
