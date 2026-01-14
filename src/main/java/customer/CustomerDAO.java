package customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import util.ConnectionManager;
import java.io.IOException;
import java.io.InputStream;

public class CustomerDAO {
    private static Connection connection = null;

    // Create Account
    public static void createAccount(customer cust) throws SQLException, IOException {
        String query = "INSERT INTO customer"
                + "(cusNRIC, custName, custEmail, custProfilePic, DOB, custUsername, custPassword, custPhoneNo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        connection = ConnectionManager.getConnection();
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setString(1, cust.getCusNRIC());
        ps.setString(2, cust.getCustName());
        ps.setString(3, cust.getCustEmail());

        InputStream profilePicStream = cust.getCustProfilePic();
        if (profilePicStream != null) {
            ps.setBinaryStream(4, profilePicStream, profilePicStream.available());
        } else {
            ps.setNull(4, java.sql.Types.BLOB);
        }

        ps.setDate(5, new java.sql.Date(cust.getDOB().getTime()));
        ps.setString(6, cust.getCustUsername());
        ps.setString(7, cust.getCustPassword());
        ps.setString(8, cust.getCustPhoneNo());

        ps.executeUpdate();
        ps.close();
    }

    // Login Customer
    public static customer loginCustomer(customer cust) throws SQLException {
        String query = "SELECT * FROM customer WHERE custUsername=? AND custPassword=?";

        connection = ConnectionManager.getConnection();
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setString(1, cust.getCustUsername());
        ps.setString(2, cust.getCustPassword());

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
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
    public static customer getCustomerById(int customerId) {
        customer c = null;

        String sql = "SELECT * FROM customer WHERE NRIC = ?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, customerId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    c = new customer(); // Initialize object here

                    c.setCusNRIC(rs.getString("cusNRIC"));
                    c.setCustName(rs.getString("custName"));
                    c.setCustEmail(rs.getString("custEmail"));
                    c.setCustProfilePic(rs.getBinaryStream("custProfilePic"));
                    c.setDOB(rs.getDate("DOB"));
                    c.setCustUsername(rs.getString("custUsername"));
                    c.setCustPassword(rs.getString("Password"));
                    c.setCustPhoneNo(rs.getString("custPhoneNo"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return c;
    }


    
    //update account 
    public  static void updateProfile(customer c) throws SQLException {
        try {
            String sql = "UPDATE customer SET phone = ?, email = ? WHERE customer_id = ?";
        	 Connection con = ConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
           
            ps.setString(1, c.getCustEmail());
            ps.setString(2, c.getCustPassword() );
            ps.setString(3, c.getCustPhoneNo());
         
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
