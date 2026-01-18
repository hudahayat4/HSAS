package appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Package.Package;

import util.ConnectionManager;

public class AppointmentDAO {
	private static Connection connection = null;

	public static List<Package> getPackageAvailable() {
		// TODO Auto-generated method stub
		List<Package> packages = new ArrayList<>();
		
		try {
			String query = "SELECT * FROM package WHERE isExist = 'YES'";
			connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				Package p = new Package();
				p.setPackageID(rs.getInt("packageID"));
				p.setPackagePic(rs.getBinaryStream("packagePic"));
				p.setPackageName(rs.getString("packageName"));
				p.setPackagePrice(rs.getDouble("packagePrice"));
				p.setIsExist(rs.getString("isExist"));
				packages.add(p);
			}
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return packages;
	}

	public static byte[] getPackageImage(int id) throws SQLException {
		// TODO Auto-generated method stub
		byte[] image = null;
		String query = "SELECT packagePic FROM package WHERE packageID=?";
		
		try(Connection conn = ConnectionManager.getConnection(); PreparedStatement ps = conn.prepareStatement(query)){
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				image = rs.getBytes("packagePic");
			}
		}
		return image;
	}

	public static void bookAppointment(appointment appt) throws SQLException {
		// TODO Auto-generated method stub
		String query = "INSERT INTO appointment(cusID, staffID, packageID, apptDate,apptTime) VALUES (?,?,?,?,?)";
		connection = ConnectionManager.getConnection();
		PreparedStatement ps = connection.prepareStatement(query);
		try {
			ps.setInt(1, appt.getCustomerID());
			ps.setInt(2, appt.getStaffID());
			ps.setInt(3, appt.getPackageID());
			ps.setDate(4, appt.getApptDate());
			ps.setTimestamp(5, appt.getApptTime());
			
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static appointment getAppointmentById(int appointmentID) {
	    appointment apt = null;
	    String sql = "SELECT a.*, c.custName, p.packageName, p.packagePrice, s.name AS staffName " +
	                 "FROM appointment a " +
	                 "JOIN customer c ON a.cusID = c.cusID " +
	                 "JOIN package p ON a.packageID = p.packageID " +
	                 "JOIN staff s ON a.staffID = s.staffID " +
	                 "WHERE a.appointmentID = ?";

	    try (Connection conn = ConnectionManager.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, appointmentID);

	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                apt = new appointment();
	                apt.setAppointmentID(rs.getInt("appointmentID"));
	                apt.setApptDate(rs.getDate("apptDate"));
	                apt.setApptTime(rs.getTimestamp("apptTime"));

	                // Set additional fields for viewapt.jsp
	                apt.setCustomerName(rs.getString("custName"));
	                apt.setPackageName(rs.getString("packageName"));
	                apt.setPackagePrice(rs.getDouble("packagePrice"));
	                apt.setPharmacistName(rs.getString("staffName"));
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return apt;
	}


	public static List<appointment> getAllAppointmentsByCustomerId(int cusID) {
	    List<appointment> appointments = new ArrayList<>();
	    String sql = "SELECT a.*, p.packageName,c.custName FROM appointment a " +
	                 "JOIN package p ON a.packageID = p.packageID " +
	                 "JOIN customer c ON a.cusID = c.cusID " +
	                 "WHERE a.cusID = ? ORDER BY a.apptDate DESC";

	    try (Connection conn = ConnectionManager.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setInt(1, cusID);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            appointment apt = new appointment();
	            apt.setAppointmentID(rs.getInt("appointmentID"));
	            apt.setApptDate(rs.getDate("apptDate"));
	            apt.setApptTime(rs.getTimestamp("apptTime"));
	            apt.setPackageName(rs.getString("packageName"));
	            apt.setCustomerName(rs.getString("custName"));
	            appointments.add(apt);
	        }
	        ps.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return appointments;
	}
	
	public static void cancelAppointment(int appointmentID) throws SQLException {
	    String sql = "DELETE FROM appointment WHERE appointmentID = ?";

	    try (Connection conn = ConnectionManager.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, appointmentID);
	        ps.executeUpdate();
	        ps.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


}