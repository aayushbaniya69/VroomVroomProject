/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject.database;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.*;
import java.sql.SQLException;
/**
 *
 * @author ACER
 */
public class MySqlConnection implements DbConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String username="root";
    private static final String password="brbprssssrbss@8";
    public Connection getConnection() throws ClassNotFoundException, java.sql.SQLException {
       try{
           Class.forName("com.mysql.jdbc.Driver");
       } catch(ClassNotFoundException e){
        throw new SQLException("Jdbc not found", e);   
    }
        
       return DriverManager.getConnection(URL, username, password);
    }

    @Override
    public Connection openConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void closeConnection(Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResultSet runQuery(Connection conn, String query) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int executeQuery(Connection conn, String query) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
