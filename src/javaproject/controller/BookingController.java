/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject.controller;

import javaproject.model.Booking;
import javaproject.view.BookingView;
import javaproject.dao.BookingDao;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javaproject.view.DashboardView;
import javaproject.view.LoginForm;

public class BookingController {
    private BookingView view;
    private BookingDao dao;
    private int loggedInUserId;
    private String email;

    // ✅ FIXED: Main constructor with proper initialization
    public BookingController(BookingView view) {
        this.view = view;
        this.dao = new BookingDao(); // ✅ FIXED: Initialize DAO
        this.loggedInUserId = 1; // ✅ FIXED: Set default user ID (you can pass this as parameter)
        
        // Initialize event listeners
        BookButton bookButton = new BookButton();
        this.view.BookButton(bookButton);
        
        Logout logout = new Logout();
        this.view.Logout(logout);
        
        BackToDashboard dashboard = new BackToDashboard();
        this.view.BackToDashboard(dashboard);    
    }

    // ✅ FIXED: Constructor with email and user ID
    public BookingController(BookingView view, String email, int userId) {
        this.view = view; // ✅ FIXED: Correct assignment
        this.email = email;
        this.loggedInUserId = userId; // ✅ FIXED: Set user ID
        this.dao = new BookingDao(); // ✅ FIXED: Initialize DAO
        
        // Initialize event listeners
        BookButton bookButton = new BookButton();
        this.view.BookButton(bookButton);
        
        Logout logout = new Logout();
        this.view.Logout(logout);
        
        BackToDashboard dashboard = new BackToDashboard();
        this.view.BackToDashboard(dashboard);
    }

    // Inner class to handle book button click
    class BookButton implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            try {
                // Get data from form fields
                String vehicleInfo = view.getVehicleInfo().getText().trim();
                String startDate = view.getStartDateField().getText().trim();
                String endDate = view.getEndDateField().getText().trim();
                String totalAmountText = view.getTotalAmountField().getText().trim();

                // ✅ FIXED: Validate input fields
                if (vehicleInfo.isEmpty() || startDate.isEmpty() || endDate.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Please fill all required fields!", "Missing Information", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // ✅ UPDATED: Parse total amount (removed numberOfVehicles calculation)
                double totalAmount = 0.0;

                try {
                    // Extract numeric value from total amount (remove "Rs " prefix if present)
                    String cleanAmount = totalAmountText.replaceAll("[^0-9.]", "");
                    totalAmount = Double.parseDouble(cleanAmount);
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(view, "Invalid total amount format!", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // ✅ UPDATED: Create booking with default numberOfVehicles = 1
                Booking booking = new Booking(
                        0, // ID will be auto-incremented by DB
                        loggedInUserId,
                        vehicleInfo,
                        startDate,
                        endDate,
                        1, // ✅ DEFAULT: Always 1 vehicle since field removed from UI
                        totalAmount
                );

                System.out.println("🔄 Creating booking for user ID: " + loggedInUserId);
                System.out.println("📋 Vehicle: " + vehicleInfo);
                System.out.println("📅 Dates: " + startDate + " to " + endDate);
                System.out.println("💰 Total: Rs " + totalAmount);
                System.out.println("🚗 Vehicles: 1 (default)");

                // ✅ FIXED: Insert booking into database
                boolean success = dao.insertBooking(booking);
                
                if (success) {
                    System.out.println("✅ Booking successful!");
                    JOptionPane.showMessageDialog(view, 
                        "🎉 Booking Successful!\n\n" +
                        "Vehicle: " + vehicleInfo + "\n" +
                        "Dates: " + startDate + " to " + endDate + "\n" +
                        "Total: Rs " + totalAmount, 
                        "Booking Confirmed", 
                        JOptionPane.INFORMATION_MESSAGE);
                    
                    // Navigate back to dashboard
                    DashboardView dashboardView = new DashboardView();
                    DashboardController dashboardController = new DashboardController(dashboardView);
                    dashboardController.open();
                    close();
                } else {
                    System.out.println("❌ Booking failed!");
                    JOptionPane.showMessageDialog(view, 
                        "❌ Booking Failed!\n\nPlease try again or contact support.", 
                        "Booking Error", 
                        JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception ex) {
                System.err.println("❌ Exception in booking: " + ex.getMessage());
                ex.printStackTrace();
                JOptionPane.showMessageDialog(view, 
                    "❌ Something went wrong!\n\nError: " + ex.getMessage(), 
                    "System Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
            }
        }

    class Logout implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            int choice = JOptionPane.showConfirmDialog(
                view,
                "Are you sure you want to logout?",
                "Confirm Logout",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );
            
            if (choice == JOptionPane.YES_OPTION) {
                System.out.println("🚪 User logging out...");
                LoginForm login = new LoginForm();
                LoginController loginController = new LoginController(login);
                loginController.open();
                close();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    class BackToDashboard implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("🔙 Navigating back to dashboard...");
            DashboardView dashboard = new DashboardView();
            DashboardController dashboardController = new DashboardController(dashboard);
            dashboardController.open();
            close();
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    public void open() {
        view.setVisible(true);
        view.setLocationRelativeTo(null); // ✅ ADDED: Center window
    }

    public void close() {
        view.dispose();
    }
}