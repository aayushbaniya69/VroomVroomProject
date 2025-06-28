package javaproject.dao;

import javaproject.model.Booking;
import javaproject.database.MySqlConnection;
import java.sql.*;

public class BookingDao {
    MySqlConnection mySql = new MySqlConnection();

    public boolean insertBooking(Booking booking) {
        String query = "INSERT INTO bookings(user_id, vehicle_info, start_date, end_date, number_of_vehicles, total_amount) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = mySql.openConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, booking.getUserId());
            stmt.setString(2, booking.getVehicleInfo());
            stmt.setString(3, booking.getStartDate());
            stmt.setString(4, booking.getEndDate());
            stmt.setInt(5, booking.getNumberOfVehicles());
            stmt.setDouble(6, booking.getTotalAmount());

            int result = stmt.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            System.err.println("❌ Booking insert failed: " + e.getMessage());
            return false;
        }
    }
}
