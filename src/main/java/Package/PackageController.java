package Package;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.annotation.MultipartConfig;
import java.io.InputStream;

/**
 * Servlet implementation class PackageController
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024,maxFileSize = 1024 * 1024 * 5,maxRequestSize = 1024 * 1024 * 10)
@WebServlet("/package/PackageController")
public class PackageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PackageController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		String action = request.getParameter("action");
//
//		try {
//			switch (action) {
//			case "list":
//				listPackage(request, response);
//				break;
//			case "delete":
//				deletePackage(request, response);
//				break;
//			case "edit":
//				showEditForm(request, response);
//			default:
//				listPackage(request, response);
//				break;
//			}
//		} catch (SQLException ex) {
//			throw new ServletException(ex);
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request,response);
		String packageID = request.getParameter("packageID");

		try {
			if (packageID == null) {
				addPackage(request, response);
			} else {
				updatePackage(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//UPDATE an existing package
	private void updatePackage(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
		// TODO Auto-generated method stub
	
		String packageName = request.getParameter("packageName");
		Part filePart = request.getPart("packagePic");
		InputStream inputStream = null;
		if (filePart != null) {
			inputStream = filePart.getInputStream();
		}
		Double packagePrice = Double.parseDouble(request.getParameter("packagePrice"));
		String isbfrReq = request.getParameter("isbfrReq");
		String isExist = request.getParameter("isExist");
		
		Package packages = new Package();
		packages.setPackageName(packageName);
		packages.setPackagePic(inputStream);
		packages.setPackagePrice(packagePrice);
		packages.setIsbfrReq(isbfrReq);
		packages.setIsExist(isExist);
		
		PackageDAO.updatePackage(packages);
		response.sendRedirect("PackageController?action=list");

	}

	private void addPackage(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
		// TODO Auto-generated method stub
		String packageName = request.getParameter("packageName");
		Part filePart = request.getPart("packagePic");
        InputStream inputStream = null;
        if (filePart != null) {
          inputStream = filePart.getInputStream();
        }
		Double packagePrice = Double.parseDouble(request.getParameter("packagePrice"));
		String isbfrReq = request.getParameter("isbfrReq");
		String isExist = request.getParameter("isExist");

		Package packages = new Package();
		packages.setPackageName(packageName);
		packages.setPackagePic(inputStream);
		packages.setPackagePrice(packagePrice);
		packages.setIsbfrReq(isbfrReq);
		packages.setIsExist(isExist);

		PackageDAO.addPackage(packages);
		response.sendRedirect("PackageController?action=list");

	}

}
