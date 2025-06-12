/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dashboard.model;

/**
 *
 * @author ASUS
 */
public class Vehicle {
    private int id;
    private String name;
    private String type;
    private double pricePerDay;
    private String status; // Available / Booked
    private String imagePath;

    // Constructor
    public Vehicle(int id, String name, String type, double pricePerDay, String status, String imagePath) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.pricePerDay = pricePerDay;
        this.status = status;
        this.imagePath = imagePath;
    }
}
