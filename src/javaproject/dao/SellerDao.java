package javaproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javaproject.database.MySqlConnection;
import javaproject.model.LoginRequest;
import javaproject.model.SellerData;

public class SellerDao {
    private MySqlConnection mySql = new MySqlConnection();
    
    public SellerData loginSeller(LoginRequest loginRequest) {
        System.out.println("=== SELLER LOGIN DAO ===");
        System.out.println("Email: '" + loginRequest.getEmail() + "'");
        System.out.println("Password length: " + loginRequest.getPassword().length());
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            connection = mySql.openConnection();
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
                    resultSet.getString("fullName"),        
                    resultSet.getString("email"),
                    resultSet.getString("location"),
                    resultSet.getString("contactNumber"),   
                    resultSet.getString("password"),
                    resultSet.getString("rePassword"),      
                    resultSet.getString("panNumber")        
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
    
    // FIX: Complete registerSeller method with PAN number
    public boolean registerSeller(SellerData seller) {
        System.out.println("=== REGISTER SELLER DAO ===");
        System.out.println("Registering seller: " + seller.getEmail());
        System.out.println("PAN Number: '" + seller.getPanNumber() + "'");
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = mySql.openConnection();
            if (connection == null) {
                System.err.println("Database connection is null!");
                return false;
            }
            
            // FIX: Include panNumber in INSERT statement
            String sql = "INSERT INTO SellerRegistration (fullName, email, location, contactNumber, password, rePassword, panNumber) VALUES (?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            
            statement.setString(1, seller.getFullName());
            statement.setString(2, seller.getEmail());
            statement.setString(3, seller.getLocation());
            statement.setString(4, seller.getContactNumber());
            statement.setString(5, seller.getPassword());
            statement.setString(6, seller.getRePassword());
            statement.setString(7, seller.getPanNumber()); // FIX: Include PAN number
            
            System.out.println("Executing registration SQL with PAN: " + seller.getPanNumber());
            
            int result = statement.executeUpdate();
            boolean success = result > 0;
            
            System.out.println("Registration successful: " + success);
            System.out.println("Rows affected: " + result);
            
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
    
    // FIX: Add method to check if email already exists
    public boolean emailExists(String email) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            connection = mySql.openConnection();
            String sql = "SELECT COUNT(*) FROM SellerRegistration WHERE email = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
            return false;
            
        } catch (SQLException e) {
            System.err.println("Error checking email existence: " + e.getMessage());
            return false;
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
    // Add these methods to your existing UserDao.java

public boolean validateSecurityAnswer(String email, String question, String answer) {
    // Since SellerRegistration table doesn't have security question/answer,
    // we'll just check if email exists
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    
    try {
        connection = mySql.openConnection();
        String sql = "SELECT * FROM SellerRegistration WHERE email = ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        
        resultSet = statement.executeQuery();
        boolean found = resultSet.next();
        
        if (found) {
            System.out.println("✅ Seller email found: " + email);
        } else {
            System.out.println("❌ Seller email not found: " + email);
        }
        
        return found;
        
    } catch (SQLException e) {
        System.err.println("Error validating seller email: " + e.getMessage());
        return false;
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

public boolean updatePassword(String email, String newPassword) {
    Connection connection = null;
    PreparedStatement statement = null;
    
    try {
        connection = mySql.openConnection();
        String sql = "UPDATE SellerRegistration SET password=?, rePassword=? WHERE email=?";
        statement = connection.prepareStatement(sql);
        
        statement.setString(1, newPassword);
        statement.setString(2, newPassword);
        statement.setString(3, email);
        
        int result = statement.executeUpdate();
        
        if (result > 0) {
            System.out.println("✅ Seller password updated successfully for: " + email);
        } else {
            System.out.println("❌ Seller password update failed for: " + email);
        }
        
        return result > 0;
        
    } catch (SQLException e) {
        System.err.println("Error updating seller password: " + e.getMessage());
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