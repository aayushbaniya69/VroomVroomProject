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
public class VehicleController {
    public ArrayList<Vehicle> getAllVehicles() {
        ArrayList<Vehicle> list = new ArrayList<>();
        list.add(new Vehicle(1, "Toyota Prius", "Sedan", 50.0, "Available", "images/prius.jpg"));
        list.add(new Vehicle(2, "Yamaha R15", "Bike", 20.0, "Booked", "images/r15.jpg"));
        return list;
    }

    public ArrayList<Vehicle> filterByType(String type) {
        ArrayList<Vehicle> filtered = new ArrayList<>();
        for (Vehicle v : getAllVehicles()) {
            if (v.getType().equalsIgnoreCase(type)) {
                filtered.add(v);
            }
        }
        return filtered;
    }
}
