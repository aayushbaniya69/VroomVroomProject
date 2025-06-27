package javaproject.controller;

import java.io.File;
import javaproject.model.Vehicle;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Singleton Controller for managing vehicle data.
 * Provides CRUD operations and utility methods for vehicles.
 */
public class VehicleController {

    private final List<Vehicle> allVehicles;

    // Step 1: Static instance
    private static VehicleController instance;

    /**
     * Private constructor to prevent external instantiation.
     * Initializes with sample data.
     */
    private VehicleController() {
        allVehicles = new ArrayList<>();
        loadInitialData();
    }

    /**
     * Load initial sample data for vehicles.
     */
    private void loadInitialData() {
    allVehicles.add(new Vehicle("V001", "Ferrari", "Car", 50.0, "Available", new File("src/Dashboard/images/Car.png").getAbsolutePath()));
    allVehicles.add(new Vehicle("V002", "Kawasaki Ninja H2R", "Bike", 20.0, "Booked", new File("src/Dashboard/images/bike.jpg").getAbsolutePath()));
    allVehicles.add(new Vehicle("V003", "Tata Van", "Van", 40.0, "Available", new File("src/Dashboard/images/van.jpg").getAbsolutePath()));
}



    /**
     * Get the singleton instance of VehicleController.
     *
     * @return The single instance of VehicleController.
     */
    public static VehicleController getInstance() {
        if (instance == null) {
            instance = new VehicleController();
        }
        return instance;
    }

    /**
     * Adds a new vehicle to the list.
     *
     * @param vehicle The vehicle to be added.
     */
    public void addVehicle(Vehicle vehicle) {
        if (vehicle == null || allVehicles.stream().anyMatch(v -> v.getVehicleId().equals(vehicle.getVehicleId()))) {
            throw new IllegalArgumentException("Vehicle is null or ID already exists.");
        }
        allVehicles.add(vehicle);
    }

    /**
     * Updates an existing vehicle in the list.
     *
     * @param vehicleId The ID of the vehicle to be updated.
     * @param updatedVehicle The updated vehicle object.
     */
    public void updateVehicle(String vehicleId, Vehicle updatedVehicle) {
        Objects.requireNonNull(updatedVehicle, "Updated vehicle cannot be null");

        for (int i = 0; i < allVehicles.size(); i++) {
            if (allVehicles.get(i).getVehicleId().equalsIgnoreCase(vehicleId)) {
                allVehicles.set(i, updatedVehicle);
                return;
            }
        }

        throw new IllegalArgumentException("Vehicle with the given ID does not exist.");
    }

    /**
     * Deletes a vehicle by its ID.
     *
     * @param vehicleId The ID of the vehicle to be deleted.
     */
    public void deleteVehicle(String vehicleId) {
        allVehicles.removeIf(v -> v.getVehicleId().equalsIgnoreCase(vehicleId));
    }

    /**
     * Retrieves all vehicles.
     *
     * @return A list of all vehicles.
     */
    public List<Vehicle> getAllVehicles() {
        return new ArrayList<>(allVehicles);
    }

    /**
     * Filters vehicles by their type.
     *
     * @param type The vehicle type to filter by.
     * @return A list of vehicles matching the given type.
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
     * Finds a vehicle by its ID.
     *
     * @param vehicleId The vehicle ID to search for.
     * @return The vehicle with the given ID, or null if not found.
     */
    public Vehicle findVehicleById(String vehicleId) {
        return allVehicles.stream()
                          .filter(v -> v.getVehicleId().equalsIgnoreCase(vehicleId))
                          .findFirst()
                          .orElse(null);
    }
}
