package javaproject.dao;

import javaproject.model.Vehicle;
import java.util.List;

public interface VehicleDao {
    boolean addVehicle(Vehicle v);
    List<Vehicle> getAllVehicles();
    boolean updateVehicle(Vehicle v);
    boolean deleteVehicle(String vehicleId);
}
