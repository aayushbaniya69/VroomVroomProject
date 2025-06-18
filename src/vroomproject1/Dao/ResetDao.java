/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vroomproject1.Dao;


import vroomproject1.Database.MySqlConnection;
import vroomproject1.Model.ResetData;
import java.sql.*;
import java.util.Random;



/**
 *
 * @author Dell
 */
public class ResetDao {
    
public boolean validateSecurityAnswer(String email, String security_question, String security_answer) {
    MySqlConnection db = new MySqlConnection(); // Instantiate the class

    try (Connection conn = db.openConnection()) {
        String query = "SELECT * FROM users WHERE " +
                "LOWER(TRIM(email)) = ? AND " +
                "LOWER(TRIM(security_question)) = ? AND " +
                "LOWER(TRIM(security_answer)) = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, email.trim().toLowerCase());
        stmt.setString(2, security_question.trim().toLowerCase());
        stmt.setString(3, security_answer.trim().toLowerCase());
        ResultSet rs = stmt.executeQuery();
        return rs.next();
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
public boolean updatePassword(String email,String newPassword){
    MySqlConnection db = new MySqlConnection();
try (Connection conn = db.openConnection()) {
        String query="UPDATE users SET password=? WHERE email=?";
        PreparedStatement stmt=conn.prepareStatement(query);
        stmt.setString(1,newPassword);
        stmt.setString(2,email);
        int rows=stmt.executeUpdate();
        return rows>0;
    } catch (Exception e){
        e.printStackTrace();
        return false;
    }
}
}