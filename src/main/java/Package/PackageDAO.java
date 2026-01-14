package Package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import java.io.InputStream;

import util.ConnectionManager;

public class PackageDAO {
	private static Connection connection = null;

	public static void addPackage(Package packages) throws SQLException, IOException {
		System.out.println("DAO: addPackage called"); // <- first line
		String query = "INSERT INTO package(packageName, packagePic, packagePrice, bfrReq, isExist) VALUES (?,?,?,?,?)";

		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)) {

			ps.setString(1, packages.getPackageName());

			InputStream profilePicStream = packages.getPackagePic();
			if (profilePicStream != null) {
				ps.setBinaryStream(2, profilePicStream, profilePicStream.available());
			} else {
				ps.setNull(2, java.sql.Types.BLOB);
			}

			ps.setDouble(3, packages.getPackagePrice());
			ps.setString(4, packages.getIsbfrReq());
			ps.setString(5, packages.getIsExist());

			int rows = ps.executeUpdate();
			System.out.println(rows + " row(s) inserted successfully.");
		}
	}
	
	//SELECT - get all bookings
	public static List<Package> getAllPackage() throws SQLException{
		// TODO Auto-generated method stub
		List<Package> packages = new ArrayList<>();
		
		try{
			String query = "SELECT * FROM package";
			connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Package p= new Package();
				p.setPackageID(rs.getInt("packageID"));
				p.setPackagePic(rs.getBinaryStream("packagePic"));
				p.setPackageName(rs.getString("packageName"));
				
				p.setIsbfrReq(rs.getString("isbfrReq"));
				p.setIsExist(rs.getString("isExist"));
				packages.add(p);
				
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return packages;
	}
	
	// READ - Get a package by ID
		 public static Package getPackagebyId(int packageID) throws SQLException{
			// TODO Auto-generated method stub
			Package p = null;
			try{
				String query = "SELECT * FROM package WHERE packageID = ?";
				connection = ConnectionManager.getConnection();
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setInt(1, packageID);
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					p = new Package();
					p.setPackageID(rs.getInt("packageID"));
					p.setPackagePic(rs.getBinaryStream("packagePic"));
					p.setPackageName(rs.getString("packageName"));
					
					p.setIsbfrReq(rs.getString("isbfrReq"));
					p.setIsExist(rs.getString("isExist"));
					
					
				}
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return p;
		 }
		 
	 public static void updatePackage(Package p) throws SQLException, IOException {
		 String sql = "UPDATE package SET packageName=?, packagePic=?, " + "packagePrice=?, bfrReq=?, isExist=? WHERE packageID=?";
	        try (Connection connection = ConnectionManager.getConnection();
	             PreparedStatement ps = connection.prepareStatement(sql)) {

	            ps.setString(1, p.getPackageName());

	            if (p.getPackagePic() != null) {
	                ps.setBinaryStream(2, p.getPackagePic(), p.getPackagePic().available());
	            } else {
	                ps.setNull(2, java.sql.Types.BLOB);
	            }

	            ps.setDouble(3, p.getPackagePrice());
	            ps.setString(4, p.getIsbfrReq());
	            ps.setString(5, p.getIsExist());
	            ps.setInt(6, p.getPackageID());

	            ps.executeUpdate();
	        }
	    }

	public static List<Package> getAvailablePackage() {
		// TODO Auto-generated method stub
		List<Package> packages = new ArrayList<>();
		
		try {
			String query = "SELECT * FROM package WHERE isExist = 'YES'";
			connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Package service = new Package();
				service.setPackageID(rs.getInt("packageID"));
				service.setPackageName(rs.getString("packageName"));
				service.setPackagePic(rs.getBinaryStream("packagePic"));
				service.setPackagePrice(rs.getDouble("packagePrice"));
			}
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return packages;
	}

	
}
