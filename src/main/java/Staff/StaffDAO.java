package Staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.ConnectionManager;
import java.io.IOException;
import java.io.InputStream;

public class StaffDAO {
    private static Connection connection = null;

    //Create Account
    public static void createAccount(Staff staff) throws SQLException, IOException {
        String query = "INSERT INTO staff "
                + "(NRIC, managerID, name, PhoneNo, username, password, DOB, profilePic, email, role) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        connection = ConnectionManager.getConnection();
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setString(1, staff.getNRIC());

        if (staff.getManagerID() > 0) {
            ps.setInt(2, staff.getManagerID());
        } else {
            ps.setNull(2, java.sql.Types.INTEGER);
        }

        ps.setString(3, staff.getName());
        ps.setString(4, staff.getPhoneNo());
        ps.setString(5, staff.getUsername());
        ps.setString(6, staff.getPassword());
        ps.setDate(7, staff.getDOB());

        InputStream profilePicStream = staff.getProfilePic();
        if (profilePicStream != null) {
            ps.setBinaryStream(8, profilePicStream, profilePicStream.available());
        } else {
            ps.setNull(8, java.sql.Types.BLOB);
        }

        ps.setString(9, staff.getEmail());
        ps.setString(10, staff.getRole());

        ps.executeUpdate();
        ps.close();
    }

    //Login Staff (JANGAN LUPA UBAH BALIK NANTI)
    public static Staff loginStaff(Staff staff) throws SQLException {
        String query = "SELECT * FROM staff WHERE username=? AND password=?";

        connection = ConnectionManager.getConnection();
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setString(1, staff.getUsername());
        ps.setString(2, staff.getPassword());

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            staff.setStaffID(rs.getInt("staffID"));
            staff.setNRIC(rs.getString("NRIC"));
            staff.setManagerID(rs.getInt("managerID"));
            staff.setName(rs.getString("name"));
            staff.setPhoneNo(rs.getString("PhoneNo"));
            staff.setDOB(rs.getDate("DOB"));
            staff.setEmail(rs.getString("email"));
            staff.setRole(rs.getString("role"));
            return staff;
        }

        rs.close();
        ps.close();
        return null;
    }
}
