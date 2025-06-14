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
    private String name;
    private String type;
    private double pricePerDay;
    private String status;
    private String imagePath;

    public Vehicle(String name, String type, double price, String status, String imagePath) {
        this.name = name;
        this.type = type;
        this.pricePerDay = price;
        this.status = status;
        this.imagePath = imagePath;
    }

    public String getName() { return name; }
    public String getType() { return type; }
    public double getPricePerDay() { return pricePerDay; }
    public String getStatus() { return status; }
    public String getImagePath() { return imagePath; }
}


