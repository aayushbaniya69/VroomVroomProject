/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject.dao;

import java.sql.Connection;
import javaproject.model.UserData;
import java.sql.*;
import java.sql.PreparedStatement;
import javaproject.database.MySqlConnection;

/**
 *
 * @author ACER
 */
public class UserProfileDao {
    public UserData getUserByEmail(String email) {
    UserData user = null;
    String query = "SELECT * FROM Registration WHERE email = ?";

    try (Connection conn = MySqlConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setString(1, email);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                user = new UserData(
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("address"),
                    rs.getString("contact")
                );
            }
        }

    } catch (SQLException e) {
        System.err.println("Error fetching user by email from database.");
        e.printStackTrace();
    }

    return user;
}
     
    public boolean updateUser(UserData user) {
    String query = "UPDATE Registration SET username = ?, address = ?, contact = ? WHERE email = ?";
    try (Connection conn = MySqlConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getAddress());
        stmt.setString(3, user.getContact());
        stmt.setString(4, user.getEmail());
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        System.err.println("Error updating user in database.");
        e.printStackTrace();
        return false;
    }
}
}
