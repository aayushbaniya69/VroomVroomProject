package javaproject.dao;

import javaproject.model.UserData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javaproject.database.MySqlConnection;

public class UserProfileDao {
    private MySqlConnection mySql = new MySqlConnection();

    // FIX: Enhanced getUserByEmail with better debugging
    public UserData getUserByEmail(String email) {
        UserData user = null;
        String query = "SELECT firstName, lastName, email, address, contactNumber FROM Registration WHERE email = ?";

        System.out.println("Searching for user with email: '" + email + "'");
        
        Connection conn = mySql.openConnection();
        if (conn == null) {
            System.err.println("ERROR: Failed to open database connection.");
            return null;
        }

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    String userEmail = rs.getString("email");
                    String address = rs.getString("address");
                    String contactNumber = rs.getString("contactNumber");
                    
                    System.out.println("SUCCESS: User found in database!");
                    System.out.println("  FirstName: " + firstName);
                    System.out.println("  LastName: " + lastName);
                    System.out.println("  Email: " + userEmail);
                    System.out.println("  Address: " + address);
                    System.out.println("  ContactNumber: " + contactNumber);
                    
                    user = new UserData(firstName, lastName, userEmail, address, contactNumber);
                } else {
                    System.err.println("ERROR: No user found for email: '" + email + "'");
                    
                    // Additional debugging - check if email exists with different case
                    String debugQuery = "SELECT email FROM Registration WHERE LOWER(email) = LOWER(?)";
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

        return user;
    }

    public boolean updateUser(UserData user) {
        String query = "UPDATE Registration SET firstName = ?, lastName = ?, address = ?, contactNumber = ? WHERE email = ?";
        Connection conn = mySql.openConnection();
        if (conn == null) {
            System.err.println("ERROR: Failed to open database connection for update.");
            return false;
        }

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getAddress());
            stmt.setString(4, user.getContactNumber());
            stmt.setString(5, user.getEmail());
            
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Update operation - rows affected: " + rowsAffected);
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("ERROR: SQL Exception in updateUser: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            mySql.closeConnection(conn);
        }
    }

    public boolean changePassword(UserData user) {
        String query = "UPDATE Registration SET password = ?, rePassword = ? WHERE email = ?";
        Connection conn = mySql.openConnection();
        if (conn == null) {
            System.err.println("ERROR: Failed to open database connection for password change.");
            return false;
        }

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getPassword());
            stmt.setString(2, user.getPassword()); // Sync rePassword
            stmt.setString(3, user.getEmail());
            
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Password change operation - rows affected: " + rowsAffected);
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("ERROR: SQL Exception in changePassword: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            mySql.closeConnection(conn);
        }
    }

    public boolean deleteAccount(UserData user) {
        if (user == null || user.getEmail() == null) {
            System.err.println("ERROR: deleteAccount received null user or email.");
            return false;
        }
        
        Connection conn = mySql.openConnection();
        if (conn == null) {
            System.err.println("ERROR: Failed to open database connection for account deletion.");
            return false;
        }

        try {
            conn.setAutoCommit(false);
            String deleteUserQuery = "DELETE FROM Registration WHERE email = ?";
            
            try (PreparedStatement userStmt = conn.prepareStatement(deleteUserQuery)) {
                userStmt.setString(1, user.getEmail());
                int rowsAffected = userStmt.executeUpdate();
                
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
                    ex.printStackTrace();
                }
            }
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    System.err.println("ERROR: Failed to close connection: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }
    
    // FIX: Add method to test database connection and list users
    public void testDatabaseConnection() {
        Connection conn = mySql.openConnection();
        if (conn != null) {
            try {
                String query = "SELECT email, firstName, lastName FROM Registration LIMIT 5";
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
                
                System.out.println("=== DATABASE CONNECTION TEST ===");
                System.out.println("Sample users in database:");
                int count = 0;
                while (rs.next()) {
                    count++;
                    System.out.println(count + ". Email: " + rs.getString("email") + 
                                     ", Name: " + rs.getString("firstName") + " " + rs.getString("lastName"));
                }
                if (count == 0) {
                    System.out.println("No users found in database!");
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