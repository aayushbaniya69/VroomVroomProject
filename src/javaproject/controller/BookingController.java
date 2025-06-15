/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject.controller;


import javaproject.model.Booking;
import javaproject.view.BookingView;
import javaproject.dao.BookingDao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookingController {
    private BookingView view;
    private BookingDao dao;
    private int loggedInUserId;

    public BookingController(BookingView view, BookingDao dao, int userId) {
        this.view = view;
        this.dao = dao;
        this.loggedInUserId = userId;

        // Register listeners
//        this.view.BookButton(new BookButtonListener());
//        this.view.BackToDashbord(e ->backToDashboard());
//        this.view.Logout(e -> logout());
    }

    // Inner class to handle book button click
    class BookButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String vehicleInfo = view.getVehicleInfo().getText();
                String startDate = view.getStartDateField().getText();
                String endDate = view.getEndDateField().getText();
                int numVehicles = Integer.parseInt(view.NumberOfVehicleField.getText());
                double total = calculateTotal(numVehicles); // example total calculation

                Booking booking = new Booking(
                        0, // ID will be auto-incremented by DB
                        loggedInUserId,
                        vehicleInfo,
                        startDate,
                        endDate,
                        numVehicles,
                        total
                );

                boolean success = dao.insertBooking(booking);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Booking Successful!");
                    view.dispose(); // or navigate back
                } else {
                    JOptionPane.showMessageDialog(null, "Booking Failed!");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number of vehicles.");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Something went wrong!");
            }
        }
    }

    private double calculateTotal(int numVehicles) {
        double pricePerVehicle = 5000.00;
        return numVehicles * pricePerVehicle;
    }

    private void backToDashboard() {
        // Code to go back to dashboard
    }

    private void logout() {
        // Code to logout and show login screen
    }
}
