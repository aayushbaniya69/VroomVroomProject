package javaproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javaproject.database.MySqlConnection;
import javaproject.model.SellerData;

public class AdminProfileDao {
    private MySqlConnection mySql = new MySqlConnection();

    // Get seller profile by email - COMPLETE with all fields
    public SellerData getUserByEmail(String email) {
        SellerData seller = null;
        // FIX: Include ALL fields including password and rePassword
        String query = "SELECT fullName, email, location, contactNumber, password, rePassword, panNumber FROM SellerRegistration WHERE email = ?";

        System.out.println("=== ADMIN PROFILE DAO DEBUG ===");
        System.out.println("Searching for seller with email: '" + email + "'");
        
        Connection conn = mySql.openConnection();
        if (conn == null) {
            System.err.println("ERROR: Failed to open database connection.");
            return null;
        }

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String sellerEmail = rs.getString("email");
                    String location = rs.getString("location");
                    String contactNumber = rs.getString("contactNumber");
                    String password = rs.getString("password");
                    String rePassword = rs.getString("rePassword");
                    String panNumber = rs.getString("panNumber");
                    
                    System.out.println("SUCCESS: Seller found in database!");
                    System.out.println("  Database FullName: '" + fullName + "'");
                    System.out.println("  Database Email: '" + sellerEmail + "'");
                    System.out.println("  Database Location: '" + location + "'");
                    System.out.println("  Database ContactNumber: '" + contactNumber + "'");
                    System.out.println("  Database PanNumber: '" + panNumber + "'");
                    
                    // FIX: Use 7-parameter constructor with ALL fields
                    seller = new SellerData(fullName, sellerEmail, location, contactNumber, password, rePassword, panNumber);
                    
                    System.out.println("SellerData object created with PAN: " + seller.getPanNumber());
                    
                } else {
                    System.err.println("ERROR: No seller found for email: '" + email + "'");
                    
                    // Debug: Check if email exists with different case
                    String debugQuery = "SELECT email FROM SellerRegistration WHERE LOWER(email) = LOWER(?)";
                    try (PreparedStatement debugStmt = conn.prepareStatement(debugQuery)) {
                        debugStmt.setString(1, email);
                        try (ResultSet debugRs = debugStmt.executeQuery()) {
                            if (debugRs.next()) {
                                System.err.println("HINT: Found similar email with different case: " + debugRs.getString("email"));
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("ERROR: SQL Exception in getUserByEmail: " + e.getMessage());
            e.printStackTrace();
        } finally {
            mySql.closeConnection(conn);
        }

        System.out.println("=== END ADMIN PROFILE DAO DEBUG ===");
        return seller;
    }
     
    // Update seller profile - corrected table and column names
    public boolean updateSellerProfile(SellerData seller) {
        System.out.println("=== UPDATE SELLER PROFILE DAO ===");
        System.out.println("Updating seller: " + seller.getEmail());
        
        Connection connection = mySql.openConnection();
        PreparedStatement statement = null;
        
        try {
            if (connection == null) {
                System.err.println("Database connection is null!");
                return false;
            }
            
            // Use correct table name and column names
            String sql = "UPDATE SellerRegistration SET fullName = ?, location = ?, contactNumber = ?, panNumber = ? WHERE email = ?";
            statement = connection.prepareStatement(sql);
            
            statement.setString(1, seller.getFullName());
            statement.setString(2, seller.getLocation());
            statement.setString(3, seller.getContactNumber());
            statement.setString(4, seller.getPanNumber());
            statement.setString(5, seller.getEmail());
            
            System.out.println("Executing update SQL for: " + seller.getEmail());
            System.out.println("  FullName: '" + seller.getFullName() + "'");
            System.out.println("  Location: '" + seller.getLocation() + "'");
            System.out.println("  ContactNumber: '" + seller.getContactNumber() + "'");
            System.out.println("  PanNumber: '" + seller.getPanNumber() + "'");
            
            int rowsUpdated = statement.executeUpdate();
            boolean success = rowsUpdated > 0;
            
            System.out.println("Rows updated: " + rowsUpdated);
            return success;
            
        } catch (SQLException e) {
            System.err.println("SQL Exception in updateSellerProfile: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                System.err.println("Error closing statement: " + e.getMessage());
            }
            mySql.closeConnection(connection);
        }
    }
    
    // Change password method
    public boolean changePassword(SellerData seller) {
        if (seller == null || seller.getEmail() == null || seller.getPassword() == null) {
            System.err.println("ERROR: Invalid seller data for password change");
            return false;
        }

        String query = "UPDATE SellerRegistration SET password = ?, rePassword = ? WHERE email = ?";
        Connection conn = mySql.openConnection();
        
        if (conn == null) {
            System.err.println("ERROR: Failed to open database connection for password change.");
            return false;
        }

        System.out.println("=== CHANGE SELLER PASSWORD DEBUG ===");
        System.out.println("Email: '" + seller.getEmail() + "'");
        System.out.println("New Password Length: " + seller.getPassword().length());

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, seller.getPassword());
            stmt.setString(2, seller.getPassword()); // Sync rePassword
            stmt.setString(3, seller.getEmail());
            
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Password change result - rows affected: " + rowsAffected);
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("ERROR: SQL Exception in changePassword: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            mySql.closeConnection(conn);
        }
    }

    // Delete account method
    public boolean deleteAccount(SellerData seller) {
        if (seller == null || seller.getEmail() == null) {
            System.err.println("ERROR: deleteAccount received null seller or email.");
            return false;
        }
        
        Connection conn = mySql.openConnection();
        if (conn == null) {
            System.err.println("ERROR: Failed to open database connection for account deletion.");
            return false;
        }

        try {
            conn.setAutoCommit(false);
            String deleteSellerQuery = "DELETE FROM SellerRegistration WHERE email = ?";
            
            try (PreparedStatement sellerStmt = conn.prepareStatement(deleteSellerQuery)) {
                sellerStmt.setString(1, seller.getEmail());
                int rowsAffected = sellerStmt.executeUpdate();
                
                if (rowsAffected > 0) {
                    conn.commit();
                    System.out.println("Account deletion successful - rows affected: " + rowsAffected);
                    return true;
                } else {
                    conn.rollback();
                    System.err.println("ERROR: No rows affected during account deletion");
                    return false;
                }
            }
        } catch (SQLException e) {
            System.err.println("ERROR: SQL Exception in deleteAccount: " + e.getMessage());
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.err.println("ERROR: Failed to rollback transaction: " + ex.getMessage());
                }
            }
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException e) {
                    System.err.println("ERROR: Failed to close connection: " + e.getMessage());
                }
                mySql.closeConnection(conn);
            }
        }
    }

    // Test database connection
    public void testDatabaseConnection() {
        Connection conn = mySql.openConnection();
        if (conn != null) {
            try {
                String query = "SELECT email, fullName, location, panNumber FROM SellerRegistration LIMIT 5";
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
                
                System.out.println("=== SELLER DATABASE CONNECTION TEST ===");
                System.out.println("Sample sellers in database:");
                int count = 0;
                while (rs.next()) {
                    count++;
                    System.out.println(count + ". Email: " + rs.getString("email") + 
                                     ", Name: " + rs.getString("fullName") + 
                                     ", Location: " + rs.getString("location") +
                                     ", PAN: " + rs.getString("panNumber"));
                }
                if (count == 0) {
                    System.out.println("No sellers found in database!");
                }
                System.out.println("=== END TEST ===");
            } catch (SQLException e) {
                System.err.println("ERROR: Database test failed: " + e.getMessage());
                e.printStackTrace();
            } finally {
                mySql.closeConnection(conn);
            }
        } else {
            System.err.println("ERROR: Database connection failed!");
        }
    }
}