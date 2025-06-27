package javaproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javaproject.database.MySqlConnection;
import javaproject.model.LoginRequest;
import javaproject.model.UserData;

public class UserDao {
    MySqlConnection mySql = new MySqlConnection();
    
    public boolean Registration(UserData user){
        String query = "insert into Registration(firstName,lastName,address,email,contactNumber,password,rePassword,securityAnswer)values(?,?,?,?,?,?,?,?)";
        Connection conn = mySql.openConnection();
        try{
            PreparedStatement stmnt = conn.prepareStatement(query);
            
            stmnt.setString(1, user.getFirstName());
            stmnt.setString(2, user.getLastName());
            stmnt.setString(3, user.getAddress());
            stmnt.setString(4, user.getEmail());
            stmnt.setString(5, user.getContactNumber());
            stmnt.setString(6, user.getPassword());
            stmnt.setString(7, user.getRePassword());
            stmnt.setString(8, user.getSecurityAnswer());
            int result = stmnt.executeUpdate();
            return result > 0;
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        finally{
            mySql.closeConnection(conn);
        }
    }
    
    public UserData login(LoginRequest loginReq){
        String query = "Select * from Registration where email=? and password=?";
        Connection conn = mySql.openConnection();
        try{
            PreparedStatement stmnt = conn.prepareStatement(query);
            System.out.println("Executing login query for email: " + loginReq.getEmail());
            stmnt.setString(1, loginReq.getEmail());
            stmnt.setString(2, loginReq.getPassword());
            ResultSet result = stmnt.executeQuery();
            if(result.next()){
                System.out.println("Login successful - User data found");
                String email = result.getString("email");
                String firstName = result.getString("firstName");
                String password = result.getString("password");
                String registrationId = result.getString("registrationId");
                UserData user = new UserData(registrationId, firstName, email, password); 
                System.out.println("UserData created with email: " + user.getEmail());
                return user;
            }
            else{
                System.out.println("Login failed - No matching user found");
                return null;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        finally{
            mySql.closeConnection(conn);
        }
    }
    
    public boolean checkEmail(String email){
        String query = "Select * from Registration where email=?";
        Connection conn = mySql.openConnection();
        try{
            PreparedStatement stmnt = conn.prepareStatement(query);
            stmnt.setString(1, email);
            ResultSet result = stmnt.executeQuery();
            
            if(result.next()){
                return true;
            }
            else{
                return false;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        finally{
            mySql.closeConnection(conn);
        }
    }
    
    // FIX: Enhanced resetPassword method with detailed debugging
    public boolean resetPassword(String email, String newPassword){
        if (email == null || email.trim().isEmpty() || newPassword == null || newPassword.trim().isEmpty()) {
            System.err.println("ERROR: Invalid email or password for reset");
            return false;
        }

        String query = "UPDATE Registration SET password=?, rePassword=? WHERE email=?";
        Connection conn = mySql.openConnection();
        
        if (conn == null) {
            System.err.println("ERROR: Failed to open database connection for password reset");
            return false;
        }

        System.out.println("=== RESET PASSWORD DEBUG ===");
        System.out.println("Email: '" + email + "'");
        System.out.println("New Password Length: " + newPassword.length());
        System.out.println("SQL Query: " + query);

        try{
            PreparedStatement stmnt = conn.prepareStatement(query);
            stmnt.setString(1, newPassword); // password
            stmnt.setString(2, newPassword); // rePassword (same as password)
            stmnt.setString(3, email);       // WHERE email = ?
            
            System.out.println("Executing password reset query...");
            int result = stmnt.executeUpdate();
            System.out.println("Password reset result - rows affected: " + result);
            
            if (result == 0) {
                // Check if user exists
                String checkQuery = "SELECT COUNT(*) FROM Registration WHERE email = ?";
                PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
                checkStmt.setString(1, email);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next()) {
                    int count = rs.getInt(1);
                    System.err.println("User exists check: " + count + " users found with email '" + email + "'");
                }
                checkStmt.close();
            }
            
            return result > 0;
        }
        catch(SQLException e){
            System.err.println("ERROR: SQL Exception in resetPassword: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        finally{
            mySql.closeConnection(conn);
        }
    }
    // Add these methods to your existing UserDao.java

public boolean validateSecurityAnswer(String email, String question, String answer) {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    
    try {
        connection = mySql.openConnection();
        String sql = "SELECT * FROM Registration WHERE email = ? AND securityAnswer = ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, answer);
        
        resultSet = statement.executeQuery();
        return resultSet.next();
        
    } catch (SQLException e) {
        System.err.println("Error validating security answer: " + e.getMessage());
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
}