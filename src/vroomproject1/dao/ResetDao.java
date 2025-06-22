/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vroomproject1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javaproject.database.MySqlConnection;

/**
 *
 * @author Dell
 */
public class ResetDao {
    
public boolean validateSecurityAnswer(String email,String security_question,String security_answer){
    try(Connection conn=MySqlConnection.getConnection()){
        String query="SELECT * FROM users WHERE " +
                "LOWER(TRIM(email))= ? AND " +
                "LOWER(TRIM(security_question))= ? AND " +
                "LOWER(TRIM(security_answer))= ? ";
        PreparedStatement stmt=conn.prepareStatement(query);
        stmt.setString(1, email.trim().toLowerCase());
        stmt.setString(2, security_question.trim().toLowerCase());
        stmt.setString(3, security_answer.trim().toLowerCase());
        ResultSet rs=stmt.executeQuery();
        return rs.next();
    } catch(Exception e) {
        e.printStackTrace();
        return false;
    }
}
public boolean updatePassword(String email,String newPassword){
    try(Connection conn=MySqlConnection.getConnection()){
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

