/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vroomproject1.Dao;


import vroomproject1.Database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import vroomproject1.Model.ResetData;
import java.sql.*;
import java.util.Random;



/**
 *
 * @author Dell
 */
public class ResetDao {
        MySqlConnection mySql = new MySqlConnection();

    // Save OTP
    public boolean saveOtp(String email, String otp) {
        String query = "INSERT INTO OtpStore (email, otp, expiryTime) VALUES (?, ?, DATE_ADD(NOW(), INTERVAL 10 MINUTE))";
        Connection conn = mySql.openConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, otp);
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            mySql.closeConnection(conn);
        }
    }

    // Generate OTP
    public String generateOtp() {
        return String.format("%06d", new Random().nextInt(999999));
    }

    // Verify OTP
    public boolean verifyOtp(String email, String otp) {
        String query = "SELECT * FROM OtpStore WHERE email = ? AND otp = ? AND expiryTime > NOW()";
        Connection conn = mySql.openConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, otp);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            mySql.closeConnection(conn);
        }
    }

    // Delete OTP
    public void deleteOtp(String email) {
        String query = "DELETE FROM OtpStore WHERE email = ?";
        Connection conn = mySql.openConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mySql.closeConnection(conn);
        }
    }

    // Reset password
    public boolean resetPassword(ResetData reset) {
        String query = "UPDATE Registration SET password = ? WHERE email = ?";
        Connection conn = mySql.openConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, reset.getNewPassword());
            stmt.setString(2, reset.getEmail());
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            mySql.closeConnection(conn);
        }
    }

    // Additional existing methods (optional)

    public boolean checkEmail(String email) {
        String query = "SELECT * FROM Registration WHERE email = ?";
        Connection conn = mySql.openConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet result = stmt.executeQuery();
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            mySql.closeConnection(conn);
        }
    }
}


