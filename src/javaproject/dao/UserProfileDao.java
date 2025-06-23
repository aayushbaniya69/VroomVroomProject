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

        Connection conn = mySql.openConnection();
        if (conn == null) {
            System.err.println("Failed to open database connection.");
            return null;
        }

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Debug: Retrieved user - FirstName=" + rs.getString("firstName") +
                                       ", LastName=" + rs.getString("lastName") +
                                       ", Email=" + rs.getString("email") +
                                       ", Address=" + rs.getString("address") +
                                       ", ContactNumber=" + rs.getString("contactNumber"));
                    user = new UserData(
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("contactNumber")
                    );
                } else {
                    System.err.println("Debug: No user found for email: " + email);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching user by email: " + e.getMessage());
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
            System.err.println("Failed to open database connection.");
            return false;
        }

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getAddress());
            stmt.setString(4, user.getContactNumber());
            stmt.setString(5, user.getEmail());
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Debug: Updated user, rows affected: " + rowsAffected);
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error updating user: " + e.getMessage());
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
            System.err.println("Failed to open database connection.");
            return false;
        }

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getPassword());
            stmt.setString(2, user.getPassword()); // Sync rePassword
            stmt.setString(3, user.getEmail());
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Debug: Changed password, rows affected: " + rowsAffected);
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error changing password: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            mySql.closeConnection(conn);
        }
    }

    public boolean deleteAccount(UserData user) {
        if (user == null || user.getEmail() == null) {
            System.err.println("Debug: deleteAccount received null user or email.");
            return false;
        }
        Connection conn = mySql.openConnection();
        if (conn == null) {
            System.err.println("Failed to open database connection.");
            return false;
        }

        try {
            conn.setAutoCommit(false);
            String deleteUserQuery = "DELETE FROM Registration WHERE email = ?";
            try (PreparedStatement userStmt = conn.prepareStatement(deleteUserQuery)) {
                userStmt.setString(1, user.getEmail());
                int rowsAffected = userStmt.executeUpdate();
                conn.commit();
                System.out.println("Debug: Deleted user, rows affected: " + rowsAffected);
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error deleting account: " + e.getMessage());
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.err.println("Error rolling back transaction: " + ex.getMessage());
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
                    System.err.println("Error closing connection: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }
}