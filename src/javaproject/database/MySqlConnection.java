/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;



import java.sql.SQLException;
/**
 *
 * @author ACER
 */
public class MySqlConnection implements DbConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/vroomvroom";
    private static final String USER="root";
    private static final String PASSWORD="brbprssssrbss@8";
    public Connection getConnection() throws ClassNotFoundException, java.sql.SQLException {
       try{
           Class.forName("com.mysql.jdbc.Driver");
       } catch(ClassNotFoundException e){
        throw new SQLException("Jdbc not found", e);   
    }
        
       return DriverManager.getConnection(URL, USER, PASSWORD);
    }

   @Override
public Connection openConnection() {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
        return null;
    }
}

@Override
public void closeConnection(Connection conn) {
    if (conn != null) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

@Override
public ResultSet runQuery(Connection conn, String query) {
    try {
        PreparedStatement stmt = conn.prepareStatement(query);
        return stmt.executeQuery();
    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
}

@Override
public int executeQuery(Connection conn, String query) {
    try {
        PreparedStatement stmt = conn.prepareStatement(query);
        return stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        return 0;
    }
}
    
    
}
