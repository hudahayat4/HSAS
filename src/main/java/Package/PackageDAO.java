package Package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.IOException;
import java.io.InputStream;

import util.ConnectionManager;

public class PackageDAO {

	public static void addPackage(Package packages) throws SQLException, IOException {
		System.out.println("DAO: addPackage called"); // <- first line
		String query = "INSERT INTO package(packageID, packageName, packagePic, packagePrice, bfrReq, isExist) VALUES (packageID_seq.NEXTVAL,?,?,?,?,?)";

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

}
