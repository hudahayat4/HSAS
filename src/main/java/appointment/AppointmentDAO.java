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





}
