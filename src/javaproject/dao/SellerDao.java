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
import javaproject.model.SellerData;
import javaproject.model.UserData;

/**
 *
 * @author ACER
 */
public class SellerDao {
    MySqlConnection mySql=new MySqlConnection();
    public boolean sellerRegister(SellerData seller){
        String query="insert into users(fullName,location,Email,contactNumber,password,rePasword,panNumber)values(?,?,?,?,?,?,?)";
        Connection conn=mySql.openConnection();
        try{
            PreparedStatement stmnt=conn.prepareStatement(query);
            stmnt.setString(1, seller.getFullName());
            stmnt.setString(2,seller.getLocation());
            stmnt.setString(3, seller.getEmail());
            stmnt.setString(4,seller.getContactNumber());
            stmnt.setString(5,seller.getPassword());
            stmnt.setString(6,seller.getRePassword());
            stmnt.setString(7, seller.getPanNumber());
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
     public SellerData login(LoginRequest loginReq){
        String query="Select * from users where email=? and password=?";
        Connection conn=mySql.openConnection();
        try{
            PreparedStatement stmnt=conn.prepareStatement(query);
            stmnt.setString(1,loginReq.getEmail()); // To get email and set email to insert
            stmnt.setString(2,loginReq.getPassword());
            ResultSet result=stmnt.executeQuery();
            if(result.next()){
                String email=result.getString("email"); // To get the email
                String name=result.getString("fullName");
                String password=result.getString("password");
                String id=result.getString("sellerId");
                SellerData seller=new SellerData(id,name,email,password); //
                return seller;
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
