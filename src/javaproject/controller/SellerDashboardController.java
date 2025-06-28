package javaproject.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javaproject.model.SellerData;
import javaproject.view.AdminDashboardView;
import javaproject.view.AdminVehiclePanel;
import javaproject.view.BookingView;
import javaproject.view.Adminpage;
import javaproject.view.LoginForm;
import javax.swing.JOptionPane;

public class SellerDashboardController {
    AdminDashboardView view;
    SellerData seller;
    private String email;

    public SellerDashboardController(AdminDashboardView view, SellerData seller) {
        this.view = view;
        this.seller = seller;
        this.email = seller != null ? seller.getEmail() : null;
        System.out.println("SellerDashboardController created with SellerData");
        System.out.println("Email extracted: '" + this.email + "'");
        initializeListeners();
    }

    public SellerDashboardController(AdminDashboardView view, String email) {
        this.view = view;
        this.email = email;
        System.out.println("SellerDashboardController created with email: '" + this.email + "'");
        initializeListeners();
    }

    private void initializeListeners() {
        this.view.setBackToLoginListener(new BackLogin());           // ✅ Correct class name
        this.view.setAdminProfileListener(new AdminProfile());
        this.view.setVehicleButtonListener(new VehicleButtonListener()); // ✅ Added below
    }

    public void open() {
        view.setVisible(true);
    }

    public void close() {
        view.dispose();
    }

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

    // ✅ CORRECTED NAME: BackLogin
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

    // ✅ NEW: For vehicle button
 class VehicleButtonListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Vehicle button clicked");
        
        // This switches the visible panel inside the existing AdminDashboardView window
        view.showVehiclePanel();  // <<--- Put step 2 here!
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}


    class AdminProfile implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("Admin Profile button clicked");
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
