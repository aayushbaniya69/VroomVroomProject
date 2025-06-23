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
    MySqlConnection mySql=new MySqlConnection();
    public boolean Registration(UserData user){
        
        String query="insert into Registration(firstName,lastName,address,email,contactNumber,password,rePassword,securityAnswer)values(?,?,?,?,?,?,?,?)";
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
            e.printStackTrace(); // Add this line
            return false;
        }
        finally{
            mySql.closeConnection(conn);
        }
    }
     public UserData login(LoginRequest loginReq){
        String query="Select * from Registration where email=? and password=?";
        Connection conn=mySql.openConnection();
        try{
            PreparedStatement stmnt=conn.prepareStatement(query);
            System.out.println("QUEEERY");
            stmnt.setString(1,loginReq.getEmail()); // To get email and set email to insert
            stmnt.setString(2,loginReq.getPassword());
            ResultSet result=stmnt.executeQuery();
            if(result.next()){
                System.out.println("Data found");
                String email=result.getString("email"); // To get the email
                String firstName=result.getString("firstName");
                String password=result.getString("password");
                String registrationId=result.getString("registrationId");
                UserData user=new UserData(registrationId,firstName,email,password); 
                return user;
            }
            else{
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
        String query="Select * from Registration where email=?";
        Connection conn=mySql.openConnection();
        try{
            PreparedStatement stmnt=conn.prepareStatement(query);
            stmnt.setString(1,email);
            ResultSet result=stmnt.executeQuery();// Return selected rows
            
            if(result.next()){ // Check whether rows return or not
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
    public boolean resetPassword(String email,String newPassword){
        String query="Update Registration set password=? where email=?";
        Connection conn=mySql.openConnection();
        try{
            PreparedStatement stmnt=conn.prepareStatement(query); //PrepareStatement more secure 
            stmnt.setString(1,email);// To set the email value in above query
            stmnt.setString(2,newPassword); //To set the password value in above query
            int result=stmnt.executeUpdate(); //Return updated rows
            return result>0; //Return rows then true otherwise false
        }
        catch(SQLException e){
            e.printStackTrace();// Print the actual SQL error
            return false;
        }
        finally{
            mySql.closeConnection(conn);
        }
    }
}
