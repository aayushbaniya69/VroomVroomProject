package javaproject.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javaproject.model.SellerData;
import javaproject.view.AdminDashboardView;
import javaproject.view.BookingView;
import javaproject.view.Adminpage;
import javaproject.view.LoginForm;
import javax.swing.JOptionPane;

public class SellerDashboardController {
    AdminDashboardView view;
    SellerData seller;
    private String email;

    // FIX: Constructor with SellerData - extract email properly
    public SellerDashboardController(AdminDashboardView view, SellerData seller) {
        this.view = view;
        this.seller = seller;
        this.email = seller != null ? seller.getEmail() : null; // FIX: Extract email from SellerData
        
        System.out.println("SellerDashboardController created with SellerData");
        System.out.println("Email extracted: '" + this.email + "'");
        
        initializeListeners();
    }

    // FIX: Constructor with email
    public SellerDashboardController(AdminDashboardView view, String email) {
        this.view = view;
        this.email = email;
        
        System.out.println("SellerDashboardController created with email: '" + this.email + "'");
        
        initializeListeners();
    }

    // FIX: Centralized listener initialization
    private void initializeListeners() {
        BackLogin backLogin = new BackLogin();
        this.view.BackLogin(backLogin);
        AdminProfile adminProfile = new AdminProfile();
        this.view.adminProfile(adminProfile);
        Booking bookings = new Booking();
        this.view.booking(bookings);
    }

    public void open() {
        view.setVisible(true);
    }

    public void close() {
        view.dispose();
    }

    // FIX: Add method to test email parameter
    public void testEmailParameter() {
        System.out.println("=== SELLER DASHBOARD EMAIL TEST ===");
        System.out.println("Email field: '" + email + "'");
        System.out.println("Seller object: " + seller);
        if (seller != null) {
            System.out.println("Seller email: '" + seller.getEmail() + "'");
            System.out.println("Seller fullName: '" + seller.getFullName() + "'");
        }
        System.out.println("=== END TEST ===");
    }

    class BackLogin implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("Back to login button clicked");
            LoginForm login = new LoginForm();
            LoginController loginController = new LoginController(login);
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
            System.out.println("Booking button clicked");
            BookingView bookingView = new BookingView();
            BookingController bookingController = new BookingController(bookingView);
            bookingController.open();
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
            System.out.println("Admin Profile button clicked");
            
            // FIX: Better email validation and extraction
            String adminEmail = email;
            if (adminEmail == null || adminEmail.trim().isEmpty()) {
                if (seller != null && seller.getEmail() != null) {
                    adminEmail = seller.getEmail();
                } else {
                    JOptionPane.showMessageDialog(view, 
                        "Admin email not found. Please login again.", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            
            System.out.println("Opening admin profile for email: '" + adminEmail + "'");
            Adminpage adminPage = new Adminpage();
            AdminProfileController adminProfileController = new AdminProfileController(adminPage, adminEmail);
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