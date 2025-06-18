/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject.model;

/**
 *
 * @author ACER
 */
public class Booking {
     //Private Attributes
     private int id;
    private int userId;
    private String vehicleInfo;
    private String startDate;
    private String endDate;
    private int numberOfVehicles;
    private double totalAmount;

    // Constructors
    public Booking(){
        
    }
     public Booking(int id, int userId, String vehicleInfo, String startDate, String endDate, int numberOfVehicles, double totalAmount){
        this.id=id;
        this.userId=userId;
        this.vehicleInfo=vehicleInfo;
        this.startDate=startDate;
        this.endDate=endDate;
        this.numberOfVehicles=numberOfVehicles;
        this.totalAmount=totalAmount;
    }
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getVehicleInfo() {
        return vehicleInfo;
    }

    public void setVehicleInfo(String vehicleInfo) {
        this.vehicleInfo = vehicleInfo;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getNumberOfVehicles() {
        return numberOfVehicles;
    }

    public void setNumberOfVehicles(int numberOfVehicles) {
        this.numberOfVehicles = numberOfVehicles;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

}
  