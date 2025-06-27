package javaproject.controller;

import javaproject.dao.VehicleDao;
import javaproject.dao.VehicleDaoImpl;
import javaproject.model.Vehicle;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Singleton Controller for managing vehicle data.
 * Uses VehicleDao for DB operations and keeps an internal cached list.
 * Loads initial sample data into DB if DB is empty.
 */
public class VehicleController {

    private List<Vehicle> allVehicles;
    private VehicleDao vehicleDao;

    // Singleton instance
    private static VehicleController instance;

    private VehicleController() {
        vehicleDao = new VehicleDaoImpl();
        allVehicles = new ArrayList<>();
        initializeData();
    }

    public static VehicleController getInstance() {
        if (instance == null) {
            instance = new VehicleController();
        }
        return instance;
    }

    /**
     * Initialize vehicles data.
     * Loads from DB if available.
     * If DB empty, loads initial sample data into DB and cache.
     */
    private void initializeData() {
        List<Vehicle> fromDb = vehicleDao.getAllVehicles();
        if (fromDb == null || fromDb.isEmpty()) {
            // DB empty, load initial sample data
            loadInitialData();
            // Save initial data to DB
            for (Vehicle v : allVehicles) {
                vehicleDao.addVehicle(v);
            }
        } else {
            // DB has data, load from DB
            allVehicles = fromDb;
        }
    }

    /**
     * Load initial sample data into the internal list.
     */
    private void loadInitialData() {
        allVehicles.add(new Vehicle("V001", "Ferrari", "Car", 50.0, "Available", new File("src/Dashboard/images/Car.png").getAbsolutePath()));
        allVehicles.add(new Vehicle("V002", "Kawasaki Ninja H2R", "Bike", 20.0, "Booked", new File("src/Dashboard/images/bike.jpg").getAbsolutePath()));
        allVehicles.add(new Vehicle("V003", "Tata Van", "Van", 40.0, "Available", new File("src/Dashboard/images/van.jpg").getAbsolutePath()));
    }

    /**
     * Refresh vehicle list from DB and update internal cache.
     */
    public void refreshVehicleList() {
        List<Vehicle> fromDb = vehicleDao.getAllVehicles();
        allVehicles = (fromDb != null) ? fromDb : new ArrayList<>();
    }

    /**
     * Add vehicle to DB and cache.
     */
    public boolean addVehicle(Vehicle vehicle) {
        if (vehicle == null || findVehicleById(vehicle.getVehicleId()) != null) {
            throw new IllegalArgumentException("Vehicle is null or ID already exists.");
        }

        boolean success = vehicleDao.addVehicle(vehicle);
        if (success) {
            allVehicles.add(vehicle);
        }
        return success;
    }

    /**
     * Update vehicle in DB and cache.
     */
    public boolean updateVehicle(String vehicleId, Vehicle updatedVehicle) {
        Objects.requireNonNull(updatedVehicle, "Updated vehicle cannot be null");

        boolean success = vehicleDao.updateVehicle(updatedVehicle);
        if (success) {
            for (int i = 0; i < allVehicles.size(); i++) {
                if (allVehicles.get(i).getVehicleId().equalsIgnoreCase(vehicleId)) {
                    allVehicles.set(i, updatedVehicle);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Delete vehicle from DB and cache.
     */
    public boolean deleteVehicle(String vehicleId) {
        boolean success = vehicleDao.deleteVehicle(vehicleId);
        if (success) {
            allVehicles.removeIf(v -> v.getVehicleId().equalsIgnoreCase(vehicleId));
        }
        return success;
    }

    /**
     * Get all vehicles from cache.
     */
    public List<Vehicle> getAllVehicles() {
        return new ArrayList<>(allVehicles);
    }

    /**
     * Filter vehicles by type.
     */
    public List<Vehicle> filterVehiclesByType(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Vehicle type cannot be null or empty.");
        }

        return allVehicles.stream()
                .filter(v -> v.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    /**
     * Find vehicle by ID.
     */
    public Vehicle findVehicleById(String vehicleId) {
        return allVehicles.stream()
                .filter(v -> v.getVehicleId().equalsIgnoreCase(vehicleId))
                .findFirst()
                .orElse(null);
    }
}
