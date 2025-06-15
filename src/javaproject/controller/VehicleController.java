/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject.controller;

import Dashboard.model.Vehicle;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
import java.util.ArrayList;
import java.util.stream.Collectors;

public class VehicleController {
    private ArrayList<Vehicle> allVehicles;

    public VehicleController() {
        allVehicles = new ArrayList<>();
        allVehicles.add(new Vehicle("Honda City", "Car", 50, "Available", "images/car.png"));
        allVehicles.add(new Vehicle("Suzuki Bike", "Bike", 20, "Booked", "images/bike.png"));
        allVehicles.add(new Vehicle("Tata Van", "Van", 40, "Available", "images/van.png"));
    }

    public ArrayList<Vehicle> getAllVehicles() {
        return allVehicles;
    }

    public ArrayList<Vehicle> filterByType(String type) {
        return (ArrayList<Vehicle>) allVehicles.stream()
                .filter(v -> v.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }
}


