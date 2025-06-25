package javaproject.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaproject.dao.AdminProfileDao;
import javaproject.dao.SellerDao;
import javaproject.model.LoginRequest;
import javaproject.model.SellerData;
import javaproject.view.AdminDashboardView;
import javaproject.view.ChangePassword;
import javaproject.view.Adminpage;
import javax.swing.JOptionPane;

public class AdminChangePasswordController {
    private ChangePassword changePassword;
    private String email;
    
    public AdminChangePasswordController(ChangePassword changePassword, String email) {
        this.changePassword = changePassword;
        this.email = email;
        
        if (email != null && !email.trim().isEmpty()) {
            changePassword.getEmail().setText(email);
        }
        
        // Set up button listeners
        changePassword.BackToDashboard(new BackToProfile());
        changePassword.ChangePassword(new ChangePasswordAction());
    }
    
    public void open() { 
        changePassword.setVisible(true); 
    }
    
    public void close() { 
        changePassword.dispose(); 
    }
    
    // Back button - goes to ADMIN profile
    class BackToProfile implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Adminpage adminPage = new Adminpage();
            AdminProfileController controller = new AdminProfileController(adminPage, email);
            controller.open();
            close();
        }
    }
    
    // Change password button - uses ADMIN DAO
    class ChangePasswordAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String currentEmail = changePassword.getEmail().getText().trim();
            String oldPassword = new String(changePassword.getCurrentPassword().getPassword()).trim();
            String newPassword = new String(changePassword.getNewPassword().getPassword()).trim();
            String confirmPassword = new String(changePassword.getConfirmPassword().getPassword()).trim();

            // Validation
            if (currentEmail.isEmpty() || oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(changePassword, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(changePassword, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                // Use SELLER DAO for verification
                SellerDao sellerDAO = new SellerDao();
                LoginRequest loginRequest = new LoginRequest(currentEmail, oldPassword);
                SellerData seller = sellerDAO.loginSeller(loginRequest);
                
                if (seller == null) {
                    JOptionPane.showMessageDialog(changePassword, "Current password is incorrect.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Use ADMIN DAO for password update
                AdminProfileDao adminDao = new AdminProfileDao();
                SellerData updatedSeller = new SellerData(
                    seller.getFullName(),
                    seller.getEmail(),
                    seller.getLocation(),
                    seller.getContactNumber(),
                    newPassword
                );
                
                boolean updated = adminDao.changePassword(updatedSeller);
                if (updated) {
                    JOptionPane.showMessageDialog(changePassword, "Password changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    
                    // Go back to ADMIN dashboard
                    changePassword.dispose();
                    AdminDashboardView dashboard = new AdminDashboardView();
                    SellerDashboardController dashboardController = new SellerDashboardController(dashboard, currentEmail);
                    dashboardController.open();
                } else {
                    JOptionPane.showMessageDialog(changePassword, "Failed to change password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(changePassword, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}