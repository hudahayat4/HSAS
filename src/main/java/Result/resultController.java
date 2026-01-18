package Result;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import appointment.appointment;

/**
 * Servlet implementation class resultController
 */
@WebServlet("/result/resultController")
public class resultController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public resultController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    String action = request.getParameter("action");

	    try {
	        if (action == null) {
	            viewForm(request, response);
	            return;
	        }

	        switch (action) {
	            case "list":
	                listResult(request, response);
	                break;
	            default:
	                viewForm(request, response);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	private void viewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String appoinmentIDStr = request.getParameter("appointmentID");
		if(appoinmentIDStr != null){
			int appointmentID = Integer.parseInt(appoinmentIDStr);
			appointment apt = ResultDAO.getAppointment(appointmentID);
			
			if(apt != null) {
				request.setAttribute("apt", apt);
				request.getRequestDispatcher("/result/createResult.jsp").forward(request, response);
			}
		}
		
	}

	private void listResult(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		createResult(request,response);
		
	}

	private void createResult(HttpServletRequest request, HttpServletResponse response) {
	    try {
	        // 1. Get appointment ID
	        int appointmentID = Integer.parseInt(request.getParameter("appointmentID"));

	        appointment apt = ResultDAO.getAppointment(appointmentID);
	        String packageName = apt.getPackageName();
	        // 2. Create parent Result

	        Date date = Date.valueOf(request.getParameter("date"));
	        String comment = request.getParameter("comment");

	        Result result = new Result();
	        result.setAppointmentId(appointmentID);
	        result.setResultDate(date);
	        result.setResultComment(comment);

	        // Save Result and get generated ID
	        int generatedResultID = ResultDAO.addResult(result);
	        if (generatedResultID == -1) {
	            System.out.println("Failed to save result");
	            return;
	        }

	        // 3. Save child based on package
	        switch (packageName) {
	            case "Uric Acid":
	                UricAcid uric = new UricAcid();
	                uric.setResultId(generatedResultID);
	                uric.setRiskIndicator(request.getParameter("riskIndicator"));
	                uric.setUricLevelRange(Double.parseDouble(request.getParameter("uricLevelRange")));
	                ResultDAO.addUricAcidResult(uric);
	                break;

	            case "HBA1c":
	                HBA1C hba = new HBA1C();
	                hba.setResultId(generatedResultID);
	                hba.setDiabetesRiskLevel(request.getParameter("diabetes"));
	                hba.setHBa1cTreShold(Double.parseDouble(request.getParameter("threshold")));
	                ResultDAO.addHBA1cResult(hba);
	                break;

	            case "Lipid":
	                Lipid lipid = new Lipid();
	                lipid.setResultId(generatedResultID);
	                lipid.setHdlCholesterol(Double.parseDouble(request.getParameter("hdl")));
	                lipid.setLdlCholesterol(Double.parseDouble(request.getParameter("ldl")));
	                lipid.setLipidPanelDetails(request.getParameter("details"));
	                ResultDAO.addLipidResult(lipid);
	                break;
	        }

	        response.sendRedirect("resultController?action=list");

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


}
