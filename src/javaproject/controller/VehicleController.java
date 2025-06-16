/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject.controller;

import Dashboard.model.Vehicle;
import java.util.ArrayList;

public class VehicleController {
    private final ArrayList<Vehicle> allVehicles;



    public VehicleController() {
        allVehicles = new ArrayList<>();
        allVehicles.add(new Vehicle("Honda City", "Car", 50, "Available", "images/car.png"));
        allVehicles.add(new Vehicle("Suzuki Bike", "Bike", 20, "Booked", "images/bike.png"));
        allVehicles.add(new Vehicle("Tata Van", "Van", 40, "Available", "images/van.png"));
    }

    public void addVehicle(Vehicle vehicle) {
    allVehicles.add(vehicle);
    }

    public void updateVehicle(String originalName, Vehicle updatedVehicle) {
    for (int i = 0; i < allVehicles.size(); i++) {
        if (allVehicles.get(i).getName().equalsIgnoreCase(originalName)) {
            allVehicles.set(i, updatedVehicle);
            break;
            }
        }
    }

    public void deleteVehicle(String name) {
    allVehicles.removeIf(v -> v.getName().equalsIgnoreCase(name));
    }

    public ArrayList<Vehicle> getAllVehicles() {
        return new ArrayList<>(allVehicles); // return a copy to avoid accidental modification
    }

    /**
     * Filters vehicles by the given type (case-insensitive).
     * @param type Vehicle type to filter (e.g., "Car", "Bike", "Van")
     * @return list of vehicles matching the type
     */
    public ArrayList<Vehicle> filterVehiclesByType(String type) {
        ArrayList<Vehicle> filtered = new ArrayList<>();
        for (Vehicle v : allVehicles) {
            if (v.getType().equalsIgnoreCase(type)) {
                filtered.add(v);
            }
        }
        return filtered;
    }

}


