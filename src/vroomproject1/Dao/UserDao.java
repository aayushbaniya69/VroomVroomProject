/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vroomproject1.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import vroomproject1.Database.MySqlConnection;
import vroomproject1.Model.FilterData;
import vroomproject1.Model.UserData;

/**
 *
 * @author Dell
 */
public class UserDao {
    MySqlConnection mySql=new MySqlConnection();
    public boolean filter(FilterData data) {
    String query = "INSERT INTO filters(brand, vehicle_type, range_value, date) VALUES (?, ?, ?, ?)";
    Connection conn = mySql.openConnection();
    
    try {
        PreparedStatement stmnt = conn.prepareStatement(query);
        stmnt.setString(1, data.getBrand());
        stmnt.setString(2, data.getVehicleType());
        stmnt.setString(3, data.getRange());
        stmnt.setString(4, data.getDate());

        int result = stmnt.executeUpdate();
        return result > 0;
    } catch (SQLException e) { e.printStackTrace();  // Optional: for debugging
        return false;
    } finally {
        mySql.closeConnection(conn);
    }
}

    public boolean reset(UserData user){ //function banako insert garnu lai
        String query="INSERT INTO users(newPassword,email,confirmPassword)VALUES(?,?,?)"; //yeta chai database sanga match hunu paro
        Connection conn=mySql.openConnection();
        try{
            PreparedStatement stmnt=conn.prepareStatement(query);//object
            stmnt.setString(1,user.getNewPassword());  //yeta chai function sanga milnu paro
            stmnt.setString(2,user.getEmail());
            stmnt.setString(3,user.getConfirmPassword());
            //variable
            int result= stmnt.executeUpdate(); 
            return result>0;
        }catch(SQLException e){
            return false;
        }finally{
            mySql.closeConnection(conn);
        }
    }
}
