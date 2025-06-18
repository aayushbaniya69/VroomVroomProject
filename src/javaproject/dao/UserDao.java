/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javaproject.database.MySqlConnection;
import javaproject.model.LoginRequest;
import javaproject.model.ResetPasswordRequest;
import javaproject.model.UserData;

/**
 *
 * @author ACER
 */
public class UserDao {

    public static boolean registration(UserData userData) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    MySqlConnection mySql=new MySqlConnection();
    public boolean register(UserData user){
        String query="insert into users(First_Name,Last_Name,Address,Email,Phone_Number,Password,Re_Pasword,Security_Answer)values(?,?,?,?,?,?,?,?)";
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
     public UserData login(LoginRequest loginReq){
        String query="Select * from users where email=? and password=?";
        Connection conn=mySql.openConnection();
        try{
            PreparedStatement stmnt=conn.prepareStatement(query);
            stmnt.setString(1,loginReq.getEmail()); // To get email and set email to insert
            stmnt.setString(2,loginReq.getPassword());
            ResultSet result=stmnt.executeQuery();
            if(result.next()){
                String email=result.getString("email"); // To get the email
                String name=result.getString("name");
                String password=result.getString("password");
                String id=result.getString("Id");
                //UserData user=new UserData(id,name,email,password); //
                //return user;
            }
            else{
                return null;
            }
        }
        catch(Exception e){
            return null;
        }
        finally{
            mySql.closeConnection(conn);
        }
        return null;
    }
    public boolean checkEmail(String email){
        String query="Select * from users where email=?";
        Connection conn=mySql.openConnection();
        try{
            PreparedStatement stmnt=conn.prepareStatement(query);
            stmnt.setString(1,email);
            ResultSet result=stmnt.executeQuery(); // Return selected rows
            if(result.next()){ // Check whether rows return or not
                return true;
            }
            else{
                return false;
            }
        }
        catch(Exception e){
            return false;
        }
        finally{
            mySql.closeConnection(conn);
        }
    }
    public boolean resetPassword(ResetPasswordRequest reset){
        String query="Update users set password=? where email=?";
        Connection conn=mySql.openConnection();
        try{
            PreparedStatement stmnt=conn.prepareStatement(query); //PrepareStatement more secure 
            stmnt.setString(1,reset.getPassword());// To set the email value in above query
            stmnt.setString(2,reset.getEmail()); //To set the password value in above query
            int result=stmnt.executeUpdate(); //Return updated rows
            return result>0; //Return rows then true otherwise false
        }
        catch(Exception e){
            return false;
        }
        finally{
            mySql.closeConnection(conn);
        }
    }
}
