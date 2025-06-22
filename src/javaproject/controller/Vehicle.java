/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject.model;

/**
 *
 * @author ASUS
 */
public class Vehicle {
    private String vehicleId;
    private String name;
    private String type;
    private double price;
    private String status;
    private String imagePath;
    
    

    public Vehicle(String vehicleId, String name, String type, double price, String status, String imagePath) {
        this.vehicleId = vehicleId;
        this.name = name;
        this.type = type;
        this.price = price;
        this.status = status;
        this.imagePath = imagePath;
    }
    
    public String getVehicleId() {
        return vehicleId;
    }

    public String getName() { 
        return name; 
    }
    public String getType() { 
        return type;
    }
    public double getPrice() {
        return price;
    }
    public String getStatus() { 
        return status;
    }
    public String getImagePath() {
        return imagePath;
    }
}


