package javaproject.dao;

import javaproject.database.MySqlConnection;
import javaproject.model.Vehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDaoImpl implements VehicleDao {

    private final MySqlConnection db = new MySqlConnection();

    @Override
    public boolean addVehicle(Vehicle v) {
        String sql = "INSERT INTO vehicles (id, name, type, price, status, image_path) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = db.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, v.getVehicleId());
            stmt.setString(2, v.getName());
            stmt.setString(3, v.getType());
            stmt.setDouble(4, v.getPrice());
            stmt.setString(5, v.getStatus());
            stmt.setString(6, v.getImagePath());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles";
        try (Connection conn = db.openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Vehicle v = new Vehicle(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("type"),
                    rs.getDouble("price"),
                    rs.getString("status"),
                    rs.getString("image_path")
                );
                vehicles.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    @Override
    public boolean updateVehicle(Vehicle v) {
        String sql = "UPDATE vehicles SET name=?, type=?, price=?, status=?, image_path=? WHERE id=?";
        try (Connection conn = db.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, v.getName());
            stmt.setString(2, v.getType());
            stmt.setDouble(3, v.getPrice());
            stmt.setString(4, v.getStatus());
            stmt.setString(5, v.getImagePath());
            stmt.setString(6, v.getVehicleId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteVehicle(String vehicleId) {
        String sql = "DELETE FROM vehicles WHERE id=?";
        try (Connection conn = db.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehicleId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
