package javaproject.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySqlConnection implements DbConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/vroomvroom?useSSL=false&serverTimezone=Asia/Kathmandu";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    @Override
    public Connection openConnection() {
        try {
            // Remove Class.forName as SPI handles driver loading in Connector/J 9.2.0
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
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

    // Remove the deprecated getConnection method
    // public Connection getConnection() throws ClassNotFoundException, SQLException { ... }
}