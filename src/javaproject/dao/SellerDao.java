package javaproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javaproject.database.MySqlConnection;
import javaproject.model.LoginRequest;
import javaproject.model.SellerData;

public class SellerDao {
    
    public SellerData loginSeller(LoginRequest loginRequest) {
        System.out.println("=== SELLER LOGIN DAO ===");
        System.out.println("Email: '" + loginRequest.getEmail() + "'");
        System.out.println("Password length: " + loginRequest.getPassword().length());
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            connection = MySqlConnection.getConnection();
            if (connection == null) {
                System.err.println("Database connection is null!");
                return null;
            }
            
            // Use your actual table name and column names
            String sql = "SELECT * FROM SellerRegistration WHERE email = ? AND password = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, loginRequest.getEmail().trim());
            statement.setString(2, loginRequest.getPassword().trim());
            
            System.out.println("Executing SQL: " + sql);
            
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                SellerData seller = new SellerData(
                    resultSet.getString("fullName"),        // Match your column name
                    resultSet.getString("email"),
                    resultSet.getString("location"),
                    resultSet.getString("contactNumber"),   // Match your column name
                    resultSet.getString("password"),
                    resultSet.getString("rePassword"),      // Match your column name
                    resultSet.getString("panNumber")        // Match your column name
                );
                
                System.out.println("Seller login successful!");
                return seller;
            } else {
                System.out.println("No seller found with provided credentials");
                return null;
            }
            
        } catch (SQLException e) {
            System.err.println("SQL Exception in loginSeller:");
            System.err.println("Message: " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.err.println("Exception in loginSeller: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
    }
    
    public boolean registerSeller(SellerData seller) {
        System.out.println("=== REGISTER SELLER DAO ===");
        System.out.println("Registering seller: " + seller.getEmail());
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = MySqlConnection.getConnection();
            if (connection == null) {
                System.err.println("Database connection is null!");
                return false;
            }
            
            // Use your actual table and column names
            String sql = "INSERT INTO SellerRegistration (fullName, email, location, contactNumber, password, rePassword, panNumber) VALUES (?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            
            statement.setString(1, seller.getFullName());
            statement.setString(2, seller.getEmail());
            statement.setString(3, seller.getLocation());
            statement.setString(4, seller.getContactNumber());
            statement.setString(5, seller.getPassword());
            statement.setString(6, seller.getRePassword());
            statement.setString(7, seller.getPanNumber());
            
            System.out.println("Executing registration SQL");
            
            int result = statement.executeUpdate();
            boolean success = result > 0;
            
            System.out.println("Registration successful: " + success);
            return success;
            
        } catch (SQLException e) {
            System.err.println("SQL Exception in registerSeller: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
    }
}