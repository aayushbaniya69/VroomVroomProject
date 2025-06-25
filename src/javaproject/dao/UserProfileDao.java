package javaproject.dao;

import javaproject.model.UserData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javaproject.database.MySqlConnection;

public class UserProfileDao {
    private MySqlConnection mySql = new MySqlConnection();

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
                System.out.println("  Database FirstName: '" + firstName + "'");
                System.out.println("  Database LastName: '" + lastName + "'");
                System.out.println("  Database Email: '" + userEmail + "'");
                System.out.println("  Database Address: '" + address + "'");
                System.out.println("  Database ContactNumber: '" + contactNumber + "'");
                
                // FIX: Use corrected constructor with proper parameter order
                user = new UserData(firstName, lastName, userEmail, address, contactNumber);
                
                // Debug: Print what was actually stored in UserData object
                System.out.println("UserData object created:");
                user.printDebugInfo();
                
            } else {
                System.err.println("ERROR: No user found for email: '" + email + "'");
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

    // FIX: Enhanced updateUser method with detailed debugging
    public boolean updateUser(UserData user) {
        if (user == null || user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            System.err.println("ERROR: Invalid user data for update");
            return false;
        }

        String query = "UPDATE Registration SET firstName = ?, lastName = ?, address = ?, contactNumber = ? WHERE email = ?";
        Connection conn = mySql.openConnection();
        
        if (conn == null) {
            System.err.println("ERROR: Failed to open database connection for update.");
            return false;
        }

        System.out.println("=== UPDATE USER DEBUG ===");
        System.out.println("Email: '" + user.getEmail() + "'");
        System.out.println("FirstName: '" + user.getFirstName() + "'");
        System.out.println("LastName: '" + user.getLastName() + "'");
        System.out.println("Address: '" + user.getAddress() + "'");
        System.out.println("ContactNumber: '" + user.getContactNumber() + "'");
        System.out.println("SQL Query: " + query);

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getAddress());
            stmt.setString(4, user.getContactNumber());
            stmt.setString(5, user.getEmail());
            
            System.out.println("Executing update query...");
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Update result - rows affected: " + rowsAffected);
            
            if (rowsAffected == 0) {
                // Check if user exists
                String checkQuery = "SELECT COUNT(*) FROM Registration WHERE email = ?";
                try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
                    checkStmt.setString(1, user.getEmail());
                    try (ResultSet rs = checkStmt.executeQuery()) {
                        if (rs.next()) {
                            int count = rs.getInt(1);
                            System.err.println("User exists check: " + count + " users found with email '" + user.getEmail() + "'");
                        }
                    }
                }
            }
            
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
        if (user == null || user.getEmail() == null || user.getPassword() == null) {
            System.err.println("ERROR: Invalid user data for password change");
            return false;
        }

        String query = "UPDATE Registration SET password = ?, rePassword = ? WHERE email = ?";
        Connection conn = mySql.openConnection();
        
        if (conn == null) {
            System.err.println("ERROR: Failed to open database connection for password change.");
            return false;
        }

        System.out.println("=== CHANGE PASSWORD DEBUG ===");
        System.out.println("Email: '" + user.getEmail() + "'");
        System.out.println("New Password Length: " + user.getPassword().length());

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getPassword());
            stmt.setString(2, user.getPassword()); // Sync rePassword
            stmt.setString(3, user.getEmail());
            
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
                }
            }
        }
    }
}