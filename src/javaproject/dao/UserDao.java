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
    
    /**
     * ✅ FIXED: Check if email exists in Registration table
     */
    public boolean checkEmail(String email){
        String query = "SELECT * FROM Registration WHERE email = ?";
        Connection conn = mySql.openConnection();
        try{
            PreparedStatement stmnt = conn.prepareStatement(query);
            stmnt.setString(1, email.trim());
            ResultSet result = stmnt.executeQuery();
            
            boolean found = result.next();
            if (found) {
                System.out.println("✅ User email found: " + email);
            } else {
                System.out.println("❌ User email not found: " + email);
            }
            
            return found;
        }
        catch(SQLException e){
            System.err.println("Error checking user email: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        finally{
            mySql.closeConnection(conn);
        }
    }
    
    /**
     * ✅ FIXED: Reset password for user
     */
    public boolean resetPassword(String email, String newPassword) {
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = mySql.openConnection();
            String sql = "UPDATE Registration SET password=?, rePassword=? WHERE email=?";
            statement = connection.prepareStatement(sql);
            
            statement.setString(1, newPassword);
            statement.setString(2, newPassword);
            statement.setString(3, email);
            
            int result = statement.executeUpdate();
            
            if (result > 0) {
                System.out.println("✅ User password reset successfully for: " + email);
            } else {
                System.out.println("❌ User password reset failed for: " + email);
            }
            
            return result > 0;
            
        } catch (SQLException e) {
            System.err.println("Error resetting user password: " + e.getMessage());
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