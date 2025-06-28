/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject.controller;

import javaproject.view.UserHomePanel;
import javaproject.dao.HomeBookingDao;

public class HomeController {

    private UserHomePanel homePanel;
    private HomeBookingDao bookingDao;  // Assume you have this DAO implemented

    private int userId;

    public HomeController(UserHomePanel homePanel, int userId) {
        this.homePanel = homePanel;
        this.userId = userId;
        this.bookingDao = new HomeBookingDao(); // Initialize your DAO here

        loadStats();
    }

    private void loadStats() {
        // Get total vehicles from VehicleController singleton
        int totalVehicles = VehicleController.getInstance().getAllVehicles().size();

        // Get total bookings for this user from bookingDao
        int myBookings = bookingDao.getBookingCountByUserId(userId);

        // Update your UI panel
        homePanel.setTotalVehicles(totalVehicles);
        homePanel.setMyBookings(myBookings);
    }
}


