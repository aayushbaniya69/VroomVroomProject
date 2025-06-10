/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaproject.dao.BookingDao;
import javaproject.model.Booking;
import javaproject.model.UserData;
import javaproject.view.BookingView;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class BookingController {
    private BookingView view;
    private UserData user;

    public BookingController(BookingView view, UserData user) {
        this.view = view;
        this.user = user;

        this.view.BookButton(new BookVehicleListener());
        this.view.BackToDashbord(new BackListener());
        this.view.Logout(new LogoutListener());
    }

    public void open() {
        view.setVisible(true);
    }

    class BookVehicleListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String startDate = view.getStartDateField().getText();
                String endDate = view.getEndDateField().getText();
                int numVehicles = Integer.parseInt(view.getNumberOfVehicle().getText());
                String vehicleInfo = view.getVehicleInfo().getText();
                double pricePerVehicle = 5000.0; // Example

                double total = numVehicles * pricePerVehicle;
                Booking booking = new Booking();
                booking.setUserId(user.getId());
                booking.setVehicleInfo(vehicleInfo);
                booking.setStartDate(startDate);
                booking.setEndDate(endDate);
                booking.setNumberOfVehicles(numVehicles);
                booking.setTotalAmount(total);

                BookingDao dao = new BookingDao();
                boolean success = dao.insertBooking(booking);

                if (success) {
                    JOptionPane.showMessageDialog(view, "Booking successful. Total: Rs " + total);
                } else {
                    JOptionPane.showMessageDialog(view, "Booking failed. Please try again.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Invalid input: " + ex.getMessage());
            }
        }
    }

    class BackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.dispose();
            DashboardView dashboard = new DashboardView();
            new DashboardController(dashboard, user).open();
        }
    }

    class LogoutListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.dispose();
            LoginForm login = new LoginForm();
            new LoginController(login).open();
        }
    }
}
