/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javaproject.model.SellerData;
import javaproject.view.AdminDashboardView;
import javaproject.view.BookingView;
import javaproject.view.SellerLoginForm;
import javaproject.view.Adminpage;

/**
 *
 * @author ACER
 */
public class SellerDashboardController {
    AdminDashboardView view;
    SellerData seller;
    private String email;

    // Constructor with SellerData
    public SellerDashboardController(AdminDashboardView view, SellerData seller) {
        this.view = view;
        this.seller = seller;
        this.email = seller != null ? seller.getEmail() : null; // Initialize email from SellerData
        BackLogin backLogin = new BackLogin();
        this.view.BackLogin(backLogin);
        AdminProfile adminProfile = new AdminProfile();
        this.view.adminProfile(adminProfile);
        Booking bookings = new Booking();
        this.view.booking(bookings);
    }

    // Constructor with email
    public SellerDashboardController(AdminDashboardView view, String email) {
        this.view = view;
        this.email = email; // Initialize email
    }

    public void open() {
        view.setVisible(true);
    }

    public void close() {
        view.dispose();
    }

    class BackLogin implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("Back button");
            SellerLoginForm login = new SellerLoginForm();
            SellerLoginController loginController = new SellerLoginController(login);
            loginController.open();
            close();
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }

    class Booking implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("Booking Page");
            BookingView login = new BookingView();
            BookingController loginController = new BookingController(login);
            loginController.open();
            close();
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }

    class AdminProfile implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (email == null) {
                System.err.println("Error: Email is null in AdminProfile listener");
                return;
            }
            System.out.println("Opening Admin Profile");
            Adminpage adminPage = new Adminpage();
            AdminProfileController adminProfileController = new AdminProfileController(adminPage, email);
            adminProfileController.open();
            close();
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }
}