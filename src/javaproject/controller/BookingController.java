package javaproject.controller;

import javaproject.model.Booking;
import javaproject.view.BookingView;
import javaproject.dao.BookingDao;
import javaproject.view.DashboardView;
import javaproject.view.LoginForm;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BookingController {
    private BookingView view;
    private BookingDao dao;
    private int loggedInUserId;
    private String email;

    public BookingController(BookingView view) {
        this.view = view;
        this.dao = new BookingDao();
        this.loggedInUserId = 1;

        initListeners();
    }

    public BookingController(BookingView view, String email, int userId) {
        this.view = view;
        this.email = email;
        this.loggedInUserId = userId;
        this.dao = new BookingDao();

        initListeners();
    }

    private void initListeners() {
        this.view.BookButton(new BookButton());
        this.view.BackToDashboard(new BackToDashboard());
        this.view.Logout(new Logout());
    }

    class BookButton implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            try {
                String vehicleInfo = view.getVehicleInfo().getText().trim();
                String startDate = view.getStartDateField().getText().trim();
                String endDate = view.getEndDateField().getText().trim();
                String totalAmountText = view.getTotalAmountField().getText().trim();

                if (vehicleInfo.isEmpty() || startDate.isEmpty() || endDate.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Please fill all required fields!", "Missing Info", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                double totalAmount;
                try {
                    totalAmount = Double.parseDouble(totalAmountText.replaceAll("[^0-9.]", ""));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(view, "Invalid total amount!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Booking booking = new Booking(0, loggedInUserId, vehicleInfo, startDate, endDate, 1, totalAmount);
                boolean success = dao.insertBooking(booking);

                if (success) {
                    JOptionPane.showMessageDialog(view,
                            "Booking Successful!\nVehicle: " + vehicleInfo +
                                    "\nDate: " + startDate + " to " + endDate +
                                    "\nTotal: Rs " + totalAmount,
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                    new DashboardController(new DashboardView()).open();
                    close();
                } else {
                    JOptionPane.showMessageDialog(view, "Booking failed!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(view, "System Error: " + ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
            }
        }

        @Override public void mousePressed(MouseEvent e) {}
        @Override public void mouseReleased(MouseEvent e) {}
        @Override public void mouseEntered(MouseEvent e) {}
        @Override public void mouseExited(MouseEvent e) {}
    }

    class BackToDashboard implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            new DashboardController(new DashboardView()).open();
            close();
        }

        @Override public void mousePressed(MouseEvent e) {}
        @Override public void mouseReleased(MouseEvent e) {}
        @Override public void mouseEntered(MouseEvent e) {}
        @Override public void mouseExited(MouseEvent e) {}
    }

    class Logout implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            int confirm = JOptionPane.showConfirmDialog(view, "Logout?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                new LoginController(new LoginForm()).open();
                close();
            }
        }

        @Override public void mousePressed(MouseEvent e) {}
        @Override public void mouseReleased(MouseEvent e) {}
        @Override public void mouseEntered(MouseEvent e) {}
        @Override public void mouseExited(MouseEvent e) {}
    }

    public void open() {
        view.setVisible(true);
        view.setLocationRelativeTo(null);
    }

    public void close() {
        view.dispose();
    }
}
