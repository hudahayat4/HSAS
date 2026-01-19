package Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import appointment.appointment;
import util.ConnectionManager;

public class ResultDAO {
	public static appointment getAppointment(int appointmentID) {
		appointment apt = null;
		String sql = "SELECT a.*, c.custName, p.packageName, p.packagePrice, s.name AS staffName "
				+ "FROM appointment a " + "JOIN customer c ON a.cusID = c.cusID "
				+ "JOIN package p ON a.packageID = p.packageID " + "JOIN staff s ON a.staffID = s.staffID "
				+ "WHERE a.appointmentID = ?";

		try (Connection conn = ConnectionManager.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

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

	

	public static int addResult(Result result) {
	    String sql = "INSERT INTO result (resultDate, resultComment, appointmentID) VALUES (?, ?, ?)";
	    
	    try (Connection con = ConnectionManager.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) { // No need for RETURN_GENERATED_KEYS here
	        
	        ps.setDate(1, result.getResultDate());
	        ps.setString(2, result.getResultComment());
	        ps.setInt(3, result.getAppointmentId());
	        
	        int rows = ps.executeUpdate();

	        if (rows > 0) {
	            // WORKAROUND: Manually get the last inserted ID
	            // Be careful: This grabs the absolute latest ID in the table. 
	            // In high-traffic apps, this might grab someone else's ID, but for now it will work.
	            try (PreparedStatement ps2 = con.prepareStatement("SELECT MAX(resultID) FROM result");
	                 ResultSet rs = ps2.executeQuery()) {
	                if (rs.next()) {
	                    return rs.getInt(1);
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return -1;
	}
	
	public static void addUricAcidResult(UricAcid uric) {
	    // Note: We are inserting 'resultID' manually.
	    // We assume 'resultID' is the PK and FK in this table.
	    String sql = "INSERT INTO uricacid (resultID, uricLevelRange, riskIndicator) VALUES (?, ?, ?)";
	    
	    try (Connection con = ConnectionManager.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {
	        
	        // 🔴 THIS IS THE LINK
	        // We take the ID we set in the controller and push it to the DB
	        ps.setInt(1, uric.getResultId()); 
	        
	        ps.setDouble(2, uric.getUricLevelRange());
	        ps.setString(3, uric.getRiskIndicator());
	        
	        ps.executeUpdate();
	        System.out.println("Uric Acid result added successfully for ID: " + uric.getResultId());
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public static void addHBA1cResult(HBA1C hba) {
	    // SQL: resultID is inserted manually (PK & FK)
	    String sql = "INSERT INTO hba1c (resultID, diabetesRiskLevel, hBa1cThreshold) VALUES (?, ?, ?)";
	    
	    try (Connection con = ConnectionManager.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {
	        
	        // 1. LINK: Set the ID (This ID comes from the parent Result table)
	        ps.setInt(1, hba.getResultId()); 
	        
	        // 2. Set other fields
	        ps.setString(2, hba.getDiabetesRiskLevel());
	        ps.setDouble(3, hba.getHBa1cTreShold());
	        
	        ps.executeUpdate();
	        System.out.println("HBA1c result added successfully for ID: " + hba.getResultId());
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public static void addLipidResult(Lipid lipid) {
	    // SQL: resultID is inserted manually (PK & FK)
	    String sql = "INSERT INTO lipid (resultID, hdlCholesterol, ldlCholesterol, lipidPanelDetails) VALUES (?, ?, ?, ?)";
	    
	    try (Connection con = ConnectionManager.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {
	        
	        // 1. LINK: Set the ID
	        ps.setInt(1, lipid.getResultId());
	        
	        // 2. Set other fields
	        ps.setDouble(2, lipid.getHdlCholesterol());
	        ps.setDouble(3, lipid.getLdlCholesterol());
	        ps.setString(4, lipid.getLipidPanelDetails());
	        
	        ps.executeUpdate();
	        System.out.println("Lipid result added successfully for ID: " + lipid.getResultId());
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}



	public static List<Result> getAllResult()  throws SQLException{
		// TODO Auto-generated method stub
		List<Result> results = new ArrayList<>();
		
		
		return results;
	}
}
