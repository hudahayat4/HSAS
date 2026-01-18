package customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.ConnectionManager;
import java.io.IOException;
import java.io.InputStream;

public class CustomerDAO {
    private static Connection connection = null;

    //Create Account
    public static void createAccount(Customer cust) throws SQLException, IOException {
    	String query = "INSERT INTO customer"
    	        + "(cusNRIC, custName, custEmail, custProfilePic, DOB, custUsername, custPassword, custPhoneNo, custVerified) "
    	        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        connection = ConnectionManager.getConnection();
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setString(1, cust.getCusNRIC());
        ps.setString(2, cust.getCustName());
        ps.setString(3, cust.getCustEmail());

        InputStream profilePicStream = cust.getCustProfilePic();
        if (profilePicStream != null) {
            ps.setBinaryStream(4, profilePicStream);
        } else {
            ps.setNull(4, java.sql.Types.BLOB);
        }

        ps.setDate(5, new java.sql.Date(cust.getDOB().getTime()));
        ps.setString(6, cust.getCustUsername());
        ps.setString(7, cust.getCustPassword());
        ps.setString(8, cust.getCustPhoneNo());
        ps.setString(9, "NO");
        ps.executeUpdate();
        ps.close();
    }
    
  //Simpan Verify Code
    public static void saveVerificationCode(String email, String code, java.sql.Timestamp expiry) throws SQLException {
        String sql = "UPDATE customer SET verificationCode = ?, verificationExpiry = ? WHERE LOWER(custEmail) = LOWER(?)";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, code);
            ps.setTimestamp(2, expiry);
            ps.setString(3, email.trim());

            int rows = ps.executeUpdate();

            //Log untuk debug
            System.out.println("saveVerificationCode → Rows updated: " + rows);
            System.out.println("saveVerificationCode → Code: " + code);
            System.out.println("saveVerificationCode → Expiry: " + expiry);
            System.out.println("saveVerificationCode → Email: " + email);
        }
    }


    //Check Verify Code
    public static boolean isCodeValid(String email, String inputCode) throws SQLException {
        String sql = "SELECT verificationCode, verificationExpiry FROM customer WHERE custEmail=?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email.trim());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String storedCode = rs.getString("verificationCode");
                java.sql.Timestamp expiry = rs.getTimestamp("verificationExpiry");
                java.sql.Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());

                //Log untuk debug
                System.out.println("DB code: " + storedCode);
                System.out.println("Input code: " + inputCode);
                System.out.println("Expiry: " + expiry);
                System.out.println("Now: " + now);

                if (storedCode != null && storedCode.equals(inputCode) && expiry != null && now.before(expiry)) {
                    return true;
                }
            }
        }
        return false;
    }


    //Update Status Verify
    public static void markAsVerified(String email) throws SQLException {
        String sql = "UPDATE customer SET custVerified='YES' WHERE custEmail=?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
        	ps.setString(1, email.trim());
            ps.executeUpdate();
        }
    }

    // Login Customer
    public static Customer loginCustomer(Customer cust) throws SQLException {
        String query = "SELECT * FROM customer WHERE custUsername=? AND custPassword=?";

        connection = ConnectionManager.getConnection();
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setString(1, cust.getCustUsername());
        ps.setString(2, cust.getCustPassword());

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
        	cust.setCusID(rs.getInt("cusID"));
            cust.setCusNRIC(rs.getString("cusNRIC"));
            cust.setCustName(rs.getString("custName"));
            cust.setCustEmail(rs.getString("custEmail"));
            cust.setCustPhoneNo(rs.getString("custPhoneNo"));
            cust.setDOB(rs.getDate("DOB"));
            return cust;
        }

        rs.close();
        ps.close();
        return null;
    }
    
    //view account
    public static Customer getCustomerById(int customerId) {
        Customer c = null;

        try {
			String query = "SELECT * FROM customer WHERE cusID = ?";
			connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, customerId);
			ResultSet rs = ps.executeQuery();

  
                if (rs.next()) {
                    c = new Customer(); // Initialize object here

                    c.setCusID(rs.getInt("cusID"));
                    c.setCusNRIC(rs.getString("cusNRIC"));
                    c.setCustName(rs.getString("custName"));
                    c.setCustEmail(rs.getString("custEmail"));
                    c.setCustProfilePic(rs.getBinaryStream("custProfilePic"));
                    c.setDOB(rs.getDate("DOB"));
                    c.setCustUsername(rs.getString("custUsername"));
                    c.setCustPassword(rs.getString("custPassword"));
                    c.setCustPhoneNo(rs.getString("custPhoneNo"));
                    
                    
                }
                rs.close();
    			ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return c;
    }


    
    //update account 
 // Method to update customer profile
    public static void updateprofile(Customer c) throws SQLException {
        // Ensure column names (custPhoneNo, custEmail, cusID) match your database table exactly
        String sql = "UPDATE customer SET custPhoneNo = ?, custEmail = ? WHERE cusID = ?";
        
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, c.getCustPhoneNo());
            ps.setString(2, c.getCustEmail());
            ps.setInt(3, c.getCusID());
            
            int rowsUpdated = ps.executeUpdate();
            
            // Logical check for debugging
            if (rowsUpdated > 0) {
                System.out.println("DEBUG: Update SUCCESSFUL for ID: " + c.getCusID());
            } else {
                System.out.println("DEBUG: Update FAILED. No record found for ID: " + c.getCusID());
            }
            
        } catch (SQLException e) {
            System.err.println("DEBUG: Database Error - " + e.getMessage());
            throw e;
        }
    }
    
    //changepassword
    public static void updatePassword(int cusID, String newPassword) throws SQLException {
        // Pastikan nama kolum CUSTPASSWORD dan CUSID betul mengikut table Oracle anda
        String sql = "UPDATE customer SET custPassword = ? WHERE cusID = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, newPassword);
            ps.setInt(2, cusID);
            
            ps.executeUpdate();
        }
    }
    
}
