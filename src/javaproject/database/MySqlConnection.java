package javaproject.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySqlConnection implements DbConnection {

    private static final String url = "jdbc:mysql://localhost:3306/vroomvroom?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Kathmandu";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    @Override
    public Connection openConnection() {
        try {
            return DriverManager.getConnection(url, USER, PASSWORD);
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

    public static Connection getConnection() throws SQLException {
        // No need for Class.forName() in Connector/J 8+ (including 9.x)
        return DriverManager.getConnection(url, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try (Connection conn = MySqlConnection.getConnection()) {
            if (conn != null) {
                System.out.println("✅ Connection Successful!");
            } else {
                System.out.println("❌ Connection returned null.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Connection Unsuccessful!");
            e.printStackTrace();
        }
    }
}
