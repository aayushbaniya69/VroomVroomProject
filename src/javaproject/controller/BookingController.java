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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javaproject.view.DashboardView;
import javaproject.view.LoginForm;

public class BookingController {
    private BookingView view;
    private BookingDao dao;
    private int loggedInUserId;
    private String email;

    public BookingController(BookingView view) {
        this.view = view;
        BookButton bookButton=new BookButton();
        this.view.BookButton(bookButton);
        Logout logout=new Logout();
        this.view.Logout(logout);
        BackToDashboard dashboard=new BackToDashboard();
        this.view.BackToDashboard(dashboard);    
    }

    BookingController(BookingView booking, String email) {
        this.view=view;
        this.email=email;
    }
    // Inner class to handle book button click
    class BookButton implements MouseListener {


        @Override
        public void mouseClicked(MouseEvent e) {
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
                    DashboardView dashboardView=new DashboardView();
                    DashboardController dashboardController=new DashboardController(dashboardView);
                    dashboardController.open();
                close();
                } else {
                    JOptionPane.showMessageDialog(null, "Booking Failed!");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number of vehicles.");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Something went wrong!");
            }        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    private double calculateTotal(int numVehicles) {
        double pricePerVehicle = 5000.00;
        return numVehicles * pricePerVehicle;
    }

class Logout implements MouseListener{


        @Override
        public void mouseClicked(MouseEvent e) {
        LoginForm login=new LoginForm();
        LoginController loginController=new LoginController(login);
        loginController.open();
        close();
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    
}
class BackToDashboard implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
        DashboardView dashboard=new DashboardView();
        DashboardController dashboardController=new DashboardController(dashboard);
        dashboardController.open();
        close();        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    
}
public void open(){
    view.setVisible(true);
}
public void close(){
    view.dispose();
}
}

