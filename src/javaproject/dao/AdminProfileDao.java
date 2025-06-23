/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaproject.database.MySqlConnection;
import javaproject.model.SellerData;
import javaproject.model.UserData;

/**
 *
 * @author ACER
 */
public class AdminProfileDao {
    MySqlConnection mySql = new MySqlConnection();
    public SellerData getUserByEmail(String email){
    SellerData seller = null;
    String query = "SELECT * FROM SellerRegistration WHERE email = ?";
    
    

    try (Connection conn = mySql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setString(1, email);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                seller = new SellerData(
                    rs.getString("fullName"),
                    rs.getString("email"),
                    rs.getString("location"),
                    rs.getString("contactNumber")
                );
            }
        }

    } catch (SQLException e) {
        System.err.println("Error fetching user by email from database.");
        e.printStackTrace();
    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminProfileDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    return seller;
}
     
    public boolean updateUser(SellerData seller) throws ClassNotFoundException {
    String query = "UPDATE SellerRegistration SET fullName = ?, address = ?, contactNumber = ? WHERE email = ?";
    try (Connection conn = mySql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, seller.getFullName());
        stmt.setString(2, seller.getLocation());
        stmt.setString(3, seller.getContactNumber());
        stmt.setString(4, seller.getEmail());
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        System.err.println("Error updating user in database.");
        e.printStackTrace();
        return false;
    }
}
     public boolean changePassword(SellerData seller) throws ClassNotFoundException{
        String query ="UPDATE SellerRegistration SET password=? WHERE email=?";
        try(Connection conn = mySql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, seller.getPassword());
            stmt.setString(2,seller.getEmail());
            return stmt.executeUpdate()>0;            
        }catch(SQLException e){
            System.err.println("Error changing password in database.");
        e.printStackTrace();
        return false;
        }
    }
    public boolean deleteAccount(SellerData seller) throws ClassNotFoundException {
    if (seller == null || seller.getEmail() == null) {
        System.err.println("Debug: deleteAccount received null user or email.");
        return false;
    }
    Connection conn = null;
    try {
        conn = mySql.getConnection();
        conn.setAutoCommit(false); // Start transaction

        // Delete the user
        String deleteUserQuery = "DELETE FROM SellerRegistration WHERE email = ?";
        try (PreparedStatement userStmt = conn.prepareStatement(deleteUserQuery)) {
            userStmt.setString(1, seller.getEmail());
            int rowsAffected = userStmt.executeUpdate();
            conn.commit(); // Commit transaction if successful
            return rowsAffected > 0;
        }

    } catch (SQLException e) {
        System.err.println("Error deleting Registration in database.");
        e.printStackTrace();
        if (conn != null) {
            try {
                conn.rollback(); // Roll back transaction on error
            } catch (SQLException ex) {
                System.err.println("Error rolling back transaction.");
                ex.printStackTrace();
            }
        }
        return false;
    } finally {
        if (conn != null) {
            try {
                conn.setAutoCommit(true); // Reset auto-commit
                conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection.");
                e.printStackTrace();
            }
        }
    }
    }
}
