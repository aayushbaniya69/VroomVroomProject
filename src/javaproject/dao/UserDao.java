/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javaproject.database.MySqlConnection;
import javaproject.model.UserData;

/**
 *
 * @author ACER
 */
public class UserDao {
    MySqlConnection mySql=new MySqlConnection();
    public boolean register(UserData user){
        String query="insert into users(FirstName,LastName,Address,email,Contact Number,password,Confirm Pasword,Security Answer)values(?,?,?,?,?,?,?,?)";
        Connection conn=mySql.openConnection();
        try{
            PreparedStatement stmnt=conn.prepareStatement(query);
            stmnt.setString(1, user.getFirstName());
            stmnt.setString(2,user.getLastName());
            stmnt.setString(3,user.getAddress());
            stmnt.setString(4, user.getEmail());
            stmnt.setString(5,user.getContactNumber());
            stmnt.setString(6,user.getPassword());
            stmnt.setString(7,user.getRePassword());
            stmnt.setString(8, user.getSecurityAnswer());
            int result=stmnt.executeUpdate();
            return result>0;
        }
        catch(SQLException e){
            return false;
        }
        finally{
            mySql.closeConnection(conn);
        }
    }
}
