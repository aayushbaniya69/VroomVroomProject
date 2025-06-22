package javaproject.model;

import java.util.Objects;

/**
 * Vehicle class representing a vehicle with basic properties like id, name, type, price, status, and image path.
 * Includes validation, toString, and equals/hashCode methods.
 */
public class Vehicle {
    private String vehicleId;
    private String name;
    private String type;
    private double price;
    private String status;
    private String imagePath;

    /**
     * Constructor for creating a new Vehicle object.
     * @param vehicleId Vehicle ID.
     * @param name Vehicle name.
     * @param type Vehicle type (e.g., Car, Truck, etc.).
     * @param price Vehicle rental price.
     * @param status Vehicle status (e.g., Available, Rented, etc.).
     * @param imagePath Path to the image representing the vehicle.
     */
    public Vehicle(String vehicleId, String name, String type, double price, String status, String imagePath) {
        if (vehicleId == null || vehicleId.isEmpty()) {
            throw new IllegalArgumentException("Vehicle ID cannot be empty.");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Type cannot be empty.");
        }
        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Status cannot be empty.");
        }

        this.vehicleId = vehicleId;
        this.name = name;
        this.type = type;
        this.price = price;
        this.status = status;
        this.imagePath = imagePath != null ? imagePath : "default-image.png"; // Handle null imagePath
    }

    // Getters
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

    // Setters
    public void setVehicleId(String vehicleId) {
        if (vehicleId == null || vehicleId.isEmpty()) {
            throw new IllegalArgumentException("Vehicle ID cannot be empty.");
        }
        this.vehicleId = vehicleId;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;
    }

    public void setType(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Type cannot be empty.");
        }
        this.type = type;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStatus(String status) {
        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Status cannot be empty.");
        }
        this.status = status;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath != null ? imagePath : "default-image.png"; // Handle null imagePath
    }

    // Override toString for easy printing and debugging
    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId='" + vehicleId + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }

    // Override equals and hashCode based on vehicleId (since it's unique)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vehicle vehicle = (Vehicle) obj;
        return vehicleId.equals(vehicle.vehicleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleId);
    }
}
